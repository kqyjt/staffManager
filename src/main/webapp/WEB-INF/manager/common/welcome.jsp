<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>
<!-- 存储登录人的权限 -->
<input type="hidden" value="${result.dataSet.WaitCust}" id = "welcomepage-WaitCust"/>
<input type="hidden" value="${result.dataSet.WaitReturn}" id = "welcomepage-WaitReturn"/>
<input type="hidden" value="${result.dataSet.WaitMainOrder}" id = "welcomepage-WaitMainOrder"/>
<input type="hidden" value="${result.dataSet.WaitOpenOrder}" id = "welcomepage-WaitOpenOrder"/>
<input type="hidden" value="${result.dataSet.WaitArchiveOrder}" id = "welcomepage-WaitArchiveOrder"/>
<input type="hidden" value="${result.dataSet.waitDealOrder}" id = "welcomepage-waitDealOrder"/>
<input type="hidden" value="${result.dataSet.preLoadApply}" id = "welcomepage-preLoadApply"/>
<!--地市工作台1  begin-->
<div class="work_cont" id="welcomepage-city1">
  <div  class="work_title">地市工作台信息</div>
   <table width="100%" border="0" cellspacing="0">
  <tr>
    <th>待审核能人</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchCustomer();">${result.dataSet.CityWaitCustCount}</a></span></td>
    </tr>
  <tr>
    <th>待审核订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchOrder();">${result.dataSet.WaitCityOrderCount}</a></span></td>
    </tr>
  <tr>
    <th>待开户订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchOpenOrder();">${result.dataSet.WaitCityOpenOrderCount}</a></span></td>
  </tr>
  <tr>
    <th>待归档订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchArchiveOrder();">${result.dataSet.WaitCityArchiveOrderCount}</a></span></td>
  </tr>
  <tr>
    <th>待处理问题订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchWaitOrder();">${result.dataSet.QueCityCount}</a></span></td>
  </tr>
    <tr>
    <th>预装申请</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchWaitDemand();">${result.dataSet.demandCount}</a></span></td>
  </tr>
  </table>
</div>
<!--地市工作台1  end-->

