<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memForm</title>
</head>
<body>
	<p>Member data</p>
	<form name="frm" action="mem.htm" method="post">
    <label for="fname">이름</label>
    <input type="text" name="name" id="fname" /><br/>
    <label for="fage">나이</label>
    <input type="text" name="age" id="fage"/>
    <input type="submit" value="send" />
  </form>
</body>
</html>