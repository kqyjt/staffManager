<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>山东联通自助实名登记系统</title>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/initjs.jsp"%>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="联通网盟销售平台" />
<meta name="keywords" content="联通网盟销售平台_能人之家" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/public.css" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/nrkj.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/index.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/zty.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min_new.css">

<script type="text/javascript" src="${contextPath}/resources/skin/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/qtip/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/Addressall.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/AreaSelect.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/SetAreaSelect.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/Validform_v5.3.2.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/FormCommit.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/ZeroClipboard/ZeroClipboard.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/TimeDown.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/AlertConfirm.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/portal/js/qitaverify_wap.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/portal/js/checkCode.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	/* var bean = toQueryParams(window.location.href);
	$("#SERIAL_NUMBER").html(bean.serialnumber);
	$("#SERIAL_NUMBER_HIDDEN").attr("value",bean.serialnumber);
	if(!bean.serialnumber){
		$("#SERIAL_NUMBER").html(bean.SERIAL_NUMBER_HIDDEN);
		$("#SERIAL_NUMBER_HIDDEN").attr("value",bean.SERIAL_NUMBER_HIDDEN);
	} */
	initQita2();
});
</script>
</head>
<style>
.qt_oger{ height:90px; overflow:hidden;
filter: progid: DXImageTransform.Microsoft.gradient(startColorstr = '#d9d9d9', endColorstr = '#f0f0f0');
/*INNER ELEMENTS MUST NOT BREAK THIS ELEMENTS BOUNDARIES*/
/*Element must have a height (not auto)*/
/*All filters must be placed together*/
-ms-filter: "progid: DXImageTransform.Microsoft.gradient(startColorstr = '#d9d9d9', endColorstr = '#f0f0f0')";
/*Element must have a height (not auto)*/
/*All filters must be placed together*/
background-image: -moz-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -ms-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -o-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -webkit-gradient(linear, center top, center bottom, from(#d9d9d9), to(#f0f0f0));
background-image: -webkit-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: linear-gradient(top, #d9d9d9, #f0f0f0);
/*--IE9 DOES NOT SUPPORT GRADIENT BACKGROUNDS--*/
border-bottom:1px solid #CCC;
}
.qt_ergrer{ height:70px; margin:10px;}
.qi_nglere{ padding-right:10px; border-right:1px solid #c1c1c1;}
img{vertical-align:middle;}
.qtyongyici{ height:70px; padding-left:10px; padding-top:12px; border-left:1px solid rgba(255,255,255,0.6);font-family:'微软雅黑';}
.qtwogerner{ width:25%; height:24px; background:#bfbfbf; border:1px solid #fff; float:left;font-family:'微软雅黑'; text-align:center; color:#fff; box-sizing:border-box}
.qtwogerner2{ background:#f95118}
.obertabler{ font-family:'微软雅黑';font-size:16px; color:#666; line-height:38px; width:96%; margin:0 auto;}
.obobere{ line-height:30px; border:1px solid #a0a0a0; width:94%; line-height:30px; color:#333; font-size:14px;border-radius:2px; padding:0 3%;}
.yanzhengma_a{ font-size:12px; color:#fff; display:block; width:90px; height:28px; line-height:28px; margin-top:2px; text-align:center;  background:#73cc34}
</style>
<body>
<section>
  <div class="index_head">
    <div class="indext_daohang qt_oger">
     <div class="centour ">
      <div class="qt_ergrer">
       
       <div class="FL qi_nglere"><img src="${contextPath}/resources/skin/portal/images/verify/liantonglogo.png" width="118" height="70"></div>
       
       <div class="FL qtyongyici">
	       <!-- <p style="font-size:26px;color: #505050;font-weight: bold; text-shadow:1px 1px 1px #fff; line-height:40px;">山东联通自助实名登记系统</p> -->
	       <p style="font-size:18px;color: #505050;font-weight: bold; text-shadow:1px 1px 1px #fff; line-height:23px;">山东联通自助</p>
	       <p style="font-size:18px;color: #505050;font-weight: bold; text-shadow:1px 1px 1px #fff; line-height:23px;">实名登记系统</p>
       </div>
       
       <div class="clear"></div>
       </div>
      <!--logo--> 
     </div>
  </div>
  </div>
  <!--head-->
</section>

<form class="loginregform" id="verifyform" enctype="multipart/form-data"  action="${portalPath}/portal/space/VerifyMgr.htm?m=execute&f=addVerify" method="post" FormType="normalform" CommitType="form" CommitBtn="#submitbutton" MsgType="5">

<div class="clear"></div>
<section>
<div style="background:#fafafa; padding:10px;">
<div>
<div style="padding:0 0 10px 0">
 <div class="qtwogerner">1.身份验证</div>
 <div class="qtwogerner">2.填写资料</div>
 <div class="qtwogerner qtwogerner2">3.上传证件</div>
 <div class="qtwogerner">4.提交完成</div>
 <div class="clear"></div>
</div>
</div>

 <div  style="background:#fff; border:1px solid #CCC; box-shadow:0 0 2px rgba(0,0,0,0.2)">

 <div style="font-family:'微软雅黑'; font-size:18px; text-align:center; margin:20px 0; "></div>
 <div style="border-top:1px solid #ccc; margin:0 auto">
  <c:if test="${result.errorCode != '00000'}">
  	<p class="F14 c_red t_c">${result.dataSet.errorDetail}</p>
 </c:if>
<%--<p class="F14 c_red t_c" style=" padding:10px 0">审核不通过原因，后台备注带过来</p>--%>
 
 <input type="text" id="SERIAL_NUMBER_HIDDEN" style="display:none"/>
 <input type="text" id="USER_NAME" value="${param.USER_NAME}" style="display:none"/>
 <input type="text" id="PSPT_ID" value="${param.PSPT_ID}" style="display:none"/>
 <input type="text" id="addr" value="${param.addr}" style="display:none"/>
 <input type="text" id="phoneNumber" value="${param.params}" style="display:none"/>
  <input type="text" id="IS_WAP" value="1" style="display:none"/>
 
  <div style="margin:10px auto 10px auto; width:94%;">
<div class="dd_shenzshangchan" style="width:100%;height:160px; ">
	<a style="width:100%; height:160px; box-sizing:border-box; font-size:16px;" href="javascript:void(0);" onclick="javascript:clickfileinput('zhengmianinput');"  id="zhengmian">
		<i style="margin-top:45px" class="fa fa-plus"></i>
		<br/><span class="c_red F16">*</span>上传身份证正面
	</a>
	<input type="file" id="zhengmianinput" onchange="previewImage(this,'zhengmian');" class="upload_file"  name="uploadFile"/>
</div> 

<div class="dd_shenzshangchan" style="width:100%;height:160px; ">
	<a style="width:100%; height:160px; box-sizing:border-box; font-size:16px;" href="javascript:void(0);" onclick="javascript:clickfileinput('beimianinput');" id="beimian">
		<i style="margin-top:45px" class="fa fa-plus"></i>
		<br/><span class="c_red F16">*</span>上传身份证背面
	</a>
	<input type="file" id="beimianinput" onchange="previewImage(this,'beimian');" class="upload_file" name="uploadFile" />
</div>

<div class="dd_shenzshangchan" style="width:100%;height:160px; ">
	<a style="width:100%; height:160px; box-sizing:border-box; font-size:16px;" href="javascript:void(0);" onclick="javascript:clickfileinput('handininput');" id="handin">
		<i style="margin-top:45px" class="fa fa-plus"></i>
		<br/><span class="c_red F16">*</span>手持身份证
	</a>
	<input type="file" id="handininput" onchange="previewImage(this,'handin');" class="upload_file" name="uploadFile" />
</div>

<div class="clear"></div>

<p class="F12 c_qhuise margintop10 t_c">点击上传身份证</p>

<p class="F12 c_shuise" style="padding:20px 0 2px 0; border-bottom:1px dotted #ccc">图片要求说明</p>
<p class="F12 c_qhuise"><i class="fa fa-lightbulb-o c_orger"></i>&nbsp;拍摄照片时尽量让身份证充满整个屏幕，且尽量让身份证处于光线充足，背景纯色的位置；</p>
<p class="F12 c_qhuise"><i class="fa fa-lightbulb-o c_orger"></i>&nbsp;拍摄手持照片时，请务必不带眼镜，选择纯色的背景并保持光线充足，尽量避免在晚上日光等下拍照;</p>
<p class="F12 c_qhuise"><i class="fa fa-lightbulb-o c_orger"></i>&nbsp;上传身份证必须是本人且有效的身份证，否则无法登记；</p>
<p class="F12 c_qhuise"><i class="fa fa-lightbulb-o c_orger"></i>&nbsp;为着照片通过率，勿使用任何美图功能对照片进行美化，拍摄上半身即可</p>
</div>

 </div>
  <p class="c_shuise F14 t_c">将要实名的手机号码：<b class="c_orger" id="SERIAL_NUMBER"></b></p>
  		<title>验证码</title>  
        <style type="text/css">  
            #code  
            {  
                font-family:Arial;  
                font-style:italic;  
                font-weight:bold;  
                border:0;  
                letter-spacing:2px;  
                color:blue;
                width:50px;
                height:18px;  
            }  
        </style>  
        <div style="width:230px; margin:20px auto">  
        	<span>请输入验证码：</span>
            <input style="width:6em" type = "text" id = "input"/>  
            <input style="width:5em" type = "button" id="code" onclick="createCode()"/>  
            <!-- <input type = "button" value = "验证" onclick = "validate()"/>  --> 
        </div>
<%--   <div style="width:160px; margin:250px auto">
    <span>请输入验证码：<input style="width:160px;height:20px; margin:auto" class="check" type="text" id="checkCode" name="checkCode"></span>
    <img class="pull-left" style="margin-top:8px;margin-left:45px" id="checkCode_image" src="${contextPath}/captcha.jpg" alt="看不清?换一张"/>
   	<div style="clear:both"></div>
  </div> --%>
  
 <div style="width:160px; margin:20px auto">
 	<!-- <a class="button_red_gwc" href="javascript:void(0);" id = "submitbutton">提交</a> -->
 	<input class="button_red_gwc" type = "button" value = "提交" id = "submitbutton"/>
 </div>
 </div>
</div> 

</section>
</form>

<script type="text/javascript">
	/* var a = $("#USER_NAME").val();
	var b = $("#addr").val();
	var c = $("#phoneNumber").val();
	document.getElementById("USER_NAME").value=decodeURIComponent(a);
	document.getElementById("addr").value=decodeURIComponent(b); */
	var parameter = $("#phoneNumber").val();

	var u = contextPath + '/portal/space/VerifyMgr.json?m=execute&f=decryptParam';

	$.ajax({
        url: u,
        secureuri: false,
        data: {param:parameter},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result = respData.result;
        	var phone = result.dataSet.phone;
        	var certName = result.dataSet.certName;
        	var certId = result.dataSet.certId;
        	var addr = result.dataSet.addr;
        	if(result.success){
        		document.getElementById('SERIAL_NUMBER').innerHTML = phone;
        		$("#SERIAL_NUMBER_HIDDEN").attr("value",phone);
        		$("#USER_NAME").attr("value",certName);
        		$("#PSPT_ID").attr("value",certId);
        		$("#addr").attr("value",addr);
	    	}else{
	    		$("#errorMessage").html(result.dataSet.errorDetail);
	    	}
        },
	    error: function(respData){ 
	    	$("#errorMessage").html('系统异常!');
	    }
    });
</script>

<section>
  <div class="long_foot">
    <div class="long_foot_bg" style="background:#555; height:auto">
  中国联合网络通信有限公司机构代码证<br>
中国联合网络通信有限公司山东省分公司 版权所有 鲁ICP备08105755号<br>
中华人民共和国增值电信业务经营许可证 经营许可证编号：A2.B1.B2-20090003
  </div>
 </div> 
</section>

</body>
</html>