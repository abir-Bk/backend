package com.example.yakdhan.service;

import com.example.yakdhan.Modele.Installation;
import com.example.yakdhan.Modele.Produit;
import com.example.yakdhan.Modele.User; // Make sure you have imported the User class
import com.example.yakdhan.repository.InstallationRepository;
import com.example.yakdhan.repository.ProduitRepositories;  // Corrected repository name
import com.example.yakdhan.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {

    @Autowired
    private InstallationRepository installationRepository;

    @Autowired
    private ProduitRepositories produitRepository; // Corrected repository name

    @Autowired
    private UserRepository userRepository;

    // Create or Update an Installation
    public Installation saveInstallation(Installation installation) {
        // Look up the Produit and User entities by their IDs
        Optional<Produit> produit = produitRepository.findById(installation.getProduit().getId()); // Fixed typo in repository
        Optional<User> client = userRepository.findById(installation.getClient().getId());

        if (produit.isPresent() && client.isPresent()) {
            installation.setProduit(produit.get());  // Set the found Produit object
            installation.setClient(client.get());    // Set the found User object
            return installationRepository.save(installation);  // Save and return the Installation
        }

        throw new RuntimeException("Produit or Client not found.");
    }

    // Get all Installations
    public List<Installation> getAllInstallations() {
        return installationRepository.findAll();
    }

    // Get Installation by ID
    public Optional<Installation> getInstallationById(Integer id) {
        return installationRepository.findById(id);
    }

    // Delete Installation by ID
    public void deleteInstallation(Integer id) {
        installationRepository.deleteById(id);
    }

    // Mark Installation as Complete
    public Installation markInstallationAsComplete(Integer id) {
        Optional<Installation> optionalInstallation = installationRepository.findById(id);
        if (optionalInstallation.isPresent()) {
            Installation installation = optionalInstallation.get();
            installation.setEtat("Complete");  // Update the status
            return installationRepository.save(installation);  // Save the updated Installation
        }
        throw new RuntimeException("Installation not found with ID: " + id);
    }
}
