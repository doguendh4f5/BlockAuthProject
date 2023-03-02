<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.inicis.std.util.SignatureUtil"%>
<%@page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
   <style>
      @font-face{
      font-family:"NanumBarunGothic";
      font-style:normal;
      src:url("/static/font/NanumBarunGothic.ttf") format("truetype");
      }
      #sp1{
         font-family:"NanumBarunGothic";
         font-size:20px;
         text-align:left;
      }
      #sp2{
         font-size:23px;
      }
      #p1{
      
      }
      #p2{
         font-size:18px;
      }
      table {
          height:600px;
          width:480px;
          border-radius:30px;
      }
      table, td, th {
          border-collapse : collapse;
      };
      #tr1{
         background-color:#F6F6F6;
         border-bottom:1px solid #ccc;
      }
      #btn1{
         background-color:#1266FF;
         color:white;
         font-size:17px;
         font-weight:bold;
         border:none;
         padding:10px;
         border-radius:20px;
         width:300px;
         height:50px;
      }
       #btn2{
         background-color:#FF9090;
         color:white;
         font-size:17px;
         font-weight:bold;
         border:none;
         padding:10px;
         border-radius:20px;
         width:300px;
         height:50px;
      }
      #btn1:hover{
         background-color:#6CC0FF;
         color:white;
         font-size:17px;
         font-weight:bold;
         border:none;
         padding:10px;
         border-radius:20px;
         width:300px;
         height:50px;
      }
      #tb1{
      background-color:white;
      }
      .wrapper{
         display : flex;
         justify-content : center;
         align-items : center;
         min-height : 100vh;
         background-color : #f8f9fd;
         background-size : cover;
      }
      #tr2{
      height:20%;
      border-top-left-radius:20px;
      border-top-right-radius:20px;
      background-color:#232323;
      color : white;
      }
      #tr3{
      height:60%;
      }
      #div1{
      width:330px;
      font-size:15px;
      border:2px solid black;
      margin:auto;
      
      }
      #sp3{
      font-weight:bold;
      font-size:25px;
      text-align:left;
      }
      #td4{
         text-align:center;
         border-top : 1px solid #ccc;
      }
      #div2{
      font-size:13px;
      margin:10px;
      }
   </style>
   <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
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
                                <input type="hidden" name="goodname" value="블록어스 멤버십">
                                <input type="hidden" name="buyername" value="${buyerCommand.buyerName }">                       
                                <input type="hidden" name="buyertel" value="${buyerCommand.buyerPhone }">
                                <input type="hidden" name="buyeremail" value="${buyerCommand.buyerEmail }">                      
				    		    <input type="hidden" name="returnUrl" value="http://localhost:8080/paymentTest/paymentReturn">
                                <input type="hidden" name="closeUrl" value="http://localhost:8080/paymentTest/paymentClose">                           
                                <input type="hidden" name="acceptmethod" value="HPP(1):below1000:va_receipt">	
                    </form>
<div class="wrapper">
   <table id="tb1">

         <tr id="tr4">
            <td id="tr2">
               <div>
                  <span id="sp1"><b id="b1">&nbsp;&nbsp;&nbsp;&nbsp;Block-Auth</b><br/>&nbsp;&nbsp;&nbsp;&nbsp;멤버십 가입하기</span>
               </div>
            </td>
         </tr>   

         <tr id="tr3"><td colspan=3>
            <span id="sp3"><Br/>&nbsp;&nbsp;&nbsp;1개월 무료체험</span><br/>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月 <b>4,990</b>원의 혜택<br/><br/><br/>
            <div id="div1">
            <br/>
            <b id="p1">&nbsp;⊙</b> 멤버십 가입 시 전 상품 <b id="p1">무료배송</b><br/>
            <b id="p1">&nbsp;⊙</b> 결제 금액 <b id="p1">1%</b> 적립 (주문 1건당 적립)<br/>
            <b id="p1">&nbsp;⊙</b> 언제든 <b id="p1">해지 가능</b>합니다.<br/>&nbsp;
         </div>
         <br/>
         <div id="div2">
         &nbsp;&nbsp;<input type="checkbox" required="required"/> (필수) <b>멤버십 정기결제</b>에 동의합니다.<br/>
         &nbsp;&nbsp;<input type="checkbox" required="required"/> (필수) <b>이용약관</b> 및 <b>결제 및 멤버십 유의사항</b>에 동의합니다.
         </div>
         </td></tr>
         <tr id="td4"><td colspan="3">
         <c:if test="${membership.toString().equals('F')}">
            <button type="button" id="btn1" onclick="paybtn()">무료체험 신청</button>
            </c:if>
            <c:if test="${membership.toString().equals('T')}">
            <button type="button" id="btn2">멤버십 구독중</button>
            </c:if>
         </td></tr>
   </table>
</div>

</body>
</html>