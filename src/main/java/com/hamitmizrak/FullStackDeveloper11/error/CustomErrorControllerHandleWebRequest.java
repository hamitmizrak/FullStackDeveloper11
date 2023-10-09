package com.hamitmizrak.FullStackDeveloper11.error;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor

// REST
@RestController
@CrossOrigin
//@CrossOrigin(origins = FrontendPortUrl.REACT_FRONTEND_PORT_URL)
public class CustomErrorControllerHandleWebRequest implements ErrorController {

    // ErrorController (implements)
    // ErrorAttributes (Injection)
    // WebRequest
    // FieldError

    // Global Variable
    private ApiResult apiResult;

    // Global Variable  S - P M - S
    private Long status;
    private String path;
    private String message;
    private Map<String,String> validationErrors;

    // INJECTION
    /*
    // 1.YOL (Field Inhjection)
    @Autowired
    private final ErrorAttributes errorAttributes;
    */

    /*
    // 2.YOL (Constructor Injection)
    private final ErrorAttributes errorAttributes;
    @Autowired
    public CustomErrorControllerHandleWebRequest(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    */

    // 3.YOL (Constructor Lombok Injection: @RequiredArgsConstructor)
    private final ErrorAttributes errorAttributes;


    // http://localhost:4444/error
    @GetMapping("/error")
    public ApiResult springErrorOwnHandleMethod(WebRequest webRequest){

        // Spring>=2.3
        // Springten gelen mesajları ve hataları almak
        Map<String,Object> attributes=errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                )
        ); //end attributes

        // Springten gelen verileier almak
        status= (Long) attributes.get("status");
        message= (String) attributes.get("message");
        path= (String) attributes.get("path");

        // Api Result
        // Long status, String path, String message
        apiResult=new ApiResult(status,path,message);

        // Eğer Spring'ten gelen hata var ise
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrorList= (List<FieldError>) attributes.get("error");
            validationErrors=new HashMap<>();
            //Bütün yakaladığımız hataları validationError ekle
            for (FieldError error :fieldErrorList){
                validationErrors.put(error.getField(),error.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationErrors);
        }
        return apiResult;
    } //end ApiResult

} //end class
