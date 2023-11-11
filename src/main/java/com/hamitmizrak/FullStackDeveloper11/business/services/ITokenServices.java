package com.hamitmizrak.FullStackDeveloper11.business.services;

// Generics
public interface ITokenServices<T> {

    // CREATE
    public String createToken(T t);

    // DELETE
    public void deleteToken(Long id);
}
