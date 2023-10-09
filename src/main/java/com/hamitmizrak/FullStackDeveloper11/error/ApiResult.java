package com.hamitmizrak.FullStackDeveloper11.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;
import java.util.Map;

// LOMBOK
@Data

// Springteki Frameworkları default errorları kendimize göre handling(yakalama)
// Jackson: Objeyi json'a çevirir.
// Backentte null değer gitmesine izin verme
@JsonInclude(JsonInclude.Include.NON_NULL) // peki null gidiyorsa o veriyi gösterme
public class ApiResult {

    // Global Variable  S - P M - E V - S
    private Long status;
    private String path;
    private String message;
    private String error;
    private Date systemDate=new Date(System.currentTimeMillis());
    private Map<String,String> validationErrors;

    // parametresiz constructor
    public ApiResult() {
    }

    //parametreli constructor
    public ApiResult(Long status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
    }

    public ApiResult(Long status, String path, String message, String error) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.error = error;
    }

} //end class
