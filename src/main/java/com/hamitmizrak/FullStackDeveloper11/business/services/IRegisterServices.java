package com.hamitmizrak.FullStackDeveloper11.business.services;

import com.hamitmizrak.FullStackDeveloper11.data.entity.TokenConfirmationEntity;

import java.util.List;
import java.util.Optional;

// D: Dto
// E: Entity
public interface IRegisterServices <D,E>{

    // INJECTION

    ////////////////////////////////////////////////////////////
    // MODEL MAPPER
    public D entityToDto(E e);
    public E dtoToEntity(D d);

    ////////////////////////////////////////////////////////////
    // SPEED DATA
    public List<D> registerServiceSpeedData(Long key);

    // ALL DELETE
    public String registerServiceDeleteAll();

    ////////////////////////////////////////////////////////////
    // REGISTER C R U D
    // CREATE
    public D registerServiceCreate(D d);

    // LIST
    public List<D> registerServiceList();

    // FIND
    public D registerServiceFindById(Long id);

    // UPDATE
    public D registerServiceUpdate(Long id, D d);

    // DELETE
    public D registerServiceDeleteById(Long id);

    ////////////////////////////////////////////////////////////
    // EMAIL CONFIRMATION
    // EMAIL TOKEN CONFIRMATION
    public void emailTokenConfirmation(TokenConfirmationEntity tokenConfirmationEntity);

    // EMAIL TOKEN FIND
    public Optional<TokenConfirmationEntity> findTokenConfirmation(String token);

} //end interface
