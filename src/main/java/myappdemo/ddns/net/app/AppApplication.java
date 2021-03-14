package myappdemo.ddns.net.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@Controller
public class AppApplication {
	@GetMapping("")
	public String hello( ){
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
