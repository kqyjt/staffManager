$(document).ready(function() {
	if($("#errorMessage")){
		$("#errorMessage").html('');
	}
});

function getPhoneVerifyCode(o){
	//alert(1);
	var serialNumber=$('#SERIAL_NUMBER').val();
	if(!serialNumber || serialNumber==''){
		Alertone('请输入手机号码!');
		return;
	}
	
	if(serialNumber.length != 11){
		Alertone('手机号码不合法!');
		return;
	}
	

	var wait=60;
	if (wait == 0) {  
        o.removeAttribute("disabled");            
        o.value="获取验证码";  
        wait = 60;  
    } else {  
        o.setAttribute("disabled", true);  
        o.value="重新发送(" + wait + ")";  
        wait--;  
        setTimeout(function() {  
        	time(o);  
        },1000);
    }
	function time(o) {
		if (wait == 0) {  
	        o.removeAttribute("disabled");            
	        o.value="获取验证码";  
	        wait = 60;  
	    } else {  
	        o.setAttribute("disabled", true);  
	        o.value="重新发送(" + wait + ")";  
	        wait--;  
	        setTimeout(function() {  
	        	time(o);  
	        },1000);
	    } 
	}
	
	
	var u = contextPath + '/portal/space/VerifyMgr.json?m=execute&f=getverifycode';

	$.ajax({
        url: u,
        secureuri: false,
        data: {SERIAL_NUMBER:serialNumber},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result = respData.result;
        	if(result.success){
        		Alertone('验证码已发送到您的手机!');
        		$("#errorMessage").html('');
	    	}else{
	    		wait=0;
	    		$("#errorMessage").html(result.dataSet.errorDetail);
	    	}
        },
	    error: function(respData){ 
	    	$("#errorMessage").html(result.dataSet.errorDetail);
	    }
    });
}

function checkPhoneVerifyCode(){
	//alert(1);
	var serialNumber=$('#SERIAL_NUMBER').val();
	if(!serialNumber || serialNumber==''){
		Alertone('请输入手机号码!');
		return;
	}
	var checkCode=$('#CHECK_CODE').val();
	if(!checkCode || checkCode==''){
		Alertone('请输入手机验证码!');
		return;
	}

	var u = contextPath + '/portal/space/VerifyMgr.json?m=execute&f=checkverifycode';

	$.ajax({
        url: u,
        secureuri: false,
        data: {SERIAL_NUMBER:serialNumber,CHECK_CODE:checkCode},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result = respData.result;
        	if(result.success){
        		var phoneNumber = result.dataSet.phoneNumber;
        		//window.location.href = contextPath +"/portal/space/VerifyMgr.htm?m=execute&f=checkverifycodesucess&serial_number=" + serialNumber;
        		window.location.href = encodeURI(contextPath +"/portal/verify/wap_qita_tow.htm?params=" + phoneNumber);
	    	}else{
	    		$("#errorMessage").html(result.dataSet.errorDetail);
	    	}
        },
	    error: function(respData){ 
	    	$("#errorMessage").html('发送验证码异常!');
	    }
    });
}

function initQita3(){
	
	if($('#USER_NAME').val() == '') {
		Alertone("请输入姓名！");
		return false;
	}
	if($('#PSPT_ID').val() == '') {
		Alertone("请输入身份证！");
		return false;
	}
	//document.getElementById("submitbutton1").style.display='none';
	document.getElementById("submitbutton1").disabled=true;
	var cardNum = $('#PSPT_ID').val();
	var userName = $('#USER_NAME').val();
	var serialNumber=$('#SERIAL_NUMBER').val();
	var params = new Object();
	
	params.certId=cardNum;
	params.certName=userName;
	
	var u = contextPath + '/portal/space/CertVerify.json?m=execute&f=certVerify';
	
	$.ajax({
        url: u,
        secureuri: false,
        data: {certId:cardNum,certName:userName,phoneNumber:serialNumber},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result1 = respData.result;
        	if(result1.success){
        		/*var phoneNumber = result1.dataSet.phoneNumber;
        		var addr = result1.dataSet.addr;*/
        		var param = result1.dataSet.param;
        		var result = result1.dataSet.result;
    			var response = result1.dataSet.response;
    			if(typeof(result)=='undefined'||result==''||result==null){
    				Alertone("您提交的身份证信息不合规，请重新输入。");
    				// window.location.href = contextPath + "/portal/space/VerifyMgr.htm?m=execute&f=checkVerify&isCheck=false" + getUrlParamFromBean(getInputArea(document.getElementById("verifyform")));
    				// document.getElementById("submitbutton1").style.display='';
    				document.getElementById("submitbutton1").disabled=false;
    			} else {
    				//window.location.href = encodeURI(contextPath +"/portal/verify/wap_qita_tow_2.htm?serialnumber=" + phoneNumber + "&USER_NAME="+encodeURIComponent(userName) + "&PSPT_ID="+cardNum + "&addr="+encodeURIComponent(addr));
    				window.location.href = encodeURI(contextPath +"/portal/verify/wap_qita_tow_2.htm?params=" + param);
    			}
        	}else{
	    		$("#errorMessage").html(result1.dataSet.errorDetail);
	    	}
        },
	    error: function(respData){ 
	    	$("#errorMessage").html('身份证验证异常!');
	    }
    });
}

