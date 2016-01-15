<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<div class="chaxun_cont">

 <ul>
  
  <li><span>注册名：</span><input  type="text" id="tomain_regName" name="regName" value="${param.regName }" class="easyui-textbox" ></li>
  <li><span>手机号码：</span><input  type="text" id="tomain_mobilePhone" name="mobilePhone" value="${param.mobilePhone }" class="easyui-textbox" ></li>
  <li><span>QQ：</span><input  type="text" id="tomain_qqNo" name="qqNo" value="${param.qqNo }" class="easyui-textbox" ></li>
  <li><span>身份证：</span><input  type="text" id="tomain_idCard" name="idCard" value="${param.idCard }" class="easyui-textbox" ></li>
  
 </ul>
 
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" onclick="TOMAIN_PUBLIC.searchCustomer()" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a></div>
</div>

<div style="margin:10px;">
	<table id="ddcx-datagrid"></table>

</div>

<script type="text/javascript">
	var TOMAIN_PUBLIC = TOMAIN_PUBLIC || {};
	TOMAIN_PUBLIC.queryUrl = managerPath + "/manager/controller/TOMainMgr.json?m=execute&f=customerQuery";
	TOMAIN_PUBLIC.params = new Object();


	TOMAIN_PUBLIC.getpagedata = function()
{
	var clientRequest = new Tcsp.ClientRequest();
	
	clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data) {
		//处理数据，重画数据区域
		$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
		
		$("#ddcx-datagrid").datagrid("getPager").pagination({
			onSelectPage:function(pageNumber,pageSize){
				TOMAIN_PUBLIC.params.page = (pageNumber - 1) * pageSize;
				TOMAIN_PUBLIC.params.size = pageSize;
	     		
	     		 /*  clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data){
				$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list}); 
	   		    });  */
	    		    $.getJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params, function (data){
		         	$("#ddcx-datagrid").datagrid('loadData', {total : data.result.dataSet.records, rows : data.result.dataSet.list});
				}
				)
       	}  
	});
	},function(errresponse) {
		alter("没有数据");
	});
	
}   

TOMAIN_PUBLIC.searchCustomer = function(){
	
	TOMAIN_PUBLIC.params.regName = $("#tomain_regName").val();
	TOMAIN_PUBLIC.params.mobilePhone = $("#tomain_mobilePhone").val();
	TOMAIN_PUBLIC.params.qqNo = $("#tomain_qqNo").val();
	TOMAIN_PUBLIC.params.idCard = $("#tomain_idCard").val();
	
	var clientRequest = new Tcsp.ClientRequest();
	clientRequest.postJSON(TOMAIN_PUBLIC.queryUrl,TOMAIN_PUBLIC.params,function(data){
		$("#ddcx-datagrid").datagrid("loadData", {total : data.result.dataSet.records, rows : data.result.dataSet.list});
	});
	
}


