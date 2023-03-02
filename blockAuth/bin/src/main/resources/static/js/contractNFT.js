data = [{
			account : null,
			contractInstance : null,
			file : null,
			tokenId : null,
			isRegistered : false,
			dataURI : null
}];

contract = {
			contractInstance : null
}

let privateKey;

function onFileSelected(event){
  const file = event.target.files[0];
  const reader = new FileReader();

  reader.onload = function(event) {
    console.log(event.target.result);
    privateKey = event.target.result;
  };
  reader.readAsText(file);
}

function _getRandomInt(min, max){
	return Math.floor(Math.random() * (max - min)) + min;	
}

window.addEventListener("load", function(){ 
	window.web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545")); 
	web3.eth.defaultAccount = $("#sellerWalletAddr").val();
	getDefault();
});

function getDefault(){
		data[0].account = $("#sellerWalletAddr").val();
		data[0].contractInstance = new web3.eth.Contract(MYNFT_ABI, MYNFT_CA); 	
		contract.contractInstance = new web3.eth.Contract(CONTRACT_ABI, CONTRACT_CA);
		data[0]['tokenId'] = _getRandomInt(123456789, 999999999);
}

$(function(){
$("#imgUpload").click(function(){
		var formData = new FormData();
		const imageInput = $("#captureFile")[0]; 
		formData.append("captureFile", imageInput.files[0]); 
		const projectId = '2Ldnym199Zyqvp1CpHy3o1ztNGV';
		const projectSecret = '93ac42db2e4257d10406f3746fbc7eaf';
		const authorization = "Basic " + btoa(projectId + ":" + projectSecret);
		var option = {
			type : "post",
			dataType : "json",
			url : "https://ipfs.infura.io:5001/api/v0/add", 
			headers : {authorization},
			data : formData, 
			contentType : false, 
			processData : false, 
			enctype : "multipart/form-data",
			success : function(response){
				data[0]['dataURI'] = response.Hash; 
				$("#metadata").val(response.Hash);
				// 파일 확인 경로 // https://selcontest.infura-ipfs.io/ipfs/
				registerUniqueToken(data[0]['contractInstance'], data[0]['account'], data[0]['tokenId'], data[0]['dataURI']);
				
				data[0].isRegistered = true;
				alert("계약서가 저장되었습니다.")
			},
			error : function(res){
				alert("오류가 발생하였습니다. 다시 시도해주세요.");
			}
			
		}
		$.ajax(option);
		return false; 
	});

	
	$("#transferToCA").click(function(){
			transferToCA(data[0]['contractInstance'], data[0]['account'], data[0]['tokenId']);
	});
	
	$("#createContract").click(function(){
			if(!data[0]['tokenId']){
				alert("계약서가 저장되지 않았습니다.");
				return;
			}
			// console.log("data[0]['dataURI'] : " + data[0]['dataURI']);
			const message = data[0]['dataURI'];
			const messageHash = web3.utils.sha3(message);
			const signature = web3.eth.accounts.sign(messageHash, privateKey);
			console.log("data[0]['tokenId'] : " + data[0]['tokenId']);
				console.log("signature.messageHash : " + signature.messageHash);
				console.log("signature.v : " + signature.v);
				console.log("signature.r : " + signature.r);
				console.log("signature.s : " + signature.s);
				
			contract.contractInstance.methods.createContract($("#contractNum").val(), $("#contractDate").val(), data[0]['dataURI'], $("#expiryDate").val(), data[0]['tokenId'], MYNFT_CA, 
			signature.v, signature.r, signature.s,)
								.send({from:data[0]['account'],gas: GAS_AMOUNT})
								.then( (transactionHash) => {
									if(transactionHash){
				$.ajax({
					url : "../contract/createContract",
					type : "post",
					data : {"contractNum" : $("#contractNum").val(), "contractName" : $("#contractName").val(), "sellerNum" : $("#sellerNum").val()
							, "sellerWalletAddr" : $("#sellerWalletAddr").val(), "contractDate" : $("#contractDate").val()
							, "expiryDate" : $("#expiryDate").val(), "metadata" : $("#metadata").val()},
					success : function(result){
						alert("계약서가 생성되었습니다.")
						location.href="/";
					},
					error : function(){
						alert("err");
					}
				});	
				}
			}); 
			
			/*
			watchCreated(function(error, result){
				if (!error) alert("creation completed!");
			});
			*/
		});
		
			$("#createContract2").click(function(){
			if(!data[0]['tokenId']){
				alert("계약서가 저장되지 않았습니다.");
				return;
			}
			
			const message = data[0]['dataURI'];
			const messageHash = web3.utils.sha3(message);
			const signature = web3.eth.accounts.sign(messageHash, privateKey);
			console.log("data[0]['tokenId'] : " + data[0]['tokenId']);
			console.log("signature.messageHash : " + signature.messageHash);
			console.log("signature.v : " + signature.v);
			console.log("signature.r : " + signature.r);
			console.log("signature.s : " + signature.s);
				
			contract.contractInstance.methods.createContract($("#contractNum").val(), $("#contractDate").val(), data[0]['dataURI'], $("#expiryDate").val(), data[0]['tokenId'], MYNFT_CA, 
			signature.v, signature.r, signature.s,)
								.send({from:data[0]['account'],gas: GAS_AMOUNT})
								.then( (transactionHash) => {
									if(transactionHash){
				$.ajax({
					url : "contract/createContract",
					type : "post",
					data : {"contractNum" : $("#contractNum").val(), "contractName" : $("#contractName").val(), "sellerNum" : $("#sellerNum").val()
							, "sellerWalletAddr" : $("#sellerWalletAddr").val(), "contractDate" : $("#contractDate").val()
							, "expiryDate" : $("#expiryDate").val(), "metadata" : $("#metadata").val()},
					success : function(result){
						alert("계약서가 생성되었습니다.")
						location.href="sellerList";
					},
					error : function(){
						alert("err");
					}
				});	
				}
			}); 
			
			/*
			watchCreated(function(error, result){
				if (!error) alert("creation completed!");
			});
			*/
		});
});

function registerUniqueToken(contractInstance, account, tokenId, dataURI){ 
	contractInstance.methods.registerUniqueToken(account, tokenId, dataURI).send({ from: account, gas: GAS_AMOUNT }).then( 
	function(result) {
		if(result.transactionHash){
		}
	}); 
}

function watchTokenRegistered(cb) {
     data[0]['contractInstance'].events.TokenRegistered().on('data',(event)=>{
		  console.log('이벤트 : ' + event);
	  });
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

function transferToCA(contractInstance, account, tokenId){
	contractInstance.transferFrom(account, AUCTIONS_CA, tokenId, {from:account, gas:GAS_AMOUNT}, 
	function(error, result){
		console.log("result : ", result);
	}); 
	/*
	watchTransfered(function(error, result){
		if(!error) alert("CA 전환 성공");
	});
	*/
}

function watchTransfered(cb){
	const currentBlock = getCurrentBlock();
	const eventWatcher = data[0]['contractInstance'].Transfer({}, {fromBlock:currentBlock-1, toBlock:"latest"});
	eventWatcher.watch(cb);
}

function watchCreated(cb){
	const currentBlock = getCurrentBlock();
	const eventWatcher = contract.contractInstance.AuctionCreated({}, {fromBlock:currentBlock-1, toBlock:"latest"});
	eventWatcher.watch(cb);
}

