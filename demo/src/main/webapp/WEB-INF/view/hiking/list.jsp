<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="./common/head.jspf" %>
	<style>td, th {text-align: center;}</style>
	<script>
		function search() {
			let field = document.getElementById('field').value;
			let query = document.getElementById('query').value;
//			console.log("search()", field, query);
			location.href='/demo/hiking/list?f=' + field + '&q=' +query;
		}
	</script>
</head>
<body>
	<%@ include file="./common/top.jspf" %>
	
    <div class="container" style="margin-top:80px">
        <div class="row">
			<%@ include file="./common/aside.jspf" %>
			<!-- =================Main================== -->
            <div class = "col-9">
            	<table class="table table-sm table-borderless">
            		<tr>
            			<td style="width: 52%; text-align:left;">
			            	<h3>
			            		<strong>산 목록</strong>
			            		<span style="font-size: 0.6em;">
				            		<a href="/demo/hiking/write">
				            			<i class="ms-5 fa-regular fa-file-lines"></i> 글쓰기
				            		</a>
			            		</span>
			            	</h3>
            			</td>
            			<td style="width: 15%;">
	            			<select class="form-select" id="field">
		                        <option value="mtName" ${field eq 'mtName' ? 'selected' : '' }>산 이름</option>
		                        <option value="location" ${field eq 'location' ? 'selected' : '' }>위치</option>
	                   		</select>
            			</td>
            			<td style="width: 25%;">
            				<input class="form-control" placeholder="검색할 내용" id="query" value="${query}" onkeyup="if(window.event.keyCode==13) search()"> 
            	
            						 
            						
            			</td>
            			<td style="width: 8%;">
            				<button class="btn btn-outline-primary" onclick="search()">검색</button>
            			</td>
            		</tr>
            	</table>
	            <hr>
	            <table class = "table">
	        		<tr class="table-secondary">
	        			<th style="width: 5%;">No.</th>
	        			<th style="width: 20%;">산 이름</th>
	        			<th style="width: 20%;">위치</th>
	        			<th style="width: 15%;">고도</th>
	        			<th style="width: 10%;">조회수</th>
	        			<th style="width: 30%;">작성시간</th>
	        		</tr>
	        		<c:forEach var="hiking" items="${hikingList}">
	        			<tr>
	        				<td>${hiking.mtid}</td>
	        				<td><a href="/demo/hiking/detail/${hiking.mtid}">${hiking.mtName}</a></td>
	        				<td>${hiking.location}</td>
	        				<td>${hiking.altitude}</td>
	        				<td>${hiking.viewCount}</td>
							<td>${fn:replace(fn:substring(hiking.modTime, 2, 16), 'T', ' ')}</td>
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