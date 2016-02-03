<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/public.css" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/grzx.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/index.css"  />

<c:set var="lan_type" value=""></c:set>
<c:set var="speed_type" value=""></c:set>
<c:set var="contract_time" value=""></c:set>
<c:set var="share_flow" value=""></c:set>
<c:set var="share_phone" value=""></c:set>
<c:set var="servpack" value=""></c:set>
<c:set var="numcityid" value=""></c:set>
<c:set var="serv_cost" value=""></c:set>
<c:set var="price" value=""></c:set>
<c:set var="start_cost" value=""></c:set>
<c:set var="device_price" value=""></c:set>
 <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'lan_type'}">
   		<c:set var="lan_type" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
   <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'speed_type'}">
   		<c:set var="speed_type" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
     <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'contract_time'}">
   		<c:set var="contract_time" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
<c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'share_flow'}">
   		<c:set var="share_flow" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
  <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'share_phone'}">
   		<c:set var="share_phone" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
    <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'servpack'}">
   		<c:set var="servpack" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
   <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'numcityid'}">
   		<c:set var="numcityid" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
     <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'serv_cost'}">
   		<c:set var="serv_cost" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
  <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'price'}">
   		<c:set var="price" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
    <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'start_cost'}">
   		<c:set var="start_cost" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
      <c:forEach items="${result.dataSet.orderDetails[0].attribute }" var="attribute">
   	<c:if test="${fn:split(attribute, '^')[2] == 'device_price'}">
   		<c:set var="device_price" value="${fn:split(attribute, '^')[1]}"></c:set>
   	</c:if>
  </c:forEach>
