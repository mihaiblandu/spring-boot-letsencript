package myappdemo.ddns.net.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppApplication {
	@GetMapping("")
	public String hello(){
		return "Hello in Https";
	}
	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
