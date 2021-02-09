package com.pack.SpringBootSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration //defines bean configuration
@EnableWebSecurity //enable security
//@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//    	return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/resources/**", "/registration").permitAll()
                    .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
        			.antMatchers("/new").hasAnyAuthority("ADMIN", "USER")
        			.antMatchers("/editproduct/**").hasAnyAuthority("ADMIN")
        			.antMatchers("/deleteproduct/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
        			.exceptionHandling().accessDeniedPage("/403");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.inMemoryAuthentication().withUser("senthil").password("abcd").roles("admim");
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//    	auth.userDetailsService(userDetailsService);
    }
}
