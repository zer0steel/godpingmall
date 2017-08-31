<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table width="70%" border="1">
	<tr>
		<th>상품명</th>
		<th>판매가격</th>
		<th>할인된 가격</th>
		<th>할인률</th>
	</tr>
	<c:forEach items="${goodsMap }" var="goods">
	<tr>
		<td>${goods.value.name }</td>
		<td>${goods.value.price }</td>
		<td>${goods.value.sellPrice }</td>
		<td>${goods.value.discountRate }</td>
	</tr>
	</c:forEach>
</table>
