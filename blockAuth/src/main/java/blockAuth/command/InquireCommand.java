package blockAuth.command;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class InquireCommand {
	String adminInqNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date adminInqDate;
	String adminInqTitle;
	String adminInqContent;
	String adminInqCategory;
	String adminNum;
	String adminReplyContent;
	Date adminReplyDate;
}
