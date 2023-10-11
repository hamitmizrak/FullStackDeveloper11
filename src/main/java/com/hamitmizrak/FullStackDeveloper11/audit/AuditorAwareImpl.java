package com.hamitmizrak.FullStackDeveloper11.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// StereoType
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    // get Current Auditor
    @Override
    public Optional<String> getCurrentAuditor() {
        // database ile login girişi yapmış kullanıcı bilgilerini almak
        // org.springframework.security.core.Authentication;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        // Eğer kullanıcı giriş yapmışsa session bilgilerini almak
        if(authentication!=null && authentication.isAuthenticated()){
            System.out.println("Name: "+authentication.getName());
            System.out.println("Principal: "+authentication.getPrincipal());
            return Optional.of(authentication.getName());
        }
        // Eğer sistemde bir kullanıcı giriş  yapmamışsa default olarak HamitM. dönder
        //return Optional.ofNullable(authentication!=null? authentication.getName() : null)
        return Optional.of("HamitM.");
    }
}
