    package com.example.yakdhan.Modele;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;

    import java.util.List;

    @Entity
    public class Produit {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false)
        private String nom;

        @Lob
        private String description;

        @Column(nullable = false)
        private double prix;

        private int quantite;

        @Lob
        @Column(name = "image_file", columnDefinition = "BLOB")
        private byte[] imageFile;

        @Column(name = "ip_adresse", unique = true)
        private String adresseIp;

        @ManyToOne(fetch = FetchType.LAZY) // Lazy fetching for better performance
        @JoinColumn(name = "categorie_id", nullable = false)
        @JsonIgnoreProperties({"produits"}) // Prevent infinite recursion
        private Categorie categorie;

        @ManyToMany(mappedBy = "produits", fetch = FetchType.LAZY)
        @JsonIgnoreProperties({"produits"})
        private List<Commande> commandes;

        @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonIgnoreProperties({"produit"}) // Ignore this relationship during serialization
        private List<Installation> installations; // Relationship to Installation

        // Constructors
        public Produit() {}

        public Produit(String nom, String description, double prix, int quantite, Categorie categorie, byte[] imageFile, String adresseIp) {
            this.nom = nom;
            this.description = description;
            this.prix = prix;
            this.quantite = quantite;
            this.categorie = categorie;
            this.imageFile = imageFile;
            this.adresseIp = adresseIp;
        }

        // Getters and Setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNom() {
            return nom;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getPrix() {
            return prix;
        }

        public void setPrix(double prix) {
            this.prix = prix;
        }

        public int getQuantite() {
            return quantite;
        }

        public void setQuantite(int quantite) {
            this.quantite = quantite;
        }

        public byte[] getImage() {
            return imageFile;
        }

        public void setImage(byte[] imageFile) {
            this.imageFile = imageFile;
        }

        public String getAdresseIp() {
            return adresseIp;
        }

        public void setAdresseIp(String adresseIp) {
            this.adresseIp = adresseIp;
        }

        public Categorie getCategorie() {
            return categorie;
        }

        public void setCategorie(Categorie categorie) {
            this.categorie = categorie;
        }

        public List<Commande> getCommandes() {
            return commandes;
        }

        public void setCommandes(List<Commande> commandes) {
            this.commandes = commandes;
        }

        public List<Installation> getInstallations() {
            return installations;
        }

        public void setInstallations(List<Installation> installations) {
            this.installations = installations;
        }
    }
