package com.hamitmizrak.FullStackDeveloper11.business.services;

import com.hamitmizrak.FullStackDeveloper11.data.entity.ForRegisterTokenEmailConfirmationEntity;

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
    // CREATE ( Rolles and Object )
    public D registerServiceCreate(Long rolesId, D d);

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
    public void emailTokenConfirmation(ForRegisterTokenEmailConfirmationEntity tokenConfirmationEntity);

    // EMAIL TOKEN FIND
    public Optional<ForRegisterTokenEmailConfirmationEntity> findTokenConfirmation(String token);



} //end interface
