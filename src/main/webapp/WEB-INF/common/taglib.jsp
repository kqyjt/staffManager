<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"/>

<%-- ${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath} --%>

<!-- server -->
<!-- <c:set var="cartPath" value="http://www.51186.com.cn/cart" />
<c:set var="centerPath" value="http://www.51186.com.cn/center" />
<c:set var="orderPath" value="http://www.51186.com.cn/order" />
<c:set var="acePath" value="http://www.51186.com.cn/ace" />
<c:set var="managerPath" value="http://www.51186.com.cn/manager" />
<c:set var="portalPath" value="http://www.51186.com.cn/portal" />
<c:set var="paymentPath" value="http://www.51186.com.cn/payment" />
<c:set var="expressPath" value="http://www.51186.com.cn/express" />
<c:set var="directPath" value="http://www.51186.com.cn/portal" /> 
<c:set var="imgPath" value="http://www.51186.com.cn/image" />
<c:set var="filePath" value="http://www.51186.com.cn" />-->

<!-- local -->
<c:set var="cartPath" value="${contextPath}" />
<c:set var="centerPath" value="${contextPath}" />
<c:set var="orderPath" value="${contextPath}" />
<c:set var="acePath" value="${contextPath}" />
<c:set var="managerPath" value="${contextPath}" />
<c:set var="portalPath" value="${contextPath}" />
<c:set var="paymentPath" value="${contextPath}" />
<c:set var="expressPath" value="${contextPath}" />
<c:set var="directPath" value="http://localhost:8080/staffManager" />
<c:set var="imgPath" value="http://localhost:8080/staffManager" />
<c:set var="filePath" value="http://134.32.32.144:9004" />

<!-- 确定目标url -->
<c:choose>
	<c:when test="${fn:contains(result.requestURI, 'Login.htm')||fn:substringAfter(result.requestURI, contextPath)=='/'}">
		<c:set var="url"  value=""/>
	</c:when>
	<c:otherwise>
		<c:set var="url"  value="${fn:substringAfter(result.requestURI, contextPath)}"/>	
 	</c:otherwise>	 
</c:choose>
