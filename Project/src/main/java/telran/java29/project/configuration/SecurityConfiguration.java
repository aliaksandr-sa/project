package telran.java29.project.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers(HttpMethod.POST, "/reservation/confirm");
		web.ignoring().antMatchers(HttpMethod.POST, "/car/{serial_number}/reservation");
		web.ignoring().antMatchers(HttpMethod.GET, "/car/{serial_number}");
		web.ignoring().antMatchers(HttpMethod.GET, "/car/best");
		web.ignoring().antMatchers(HttpMethod.GET, "/comment");
	}
}
