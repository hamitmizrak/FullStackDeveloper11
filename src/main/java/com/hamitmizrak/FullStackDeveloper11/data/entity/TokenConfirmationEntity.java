package com.hamitmizrak.FullStackDeveloper11.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

// LOMBOK
@Data
@AllArgsConstructor
@Builder
@Log4j2
// @SneakyThrows

@Entity
@Table(name="confirmation_token")
// 1(TokenCEntity) - 1(UserEntity)
public class TokenConfirmationEntity extends BaseEntity {

    // TOKEN
    private String token;

    // CONSTRUCTOR PARAMETRESIZ
    public TokenConfirmationEntity() {}

    // CONSTRUCTOR PARAMETRELI  (1-1) için UserEntity eklemelisinnnnnn.
    public TokenConfirmationEntity(RegisterEntity registerEntity) {
        this.userEntity = registerEntity;// Null Pointer Exception almamak için
        this.token= UUID.randomUUID().toString(); //benzersiz bir unique oluştur.
    }

    // @OneToOne (1-1)
    // Her kullanıcın 1 tane tokeni olmalıdır.
    @OneToOne(targetEntity =RegisterEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name="user_id")
    private RegisterEntity userEntity;

}// end class
