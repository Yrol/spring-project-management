package com.yrol.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Authentication using the In-memory as the datasource.
 * */
@Configuration
@EnableWebSecurity
@Profile("dev-jdbc")
public class SecurityConfigurationInMemory {

    /**
     * Password encryption using BCrypt
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * User Authentication - In-memory
     * Using the encrypted(BCrypt) passwords generated via: https://bcrypt-generator.com/
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user1")
                .password("$2a$12$lyybDchRgwKOVyppgcmeIehlpe0Hgo8.XgNFjLyvFMnSpAjTlDBAi") // pass123
                .roles("USER")
                .and()
                .withUser("user2")
                .password("$2a$12$4/WSenbfyWyFZMAPsz/Nu.fqBM5rVTn6W/tjQxnWSRdkF6mxXrWv.") // pass1234
                .roles("USER")
                .and()
                .withUser("admin")
                .password("$2a$12$6AwjQfZVr9EUfw/4bQE1/eDSXfSkCWrNit6LIWv.xNjLnUm44edxO") // admin1234
                .roles("ADMIN").and().and().build();

    }

    /**
     * User Authorization mapping. Ex: only ADMIN users can create new projects and Employees
     * */
    @Bean
    public SecurityFilterChain authorizationManager(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/login*").permitAll()
                .antMatchers("/h2-console*").permitAll()
                .antMatchers("/").authenticated().and().formLogin();
        return http.build();
    }

}
