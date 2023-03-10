<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
   /*!
 * Start Bootstrap - Shop Homepage (https://startbootstrap.com/template-overviews/shop-homepage)
 * Copyright 2013-2019 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-shop-homepage/blob/master/LICENSE)
 */
body {
  padding-top: 56px;
}
#con1{
	width:auto;
	height:80vh;
	display: flex;
justify-content: center;
align-items: center;
}
#img{
	height: 200px;
	width : 200px;
}
#img1{
	height: 300px;
	width : 600px;
}
#sp1{
	background-color: #007bff;
    border: none;
    border-color: #007bff;
    width: 85px;
    height: 27px;
    color: white;
    border-radius: 5px;
}
#sp1:hover{
	background-color: #9DCFFF;
    border: none;
    border-color: #9DCFFF;
    width: 85px;
    height: 27px;
    color: white;
    border-radius: 5px;
}
#pb1{
	background-color: #B2CCFF;
}
</style>
<link rel="stylesheet" href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" >
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css">

<script type="text/javascript"
	src="/webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/web3@0.19.0/dist/web3.js"></script>
<script type="text/javascript" src="/static/js/vote.js"></script>
<script>
$(function(){
	var a = "${authInfo.email}"
	console.log("see : " + a);
});

</script>
</head>
<body>
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
            <a class="nav-link" href="/buyer/buyerList">관리자페이지
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
  <div class="container" id="con1">
			<div id="div1">
	<table cellpadding="10" border="1">
	<tr><td colspan="2"><img src="/voteImg/${voteImg}" id="img1" /><br/></td></tr>
	<tr><td colspan="2" style="text-align:center;">원하시는 상품을 투표해주세요 (*^v^*)<br/></td></tr>
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
					
						<button id="sp1" onclick="setVote('${list.candidatesDTO.idx}', '${list.goodsDTO.goodsName}', '${authInfo.walletAddr}', '${list.goodsDTO.goodsNum}');" >투표하기</button>
					<c:if test="${status.count % 3 == 0 }">
					</td>
				</c:if>
				</c:forEach>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	</div>
	</div>
	 <!-- Footer -->
  <footer class="py-5 bg-dark">
    <div class="container">
      <p class="m-0 text-center text-white">Copyright © BlockAuth Corp. 2023 All Rights Reserved.</p>
    </div>
    <!-- /.container -->
  </footer>
</body>
</html>