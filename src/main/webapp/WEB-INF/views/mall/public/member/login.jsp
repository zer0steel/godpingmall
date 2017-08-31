<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
로그인 페이지
<form method="POST">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label>이메일<input type="text" name="username"></label>
    </div>
    <div>
        <label>비밀번호<input type="password" name="password"></label>
    </div>
    <div>
        <button>로그인</button>
    </div>
</form>