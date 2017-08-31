const ajax = function(url, data, method) {
	return $.ajax({
		url : url,
		data : data,
		method : method || 'GET'
	});
}

const handlingResult = function(result, redirectUrl) {
	if(result.success)
		location.href = redirectUrl;
	else
		alert(result.message);
}