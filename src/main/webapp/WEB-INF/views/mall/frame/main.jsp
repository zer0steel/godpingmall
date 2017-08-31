<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head><jsp:include page="/WEB-INF/views/head.jsp"></jsp:include></head>
<body>
<sec:authorize access="isAnonymous()">
	<a href="${pageContext.request.contextPath}/login">로그인</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button>로그아웃</button>
	</form>
</sec:authorize>
<div>
	<a href="${pageContext.request.contextPath}/main">메인 페이지</a>
	<a href="${pageContext.request.contextPath}/my/info">개인 정보</a>
	<a href="${pageContext.request.contextPath}/admin/main">관리자</a>
</div>
<div>
    <jsp:include page="/WEB-INF/views${viewPage }"/>
</div>
</body>
</html>
