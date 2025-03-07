package com.example.security.section1.Config;

import com.example.security.section1.Exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfig
{
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (requests) -> requests.anyRequest().permitAll());
        http.sessionManagement(
                smc->smc.sessionFixation(sfc->sfc.changeSessionId())
                        .invalidSessionUrl("/invalidSession").maximumSessions(1).maxSessionsPreventsLogin(true)
        );
        http.requiresChannel(
                rcc->rcc.anyRequest().requiresInsecure()  // Only HTTP
        );
        http.csrf(csrfConfig->csrfConfig.disable());
        http.authorizeHttpRequests(
                (requests) -> requests.requestMatchers("/myAccount",
                        "/myBalance",
                        "/myCards","/myLoans").authenticated()
                        .requestMatchers("/contact","/notices","/error","/register","/invalidSession").permitAll());
//        http.formLogin(flc->
//                flc.disable());
        //http.formLogin(flc->flc.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("login?error=true"))
        //http.logout(loc->loc.logoutSuccessUrl("").invalidateHttpSession(true).clearAuthentication(true).deleteCookies("JSESSIONID"))

        http.formLogin(withDefaults());
//        http.httpBasic(withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource)
//    {
//
//       return new JdbcUserDetailsManager(dataSource);
//    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker()
    {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
