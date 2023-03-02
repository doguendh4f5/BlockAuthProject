pragma solidity ^0.4.23;

contract Voting{

    address voter;
    mapping (address => bool)  voters;
    mapping (address => string)  candidates;
    mapping (address => string)  voteNum;

    constructor() public{
        voter = msg.sender;
    }

    function getVoteNum() public constant returns(string){
        return voteNum[msg.sender];
    }

    function getVoter() public constant returns(bool){
        return voters[msg.sender];
    }

    function getCandidate() public constant returns(string){
        return candidates[msg.sender];
    }

    function setVote(string name, string num)  public  {
        if(voters[msg.sender] != true){
            voters[msg.sender] = true;
            candidates[msg.sender] = name;
            voteNum[msg.sender] = num;
        }
    }

    function setVote_cancel() public{
        voters[msg.sender] = false;
        candidates[msg.sender] = "";
    }
    
} 