package com.example.security.section1.Events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents
{
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent authenticationSuccessEvent)
    {
        log.info("Login SuccessFul for the user:{}",authenticationSuccessEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent authenticationFailureEvent)
    {
        log.error("Login fail for the user:{}: due to {}",authenticationFailureEvent.getAuthentication().getName(),
                authenticationFailureEvent.getException().getMessage());
    }
}
