package com.backend.BackendSpringBootCreditpro.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Administrator model
 */
@Entity  // Indicador de que es un modelo Real para una Base de datos
@Table(name = "Client") // Indicamos el nombre que queremos para la tabla en la bd para que no use el de la clase
public class ClientModel {

     //Propiedades de la entidas / clase


    @Id // Indicamos que es un id  primary key
    @Column(unique = true, nullable = false) // Es unico  y no nullo
    private int Id;

    @Column(nullable = false,  columnDefinition = "VARCHAR(250)" )  //  no nullo, y tiene 255 de max long
    private String name;

    @Column( nullable = false, columnDefinition = "VARCHAR(250)") //   no nullo, y tiene 255 de max long
    private String lastName;

    
    @ManyToOne // Relacion de Uno a Muchos 1 Admin tiene muchos clientes
    @JoinColumn(nullable = false, unique=false) //  no nullo, no es unico el identificador y tiene 255 de max long
    private AdministratorModel Id_Admin;



    public ClientModel(){}

    public void setId(int id){
        this.Id=id;
    }

    public int getId(){
        return this.Id;
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


    public void setId_Admin(AdministratorModel id){
        this.Id_Admin = id;
    }

    public AdministratorModel getId_Admin(){
        return this.Id_Admin; 
    }
    
    
}
