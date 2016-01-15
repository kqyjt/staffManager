


$(document).ready(function() {
	// 登陆页面    点击 展示 修改密码的dialog按钮
	var newiframeModPassWord=false;
	
	$('#captcha-image').click(
			function() {
				$(this).attr(
						'src',
						contextPath+'/captcha.jpg?'
								+ Math.floor(Math.random() * 100));
			});
	
	$("#loginform").FormCommit(function(form) {
			var rsa_n = "b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
			setMaxDigits(130); 
		    var key      = new RSAKeyPair("10001", "", rsa_n);
		    var rsapassword = encryptedString(key, encodeURIComponent($('#password').val())); 
		   // $('#password').attr('value',rsapassword);
		    
		    var action = form.attr("action");
	        var form2 = $("<form style='display:none;'></form>");
	        form2.attr('action',action)
	        form2.attr('method','post');
	        var input1 = $("<input type='text' name='staffId' value='"+$('#inputstaffId').val()+"'/>");
	        var input2 = $("<input type='text' name='password' value='"+rsapassword+"' />");
	        var input3 = $("<input type='text' name='captcha' value='"+$('#imgcaptcha').val()+"' />");
	        form2.append(input1);
	        form2.append(input2);
	        form2.append(input3);
	        form2.appendTo("body");
	        form2.submit();
		    return false;
    }, function(data) { 
    	/*if((data.result.errorCode=='10085')){
    		$('#showModPassWordDialog_button').click();
    	}*/
    });
	
	
	$('#showModPassWordDialog_button').click(function(){
		//弹出修改密码dialog
		var maskheight = window.screen.height;
		$('#emarkt_amask_divModPassWord').css('height', maskheight).show();
		
	   if (!newiframeModPassWord) {
	        $('#modPassWordDiv').show();
	        newiframelogin=true;
	    } else {
	    	 $('#emarkt_amask_divModPassWord').show();
	         $('#modPassWordDiv').show();
	    }
	});
	
	/*$('#newPassword1').blur(function(){
		if($('#oldPassword').val() == $('#newPassword1').val()){//新旧密码不能相同
	   		$('#newpwd1to').addClass('Validform_wrong');
	   		$('#newPassword1').addClass('Validform_error');
	   		$('#newpwd1to').html("新旧密码不能相同");
	   	}
	});
	
	$("#chgpwdform").FormCommit(function(form) {
			if($('#oldPassword').val() == $('#newPassword1').val()){//新旧密码不能相同
		   		$('#newpwd1to').addClass('Validform_wrong');
		   		$('#newPassword1').addClass('Validform_error');
		   		$('#newpwd1to').html("新旧密码不能相同");
		   		return false;
		   	}
			
		    var rsa_n = "b3ef1a648c8bae61b79a6e6234ab49b0dcde72b04b91294cba0d606dae0ad05829d82591cf9f8dd2fd0bd834d25b30cb3243bb9683ce45045a8195b2eac2c258e4c46eb8176de06cc63e770e2cb55ad1b1125a4cc66de5dcab9fba615ceee58f8a4bd492da1173b2644055a7b8144e56d8aad17a83bf5d22f4e0d808cea41133";
			setMaxDigits(130); 
		    var key  = new RSAKeyPair("10001", "", rsa_n);
		    var rsapassword = encryptedString(key, encodeURIComponent($('#oldPassword').val())); 
		    $('#oldPassword').attr('value',rsapassword);
		    
		    var rsanewPassword1 = encryptedString(key, encodeURIComponent($('#newPassword1').val())); 
		    $('#newPassword1').attr('value',rsanewPassword1);
		    var rsanewPassword2 = encryptedString(key, encodeURIComponent($('#newPassword2').val())); 
		    $('#newPassword2').attr('value',rsanewPassword2);
		    $('#modPassWordDiv').css("z-index","1000");
	    }, function(data) {
	    	 $('#modPassWordDiv').css("z-index","99999");
	    	if((data.result.success&&data.result.errorCode=='00000')){
	    		
	    		//$('#ModPswInfor').html("密码修改成功");
	    		$("#modPassWordDiv").hide();
	    		$('#emarkt_amask_divModPassWord').hide();
	    		$("#remodifpwd").html('<div class="c_red"  id="errmsg"  style="width:230px; margin:0 auto; background:#ffc9c2; border:1px solid #F30;  line-height:20px; padding:4px;"><i class="fa fa-exclamation-triangle"></i><span id="loginerr"  style="margin-left:5px">密码修改成功</span></div>');
	    	}else{
	    		$('#errmsg').show();
	    		$('#ModPswInfor').html(data.result.dataSet.errorDetail);
	    	}
	    	$('#oldPassword').val('');
    		$('#newPassword1').val('');
    		$('#newPassword2').val('');
    		$('#pwdto').html('请输入原始的密码');
	   		$('#newpwd1to').html('请输入新密码');
	   		$('#newpwd2to').html('请输入确认密码');
	    });
	
	//修改密码取消按钮 被点击
	$('#resetButton').click(function(){
		//内容清空
		$('#userName').val('');
		$('#oldPassword').val('');
		$('#newPassword1').val('');
		$('#newPassword2').val('');
		//重返登陆界面
		 $('#emarkt_amask_divModPassWord').hide();
	     $('#modPassWordDiv').hide();
	     $('#errmsg').hide();
		 $('#ModPswInfor').html("");
		 $('#namemsg').html('请输入用户名');
		 $('#pwdto').html('请输入原始的密码');
		 $('#newpwd1to').html('请输入新密码');
		 $('#newpwd2to').html('请输入确认密码');
	});*/
	
	$(window).keyup(function(event){
        if(event.keyCode == 13)
        {
        	$("#login_button").click();
        }
        });
	
});

