package com.hamitmizrak.FullStackDeveloper11.controller.api;

import org.springframework.http.ResponseEntity;

import java.util.List;

// D: Dto
public interface IRegisterApi<D> {

    // INJECTION

    // C R U D
    // CREATE
    public ResponseEntity<?> registerApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>> registerApiList();

    // FIND
    public ResponseEntity<?> registerApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?> registerApiUpdate(Long id, D d);

    // DELETE
    public ResponseEntity<?> registerApiDeleteById(Long id);

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    public ResponseEntity<List<D>> registerApiSpeedData(Long key);

    // ALL DELETE
    public ResponseEntity<?> registerApiDeleteAll();

} //end interface
