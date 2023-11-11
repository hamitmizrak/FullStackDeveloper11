package com.hamitmizrak.FullStackDeveloper11.data.entity;

import com.hamitmizrak.FullStackDeveloper11.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor

// ENTITY
@Entity
@Table(name = "registers")
public class RegisterEntity extends AuditingAwareBaseEntity implements Serializable {

    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Id   // ID : import jakarta.persistence.Id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    protected  Long registerId;

    // Global Variable (6)
    // Dikkat: message sonunda boşluk olmasın
    // unique = true,
    @Column(name = "register_nick_name",nullable = true, updatable = false,insertable = true,length = 100)
    private String registerNickName;

    @Column(name = "register_name",columnDefinition = "varchar(255) default 'adınızı girmediniz'")
    private String registerName;

    @Column(name = "register_surname")
    private String registerSurname;

    @Column(name = "register_email")
    private String registerEmail;

    @Column(name = "register_password")
    private String registerPassword;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date systemDate;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // USER DETAILS (Mail Confirmation)
    // @Embedded
    // @Embeddable
    // @EmbeddedId
    @Embedded
    private UserDetailsEmbeddable userDetailsEmbeddable=new UserDetailsEmbeddable();

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

} //end class
