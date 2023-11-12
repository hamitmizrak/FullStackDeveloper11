package com.hamitmizrak.FullStackDeveloper11.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// NOT: interface için önemli bilgiler
// 1-) interface extends ile başka bir interface ekleyebilirsin. =>
// public interface IProfileHeaderApp extends IModelMapperService

// 2-) interface abstract ekleyerek implements eden class bütün metotları eklemez. =>
// abstract public interface IProfileHeaderApp

// D: Dto
public interface IRoleRegisterApi<D> {

    // ROLES CREATE
    public ResponseEntity<?> roleCreate(D d);

    // ROLES LIST
    public ResponseEntity<List<D>> roleList();

    // FIND
    ResponseEntity<?> roleApiFindById(Long id);

    // UPDATE
    ResponseEntity<?> roleUpdate(Long id, D d);

    ////////////////////////////////////////////////////////////////////////
    //Email adresinde kullanı rolünü bulmak
    ResponseEntity<?> registerEmailFindRole(String emailAddress);

    // ROLE DELETE
    ResponseEntity<?> roleDelete(Long id);

} // end class