<section>
  <div class="centour">
    
    <div class="grzx_rightlist">
      <div class="grzx_dingdan">
        <p class="F16 c_black PTit30"></p>
        <!--cx-->
        <div class="grzx_dd_xq">
         <p class="c_shuise border_bottom">订单号：${result.dataSet.oMain.orderSn }&nbsp;&nbsp;&nbsp;&nbsp;状态：<span class="c_green">
         		<c:if test="${result.dataSet.oMain.state == 'OM00' }">待支付</c:if>
         		<c:if test="${result.dataSet.oMain.state == 'OM01' }">待审核</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM02'}">审核不通过</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM03'}">待分配</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM04'}">待开户</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM05' }">开户失败</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM06' }">待配送</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM07' }">待签收</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM08'}">已签收</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM09'}">拒绝签收</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM10' }">已取消</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM11' }">已失效</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM12' }">已退款</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM14' }">待自提</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM15' }">已完成</c:if>
              	<c:if test="${result.dataSet.oMain.state == 'OM16' }">归档失败</c:if>
              	</span>&nbsp;&nbsp;&nbsp;&nbsp;类别：<span class="c_bule">
         <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT00' || result.dataSet.orderDetails[0].goodsType == 'GT03' || result.dataSet.orderDetails[0].goodsType == 'GT04' || result.dataSet.orderDetails[0].goodsType == 'GT08' || result.dataSet.orderDetails[0].goodsType == 'GT09' || result.dataSet.orderDetails[0].goodsType == 'GT13' || result.dataSet.orderDetails[0].goodsType == 'GT14' || result.dataSet.orderDetails[0].goodsType == 'GT15'
          || result.dataSet.orderDetails[0].goodsType == 'GT17' }">4G套餐</c:if>
          <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT05' }">宽带</c:if><c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT06' }">智慧沃家</c:if>
          <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT07' || result.dataSet.orderDetails[0].goodsType == 'GT10' || result.dataSet.orderDetails[0].goodsType == 'GT11' || result.dataSet.orderDetails[0].goodsType == 'GT12' }">老用户续约</c:if>
         </span></p>
         <p class="c_red">${result.dataSet.orderDetails[0].goodsName}</p>
        </div>
  
      <div class="publuc_box">
       <p class="public_dahang F14 c_black"><i class="icon-eye-open c_green F16"></i>&nbsp;&nbsp;订单跟踪信息</p>
       <table border="0" cellpadding="0" cellspacing="0" class="grzx_ddgz_table">
        <tr class="grzx_ddgz_th">
         <td class="border_bottom_g" width="30"></td>
         <td class="border_right_g border_bottom_g" width="180"><i class="icon-time"></i>处理时间</td>
         <td class="border_right_g border_bottom_g" width="520"><i class="icon-paste"></i>处理信息</td>
         <td class="border_bottom_g"><i class="icon-user"></i>操作人</td>
        </tr>
        <c:forEach items="${result.dataSet.oStatehistorys }" var="oStatehistory" varStatus="status">
        <tr>
        <td colspan="4" style="padding:0">
          <table  border="0" cellpadding="0" cellspacing="0"  style="width:100%;" class="grzx_ddgz_table_l border_bottom_g">
           <tr>
             <td class="bg_green c_fff" width="30">${status.count }</td>
             <td class="border_right_g" width="180"> <fmt:formatDate  value="${oStatehistory.beginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
             <td class="border_right_g" width="520">${oStatehistory.remark}
             <c:if test="${not empty oStatehistory.optReback }">
             <br>失败原因：${oStatehistory.optReback }
             </c:if>
             </td>
             <td>${oStatehistory.operateStaffName}</td>
           </tr>
          </table> 
        </td>
        </tr>
        </c:forEach>
       </table>	
      </div>
      <!-- 物流信息 -->
      <c:if test="${result.dataSet.orderDetails[0].goodsType != 'GT07' && result.dataSet.orderDetails[0].goodsType != 'GT10' && result.dataSet.orderDetails[0].goodsType != 'GT11' && result.dataSet.orderDetails[0].goodsType != 'GT12'}">
      <c:if test="${result.dataSet.oLogistics.deliverType == 'DT00' || result.dataSet.oLogistics.deliverType == 'DT01' }">
		<div class="publuc_box">
			<p class="public_dahang F14 c_black">物流信息&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size:10px;float:right;">承运方：顺丰速运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;物流单号：<b class="c_red">${result.dataSet.oLogistics.mailNo }</b></span></p>
			<table border="0" cellpadding="0" cellspacing="0" class="grzx_ddgz_table grzx_ddgz_table_l border_bottom_g"">
				<tr class="grzx_ddgz_th" style="padding:10px;">
					<td class="border_bottom_g" width="30"></td>
					<td class="border_right_g border_bottom_g" width="180"><i
						class="icon-time"></i>处理时间</td>
					<td class="border_right_g border_bottom_g" width="520"><i
						class="icon-paste"></i>处理信息</td>
					<td class="border_bottom_g"><i class="icon-user"></i>处理地点</td>
				</tr>
				<c:forEach items="${result.dataSet.expRoutes }" var="expRoute" varStatus="status">
					<tr class="">
					<td class="bg_green c_fff" width="30">${status.index + 1 }</td>
					<td class="border_right_g border_bottom_g" width="180"><i
						class="icon-time"></i><fmt:formatDate  value="${expRoute.acceptTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td class="border_right_g border_bottom_g" width="520"><i
						class="icon-paste"></i>${expRoute.remark }</td>
					<td class="border_bottom_g"><i class="icon-user"></i>${expRoute.acceptAddress }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>	
	</c:if>
	<div class="publuc_box">
		<c:if test="${result.dataSet.orderDetails[0].goodsType != 'GT07' && result.dataSet.orderDetails[0].goodsType != 'GT10' && result.dataSet.orderDetails[0].goodsType != 'GT11' && result.dataSet.orderDetails[0].goodsType != 'GT12'}">
       <p class="public_dahang F14 c_black">订单信息</p>  
       <div class="grzx_box_one grzx_bugrt1 FL">
        <p>收货人信息</p>
        <table border="0" cellpadding="0" cellspacing="0">
         <tr>
           <td width="72">收&nbsp;&nbsp;货&nbsp;&nbsp;人：</td><td width="228">
           <c:choose>
           	<c:when test="${not empty result.dataSet.oLogistics.DContact }">${result.dataSet.oLogistics.DContact }</c:when>
           	<c:otherwise>${result.dataSet.networkinfo.phoneName }</c:otherwise>
           </c:choose>
           </td>
          </tr>
          <tr> 
           <td valign="top">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</td><td>
           ${result.dataSet.oLogistics.DProvince }省${result.dataSet.oLogistics.DCity }${result.dataSet.oLogistics.DCounty }${result.dataSet.oLogistics.DAddress }</td>
          </tr> 
          <tr>
           <td>联系电话：</td><td>${result.dataSet.oLogistics.DMobile }</td>      
          </tr>
         </table> 
       </div>  
