package myappdemo.ddns.net.app;

import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import myappdemo.ddns.net.app.models.GeoIP;
import myappdemo.ddns.net.app.repo.RequestService;
import myappdemo.ddns.net.app.utils.RawDBDemoGeoIPLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@SpringBootApplication
@Controller
public class AppApplication {
	Logger logger = LoggerFactory.getLogger(AppApplication.class);
	@Autowired
	private RequestService requestService;
	@Autowired
	private RawDBDemoGeoIPLocationService locationService;

	@Value("${spring.application.name}")
	private String appName;

	@GetMapping("")
	public String hello(HttpServletRequest request, Model model){
		logger.warn(request.getSession().getId());
		logger.warn( requestService.getClientIp(request));
		GeoIP location = null;
		try {
			location = locationService.getLocation(requestService.getClientIp(request));
		} catch (AddressNotFoundException e) {
			logger.error("is locale");
			model.addAttribute("location","Local");
			model.addAttribute("appName",appName);
			return  "index";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("location",location.getCity());
		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
