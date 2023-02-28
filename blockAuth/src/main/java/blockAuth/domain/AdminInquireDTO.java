package blockAuth.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "adminInquireDTO")
public class AdminInquireDTO {
	String adminInqNum;
	String sellerNum;
	String buyerNum;
	String adminInqTitle;
	String adminInqContent;
	Date adminInqDate;
	String adminInqCategory;
	String adminNum;
	String adminReplyContent;
	Date adminReplyDate;
}      