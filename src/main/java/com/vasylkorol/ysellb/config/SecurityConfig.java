package com.vasylkorol.ysellb.config;
import com.vasylkorol.ysellb.service.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final CustomUserDetailsServiceImpl customUserDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .antMatchers("/api/admin/**").hasRole("ADMIN")
                        .antMatchers("/api/auth/**","/api/home").permitAll()
                        .antMatchers("/api/message/**").hasAnyRole("USER","ADMIN")
                        .antMatchers("/api/mails/activation/**").hasRole("NOT_CONFIRMED_USER")
                        .anyRequest()
                        .hasAnyRole("USER", "ADMIN")
                        .and()
                )
                .userDetailsService(customUserDetailsService)

                .formLogin().loginPage("/api/auth/login")
                    .loginProcessingUrl("/process_login")
                    .defaultSuccessUrl("/api/home",true)
                    .failureUrl("/api/auth/login?error")
                    .permitAll()
                .and()
                .logout((logout) -> logout.permitAll());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
         return (web) -> web.ignoring();
    }

}