<div class="grzx_box_one grzx_bugrt2 FL">
        <p>支付及配送方式</p>
        <table border="0" cellpadding="0" cellspacing="0">
         <tr>
           <td width="72">支付方式：</td><td width="228">
           <c:choose>
           		<c:when test="${result.dataSet.oPayInfo.payType == 'pay_on_delivery' }">货到付款</c:when>
           		<c:otherwise>在线支付</c:otherwise>
           </c:choose>
           </td>
          </tr>
           <tr>
           <td width="72">配送方式：</td><td width="228">
           <c:choose>
           		<c:when test="${result.dataSet.oLogistics.deliverType == 'DT00' }">顺丰</c:when>
           		<c:when test="${result.dataSet.oLogistics.deliverType == 'DT01' }">EMS</c:when>
           		<c:when test="${result.dataSet.oLogistics.deliverType == 'DT02' }">自提</c:when>
           </c:choose>
           </td>
          </tr>
          <tr> 
           <td valign="top">运&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费：</td><td>￥${result.dataSet.oMain.mailFee }元</td>
          </tr>
          <tr>
           <td>发货日期：</td><td>
           <c:choose>
           	<c:when test="${(result.dataSet.oLogistics.state == 'OL01' || result.dataSet.oLogistics.state == 'OL02')}">
           		<c:choose>
		        	<c:when test="${not empty result.dataSet.orderShowStates['OM07']}">
		        		    <fmt:formatDate  value="${result.dataSet.orderShowStates['OM07'].beginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
		        	</c:when>
		        	<c:when test="${not empty result.dataSet.orderShowStates['OM15']}">
		        		    <fmt:formatDate  value="${result.dataSet.orderShowStates['OM15'].beginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" />
		        	</c:when>
		        </c:choose>
           	</c:when>
           	<c:otherwise>未发货</c:otherwise>
           </c:choose>
           </td>      
          </tr>
         </table> 
       </div>  
