package com.hamitmizrak.FullStackDeveloper11.data.repository;

import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity,Long> {

    // Delivered Query
    Optional<RegisterEntity> findByRegisterEmail(String email);
} //end interface
