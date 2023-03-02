package blockAuth.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import blockAuth.domain.AdminInquireDTO;

@Repository("blockAuth.mapper.InquireMapper")
public interface InquireMapper {
	public String sellerInqNumGenerate();
	public int insertInq(AdminInquireDTO inquire);
	public List<AdminInquireDTO> inqList();
	public AdminInquireDTO inqSelcetOne(String adminInqNum);
	public int inquireUpdate(AdminInquireDTO inquire);
	public int sellerReplyUpdate(AdminInquireDTO inquire);
	public int sellerAdminInqDel(String adminInqNum);
}
