package com.example.yakdhan.service;

import com.example.yakdhan.repository.CategorieRepositories;
import com.example.yakdhan.Modele.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepositories CategorieRepository;

    public Categorie ajouterCategorie(Categorie categorie) {
        return CategorieRepository.save(categorie);
    }

    public Categorie getCategorieByNom(String nom) {
        return CategorieRepository.findByNom(nom);
    }

    public Categorie getCategorieById(int id) {
        return CategorieRepository.findById(id).orElse(null); // Renvoie null si la catégorie n'existe pas
    }

    public List<Categorie> getAllCategorie() {
        return CategorieRepository.findAll();
    }
}