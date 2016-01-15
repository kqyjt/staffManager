$(document).ready(function() {
	
	$('.grzx_resourcekind').click(function (event){
		Maskpage();
	  	$(this).siblings().removeClass('cx_clexbox');
	  	$(this).addClass('cx_clexbox');
	  	var prodCatId = $(this).attr("res");
	  	//查询资源预判
	  	var clientRequest = new emarketing.ClientRequest();
		var url= managerPath +"/manager/order/SearchManagerAddr.json?m=query&f=antiLaunch";
		var params = new Object();
		 var localNetId = $("#cityselect").val();
		 var addressCode = $("input[name='chooseaddress']:radio:checked").attr("addressCode");
		 var exchCode = $("input[name='chooseaddress']:radio:checked").attr("exchCode");

		 params.localNetId=localNetId;
		 params.addressCode=addressCode;
		 params.exchCode=exchCode;
		 params.prodCatId=prodCatId;
		 
		clientRequest.postJSON(url,params,function(response) {

			if(response.result.errorCode =="00000")
			{
				 var obj=response.result.dataSet.reponseMap.netResourceList;
				 var tdHtml="";
				 if(obj.length>0)
				 {
					 for(var i=0;i<obj.length;i++)
					 {
						 tdHtml = tdHtml+'<td class="t_c"><span>'+obj[i].brandCode+'</span></td><td class="t_c"><span>'+obj[i].accessType+'</span></td><td class="t_c"><span class="c_orger">'+obj[i].typeName+'</span></td><td class="t_c"><span class="c_orger">'+obj[i].maxRate+'</span></td><td class="t_c"><span>'+obj[i].exchCode+'</span></td><td style="border-right:none;" class="t_c"><span>'+obj[i].exchName+'</span></td>';
					 }
				 }
				 else
				 {
					 tdHtml='<td colspan="6" class="t_c"><span class="c_orger">没有资源信息</span></td>';
				 }
				 
				$('#dataResourceList').html(tdHtml);
			}
			else
			{
				Alertone(response.result.dataSet.errorDetail);
			}
		});
		CloseMaskpage();
	  });
	
	//查询地址
	$('#searchbtn').click(function (event){
		 Maskpage();
		 $("#displayresarea").hide();
		 $('.grzx_resourcekind').removeClass('cx_clexbox');
		 $("#dataResourceList").html('<td colspan="6" class="t_c"><span class="c_orger">请选择资源类别</span></td>');
		var clientRequest = new emarketing.ClientRequest();
		var url = managerPath +"/manager/order/SearchManagerAddr.json?m=query&f=searchLaunch";
		var params = new Object();
		 var localNetId = $("#cityselect").val();
		 var qryType = $("#qryType").val();
		 var addrKeywords = $("#addrKeywords").val();

		 params.LocalNetId=localNetId;
		 params.qryType=qryType;
		 params.addrKeywords=addrKeywords;
		 
		clientRequest.postJSON(url,params,function(response) {

			if(response.result.errorCode =="00000")
			{
				 var obj=response.result.dataSet.reponseMap.addressList;
				 var tdHtml="";
				 if(obj.length>0)
				 {
					 for(var i=0;i<obj.length;i++)
					 {
						 if(i==0)
						 {
							 tdHtml = tdHtml+'<tr><td class="t_c grzx_huise_ed"><input type="radio" onchange="rechoseaddress()" checked="checked" name="chooseaddress" class="chooseaddress" exchCode="'+obj[i].exchCode+'"   addressCode="'+obj[i].addressCode+'"  /></td><td class="t_l"><span class="c_red F14">'+obj[i].addressName+'</span></td><td class="t_c"><span>'+obj[i].addressCode+'</span></td><td class="t_c"><span>'+obj[i].exchName+'</span></td><td style="border-right:none;" class="t_c"><span>'+obj[i].exchCode+'</span></td></tr>';
						 }
						 else
						 {
							 tdHtml = tdHtml+'<tr><td class="t_c grzx_huise_ed"><input type="radio" onchange="rechoseaddress()"  name="chooseaddress" class="chooseaddress" exchCode="'+obj[i].exchCode+'"   addressCode="'+obj[i].addressCode+'"  /></td><td class="t_l"><span class="c_red F14">'+obj[i].addressName+'</span></td><td class="t_c"><span>'+obj[i].addressCode+'</span></td><td class="t_c"><span>'+obj[i].exchName+'</span></td><td style="border-right:none;" class="t_c"><span>'+obj[i].exchCode+'</span></td></tr>';
						 }
						 
						 $("#displayresarea").show();
					 }
				 }
				 else
				 {
					 tdHtml='<tr><td colspan="6" class="t_c"><span class="c_orger">标准地址库中没有对应地址信息</span></td></tr>';
				 }
				 
				$('#dataAddressList').html(tdHtml);
			}
			else
			{
				Alertone(response.result.dataSet.errorDetail);
			}
		});
		CloseMaskpage();
	});
	
});

function rechoseaddress()
{
	$('.grzx_resourcekind').removeClass('cx_clexbox');
	$("#dataResourceList").html('<td colspan="6" class="t_c"><span class="c_orger">请选择资源类别</span></td>');
}
