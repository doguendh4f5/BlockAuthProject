<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script>
function num(val){
	var data = $('#ip1', opener.document).val();
	$(opener.document).find("#ip1").val(data + val + ',');
	window.self.close();
}
</script>
</head>
<body>
	<table border="1">
	<caption>후보자 추가</caption>
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
</body>
</html>