function initQita2(){
	$("#verifyform").FormCommit(function(form) {
		if(!imgzhengmian) {
			Alertone("请上传身份证正面照！");
			return false;
		}
		if(!imgbeimian) {
			Alertone("请上传身份证背面照！");
			return false;
		}
		if(!imghandin) {
			Alertone("请上传手持身份证照！");
			return false;
		}
		/*if($('#checkCode').val() == '') {
			Alertone("请输入验证码！");
			return false;
		}*/
		
		if($('#input').val() == '') {
			Alertone("请输入验证码！");
			return false;
		}
		var code = document.getElementById("code").value;
		var code2 =  document.getElementById("input").value.toUpperCase();
		if(code != code2) {
			Alertone("验证码输入错误！");
			return false;
		}
		
		document.getElementById("verifyform").action = contextPath + "/portal/space/VerifyMgr.htm?m=execute&f=addVerify&isCheck=true" + getUrlParamFromBean(getInputArea(document.getElementById("verifyform")));
    }, function(data) { 
    	var result = respData.result;
    	if(!result.success){
    		$("#errorMessage").html(result.dataSet.errorDetail);
    	}
    });
}


function getUrlValue(source,parm) {  
   var reg = new RegExp("(^|&)"+ parm +"=([^&]*)(&|$)");  
   var r = source.substr(source.indexOf("\?")+1).match(reg);  
   if (r!=null) return unescape(r[2]); return null;  
}

function toQueryParams(source){ 
	var search = source.replace(/^\s+/,'').replace(/\s+$/,'').match(/([^?#]*)(#.*)?$/);//提取location.search中'?'后面的部分 
	if(!search){ 
		return {}; 
	} 
	var searchStr = search[1]; 
	var searchHash = searchStr.split('&'); 
	
	var ret = {}; 
	for(var i = 0, len = searchHash.length; i < len; i++){ //这里可以调用each方法 
		var pair = searchHash[i]; 
		if((pair = pair.split('='))[0]){ 
			var key = decodeURIComponent(pair.shift()); 
			var value = pair.length > 1 ? pair.join('=') : pair[0]; 
			//console.log();
			if(value != undefined){ 
				value = decodeURIComponent(value); 
			} 
			if(key in ret){ 
				if(ret[key].constructor != Array){ 
					ret[key] = [ret[key]]; 
				} 
				ret[key].push(value); 
			}else{ 
				ret[key] = value; 
			} 
		} 
	} 
	return ret; 
} 


var imgzhengmian=false;
var imgbeimian=false;
var imghandin=false;

function previewImage(file,showpos)
{ 

	if(!checkext(file)){
		alert("图片的格式为bmp、jpg、jpeg！");
		return;
	}
	
	if(showpos=='zhengmian')
		{
		imgzhengmian=true;
		}
	if(showpos=='beimian')
		{
		imgbeimian=true;
		}
	if(showpos=='handin')
		{
		imghandin=true;
		}

   	$('#'+showpos+'div').remove();
	if (file.files && file.files[0])  
	{  
		$('#'+showpos).append('<div id="'+showpos+'div" class="regpreviewimg" ><img id="'+showpos+'img" src=""></img></div>');
		var img = document.getElementById(showpos+'img'); 
		var reader = new FileReader();  
		reader.onload = function(evt){
			img.src = evt.target.result;
		}  
		reader.readAsDataURL(file.files[0]);  
	}  
	else  
	{  
		var imgPath = getPath(file);  
		var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'+imgPath+'"'; 
		$('#'+showpos).append("<div id=\""+showpos+"div\" class=\"regpreviewimg\" style=' "+sFilter+" '></div>"); 

	} 
};
function checkext(file)
{
	var filename=file.value;
	var extension ;
	if(file == '') {
		return false;
	}
	else
	{
		var regext = /\.jpg$|\.jpeg$|\.bmp$/gi; 

		if(regext.test(filename.toLowerCase()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
function clickfileinput(inputid)
{
	$('#'+inputid).click();
	
};


function getInputArea(area){
	var bean = {};
	var elements = area.elements;
	for(var i=0; i<elements.length; i++){
		var element = elements[i];
		if(element.type != 'file' && element.type != 'button' && element.type != 'checkbox'){
			bean[element.id] = document.getElementById(element.id).value;
		}
	}
	return bean;
}
function getUrlParamFromBean(bean){
	var urlParam = "";
	for (var key in bean){
		urlParam += "&"+key+"="+bean[key];
	}
	return urlParam;
}
