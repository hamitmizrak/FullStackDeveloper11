package com.hamitmizrak.FullStackDeveloper11.business.dto;

import com.hamitmizrak.FullStackDeveloper11.annotation.AnnotationUniqueEmailAddress;
import com.hamitmizrak.FullStackDeveloper11.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;

// LOMBOK
@Data
@Log4j2
@Builder

// Dikkat: message sonunda boşluk olmasın
// REGISTER
public class RegisterDto extends AuditingAwareBaseDto implements Serializable {

    // SERILEŞTIRME
    public static final Long serialVersionUID=1L;

    // NICKNAME
    @NotEmpty(message = "{register.nickname.validation.constraints.NotNull.message}")
    private String registerNickName;

    // NAME
    @NotEmpty(message = "{register.name.validation.constraints.NotNull.message}")
    private String registerName;

    // SURNAME
    @NotEmpty(message = "{register.surname.validation.constraints.NotNull.message}")
    private String registerSurname;

    // EMAIL
    // Kendi annotation'ımı yazdı
    @AnnotationUniqueEmailAddress
    @NotEmpty(message = "{register.email.validation.constraints.NotNull.message}")
    @Email(message = "{register.email.validation.constraints.regex.message}")
    private String registerEmail;

    // PASSWORD
    // Hm123456@.
    // @JsonIgnore // backentte giden veriyi saklar
    @NotEmpty(message = "{register.password.validation.constraints.NotNull.message}")
    @Size(min = 7, max = 15, message = "{register.password.validation.constraints.MinMax.NotNull.message}")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$", message = "{register.password.pattern.validation.constraints.NotNull.message}")
    private String registerPassword;

    // ACTIVE INACTIVE
    @Builder.Default //default olarak kullanıcı pasif olsun admin bunu aktif yapsın
    private Boolean registerIsPassive=false;

    //parametresiz constructor
    public RegisterDto() {
    }

    //parametreli constructor
    public RegisterDto(String registerNickName, String registerName, String registerSurname, String registerEmail, String registerPassword, Boolean registerIsPassive) {
        this.registerNickName = registerNickName;
        this.registerName = registerName;
        this.registerSurname = registerSurname;
        this.registerEmail = registerEmail;
        this.registerPassword = registerPassword;
        this.registerIsPassive = registerIsPassive;
    }

    // toString
    @Override
    public String toString() {
        return "RegisterDto{" +
                ", id=" + id +
                "registerNickName='" + registerNickName + '\'' +
                ", registerName='" + registerName + '\'' +
                ", registerSurname='" + registerSurname + '\'' +
                ", registerEmail='" + registerEmail + '\'' +
                ", registerPassword='" + registerPassword + '\'' +
                ", registerIsPassive='" + registerIsPassive + '\'' +
                ", systemDate=" + systemDate +
                ", createdUser='" + createdUser + '\'' +
                ", createdDate=" + createdDate +
                ", lastUser='" + lastUser + '\'' +
                ", lastDate=" + lastDate +
                '}';
    } //end toString

} //end class
