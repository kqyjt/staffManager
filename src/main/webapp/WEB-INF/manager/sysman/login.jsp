<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>山东联通网盟平台后台管理</title>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/initjs.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet" href="${contextPath}/resources/skin/manager/css/login.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/skin/common/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/resources/skin/common/css/font-awesome.min_new.css">
<link rel="stylesheet" href="${contextPath}/resources/skin/common/js/ValidForm/css/style.css"  type="text/css" /> 

<script src="${contextPath}/resources/skin/common/js/jquery.min.js"></script>
<script src="${contextPath}/resources/skin/manager/js/login.js" type="text/javascript"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/Validform_v5.3.2.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/FormCommit.js"></script> 
<script src="${contextPath}/resources/skin/manager/js/AlertConfirm.js"></script>
<script src="${contextPath}/resources/skin/portal/js/RSA.js"></script>
<script src="${contextPath}/resources/skin/portal/js/Barrett.js"></script>
<script src="${contextPath}/resources/skin/portal/js/BigInt.js"></script>

</head>

<body class="b">
<script>
if(aminframework)
{
$('body').html('<div style="padding-top:20px;margin:auto;size:18px;color:#666699;text-align:center;">您的账号长时间无操作，为了安全，请重新登录！</div>');
window.open (managerPath+'/manager/sysman/Login.htm', '_top');
}

</script>

	<div class="lg">
	<form class="loginform" id="loginform" action="${managerPath}/manager/sysman/Login.htm?m=execute&f=login" method="post" FormType="loginform" CommitType="form" CommitBtn="#login_button"  Loginerr="#loginerr">
		<div class="lg_top" style="height:140px;"></div>
		<div class="lg_main" style="height:241px;">
			<div class="lg_m_1">
				<div style="height:24px;" id="remodifpwd">
					<c:if test="${result.errorCode != 00000}">
				   		<div class="c_red"  id="errmsg"  style="width:230px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;"><i class="fa fa-exclamation-triangle"></i><span id="loginerr"  style="margin-left:5px">${result.dataSet.errorDetail}</span></div>
				   </c:if>
				   <c:if test="${result.errorCode == 00000}">
				   		<div class="c_red" id="errmsg" style="width:230px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;display:none"><i class="fa fa-exclamation-triangle"></i><span id="loginerr" style="margin-left:5px"></span></div>
				   </c:if>
			    </div>
			   
				<input type="text" id="inputstaffId" style="margin-top:10px" class="ur" placeholder="用户名" name="staffId" datatype="*" nullmsg="请输入账号！" errormsg="账号输入不正确！" showokmsg="noshow" /> 
				<input  type="password" class="pw" placeholder="密码" id="password" name="password" datatype="*" autocomplete="off" nullmsg="请输入密码！" errormsg="密码输入不正确！" showokmsg="noshow"/>
				<div>
			    <input style="width:144px;background-image: none; border:1px solid #dfdfdf;border-radius: 4px; " class="ur pull-left" type="text" id="imgcaptcha" placeholder="验证码" name="captcha" datatype="n4-4" nullmsg="请输入验证码！" errormsg="验证码是四位数字！" showokmsg="noshow">
			    <img class="pull-left" style="margin-top:8px;margin-left:-16px" id="captcha-image" src="${contextPath}/captcha.jpg" alt="看不清?换一张"/>
			   	<div style="clear:both"></div>
			   	</div>
				<!-- <input class="ur1"  type="button" id="showModPassWordDialog_button" href="javascript:void(0);" value="修改密码" /> -->
			</div>
		</div>
		<div class="lg_foot">
			<input  class="bn" type="button" id="login_button" href="javascript:void(0);" value="登陆"/>
		</div>
		</form>
	</div>
	

</body>
</html>
