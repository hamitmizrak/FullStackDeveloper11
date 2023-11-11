package com.hamitmizrak.FullStackDeveloper11.data.repository;

import com.hamitmizrak.FullStackDeveloper11.data.entity.TokenConfirmationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokenRepository extends CrudRepository<TokenConfirmationEntity,Long> {

    // Delivered Query
   Optional<TokenConfirmationEntity>  findTokenConfirmationEntityByToken(String token);
}
