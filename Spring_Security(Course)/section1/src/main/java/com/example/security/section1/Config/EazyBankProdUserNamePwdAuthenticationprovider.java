package com.example.security.section1.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class EazyBankProdUserNamePwdAuthenticationprovider implements AuthenticationProvider
{
    @Autowired
    private EazyBankUserDetailsService eazyBankUserDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String pwd=authentication.getCredentials().toString();
        UserDetails userDetails=eazyBankUserDetailsService.loadUserByUsername(username);
    if(passwordEncoder.matches(pwd,userDetails.getPassword()))
    {
        return new UsernamePasswordAuthenticationToken(username,pwd,userDetails.getAuthorities());
    }
    else {
        throw new BadCredentialsException("Invalid password");
    }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
