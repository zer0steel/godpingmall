<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><jsp:include page="/WEB-INF/views/head.jsp"></jsp:include></head>
<body>
<div>
	<a href="${pageContext.request.contextPath}/main">쇼핑몰 메인 페이지</a>
	<a href="${pageContext.request.contextPath}/admin/option">옵션 등록</a>
	<a href="${pageContext.request.contextPath}/admin/category">카테고리 등록</a>
	<a href="${pageContext.request.contextPath}/admin/goods/insert">상품 등록</a>
	<a href="${pageContext.request.contextPath}/admin/goods">상품 목록</a>
</div>
<div>
    <jsp:include page="/WEB-INF/views${viewPage }"/>
</div>
</body>
</html>