<!--地市工作台2  begin-->
<div class="work_cont" id="welcomepage-city2">
  <div  class="work_title">地市工作台信息</div>
   <table width="100%" border="0" cellspacing="0">
  <tr>
    <th width="119">待审核能人</th>
    <%-- <td width="102">总计:<span><a href="#" onclick="WelcomePage.SearchCustomer();">${result.dataSet.WaitCustCount}</a></span></td> --%>
    <td width="102">总计:<span><a  href="javascript:void(0)" >${result.dataSet.WaitCustCount}</a></span></td>
    <td width="1099">
    	<c:if test="${result.dataSet.WaitCustCount != 0}">其中 </c:if>
    
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitCustPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitCustPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitCustPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitCustPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitCustPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitCustPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitCustPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitCustPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitCustPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitCustPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitCustPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitCustPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitCustPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitCustPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitCustPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitCustPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitCustPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
    </tr>
  
  <tr>
    <th>待审核订单</th>
    <%-- <td>总计:<span><a href="#" onclick="WelcomePage.SearchOrder();">${result.dataSet.WaitCityOrderCount}</a></span></td> --%>
    <td>总计:<span><a  href="javascript:void(0)">${result.dataSet.WaitCityOrderCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.WaitCityOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitCityOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitCityOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitCityOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitCityOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitCityOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitCityOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitCityOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitCityOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitCityOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitCityOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitCityOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitCityOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitCityOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitCityOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitCityOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitCityOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitCityOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待开户订单</th>
    <%-- <td>总计:<span><a href="#" onclick="WelcomePage.SearchOpenOrder();">${result.dataSet.WaitCityOpenOrderCount}</a></span></td> --%>
    <td>总计:<span><a  href="javascript:void(0)">${result.dataSet.WaitCityOpenOrderCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.WaitCityOpenOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityOpenOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitCityOpenOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待归档订单</th>
   <%--  <td>总计:<span><a href="#" onclick="WelcomePage.SearchArchiveOrder();">${result.dataSet.WaitCityArchiveOrderCount}</a></span></td> --%>
    <td>总计:<span><a  href="javascript:void(0)">${result.dataSet.WaitCityArchiveOrderCount}</a></span></td>
    <td> 
    	<c:if test="${result.dataSet.WaitCityArchiveOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCityArchiveOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitCityArchiveOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待处理问题订单</th>
    <%-- <td>总计:<span><a href="#" onclick="WelcomePage.SearchWaitOrder();">${result.dataSet.QueCityCount}</a></span></td> --%>
    <td>总计:<span><a  href="javascript:void(0)">${result.dataSet.QueCityCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.QueCityCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.QueCityPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.QueCityPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.QueCityPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.QueCityPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.QueCityPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.QueCityPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.QueCityPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.QueCityPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.QueCityPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.QueCityPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.QueCityPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.QueCityPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.QueCityPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.QueCityPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.QueCityPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.QueCityPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.QueCityPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.QueCityPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>预装申请</th>
    <%-- <td>总计:<span><a href="#" onclick="WelcomePage.SearchWaitDemand();">${result.dataSet.demandCount}</a></span></td> --%>
    <td>总计:<span><a  href="javascript:void(0)">${result.dataSet.demandCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.demandCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalJiNan != 0}">济南<span>${result.dataSet.perCityDemandCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.perCityDemandCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.perCityDemandCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.perCityDemandCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.perCityDemandCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.perCityDemandCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.perCityDemandCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalDongYing != 0}">东营<span>${result.dataSet.perCityDemandCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.perCityDemandCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.perCityDemandCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.perCityDemandCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.perCityDemandCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.perCityDemandCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.perCityDemandCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.perCityDemandCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.perCityDemandCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.perCityDemandCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.perCityDemandCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  </table>
</div>
<!--地市工作台2 end-->

<!--省分工作台  begin-->
<div class="work_cont" id="welcomepage-province">
  <div  class="work_title">省分工作台信息</div>
   <table width="100%" border="0" cellspacing="0">
  <tr>
    <th width="119">待审核能人</th>
    <td width="102">总计:<span><a href="#" onclick="WelcomePage.SearchCustomer();">${result.dataSet.WaitCustCount}</a></span></td>
    <td width="1099">
    	<c:if test="${result.dataSet.WaitCustCount != 0}">其中 </c:if>
    
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitCustPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitCustPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitCustPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitCustPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitCustPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitCustPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitCustPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitCustPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitCustPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitCustPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitCustPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitCustPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitCustPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitCustPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitCustPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitCustPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitCustPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitCustPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
    </tr>
  <tr>
    <th>待审核退款</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchReturnOrder();">${result.dataSet.WaitReturnCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.WaitReturnCount != 0}">其中 </c:if>
   
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitReturnPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitReturnPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitReturnPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitReturnPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitReturnPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitReturnPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitReturnPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitReturnPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitReturnPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitReturnPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitReturnPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitReturnPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitReturnPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitReturnPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitReturnPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitReturnPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitReturnPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitReturnPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
    </tr>
  <tr>
    <th>待审核订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchOrder();">${result.dataSet.WaitMainOrderCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.WaitMainOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitMainOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitMainOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitMainOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitMainOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitMainOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitMainOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitMainOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitMainOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitMainOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitMainOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitMainOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitMainOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitMainOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitMainOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitMainOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitMainOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitMainOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitMainOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待开户订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchOpenOrder();">${result.dataSet.WaitOpenOrderCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.WaitOpenOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitOpenOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitOpenOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitOpenOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitOpenOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitOpenOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitOpenOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitOpenOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitOpenOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitOpenOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitOpenOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitOpenOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitOpenOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitOpenOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitOpenOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitOpenOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitOpenOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitOpenOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitOpenOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待归档订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchArchiveOrder();">${result.dataSet.WaitArchiveOrderCount}</a></span></td>
    <td> 
    	<c:if test="${result.dataSet.WaitArchiveOrderCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.WaitArchiveOrderPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.WaitArchiveOrderPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  <tr>
    <th>待处理问题订单</th>
    <td>总计:<span><a href="#" onclick="WelcomePage.SearchWaitOrder();">${result.dataSet.QueMainCount}</a></span></td>
    <td>
    	<c:if test="${result.dataSet.QueMainCount != 0}">其中 </c:if>
    	
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalJiNan != 0}">济南<span>${result.dataSet.QueMainPerCount[0].totalJiNan}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalQingDao != 0}">青岛<span>${result.dataSet.QueMainPerCount[0].totalQingDao}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalZiBo != 0}">淄博<span>${result.dataSet.QueMainPerCount[0].totalZiBo}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalBinZhou != 0}">滨州<span>${result.dataSet.QueMainPerCount[0].totalBinZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalLinyi != 0}">临沂<span>${result.dataSet.QueMainPerCount[0].totalLinyi}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalRiZhao != 0}">日照<span>${result.dataSet.QueMainPerCount[0].totalRiZhao}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalWeiFang != 0}">潍坊<span>${result.dataSet.QueMainPerCount[0].totalWeiFang}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalDongYing != 0}">东营<span>${result.dataSet.QueMainPerCount[0].totalDongYing}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalZaoZhuang != 0}">枣庄<span>${result.dataSet.QueMainPerCount[0].totalZaoZhuang}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalJiNing != 0}">济宁<span>${result.dataSet.QueMainPerCount[0].totalJiNing}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalHeZe != 0}">菏泽<span>${result.dataSet.QueMainPerCount[0].totalHeZe}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalLaiWu != 0}">莱芜<span>${result.dataSet.QueMainPerCount[0].totalLaiWu}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalYanTai != 0}">烟台<span>${result.dataSet.QueMainPerCount[0].totalYanTai}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalTaiAn != 0}">泰安<span>${result.dataSet.QueMainPerCount[0].totalTaiAn}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalDeZhou != 0}">德州<span>${result.dataSet.QueMainPerCount[0].totalDeZhou}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalLiaoCheng != 0}">聊城<span>${result.dataSet.QueMainPerCount[0].totalLiaoCheng}</span>，</c:if>
    	<c:if test="${result.dataSet.QueMainPerCount[0].totalWeiHai != 0}">威海<span>${result.dataSet.QueMainPerCount[0].totalWeiHai}</span>，</c:if>
    	
    </td>
  </tr>
  </table>
