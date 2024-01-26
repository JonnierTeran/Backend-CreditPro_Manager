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
@Table(name = "Observations")
public class Obvservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = false, nullable = false)
    private int code;

    @Column(nullable = false,  columnDefinition = "VARCHAR(250)" )  // Es unico  y no nullo, y tiene 255 de max long
    private String title;

    @Column(nullable = false,  columnDefinition = "VARCHAR(250)" )  // Es unico  y no nullo, y tiene 255 de max long
    private String description;

    @Column(nullable = false)
    private LocalDate toDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CreditsModel CodeCredits;

    public Obvservations(){}

    // Getter y Setter para 'code'
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    // Getter y Setter para 'title'
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter y Setter para 'description'
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter y Setter para 'toDate'
    public LocalDate getToDate() {
        return this.toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    // Getter y Setter para 'code' de la relación ManyToOne
    public CreditsModel getCodeCredits() {
        return this.CodeCredits;
    }

    public void setCode(CreditsModel cod) {
        this.CodeCredits = cod;
    }


    
}
