package se.vandingenen.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean    // Password Encryption (encrypterad för databasen)
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired    // Authentication
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/player/**").hasRole("PLAYER")
                        .anyRequest().authenticated()
        )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .defaultSuccessUrl("/success",true)
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                                .logoutSuccessUrl("/")
                )
                .exceptionHandling(
                        configurer -> configurer
                                .accessDeniedPage("/acces-denied")
                );
        return httpSecurity.build();
    }
}
