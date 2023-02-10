package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.SellerDTO;

@Repository("blockAuth.mapper.SellerMapper")
public interface SellerMapper {
	//판매자 번호 자동부여
	public String sellerNumGenerate();
	//판매자 정보 삽입
	public Integer sellerInsert(SellerDTO sellerDTO);
	//판매자정보 리스트 불러오기
	public List<SellerDTO> selectAll();
	//판매자상세정보 불러오기
	public SellerDTO selectOne(String sellerNum);
	//판매자정보수정
	public Integer sellerUpdate(SellerDTO sellerDTO);
	//판매자정보삭제
	public Integer sellerDelete(String sellerNum);
	
	//AuthInfo에서 Id로 Seller 정보 찾기
	public SellerDTO selectNum(String sellerId);
}
