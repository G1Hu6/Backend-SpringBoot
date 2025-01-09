package com.security.configs;

import com.security.filters.JwtAuthFilter;
import com.security.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{
        return httpSecurity
                //.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.loginPage("/new_login.html"))
                .authorizeHttpRequests(auth -> auth
                                //.anyRequest().authenticated()
                                 .requestMatchers("/posts","/auth/**","/home.html").permitAll()
                                 .requestMatchers("/posts/**").permitAll()
                                 //.requestMatchers("/posts/**").hasAnyRole("ADMIN")
                                 //.anyRequest().authenticated()
                                //.requestMatchers("/posts/**").permitAll()
                                //.requestMatchers("/posts").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig-> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .oauth2Login(
//                        oauth2login-> oauth2login
//                                .failureUrl("/login?error=true")
//                                .successHandler(oAuth2SuccessHandler)
//                )
                .build();

    }

//    @Bean
//    UserDetailsService muInMemoryUserDetailsService(){
//        UserDetails normalUser = User.builder()
//                .username("pranav")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails adminUser = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
