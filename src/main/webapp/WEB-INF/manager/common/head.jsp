<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<div class="top">
 <ul>
  <a href="javascript:void(0);"><li class="logo"></li></a>
  <c:forEach var="menu" items="${result.dataSet.menuList}" varStatus="status">
  	<a href="javascript:void(0);"><li class='${menu.lvlId } topmenu <c:if test="${status.index==0 }">bor-over</c:if>' menugroupid="${menu.id }" iconcls="${menu.icon }">${menu.name }</li></a>
  </c:forEach>
  <a href="javascript:logout();"><li class="close">退出</li></a>
 </ul>
</div>
