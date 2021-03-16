package myappdemo.ddns.net.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;


@SpringBootApplication
@Controller
public class AppApplication {
	Logger logger = LoggerFactory.getLogger(AppApplication.class);
	@Autowired
	private RequestService requestService;
	@GetMapping("")
	public String hello(HttpServletRequest request){
		logger.warn(request.getSession().getId());
		logger.warn( requestService.getClientIp(request));
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
