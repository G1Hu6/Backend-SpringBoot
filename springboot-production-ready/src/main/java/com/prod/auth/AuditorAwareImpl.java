package com.prod.auth;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.ReactiveAuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    // Use Spring-Security to get current user who made changes in database
    @Override
    public Optional<String> getCurrentAuditor() {
        // get Spring-Security content
        // get authentication
        // get the principle
        // get the username
        return Optional.of("King SuperV");
    }
}
