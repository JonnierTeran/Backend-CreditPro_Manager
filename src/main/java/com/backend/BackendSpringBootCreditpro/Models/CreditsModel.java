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
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private int code;

    @Column(nullable = false, columnDefinition="VARCHAR (255)")
    private LocalDate initialDate;

    @Column(nullable = false, columnDefinition="VARCHAR (255)")
    private LocalDate finishDate;

    @Column(nullable = false, columnDefinition="VARCHAR (255) DEFAULT  'ACTIVO' " )
    private String status;
    
    @Column(nullable = false, columnDefinition = "FLOAT")
    private int interest;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double creditAmount;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double totalDebt;

  
    @Column(nullable = false, columnDefinition = "DEFAULT FALSE")
    private Boolean Renovated;

    @Column(nullable = true, columnDefinition= "VARCHAR (255)")
    private String Observations;

    @ManyToOne
    @JoinColumn(nullable = false, unique=false)
    private AdministratorModel Id_Admin;

    @ManyToOne
    @JoinColumn(nullable = false, unique=false)
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

    public void setObservations( String Observations){
            this.Observations = Observations;

    }

    public String getObservations(){
        return this.Observations;

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
