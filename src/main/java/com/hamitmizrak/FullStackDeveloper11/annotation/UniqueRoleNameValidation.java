package com.hamitmizrak.FullStackDeveloper11.annotation;

import com.hamitmizrak.FullStackDeveloper11.data.repository.IRoleRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

// LOMBOK
@RequiredArgsConstructor

// <A,T> A=Annotation T=Type
public class UniqueRoleNameValidation implements ConstraintValidator<AnnotationUniqueRoleName,String> {

    //Injection
    private final IRoleRepository iRoleRepository;

    // Database email bulunan  kullanıcıyı var mı yok mu ?
    @Override
    public boolean isValid(String rolName, ConstraintValidatorContext constraintValidatorContext) {
        //UserEntity userEntity= iUserRepository.findByEmail(email).orElseThrow( ()->new NotFoundException(email+" bulunmadı"));
       Boolean  rolesFind= iRoleRepository.findByRoleName(rolName).isPresent();
        // Eğer böyle bir email varsa return false döndürsün
        System.out.println();
        if(rolesFind){
            return false;
        }
        return true;
    } // end  isValid
} //end class
