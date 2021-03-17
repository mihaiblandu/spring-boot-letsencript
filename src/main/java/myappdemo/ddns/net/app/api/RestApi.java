package myappdemo.ddns.net.app.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RestApi {

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
        }
        return "No cookies";
    }
    @GetMapping("/simpleCookie")
    public String simpleCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("simpleCookie", "simpleCookie");
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
        //add cookie to response
        response.addCookie(cookie);
        return "OK";
    }
    @GetMapping("/secureCookie")
    public String secureCookie(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("secureCookie", "secureCookie");
        cookie.setMaxAge(60); // expires in one minute
        cookie.setSecure(true);
        //add cookie to response
        response.addCookie(cookie);
        return "OK";
    }

    @GetMapping("/scope")
    public String cookieScope(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("scope", "cookieScope");
        cookie.setMaxAge(60); // expires in one minute
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("https://google.com/");
        //add cookie to response
        response.addCookie(cookie);
        return "OK";
    }
    @GetMapping("/httpOnly")
    public String httpOnly(HttpServletResponse response) {
        // create a cookie
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setMaxAge(60); // expires in one minute
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        //add cookie to response
        response.addCookie(cookie);
        return "OK";
    }
    @GetMapping("/oneCookie")
    public Optional<Cookie> oneCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(c->c.getName().equals("simpleCookie")).findFirst();
        }
        return null;
    }

    @GetMapping("/delete-all")
    public String delete(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(el->{
                        el.setMaxAge(0);
                        response.addCookie(el);
                    });
        }
        return "All are deleted";
    }

}
