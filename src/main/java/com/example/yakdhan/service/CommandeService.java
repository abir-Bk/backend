package com.example.yakdhan.service;

import com.example.yakdhan.Modele.Commande;
import com.example.yakdhan.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    // Create or update a commande
    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    // Get all commandes
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    // Get a commande by id
    public Optional<Commande> getCommandeById(int id) {
        return commandeRepository.findById(id);
    }

    // Delete a commande by id
    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }

    // Additional business logic can be added here
}
