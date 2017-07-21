<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HttpMessageConvert Demo</title>
</head>
<body>
	<div id="resp"></div>
	<input type="button" onclick="req();" value="请求"/>
</body>
<script src="assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
<script>
	function req(){
		$.ajax({
			url:"convert",
			data:"007-fly",
			type:"POST",
			contentType:"application/x-demotype",
			success:function(msg){
				$("#resp").html(msg);
			}
		});
	}
</script>
</html>
