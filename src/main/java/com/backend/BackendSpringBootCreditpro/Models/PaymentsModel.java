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

@Entity
@Table(name= "Payments")
public class PaymentsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Reference;

    @Column(nullable = false, columnDefinition = "DECIMAL (10,2)")
    private double countPayment;

    @Column(nullable = false)
    private LocalDate datePayment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CreditsModel CodeCredits;

    public PaymentsModel(){}

      // Métodos Getters y Setters

      public int getReference() {
        return this.Reference;
    }

    public void setReference(int Reference) {
        this.Reference = Reference;
    }

    public double getCountPayment() {
        return this.countPayment;
    }

    public void setCountPayment(double countPayment) {
        this.countPayment = countPayment;
    }

    public void setDatePayment(LocalDate datepayment){
        this.datePayment = datepayment;
    }

    public LocalDate getDatePayment(){
        return this.datePayment;
    }

    public CreditsModel getCode() {
        return this.CodeCredits;
    }

    public void setCode(CreditsModel Code) {
        this.CodeCredits = Code;
    }
    
}
