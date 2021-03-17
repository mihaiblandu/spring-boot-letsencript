package myappdemo.ddns.net.app.api;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.record.Location;
import myappdemo.ddns.net.app.models.GeoIP;
import myappdemo.ddns.net.app.utils.RawDBDemoGeoIPLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class GeoIPTestController {

    @Autowired
    private RawDBDemoGeoIPLocationService locationService;

    
    @GetMapping("/GeoIPTest")
    public GeoIP getLocation(
      @RequestParam(value="ipAddress", required=true) String ipAddress
    ){

        try {
            return locationService.getLocation(ipAddress);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (GeoIp2Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}