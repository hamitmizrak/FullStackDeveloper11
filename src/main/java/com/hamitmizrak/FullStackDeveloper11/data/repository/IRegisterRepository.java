package com.hamitmizrak.FullStackDeveloper11.data.repository;

import com.hamitmizrak.FullStackDeveloper11.data.entity.RegisterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// 3 2 1 8

// CrudRepository
// JpaRepository
// PagingAndSortingRepository
@Repository
public interface IRegisterRepository extends CrudRepository<RegisterEntity,Long> {

    // Delivered Query
    Optional<RegisterEntity> findByRegisterEmail(String email);

    // Login surname
    Optional<RegisterEntity> findByRegisterSurname(String surname);

    /////////////////////////////////
    // ROLES
    // Query: Karmaşık sorgular için kullanırız.
    // @ManyToMany roles==> Register Inner Join Roles
    @Query("select reg from Registers reg join reg.roles rol where rol.roleName = :roleNameParam")
    List<RegisterEntity> findAllByRegisterInJoinRolesRoleName(@Param("roleNameParam") String roleName);

} //end interface
