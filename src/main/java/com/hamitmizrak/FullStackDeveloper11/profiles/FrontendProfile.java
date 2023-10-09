package com.hamitmizrak.FullStackDeveloper11.profiles;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// @Component: FrontendProfile nesnesi Spring nesnesi olması için
@Component
@Profile("frontend")
public class FrontendProfile implements IChooiseProfile{
    @Override
    public String message(String name) {
        return FrontendProfile.class+  " Profile: "+name;
    }
}
