<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- if,foreach 조건문 반복문 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> <!-- 날짜 같은 형식-->
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%> <!-- 산술연사자만 되는 jsp에 문장도 +로 가능하게 하는 기능-->

<!DOCTYPE html>
<html>
<head>
	<%@ include file="./common/head.jspf" %>
	<style>td, th {text-align: center;}</style>
</head>
<body>
	<%@ include file="./common/top.jspf" %>
	
    <div class="container" style="margin-top:80px">
        <div class="row">
			<%@ include file="./common/aside.jspf" %>
			<!-- =================Main================== -->
            <div class = "col-9">
            	<h3> <!--h는 블록이라서 이것 밖에 작성하면 줄이 바뀐다 -->
            		<strong>블로그 목록</strong>
            		<span style="font-size: 0.6em;">
	            		<a href="/demo/blog/write">
	            			<i class="ms-5 fa-regular fa-file-lines"></i> 글쓰기 <!-- 폰트어썸에서 가져온 아이콘 -->
	            		</a>
            		</span>
            	</h3>
	            <hr>
	            <table class = "table">
	        		<tr class="table-secondary">
	        			<th style="width: 8%;">ID</th>
	        			<th style="width: 14%;">필명</th>
	        			<th style="width: 48%;">제목</th>
	        			<th style="width: 20%;">작성시간</th>
	        			<th style="width: 10%;">조회수</th>
	        		</tr>
	        		<c:forEach var="blog" items="${blogList}"> <!--blog라는 변수를 써서 blogList라고 받는다! -->
	        			<tr>
	        				<td>${blog.bid}</td>
	        				<td>${blog.penName}</td>
	        				<td><a href="/demo/blog/detail/${blog.bid}">${blog.title}</a></td>
							<td>${fn:replace(fn:substring(blog.modTime, 2, 16), 'T', ' ')}</td><!-- substring으로 글자수를 줄이고, 다시 replace로 감싸 변경 -->
	        				<td>${blog.viewCount}</td>
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