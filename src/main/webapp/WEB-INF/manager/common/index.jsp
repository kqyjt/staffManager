<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>员工信息管理系统</title>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/initjs.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="description" content="员工信息管理系统" />
<meta name="keywords" content="员工信息管理系统" />


    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/manager/js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/manager/js/easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/manager/js/easyui/icons/icon-munich.css" />
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/manager/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/manager/css/publicall.css"/>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/skin/common/js/JqueryPagination/jquery.pagination.css"/>
    
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/jquery.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/extends/tabs.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/easyextend.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/plungin/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/extends/kindeditor.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/main.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/common/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/manager/js/AlertConfirm.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/common/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="${contextPath}/resources/skin/common/js/JqueryPagination/jquery.pagination-1.2.7.js"></script>
<body class="easyui-layout">   

<div data-options="region:'north',split:false,href:'${contextPath}/manager/pageloader/PageLoader.htm?m=query&f=headPage'" class="northhead" ></div>
<div id="menupanel" data-options="region:'west',split:true,title:'${result.dataSet.initMenu }',href:'${contextPath}/manager/pageloader/PageLoader.htm?m=query&f=leftMenuPage',iconCls:'${result.dataSet.initMenuIcon }'" class="westmenu" ></div> 
<div data-options="region:'center',border:false" >
		<div id="center_tabs" class="easyui-tabs"
			data-options="fit:true,border:true,plain:false">
			<div title="欢迎使用后台管理"
				data-options="iconCls:'icon-munich-bank',closable:false,href:'${contextPath}/manager/pageloader/PageLoader.htm?m=query&f=welcomePage',refreshable: false">
			</div>
		</div>
	</div>
</body>
</html>