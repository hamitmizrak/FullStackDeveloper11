package com.hamitmizrak.FullStackDeveloper11.runner;

import com.hamitmizrak.FullStackDeveloper11.business.dto.RegisterDto;
import com.hamitmizrak.FullStackDeveloper11.business.services.IRegisterServices;
import com.hamitmizrak.FullStackDeveloper11.data.entity.RoleEntity;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRegisterRepository;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRoleRepository;
import com.hamitmizrak.FullStackDeveloper11.role.ERole;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// LOMBOK
@RequiredArgsConstructor // Injection

@Log4j2
@Component
public class BlogCommandLineRunner2 implements CommandLineRunner {

    private final IRoleRepository iRoleRepository;
    private final IRegisterRepository iRegisterRepository;
    private final IRegisterServices iRegisterServices;

    private void justOneMailSend() {
        synchronized (this) {
            //ROLES SUPER_ADMIN
            // Dikkat: ROLE_  eklemelisiiiin.
            Long superRoleId = iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERole.SUPER_ADMIN.toString()).build()).getRolesId();
            Long adminRoleId = iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERole.ADMIN.toString()).build()).getRolesId();
            Long writerRoleId = iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERole.WRITER.toString()).build()).getRolesId();
            Long userRoleId = iRoleRepository.save(RoleEntity.builder().rolesId(0L).roleName(ERole.USER.toString()).build()).getRolesId();
            // Multithreading Senkron
       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });*/
            for (long i = 1; i <= 4; i++) {
                //USER
                RegisterDto registerDto = new RegisterDto();
                registerDto.setRegisterNickName("computer" + i);
                registerDto.setRegisterName("Hamit" + i);
                registerDto.setRegisterSurname("Mızrak" + i);
                registerDto.setRegisterEmail("hamitmizrak" + i + "@gmail.com");
                registerDto.setRegisterPassword("Hm4444@.");

                // USERDETAILS
                registerDto.setIsAccountNonExpired(Boolean.TRUE);
                registerDto.setIsCredentialsNonExpired(Boolean.TRUE);
                registerDto.setIsAccountNonLocked(Boolean.FALSE);
                registerDto.setIsEnabled(Boolean.TRUE);

                //KAYDET
                iRegisterServices.registerServiceCreate(i, registerDto);
                System.out.println(registerDto);
                System.out.println("User Eklendi");
            } //end for
        }
    }

    @Override
    public void run(String... args) throws Exception {
        // Uygulama başladığında çalışmasını istediğimiz komutlar
        System.out.println("CommandLineRunner Çalıştı Sade");
        log.info("CommandLineRunner Çalıştı Sade");
        justOneMailSend();
    }

} //end class

