package com.example.BloodBankManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private BloodDonorService bloodDonorService;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService()).passwordEncoder(passwordEncoder());
    }

    @SuppressWarnings({ "deprecation", "removal" })
	protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .requestMatchers("/blood-donors/register", "/blood-donors/login").permitAll()
                .requestMatchers("/blood-donors/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/blood-donors/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return email -> {
            Optional<BloodDonor> donor = bloodDonorService.loginDonor(email, "");

            return donor.map(value -> User.withUsername(value.getEmail())
                    .password(value.getPassword())
                    .roles("USER")
                    .build())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        };
    }
}