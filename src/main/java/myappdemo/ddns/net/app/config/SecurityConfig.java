package myappdemo.ddns.net.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        * Cross-Site Request Forgery is an attack that forces
        * a user to execute unwanted actions in an application
        * they’re currently logged into.
        * */
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        /*
        * Content Security Policy (CSP) is an added layer of security that helps mitigate XSS
        * (cross-site scripting) and data injection attacks.
         * */
/*        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");*/
       /*
       TLS/SSL certificates used to be expensive, and HTTPS was considered slow.
       Machines have become much faster, solving the performance problem, and
       Let’s Encrypt provides free TLS certificates.
       These two developments have changed the game and caused TLS to become mainstream.
       * */
        http
                .requiresChannel().anyRequest().requiresSecure();
    }

}
