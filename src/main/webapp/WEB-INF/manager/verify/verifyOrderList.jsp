<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

 
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>

<style>
#VerifyOrder3_search td{width:250px;}
</style>
<div class="chaxun_cont" id="VerifyOrder3_search">
<form action="${managerPath }/manager/verify/VerifyManagerMgr.htm?m=query&f=exportVerifyOrderList" method="post" id="verifyOrderForm">
 <table>
		<tr>
			<td><span>订单号：</span></td>
			<td><input  name="USER_ID" class="easyui-textbox" ></td>
			<td><span>地市：</span></td>
			<td><select  name="CITY_ID" class="easyui-combobox">
			  		<c:forEach items="${result.dataSet.areas }" var="area">
			  			<option value="${area.code }">${area.name }</option>
			  		</c:forEach>
			  	</select>
  			</td>
			<td><span>身份证号：</span></td>
			<td><input name="CARD_NUM" class="easyui-textbox"></td>
			<td><span>手机号码：</span></td>
			<td><input name="PHONE_NUM" class="easyui-textbox"></td>
		</tr>
		<tr>
			<td><span>提交时间起：</span></td>
			<td><input name="BEGIN_TIME" class="easyui-datebox">
			</td>
			<td><span>提交时间至：</span></td>
			<td><input name="END_TIME" class="easyui-datebox">
  			</td>
  			<td><span>审核状态：</span></td>
			<td>
			<select id="AUDIT_STATE"  name="AUDIT_STATE" class="easyui-combobox">
		  		<option value="">--全部--</option>
		  		<option value="A0">待审核</option>
		  		<option value="A1">已通过</option>
		  		<option value="A2">未通过</option>
		  	</select>
  			</td>
  			<td><span>业务办理状态：</span></td>
			<td>
			<select id="BIS_STATE"  name="BIS_STATE" class="easyui-combobox">
		  		<option value="">--全部--</option>
		  		<option value="B0">待办理</option>
		  		<option value="B1">已办理</option>
		  		<option value="B2">办理失败</option>
		  	</select>
  			</td>
		</tr>
		
		<tr>
			<td><span>审核员工：</span></td>
			<td><input name="AUDIT_STAFF" class="easyui-textbox"></td>
		</tr>
		
	</table>
</form>
 <div class="clear"></div>
	 <div class="butt_cont">
	 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="VerifyOrder3.searchSubmit();" style="width:80px">查询</a>
	 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="VerifyOrder3.exprotVerifyOrderList();" style="width:80px">导出</a>
	 </div>
</div>
<div style="margin:10px;">

	<table id="VerifyOrder3_datagrid" class="easyui-datagrid" style="width:auto;height:350px;"
			data-options="toolbar:'#VerifyOrder3_toolbar',pageSize:10,rownumbers:true,singleSelect:true,collapsible:false,url:'${managerPath}/manager/verify/VerifyManagerMgr.json?m=query&f=getVerifyOrderList',method:'post',pagination:true,fitColumns:false,striped:true,autoRowHeight:false">
		<thead>
			<tr>
			    <th data-options="field:'ID',checkbox:true"></th>
				<th data-options="field:'USER_ID',width:80,align:'left'">订单号</th>
				<th data-options="field:'PHONE_NUM',width:100,align:'left'">手机号码</th>
				<th data-options="field:'CITY_NAME',width:100,align:'left'">地市</th>
				<th data-options="field:'CARD_NUM',width:120,align:'left'">身份证号</th>
				<th data-options="field:'USER_NAME',width:100,align:'left'">姓名</th>
				<th data-options="field:'CARD_ADDRESS',width:200,align:'left'">证件地址</th>
				<th data-options="field:'CREATE_TIME',width:130,align:'left'">提交时间</th>
				<th data-options="field:'AUDIT_STATE',width:100,align:'left',formatter:function(value,row,index){return verifyOrder2ConvertState(value);}">审核状态</th>
				<th data-options="field:'BIS_STATE',width:100,align:'left',formatter:function(value,row,index){return verifyOrder2ConvertState(value);}">业务办理状态</th>
				<th data-options="field:'UPDATE_TIME',width:130,align:'left'">更新时间</th>
			</tr>
		</thead>
	</table>
</div>

<div id="VerifyOrder3_toolbar" style="height:auto">
<table>
  <tr>
  	<td>
<%--  			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="VerifyOrder3.exportVerifyOrders();">导出</a>--%>
 	</td>
  </tr>
</table>

<div id="VerifyOrder3_dispense" class="easyui-dialog"    
        data-options="resizable:false,modal:true,title:'分配信息',width:300,height:200,closed:true"> 

</div>

<%--<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/order/OrderSearch.js"> </script>--%>
<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/verify/verifyOrderList.js"> </script>
</div>