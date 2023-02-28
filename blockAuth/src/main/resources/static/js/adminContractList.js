var account;
var tokenId;
var cnt;

window.addEventListener("load", function(){ 
	window.web3 = new Web3(new Web3.providers.HttpProvider("http://localhost:7545")); 
	web3.eth.defaultAccount = account;
	ciMyNFT =  web3.eth.contract(MYNFT_ABI).at(MYNFT_CA);
	ciContract = web3.eth.contract(CONTRACT_ABI).at(CONTRACT_CA);
	getMyContract();
});


function getMyContract(){ 
	account = $("#wallet").val();
	ciContract.getContractOf(account, {from : account, gas : GAS_AMOUNT}, function(error, result){
		cnt = result.length;
		result.forEach((num, index, array) => {
			getContractById(Number(num));
			}); 
	});
}


function getContractById(value){ 
	ciContract.getContractById(value, {from : account, gas : GAS_AMOUNT}, function(error, result){
			$("#contractNum").text(result[0]);
			$("#metadata").text(result[3]);
			$("#tokenId").text(result[4]['c']);
			$("#contractDate").text(result[1]);
			$("#expiryDate").text(result[2]);
			$("#contractAddress").text(result[5]);
			$("#owner").text(result[6]);
			$("#active").text(result[7]);
			$("#finalized").text(result[8]);
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