<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../../common/taglib.jsp"%>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>

<style>
#CustomerInfo_search td{width:250px;}
</style>
	
	<!-- 查询条件及按钮 -->
	<div class="chaxun_cont" id="CustomerInfo_search">
		<ul>
			<li><span>登录账户：</span><input class="zc_inputtext" name="regName" id = "regName" type="text" value=""  datatype="*4-10"  nullmsg="请输入登录账号!">
			 </li>
		</ul>
	 	<div class="clear"></div>
	 	<div class="butt_cont">
	 		<a href="#" class="easyui-linkbutton" onclick="RESET_PASSWORD.searchCustInfo()" data-options="iconCls:'icon-search'" style="width:80px">
	 			查询
 			</a>
		</div>
	</div>
	
	
	<!-- 数据表格 -->
	<div id="divhide">
		<div style="margin:10px;">
			<table id="czmm_datagrid" class="easyui-datagrid" style="width:auto;height:350px;"
				data-options="toolbar:'#Cpgl-mytoolbar',pageSize:10,rownumbers:true,singleSelect:true,collapsible:false,method:'get',pagination:true,fitColumns:false,striped:true,autoRowHeight:false">
		  </table>
		</div>
	</div>
	 
	
	<script type="text/javascript">
	// 命名空间共用变量
	var RESET_PASSWORD = RESET_PASSWORD || {};
	RESET_PASSWORD.params = new Object();
	RESET_PASSWORD.currTab =  $('#center_tabs').tabs('getSelected'); // 获得当前tab
	
	
	// 登录账户信息查询
	RESET_PASSWORD.searchCustInfo = function(){
		RESET_PASSWORD.params.regName = $("#regName").val();
		$('#czmm_datagrid').datagrid('reload');
	}
	
	//重置密码
	RESET_PASSWORD.resetCustPassword = function(target){
		var regName = $("#regName").val();
		var url = managerPath + "/manager/sysman/ResetPassword.json?m=query&f=resetPassword&loginname="+regName;
		// 提示确认取消
		$.messager.confirm('确认对话框', '您确认要重置密码吗？', function(r){
			if(r){
				var clientRequest = new Tcsp.ClientRequest();
				var params = new Object();
				params.regName = regName;
				clientRequest.postJSON(url,params,function(respData){
					  if(respData != ''){
						  	var approveResult = "密码成功重置为：qw12!@ ,请登陆后自行修改!";
							if (respData.result.errorCode != '00000') {
								approveResult = "密码重置失败";
							}
							$.messager.alert('操作结果',approveResult,'info',function(){
								 $("#czmm_datagrid").datagrid("reload");
							});
					  }
				});
			}
		});
	}
	
	
	// 重置密码加载
	RESET_PASSWORD.load = function(){
		$('#czmm_datagrid').datagrid({
			url: managerPath + "/manager/sysman/ResetPassword.json?m=query&f=getCustInfo",
		    fitColumns:true,  
            singleSelect:true,
            queryParams : RESET_PASSWORD.params,
            pageList: [10,20,50],
            columns: [[
                   	{title: '注册名',field: 'regName',width:120,sortable: true},
                   	{title: '客户名',field: 'custName',width:100},
                   	{title: '手机号码',field: 'mobilePhone',width:100},
                   	{title: '邮箱',field: 'email',width:100},
                	{title: '身份证',field: 'idCard',width:100},
                   	{title: '归属地市',field: 'homeCityid',width:100},
                   	{title: '操作',field:'操作',sortable: false,align:'center',width:60,
                        formatter:function(value,row,index){
        					var e = '<a href="#" onclick="RESET_PASSWORD.resetCustPassword(this)">重置密码</a> ';
        					return e;
                       }
                    }
            ]],
            loadFilter: function(data){
        		if (data.result.dataSet){
        			return data.result.dataSet;
        		} else {
        			return data;
        		}
        	}
		});
	}

	// 页面初始化
	$(document).ready(function() {
		RESET_PASSWORD.load();
	});
		
	</script>

	
