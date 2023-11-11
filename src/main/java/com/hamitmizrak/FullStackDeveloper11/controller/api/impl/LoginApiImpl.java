package com.hamitmizrak.FullStackDeveloper11.controller.api.impl;

import com.google.protobuf.Api;
import com.hamitmizrak.FullStackDeveloper11.bean.PasswordEncoderBeanClass;
import com.hamitmizrak.FullStackDeveloper11.controller.api.ILoginApi;
import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRegisterRepository;
import com.hamitmizrak.FullStackDeveloper11.error.ApiResult;
import com.hamitmizrak.FullStackDeveloper11.exception.HamitMizrakException;
import com.hamitmizrak.FullStackDeveloper11.utils.FrontendPortUrl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


import java.util.*;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API
@RestController //API Dış dünyaya açılan kapı
@RequestMapping("/login/api/v1.0.0") //v1.0.0 1: major version 0: minor 0: patch
@CrossOrigin // CORSS Hatası almamak için
//@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)

// Authentication: Kimlik Doğrulama
// Authorization : Kimlik Yetkilendirme
public class LoginApiImpl implements ILoginApi {

    // Injection
    private final IRegisterRepository iRegisterRepository;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;


    // http://localhost:4444/login/api/v1.0.0/authentication
    // Header bilgisini almak => @RequestHeader
    // Basic Authentication   => Authorization
    // @PathVariable => /
    // @RequestParam => ?
    @Override
    @PostMapping("/authentication")
    public ResponseEntity<?> handleAuthenticationData(@RequestHeader(name = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            // 401: Yetkisiz Giriş (UNAUTHORIZED)
            ApiResult apiResult = ApiResult.builder()
                    .status(401)
                    .path("/login/api/v1.0.0/authentication")
                    .message("un-authorized")
                    .build();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }
        log.info(authorization);

        // Dikkat: Basic sonrasında 1 tane boşluk bırakmalısın
        // Basic KlmRk44==
        // Basic sonrasındaki data almak =>  KlmRk44==
        String base64HandleEncoderSplit = authorization.split("Basic ")[1];
        System.out.println(base64HandleEncoderSplit);

        // Sifreleme =>  email:password
        String base64Decoder = new String(Base64.getDecoder().decode(base64HandleEncoderSplit));
        System.out.println(base64Decoder);

        // email:password ayırmak
        String[] arrayEmailPassword = base64Decoder.split(":");
        String email = arrayEmailPassword[0]; //email
        System.out.println(email);
        String password = arrayEmailPassword[1]; //password
        System.out.println(password);

        // Sistemde ilgili email varsa göstersin
        Optional<RegisterEntity> findRegisterEntity = iRegisterRepository.findByRegisterEmail(email);
        if (findRegisterEntity.isPresent() == false) { // Kullanıcı yoksa
            ApiResult apiResult = new ApiResult("/login/api/v1.0.0./authentication", "Kullanıcı bulunamadı", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResult);
        } else if (findRegisterEntity.isPresent() == true && findRegisterEntity.get().getRegisterIsPassive() == false) { // Kullanıcı Kilitli mi ?
            ApiResult apiResult = new ApiResult("/login/api/v1.0.0./authentication", "Kullanıcı kilitli", 401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }

        String passwordHashing = findRegisterEntity.get().getRegisterPassword();
        // Database'deki Hashlenmiş şifre ile kullanıcının girdiği şifre karşılaştırılması
        //.matches(kullanıcıGirdiğiŞifre,databaeŞifre))
        if (!passwordEncoderBeanClass.passwordEncoderMethod().matches(password, passwordHashing)) {
            ApiResult apiResult = new ApiResult("/login/api/v1.0.0./authentication", "unauthorized44", 401);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiResult);
        }

        Map<String, Object> resultRegisterEntity = new HashMap<>();
        resultRegisterEntity.put("name", findRegisterEntity.get().getRegisterName());
        resultRegisterEntity.put("surname", findRegisterEntity.get().getRegisterSurname());
        resultRegisterEntity.put("email", findRegisterEntity.get().getRegisterEmail());
        resultRegisterEntity.put("password", findRegisterEntity.get().getRegisterPassword());
        resultRegisterEntity.put("systemDate", findRegisterEntity.get().getSystemDate());

        return ResponseEntity.ok(resultRegisterEntity);
    }


    // LOGOUT
    // http://localhost:4444/logout
    @GetMapping("/logout")
    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Login sayfasına giriş yapmış kullanıcı
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return ResponseEntity.ok(ApiResult.builder().status(200).message("Çıkış yapıldı").path("/logout").build());
        }
        throw new HamitMizrakException("Çıkış yapılmadı");
    }
} //end class
