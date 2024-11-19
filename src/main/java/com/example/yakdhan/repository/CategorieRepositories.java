package com.example.yakdhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.yakdhan.Modele.Categorie;

public interface CategorieRepositories extends JpaRepository<Categorie, Integer> {
    Categorie findByNom(String nom);
}
