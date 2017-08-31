<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>신규 옵션 추가</h3>
<input type="text" id="optionName" placeholder="옵션 이름">
<button type="button" id="btnOptionAdd">등록</button>
<c:forEach items="${optionList }" var="option">
	<p>${option }
</c:forEach>
<script>
(function() {
	const btnOptionAdd = $('#btnOptionAdd');
	btnOptionAdd.click(function() {
		const optionName = $('#optionName').val();
		addOption(optionName);
	});
	
	const addOption = function(optionName) {
		ajax('option', {optionName : optionName}, 'post').done(function(result) {
			if(result.success)
				location.href = '/godpingmall/admin/option';
			else
				alert(result.message);
		});
	}
}());
</script>