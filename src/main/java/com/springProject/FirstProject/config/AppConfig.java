package com.springProject.FirstProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class AppConfig {

    // create user and login using java code in memory
    // @Bean
    // public UserDetailsService userDetailsService() {

    // UserDetails user1 = User.builder().username("rohit")
    // .password(passwordEncoder().encode("rohit")).roles("ADMIN").build();

    // UserDetails user2 = User.builder().username("ram")
    // .password(passwordEncoder().encode("ram")).roles("ADMIN").build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,
    // user2);
    // return inMemoryUserDetailsManager;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
