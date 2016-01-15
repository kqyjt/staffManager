/*
json 数据格式如下
var addressdata = [{"id":2,"name":"\u5317\u4eac","child":[{"id":36,"name":"\u5317\u4eac\u5e02","child":[

传入参数要求：addressdata，省selectid，市selectid，区selectid

*/

$.AreaSelect = function(areadata,provinceid,eparchyid,areaid,callback) {
	_this=this;
	_this.provcenhtml='<option value="">省份/自治区</option>';
	_this.eparchyhtml='<option value="">城市</option>';
	_this.areahtml='<option value="">区/县</option>';
	
	_this.setoption= function (data,optionid,basehtml)
		{ 
		      var optionhtml=basehtml;
		      $('#' + optionid).empty();
			  $.each(data,function(n,value) {
			    	optionhtml=optionhtml+'<option value="'+value.id+'">'+value.name+'</option>' ;
			    });
			    $('#'+optionid).append(optionhtml);

		};
		
      _this.getnowobj =function  (data,key,value)
			{
			   return $.grep(data, function(cur,i){
			          return cur[key]==value;
			       });
			};
	
	
			    $('#' + provinceid).empty();
				$('#'+provinceid).append(_this.provcenhtml);
	            $('#' + eparchyid).empty();
				$('#'+eparchyid).append(_this.eparchyhtml);
				$('#' + areaid).empty();
				$('#'+areaid).append(_this.areahtml);
	         _this.setoption(areadata,provinceid,_this.provcenhtml);

			 var nowprovince=new Object();
			 
		      $('#'+provinceid).change(function(){
		      	var opid=$(this).val();
		      	nowprovince=_this.getnowobj(areadata,'id',opid)[0];
		      	$('#' + eparchyid).empty();
				$('#'+eparchyid).append(_this.eparchyhtml);
				$('#' + areaid).empty();
				$('#'+areaid).append(_this.areahtml);
		      	if(typeof(nowprovince) != 'undefined')
					{
		      	    _this.setoption(nowprovince.child,eparchyid,_this.eparchyhtml);
		      	    }
					
		      });
		      
		      $('#'+eparchyid).change(function(){
		      	var opid=$(this).val();
		      	var nowearchy=_this.getnowobj(nowprovince.child,'id',opid)[0];
		      	$('#' + areaid).empty();
				$('#'+areaid).append(_this.areahtml);
		      	if(typeof(nowearchy) != 'undefined')
					{
						_this.setoption(nowearchy.child,areaid,_this.areahtml);
					}
				   
		      	
		      });
		      
		      if(typeof(callback) != "undefined"){
			           callback();
		       }

							
};

	

