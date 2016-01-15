<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<link rel="stylesheet" href="${contextPath}/resources/skin/manager/css/login.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/resources/skin/common/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/resources/skin/common/css/font-awesome.min_new.css">
<link rel="stylesheet" href="${contextPath}/resources/skin/common/js/ValidForm/css/style.css"  type="text/css" /> 

<script src="${contextPath}/resources/skin/common/js/ValidForm/js/Validform_v5.3.2.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/FormCommit.js"></script> 
<script src="${contextPath}/resources/skin/portal/js/RSA.js"></script>
<script src="${contextPath}/resources/skin/portal/js/Barrett.js"></script>
<script src="${contextPath}/resources/skin/center/js/BigInt.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	$('#mmxg_newPassword1').blur(function(){
		if($('#mmxg_oldPassword').val() == $('#newPassword1').val()){//新旧密码不能相同
	   		$('#mmxg_newpwd1to').addClass('Validform_wrong');
	   		$('#mmxg_newPassword1').addClass('Validform_error');
	   		$('#mmxg_newpwd1to').html("新旧密码不能相同");
	   	}
	});
	
	$("#mmxg_chgpwdform").FormCommit(function(form) {
			if($('#mmxg_oldPassword').val() == $('#mmxg_newPassword1').val()){//新旧密码不能相同
		   		$('#mmxg_newpwd1to').addClass('Validform_wrong');
		   		$('#mmxg_newPassword1').addClass('Validform_error');
		   		$('#mmxg_newpwd1to').html("新旧密码不能相同");
		   		return false;
		   	}
			
		    var rsa_n = "b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
			setMaxDigits(130); 
		    var key  = new RSAKeyPair("10001", "", rsa_n);
		    var rsapassword = encryptedString(key, encodeURIComponent($('#mmxg_oldPassword').val()));
		    $('#mmxg_oldPassword').val(rsapassword);
		    var rsanewPassword1 = encryptedString(key, encodeURIComponent($('#mmxg_newPassword1').val())); 
		    $('#mmxg_newPassword1').val(rsanewPassword1);
		    var rsanewPassword2 = encryptedString(key, encodeURIComponent($('#mmxg_newPassword2').val())); 
		    $('#mmxg_newPassword2').val(rsanewPassword2);
	    }, function(data) {
	    	if((data.result.success&&data.result.errorCode=='00000')){
	    		$('#mmxg_errmsg').show();
	    		$('#mmxg_ModPswInfor').html("密码修改成功");
	    	}else{
	    		$('#mmxg_errmsg').show();
	    		$('#mmxg_ModPswInfor').html(data.result.dataSet.errorDetail);
	    	}
	    	$('#mmxg_oldPassword').val('');
    		$('#mmxg_newPassword1').val('');
    		$('#mmxg_newPassword2').val('');
    		$('#mmxg_pwdto').html('请输入原始的密码');
	   		$('#mmxg_newpwd1to').html('请输入新密码');
	   		$('#mmxg_newpwd2to').html('请输入确认密码');
	    });
	
	//修改密码取消按钮 被点击
	$('#mmxg_resetButton').click(function(){
		//内容清空
		$('#mmxg_userName').val('');
		$('#mmxg_oldPassword').val('');
		$('#mmxg_newPassword1').val('');
		$('#mmxg_newPassword2').val('');
		$('#mmxg_namemsg').html('请输入用户名');
		$('#mmxg_pwdto').html('请输入原始的密码');
		$('#mmxg_newpwd1to').html('请输入新密码');
		$('#mmxg_newpwd2to').html('请输入确认密码');
	});
});
</script>

<!-- 修改密码的form -->
<div style="position:fixed;background-color: white;text-align: center;"  id="mmxg_modPassWordDiv" class="long_widwe">
	<form class="chgpwdform" id="mmxg_chgpwdform" action="${managerPath }/manager/sysman/Login.json?m=execute&f=changePassWord" FormType="editform" CommitType="post" CommitBtn="#mmxg_modibtn"  MsgType="5">
		<div class="c_red"  id="mmxg_errmsg"  style=" display:none; width:230px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;"><i class="fa fa-exclamation-triangle"></i><span id="mmxg_ModPswInfor"  style="margin-left:5px"></span></div>
         <table class=" ruwangxinxi_tab" >
           <tr>
          <td width="84" align="right">
          用户名:
          </td>
          <td >
          <input id="mmxg_userName" class="zc_inputtext2" type="text" width="160px"
				value="${result.dataSet.customer.staffId}"
				name="userName" datatype="*" nullmsg="请输入用户名"
				errormsg="密用户名！" sucmsg="" showokmsg="noshow"  disabled="disabled">
          </td><td id="mmxg_namemsg" align="left" class="login_shuoming"></td>
          </tr>
         <tr>
          <td width="84" align="right" >
          旧密码:
          </td>
          <td>
          <input id="mmxg_oldPassword" class="zc_inputtext2" type="password" width="160px"
				name="oldPassword" datatype="*" nullmsg="请输入原始密码"
				errormsg="请输入原始密码" sucmsg="" autocomplete="off" showokmsg="noshow" messageto="#mmxg_pwdto">
          </td><td align="left" id="mmxg_pwdto" class="login_shuoming">请输入原始的密码</td>
          </tr>

         <tr>
          <td align="right">
          新密码:
          </td>
          <td>
          <input id="mmxg_newPassword1" class="zc_inputtext2" type="password" width="160px"
				name="newPassword1" datatype="pwdreg" nullmsg="请输入新的密码" 
				errormsg="字母、数字、特殊符号的组合(6-12位)" sucmsg="" autocomplete="off" showokmsg="noshow" messageto="#mmxg_newpwd1to" >
          </td><td id="mmxg_newpwd1to" align="left" class="login_shuoming">请输入新密码</td>
          </tr>
          
          <tr>
          <td align="right">
 确认密码:
          </td>
          <td>
          <input id="mmxg_newPassword2" class="zc_inputtext2" type="password" width="160px"
				name="newPassword2" datatype="*" nullmsg="请再次输入新密码" autocomplete="off"
				errormsg="两次输入的新密码不一致！"  recheck="newPassword1" showokmsg="noshow"  sucmsg=""  messageto="#mmxg_newpwd2to">
          </td><td id="mmxg_newpwd2to"  align="left" class="login_shuoming">请输入确认密码</td>
          </tr>    
          <tr>
          <td></td>
          <td>
          <p class="c_red F12" id="mmxg_resultinfo"></p>
          </td>
          </tr> 
          <tr >
          <td></td><td><a class="span_bth" id="mmxg_modibtn" href="javaScript:void(0)">修改</a><a class="span_bth"  href="javaScript:void(0)" id="mmxg_resetButton">取消</a></td><td></td>
          </tr>                                      
       </table>
  </form> 
  </div>
