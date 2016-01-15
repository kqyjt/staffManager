<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>山东联通电子商务全业务辅助运营平台</title>
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
<style>
.login_box {
	width: 200px;
	padding: 0 40px;
	height: 300px;
	margin: 120px 0 0 580px;
	background: #009bca;
	border-radius: 6px;
}

.login_box input {
	background: #FFF;
	margin: 0;
	border: none;
	line-height: 30px;
	color: #666;
	font-size: 14px;
	height: 30px;
	width: 180px;
	padding: 0 10px;
}

.login_box p {
	font-size: 16px;
	font-family: "微软雅黑";
	color: #fff;
	padding: 0;
	margin: 0;
	line-height: 30px;
	margin-top: 10px;
	border-radius: 2px;
}

.login_box a {
	display: block;
	background: #097bb0;
	font-family: "微软雅黑";;
	margin-top: 30px;
	text-align: center;
	line-height: 30px;
	height: 30px;
	color: #fff;
	font-size: 16px;
	width: 200px;
	border-radius: 2px;
}
</style>

</head>

<body style="background: #2cb6d6; padding: 0; margin: 0;" class="b">
	<script>
		if (aminframework) {
			$('body').html(
							'<div style="padding-top:20px;margin:auto;size:18px;color:#666699;text-align:center;">您的账号长时间无操作，为了安全，请重新登录！</div>');
			window.open(managerPath + '/manager/sysman/Login.htm', '_top');
		}
	</script>
	<div
		style="background: url(${contextPath}/resources/skin/manager/images/bg123_02.png) top center no-repeat; width: 100%; height: 698px; overflow: hidden; margin: 0; padding: 0; margin-top: 20px;">
		<div style="width: 1000px; margin: 0 auto">
			<form class="loginform" id="loginform" action="${managerPath}/manager/sysman/Login.htm?m=execute&f=login" method="post" FormType="loginform" CommitType="form" CommitBtn="#login_button"  Loginerr="#loginerr">
			<div class="login_box">
				<div style="height:24px;" id="remodifpwd">
					<c:if test="${result.errorCode != 00000}">
				   		<div class="c_red"  id="errmsg"  style="width:190px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;"><i class="fa fa-exclamation-triangle"></i><span id="loginerr"  style="margin-left:5px">${result.dataSet.errorDetail}</span></div>
				   </c:if>
				   <c:if test="${result.errorCode == 00000}">
				   		<div class="c_red" id="errmsg" style="width:190px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;display:none"><i class="fa fa-exclamation-triangle"></i><span id="loginerr" style="margin-left:5px"></span></div>
				   </c:if>
			    </div>
			    <!-- 
				<p
					style="font-size: 12px; color: #fff; text-align: center; height: 30px;"></p> -->
				<p>用户名</p>
				<input type="text" id="inputstaffId" placeholder="用户名" name="staffId" datatype="*" nullmsg="请输入账号！" errormsg="账号输入不正确！" showokmsg="noshow" />
				<p>密码</p>
				<input type="password" placeholder="密码" id="password" name="password" datatype="*" autocomplete="off" nullmsg="请输入密码！" errormsg="密码输入不正确！" showokmsg="noshow" />
				<p>填写验证码</p>
				<input  type="text" id="imgcaptcha" placeholder="验证码" name="captcha" datatype="n4-4" nullmsg="请输入验证码！" errormsg="验证码是四位数字！" showokmsg="noshow" style="width: 90px;"> 
				<img style="vertical-align: middle;" id="captcha-image" src="${contextPath}/resources/skin/manager/images/captcha.jpg" alt="看不清?换一张"  width="73" height="32" /> 
				<a href="javascript:void(0);" id="login_button">登录</a>
			</div>
			</form>
		</div>
	</div>

</body>
</html>