pragma solidity ^0.4.18;

import "./MyNFT.sol";

// 계약서 구조체
contract SellerContract {
	SellerContract[] public sellerContract; 
 	mapping(address => uint[]) public contractOwner; 

	struct SellerContract {  
	  string contractNum; // 계약번호
	  string contractDate; // 계약일
	  string expiryDate; // 계약 종료일
 	  string metadata;

	  uint256 tokenId; // 토큰 아이디
	  address repoAddress; // nft 컨트랙트 주소
	  address owner; // 토큰 소유자				
	  bool active; //활성화 여부
	  bool finalized; //판매 종료여부
	}

	function() public {
	  revert();
	}
    
	// 해당 컨트랙트가 특정 NFT 소유권을 가지고 있는지 확인
	modifier contractIsNFTOwner(address _repoAddress, uint256 _tokenId) {
	  address nftOwner = MyNFT(_repoAddress).ownerOf(_tokenId); 
	  require(nftOwner == msg.sender); 
	  _;
	}

    // 해당 토큰이 해당 컨트랙트 주소의 소유인지 확인
	function createContract(string _contractNum, string _contractDate, string _metadata,
							string _expiryDate, uint256 _tokenId, address _repoAddress) public contractIsNFTOwner(_repoAddress, _tokenId) returns(bool) {

		uint contractId = sellerContract.length;
		SellerContract memory newContract;
		newContract.contractNum = _contractNum;
		newContract.contractDate = _contractDate;
		newContract.metadata = _metadata;
		newContract.expiryDate = _expiryDate;
		newContract.tokenId = _tokenId;
		newContract.repoAddress = _repoAddress;
		newContract.owner = msg.sender;
		newContract.active = true;
		newContract.finalized = false;

		sellerContract.push(newContract);
		contractOwner[msg.sender].push(contractId); 

		emit ContractCreated(msg.sender, contractId); 
		return true;
	}

    // // 계약서 만료 혹은 계약 해지로 인한 토큰 폐기
	// function finalizeContract(uint _contractId, address _owner, uint256 _tokenId) public returns(string){

	// 	    sellerContract[_contractId].active = false;
	// 	    sellerContract[_contractId].finalized = true;

	// 		_burn(_owner, _tokenId);

	// 	    emit ContractFinalized(msg.sender, _contractId);

	// 		return "done";

	// }

	// 계약서 만료 혹은 계약 해지로 인한 토큰 폐기
	function finalizeContract(address _repoAddress, uint _contractId, address _owner, uint256 _tokenId) public returns(string){
			
			// contractId = contractOwner[msg.sender];
			delete contractOwner[msg.sender];
			// sellerContract[_contractId] = null;  
			delete sellerContract[_contractId];

			MyNFT(_repoAddress).burn(msg.sender, _tokenId);
			
		    // emit ContractFinalized(msg.sender, _contractId);

			return "done";

	}
	
	// 컨트랙트 내 토큰 소유 권한 변경
	function approveAndTransfer(address _from, address _to, address _repoAddress, uint256 _tokenId) internal returns(bool) {
		MyNFT remoteContract = MyNFT(_repoAddress);// MyNFT 컨트랙트에 컨트랙트 어드래스를 넣고 인스턴스를 가져온 후 
		remoteContract.approve(_to, _tokenId); // 인스턴스에 있는 approve(_to, _tokenId)를 통해 해당 토큰을 받는 어드레스(_to)를 승인하고
		remoteContract.transferFrom(_from, _to, _tokenId); // 해당 어드레스를 전송
		return true;
	}

	// 계약서 전체의 개수를 반환
    function getCount() public constant returns(uint) {
		return sellerContract.length;
	}

	// 소유자의 리스트를 반환
	function getContractOf(address _owner) public constant returns(uint[]) {
		uint[] memory ownedContract = contractOwner[_owner];
		return ownedContract;
	}

	// 해당 소유자의 계약 갯수를 반환
	function getContractCountOfOwner(address _owner) public constant returns(uint) {
		return contractOwner[_owner].length;
	}

	// 특정 ID에 대한 계약 정보를 반환 
	function getContractById(uint _contractId) public constant returns(
	  string contractNum,
	  string contractDate,
	  string expiryDate,

	  string metadata,
	  uint256 tokenId,
	  address repoAddress,
	  address owner,		
	  bool active,
	  bool finalized
) {
		SellerContract memory con = sellerContract[_contractId];
		return (
			con.contractNum,
			con.contractDate,
			con.expiryDate,
			con.metadata,
			con.tokenId,
			con.repoAddress,
			con.owner,
			con.active,
			con.finalized
);
	}

	// 이벤트
	event ContractCreated(address _owner, uint _contractId);
	event ContractFinalized(address _owner, uint _contractId);
}
