package com.hamitmizrak.FullStackDeveloper11.controller.api.impl;

import com.hamitmizrak.FullStackDeveloper11.business.dto.RegisterDto;
import com.hamitmizrak.FullStackDeveloper11.business.services.IRegisterServices;
import com.hamitmizrak.FullStackDeveloper11.controller.api.IRegisterApi;
import com.hamitmizrak.FullStackDeveloper11.data.entity.ForRegisterTokenEmailConfirmationEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API
@RestController
//v1.0.0 1: major version 0: minor 0: patch
@RequestMapping("/register/api/v1.0.0")
@CrossOrigin // CORSS Hatası almamak için
//@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)
//Dış dünyaya açılan kapı
public class RegisterApiImpl implements IRegisterApi<RegisterDto> {

    // INJECTION
    private final IRegisterServices iRegisterServices;


    ///////////////////////////////////////////////////////////////////////////
    // SPEED DATA
    // http://localhost:4444/register/api/v1.0.0/speed/data/10
    @Override
    @GetMapping("/speed/data/{id}")
    public ResponseEntity<List<RegisterDto>> registerApiSpeedData( @PathVariable(name="id")  Long key) {
        return ResponseEntity.ok(iRegisterServices.registerServiceSpeedData(key));
    }

    // DELETE ALL
    // http://localhost:4444/register/api/v1.0.0/delete/all
    @Override
    @GetMapping("/delete/all")
    public ResponseEntity<?> registerApiDeleteAll() {
        return ResponseEntity.ok(iRegisterServices.registerServiceDeleteAll());
    }

    ///////////////////////////////////////////////////////////////////////////
    // CREATE
    // http://localhost:4444/register/api/v1.0.0/create/1
    @Override
    //@PostMapping({"/create","/create/{roles_id}"})
    @PostMapping("/create/{roles_id}")
    public ResponseEntity<?> registerApiCreate(@PathVariable(name="roles_id") Long rolesId,@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(iRegisterServices.registerServiceCreate(rolesId,registerDto)) ;
    }

    // LIST
    // http://localhost:4444/register/api/v1.0.0/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RegisterDto>> registerApiList() {
        return ResponseEntity.ok().body(iRegisterServices.registerServiceList());
    }

    // FIND BY ID
    // http://localhost:4444/register/api/v1.0.0/find/1
    @Override
    @GetMapping("/find/{id}")
    public ResponseEntity<?> registerApiFindById(@PathVariable(name = "id") Long id) {
        //return ResponseEntity.status(200).body(iRegisterServices.registerServiceFindById(id));
        return ResponseEntity.ok(iRegisterServices.registerServiceFindById(id));
    }

    // UPDATE
    // http://localhost:4444/register/api/v1.0.0/update/1
    @Override
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> registerApiUpdate( @PathVariable(name="id") Long id,@Valid @RequestBody RegisterDto registerDto) {
        return ResponseEntity.status(HttpStatus.OK).body(iRegisterServices.registerServiceUpdate(id,registerDto));
    }

    // DELETE BY ID
    // http://localhost:4444/register/api/v1.0.0/delete/1
    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> registerApiDeleteById(@PathVariable(name="id")  Long id) {
        //return new ResponseEntity<>((HttpStatusCode) iRegisterServices.registerServiceDeleteById(id));
        return ResponseEntity.ok(iRegisterServices.registerServiceDeleteById(id));
    }


    /////////////////////////////////////////////////////////
    // EMAIL CONFIRMATION
    //http://localhost:4444/user/api/v1/confirm?token=ca478481-5f7a-406b-aaa4-2012ebe1afb4
    @Override
    @GetMapping("/confirm")
    public ResponseEntity<String> emailTokenConfirmation(@RequestParam("token") String token) {
        Optional<ForRegisterTokenEmailConfirmationEntity> tokenConfirmationEntity = iRegisterServices.findTokenConfirmation(token);
        tokenConfirmationEntity.ifPresent(iRegisterServices::emailTokenConfirmation);
        String html="<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "  <title>Register</title>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "  <style>\n" +
                "    body{\n" +
                "        background-color: black;\n" +
                "        color:white;\n" +
                "    }\n" +
                "  </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "    <p style='padding:4rem;'>Üyeliğiniz Aktif olunmuştur.  <a href='http://localhost:3000'>Ana sayfa için tıklayınız </a></p>\n" +
                "  \n" +
                "</body>\n" +
                "</html>";
        return ResponseEntity.ok(html);
    }

} //end class
