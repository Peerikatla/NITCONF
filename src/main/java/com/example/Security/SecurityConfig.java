package com.example.Security;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Autowired
    // private UserDetailsService customUserDetailsService;

    // @Bean
    // PasswordEncoder customPasswordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // AuthenticationProvider
    // customAuthenticationProvider(AuthenticationConfiguration config) throws
    // Exception {
    // DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    // provider.setUserDetailsService(customUserDetailsService);
    // provider.setPasswordEncoder(customPasswordEncoder());
    // return provider;
    // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityConfig.filterChain()");
        return http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/", "/Home").permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/Home", true)
                        .successHandler((request, response, authentication) -> {
                            // Get the authenticated user (CustomUser)
                            CustomUser customUser = (CustomUser) authentication.getPrincipal();

                            // Extract userid from CustomUser
                            Integer userId = customUser.getUserId();

                            // Store userid in session
                            request.getSession().setAttribute("userid", userId);
                            System.out.println("Userid: " + userId);
                            response.sendRedirect("/Home?userId=" + userId);
                        }))
                .logout(logout -> logout
                        .permitAll())
                .build();
    }
}
