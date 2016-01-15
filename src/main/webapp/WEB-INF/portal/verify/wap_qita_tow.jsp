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

<script type="text/javascript">
$(document).ready(function() {
	var bean = toQueryParams(window.location.href);
	$("#SERIAL_NUMBER").html(bean.params);
	$("#SERIAL_NUMBER_HIDDEN").attr("value",bean.params);
	if(!bean.params){
		$("#SERIAL_NUMBER").html(bean.SERIAL_NUMBER_HIDDEN);
		$("#SERIAL_NUMBER_HIDDEN").attr("value",bean.SERIAL_NUMBER_HIDDEN);
	}
	//initQita2();
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

<form class="loginregform" id="verifyform" enctype="multipart/form-data"  action="${portalPath}/portal/space/VerifyMgr.htm?m=execute&f=addVerify" method="post" FormType="normalform" CommitType="form" CommitBtn="#submitbutton1" MsgType="5">

<div class="clear"></div>
<section>
<div style="background:#fafafa; padding:10px;">
<div>
<div style="padding:0 0 10px 0">
 <div class="qtwogerner">1.身份验证</div>
 <div class="qtwogerner qtwogerner2">2.填写资料</div>
 <div class="qtwogerner">3.上传证件</div>
 <div class="qtwogerner">4.提交完成</div>
 <div class="clear"></div>
</div>
</div>

 <div  style="background:#fff; border:1px solid #CCC; box-shadow:0 0 2px rgba(0,0,0,0.2)">

 <div style="font-family:'微软雅黑'; font-size:18px; text-align:center; margin:20px 0; ">填写信息</div>
 <div style="border-top:1px solid #ccc; margin:0 auto">
  <c:if test="${result.errorCode != '00000'}">
  	<p class="F14 c_red t_c">${result.dataSet.errorDetail}</p>
 </c:if>
<%--<p class="F14 c_red t_c" style=" padding:10px 0">审核不通过原因，后台备注带过来</p>--%>
 
 <table class="obertabler">
  <tr>
   <td><input class="obobere" placeholder="姓名" type="text" id="USER_NAME"></td>
  </tr>
  <tr> 
   <td><input class="obobere" placeholder="身份证" type="text" id="PSPT_ID"></td>
  </tr>
 </table> 
 
 <input type="text" id="SERIAL_NUMBER_HIDDEN" style="display:none"/>
 <input type="text" id="SERIAL_NUMBER" value="${param.params}" style="display:none"/>
  <input type="text" id="IS_WAP" value="1" style="display:none"/>
 
  

 </div>
  
 <div style="width:160px; margin:50px auto">
 	<!-- <a class="button_red_gwc" href="javascript:void(0);" id = "submitbutton1" onclick="initQita3();">下一步</a> -->
 	<input class="button_red_gwc" type = "button" value = "下一步"  id = "submitbutton1" onclick = "initQita3()"/>
 </div>
 </div>
</div> 

</section>
</form>

<script type="text/javascript">
	var c = $("#SERIAL_NUMBER").val();
	
	var u = contextPath + '/portal/space/VerifyMgr.json?m=execute&f=decryptPhone';

	$.ajax({
        url: u,
        secureuri: false,
        data: {phoneNumber:c},
        dataType: 'json',
        type: 'POST',
        success: function (respData) {
        	var result = respData.result;
        	var phone = result.dataSet.phone;
        	if(result.success){
        		document.getElementById('SERIAL_NUMBER').value = phone;
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