package com.backend.BackendSpringBootCreditpro.Models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Credits model
 */
@Entity  // Indicador de que es un modelo Real para una Base de datos
@Table(name = "Credits") // Indicamos el nombre que queremos para la tabla en la bd para que no use el de la clase
public class CreditsModel {

    //Propiedades de la entidad / clase
    
    @Id //ID Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Incrementable y unico 
    @Column(unique = false, nullable = false)   // Unico y No null
    private int code;

    @Column(nullable = false) // No null 
    private LocalDate initialDate;

    @Column(nullable = false) // no Null
    private LocalDate finishDate;

    @Column(nullable = false, columnDefinition="VARCHAR (255) DEFAULT  'ACTIVO' " )  // no nullo, y tiene 255 de max long
    private String status;
    
    @Column(nullable = false) //  no nullo
    private int interest;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double creditAmount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalDebt;

  
    @Column(columnDefinition = " BOOLEAN DEFAULT FALSE ", nullable = false)
    private Boolean Renovated;



    @ManyToOne  //Relacion de 1 a muchos con Admin, 1 admin registra muchos Creditos
    @JoinColumn(nullable = false, unique=false) //Null y no es unico
    private AdministratorModel Id_Admin;

    @ManyToOne //Relacion de 1 a muchos con Admin, 1 admin registra muchos Creditos
    @JoinColumn(nullable = false, unique=false) //Null y no es unico
    private ClientModel Id_Client;
    

    public CreditsModel(){}

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LocalDate getInitialDate() {
        return this.initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinishDate() {
        return this.finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getInterest() {
        return this.interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public double getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public double getTotalDebt() {
        return this.totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public void setRenovated(Boolean Renovated) {
        this.Renovated = Renovated;
    }

    public Boolean getRenovated() {
        return this.Renovated;
    }


    public AdministratorModel getId_Admin() {
        return this.Id_Admin;
    }

    public void setId_Admin(AdministratorModel id_Admin) {
        this.Id_Admin = id_Admin;
    }

    public ClientModel getId_Client() {
        return this.Id_Client;
    }

    public void setId_Client(ClientModel id_Client) {
        this.Id_Client = id_Client;
    }

    

    
}
