const MYNFT_ABI = 	[
	{
		"constant": true,
		"inputs": [],
		"name": "name",
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
		"constant": true,
		"inputs": [
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "getApproved",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "approve",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "totalSupply",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_from",
				"type": "address"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "transferFrom",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			},
			{
				"name": "_index",
				"type": "uint256"
			}
		],
		"name": "tokenOfOwnerByIndex",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_from",
				"type": "address"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "safeTransferFrom",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_name",
				"type": "string"
			},
			{
				"name": "_symbol",
				"type": "string"
			}
		],
		"name": "initialize",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "exists",
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
		"inputs": [
			{
				"name": "_index",
				"type": "uint256"
			}
		],
		"name": "tokenByIndex",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "ownerOf",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "balanceOf",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "symbol",
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
		"inputs": [
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			}
		],
		"name": "burn",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_approved",
				"type": "bool"
			}
		],
		"name": "setApprovalForAll",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_from",
				"type": "address"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"name": "_data",
				"type": "bytes"
			}
		],
		"name": "safeTransferFrom",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"name": "_tokenURI",
				"type": "string"
			}
		],
		"name": "registerUniqueToken",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "tokenURI",
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
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			},
			{
				"name": "_operator",
				"type": "address"
			}
		],
		"name": "isApprovedForAll",
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
		"inputs": [
			{
				"name": "_name",
				"type": "string"
			},
			{
				"name": "_symbol",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "constructor"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_by",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "TokenRegistered",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"name": "_from",
				"type": "address"
			},
			{
				"indexed": true,
				"name": "_to",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"indexed": false,
				"name": "_timestamp",
				"type": "uint256"
			}
		],
		"name": "Transfer",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": true,
				"name": "_approved",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "Approval",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": true,
				"name": "_operator",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_approved",
				"type": "bool"
			}
		],
		"name": "ApprovalForAll",
		"type": "event"
	}
];

const CONTRACT_ABI = [
	{
		"constant": false,
		"inputs": [
			{
				"name": "_repoAddress",
				"type": "address"
			},
			{
				"name": "_contractId",
				"type": "uint256"
			},
			{
				"name": "_owner",
				"type": "address"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			}
		],
		"name": "finalizeContract",
		"outputs": [
			{
				"name": "",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "sellerContract",
		"outputs": [
			{
				"name": "contractNum",
				"type": "string"
			},
			{
				"name": "contractDate",
				"type": "string"
			},
			{
				"name": "expiryDate",
				"type": "string"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "active",
				"type": "bool"
			},
			{
				"name": "finalized",
				"type": "bool"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_contractNum",
				"type": "string"
			},
			{
				"name": "_contractDate",
				"type": "string"
			},
			{
				"name": "_metadata",
				"type": "string"
			},
			{
				"name": "_expiryDate",
				"type": "string"
			},
			{
				"name": "_tokenId",
				"type": "uint256"
			},
			{
				"name": "_repoAddress",
				"type": "address"
			}
		],
		"name": "createContract",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getContractCountOfOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "",
				"type": "address"
			},
			{
				"name": "",
				"type": "uint256"
			}
		],
		"name": "contractOwner",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_owner",
				"type": "address"
			}
		],
		"name": "getContractOf",
		"outputs": [
			{
				"name": "",
				"type": "uint256[]"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "_contractId",
				"type": "uint256"
			}
		],
		"name": "getContractById",
		"outputs": [
			{
				"name": "contractNum",
				"type": "string"
			},
			{
				"name": "contractDate",
				"type": "string"
			},
			{
				"name": "expiryDate",
				"type": "string"
			},
			{
				"name": "metadata",
				"type": "string"
			},
			{
				"name": "tokenId",
				"type": "uint256"
			},
			{
				"name": "repoAddress",
				"type": "address"
			},
			{
				"name": "owner",
				"type": "address"
			},
			{
				"name": "active",
				"type": "bool"
			},
			{
				"name": "finalized",
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
		"name": "getCount",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "fallback"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_contractId",
				"type": "uint256"
			}
		],
		"name": "ContractCreated",
		"type": "event"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": false,
				"name": "_owner",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "_contractId",
				"type": "uint256"
			}
		],
		"name": "ContractFinalized",
		"type": "event"
	}
];

const GAS_AMOUNT = 500000;

const MYNFT_CA = "0xAefc9B26804E039b739B81F0d28c380671779215";
const CONTRACT_CA = "0xB4bBA1CF4aA9E93a79471DAC07549984Bc073816";

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
		data[0].contractInstance = web3.eth.contract(MYNFT_ABI).at(MYNFT_CA); 	
		contract.contractInstance = web3.eth.contract(CONTRACT_ABI).at(CONTRACT_CA);
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
			contract.contractInstance.createContract($("#contractNum").val(), $("#contractDate").val(), data[0]['dataURI'], $("#expiryDate").val(), data[0]['tokenId'], MYNFT_CA, 
			{from : data[0]['account'], gas : GAS_AMOUNT}, function(error, transactionHash){
				$.ajax({
					url : "../contract/createContract",
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
			}); 
			
			/*
			watchCreated(function(error, result){
				if (!error) alert("creation completed!");
			});
			*/
		});
});

function registerUniqueToken(contractInstance, account, tokenId, dataURI){ 
	contractInstance.registerUniqueToken(account, tokenId, dataURI, {from : account, gas : GAS_AMOUNT}, function(error, result){
		if(!error){
		}
	}); 
}

function watchTokenRegistered(cb){
	const currentBlock = getCurrentBlock();
	const eventWatcher = data[0]['contractInstance'].TokenRegistered({}, {fromBlock:currentBlock-1, toBlock:"latest"});
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

