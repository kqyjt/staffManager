<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>

<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/public.css" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/grzx.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/index.css"  />

<section>
  <div class="centour">
    <div class="grzx_rightlist">
    
      <div class="grzx_dingdan">
        <p class="F16 c_black PTit30"> </p> 
      <div class="publuc_box">
       <p class="public_dahang F14 c_black">用户信息</p>  
       <div class="grzx_box_two grzx_bugrt1 FL">
        <p>基本信息</p>
        <input type="text" id="USER_ID_HIDDEN" name='USER_ID_HIDDEN' style="display:none" value="${result.dataSet.userInfo.USER_ID}"/>
        <table border="0" cellpadding="0" cellspacing="0">
         <tr>
           <td width="72">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td><td>${result.dataSet.userInfo.USER_NAME }</td>
          </tr>
          <tr> 
           <td valign="top">身份证号：</td><td>${result.dataSet.userInfo.CARD_NUM }</td>
          </tr> 
          <TR>
          <td width="72">审核状态：</td><td width="228">
           	<c:choose>
           		<c:when test="${result.dataSet.userInfo.AUDIT_STATE == 'A0' }">待审核</c:when>
           		<c:when test="${result.dataSet.userInfo.AUDIT_STATE == 'A1' }">已通过</c:when>
           		<c:when test="${result.dataSet.userInfo.AUDIT_STATE == 'A2' }">未通过</c:when>
           	</c:choose>
           </td>
           </TR>
           <tr>
           <td width="72">手机号码：<td>${result.dataSet.userInfo.PHONE_NUM }</td>
          </tr>
          <tr> 
           <td valign="top">办理状态：</td>
           
           <td>
           
           <c:choose>
           		<c:when test="${result.dataSet.userInfo.BIS_STATE == 'B0' }">未办理</c:when>
           		<c:when test="${result.dataSet.userInfo.BIS_STATE == 'B1' }">已办理</c:when>
           		<c:when test="${result.dataSet.userInfo.BIS_STATE == 'B2' }">办理失败</c:when>
           	</c:choose>
           
           </td>
          </tr>
          
         </table> 
         <div class="grzx_dd_zpyl">
	        <c:if test="${not empty result.dataSet.userInfo.CARD_PIC_A2 }"><img class="testbigimg" src="${result.dataSet.userInfo.CARD_PIC_A2 }" width="120" height="88"></c:if>
	        <c:if test="${not empty result.dataSet.userInfo.CARD_PIC_B2 }"><img class="testbigimg" src="${result.dataSet.userInfo.CARD_PIC_B2 }" width="120" height="88"></c:if>
	        <c:if test="${not empty result.dataSet.userInfo.CARD_PIC_C2 }"><img class="testbigimg" src="${result.dataSet.userInfo.CARD_PIC_C2 }" width="120" height="88"></c:if>
	      </div>
       </div>  
	<div class="grzx_box_two grzx_bugrt2 FL">
        <p>国政通证件信息</p>
        
        <div class="c_59 F14">姓名：${result.dataSet.userInfo.certName }</div>
        <div class="c_59 F14">身份证号：${result.dataSet.userInfo.certId }</div>
        <div class="c_59 F14">性别：${result.dataSet.userInfo.sex }</div>
        <div class="c_59 F14">民族：${result.dataSet.userInfo.nation }</div>
        <div class="c_59 F14">出生年月：${result.dataSet.userInfo.birthday }</div>
        <div class="c_59 F14">住址：${result.dataSet.userInfo.addr }</div>
        <div class="c_59 F14">签发机关：${result.dataSet.userInfo.issue }</div>
        <div class="c_59 F14">有效期：${result.dataSet.userInfo.exp }</div>
         <div class="grzx_dd_zpyl">
	       	<c:if test="${not empty result.dataSet.userInfo.CARD_PIC_A_GZT }"><img class="testbigimg" src="${contextPath}${result.dataSet.userInfo.CARD_PIC_A_GZT }" width="102" height="126"></c:if>
	        <%-- <c:if test="${not empty result.dataSet.userInfo.CARD_PIC_B_GZT }"><img class="testbigimg" src="${result.dataSet.userInfo.CARD_PIC_B_GZT }" width="60" height="45"></c:if>
	        <c:if test="${not empty result.dataSet.userInfo.CARD_PIC_C_GZT }"><img class="testbigimg" src="${result.dataSet.userInfo.CARD_PIC_C_GZT }" width="60" height="45"></c:if> --%>
	     </div>
       </div>               
       <div class="clear"></div> 
      </div>
      </div>
    
      <div class="publuc_box">
       <p class="public_dahang F14 c_black"><i class="icon-eye-open c_green F16"></i>&nbsp;&nbsp;审核历史信息</p>
       <table border="0" cellpadding="0" cellspacing="0" class="grzx_ddgz_table">
        <tr class="grzx_ddgz_th">
         <td class="border_bottom_g" width="30"></td>
         <td class="border_right_g border_bottom_g" width="160"><i class="icon-paste"></i>审核时间</td>
         <td class="border_right_g border_bottom_g" width="90"><i class="icon-paste"></i>类型</td>
         <td class="border_right_g border_bottom_g" width="90"><i class="icon-paste"></i>审核结果</td>
         <td class="border_right_g border_bottom_g" width="450"><i class="icon-paste"></i>审核意见</td>
         <td class="border_bottom_g"><i class="icon-user"></i>操作人</td>
        </tr>
        <c:forEach items="${result.dataSet.auditList }" var="auditList" varStatus="status">
        <tr>
        <td colspan="6" style="padding:0">
          <table  border="0" cellpadding="0" cellspacing="0"  style="width:100%;" class="grzx_ddgz_table_l border_bottom_g">
           <tr>
             <td class="bg_green c_fff" width="30">${status.count }</td>
             <td class="border_right_g" width="160"> ${auditList.CREATE_TIME} </td>
             <td class="border_right_g" width="90"> 
             	<c:choose>
	           		<c:when test="${auditList.STATE_TYPE == '0' }">实名审核</c:when>
	           		<c:when test="${auditList.STATE_TYPE == '1' }">业务办理</c:when>
           		</c:choose>
			 </td>
             <td class="border_right_g" width="90">
             	<c:choose>
	           		<c:when test="${auditList.STATE == 'A0' }">已通过</c:when>
	           		<c:when test="${auditList.STATE == 'A1' }">已审核</c:when>
	           		<c:when test="${auditList.STATE == 'A2' }">不通过</c:when>
	           		<c:when test="${auditList.STATE == 'B0' }">未办理</c:when>
	           		<c:when test="${auditList.STATE == 'B1' }">已办理</c:when>
	           		<c:when test="${auditList.STATE == 'B2' }">办理失败</c:when>
           		</c:choose>
             </td>
             <td class="border_right_g" width="450">
             	<c:choose>
	           		<c:when test="${auditList.SUGGEST == 'B1' }">上传身份证信息不全</c:when>
	           		<c:when test="${auditList.SUGGEST == 'B2' }">非持证件正面照片</c:when>
	           		<c:when test="${auditList.SUGGEST == 'B3' }">照片模糊，看不清本人</c:when>
	           		<c:when test="${auditList.SUGGEST == 'B4' }">证件信息与国政通返回信息不符</c:when>
	           		<c:when test="${auditList.SUGGEST == 'B5' }">人证不一致</c:when>
	           		<c:when test="${auditList.SUGGEST == '靓号用户' }">靓号用户</c:when>
	           		<c:when test="${auditList.SUGGEST == '当前登记机主信息已实名' }">当前登记机主信息已实名</c:when>
	           		<c:when test="${auditList.SUGGEST == '当前登记机主为单位客户' }">当前登记机主为单位客户</c:when>
	           		<c:when test="${auditList.SUGGEST == '证件下用户数量超过系统限制' }">证件下用户数量超过系统限制</c:when>
	           		<c:otherwise>
				        ${auditList.SUGGEST}
				    </c:otherwise>
           		</c:choose>
             </td>
             <td>${auditList.UPDATE_STAFF_ID}</td>
           </tr>
          </table> 
        </td>
        </tr>
        </c:forEach>
       </table>	
      </div>
      
      <!-- 审核信息 -->
      <c:if test="${result.dataSet.userInfo.IS_AUDIT == '1'}">
        <form action="${managerPath }/manager/verify/VerifyManagerMgr.htm?m=execute&f=saveAuditInfo" method="post" id="auditForm"> 
      	<div class="publuc_box" >
       	<p class="public_dahang F14 c_black"><i class="icon-eye-open c_green F16"></i>&nbsp;&nbsp;实名信息审核</p>

       <table border="0" cellpadding="0" cellspacing="0" class="grzx_ddgz_table" >
        <tr class="grzx_ddgz_th" >
        <td width="115px" style="padding-left:50px;">审核意见：</td>
        	<td>
        	<select style="width:180px" id='SEL_AUDIT_STATE' name='SEL_AUDIT_STATE' class="easyui-combobox">
			  <option value=""></option>
			  <option value="A1">审核通过</option>
			  <option value="A2">审核不通过</option>
      		</select>  
      		</td>
      	</tr>
        <tr class="grzx_ddgz_th " id="AUDIT_FAIL_DIV" style="display:none">
			<td width="115px" style="padding-left:50px;">审核不通过原因：</td>
			<td>
        	<select style="width:180px" id='AUDIT_FAIL_REASON' name='AUDIT_FAIL_REASON' class="easyui-combobox">
			  <option value="B1">上传身份证信息不全</option>
			  <option value="B2">非持证件正面照片</option>
			  <option value="B3">照片模糊，看不清本人</option>
			  <option value="B4">证件信息与国政通返回信息不符</option>
			  <option value="B5">人证不一致</option>
			  <option value="其他">其他</option>
      		</select> 
      		</td> 
      	</tr>	
      		
      	<tr class="grzx_ddgz_th" id="AUDIT_FAIL_OTHER_REASON_DIV" style="display:none">
			<td width="115px" style="padding-left:50px;">其他审核不通过</br>原因请在此填写：</td>
			<td><textarea style="width:80%;height:100px"  id="AUDIT_ERROR_OTHER_REASON" name="AUDIT_ERROR_OTHER_REASON" width="500px" rows="3"></textarea></td>
        </tr>	
        
        
        </table>     
       <div style="padding:10px;">
        <p style="margin-left:430px;" id="OrderDispense_btn_cont">
			<!-- <a id="auditSubmit" href="javascript:auditInfo_save();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
			<input style="width:80px;height:28px" class="easyui-linkbutton" value = "提交"  id = "auditSubmit" data-options="iconCls:'icon-save'"  onclick = "auditInfo_save()"/>&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		</div>
		</form>
      	</div>
      	
      </c:if>
      
      
      
      <!-- 业务办理状态信息 -->
      <c:if test="${result.dataSet.userInfo.IS_CHANGE_BIS == '1'}">
      
      	<div class="publuc_box">
       	<p class="public_dahang F14 c_black"><i class="icon-eye-open c_green F16"></i>&nbsp;&nbsp;业务办理状态</p>

       <table border="0" cellpadding="0" cellspacing="0" class="grzx_ddgz_table">
        <tr class="grzx_ddgz_th">
        	<td width="115px" style="padding-left:50px;">业务办理状态：</td>
        	<td>
        	<select style="width:180px" id='SEL_BIS_STATE' name='SEL_BIS_STATE' class="easyui-combobox">
			  <option value=""></option>
			  <option value="B1">已办理</option>
			  <option value="B2">办理失败</option>
      		</select>  
      		</td>
      	</tr>
        <tr class="grzx_ddgz_th" id="BIS_FAIL_REASON_DIV" style="display:none">
			<td width="115px" style="padding-left:50px;">办理失败原因：</td>
			<td>
        	<select style="width:180px" id='BIS_FAIL_REASON' name='BIS_FAIL_REASON' class="easyui-combobox" >
			  <option value=""></option>
			  <option value="靓号用户">靓号用户</option>
		  	  <option value="当前登记机主信息已实名">当前登记机主信息已实名</option>
		  	  <option value="当前登记机主为单位客户">当前登记机主为单位客户</option>
		  	  <option value="证件下用户数量超过系统限制">证件下用户数量超过系统限制</option>
		  	  <option value="其它存在风险用户">其他</option>
		  	  
			  <!-- <option value="客户下有多个号码">客户下有多个号码</option>
		  	  <option value="靓号用户、合约用户">靓号用户、合约用户</option>
		  	  <option value="用户为实名用户">用户为实名用户</option>
		  	  <option value="其它存在风险用户">其它存在风险用户</option> -->
      		</select> 
      		</td> 
      	</tr>	
      		
      	<tr class="grzx_ddgz_th" id="BIS_ERROR_OTHER_REASON_DIV" style="display:none">
			<td width="115px" style="padding-left:50px;">其他办理失败</br>原因请在此填写：</td>
			<td><textarea style="width:80%;height:100px" id="BIS_ERROR_OTHER_REASON" name="BIS_ERROR_OTHER_REASON" width="500px" rows="3"></textarea></td>
        </tr>	
        
        </table>     
       <div style="padding:10px;">
        <p style="margin-left:430px;" id="OrderDispense_btn_cont">
        
			<!-- <a id="bisSubmit" href="javascript:changebisstate_save();" class="easyui-linkbutton" data-options="iconCls:'icon-save'">提交</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
			<input style="width:80px;height:28px" class="easyui-linkbutton" value = "提交"  id = "bisSubmit" data-options="iconCls:'icon-save'"  onclick = "changebisstate_save()"/>&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		</div>
		
      	</div>
      	
      </c:if>
      
     
      <!--我的订单--> 
    </div>
    <!--个人中心右侧-->
    <div class="clear"></div>
  </div>
</section>

<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/verify/getMyAuditOrders.js"> </script> 
<script type="text/javascript" >

      /* $('.testbigimg').tooltip({
				content: function(content){
				var imgsrc=$(this).attr('src');
				return '<img  src="'+imgsrc+'"  style="width:500px;height:300px;"/>';
				},
				onShow: function(){
					var t = $(this);
					t.tooltip('tip').unbind().bind('mouseenter', function(){
						t.tooltip('show');
					}).bind('mouseleave', function(){
						t.tooltip('hide');
					});
				}
			}); */
			
			$(".testbigimg").click(function(){
			       showbigimg($(this));
			   });

</script>