</div>
<!--省分工作台 end-->

<script type="text/javascript">

		var s=${result.dataSet.strAreaCode};
		
		if(s=="999"){
			document.getElementById("welcomepage-province").style.display="none";
			document.getElementById("welcomepage-city2").style.display="none";
			document.getElementById("welcomepage-city1").style.display="none";
		} else if(s!="000"){
			//document.getElementById("city").style.display="none";
			document.getElementById("welcomepage-province").style.display="none";
			document.getElementById("welcomepage-city2").style.display="none";
		} else {
			//document.getElementById("province").style.display="none";
			document.getElementById("welcomepage-city1").style.display="none";
		}
		
var WelcomePage= {};
//跳到订单审核页面
WelcomePage.SearchOrder=function(){
	var WaitMainOrder = $('#welcomepage-WaitMainOrder').val();
	if(WaitMainOrder == 'yes'){
		OpenTab('center_tabs','订单审核',managerPath+'/manager/order/OrderMgr.htm?m=query&f=getWaitApproveOrders','icon-munich-collaboration');
	}else{
		 $.messager.alert("操作提示",'您没有此权限!');
	}
};
//跳到能人审核页面
WelcomePage.SearchCustomer=function(){
	var waitCust = $('#welcomepage-WaitCust').val();
	if(waitCust == 'yes'){
		OpenTab('center_tabs','能人审核',managerPath+'/manager/customer/CustomerMgr.htm?m=execute&f=showCustomerPage','icon-munich-collaboration');
	}else{
		 $.messager.alert("操作提示",'您没有此权限!');
	}
};
//跳到回访开户发货页面
WelcomePage.SearchOpenOrder=function(){
	var WaitOpenOrder = $('#welcomepage-WaitOpenOrder').val();
	if(WaitOpenOrder == 'yes'){
		OpenTab('center_tabs','回访开户发货',managerPath+'/manager/order/OrderMgr.htm?m=query&f=getWaitOpenAccountOrders','icon-munich-collaboration');
	}else{
		$.messager.alert("操作提示",'您没有此权限!');
	}
	
};
//跳到订单归档页面
WelcomePage.SearchArchiveOrder=function(){
	var WaitArchiveOrder = $('#welcomepage-WaitArchiveOrder').val();
	if(WaitArchiveOrder == 'yes'){
		OpenTab('center_tabs','订单归档',managerPath+'/manager/order/OrderMgr.htm?m=query&f=getWaitArchiveOrders','icon-munich-collaboration');
	}else{
		$.messager.alert("操作提示",'您没有此权限!');
	}
	
};
//跳到待审核退款页面
WelcomePage.SearchReturnOrder=function(){
	var WaitReturn = $('#welcomepage-WaitReturn').val();
	if(WaitReturn == 'yes'){
		OpenTab('center_tabs','待审核退款',managerPath+'/manager/order/OrderMgr.htm?m=query&f=getWaitApproveReturnOrders','icon-munich-collaboration');
	}else{
		$.messager.alert("操作提示",'您没有此权限!');
	}
};
//跳到待处理订单页面
WelcomePage.SearchWaitOrder=function(){
	var waitDealOrder = $('#welcomepage-waitDealOrder').val();
	if(waitDealOrder == 'yes'){
		OpenTab('center_tabs','待处理订单',managerPath+'/manager/controller/TOMainMgr.htm?m=execute&f=cityQuery','icon-munich-collaboration');
	}else{
		$.messager.alert("操作提示",'您没有此权限!');
	}
};
//跳到待处理预装申请
WelcomePage.SearchWaitDemand=function(){
	var preLoadApply = $('#welcomepage-preLoadApply').val();
	if(preLoadApply == 'yes'){
		OpenTab('center_tabs','预装申请',managerPath+'/manager/order/DemandMgr.htm?m=query&f=getDemands','icon-munich-collaboration');
	}else{
		$.messager.alert("操作提示",'您没有此权限!');
	}
};

</script>

