<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="goodsInsertForm">
	상품명<input type="text" name="name">
	판매가격<input type="number" name="price">
	할인율<input type="number" name="discountRate">
	카테고리<input type="text" name="category.name">
	<button type="button" id="btnAddGoods">등록</button>
</form>
<table width="70%" border="1">
	<thead>
		<tr>
			<th>추가 가능한 옵션 목록</th>
		</tr>
		<tr>
			<th>
				<select>
				<c:forEach var="name" items="${optionList }">
				<option>${name }</option>
				</c:forEach>
				</select>
				<button>추가</button>
			</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<script>
(function() {
	$('#btnAddGoods').click(function() {
		const data = $('#goodsInsertForm').serializeArray();
		ajax('/godpingmall/admin/goods', data, 'post').done(result => {
			handlingResult(result, '/godpingmall/admin/goods/insert');
		});
	});
}())
</script>