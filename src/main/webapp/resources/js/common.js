const ajax = function(url, data, method) {
	return $.ajax({
		url : url,
		data : data,
		method : method || 'GET'
	});
}