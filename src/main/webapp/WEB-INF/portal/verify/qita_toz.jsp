<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>山东联通自助实名登记系统</title>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/initjs.jsp"%>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="联通网盟销售平台" />
<meta name="keywords" content="联通网盟销售平台_能人之家" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/public.css" />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/nrkj.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/index.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/portal/css/zty.css"  />
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css"  href="${contextPath}/resources/skin/common/css/font-awesome.min_new.css">

<script type="text/javascript" src="${contextPath}/resources/skin/common/js/jquery.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/qtip/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/Addressall.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/AreaSelect.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/SetAreaSelect.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ClientRequest.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/Validform_v5.3.2.js"></script>
<script src="${contextPath}/resources/skin/common/js/ValidForm/js/FormCommit.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/ZeroClipboard/ZeroClipboard.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/TimeDown.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/common/js/AlertConfirm.js"></script>
<script type="text/javascript" src="${contextPath}/resources/assets/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${contextPath}/resources/skin/portal/js/qitaverify.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var bean = toQueryParams(window.location.href);
	$("#SERIAL_NUMBER").html(bean.serialnumber);
});
</script>
</head>
<style>
.qt_oger{ height:90px; overflow:hidden;
filter: progid: DXImageTransform.Microsoft.gradient(startColorstr = '#d9d9d9', endColorstr = '#f0f0f0');
/*INNER ELEMENTS MUST NOT BREAK THIS ELEMENTS BOUNDARIES*/
/*Element must have a height (not auto)*/
/*All filters must be placed together*/
-ms-filter: "progid: DXImageTransform.Microsoft.gradient(startColorstr = '#d9d9d9', endColorstr = '#f0f0f0')";
/*Element must have a height (not auto)*/
/*All filters must be placed together*/
background-image: -moz-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -ms-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -o-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: -webkit-gradient(linear, center top, center bottom, from(#d9d9d9), to(#f0f0f0));
background-image: -webkit-linear-gradient(top, #d9d9d9, #f0f0f0);
background-image: linear-gradient(top, #d9d9d9, #f0f0f0);
/*--IE9 DOES NOT SUPPORT GRADIENT BACKGROUNDS--*/
border-bottom:1px solid #CCC;
}
.qt_ergrer{ height:70px; margin:10px;}
.qi_nglere{ padding-right:10px; border-right:1px solid #c1c1c1;}
img{vertical-align:middle;}
.qtyongyici{ height:70px; padding-left:10px; border-left:1px solid rgba(255,255,255,0.6);font-family:'微软雅黑';}
.qtwogerner{ width:300px; height:24px; background:#bfbfbf; border:1px solid #fff; float:left;font-family:'微软雅黑'; text-align:center; color:#fff; box-sizing:border-box}
.qtwogerner2{ background:#f95118}
.obertabler{ font-family:'微软雅黑';font-size:16px; color:#666; line-height:38px; width:450px; margin:0 auto;}
.obobere{ line-height:30px; border:1px solid #a0a0a0; padding:0 5px; width:260px; line-height:30px; color:#333; font-size:14px}
.yanzhengma_a{ font-size:12px; color:#fff; display:block; width:90px; height:28px; line-height:28px; margin-top:2px; text-align:center;  background:#73cc34}
</style>
<body>
<section>
  <div class="index_head">
    <div class="indext_daohang qt_oger">
     <div class="centour ">
      <div class="qt_ergrer">
       
       <div class="FL qi_nglere"><img src="${contextPath}/resources/skin/portal/images/verify/liantonglogo.png" width="118" height="70"></div>
       
       <div class="FL qtyongyici"><p style="font-size:26px;color: #505050;font-weight: bold; text-shadow:1px 1px 1px #fff; line-height:40px;">山东联通自助实名登记系统</p><p style="font-size:18px; line-height:20px; color:#CA2A2A;font-weight:600;">山东分公司</p></div>
       <div class="clear"></div>
       </div>
      <!--logo--> 
     </div>
  </div>
  <!--head-->
</section>
<div class="clear"></div>
<section>
<div style="background:#fafafa; padding:20px;">
<div class="centour">
<div style="padding:0 0 20px 0">
 <div class="qtwogerner">1.身份验证</div>
 <div class="qtwogerner">2.填写资料</div>
 <div class="qtwogerner">3.上传证件</div>
 <div class="qtwogerner qtwogerner2">4.提交完成</div>
 <div class="clear"></div>
</div>
</div>

 <div class="centour" style="background:#fff; border:1px solid #CCC; box-shadow:0 0 2px rgba(0,0,0,0.2)">

 <div style="font-family:'微软雅黑'; font-size:18px; text-align:center; margin:20px 0; ">实名结果查询</div>
 <div style="width:800px; border-top:1px solid #ccc; margin:0 auto">
  
  <div style=" font-size:16px; line-height:40px;  color:#666; width:600px; margin:30px auto">
  <p>手机号码：<b class="c_orger" id="SERIAL_NUMBER"></b></p>
  <p>温馨提示：尊敬的用户，你的实名登记正在处理中，最新结果将通过手机短信通知您，谢谢您的配合！</p>
  </div>
  
 </div>
<!--  <div style="width:160px; margin:50px auto"><a class="button_red_gwc" href="javascript:window.close();">退出</a></div> -->
 </div>
</div> 
</section>

<section>
  <div class="long_foot">
    <div class="centour long_foot_bg" style="background:#555">
  中国联合网络通信有限公司机构代码证<br>
中国联合网络通信有限公司山东省分公司 版权所有 鲁ICP备08105755号<br>
中华人民共和国增值电信业务经营许可证 经营许可证编号：A2.B1.B2-20090003
  </div>
 </div> 
</section>
</body>
</html>