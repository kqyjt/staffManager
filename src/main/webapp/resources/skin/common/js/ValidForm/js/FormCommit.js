$.fn.FormCommit = function(beforsubmit,aftersubmit) {
	var formtype=$(this).attr('FormType');
	var committype=$(this).attr('CommitType');
	var commitbtn=$(this).attr('CommitBtn');
	var resetbtn=$(this).attr('ResetBtn');
	var msgtype=$(this).attr('MsgType');
	var loginerr=$(this).attr('Loginerr');
	
	var nowform;
	
	var postbool=true;
	

	if(typeof(formtype)=='undefined'&&formtype=='')
	{
		formtype='normalform';
	}
	if(typeof(committype)=='undefined'&&committype=='')
	{
		committype='post';
	}
	if(committype!='post')
	{
		postbool=false;
	}
	
	if(typeof(commitbtn)=='undefined'&&commitbtn=='')
	{
		alert('请在form中定义提交按钮（CommitBtn）');
		return;
	}
	if(typeof(loginerr)=='undefined'&&loginerr==''&&formtype=='loginform')
	{
		alert('请在form中定义错误信息提示的位置（Loginerr）');
		return;
	}
	if(typeof(msgtype)=='undefined'&&msgtype=='')
	{
		msgtype='1';
	}

	
	if(formtype=='loginform')
	{
		 nowform=	$(this).Validform({
		        tiptype:function(msg,o,cssctl){
							var objtip=$(loginerr);
							 
							if(!o.obj.is("form")){
								if(o.type==3)
								{
								cssctl(objtip,o.type);
								objtip.text(msg);
								 objtip.parent().show();
								}
								else
								{
									objtip.removeClass();
						            objtip.text('');
						            objtip.parent().hide();
								}
							}
							else
							{
						    objtip.removeClass();
						    objtip.text('');
						    objtip.parent().hide();
							}
						},
		        ignoreHidden:true,
		        tipSweep:false,
		        btnSubmit:commitbtn,
		        showAllError:false,
		        postonce:true,
		        ajaxPost:postbool,
		        dragonfly:false
		     });
		     
		     if(typeof(resetbtn)!='undefined'&&formtype!='')
		     {
		     	nowform.config({
		            btnReset:resetbtn
	              });
		     }
		     
		     if(typeof(beforsubmit) != "undefined"){
			      
			      nowform.config({
		           beforeSubmit:function(curform){
		           	return beforsubmit(curform);
		           }
	              });
		    }
		     if(typeof(aftersubmit) != "undefined"){
			      
			      nowform.config({
		           callback:function(data){
		           	aftersubmit(data);
		           }
	              });
		    }
		    
		
	}
	
	
		if(formtype=='normalform')
	{
		nowform=$(this).Validform({
        tiptype:msgtype,
        ignoreHidden:true,
        tipSweep:false,
        btnSubmit:commitbtn,
        showAllError:true,
        postonce:true,
        ajaxPost:postbool,
        usePlugin:{
    		passwordstrength:{
    			minLen:6,//设置密码长度最小值，默认为0;
    			maxLen:20,//设置密码长度最大值，默认为30;
    			trigger:function(obj,error){
    				//alert("plungin:"+error);
    				//该表单元素的keyup和blur事件会触发该函数的执行;
    				//obj:当前表单元素jquery对象;
    				//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;
    				if(error){
    					obj.parent().next().find("#passwdinfo").show();
    					obj.parent().next().find("#passwordStrength").hide();
    				}else{
    					obj.parent().next().find("#passwdinfo").hide();
    					obj.parent().next().find("#passwordStrength").show();
    				}
    			}
    		}
    	},
        dragonfly:false/*,
         tiptype:function(msg,o,cssctl){
         	             var objtip=o.obj.parent().next().find(".Validform_checktip");
								if(o.type==3)
								{
								cssctl(objtip,o.type);
							    objtip.text(msg);
							    return;
								}
								
								if(o.type==2)
								{
									if(o.obj.attr('type')=='checkbox')
									{
										objtip.removeClass().addClass('Validform_checktip');
						                objtip.text('');
									}
									else
									{
									  cssctl(objtip,o.type);
									  objtip.text(msg);
									}
								}
						}*/
     });
     
      if(typeof(resetbtn)!='undefined'&&formtype!='')
		     {
		     	nowform.config({
		            btnReset:resetbtn
	              });
		     }

		     if(typeof(beforsubmit) != "undefined"){
			      
			      nowform.config({
		           beforeSubmit:function(curform){
		           	return beforsubmit(curform);
		           }
	              });
		    }
		    if(postbool)
		    {
			     if(typeof(aftersubmit) != "undefined"){
				      
				      nowform.config({
			           callback:function(data){
			           	aftersubmit(data);
			           }
		              });
			    }
		    }
		
	}
		
		
		if(formtype=='editform')
		{
			nowform=$(this).Validform({
	        tiptype:msgtype,
	        ignoreHidden:true,
	        tipSweep:false,
	        btnSubmit:commitbtn,
	        showAllError:true,
	        postonce:false,
	        ajaxPost:postbool,
	        dragonfly:false/*,
	         tiptype:function(msg,o,cssctl){
	         	             var objtip=o.obj.parent().next().find(".Validform_checktip");
									if(o.type==3)
									{
									cssctl(objtip,o.type);
								    objtip.text(msg);
								    return;
									}
									
									if(o.type==2)
									{
										if(o.obj.attr('type')=='checkbox')
										{
											objtip.removeClass().addClass('Validform_checktip');
							                objtip.text('');
										}
										else
										{
										  cssctl(objtip,o.type);
										  objtip.text(msg);
										}
									}
							}*/
	     });
	     
	      if(typeof(resetbtn)!='undefined'&&formtype!='')
			     {
			     	nowform.config({
			            btnReset:resetbtn
		              });
			     }

			     if(typeof(beforsubmit) != "undefined"){
				      
				      nowform.config({
			           beforeSubmit:function(curform){
			           	return beforsubmit(curform);
			           }
		              });
			    }
			    if(postbool)
			    {
				     if(typeof(aftersubmit) != "undefined"){
					      
					      nowform.config({
				           callback:function(data){
				           	aftersubmit(data);
				           }
			              });
				    }
			    }
			
		}
		
		
				if(formtype=='normalformmarktip')
	{
		nowform=$(this).Validform({
        tiptype:msgtype,
        ignoreHidden:true,
        tipSweep:false,
        btnSubmit:commitbtn,
        showAllError:true,
        postonce:true,
        marktip:true,
        righttip:'<i class="fa fa fa-check c_green t_l"></i>',
        errortip:'<i class="fa fa-exclamation c_red t_l"></i>',
        ajaxPost:postbool,
        usePlugin:{
    		passwordstrength:{
    			minLen:6,//设置密码长度最小值，默认为0;
    			maxLen:20,//设置密码长度最大值，默认为30;
    			trigger:function(obj,error){
    				//alert("plungin:"+error);
    				//该表单元素的keyup和blur事件会触发该函数的执行;
    				//obj:当前表单元素jquery对象;
    				//error:所设密码是否符合验证要求，验证不能通过error为true，验证通过则为false;
    				if(error){
    					obj.parent().next().find("#passwdinfo").show();
    					obj.parent().next().find("#passwordStrength").hide();
    				}else{
    					obj.parent().next().find("#passwdinfo").hide();
    					obj.parent().next().find("#passwordStrength").show();
    				}
    			}
    		}
    	},
        dragonfly:false/*,
         tiptype:function(msg,o,cssctl){
         	             var objtip=o.obj.parent().next().find(".Validform_checktip");
								if(o.type==3)
								{
								cssctl(objtip,o.type);
							    objtip.text(msg);
							    return;
								}
								
								if(o.type==2)
								{
									if(o.obj.attr('type')=='checkbox')
									{
										objtip.removeClass().addClass('Validform_checktip');
						                objtip.text('');
									}
									else
									{
									  cssctl(objtip,o.type);
									  objtip.text(msg);
									}
								}
						}*/
     });
     
      if(typeof(resetbtn)!='undefined'&&formtype!='')
		     {
		     	nowform.config({
		            btnReset:resetbtn
	              });
		     }

		     if(typeof(beforsubmit) != "undefined"){
			      
			      nowform.config({
		           beforeSubmit:function(curform){
		           	return beforsubmit(curform);
		           }
	              });
		    }
		    if(postbool)
		    {
			     if(typeof(aftersubmit) != "undefined"){
				      
				      nowform.config({
			           callback:function(data){
			           	aftersubmit(data);
			           }
		              });
			    }
		    }
		
	}
	
	
	return nowform;
	
}; 