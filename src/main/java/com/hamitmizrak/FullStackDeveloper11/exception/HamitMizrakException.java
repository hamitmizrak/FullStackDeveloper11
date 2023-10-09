package com.hamitmizrak.FullStackDeveloper11.exception;

// 201 : oluşturuldu (Created)
// 400 : Kötü istek (Bad Request)
// 401 : Yetkisiz Giriş ( Authorized)
// 403 : Yasak Giriş ( Forbidden)
// 404 : Bulunamadı (not found)

// My Special Exception
public class HamitMizrakException extends RuntimeException{

    // Override
    public HamitMizrakException(String message) {
        super(message);
    }
}
