package com.hamitmizrak.FullStackDeveloper11.controller.api.impl;

import com.hamitmizrak.FullStackDeveloper11.business.dto.RoleDto;
import com.hamitmizrak.FullStackDeveloper11.business.services.IRoleRegisterService;
import com.hamitmizrak.FullStackDeveloper11.controller.api.IRoleRegisterApi;
import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import com.hamitmizrak.FullStackDeveloper11.data.repository.IRegisterRepository;
import com.hamitmizrak.FullStackDeveloper11.error.ApiResult;
import com.hamitmizrak.FullStackDeveloper11.role.ERole;
import com.hamitmizrak.FullStackDeveloper11.utils.FrontendPortUrl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// REST
@RestController
@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/role/api/v1.0.0")
public class RoleRegisterApiImpl implements IRoleRegisterApi<RoleDto> {

    // INJECTION
    private final IRoleRegisterService iRolesUserService;
    private final IRegisterRepository iRegisterRepository;

    // ERROR
    private ApiResult apiResult;


    // ROLES CREATE
    // http://localhost:4444/roles/api/v1.0.0/create
    @PostMapping("/create")
    @Override
    public ResponseEntity<?> roleCreate(@Valid @RequestBody RoleDto roleDto) {
        //Sisteme olan kullancılar
        /**
         * Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         * if(authentication!=null && authentication.isAuthenticated()){
         * System.out.println(authentication.getName());
         * System.out.println(authentication.getPrincipal());
         * }*/
        return ResponseEntity.ok(iRolesUserService.roleCreate(roleDto));
    }

    // ROLES LIST
    // http://localhost:4444/roles/api/v1.0.0/list
    @Override
    @GetMapping("/list")
    public ResponseEntity<List<RoleDto>> roleList() {
        return ResponseEntity.ok(iRolesUserService.roleList());
    }

    // FIND
    // http://localhost:4444/roles/api/v1.0.0/find
    // http://localhost:4444/roles/api/v1.0.0/find/0
    // http://localhost:4444/roles/api/v1.0.0/find/-1
    // http://localhost:4444/roles/api/v1.0.0/find/1
    @Override
    @GetMapping({"/find", "/find/{id}"})
    public ResponseEntity<?> roleApiFindById(@PathVariable(name = "id", required = false) Long id) {
        if (id == null) {
            log.error("API => 404 NOT FOUND");
            //return ResponseEntity.notFound().build();
        } else if (id == 0) {
            log.error("API => 400 BAD REQUEST");
            apiResult = new ApiResult("localhost:2222/blog/api/v1.0.0/blog/0" , "Kötü istek", "Bad Request",400);
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body(apiResult);
        } else if (id < 0) {
            log.error("API => 401 UNAUTHROZED");
            apiResult = ApiResult.builder()
                    .error("unAuthorized")
                    .message("Yetkisiz Giriş")
                    .path("localhost:2222/blog/api/v1.0.0/blog/-1")
                    .status(401)
                    .build();
            return ResponseEntity.status(401).body(apiResult);
        }
        System.out.println(iRolesUserService.roleFind(id));
        return ResponseEntity.ok(iRolesUserService.roleFind(id));
    }


    // UPDATE
    // http://localhost:4444/roles/api/v1.0.0/update/1
    @Override
    @PutMapping({"/update", "/update/{id}"})
    public ResponseEntity<?> roleUpdate(@PathVariable(name = "id", required = false) Long id, @Valid @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(iRolesUserService.roleUpdate(id, roleDto));
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    // Email adresinden kullanıcı rolünü bulmak
    // http://localhost:4444/roles/api/v1.0.0/roles/hamitmizrak1@gmail.com
    @Override
    @GetMapping("/roles/{email}")
    public ResponseEntity<?> registerEmailFindRole(@PathVariable(name="email") String emailAddress) { //@RequestParam
        return ResponseEntity.ok(iRolesUserService.registerEmailFindRole(emailAddress));
    }


    // DELETE
    // http://localhost:4444//role/api/v1.0.0/delete/5

    @Override
    @DeleteMapping({"/delete", "/delete/{id}"})
    public ResponseEntity<?> roleDelete(@PathVariable(name = "id", required = false) Long id) {
       // System.out.println(iBlogRepository.mySpecialBlogList());

        RoleDto roleName= (RoleDto) iRolesUserService.roleFind(id);

        StringBuilder stringBuilder=new StringBuilder();
        List<RegisterEntity> list= iRegisterRepository.findAllByRegisterInJoinRolesRoleName(roleName.getRoleName()); // "USER" ERole.USER.toString()
        list.forEach((temp)->{
            System.out.println(temp);
            stringBuilder.append(temp.getRegisterEmail()+" ");
        });
        if(list.size()!=0){
            apiResult = new ApiResult("localhost:4444/roles/api/v1.0.0/delete" , "Bu Rolü silemezsiniz." +"Öncelikle Kullanıcılardan, "+stringBuilder+ "Silmelisin", "Bad Request",400);
            //return ResponseEntity.badRequest().build();
            return  ResponseEntity.status(400).body(apiResult);
        }

        return  ResponseEntity.ok(iRolesUserService.roleDelete(id));
    } //end delete

} // end class