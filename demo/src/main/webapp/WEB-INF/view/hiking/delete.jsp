<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="./common/head.jspf" %>

</head>
<body>
	<%@ include file="./common/top.jspf" %>
	
    <div class="container" style="margin-top:80px">
        <div class="row">
			<%@ include file="./common/aside.jspf" %>
			<!-- =================Main================== -->
            <div class = "col-9">
            	<h3><strong>산 정보 삭제</strong></h3>
            <hr>
<!-- 선생님 --> <div class="row">
            	<div class="col-3"></div>
            	<div class="col-6">
            		<div class= "card border-warning">
            			<div class="card-body">
            				<strong class="card-title"> 삭제하시겠습니까?</strong>
            				<br>
            				<p class="card-text text-center mt-4">
            					<button class="btn btn-danger" onclick="location.href='/demo/hiking/deleteConfirm/${mtid}'">삭제</button>
            					<button class="btn btn-secondary" onclick="location.href='/demo/hiking/detail/${mtid}?option=DNI'">취소</button>
            				</p>
            			</div>
            		</div>	
            	</div>
   
            
 <!--내가한것	<div class="card" style="width:400px">
  			    <div class="card-body" style="text-align: center;">
			      <p class="card-text" style="text-align: center;">정말로 삭제하시겠습니까?</p>
			      <a href="/demo/blog/deleteConfirm/${blog.bid}" class="btn btn-primary">확인</a>
			      <a href="/demo/blog/detail/${blog.bid}?option=DNI" class="btn btn-secondary">취소</a>
			    </div>
			 </div>
			  <br>
            </div> -->
            <!-- =================Main================== -->

        </div>
    </div>	
	
	
	
	<%@ include file="./common/bottom.jspf" %>
</body>
</html>