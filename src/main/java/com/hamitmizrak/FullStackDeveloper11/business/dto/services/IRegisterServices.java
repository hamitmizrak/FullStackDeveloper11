package com.hamitmizrak.FullStackDeveloper11.business.dto.services;

import java.util.List;

// D: Dto
// E: Entity
public interface IRegisterServices <D,E>{

    // INJECTION

    // Model Mapper
    public D entityToDto(E e);
    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D registerServiceCreate(D d);

    // LIST
    public List<D> registerServiceList();

    // FIND
    public D registerServiceFindById(D d);

    // UPDATE
    public D registerServiceUpdate(Long id, D d);

    // DELETE
    public D registerServiceDeleteById(Long id);

} //end interface