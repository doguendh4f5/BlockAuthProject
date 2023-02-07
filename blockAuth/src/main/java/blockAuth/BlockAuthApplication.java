package blockAuth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@MapperScan(value = {"blockAuth"})
public class BlockAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockAuthApplication.class, args);
	}
	
	@RequestMapping("/")
	public String index() {
		return "thymeleaf/index";
	}

}
