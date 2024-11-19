package com.example.yakdhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yakdhan.Modele.Produit;
import com.example.yakdhan.Modele.Categorie;

import java.util.List;

public interface ProduitRepositories extends JpaRepository<Produit, Integer> {
    List<Produit> findByCategorie(Categorie categorie);
}
