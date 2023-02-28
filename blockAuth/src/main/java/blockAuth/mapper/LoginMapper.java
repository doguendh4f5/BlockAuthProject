package blockAuth.mapper;

import org.springframework.stereotype.Repository;

import blockAuth.domain.AuthInfo;

@Repository(value = "blockAuth.mapper.LoginMapper")
public interface LoginMapper {
	public AuthInfo loginSelect(String userId);
	public AuthInfo addressLogin(String address);
}
