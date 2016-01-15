<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>山东联通网盟平台</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="山东联通网盟平台" />
<meta name="keywords" content="山东联通网盟平台_能人之家" />

</head>
<body> 

<div class="chaxun_cont">
 <ul>
  <li><span>姓名：</span><input  name="name" class="easyui-textbox" ></li>
  <li><span>电话号码：</span><input name="hppnhone" class="easyui-textbox"></li>
  <li><span>姓名：</span><input class="easyui-textbox"></li>
  <li><span>姓名：</span><input class="easyui-textbox"></li>
  <li><span>姓名：</span><input class="easyui-textbox"></li>
 </ul>
 <div class="clear"></div>
 <div class="butt_cont"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">查询</a><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">导出</a></div>
</div>

<div style="margin:10px;">

	<table id="cppz-datagrid" class="easyui-datagrid" style="width:auto;height:350px;"
			data-options="toolbar:'#Cpgl-mytoolbar',rownumbers:true,singleSelect:false,collapsible:false,url:'datagrid_data1.json',method:'get',pagination:true,fitColumns:true,striped:true,autoRowHeight:false">
		<thead>
			<tr>
			    <th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemid',width:80">Item ID</th>
				<th data-options="field:'productid',width:100">Product</th>
				<th data-options="field:'listprice',width:80,align:'right'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
				<th data-options="field:'attr1',width:250">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th>
			</tr>
		</thead>
	</table>
</div>

<div id="Cpgl-mytoolbar" style="height:auto">
<table>
  <tr>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">新增</a></td>
	<td><div class="datagrid-btn-separator"></div></td>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">修改</a></td>
	<td><div class="datagrid-btn-separator"></div></td>
	<td><a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">删除</a></td>
  </tr>
</table>
</div>


</body>
</html>