package com.github.iceant.point.assetshub.webui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login","/logout", "/static/**", "/webjars/**", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().mvcMatchers("/favicon.ico");
    }

    ////////////////////////////////////////////////////////////////////////////////
    ////

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        PasswordEncoder passwordEncoder = passwordEncoder();
        String password = passwordEncoder.encode("password");

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
        userDetailsManager.setEnableAuthorities(true);
        userDetailsManager.setEnableGroups(true);
        userDetailsManager.setDataSource(dataSource);
        if(!userDetailsManager.userExists("user")){
            userDetailsManager.createUser(User.withUsername("user").password(password)
                    .roles("USER").build());
        }
        if(!userDetailsManager.userExists("admin")){
            userDetailsManager.createUser(User.withUsername("admin").password(password)
                    .roles("USER", "ADMIN").build());
        }
        return userDetailsManager;
    }
}