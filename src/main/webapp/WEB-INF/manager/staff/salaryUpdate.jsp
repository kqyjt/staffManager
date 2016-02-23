<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<form name="salaryUpdateForm" method="post" action="#" target="newwindow">

	<div class="chaxun_cont">
		<li>
			<span style="display:none">
				<input id="salaryUpdate_id" name="salaryUpdate_id" class="easyui-textbox" style="width:180px;" value="${result.dataSet.id}">
			</span>
		</li>
		
		<li>
			<span>月份:</span>
			<select id="su_month" name="su_month" class="easyui-combobox" style="width:180px;" data-options="editable:false"> 
				<c:forEach items="${result.dataSet.monthList }" var="entry">
					<option value="${entry.monthId }">${entry.monthName }</option>
				</c:forEach>    
			</select>
		</li>
	</div>

	<div class="chaxun_cont">
		<li>
			<span>员工工号:</span>
			<input id="su_staffId" name="su_staffId" class="easyui-textbox" style="width:180px" value="${result.dataSet.staffId}">
			<span>姓          名:</span>
			<input id="su_staffName" name="su_staffName" class="easyui-textbox" style="width:180px" value="${result.dataSet.staffName}">
			<span>职工状况:</span>
			<select id="su_state" name="su_state" class="easyui-combobox" style="width:180px;" data-options="editable:false"> 
				<c:forEach items="${result.dataSet.stateList }" var="entry">
					<option value="${entry.stateId }">${entry.stateName }</option>
				</c:forEach>    
			</select>
		</li>
		
		<li>
			<span>录入时间:</span>
			<input id="su_inputtime" name="su_inputtime" class="easyui-datebox" style="width:180px" value="${result.dataSet.inputtime}">
			<span>工          龄:</span>
			<input id="su_workage" name="su_workage" class="easyui-textbox" style="width:180px" value="${result.dataSet.workage}">
		</li>
	</div>
	<div class="clear"></div>
	<div  class="chaxun_cont">
		<li>
			<span>基本工资:</span>
			<input id="su_basicSalary" name="su_basicSalary" class="easyui-textbox" style="width:180px" value="${result.dataSet.basicSalary}">
			<span>福  利  金 :</span>
			<input id="su_welfare" name="su_welfare" class="easyui-textbox" style="width:180px" value="${result.dataSet.welfare}">
			<span>缺  勤  费 :</span>
			<input id="su_absencePay" name="su_absencePay" class="easyui-textbox" style="width:180px" value="${result.dataSet.absencePay}">
		</li>
		
		<li>
			<span>职  务  费 :</span>
			<input id="su_postPay" name="su_postPay" class="easyui-textbox" style="width:180px" value="${result.dataSet.postPay}">
			<span>伙  食  费 :</span>
			<input id="su_mealPay" name="su_mealPay" class="easyui-textbox" style="width:180px" value="${result.dataSet.mealPay}">
			<span>医疗保险:</span>
			<input id="su_medicalPay" name="su_medicalPay" class="easyui-textbox" style="width:180px" value="${result.dataSet.medicalPay}">
		</li>
		
		<li>
			<span>工  龄  费 :</span>
			<input id="su_workagePay" name="su_workagePay" class="easyui-textbox" style="width:180px" value="${result.dataSet.workagePay}">
			<span>养老保险:</span>
			<input id="su_retirePay" name="su_retirePay" class="easyui-textbox" style="width:180px" value="${result.dataSet.retirePay}">
			<span>加  班  费 :</span>
			<input id="su_overtimePay" name="su_overtimePay" class="easyui-textbox" style="width:180px" value="${result.dataSet.overtimePay}">
		</li>
		
		<li>
			<span>补发工资:</span>
			<input id="su_addSalary" name="su_addSalary" class="easyui-textbox" style="width:180px" value="${result.dataSet.addSalary}">
			<span>本月奖金:</span>
			<input id="su_bonus" name="su_bonus" class="easyui-textbox" style="width:180px" value="${result.dataSet.bonus}">
			<span>其他费用:</span>
			<input id="su_otherPay" name="su_otherPay" class="easyui-textbox" style="width:180px" value="${result.dataSet.otherPay}">
		</li>
		
		<li>
			<span>所  得  税 :</span>
			<input id="su_incomeTax" name="su_incomeTax" class="easyui-textbox" style="width:180px" value="${result.dataSet.incomeTax}">
			<span>应扣工资:</span>
			<input id="su_deductSalary" name="su_deductSalary" class="easyui-textbox" style="width:180px" value="${result.dataSet.deductSalary}">
			<span>实发工资:</span>
			<input id="su_realitySalary" name="su_realitySalary" class="easyui-textbox" style="width:180px" value="${result.dataSet.realitySalary}">
		</li>
	</div>
	<div style="margin: 10px;">	
		<li>
			<span>备          注:</span>
			<input id="su_remark" name="su_remark" class="easyui-textbox" style="width:680px" value="${result.dataSet.remark}">
		</li>
	</div>
	<div class="butt_cont_1">
		<a href="#" id="saveUpdateSal" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width: 80px">保存</a> 
	</div>
