package com.example.yakdhan.Modele;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Installation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    private Produit produit; // Linked to Produit entity

    @Column(name = "date_installation", nullable = false)
    private LocalDate dateInstallation; // Installation date

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private User client; // Linked to User entity

    @Column(name = "etat", nullable = false)
    private String etat; // Status of the installation (e.g., "In Progress", "Completed")

    // Constructors
    public Installation() {}

    public Installation(Produit produit, LocalDate dateInstallation, User client, String etat) {
        this.produit = produit;
        this.dateInstallation = dateInstallation;
        this.client = client;
        this.etat = etat;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public LocalDate getDateInstallation() {
        return dateInstallation;
    }

    public void setDateInstallation(LocalDate dateInstallation) {
        this.dateInstallation = dateInstallation;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
