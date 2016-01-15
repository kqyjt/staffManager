﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>山东联通网盟平台</title>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/initjs.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<meta name="description" content="山东联通网盟平台" />
<meta name="keywords" content="山东联通网盟平台_能人之家" />

<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/public.css" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/nrkj.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/index.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min_new.css">

</head>
<body style="width:100%; height:100%; background:#fffbbc;">
<section>
 <div class="indext404">
  <div style="margin:190px 0 0 300px;">
  <p>您要访问的页面可能已被删除或者暂时不可用。</p>
  <p>原因：<span class="c_shuise">${result.errorMsg }</span></p>
  <p>点击一下链接继续浏览网站</p>
  <p><a href="javascript:history.go(-1);"><i class="fa fa-angle-double-right"></i>&nbsp;返回上一级</a></p>
  <p><a href="${portalPath }" target="_self"><i class="fa fa-angle-double-right"></i>&nbsp;返回网站首页</a></p>
  </div>
 </div>
</section>

</body>
</html>