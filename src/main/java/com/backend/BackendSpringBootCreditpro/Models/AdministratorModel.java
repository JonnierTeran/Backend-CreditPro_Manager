package com.backend.BackendSpringBootCreditpro.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;


/**
 * Administrator model
 */
@Entity  // Indicador de que es un modelo Real para una Base de datos
@Table(name = "Administrator") // Indicamos el nombre que queremos para la tabla en la bd para que no use el de la clase
public class AdministratorModel {
    
    //Propiedades de la entidas / clase


    @Id // Indicamos que es un id  primary key
    @Column(unique = true, nullable = false) // Es unico  y no nullo
    private Long identificacion;

    @Column(nullable = false,  columnDefinition = "VARCHAR(250)" )  // Es unico  y no nullo, y tiene 255 de max long
    private String name;

    @Column( nullable = false, columnDefinition = "VARCHAR(250)") // Es unico  y no nullo
    private String lastName;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(250)") // Es unico  y no nullo
    private String user;

    @Column(nullable = false, columnDefinition = "VARCHAR(250)") // Es unico  y no nullo
    private String password;


    public AdministratorModel(){}

    public void setIdentificacion(Long identificacion){
        this.identificacion=identificacion;
    }

    public Long getIdentificacion(){
        return this.identificacion; 
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setUser(String user){
        this.user=user;
    }

    public String getUser(){
        return this.user;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }



}
