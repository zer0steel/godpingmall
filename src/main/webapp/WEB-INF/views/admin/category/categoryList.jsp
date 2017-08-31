<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="categoryInsertForm">
	카테고리명<input type="text" name="name">
	카테고리레벨<input type="number" name="level">
	상위카테고리<input type="text" name="superName">
	<button type="button" id="btnAddCategory">추가</button>
</form>
<table width="70%" border="1">
	<tr>
		<th>카테고리명</th>
		<th>레벨</th>
		<th>상위 카테고리</th>
	</tr>
	<c:forEach items="${categoryMap }" var="c">
	<tr>
		<td>${c.value.name }</td>
		<td>${c.value.level }</td>
		<td>${c.value.superName }</td>
	</tr>
	</c:forEach>
</table>
<script>
(function(){
	$('#btnAddCategory').click(function() {
		const data = $('#categoryInsertForm').serializeArray();
		checkLevel(data);
		ajax('category', data, 'post').done(result => {
			handlingResult(result, 'category');
		});
	});
	
	const checkLevel = function(data) {
		if(!data[1].value)
			data[1].value = 0;
	}
}())
</script>