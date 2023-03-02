var abi = [
	{
		"constant": true,
		"inputs": [],
		"name": "getVoter",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "getCandidate",
		"outputs": [
			{
				"name": "",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [],
		"name": "setVote_cancel",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "name",
				"type": "string"
			},
			{
				"name": "num",
				"type": "string"
			}
		],
		"name": "setVote",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "getVoteNum",
		"outputs": [
			{
				"name": "",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"inputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "constructor"
	}
];

var isVote = false;
const contract_address = "0x797915990041363f8AEED0AFB4781Da4b9A9353a";


function candidate() {
		window.open("candidate","후보자 등록",
		"width=300, height=300, top=100, left=100");
}

window.addEventListener("load", function() {
	window.web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545"));

	console.log("web3.eth.accounts :: " + web3.eth.accounts);
	voting = web3.eth.contract(abi).at(contract_address);

});

function getDefault2(walletAddr, num) {
	account_address = walletAddr;
	web3.eth.defaultAccount = account_address;
	
	voting.getVoter(function(e, r) {
		isVote = r;
		if (r) {
			voting.getVoteNum(function(e2, r2) {
				if (r2 != num) {
					isVote = 'false';
				}
			});
		}
	});
}

function setVote(idx, name, walletAddr, num){
	getDefault2(walletAddr, num);
	
	if(isVote){
		alert('이미 투표에 참여하셨습니다.');
		return;
	}
	
	if(account_address == ""){
		alert('지갑주소 정보가 존재하지 않습니다. 지갑주소를 입력해주세요.');
		location.href="/mypage";
	}else if(walletAddr == "none"){
		alert('투표 권한이 존재하지 않습니다');
	}
	
	var newName = num + '_' + name

	voting.setVote(newName, num, { gas: 200000 }, function(e, r) {
			vote_transaction_id = r;
			if(r){
				$.ajax({
					url: '/vote/voteWrite',
					type: 'post',
					data:{'idx': idx, 'voteNum' : num, 'account' : account_address, 'tx_id' : r},
					success : function(resp) {
						console.log('resp :: ' + resp);
						if(resp){
							alert("투표되었습니다.");
							location.reload();
						}else{
							alert("이미 투표가 완료되었습니다.");
						}
					},
					error : function(){
						alert("에러가 발생했습니다.");
					}							
				});
			}
	});
}

/*
function setRemoveCandidate(idx){
	$.ajax({
		url:"voteRemoveCandidate",
		type: 'post',
		data: { idx: idx},
		success: function(resp) {
			if(resp){
				location.reload();
			}else{
				alert("삭제되지 않았습니다.");
			}
		},
		error: function(){
			alert("서버 오류");
		}
	});
}
*/

/*
function getDefault() {
	account_address = $("#voteAccount").val();
	web3.eth.defaultAccount = account_address ;
	//투표 여부
	voting.getVoter(function(e, r) {
		isVote = r;
		console.log('Smart Contract Vote :: ' + r);
		if (r) {
			voting.getCandidate(function(e1,r1) {
				vote_candidate = r1;
				console.log('Smart Contract Vote Candidate :: ' + r1);
			});
		}
	});
}

function setVote_cancel(){
	getDefault();
	if(!isVote){
		alert('투표를 안했습니다.');
		return;
	}
	if(account_address == ""){
		alert("주소를 입력해주세요.");
		return;
	}
	if (confirm('투표를 취소 하시겠습니까?')) {
		voting.setVote_cancel({gas:200000},function(e, r){
			vote_transaction_id = r;
			console.log('vote_transaction_id :: ' + r);
			if(r){
				$.ajax({
					url: '/vote_cancel',
					type: 'post',
					data: { account: account_address },
					success: function(resp) {
						console.log('resp :: ' + resp);
						location.href="vote?account="+account_address;
					},
					error:function(){
						alert("서버오류");
					}
				});
			}
		});
	}
}
*/