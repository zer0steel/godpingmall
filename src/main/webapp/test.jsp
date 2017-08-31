<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<button id="btn">ajax 보내기</button>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
(function() {
	$('#btn').click(function() {
		let data = {
			map : {
				key : [
					{goodsId : 1, optionName : '옵션이름', optionValue : '옵션값', extraPrice : 1000},
					{goodsId : 2, optionName : '옵션이름2', optionValue : '옵션값2', extraPrice : 2000}
					]
			}
		};
		
		$.ajax({url : 'test', data : data}).done(function(result) {
			
		})
	})
}())
</script>
</body>
</html>