<div class="grzx_box_one grzx_bugrt3 FL" >
        <p>入网信息 &nbsp;&nbsp;</p>
        <div style="width:500px; float:left">
	        <div class="c_59 F14">
	        <c:choose>
        		<c:when test="${result.dataSet.orderDetails[0].goodsType == 'GT15' }">开户副卡：</c:when>
        		<c:otherwise>开户卡号：</c:otherwise>
        	</c:choose>
	          <c:forEach items="${fn:split(result.dataSet.networkinfo.phoneNumber, '^') }" var="number">
			   		${number}&nbsp;
			  </c:forEach>
	        </div>
	        <div class="c_59 F14">开户姓名：${result.dataSet.networkinfo.phoneName }</div>
	        <div class="c_59 F14">身份证号：${result.dataSet.networkinfo.idCard }</div>
	        <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT14' || result.dataSet.orderDetails[0].goodsType == 'GT15' }">
	        	<c:if test="${not empty result.dataSet.networkinfo.unlimitCall }">
	        	<div class="c_59 F14">敞开打：
	        		<span class="innetshow" id="unlimitCall">
	        			<c:if test="${result.dataSet.networkinfo.unlimitCall == '0' }">不需要</c:if>
	        			<c:if test="${result.dataSet.networkinfo.unlimitCall == '2' }">2元/月拨打本地联通免费</c:if>
	        		</span>
	        	</div>
	        	</c:if>
	        </c:if>
	        <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT00' || result.dataSet.orderDetails[0].goodsType == 'GT17' }">
	       		<c:if test="${not empty result.dataSet.networkinfo.unlimitCall }">
	       		<div class="c_59 F14">敞开打：
	        		<span class="innetshow" id="unlimitCall">
	        			<c:if test="${result.dataSet.networkinfo.unlimitCall == '0' }">不需要</c:if>
	        			<c:if test="${result.dataSet.networkinfo.unlimitCall == '2' }">2元/月拨打本地联通免费</c:if>
	        		</span>
	        	</div>
	        	</c:if>
	        	<c:if test="${not empty result.dataSet.networkinfo.freeFlow }">
	        		<div class="c_59 F14">流量包：
		        		<span class="innetshow" id="freeFlow">
		        		<c:if test="${result.dataSet.networkinfo.freeFlow == '0' }">不需要</c:if>
		        		<c:if test="${result.dataSet.networkinfo.freeFlow == '3' }">2元/月包省内2GB</c:if>
		        		</span>
		        	</div>
	        	</c:if>
	       	</c:if>
	       	<!-- 
	        <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT03' }">
	        	<div class="c_59 F14">敞开打：
	        		<c:if test="${result.dataSet.networkinfo.unlimitCall == '0' }">不需要</c:if>
	        		<c:if test="${result.dataSet.networkinfo.unlimitCall == '1' }">1元/月拨打本地联通免费</c:if>
	        	</div>
	        	<div class="c_59 F14">闲时流量：
	        		<c:if test="${result.dataSet.networkinfo.freeFlow == '0' }">不需要</c:if>
	        		<c:if test="${result.dataSet.networkinfo.freeFlow == '1' }">1元/月包省内1GB</c:if>
	        		<c:if test="${result.dataSet.networkinfo.freeFlow == '2' }">2元/月包省内3GB</c:if>
	        	</div>
	        </c:if>
	         -->
	        <c:if test="${not empty result.dataSet.networkinfo.phoneNumber}">
        	<c:forEach items="${fn:split(result.dataSet.networkinfo.phoneNumber, '^') }" var="number" varStatus="numStatus">
		   		<div class="c_59 F14">
		   		${number}卡板：
		   		<c:forEach items="${fn:split(result.dataSet.networkinfo.simCardType, '^') }" var="simCardType" varStatus="simCardStatus">
		   			<c:if test="${numStatus.index == simCardStatus.index }">
		   				<c:if test="${simCardType == '0' }">普通卡</c:if>
		   				<c:if test="${simCardType == '1' }">微卡</c:if>
		   				<c:if test="${simCardType == '2' }">Nano卡</c:if>
		   			</c:if>
		   		</c:forEach>
		   		</div>
		   </c:forEach>
		   </c:if>
		   <c:if test="${not empty result.dataSet.networkinfo.effectTime }">
	        <div class="c_59 F14">生效时间：
	        	<c:if test="${result.dataSet.networkinfo.effectTime == '0' }">立即生效</c:if>
    			<c:if test="${result.dataSet.networkinfo.effectTime == '1' }">半月生效</c:if>
    			<c:if test="${result.dataSet.networkinfo.effectTime == '2' }">次月生效</c:if>
    			<c:if test="${result.dataSet.networkinfo.effectTime == '3' }">插卡生效</c:if>
	        </div>
	        </c:if>
	        <!-- 合约惠机 -->
        <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT17' }">
        	<div class="c_59 F14">返还模式：
        		<span class="innetshow" id="returnMode">
        			<c:if test="${result.dataSet.networkinfo.returnMode == '00' }">一次性返还</c:if>
        			<c:if test="${result.dataSet.networkinfo.returnMode == '01' }">分月返还</c:if>
        		</span>
        	</div>
        	<c:if test="${not empty result.dataSet.networkinfo.contractMachineImei }">
        		<div class="c_59 F14">手机串号：${result.dataSet.networkinfo.contractMachineImei }
        		</div>
        	</c:if>
        </c:if>
	        
	        <c:if test="${not empty result.dataSet.networkinfo.mainNumber }">
	        	<div class="c_59 F14">主号码：
	    			${result.dataSet.networkinfo.mainNumber }
		        </div>
	        </c:if>
	        <c:if test="${not empty result.dataSet.networkinfo.newBandNumber }">
	        	<div class="c_59 F14">新开宽带号：
	    			${result.dataSet.networkinfo.newBandNumber }
		        </div>
	        </c:if>
	        <c:if test="${not empty fn:trim(result.dataSet.networkinfo.oldBandNumber) }">
	        <div class="c_59 F14">已有宽带号：
    			${result.dataSet.networkinfo.oldBandNumber }
	        </div>
	        </c:if>
	        <c:if test="${not empty result.dataSet.networkinfo.fuseNumber }">
	        <div class="c_59 F14">融合号：
    			${result.dataSet.networkinfo.fuseNumber }
	        </div>
	        </c:if>
	        <c:if test="${not empty result.dataSet.networkinfo.fixNumber}">
	        <div class="c_59 F14">固话：
    			${result.dataSet.networkinfo.fixNumber }
	        </div>
	        </c:if>
	        <c:if test="${not empty result.dataSet.networkinfo.oldPhoneNumber && result.dataSet.orderDetails[0].goodsType != 'GT15' }">
	        <div class="c_59 F14">已有手机号：
    			<c:forEach items="${fn:split(result.dataSet.networkinfo.oldPhoneNumber, '^') }" var="number">
			   		${number}&nbsp;
			  </c:forEach>
	        </div>
	        </c:if>
	        <c:if test="${not empty fn:trim(result.dataSet.networkinfo.deviceType) }">
	        <div class="c_59 F14">设备类型：
    			<c:if test="${result.dataSet.networkinfo.deviceType == '00' }">HG8342R（GPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '01' }">HG8321R（GPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '02' }">HG8321R（EPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '03' }">HG8321R（EPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '04' }">网关型I-120E-Q（GPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '05' }">网关型HG261G（GPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '06' }">ZTE-F407（EPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == '07' }">ZTE-F607（GPON）</c:if>
    			<c:if test="${result.dataSet.networkinfo.deviceType == 'other' }">其他(请查看备注)</c:if>
	        </div>
	        </c:if>
	        <c:if test="${not empty fn:trim(result.dataSet.networkinfo.lanAddress) }">
	        <div class="c_59 F14">安装地址：${result.dataSet.networkinfo.lanAddress }
	        </div>
	        </c:if>
	         <c:if test="${not empty fn:trim(result.dataSet.networkinfo.expireProcess) }">
	        <div class="c_59 F14">到期处理：
	        	<c:if test="${result.dataSet.networkinfo.expireProcess == '00' }">停机</c:if>
        		<c:if test="${result.dataSet.networkinfo.expireProcess == '01' }">转包月资费</c:if>
	        </div>
	        </c:if>
        </div>
        <div class="grzx_dd_zpyl">
        <c:if test="${not empty result.dataSet.networkinfo.cardPhotoA }"><img src="${result.dataSet.showCardPhotoA }" class="previewImg" width="120" height="90"></c:if>
        <c:if test="${not empty result.dataSet.networkinfo.cardPhotoB }"><img src="${result.dataSet.showCardPhotoB }" class="previewImg" width="120" height="90"></c:if>
        <c:if test="${not empty result.dataSet.networkinfo.cardPhotoHand }"><img src="${result.dataSet.showCardPhotoHand }" class="previewImg" width="120" height="90"></c:if>
        </div>
       </div>             
       </c:if>
       <div class="clear"></div> 
       <table border="0" cellpadding="0" cellspacing="0" class="grzx_sp_list">
        <tr class="grzx_sp_list_th">
         <td width="10%" class="grzx_border_right"><b>商品类别</b></td>
         <td width="10%" class="grzx_border_right"><b>商品图片</b></td>
         <td width="60%"  class="grzx_border_right"><b>商品名称</b></td>
         <td width="10%" class="grzx_border_right"><b>商品价格</b></td>
         <td width="10%" class="grzx_border_right"><b>商品数量</b></td>
        </tr>
        <tr class="grzx_sp_list_td">
         <td class="grzx_border_right">
         	<c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT00' || result.dataSet.orderDetails[0].goodsType == 'GT03' || result.dataSet.orderDetails[0].goodsType == 'GT04' || result.dataSet.orderDetails[0].goodsType == 'GT08' || result.dataSet.orderDetails[0].goodsType == 'GT09' || result.dataSet.orderDetails[0].goodsType == 'GT13' || result.dataSet.orderDetails[0].goodsType == 'GT14' || result.dataSet.orderDetails[0].goodsType == 'GT15'
         	 || result.dataSet.orderDetails[0].goodsType == 'GT17' }">4G套餐</c:if>
          <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT05' }">宽带</c:if><c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT06' }">智慧沃家</c:if><c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT07' || result.dataSet.orderDetails[0].goodsType == 'GT10' || result.dataSet.orderDetails[0].goodsType == 'GT11' || result.dataSet.orderDetails[0].goodsType == 'GT12' }">老用户续约</c:if>
         </td>
         <td class="grzx_border_right"><div style="height:60px; overflow:hidden; text-align:center;"><img src="${imgPath }${result.dataSet.orderDetails[0].imageUrl}" width="60" height="60"></div></td>
         <td class="grzx_border_right"><a target="_blank" class="c_bule" href="${portalPath }${result.dataSet.orderDetails[0].detailUrl}">${result.dataSet.orderDetails[0].goodsName}</a><br>
	<span class="F12 c_qhuise">
	<c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT07' || result.dataSet.orderDetails[0].goodsType == 'GT10' || result.dataSet.orderDetails[0].goodsType == 'GT11' || result.dataSet.orderDetails[0].goodsType == 'GT12'}">
	${result.dataSet.networkinfo.oldPhoneNumber }(${result.dataSet.area.name })
	</c:if>
	<c:if test="${not empty result.dataSet.networkinfo.phoneNumber }">
		<c:forEach items="${fn:split(result.dataSet.networkinfo.phoneNumber, '^') }" var="number" varStatus="numStatus">
		   		${number}(${numcityid })
		   		&nbsp;
		</c:forEach>
		</c:if>
		<c:if test="${lan_type == '0' }">接入方式:ADSL&nbsp;</c:if>
		<c:if test="${lan_type == '1' }">接入方式:LAN&nbsp;</c:if>
		<c:if test="${lan_type == '2' }">接入方式:FTTH&nbsp;</c:if>
		
		<c:if test="${speed_type == '0' }">宽带速率:4M&nbsp;</c:if>
		<c:if test="${speed_type == '1' }">宽带速率:10M&nbsp;</c:if>
		<c:if test="${speed_type == '2' }">宽带速率:20M&nbsp;</c:if>
		
		<c:if test="${not empty contract_time}">合约期:${contract_time}个月&nbsp;</c:if>
		<c:if test="${not empty share_flow}">共享国内手机流量:${share_flow }&nbsp;</c:if>
		<c:if test="${not empty share_phone}">共享国内通话时长:${share_phone }分钟/月&nbsp;</c:if>
		<c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT06' }">
			<c:if test="${not empty servpack}">联通电视:${servpack }&nbsp;</c:if>
			<c:if test="${not empty serv_cost}">联通电视费用:${serv_cost }元&nbsp;</c:if>
		</c:if>
	</span>
	</td>
         <td class="grzx_border_right"><span class="c_red">￥${result.dataSet.orderDetails[0].goodsPrice }</span>
          <c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT05' }">
         		<br><span class="F12 c_qhuise">
         		包年价格${price }元+初装费${start_cost }元+设备费${device_price }元
         		</span>
         	</c:if>
         	<c:if test="${result.dataSet.orderDetails[0].goodsType == 'GT06' }">
         		<br><span class="F12 c_qhuise">
         		预存款${price }元+初装费${start_cost }元+光猫设备费${device_price }元+联通电视费${serv_cost }元
         		</span>
         	</c:if>
         </td>
         <td class="grzx_border_right">1</td>
        </tr> 
       </table>
       <div class="grzx_sp_Zx">
       <div class="FL grzx_widther">
       <h2 class="F16 c_shuise">订单备注</h2>
       <p class="F14 c_shuise">${result.dataSet.oMain.remark }</p>
       </div>
       <div class="gezx_jg FR">
        <table>
         <tr>
          <td class="t_l c_shuise">总商品金额：</td>
          <td class="t_r c_red">￥${result.dataSet.oMain.costs }</td>
         </tr>
         <tr>
          <td class="t_l c_shuise">增加预存款：</td>
          <td class="t_r c_red">￥${result.dataSet.oMain.prestoreFee }</td>
         </tr>
         <tr>
          <td class="t_l c_shuise">- 优惠：</td>
          <td class="t_r c_red">￥0.00</td>
         </tr>    
         <tr>
          <td class="t_l c_shuise">+运费：</td>
          <td class="t_r c_red">￥${result.dataSet.oMain.mailFee }</td>
         </tr>  
         <tr>
          <td style="padding-top:5px; border-top:1px solid #999;" colspan="2"><span class="F16">应付总额：<b class="c_red">￥${result.dataSet.oMain.paid }</b></span></td>
         </tr>           
         </table>       
       </div>
       <div class="clear"></div>
       <div class="tkyy">
       	<h2 class="F16 c_shuise">退款原因</h2>
        <p class="F14 c_shuise">${result.dataSet.toReturns.description }</p>
       </div>
       <div class="clear"></div>
       </div>
      </div>
      </div>
      <!--我的订单--> 
    </div>
  </div>
  <div class="clear"></div>
    <table width="100%" style="padding-bottom:20px;">
      	<tr>
      		<td align="center">
      			<a href="javascript:ReturnOrderApprove.approve('${result.dataSet.oMain.id }','yes');" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">审核通过</a>
        	 	<a href="javascript:ReturnOrderApprove.approve('${result.dataSet.oMain.id }','no');"  class="easyui-linkbutton" data-options="iconCls:'icon-no'">审核不通过</a>
      		</td>
      	</tr>
      	</table>
  </div>
</section>
<div id="ReturnOrderApprove_approveFail" style="display:none;" style="width:600px;height:600px;"   
        data-options="resizable:false,modal:true,toolbar:'#ReturnOrderApprove_dialogBtn',title:'审批信息'">   
</div>
<div id="ReturnOrderApprove_dialogBtn" style="display:none;">
	<a href="javascript:ReturnOrderApprove_approveFail_save();" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true">提交</a>
	<a href="javascript:ReturnOrderApprove_approveFail_cancel();" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">取消</a>
</div>

<script type="text/javascript" src="${contextPath }/resources/skin/manager/js/order/getReturnsOrder.js"> </script>