/*
 *
 * 
 * 弹出提示信息窗口
 * 第一个参数提示信息，第二个是回调的function
 * 
 * Alertone('这里是信息',function(btnnum){
	    	alert(btnnum);
	    });
	    

*弹出确认和取消窗口
*第一个参数提示消息，第二个是第一个按钮汉字，第三个是第二个按钮汉字，第四个是回调的function
*第一个按钮是确定，第二个按钮是取消
*右上角关闭，等同于按钮2点击
*第一个按钮点击后返回1，第二按钮点击后返回2
*Alertconfirm('这里是信息,你确定？','好吧','滚蛋',function(btnnum){
	    	alert(btnnum);
	    });
	   });
*
*/

function Alertone(contenthtml,callback)
{
	$('#emarkt_amask_div').remove();
    $('#emarkt_adialog_div').remove();
	var maskdiv='<div class="ng_zhezhao" id="emarkt_amask_div" style="display:none;"></div>'
					+'<div class="public_tc" id="emarkt_adialog_div">'
					+' <a class="public_tc_close FR" href="javascript:CancelAlertone();"><i class="icon-remove-sign"></i></a>'
					+'  <div class="clear"></div>'
					+' <div class="F16 c_shuise haomaxzp_tc_text">'
					+' <img class="FL" src="'+contextPath+'/resources/skin/portal/images/biaoqing/smile.PNG" width="60" height="60"> <p style=" margin:5px 0 0 5px;width:180px;" class="FL">'+contenthtml+'</p><div class="clear"></div> </div>'
					+' <div style="padding:10px 20px">'
					+'  <a class="tc_tcbutton bg_red" style="margin-left:66px;" href="javascript:void(0);" id="emarkt_amask_btn1">确认</a>'
					+'  <div class="clear"></div>'
					+' </div> '
					+'</div>';
	$('body').append(maskdiv);
	$('#emarkt_amask_div').fadeIn();
	var maskheight = document.body.scrollHeight;
	$('#emarkt_amask_div').css('height', maskheight);
	$('#emarkt_amask_btn1').click(function(){
				CloseAlertone();
				if(typeof(callback)!='undefined')
				{
					callback('1');
				}
			});

}

function CloseAlertone()
{
	$('#emarkt_amask_div').fadeOut();
    $('#emarkt_adialog_div').fadeOut();
}

function CancelAlertone()
{
	$('#emarkt_amask_btn1').click();
}


function Alertconfirm(contenthtml,btn1text,btn2text,callback)
{
	$('#emarkt_cmask_div').remove();
    $('#emarkt_cdialog_div').remove();
	var maskdiv='<div class="ng_zhezhao" id="emarkt_cmask_div" style="display:none;"></div>'
					+'<div class="public_tc" id="emarkt_cdialog_div">'
					+' <a class="public_tc_close FR" href="javascript:CancelConfirm();"><i class="icon-remove-sign"></i></a>'
					+'  <div class="clear"></div>'
					+' <div class="F16 c_shuise haomaxzp_tc_text">'
					+' <img class="FL" src="'+contextPath+'/resources/skin/portal/images/biaoqing/smile.PNG" width="60" height="60"> <p style=" margin:5px 0 0 5px;width:180px;" class="FL">'+contenthtml+'</p><div class="clear"></div> </div>'
					+' <div style="padding:10px 20px">'
					+'  <a class="tc_tcbutton bg_red" style="margin-right:20px;" href="javascript:void(0);" id="emarkt_cmask_btn1">'+btn1text+'</a><a class="tc_tcbutton bg_bule" href="javascript:void(0);" id="emarkt_cmask_btn2">'+btn2text+'</a>'
					+'  <div class="clear"></div>'
					+' </div> '
					+'</div>';
	$('body').append(maskdiv);
	$('#emarkt_cmask_div').fadeIn();
	var maskheight = document.body.scrollHeight;
	$('#emarkt_cmask_div').css('height', maskheight);
	
    $('#emarkt_cmask_btn1').click(function(){
				CloseAlertconfirm();
				callback('1');
			});
	$('#emarkt_cmask_btn2').click(function(){
				CloseAlertconfirm();
				callback('2');
			});
}

function CloseAlertconfirm()
{
	$('#emarkt_cmask_div').fadeOut();
    $('#emarkt_cdialog_div').fadeOut();
}
function CancelConfirm()
{
	$('#emarkt_cmask_btn2').click();
}

function Alertinfo(contenthtml)
{
	$('#emarkt_amask_div').remove();
    $('#emarkt_adialog_div').remove();
	var maskdiv='<div class="ng_zhezhao" id="emarkt_amask_div" style="display:none;"></div>'
					+'<div class="public_tc" id="emarkt_adialog_div">'
					+'  <div class="clear"></div>'
					+' <div class="F16 c_shuise haomaxzp_tc_text">'
					+' <img class="FL" src="'+contextPath+'/resources/skin/portal/images/biaoqing/question.PNG" width="60" height="60"> <p style=" margin:50px 0 0 5px;width:180px;" class="FL">'+contenthtml+'</p><div class="clear"></div> </div>'
					+'</div>';
	$('body').append(maskdiv);
	$('#emarkt_amask_div').fadeIn();
	var maskheight = document.body.scrollHeight;
	$('#emarkt_amask_div').css('height', maskheight);

}

function CloseAlertinfo()
{
	$('#emarkt_amask_div').fadeOut();
    $('#emarkt_adialog_div').fadeOut();
}

function Maskpage()
{
	$('#emarkt_amask_div').remove();
	var maskdiv='<div class="ng_zhezhao" id="emarkt_amask_div" style="display:none;"></div>';
	$('body').append(maskdiv);
	$('#emarkt_amask_div').fadeIn();
	var maskheight = document.body.scrollHeight;
	$('#emarkt_amask_div').css('height', maskheight);

}

function CloseMaskpage()
{
	$('#emarkt_amask_div').fadeOut();
}

