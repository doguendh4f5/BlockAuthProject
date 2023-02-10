package blockAuth.domain;



import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias(value = "goodsDTO")
public class GoodsDTO {
	String goodsNum;
	String sellerNum;
	String goodsName;
	Long goodsPrice;
	String goodsContent;
	String goodsSize;
	Long goodsStock;
	String goodsCountry;
	String goodsCategory;
	String goodsCompany;
	String modifyNum;
	Date goodsRegiDate;
	String goodsMain;
	Date goodsUptDate;
	Long goodsDeliveryFee;
	String writerNum;
	String goodsMainOrg;  
}