TOMAIN_PUBLIC.updateActions=function(index){
	$('#ddcx-datagrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}


	TOMAIN_PUBLIC.grid=$('#ddcx-datagrid').datagrid({
		
	    iconCls:'icon-edit',
	    //width:'auto',
	    height: 'auto',  
	    nowrap: false,  
	    striped: true,  
	    border: true,  
	    collapsible:false,//是否可折叠的  
	    fitColumns: false,//自动大小  
	    url: managerPath+'/manager/controller/TOMainMgr.json?m=execute&f=customerQuery',
	    //sortName: 'ID',  
	    //sortOrder: 'asc', 
	    loadMsg:'数据加载中...',
	    remoteSort:false,   
	    idField:'id',  
	    singleSelect:true,//只允许选择一行  
	    pagination:true,//分页控件  
	    rownumbers:true,//显示行号  
	    frozenColumns:[[]],
	    columns: [[
	    		{field: 'ck',checkbox:true},
	    		{title: '唯一标识',field: 'id',width:80,sortable:false,align:'center'},
	    		{title: '注册名',field: 'regName',width:80,sortable: true,align:'left',editor:'text'},
	    		{title: '客户名',field: 'custName',width:80,sortable: true,align:'left',editor:'text'},
	    		{title: '登录密码',field: 'password',width:100,sortable:false,align:'center',editor:'text'},
				{title: '手机号码', field: 'mobilePhone',width:60,sortable: false,align:'center',editor:'text'},
	        	{title: '邮箱', field: 'email', width:80,sortable: false,align:'center',editor:'text'},
	        	{title: '固定电话', field: 'telephone', width:100,sortable: false,align:'center',editor:'text'},
	        	{title: 'QQ', field: 'qqNo', width:100,sortable: false,align:'center',editor:'text'},
	        	{title: '客户状态', field: 'state', width:120,sortable: false,align:'center',editor:'text',
	        		formatter:function(value,row,index){
	                	if (value=='CU01'){
							return '审核中';
						}
	               }
	        	},
	        	{title: '客户类型', field: 'custType', width:80,sortable: false,align:'center',editor:'text',
	        		formatter:function(value,row,index){
	                	if (value=='CT01'){
							return '能人';
						}
	               }
	        	},
	        	{title: '客户级别', field: 'custLvl', width:80,sortable: false,align:'center',editor:'text',
	        		formatter:function(value,row,index){
	                	if (value=='CL00'){
							return '酒虫';
						}else if (value=='CL01'){
							return '酒鬼';
						}else if (value=='CL02'){
							return '酒仙';
						}else if (value=='CL03'){
							return '普通经销商';
						}else if (value=='CL04'){
							return '金牌经销商';
						}
	               }
	        	},
	        	{title: '性别', field: 'sex', width:100,sortable: false,align:'center',editor:'text'},
	        	{title: '身份证', field: 'idCard', width:80,sortable: false,align:'center',editor:'text'},
	        	{title: '所在省份',field: 'homeprovinceid',width:80,sortable:false,align:'center'},
	    		{title: '所在地市',field: 'homeCityid',width:80,sortable: true,align:'left',editor:'text'},
	    		{title: '所在区县',field: 'homeAreaid',width:80,sortable: true,align:'left',editor:'text'},
	    		{title: '联系地址',field: 'receiveAddr',width:100,sortable:false,align:'center',editor:'text'},
				{title: '销售省份', field: 'provinceid',width:60,sortable: false,align:'center',editor:'text'},
	        	{title: '销售区域', field: 'cityid', width:80,sortable: false,align:'center',editor:'text'},
	        	{title: '客户来源', field: 'userSource', width:100,sortable: false,align:'center',editor:'text',
	        		formatter:function(value,row,index){
	                	if (value=='CO00'){
							return '网站申请';
						}else if (value=='CO01'){
							return '手机客户端申请';
						}else if (value=='CO02'){
							return '微信申请';
						}else if (value=='CO03'){
							return '管理员创建';
						}
	               }
	        	},
	        	{title: '开户工号', field: 'staffId', width:100,sortable: false,align:'center',editor:'text'},
	        	{title: '客户经理编号', field: 'managerId', width:120,sortable: false,align:'center',editor:'text'},
	        	{title: '客户经理姓名', field: 'managerName', width:80,sortable: false,align:'center',editor:'text'},
	        	{title: '发展人标识', field: 'devCustId', width:80,sortable: false,align:'center',editor:'text'},
	        	{title: '配送标志', field: 'expressFlag', width:100,sortable: false,align:'center',editor:'text',
	        		formatter:function(value,row,index){
	                	if (value=='0'){
							return '仅需自提';
						}else if (value=='1'){
							return '允许配送';
						}
	               }
	        	},
	        	{title: '头像URL', field: 'faceUrl', width:100,sortable: false,align:'center',editor:'text'},
	        	{title: '备注', field: 'remark', width:120,sortable: false,align:'center',editor:'text'},
	        	{title: '创建时间', field: 'createTime', width:80,sortable: false,align:'center',editor:'datetimebox'},
	        	{title: '更新时间', field: 'updateTime', width:120,sortable: false,align:'center',editor:'datetimebox'},
	        	{title: '更新人员', field: 'updateOperator', width:60,sortable: false,align:'center',editor:'text'}
	        	
	        	
	        	
	    	]],
	    	onBeforeEdit:function(index,row){
				row.editing = true;
				TOMAIN_PUBLIC.updateActions(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				TOMAIN_PUBLIC.updateActions(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				TOMAIN_PUBLIC.updateActions(index);
			},
	    loadFilter: function(data){
	    		if (data.result){
	    			return data.result.dataSet;
	    		} else {
	    			return data;
	    		}
	    },
	    pageNumber: 1,
	    pageList: [10,20,30,40,50],
	    pageSize: 10
	
	});
	
	TOMAIN_PUBLIC.getpagedata();
	

</script>

