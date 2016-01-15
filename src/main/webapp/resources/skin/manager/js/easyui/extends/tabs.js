
/*使用方式：
$(function(){
    $('#tb').tabs('bindDblclick', function(index, title){
        alert(title + ':' + index);
    });
});
*/
$.extend($.fn.tabs.methods, {
    /**
     * 绑定双击事件
     * @param {Object} jq
     * @param {Object} caller 绑定的事件处理程序
     */
    bindDblclick: function(jq, caller){
        return jq.each(function(){
            var that = this;
            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs').delegate('li', 'dblclick.tabs', function(e){
                if (caller && typeof(caller) == 'function') {
                    var title = $(this).text();
                    var index = $(that).tabs('getTabIndex', $(that).tabs('getTab', title));
                    caller(index, title);
                }
            });
        });
    },
    /**
     * 解除绑定双击事件
     * @param {Object} jq
     */
    unbindDblclick: function(jq){
        return jq.each(function(){
            $(this).children("div.tabs-header").find("ul.tabs").undelegate('li', 'dblclick.tabs');
        });
    }
});


/*实现select和exists两个接口按照ID检索功能
 * 
var tab = $("tabs").tabs("selectById","tabId");
var isExist = $("tabs").tabs("existsById","tabId");
这段代码扩展了三个接口，但是只有selectById和existById才会直接用到，使用这两个方法的时候传入ID参数即可。需要注意的是：标签页的ID属性不是自动产生的，要在使用add方法或者用DOM初始化时设置对应panel的id属性。
 * 
 * */

$.extend($.fn.tabs.methods, {
    getTabById: function(jq,id) {
        var tabs = $.data(jq[0], 'tabs').tabs;
        for(var i=0; i<tabs.length; i++){
            var tab = tabs[i];
            if (tab.panel('options').id == id){
                return tab;
            }
        }
        return null;
    },
    selectById:function(jq,id) {
        return jq.each(function() {
            var state = $.data(this, 'tabs');
            var opts = state.options;
            var tabs = state.tabs;
            var selectHis = state.selectHis;
            if (tabs.length == 0) {return;}
            var panel = $(this).tabs('getTabById',id); // get the panel to be activated 
            if (!panel){return}
            var selected = $(this).tabs('getSelected');
            if (selected){
                if (panel[0] == selected[0]){return}
                $(this).tabs('unselect',$(this).tabs('getTabIndex',selected));
                if (!selected.panel('options').closed){return}
            }
            panel.panel('open');
            var title = panel.panel('options').title;        // the panel title 
            selectHis.push(title);        // push select history 
            var tab = panel.panel('options').tab;        // get the tab object 
            tab.addClass('tabs-selected');
            // scroll the tab to center position if required. 
            var wrap = $(this).find('>div.tabs-header>div.tabs-wrap');
            var left = tab.position().left;
            var right = left + tab.outerWidth();
            if (left < 0 || right > wrap.width()){
                var deltaX = left - (wrap.width()-tab.width()) / 2;
                $(this).tabs('scrollBy', deltaX);
            } else {
                $(this).tabs('scrollBy', 0);
            }
            $(this).tabs('resize');
            opts.onSelect.call(this, title, $(this).tabs('getTabIndex',panel));
        });
    },
    existsById:function(jq,id){
        return $(jq[0]).tabs('getTabById',id) != null;
    }
});
