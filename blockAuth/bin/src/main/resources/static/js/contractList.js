var account;
var tokenId;
var cnt;

window.addEventListener("load", function(){ 
	web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545")); 
	web3.eth.defaultAccount = account;
	ciMyNFT =  new web3.eth.Contract(MYNFT_ABI, MYNFT_CA);
	ciContract = new web3.eth.Contract(CONTRACT_ABI, CONTRACT_CA);
});

let metadata;
let privateKey;
let _v;
let _r;
let _s;

function onFileSelected(event){
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = function(event) {
    console.log(event.target.result);
    privateKey = event.target.result;
	getMyContract();
  };
  reader.readAsText(file);
}

function getMyContract(){ 
	account = $("#wallet").val();
	ciContract.methods.getContractOf(account).call()
			.then(result => {
		cnt = result.length;
		result.forEach((num, index, array) => {
			getSellerAccountById(Number(num));
			getContractById(Number(num));
			}); 
	});
}

function getSellerAccountById(target){ 
	ciContract.methods.getSellerAccountById(target).call({ from: account, gas: GAS_AMOUNT }).then(
		(result) => {
			 _v = result[0];
			 _r = result[1];
			 _s = result[2];
			
				});
}

function getContractById(target){ 
	ciContract.methods.getContractById(target).call({ from: account, gas: GAS_AMOUNT }).then(
		(result) => {
			
			const privateKey1 = privateKey;
			const message = result[3];
			const messageHash = web3.utils.sha3(message); // 받아온 메시지 암호화
			const signature = web3.eth.accounts.sign(messageHash, privateKey1); // 재서명
			// 재서명 정보 구조체에
			const signature1 = {
				messageHash : signature.messageHash,
				v : signature.v,
				r : signature.r,
				s : signature.s
			}
			console.log(_v);
			console.log(_r);
			console.log(_s);
			
			console.log(signature.v);
			console.log(signature.r);
			console.log(signature.s);
			// 블록체인 내의 디지털 서명과 현재 서명이 일치하는지 확인 후 리커버
			const signerAddress = web3.eth.accounts.recover(signature1);
			if(signerAddress == web3.eth.accounts.recover(messageHash, _v, _r, _s)){						
				$("#contractNum").text(result[0]);
				$("#metadata").text(result[3]);
				console.log(result[4]);
				$("#tokenId").text(result[4]);
				$("#contractDate").text(result[1]);
				$("#expiryDate").text(result[2]);
				$("#contractAddress").text(result[5]);
				$("#owner").text(result[6]);
				$("#active").text(result[7]);
				$("#finalized").text(result[8]);
			}else{
				alert("인증서가 일치하지 않습니다. 인증서를 확인해주세요.");
			}

	});
}


function finalizeContract(){ 
	ciContract.finalizeContract(CONTRACT_CA, 0, $("#wallet").val(), 371268423,{from : account, gas : GAS_AMOUNT}, function(error, result){
	}); 
	
	/*
	watchFinalized(function(error, result){
		if(!error) alert("Auction Finalized!");
	});
	*/
}


function watchFinalized(cb){
	const currentBlock = getCurrentBlock();
	const eventWatcher = ciAuctions.AuctionFinalized({}, {fromBlock:currentBlock-1, toBlock:"latest"});
	eventWatcher.watch(cb);
}

function getCurrentBlock(){
	return new Promise((resolve, reject) => {
		web3.eth.getBlockNumber((err, blocknumber)=>{ 
			if(!err) resolve(blocknumber)
			reject(err)
		});
	});
}