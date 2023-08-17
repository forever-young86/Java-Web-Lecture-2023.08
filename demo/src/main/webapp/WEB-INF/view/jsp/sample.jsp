<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- if,foreach 조건문 반복문 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 날짜 같은 형식-->
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%> <!-- 산술연사자만 되는 jsp에 문장도 +로 가능하게 하는 기능-->

<!DOCTYPE html>
<html>
<head>
	<%@ include file="./common/head.jspf" %>

</head>
<body>
	<%@ include file="./common/top.jspf" %>
	
    <div class="container" style="margin-top:80px">
        <div class="row">
			<%@ include file="./common/aside.jspf" %>
			<!-- =================Main================== -->
            <div class = "col-9">
            	<h3><strong>샘플</strong></h3>
            <hr>
        	<h3><strong>MemberList</strong></h3>
 		<table class="table table-bordered text-center">
		    <thead>
		      <tr>
				<tr><th>아이디</th><th>이름</th><th>주소</th><th>국가</th></tr>
				<c:forEach var="member" items="${memberList}">
                   <tr>
					<td class="col-3">${member.id}</td>
					<td class="col-3">${member.name}</td>
					<td class="col-3">${member.addr.city}</td>
					<td class="col-3">${member.addr.country}</td>
				</tr>
				</c:forEach>
			</table>

            </div>
            <!-- =================Main================== -->

        </div>
    </div>	
	
	
	
	<%@ include file="./common/bottom.jspf" %>
</body>
</html>