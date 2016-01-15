/*使用方法
 * $.AlertOne('标题','内容标题','详细内容','按钮1文本',function(num){});
 * */
$.AlertOne = function(title,errtype,errmsg,btntext,callback) {

	$('#laogong-dialog-message').remove();
	
	if(typeof(title)=='undefined')
	{
		title="";
	}
	if(typeof(errtype)=='undefined')
	{
		errtype="";
	}
	if(typeof(errmsg)=='undefined')
	{
		errmsg="";
	}
	if(typeof(btntext)=='undefined')
	{
		btntext="确定";
		
	}
	
	var dialogdiv='<div id="laogong-dialog-message" class="hide" >'
				+'<p>'
				+'	<b>'+errtype+'</b>'
				+'</p>'
				+'<div class=\"hr hr-12 hr-double\"></div>'
				+'<p>'
				+errmsg
				+'</p>'
				+'</div>';
	
	$('body').append(dialogdiv);

   $( "#laogong-dialog-message" ).removeClass('hide').dialog({
		modal: true,
		title: title,
		title_html: true,
		hide: {
	        effect: "explode",
	        duration: 300
	      },
	      show: {
	          effect: "blind",
	          duration: 300
	        },
		buttons: [ 
			{
				text: btntext,
				"class" : "btn btn-primary btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
					if(typeof(callback) != "undefined"){
						callback('1');
					}
				} 
			}
		]
	});
	
}

/*使用方法
 * $.AlertConfirm('标题','内容标题','详细内容','按钮1文本','按钮2文本',function(num){});
 * */

$.AlertConfirm = function(title,errtype,errmsg,btn1text,btn2text,callback) {

	$('#laogong-dialog-confirm').remove();
	
	if(typeof(title)=='undefined')
	{
		title="";
	}
	if(typeof(errtype)=='undefined')
	{
		errtype="";
	}
	if(typeof(errmsg)=='undefined')
	{
		errmsg="";
	}
	if(typeof(btn1text)=='undefined')
	{
		btn1text="确定";
		
	}
	if(typeof(btn2text)=='undefined')
	{
		btn2text="取消";
		
	}
	
	var dialogdiv='<div id="laogong-dialog-confirm" class="hide">'
	+'<p>'
	+'	<b>'+errtype+'</b>'
	+'</p>'
	+'<div class=\"hr hr-12 hr-double\"></div>'
	+'<p>'
	+errmsg
	+'</p>'
	+'</div>';
	
	$('body').append(dialogdiv);

   $( "#laogong-dialog-confirm" ).removeClass('hide').dialog({
		modal: true,
		title: title,
		title_html: true,
		hide: {
	        effect: "explode",
	        duration: 300
	      },
	      show: {
	          effect: "blind",
	          duration: 300
	        },
		buttons: [ 
			{
				text: btn1text,
				"class" : "btn btn-primary btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
					if(typeof(callback) != "undefined"){
						callback('1');
					}
				} 
			},
			{
				text: btn2text,
				"class" : "btn btn-primary btn-xs",
				click: function() {
					$( this ).dialog( "close" ); 
					if(typeof(callback) != "undefined"){
						callback('0');
					}
				} 
			}
		]
	});
	
}

var pageprocessdialog;

$.ProcessWait = function(titlein) {
	$('#laogong-dialog-process').remove();
	var processdiv='<div id="laogong-dialog-process" class="hide"><div id="laogong-progressbar" style="margin:30px;"></div></div>';
	$('body').append(processdiv);
	pageprocessdialog=$( "#laogong-dialog-process" ).removeClass('hide').dialog({
		 title: titlein,
		 resizable: false,
		 modal: true 
	 });
	 $( "#laogong-progressbar" ).progressbar({
	      value: false
	    });
}

$.ProcessClose = function() {

	pageprocessdialog.dialog( "close" );
}