</form>



<script type="text/javascript">
	var updateSal_util = updateSal_util || {};
	updateSal_util.params = new Object();
	updateSal_util.params.salaryUpdateId = $("#salaryUpdate_id").val();
	$("#salaryUpdate_id").attr("readonly", true);
	
	$("#saveUpdateSal").click(function(){
		updateSal_util.params.id = $("#salaryUpdate_id").val();
		updateSal_util.params.month = $("#su_month").combobox('getValue');
		updateSal_util.params.staffId = $("#su_staffId").val();
		updateSal_util.params.staffName = $("#su_staffName").val();
		updateSal_util.params.state = $("#su_state").combobox('getValue');
		updateSal_util.params.inputtime = $("#su_inputtime").datebox('getValue');
		updateSal_util.params.workage = $("#su_workage").val();
		updateSal_util.params.basicSalary = $("#su_basicSalary").val();
		updateSal_util.params.welfare = $("#su_welfare").val();
		updateSal_util.params.absencePay = $("#su_absencePay").val();
		updateSal_util.params.postPay = $("#su_postPay").val();
		updateSal_util.params.mealPay = $("#su_mealPay").val();
		updateSal_util.params.medicalPay = $("#su_medicalPay").val();
		updateSal_util.params.workagePay = $("#su_workagePay").val();
		updateSal_util.params.retirePay = $("#su_retirePay").val();
		updateSal_util.params.overtimePay = $("#su_overtimePay").val();
		updateSal_util.params.addSalary = $("#su_addSalary").val();
		updateSal_util.params.bonus = $("#su_bonus").val();
		updateSal_util.params.otherPay = $("#su_otherPay").val();
		updateSal_util.params.incomeTax = $("#su_incomeTax").val();
		updateSal_util.params.deductSalary = $("#su_deductSalary").val();
		updateSal_util.params.realitySalary = $("#su_realitySalary").val();
		updateSal_util.params.remark = $("#su_remark").val();
		
		var clientRequest = new Tcsp.ClientRequest();
		if(typeof(updateSal_util.params.month)=='undefined'||updateSal_util.params.month==''){
			$.messager.alert("操作提示",'月份不能为空');
			return;
		} else if(typeof(updateSal_util.params.staffId)=='undefined'||updateSal_util.params.staffId==''){
			$.messager.alert("操作提示",'工号不能为空');
			return;
		} else if(typeof(updateSal_util.params.staffName)=='undefined'||updateSal_util.params.staffName==''){
			$.messager.alert("操作提示",'姓名不能为空');
			return;
		} else if(typeof(updateSal_util.params.inputtime)=='undefined'||updateSal_util.params.inputtime==''){
			$.messager.alert("操作提示",'录入时间不能为空');
			return;
		} else if(typeof(updateSal_util.params.basicSalary)=='undefined'||updateSal_util.params.basicSalary==''){
			$.messager.alert("操作提示",'基本工资不能为空');
			return;
		} else if(typeof(updateSal_util.params.realitySalary)=='undefined'||updateSal_util.params.realitySalary==''){
			$.messager.alert("操作提示",'实发工资不能为空');
			return;
		}
		var action='Add';
		if(updateSal_util.params.salaryUpdateId){
			action='Save';
		}
		
		var url = managerPath + "/manager/staff/SalaryMgr.json?m=execute&f=salary"+action;
		clientRequest.postJSON(url,updateSal_util.params,function(respData){
			if(respData.result){
				if(respData.result.success&&respData.result.errorCode=='00000'){
					$.messager.alert("操作提示","保存成功！");
					$('#salary_datagrid').datagrid('reload');
				} else{
					$.messager.alert("操作提示",respData.result.errorMsg);
				}
			}
		});
		
	});

</script>