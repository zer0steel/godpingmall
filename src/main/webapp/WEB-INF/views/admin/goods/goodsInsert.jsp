<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="goodsInsertForm">
	상품명<input type="text" name="name">
	판매가격<input type="number" name="price">
	할인율<input type="number" name="discountRate">
	카테고리<input type="text" name="category.id">
	<button type="button" id="btnAddGoods">등록</button>
</form>

<script>
(function() {
	$('#btnAddGoods').click(function() {
		const data = $('#goodsInsertForm').serializeArray();
		ajax('/godpingmall/admin/goods', data, 'post').done(result => {
			if(result.success)
				location.href = '/godpingmall/admin/goods/insert';
			else
				alert(result.message);
		});
	});
}())
</script>