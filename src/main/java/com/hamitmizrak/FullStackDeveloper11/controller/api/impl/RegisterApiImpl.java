package com.hamitmizrak.FullStackDeveloper11.controller.api.impl;

import com.hamitmizrak.FullStackDeveloper11.business.dto.RegisterDto;
import com.hamitmizrak.FullStackDeveloper11.business.services.IRegisterServices;
import com.hamitmizrak.FullStackDeveloper11.controller.api.IRegisterApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

@RestController
//v1.0.0 1: major version 0: minor 0: patch
@RequestMapping("/register/api/v1.0.0")
@CrossOrigin // CORSS Hatası almamak için
//@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)
//Dış dünyaya açılan kapı
public class RegisterApiImpl implements IRegisterApi<RegisterDto> {

    // INJECTION
    private final IRegisterServices iRegisterServices;

    // CREATE
    @Override
    public ResponseEntity<?> registerApiCreate(@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(iRegisterServices.registerServiceCreate(registerDto)) ;
    }

    // LIST
    @Override
    public ResponseEntity<List<RegisterDto>> registerApiList() {
        return ResponseEntity.ok().body(iRegisterServices.registerServiceList());
    }

    // FIND BY ID
    @Override
    public ResponseEntity<?> registerApiFindById(Long id) {
        return ResponseEntity.status(200).body(iRegisterServices.registerServiceFindById(id));
    }

    // UPDATE
    @Override
    public ResponseEntity<?> registerApiUpdate(Long id,@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(iRegisterServices.registerServiceUpdate(id,registerDto));
    }

    // DELETE BY ID
    @Override
    public ResponseEntity<?> registerApiDeleteById(Long id) {
        return new ResponseEntity<>((HttpStatusCode) iRegisterServices.registerServiceDeleteById(id));
    }

    ///////////////////////////////////////////////////////////////////////////
    // SPEED DATA
    @Override
    public ResponseEntity<List<RegisterDto>> registerApiSpeedData(Long key) {
        return ResponseEntity.ok(iRegisterServices.registerServiceSpeedData(key));
    }

    // DELETE ALL
    @Override
    public ResponseEntity<?> registerApiDeleteAll() {
          return ResponseEntity.ok(iRegisterServices.registerServiceDeleteAll());
    }

} //end class
