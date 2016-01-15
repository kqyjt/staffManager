<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../common/taglib.jsp"%>


<ul>
<c:forEach var="module" items="${result.dataSet.modulesList}" varStatus="status">
	 <a href="javascript:void(0);"><li class="level_02 listmenu" taburl="${managerPath }${module.url}" iconcls="${module.icon }">${module.name }</li></a>
</c:forEach>
</ul>

