package com.hamitmizrak.FullStackDeveloper11.business.services;

import java.util.List;

// D: Dto
// E: Entity
public interface IRoleRegisterService<D,E> {

    // MODEL MAPPER
    public E DtoToEntity(D d);

    public D EntityToDto(E e);

    ////////////////////////////////////////////////////////////////////////////
    // ROLE LIST
    public List<D> roleList();

    // ROLE CREATE
    public D roleCreate(D d);

    // ROLE FIND
    public D roleFind(Long id);

    // ROLE UPDATE
    public D roleUpdate(Long id, D d);

    // ROLE DELETE
    public D roleDelete(Long id);

    ////////////////////////////////////////////////////////////////////////////
    //Email adresinde kullanı rolünü bulmak
    public D registerEmailFindRole(String emailAddress);

} //end Class

