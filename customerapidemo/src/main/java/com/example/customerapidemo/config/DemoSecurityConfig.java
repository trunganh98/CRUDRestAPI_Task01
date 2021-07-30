package com.example.customerapidemo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("securityDataSource")
    private DataSource securityDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(securityDataSource);
    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/employees/showForm*").hasAnyRole("MANAGER","ADMIN")
////                .antMatchers("/employees/save*").hasAnyRole("MANAGER","ADMIN")
////                .antMatchers("/employees/delete").hasRole("ADMIN")
////                .antMatchers("/employees/**").hasRole("EMPLOYEE")
////                .antMatchers("/resources/**").permitAll()
////                .and()
////                .formLogin()
////                    .loginPage("/showMyLoginPage")
////                    .loginProcessingUrl("/authenticateTheUser")
////                    .permitAll()
////                .and()
////                .logout().permitAll()
////                .and()
////                .exceptionHandling().accessDeniedPage("/access-denied");
//
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"api/cus/**").hasAnyRole("ADMIN","EMPLOYEE","MANAGER")
//                .antMatchers(HttpMethod.PUT,"api/cus/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"api/cus/**").hasAnyRole("ADMIN","MANAGER")
//                .antMatchers(HttpMethod.DELETE,"api/cus/**").hasRole("ADMIN")
//                .and()
//                .httpBasic()
//                .and()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "api/cus/**").hasAnyRole("ADMIN", "EMPLOYEE", "MANAGER")
                .antMatchers(HttpMethod.PUT, "api/cus/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "api/cus/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers(HttpMethod.DELETE, "api/cus/**").hasRole("ADMIN")
                .anyRequest().permitAll();
    }
}
