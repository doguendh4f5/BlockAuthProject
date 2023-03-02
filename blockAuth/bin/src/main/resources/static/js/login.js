// 가나슈에 보내기 전 파일(개인키)을 웹브라우저에서 js를 이용해 내용을 읽어옴
// 이후 가나슈로부터 개인키를 이용해 지갑주소를 받아옴

window.addEventListener("load", function() {
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545")); // Ganache
});

// 자바스크립트를 이용해서 파일 읽어오기
function onFileSelected(event) {
	const file = event.target.files[0]; // 받아온 파일
	const reader = new FileReader(); // 파일을 읽어오는 객체
	reader.onload = function(event) {
		console.log("파일 내용 : " + event.target.result);
		const privateKey = event.target.result; // 개인키 정보 저장
		// 지갑
		const account = web3.eth.accounts.privateKeyToAccount(privateKey);// 가나슈로부터 지갑(공개키, 개인키...) 가져옴
		const address = account.address; // 지갑에서 주소만 가져옴
		console.log("address : " + address);
		$.ajax({
			type: "post",
			dataType: "text",
			url: "/login/privateLogin",
			data: { "address": address },
			success: function(result) {
				if (result == '1') {
					location.href = "/";
				} else {
					alert("인증서에 해당하는 사용자가 없어 로그인되지 않았습니다.");
					location.reload();
				}
			},
			error: function() {
				alert("오류입니다.");
				return false;
			}
		});
	}
	reader.readAsText(file);
}