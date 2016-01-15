/*
json 数据格式如下
var addressdata = [{"id":2,"name":"\u5317\u4eac","child":[{"id":36,"name":"\u5317\u4eac\u5e02","child":[

传入参数要求：addressdata，省selectid，市selectid，区selectid
对象结构要求是下属 三个span，以此省，市，区
<p class="svselall" limitin="济南市,青岛市"><span>山东</span> <span>济南市</span>  <span>高新区</span></p>

*/

$.fn.SetAreaSelect = function(areadata,provinceid,eparchyid,areaid) {
	_this=this;
	_this.provcenhtml='<option value="">省份/自治区</option>';
	_this.eparchyhtml='<option value="">城市</option>';
	_this.areahtml='<option value="">区/县</option>';
	
	_this.svselallp=$(this);
	var provencename=_this.svselallp.find('span:eq(0)').text();
	var eparchyname=_this.svselallp.find('span:eq(1)').text();
	var areaname=_this.svselallp.find('span:eq(2)').text();
	
	var listeparchystr=_this.svselallp.attr('limitin');
	var whiteeparchy= new Array();
	if(typeof(listeparchystr)!="undefined"&&listeparchystr)
	{
		whiteeparchy=listeparchystr.split(",");
	}

	
	_this.seteparchyoption= function (data,optionid,basehtml,checkname)
		{ 
		      var optionhtml=basehtml;
		      var tempnowid='';
		      $('#' + optionid).empty();
			  $.each(data,function(n,value) {
			  	
			  	  if(whiteeparchy.length>0&&$.inArray(value.name, whiteeparchy)>=0)
			  	  {
			  	  	if(value.name==checkname)
			  	    {
			    	optionhtml=optionhtml+'<option value="'+value.id+'" selected >'+value.name+'</option>' ;
			  	    }
			  	    else
			  	    {
			  	    optionhtml=optionhtml+'<option value="'+value.id+'">'+value.name+'</option>' ;	
			  	    }
			  	  }
			  	  else if(typeof(listeparchystr)=="undefined")
			  	  {
			  	  	if(value.name==checkname)
			  	    {
			    	optionhtml=optionhtml+'<option value="'+value.id+'" selected >'+value.name+'</option>' ;
			  	    }
			  	    else
			  	    {
			  	    optionhtml=optionhtml+'<option value="'+value.id+'">'+value.name+'</option>' ;	
			  	    }
			  	  }
			  	    
			    });
			    $('#'+optionid).append(optionhtml);
			    $('#'+optionid).trigger("change",tempnowid); 

		};
	
	
	_this.setoption= function (data,optionid,basehtml,checkname)
		{ 
		      var optionhtml=basehtml;
		      var tempnowid='';
		      $('#' + optionid).empty();
			  $.each(data,function(n,value) {
			  	    if(value.name==checkname)
			  	    {
			    	optionhtml=optionhtml+'<option value="'+value.id+'" selected >'+value.name+'</option>' ;
			    	tempnowid=value.id;
			  	    }
			  	    else
			  	    {
			  	    optionhtml=optionhtml+'<option value="'+value.id+'">'+value.name+'</option>' ;	
			  	    }
			    });
			    $('#'+optionid).append(optionhtml);
			    $('#'+optionid).trigger("change",tempnowid); 

		};
		
      _this.getnowobj =function  (data,key,value)
			{
			   return $.grep(data, function(cur,i){
			          return cur[key]==value;
			       });
			};
	   var  nowprovince=_this.getnowobj(areadata,'name',provencename)[0];
	   var  nowearchy=_this.getnowobj(nowprovince.child,'name',eparchyname)[0];
	  
	    $('#' + provinceid).empty();
	    $('#' + eparchyid).empty();
		$('#' + areaid).empty();
		
		_this.setoption(areadata,provinceid,_this.provcenhtml,provencename);
	    _this.seteparchyoption(nowprovince.child,eparchyid,_this.eparchyhtml,eparchyname);
	    _this.setoption(nowearchy.child,areaid,_this.areahtml,areaname);
};

	

