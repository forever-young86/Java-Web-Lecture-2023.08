<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<% pageContext.setAttribute("newline", "\n"); %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="./common/head.jspf" %>
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<script>
		function showModal() {
			$('#deleteModal').modal('show');
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
                 <h3>
            		<strong>산 정보 상세 조회</strong>
            		<span style="font-size: 0.6em;">
	            		<a href="/demo/hiking/list">
	            			<i class="ms-5 fa-solid fa-list"></i> 목록
	            		</a>
	            		<a href="/demo/hiking/update/${hiking.mtid}">
	            			<i class="ms-3 fa-regular fa-pen-to-square"></i> 수정
	            		</a>
	            		<a href="/demo/hiking/delete/${hiking.mtid}">
	            			<i class="ms-3 fa-solid fa-trash-can"></i> 삭제
	            		</a>
	            		<a href="javascript:showModal()">
            				<i class="ms-3 fa-regular fa-window-maximize"></i> 모달로 삭제</a>
	            		<!--<button type="button" class="btn btn-outline-primary btn-small ms-3" data-bs-toggle="modal" data-bs-target="#deleteModal">-->
				        </button>      
            		</span>
            	</h3>
            	<hr>
            	<div class="row">
            		<div class="col-8">
            			<h5>${hiking.mtName}</h5>
            			<h6>글번호: ${hiking.mtid} 
            			<br>
            			작성시간: ${fn:replace(hiking.modTime, 'T', ' ')}
  						<h6 style: "text-end;">조회 ${hiking.viewCount}</h6>
            			<br>
            			산이름: ${hiking.mtName}
            			<br>
            			위치: ${hiking.location}
            			<br>
            			고도: ${hiking.altitude}
            			</h6>
            		</div>
            	</div>
            	<hr>
            	<div class="row">
            		<div class="col-1"></div>
            		<div class="col-10">
            			${fn:replace(hiking.altitude, newline, '<br>') }
            		</div>
            		<div class="col-1"></div>
            	</div>
            </div>
            <!-- =================Main================== -->

        </div>
    </div>	
	
	
	
	<%@ include file="./common/bottom.jspf" %>
	
    <div class="modal" id="deleteModal"> <!-- 모달은 hidden 화면에 보이지 않기때문에 통상 코드를 뒤쪽에 배치한다 -->
        <div class="modal-dialog">
            <div class="modal-content">
        
                <div class="modal-header">
                    <h4 class="modal-title">삭제 확인</h4>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    정말로 삭제하시겠습니까?
                </div>
        
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal"
                    		onclick="location.href='/demo/hiking/deleteConfirm/${hiking.mtid}'">삭제</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>
             </div>
         </div>
        </div>
</body>
</html>