<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<form name="agreeUpdateForm" method="post" action="#" target="newwindow">

	<div class="chaxun_cont">
		<li>
			<span style="display:none">
				<input id="agreeUpdate_id" name="agreeUpdate_id" class="easyui-textbox" style="width:180px;" value="${result.dataSet.id}">
			</span>
		</li>
		<li>
			<span>合同id:</span>
			<input id="au_agreeId" name="au_agreeId" class="easyui-textbox" style="width:180px" value="${result.dataSet.agreeId}">
			<span>合同名称:</span>
			<input id="au_agreeName" name="au_agreeName" class="easyui-textbox" style="width:180px" value="${result.dataSet.agreeName}">
			<span>员工工号:</span>
			<input id="au_staffId" name="au_staffId" class="easyui-textbox" style="width:180px" value="${result.dataSet.staffId}">
		</li>
		
		<li>
			<span>姓 名 :</span>
			<input id="au_staffName" name="au_staffName" class="easyui-textbox" style="width:180px" value="${result.dataSet.staffName}">
			<span>开始时间:</span>
			<input id="au_starttime" name="au_starttime" class="easyui-datebox" style="width:180px" value="${result.dataSet.starttime}">
			<span>结束时间:</span>
			<input id="au_endtime" name="au_endtime" class="easyui-datebox" style="width:180px" value="${result.dataSet.endtime}">
		</li>
	</div>
	<div class="clear"></div>
	<div style="margin: 10px;">
		<span>合同内容：</span>
		<textarea name="au_content" id="au_content" class="easyui-kindeditor" style="width:80%;height:318px" >${result.dataSet.content}</textarea>
	</div>
	<div class="butt_cont">
		<a href="#" id="saveUpdateAgree" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">保存</a> 
	</div>
</form>



<script type="text/javascript">
	var updateAgree_util = updateAgree_util || {};
	updateAgree_util.params = new Object();
	updateAgree_util.params.agreeUpdateId = $("#agreeUpdate_id").val();
	$("#agreeUpdate_id").attr("readonly", true);
	
	$("#saveUpdateAgree").click(function(){
		updateAgree_util.params.id = $("#agreeUpdate_id").val();
		updateAgree_util.params.agreeId = $("#au_agreeId").val();
		updateAgree_util.params.agreeName = $("#au_agreeName").val();
		updateAgree_util.params.staffId = $("#au_staffId").val();
		updateAgree_util.params.staffName = $("#au_staffName").val();
		updateAgree_util.params.starttime = $("#au_starttime").datebox('getValue');
		updateAgree_util.params.endtime = $("#au_endtime").datebox('getValue');
		updateAgree_util.params.content = escape(document.getElementById("au_content").value);
		
		var clientRequest = new Tcsp.ClientRequest();
		if(typeof(updateAgree_util.params.agreeId)=='undefined'||updateAgree_util.params.agreeId==''){
			$.messager.alert("操作提示",'合同id不能为空');
			return;
		} else if(typeof(updateAgree_util.params.agreeName)=='undefined'||updateAgree_util.params.agreeName==''){
			$.messager.alert("操作提示",'合同名称不能为空');
			return;
		} else if(typeof(updateAgree_util.params.staffId)=='undefined'||updateAgree_util.params.staffId==''){
			$.messager.alert("操作提示",'工号不能为空');
			return;
		} else if(typeof(updateAgree_util.params.staffName)=='undefined'||updateAgree_util.params.staffName==''){
			$.messager.alert("操作提示",'姓名不能为空');
			return;
		} else if(typeof(updateAgree_util.params.starttime)=='undefined'||updateAgree_util.params.starttime==''){
			$.messager.alert("操作提示",'开始时间不能为空');
			return;
		} else if(typeof(updateAgree_util.params.endtime)=='undefined'||updateAgree_util.params.endtime==''){
			$.messager.alert("操作提示",'结束时间不能为空');
			return;
		} else if(typeof(updateAgree_util.params.content)=='undefined'||updateAgree_util.params.content==''){
			$.messager.alert("操作提示",'合同内容不能为空');
			return;
		}
		var action='Add';
		if(updateAgree_util.params.agreeUpdateId){
			action='Save';
		}
		
		var url = managerPath + "/manager/staff/AgreementMgr.json?m=execute&f=agreement"+action;
		clientRequest.postJSON(url,updateAgree_util.params,function(respData){
			if(respData.result){
				if(respData.result.success&&respData.result.errorCode=='00000'){
					$.messager.alert("操作提示","保存成功！");
					$('#agreement_datagrid').datagrid('reload');
				} else{
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
		});
		
	});
	
	$('#centertabs').tabs({
		onBeforeClose: function(title,index){
			var target = this;
			if(title=='更新合同'){
				$("#au_content").kindeditor('editor').remove();
			} else if(title=='新增合同'){
				$("#au_content").kindeditor('editor').remove();
			}
		}
	});

</script>