package com.mmede.MangoDevTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
	private SpringDataJpaUserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
			.userDetailsService(this.userDetailsService)
				.passwordEncoder(User.PASSWORD_ENCODER);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/built/**", "/main.css").permitAll()
        .anyRequest().authenticated()
        .and()
    .formLogin()
        .defaultSuccessUrl("/", true)
        .permitAll()
        .and()
    .httpBasic()
        .and()
    .csrf().disable()
    .logout()
        .logoutSuccessUrl("/");

    }
}