
(function ($, K) {
    if (!K)
        throw "KindEditor未定义!";

    function create(target) {
        var opts = $.data(target, 'kindeditor').options;
        var editor = K.create(target, opts);
        $.data(target, 'kindeditor').options.editor = editor;
    }

    $.fn.kindeditor = function (options, param) {
        if (typeof options == 'string') {
            var method = $.fn.kindeditor.methods[options];
            if (method) {
                return method(this, param);
            }
        }
        options = options || {};
        return this.each(function () {
            var state = $.data(this, 'kindeditor');
            if (state) {
                $.extend(state.options, options);
            } else {
                state = $.data(this, 'kindeditor', {
                        options : $.extend({}, $.fn.kindeditor.defaults, $.fn.kindeditor.parseOptions(this), options)
                    });
            }
            create(this);
        });
    }

    $.fn.kindeditor.parseOptions = function (target) {
        return $.extend({}, $.parser.parseOptions(target, []));
    };

    $.fn.kindeditor.methods = {
        editor : function (jq) {
            return $.data(jq[0], 'kindeditor').options.editor;
        },
        removeit: function(jq) {
        	$.data(jq[0], 'kindeditor').options.editor.remove();
        }
    };

    $.fn.kindeditor.defaults = {
    	uploadJson : 'jslib/kindeditor/upload/upload_json.jsp',
    	allowFileManager : false,
    	resizeType:1,
        items:
        [
        'source', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
        'italic', 'underline', 'strikethrough', '|', 
        'emoticons', 'link', 'unlink','|','undo', 'redo', '|', 'justifyleft', 'justifycenter', 'justifyright',
        'justifyfull'
         ],
        /*afterCreate : function() {
          var nowwitdh = K.removeUnit(this.width);
        },*/
        afterChange:function(){
             this.sync();
             /*var minHeight = K.removeUnit(this.height);
             var body = this.edit.doc.body;
             this.resize(null, Math.max((K.IE ? body.scrollHeight-12 : body.offsetHeight) + 62, minHeight));
             */
             //this.edit.setHeight(Math.max((K.IE ? body.scrollHeight : body.offsetHeight) + 62, minHeight));
        }
    };
    $.parser.plugins.push("kindeditor");
})(jQuery, KindEditor);