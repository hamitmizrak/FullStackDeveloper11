package com.hamitmizrak.FullStackDeveloper11.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

// LOMBOK
@Getter
@Setter

// Embeddable
@Embeddable
public class UserDetailsEmbeddable {

    // USER DETAILS
    // Kullanıcı başlangıçta kilitli yani sisteme giremezsin
    @Column(name = "locked")
    private Boolean isAccountNonLocked=false;

    // Kullanıcını Hesap süresi Doldu mu ?
    @Column(name = "account_expired")
    private Boolean isAccountNonExpired=true;

    // Kulllanıcının Bilgi Süresi Doldu mu?
    @Column(name = "credentials_expired")
    private Boolean isCredentialsNonExpired=true;

    // Kullanıcı Aktif mi ? pasif mi
    @Column(name = "enabled")
    private Boolean isEnabled=true;

} //end class UserDetailsEmbeddable
