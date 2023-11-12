package com.hamitmizrak.FullStackDeveloper11.data.entity;

import com.hamitmizrak.FullStackDeveloper11.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// LOMBOK
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor

// ENTITY
@Entity(name= "Registers") // Sql JOIN için yazdım
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
    // ROLES
    // ROLE ENTITIY
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "register_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
    //2.YOL
	/*
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
	joinColumns = {
	            @JoinColumn(name = "user_id",referencedColumnName = "user_id")},
	            inverseJoinColumns = {
	                            @JoinColumn(name = "roles_id",referencedColumnName = "roles_id")
	                            }
	             )
	 private List<RoleEntity> roles;
	   */


} //end class
