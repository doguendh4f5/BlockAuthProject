package blockAuth.command;



import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GoodsCommand {
	String goodsNum;
	String goodsName;
	Long goodsPrice;
	String goodsContent;
	String goodsSize;
	Long goodsStock;
	String goodsCountry;
	String goodsCategory;
	String goodsCompany;
	Date goodsRegiDate;
	MultipartFile goodsMain;
	Long goodsDeliveryFee;
	
}