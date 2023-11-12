package com.hamitmizrak.FullStackDeveloper11.business.dto;

import com.hamitmizrak.FullStackDeveloper11.annotation.AnnotationUniqueRoleName;
import com.hamitmizrak.FullStackDeveloper11.audit.AuditingAwareBaseDto;
import com.hamitmizrak.FullStackDeveloper11.role.ERole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

// LOMBOK
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
// @SneakyThrows
public class RoleDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final long serialVersionUID=1L;

    // ROLE ID
    private Long roleId;

    // ROLE NAME
    // Eğer Bir kullanıcı Admin belirlememişse Bu kullanıcı USER olduk
    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @AnnotationUniqueRoleName // Kendi Annotation RolName yazdım
    @Builder.Default
    private String roleName= ERole.USER.toString(); //Default olarak User

} //end class
