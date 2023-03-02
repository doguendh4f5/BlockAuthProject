<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%

	String mid					= "INIpayTest";		                    // 상점아이디					
	String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
	
	String mKey = SignatureUtil.hash(signKey, "SHA-256");

	String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
	String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
	String price				= "49";								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


	Map<String, String> signParam = new HashMap<String, String>();

	signParam.put("oid", orderNumber);
	signParam.put("price", price);
	signParam.put("timestamp", timestamp);

	String signature = SignatureUtil.makeSignature(signParam);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--테스트 JS--><script language="javascript" type="text/javascript" src="https://stgstdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
        <script type="text/javascript">
            function paybtn() {
                INIStdPay.pay('SendPayForm_id');
            }
        </script>
  
   <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = '(' + extraRoadAddr + ')';
                }
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;                
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }
                /*
                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
                */
            }
        }).open();
    }
</script>
</head>
<body>
<form name="" id="SendPayForm_id" method="post" class="mt-5">
                                <input type="hidden" name="version" value="1.0">
                                <input type="hidden" name="gopaymethod" value="Card:Directbank:vbank">				    	
                                <input type="hidden" name="mid" value="<%=mid%>">                     				   
                                <input type="hidden" name="oid" value="<%=orderNumber%>">                     				    
                                <input type="hidden" name="price" value="<%=price%>">                            
                                <input type="hidden" name="timestamp" value="<%=timestamp%>">       				    				    
                                <input type="hidden" name="signature" value="<%=signature%>">
				    		    <input type="hidden" name="mKey" value="<%=mKey%>">
                                <input type="hidden" name="currency" value="WON">				    		
                                <input type="hidden" name="goodname" value="블록어스 상품 결제">
                                <input type="hidden" name="buyername" value="${buyerCommand.buyerName }">                       
                                <input type="hidden" name="buyertel" value="${buyerCommand.buyerPhone }">
                                <input type="hidden" name="buyeremail" value="${buyerCommand.buyerEmail }">                      
				    		    <input type="hidden" name="returnUrl" value="http://localhost:8080/paymentTest/paymentReturn">
                                <input type="hidden" name="closeUrl" value="http://localhost:8080/paymentTest/paymentClose">                           
                                <input type="hidden" name="acceptmethod" value="HPP(1):below1000:va_receipt">
                    </form>
<table width="700" align="center">
<tr><td>
<strong>주문서</strong>
<hr />
<strong>1. 주문 상품</strong><br />
</td></tr>
</table>
<p>
<table width="700" align="center">
	<tr><td>주문 상품 정보</td>	<td>수량/상품금액</td><td>배송비</td></tr>
	
	<c:forEach items="${list}" var="dto">
	<tr>
	
		<td>
		<img src="/goods/upload/${dto.goodsDTO.goodsMain}" height="60" />
		${dto.goodsDTO.goodsName}
		</td>
		<td>${dto.cartDTO.qty}개<br />
			${dto.totalPrice}원</td>
		<td>
		<c:if test="${dto.goodsDTO.goodsDeliveryFee == 0}">
			<span>무료배송</span>
			</c:if>
			<c:if test="${dto.goodsDTO.goodsDeliveryFee > 0}">
			<span>${dto.goodsDTO.goodsDeliveryFee }원</span>
		</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
</p>
<p>
<form action="purchase" method="post">
<input type="hidden" name="goodsNums" value="${goodsNums}">
<input type="hidden" name="goodsTotalPrice" value="${goodsTotalPrice}"> 
<input type="hidden" name="goodsTotalDelivery" value="${goodsTotalDelivery}">  
<input type="hidden" name="totalPrice" value="${goodsTotalPrice + goodsTotalDelivery}">
<table width="700"  align="center" method="post">
	<tr>
		<td alignt="left">
			<table width="350" >
				<tr><th colspan=2>2. 배송지 정보 입력</th></tr>
				<tr><td>받는 사람</td>
					<td><input type="text" name="RECEIVER_NAME" /></td></tr>
				<tr>
            <td>주소</td>
            
                  <td><input type="text" name="buyerAddr1" 
                  id="sample4_roadAddress" 
                  style="width: 65%; background-image:url('img/search.png'); 
                  background-repeat:no-repeat; background-position:right; background-size:20px 20px; width:171px;" 
                  onclick="sample4_execDaumPostcode();" readonly="readonly" placeholder="주소"/><br />
                  
                  
                  <input type="text" name="buyerAddr2"  id="sample4_extraAddress"  placeholder="상세주소"/><br />
                  
                 
                  <input type="text" name="buyerPostcode" 
                  id="sample4_postcode" readonly="readonly" placeholder="우편번호"/>
                  
                  </td>
         </tr>
						
				<tr><td>받는 사람 연락처</td>
					<td><input type="text" name="RECEIVER_PHONE" /></td></tr>
				<tr><td>주문메세지</td>
					<td><input type="text" name="DELIVERY_REQ" /></td></tr>
				
			</table>
		</td>
		<td alignt="right">
			<table width="300">
				<tr><th colspan=2>3.결제금액</th></tr>
				
				<tr><td align="left">상품금액 : <fmt:formatNumber type="currency" value="${goodsTotalPrice }" /></td><td align = "right"> 원</td></tr>
				<tr><td align="left">배송비 : <fmt:formatNumber type="currency" value="${goodsTotalDelivery }" /></td><td align = "right"> 원</td></tr>
				<tr><td align="left">총 결제 금액 : <fmt:formatNumber type="currency" value="${goodsTotalPrice + goodsTotalDelivery }" /></td><td align = "right"> 원</td></tr>
				<tr><td align="center" colspan=2><button type="button" onclick="paybtn()">결제하기</button></td></tr>
			</table>
		</td>
	</tr>
</table>
</form>
</p>
</body>
</html>