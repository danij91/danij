<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Member data</p>
	<p>이름:${name}</p>
	<p>나이:${age}</p>
	<hr/>
	
	<p>이름:${memDTO.name}</p>
	<p>나이:${memDTO.age}</p>
	<hr/>
	
	<p>이름:${dto.name}</p>
	<p>나이:${dto.age}</p>
	<hr/>
</body>
</html>