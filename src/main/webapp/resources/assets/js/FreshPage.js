/*
指定局部页面数据刷新
要求行内数据包裹为<span class="key">777777</span>
参数

*/

$.fn.FreshLinebyClass = function(arrayobj) {
// var args = arguments;
 var domobj=$(this);
 var nowid=domobj.attr('id');
 if(typeof(nowid)=='undefined'||nowid.length<1)
 {
 	return;
 }
 var nowobj=$.grep(arrayobj, function(curobj,i){
	            return curobj.id==nowid;
			      });
	if(typeof(nowobj)=='undefined'||nowobj.length==0)
	{
		return;
	}
	else
    {
		$.each(nowobj[0],function(key,value) {
			var jqhtmlobj=domobj.find('.'+key);
            $.SetData(jqhtmlobj,value);
	 	  });
	}
 
};

/*
全部页面数据刷新
要求数据包裹为
<div id="xxxx">
<span class="key">777777</span>
<div>...
<input .....>
<textarea.....>
<select....>
</div>
参数

*/

$.fn.FreshAreabyClass = function(arrayobj) {
// var args = arguments;
 var domobj=$(this);
 $.each(arrayobj,function(i,objone){
 	 var lineobj=domobj.find('#'+objone.id);
 	 if(typeof(lineobj)=='undefined'||lineobj.size()==0)
 	  	{
 	  		return true;
 	  	}
 	  $.each(objone,function(key,value) {
 	  	var jqhtmlobj=lineobj.find('.'+key);
        $.SetData(jqhtmlobj,value);
 	  });
 });
 
};

$.SetData=function (jqhtmlobj,value)
{
	if(typeof(jqhtmlobj)=='undefined'||jqhtmlobj.size()==0)
 	  	{
 	  		 return;
 	  	}
 	  	else
 	  	{
			 var t = jqhtmlobj[0].type, tag = jqhtmlobj[0].tagName.toLowerCase();
			 var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
			  if (re.test(t) || tag == 'textarea') {
			        jqhtmlobj.attr('value',value);
			    }
			  if(tag=='div')
			  {
			  	jqhtmlobj.removeClass('icon-spinner icon-spin icon-2x');
			  	jqhtmlobj.html(value);
			  }
			  if(tag=='label')
			  {
			  	jqhtmlobj.removeClass('icon-spinner icon-spin icon-2x');
			  	jqhtmlobj.html(value);
			  }
			  if(tag=='span')
			  {
			  	jqhtmlobj.removeClass('icon-spinner icon-spin icon-2x');
			  	jqhtmlobj.text(value);
			  }
			  if(t == 'checkbox' || t == 'radio') 
			  	{
				  jqhtmlobj.attr('checked', value);
				}
			  if (tag == 'select') {
			  	 jqhtmlobj.find('option[text="'+value+'"]').attr("selected", true);
			  }
 	  	}
};


/*
模板替换

*/
/*$.fn.TempletData=function(arrayobj,templethtml) {
	var outhtml='';
	$.each(arrayobj,function(i,objone){
		$.each(objone,function(key,value) {
	    var reg = new RegExp('{'+key+'}','g');
 	  	outhtml=templethtml.replace(reg,value);
 	  });
		
	});
	return outhtml;
};
*/

/*
显示刷新的旋转图片，重置input，textarea，span，div的内容为空
*/

$.fn.CycleAreabyClass = function(arraycycle,arrayexp) {
	    var domobj=$(this);
	    if(typeof(arrayexp)!='undefined'&&arrayexp.length!=0)
 	  	{ 
		    $.each(arrayexp,function(i,arrayexpone){
		    	var notfreshdomobj=domobj.find('#'+arrayexpone);
		    	$.each(arraycycle,function(i,arraycycleone){
		    		 var onekindclass=notfreshdomobj.find('.'+arraycycleone);
		    		 onekindclass.addClass('nofresh');
		    	});
		    });
 	  	}
  		$.each(arraycycle,function(i,arrayone){
		 	 var onekindclass=domobj.find('.'+arrayone);
		 	 if(typeof(onekindclass)=='undefined'||onekindclass.size()==0)
		 	  	{
		 	  		return true;
		 	  	}
		 	  $.each(onekindclass,function(index,onedom) {
		 	  	 $.SetCycle($(this));
		 	  });
		 });
	
};

/*$.fn.CycleAreabyClass = function(arraycycle) {
	    var domobj=$(this);
  		$.each(arraycycle,function(i,arrayone){
		 	 var onekindclass=domobj.find('.'+arrayone);
		 	 if(typeof(onekindclass)=='undefined'||onekindclass.size()==0)
		 	  	{
		 	  		return true;
		 	  	}
		 	  $.each(onekindclass,function(index,onedom) {
		 	  	 $.SetCycle($(this));
		 	  });
		 });
	
};*/


$.SetCycle=function (jqhtmlobj)
{
	if(typeof(jqhtmlobj)=='undefined'||jqhtmlobj.size()==0||jqhtmlobj.hasClass('nofresh'))
 	  	{
		     jqhtmlobj.removeClass('nofresh');
 	  		 return;
 	  	}
 	  	else
 	  	{
			 var t = jqhtmlobj[0].type, tag = jqhtmlobj[0].tagName.toLowerCase();
			 var re = /^(?:color|date|datetime|email|month|number|password|range|search|tel|text|time|url|week)$/i;
			  if (re.test(t) || tag == 'textarea') {
			        jqhtmlobj.attr('value','');
			    }
			  if(tag=='div')
			  {
				var objmargleft=jqhtmlobj.width()/2;
				var objmargtop=jqhtmlobj.height()/2;
			  	jqhtmlobj.html('<laber style="margin:'+objmargtop+'px  '+objmargleft+'px;" class="icon-spinner icon-spin icon-2x"> </laber>');
			  	//jqhtmlobj.addClass('icon-spinner icon-spin icon-2x');
			  }
			  if(tag=='label')
			  {
			  	jqhtmlobj.html('');
			  	jqhtmlobj.addClass('icon-spinner icon-spin icon-2x');
			  }
			  if(tag=='span')
			  {
			  	jqhtmlobj.text('');
			  	jqhtmlobj.addClass('icon-spinner icon-spin icon-2x');
			  }
			  if(t == 'checkbox' || t == 'radio') 
			  	{
				  jqhtmlobj.attr('checked', false);
				}
 	  	}
};
