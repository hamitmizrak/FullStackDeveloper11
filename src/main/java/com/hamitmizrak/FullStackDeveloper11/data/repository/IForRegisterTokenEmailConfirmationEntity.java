package com.hamitmizrak.FullStackDeveloper11.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IForRegisterTokenEmailConfirmationEntity extends CrudRepository<com.hamitmizrak.FullStackDeveloper11.data.entity.ForRegisterTokenEmailConfirmationEntity,Long> {

    // Delivered Query
   Optional<com.hamitmizrak.FullStackDeveloper11.data.entity.ForRegisterTokenEmailConfirmationEntity>  findTokenConfirmationEntityByToken(String token);
}
