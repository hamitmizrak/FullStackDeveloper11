package com.hamitmizrak.FullStackDeveloper11.controller.api;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;


// Authentication: Kimlik Doğrulama
// Authorization : Kimlik Yetkilendirme
public interface ILoginApi {

    //  Giris =>  LOGIN Basic Authentication
    public ResponseEntity<?> handleAuthenticationData(String authentication);

    // Cikis =< LOGOUT
    //    // import jakarta.servlet.http.HttpServletRequest;
    //    // import jakarta.servlet.http.HttpServletResponse;
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response );

} //end interface

