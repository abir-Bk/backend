package com.example.yakdhan.service;
import com.example.yakdhan.repository.ProduitRepositories;
import com.example.yakdhan.Modele.Produit;
import com.example.yakdhan.Modele.Categorie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepositories produitRepository;

    @Autowired
    private CategorieService categorieService;

    public List<Produit> getProduitsByCategorie(String categorieNom) {
        Categorie categorie = categorieService.getCategorieByNom(categorieNom);
        return produitRepository.findByCategorie(categorie);
    }

    public Produit ajouterProduit(Produit produit, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            produit.setImage(imageFile.getBytes());
        }
        return produitRepository.save(produit);
    }

    public void supprimerProduit(int id) {
        produitRepository.deleteById(id);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }
}
