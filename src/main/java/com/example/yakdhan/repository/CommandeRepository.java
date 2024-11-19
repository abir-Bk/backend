package com.example.yakdhan.repository;

import com.example.yakdhan.Modele.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    // You can define custom queries here if needed
}
