<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>EL 표현언어</title>
	<style>td{padding: 3px;}</style>
</head>
<body style="margin:40px;">
	<h1>EL Operator(표현언어 연산자)</h1>
	<hr>
	<table border="1">
		<tr><th>산술표현식</th><th>결과</th></tr>
		<tr><td>\${100}</td><td>${100}</td></tr>
		<tr><td>\${10 + 1}</td><td>${10 + 1}</td></tr>
		<tr><td>\${"10" + 1}</td><td>${"10" + 1}</td></tr> <%-- + 연산자는 오로지 숫자에만 적용됨/ "10"문자열이 아니라 숫자로 인식함--%>
		<tr><td>\${null + 1}</td><td>${null + 1}</td></tr><%--null 문자열이 아니라 숫자 0으로 인식 --%>
		<tr><td>\${"hello"}</td><td>${"hello"}</td></tr>
		<%--<tr><td>\${"hello" + "안녕"}</td><td>${"hello"}${"안녕"}</td></tr> 문자끼리 + 연사자 쓰면 에러남!--%>
		<tr><th>산술표현식</th><th>결과</th></tr>
		<tr><td>\${20-10}</td><td>${20-10}</td></tr>
		<tr><td>\${20*10}</td><td>${20*10}</td></tr>
		<tr><td>\${20/8}</td><td>${20/8}</td></tr>
		<tr><td>\${20 div 8}</td><td>${20 div 8}</td></tr>
		<tr><td>\${20%8}</td><td>${20%8}</td></tr>
		<tr><td>\${20 mod 8}</td><td>${20 mod 8}</td></tr>
		<tr><th>비교연산표현식</th><th>결과</th></tr>
		<tr><td>\${20 == 8}</td><td>${20 == 8}</td></tr>
		<tr><td>\${20 eq 8}</td><td>${20 eq 8}</td></tr>
		<tr><td>\${20 ne 8}</td><td>${20 ne 8}</td></tr> <!-- ne : not equal -->
		<tr><td>\${"안녕" == "hello"}</td><td>${"안녕" == "hello"}</td></tr>
		<tr><td>\${"안녕" eq "hello"}</td><td>${"안녕" eq "hello"}</td></tr>
		<tr><td>\${20 > 8}</td><td>${20 > 8}</td></tr>
		<tr><td>\${20 gt 8}</td><td>${20 gt 8}</td></tr><!--gt: greater than  -->
		<tr><td>\${20 ge 8}</td><td>${20 ge 8}</td></tr><!--ge: greater than or equal -->
		<tr><th>논리연산표현식</th><th>결과</th></tr>
		<tr><td>\${20 > 10 && "안녕" == "hello"}</td><td>${20 > 10 && "안녕" == "hello"}</td></tr>
		<tr><td>\${20 gt 10 and "안녕" eq 'hello'}</td><td>${20 gt 10 and "안녕" eq 'hello'}</td></tr>
		<tr><td>\${20 gt 10 or "안녕" eq "hello"}</td><td>${20 gt 10 or "안녕" eq "hello"}</td></tr>
		<tr><td>\${not ("안녕" eq "hello")}</td><td>${not ("안녕" eq "hello")}</td></tr>
		<tr><th>빈 문자열 확인 표현식</th><th>결과</th></tr>
		<tr><td>\${empty "hello"}</td><td>${empty "hello"}</td></tr>
		<tr><td>\${empty ""}</td><td>${empty ""}</td></tr>
		<tr><td>\${empty null}</td><td>${empty null}</td></tr>
	</table>
	<h3>사례</h3>
	<ul>
		<li>파라메터 값은 ${empty param.num ? '입력하지 않았음' : param.num}입니다.</li>
		<li><a class="nav-link ${menu eq 'board' ? 'active' : ''}" href = "#">게시판</li>
	</ul>
</body>
</html>