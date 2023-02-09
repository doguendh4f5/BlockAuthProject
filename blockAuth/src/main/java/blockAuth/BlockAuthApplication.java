package blockAuth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import blockAuth.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
@Controller
@MapperScan(value = {"blockAuth"})
public class BlockAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockAuthApplication.class, args);
	}
	
	@Autowired
	CookieService cookieService;
	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model) {
		cookieService.execute(request, model);
		return "thymeleaf/index";
	}
	
	@Bean
	// 암호화 모듈
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// PasswordEncoder passwordEncoder 
	//     

}
