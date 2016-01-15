//form序列化，用于ajax请求：
function getFormfields(formid){        
    var fields =$('#'+formid).serializeArray();
    var $grid = $('#'+datagrid);  
    var params = $grid.datagrid('options').queryParams;  
    $.each( fields, function(i, field){
        params[field.name] = field.value;
    });  
    return params;
}  



//表格根据form内容查询：
function doSearchGrid(formid,datagrid){     
    var fields =$('#'+formid).serializeArray();
    var $grid = $('#'+datagrid);  
    var params = $grid.datagrid('options').queryParams;  
    $.each( fields, function(i, field){
        params[field.name] = field.value;
    });  
    $grid.datagrid('reload');
}  


//打开tab页通用方法
function OpenTab(tabid,text,url,iconcls) {
   if ($("#"+tabid).tabs('exists', text)) 
    {
        $("#"+tabid).tabs("select",text);
       var nowtab = $("#"+tabid).tabs('getSelected').panel("refresh",url);
   } 
   else
   {
       $("#"+tabid).tabs('add', {
           title : text,
           closable : true,
           href: url,
           iconCls:iconcls,
           refreshable:false
       });
       
   }
}

function waitloading(message) {  
    $("<div class=\"datagrid-mask\" id=\"myloadingmask\" style=\"position:absolute;z-index: 1000; top: 0px; left: 0px;  \"> </div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" id=\"myloadingmsg\" style=\"z-index: 1001;\"></div>").html(message).appendTo("body").css({ display: "block", left: ($(document.body).outerWidth(true) - 190) / 2, top: ($(window).height() - 45) / 2 });  
    }

function removeloading() {
    $("#myloadingmask").remove();  
    $("#myloadingmsg").remove();  
}  

function showbigimg(jqimgin)
{
   var imgsrc=jqimgin.attr('src');
    $('#imgwin').remove();
    $('body').append('<div id="imgwin" style="margin:3px;text-align:center;"></div> ');
		$('#imgwin').html('<img id="showbigimg" src="'+imgsrc+'" />');
		
		$('#imgwin').window({ 
		    title:'图片查看',
		    width:620,    
		    height:490,    
		    modal:true,
		    collapsible:false,
		    minimizable:false,
		    maximizable:true,
		    onMaximize:function(width, height){
		        $('#showbigimg').removeAttr('style');
		    },
		    onRestore:function(){
		        $('#showbigimg').width(600);
             $('#showbigimg').height(450);
		    }
		});  
    $('#showbigimg').width(600);
    $('#showbigimg').height(450);

}