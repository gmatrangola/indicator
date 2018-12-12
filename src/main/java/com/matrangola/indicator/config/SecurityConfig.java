package com.matrangola.indicator.config;

import com.matrangola.indicator.controller.AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                .contextSource()
                .root("dc=matrangola,dc=com")
                .ldif("classpath:users.ldif");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
                .antMatchers("/customers")
                .permitAll().anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);

        http.headers().cacheControl().disable();

//        http.authorizeRequests()
//                .antMatchers("/customers")
//                .permitAll().anyRequest().authenticated();

//        http
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/");
    }

}