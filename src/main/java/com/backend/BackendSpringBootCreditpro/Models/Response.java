package com.backend.BackendSpringBootCreditpro.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

//Clase para enviar respuestas al Cliente
public class Response {
    
    @JsonProperty("message")
    String messaje;

    public Response(String messaje){
        this.messaje = messaje;
    }
}
