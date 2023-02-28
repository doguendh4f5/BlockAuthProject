<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function num(val){

	var val = $("#" + val).text();
	console.log(val);

	$("#candi").val(val);
}
</script>
</head>
<body>
	<table border="1">
		<tr>
			<th>상품 번호</th>
			<th>상품명</th>
			<th>판매자</th>
		</tr>
		<c:forEach var="goodsCommand" items="${list}" varStatus="cnt">
		<tr>
			<td><span id="${goodsCommand.goodsNum}" onclick="num('${goodsCommand.goodsNum}');">${goodsCommand.goodsNum}</span></td>
			<td>${goodsCommand.goodsName}</td>
			<td>${goodsCommand.goodsCompany}</td>
		</tr>
</c:forEach>
	</table>
	<form action="candidateOk">
		후보 상품번호 : <input type="text" name="name" id="candi"/><br /> 
		<input type="submit" value="후보 상품 등록" />
	</form>
</body>
</html>