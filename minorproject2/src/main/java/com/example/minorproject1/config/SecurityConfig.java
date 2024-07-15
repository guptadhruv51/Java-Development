package com.example.minorproject1.config;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.models.Authority;
import com.example.minorproject1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
    @Bean
    public AuthenticationManager authenticationManager(UserService userService, PasswordEncoder passwordEncoder)
    {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf->csrf.disable())
        .authorizeHttpRequests(

                (authz)->authz.
                        requestMatchers("/students/admin/**").hasAuthority(Authority.admin.name())
                        .requestMatchers("/students/**").hasAuthority(Authority.admin.name())
                        .requestMatchers("/admin/**").hasAuthority(Authority.admin.name())
                        .requestMatchers(HttpMethod.GET,"/books/**").hasAnyAuthority(Authority.admin.name(), Authority.student.name())
                        .requestMatchers("/books/**").hasAuthority(Authority.admin.name())
                        .requestMatchers("transaction/admin/**").hasAuthority(Authority.admin.name())
                        .requestMatchers("/transaction/**").hasAuthority(Authority.student.name())
                        .requestMatchers("/**").permitAll()

        ).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }


}
