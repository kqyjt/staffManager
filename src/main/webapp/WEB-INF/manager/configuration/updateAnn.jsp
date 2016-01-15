<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<form name="annForm" method="post" action="#" target="newwindow">
	<div class="chaxun_cont">
		<li>
			<span style="display:none">
				<input id="annId" name="annId" class="easyui-textbox" style="width:180px;" value="${result.dataSet.annId}">
			</span>
		</li>
		<li>
			<span>目标城市:</span>
			<select id = "cityId" name="cityId" class="easyui-combobox" style="width:180px;" data-options="editable:false"> 
				<c:forEach items="${result.dataSet.cityIds }" var="area">
					<option value="${area.remark }">${area.name }</option>
				</c:forEach>    
			</select>
			<span>公告类型:</span>
			<input id="annType" name="annType" class="easyui-textbox" style="width:180px" value="${result.dataSet.annType}">
			<span>公告标题:</span>
			<input id="annTitle" name="annTitle" class="easyui-textbox" style="width:180px" value="${result.dataSet.annTitle}">
		</li>
		
		<li>
			<span>链接地址:</span>
			<input id="linkUrl" name="linkUrl" class="easyui-textbox" style="width:180px" value="${result.dataSet.linkUrl}">
			<span>生效时间:</span>
			<input id="beginTime" name="beginTime" class="easyui-datetimebox" style="width:180px" value="${result.dataSet.beginTime}">
			<span>失效时间:</span>
			<input id="endTime" name="endTime" class="easyui-datetimebox" style="width:180px" value="${result.dataSet.endTime}">
		</li>
		
		<li>
			<span>公告备注:</span>
			<input id="remark" name="remark" class="easyui-textbox" style="width:180px" value="${result.dataSet.remark}">
			<span style="display:none">
				<input id="createTime" name="createTime" class="easyui-datetimebox" style="width:180px" value="${result.dataSet.createTime}">
			</span>
			<span>创  建  人 :</span>
			<input id="createStaffId" name="createStaffId" class="easyui-textbox" style="width:180px" value="${result.dataSet.createStaffId}">
			<span>更  新  人 :</span>
			<input id="updateStaffId" name="updateStaffId" class="easyui-textbox" style="width:180px" value="${result.dataSet.updateStaffId}">
		</li>
		
	</div>
	<div class="clear"></div>
	<div style="margin: 10px;">
		<span>公告内容：</span>
		<textarea name="annContent" id="annContent" class="easyui-kindeditor" style="width:80%;height:318px" >${result.dataSet.annContent}</textarea>
	</div>
	<div class="butt_cont">
		<a href="#" id="saveUpdateAnn" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">保存</a> 
	</div>
</form>



<script type="text/javascript">
	var updateAnn_util = updateAnn_util || {};
	updateAnn_util.params = new Object();
	updateAnn_util.params.ann_annId = $("#annId").val();
	$("#annId").attr("readonly", true);
	
	$("#saveUpdateAnn").click(function(){
		updateAnn_util.params.ann_annType = $("#annType").val();
		updateAnn_util.params.ann_annTitle = $("#annTitle").val();
		updateAnn_util.params.ann_linkUrl = $("#linkUrl").val();
		updateAnn_util.params.ann_remark = $("#remark").val();
		updateAnn_util.params.ann_createStaffId = $("#createStaffId").val();
		updateAnn_util.params.ann_updateStaffId = $("#updateStaffId").val();
		updateAnn_util.params.ann_cityId = $("#cityId").combobox('getValue');
		updateAnn_util.params.ann_beginTime = $("#beginTime").datetimebox('getValue');
		updateAnn_util.params.ann_endTime = $("#endTime").datetimebox('getValue');
		updateAnn_util.params.ann_createTime = $("#createTime").datetimebox('getValue');
		updateAnn_util.params.ann_annContent = escape(document.getElementById("annContent").value);
		
		var clientRequest = new Tcsp.ClientRequest();
		if(typeof(updateAnn_util.params.ann_cityId)=='undefined'||updateAnn_util.params.ann_cityId==''){
			$.messager.alert("操作提示",'目标地市不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_annTitle)=='undefined'||updateAnn_util.params.ann_annTitle==''){
			$.messager.alert("操作提示",'公告标题不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_linkUrl)=='undefined'||updateAnn_util.params.ann_linkUrl==''){
			$.messager.alert("操作提示",'链接地址不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_beginTime)=='undefined'||updateAnn_util.params.ann_beginTime==''){
			$.messager.alert("操作提示",'生效时间不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_endTime)=='undefined'||updateAnn_util.params.ann_endTime==''){
			$.messager.alert("操作提示",'失效时间不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_createStaffId)=='undefined'||updateAnn_util.params.ann_createStaffId==''){
			$.messager.alert("操作提示",'创建人不能为空');
			return;
		} else if(typeof(updateAnn_util.params.ann_annContent)=='undefined'||updateAnn_util.params.ann_annContent==''){
			$.messager.alert("操作提示",'公告内容不能为空');
			return;
		}
		var action='create';
		if(updateAnn_util.params.ann_annId){
			action='save';
		}
		
		var url = managerPath + "/manager/sysman/AnnouncementMgr.json?m=execute&f="+action+"Ann";
		clientRequest.postJSON(url,updateAnn_util.params,function(respData){
			if(respData.result){
				if(respData.result.success&&respData.result.errorCode=='00000'){
					$.messager.alert("操作提示","保存成功！");
					$('#ann-datagrid').datagrid('reload');
				} else{
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
		});
		
	});

	$('#centertabs').tabs({
		onBeforeClose: function(title,index){
			var target = this;
			if(title=='公告更新'){
				$("#annContent").kindeditor('editor').remove();
			} else if(title=='新增公告'){
				$("#annContent").kindeditor('editor').remove();
			}
		}
	});

</script>