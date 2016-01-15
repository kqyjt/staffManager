<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>

<style>
#reset_admin_passWord_custInfor_search td{width:250px;}
</style>
	<input type="hidden"  id = "reset_admin_passWord_staff_name"/ >
	<!-- 查询条件及按钮 -->
	<div class="chaxun_cont" id="reset_admin_passWord_custInfor_search">
		<ul>
			<li><span>用户名：</span><input class="zc_inputtext" name="regName" id = "reset_admin_passWord_regName" type="text" value="" >
			 </li>
		</ul>
	 	<div class="clear"></div>
	 	<div class="butt_cont">
	 		<a href="#" class="easyui-linkbutton" onclick="RESET_ADMIN_PASSWORD.searchStaffInfo()" data-options="iconCls:'icon-search'" style="width:80px">
	 			查询
 			</a>
		</div>
	</div>
	
	
	<!-- 数据表格 -->
	<div id="divhide">
		<div style="margin:10px;">
			<table id="reset_admin_passWord__datagrid"  style="width:auto;height:350px;">
		  </table>
		</div>
	</div>
	 
	
<script type="text/javascript">
	// 命名空间共用变量
	var RESET_ADMIN_PASSWORD = RESET_ADMIN_PASSWORD || {};
	RESET_ADMIN_PASSWORD.params = new Object();
	// 登录账户信息查询
	RESET_ADMIN_PASSWORD.searchStaffInfo = function(){
		RESET_ADMIN_PASSWORD.params.regName = $("#reset_admin_passWord_regName").val();
		var staffRegname = $("#reset_admin_passWord_staff_name").val();
		if($("#reset_admin_passWord_regName").val() === 'system'){
			if(staffRegname != 'system'){
				return;
			}
		}
		$('#reset_admin_passWord__datagrid').datagrid('reload');
	}
	
	//重置密码
	RESET_ADMIN_PASSWORD.resetStaffPassword = function(target){
		var regName = $('#reset_admin_passWord__datagrid').datagrid('getData').rows[target].staffId;
		var staffRegname = $("#reset_admin_passWord_staff_name").val();
		if(staffRegname === regName){
			$.messager.alert("操作提示",'不可重置本人密码,<br/> 如需修改，请使用管理员密码修改功能!');
			return;
		}
		var url = managerPath + "/manager/sysman/ResetPassword.json?m=execute&f=resetStaffPassword";
		// 提示确认取消
		$.messager.confirm('确认对话框', '您确认要重置密码吗？', function(r){
			if(r){
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.regName = regName;
				clientRequest.postJSON(url,params,function(respData){
					  if(respData != ''){
						  	var approveResult = "密码成功重置为：qw12!@ ,请管理员登陆后自行修改!";
							if (respData.result.errorCode != '00000') {
								approveResult = "密码重置失败";
							}
							$.messager.alert('操作结果',approveResult,'info',function(){
								 $("#reset_admin_passWord__datagrid").datagrid("reload");
							});
					  }
				});
			}
		});
	}
	
	
	//表格加载
	$('#reset_admin_passWord__datagrid').datagrid({
		url: managerPath + "/manager/sysman/ResetPassword.json?m=query&f=getStaffInfo",
	    fitColumns:true,  
           singleSelect:true,
           queryParams : RESET_ADMIN_PASSWORD.params,
           pageList: [10,20,50],
           columns: [[
                  	{title: '用户名',field: 'staffId',width:120,sortable: true},
                  	{title: '姓名',field: 'NAME',width:100},
                  	{title: '手机号码',field: 'phoneNumber',width:100},
                  	{title: '邮箱',field: 'email',width:100},
                  	{title: '归属地市',field: 'areaCode',width:100},
               	{title: '更新员工',field: 'updateStaffId',width:100},
               	{title: '更新时间',field: 'updateTime',width:100},
                  	{title: '操作',field:'操作',sortable: false,align:'center',width:60,
                       formatter:function(value,row,index){
       					var e = '<a href="#" onclick="RESET_ADMIN_PASSWORD.resetStaffPassword('+index+')">重置密码</a> ';
       					return e;
                      }
                   }
           ]],
           loadFilter: function(data){
       		if (data.result.dataSet){
       			$('#reset_admin_passWord_staff_name').val(data.result.dataSet.staffName);
       			return data.result.dataSet;
       		} else {
       			return data;
       		}
       	}
	});
</script>

	
