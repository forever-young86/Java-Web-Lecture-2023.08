<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EL 표현언어</title>
	<style>td{padding: 3px;}</style>
</head>
<body style="margin:40px;">
	<h1>Collections</h1>
	<hr>
	<table border="1">
	<tr><th>표현식</th><th>결과</th></tr>
	<tr><td>\${fruitArray[0] }</td><td>${fruitArray[0] }</td></tr>
	<tr><td>\${fruitArray[3] }</td><td>${fruitArray[3] }</td></tr>
	<tr><td>\${sportList[0] }</td><td>${sportList[0] }</td></tr>
	<tr><td>\${sportList[2] }</td><td>${sportList[2] }</td></tr>
	<tr><td>\${map.key}</td><td>${map.key}</td></tr>
	<tr><td>\${map.value}</td><td>${map.value}</td></tr>
	
	
	</table>
</body>
</html>
