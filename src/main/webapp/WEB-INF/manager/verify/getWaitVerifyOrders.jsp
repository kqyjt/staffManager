<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

 
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>

<style>
#VerifyOrder1_search td{width:250px;}
</style>
<div class="chaxun_cont" id="VerifyOrder1_search">

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
		</tr>
		
	</table>

 <div class="clear"></div>
 <div class="butt_cont"><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="VerifyOrder1.searchSubmit();" style="width:80px">查询</a></div>
</div>
<div style="margin:10px;">

	<table id="VerifyOrder1_datagrid" class="easyui-datagrid" style="width:auto;height:350px;"
			data-options="toolbar:'#VerifyOrder1_toolbar',pageSize:10,rownumbers:true,singleSelect:false,collapsible:false,url:'${managerPath}/manager/verify/VerifyManagerMgr.json?m=query&f=getWaitVerifyOrders',method:'post',pagination:true,fitColumns:false,striped:true,autoRowHeight:false">
		<thead>
			<tr>
			    <th data-options="field:'ID',checkbox:true"></th>
				<th data-options="field:'USER_ID',width:100,align:'left'">订单号</th>
				<th data-options="field:'PHONE_NUM',width:100,align:'left'">手机号码</th>
				<th data-options="field:'CARD_NUM',width:200,align:'left'">身份证号</th>
				<th data-options="field:'USER_NAME',width:100,align:'left'">姓名</th>
				<th data-options="field:'CARD_ADDRESS',width:200,align:'left'">证件地址</th>
				<th data-options="field:'CREATE_TIME',width:200,align:'left'">提交时间</th>
			</tr>
		</thead>
	</table>
</div>

<div id="VerifyOrder1_toolbar" style="height:auto">
<table>
  <tr>
  	<td>
  			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="VerifyOrder1.dispense();">领用</a>
 	</td>
  </tr>
</table>

<div id="VerifyOrder1_dispense" class="easyui-dialog"    
        data-options="resizable:false,modal:true,title:'分配信息',width:300,height:200,closed:true"> 

</div>

<%--<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/order/OrderSearch.js"> </script>--%>
<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/verify/getWaitVerifyOrders.js"> </script>
</div>