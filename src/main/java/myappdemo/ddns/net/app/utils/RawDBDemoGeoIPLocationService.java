package myappdemo.ddns.net.app.utils;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.model.IspResponse;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Subdivision;
import myappdemo.ddns.net.app.models.GeoIP;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

@Service
public class RawDBDemoGeoIPLocationService {
    private DatabaseReader dbReader;
    private DatabaseReader ans;

    public RawDBDemoGeoIPLocationService() throws IOException {
        File database = new File("./GeoLite2-ASN_20210316/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
        ans = new DatabaseReader.Builder(new File("./GeoLite2-ASN_20210316/GeoLite2-City.mmdb")).build();
    }

    public GeoIP getLocation(String ip)
      throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        System.out.println(dbReader.country(ipAddress).getCountry().getName());
        CityResponse response = dbReader.city(ipAddress);
        System.out.println(ans.country(ipAddress));
        System.out.println(response.getLocation());
        System.out.println(response.getPostal());
        response.getSubdivisions().forEach((Subdivision el)-> el.getNames().forEach((k,v)->{
            System.out.print(k);
            System.out.println(" " + v);
        } ));
        CountryResponse c = ans.country(ipAddress);
        String cityName = response.getCity().getName();
        String latitude =
          response.getLocation().getLatitude().toString();
        String longitude =
          response.getLocation().getLongitude().toString();
        return new GeoIP(ip, cityName, latitude, longitude);
    }
}