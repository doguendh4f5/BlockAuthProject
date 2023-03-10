<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="/static/css/style3.css">
<script type="text/javascript" src="/static/js/vote.js"></script>
<style type="text/css">
   /*!
 * Start Bootstrap - Shop Homepage (https://startbootstrap.com/template-overviews/shop-homepage)
 * Copyright 2013-2019 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-shop-homepage/blob/master/LICENSE)
 */
body {
  padding-top: 56px;
}
.ftco-section{
	padding-top : 50px;
	padding-bottom : 50px;
}
#btn1{
    background-color : #007bff;
    border : none;
    border-color : #007bff;
    width : 100px;
    height : 40px;
    color : white;
    border-radius : 5px;
}  

#btn2{
    background-color : #FFBA85;
    border : none;
    border-color : #FFBA85;
    color : white;
    border-radius : 5px;
} 
 
#ip1{
	width : 420px;
	display : inline-block;
}
</style>
<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" >
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>관리자 페이지</title>
  <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>

   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
   
   
<style type="text/css">
   th, td{
      text-align : center;   
   }
   #div1{
   text-align : center;
   margin-right:150px;
   }
   .table{
       word-break : break-all;
   }
   th{
   width:200px;
   }
   #img{
	height: 150px;
	width : 150px;
}
#img1{
	height: 250px;
	width : 550px;
}
#con1{
	width:auto;
	height:80vh;
	display: flex;
	justify-content: center;
	align-items: center;
}
</style>

<script type="text/javascript">
function reg(){
   location.href="voteWrite";
}
</script>
</head>

<body>

  <!-- Navigation -->
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <div class="container">
      <a class="navbar-brand" href="/">Block-Auth</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
        <c:if test="${authInfo != null}">
        <c:if test="${authInfo.grade == 'admin'}">
          <li class="nav-item">
            <a class="nav-link" href="/admin/adminList">관리자페이지
              <span class="sr-only">(current)</span>
            </a>
          </li>
          </c:if>
         </c:if>
         <c:if test="${authInfo != null}">
        <c:if test="${authInfo.grade == 'buyer'}">
          <li class="nav-item">
            <a class="nav-link" href="../cartList">장바구니</a>
          </li>
          </c:if></c:if>
         <c:if test="${authInfo == null}">
          <li class="nav-item">
            <a class="nav-link" href="/login">마이페이지</a>
          </li>
          </c:if>
          <c:if test="${authInfo != null}">
          <c:if test="${authInfo.grade == 'buyer' || authInfo.grade == 'seller'}">
          <li class="nav-item">
            <a class="nav-link" href="/mypage">마이페이지</a>
          </li>
          </c:if>
          </c:if>
          <c:if test="${authInfo == null}">
          <li class="nav-item">
            <a class="nav-link" href="/login">로그인</a>
          </li>
          </c:if>
          <c:if test="${authInfo != null}">
          <li class="nav-item">
            <a class="nav-link" href="/login/logout">로그아웃</a>
          </li>
          </c:if>
           <c:if test="${authInfo != null}">
          <li class="nav-item">
            <a class="nav-link" href="/inquire/inquireList">고객센터</a>
          </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <h1 class="my-4">Block-Auth</h1>
        <div class="list-group">
          <a href="/buyer/buyerList" class="list-group-item">구매자 관리</a>
          <a href="/seller/sellerList" class="list-group-item">판매자 관리</a>
          <a href="/goods/goodsList" class="list-group-item">상품 관리</a>
          <a href="/contract/contractList" class="list-group-item">계약 관리</a>
          <a href="/vote/voteList" class="list-group-item">투표 관리</a>
        </div>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
        <div class="container" id="con1">
<table cellpadding="10" border="1">
	<tr><td colspan="2"><img src="/voteImg/${voteImg}" id="img1" /><br/></td></tr>
		<c:choose>
			<c:when test="${lists.size() eq 0 }">
				<tr>
					<td colspan=5 style="text-align: center">투표 상품이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
			<tr style="text-align:center;">
				<c:forEach items="${lists}" var="list" varStatus="status">
					
						<td><b>[ ${status.count } ]</b>
						<br/>
						<span><img src="/goods/upload/${list.goodsDTO.goodsMain }" id="img"/></span>
					<br/>
						<span onclick="setRemoveCandidate('')"><b>${list.goodsDTO.goodsName} </b></span><span>(${list.goodsDTO.goodsCompany})</span>
				<br/>
						<span><c:if test="${list.candidatesDTO.vote <= 0}">
								<div class="progress-bar" role="progressbar"
									style="width: 0%; max-width: 0%;"
									aria-valuenow:"0" 
							aria-valuemin="0" aria-valuemax="0">
									<span class="progress-title" style="color: black;">0%</span>
								</div>
							</c:if> <c:if test="${list.candidatesDTO.vote > 0 }">
								<div class="progress-bar" id="pb1" role="progressbar"
									style="width:${list.candidatesDTO.vote / sum * 100}%; 
								   max-width:${list.candidatesDTO.vote / sum * 100}%; "
									aria-valuenow:"${list.candidatesDTO.vote / sum
									* 100}" 
							aria-valuemin="0"
									aria-valuemax="${list.candidatesDTO.vote / sum * 100}">
									<span class="progress-title" style="color: black;"> <fmt:formatNumber
											value="${list.candidatesDTO.vote / sum * 100}" pattern="0.00" />%
									</span>
								</div></span>
						<span><b>${list.candidatesDTO.vote}</b>표</span>
					<br/>
					</c:if>
					<c:if test="${status.count % 2 == 0 }">
					</td>
				</c:if>
				</c:forEach>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>
      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright © BlockAuth Corp. 2023 All Rights Reserved.</p>
    </div>
    <!-- /.container -->
  </footer>

</body>
</html>