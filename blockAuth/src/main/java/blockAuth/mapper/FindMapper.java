package blockAuth.mapper;

import org.springframework.stereotype.Repository;

import blockAuth.command.FindPwCommand;
import blockAuth.domain.AuthInfo;
import blockAuth.domain.PwChangeDTO;

@Repository("blockAuth.mapper.FindMapper")
public interface FindMapper {
	public AuthInfo findId(String email);
	public String findPw(FindPwCommand findPwCommand);
	public Integer changePw(PwChangeDTO dto);
}
