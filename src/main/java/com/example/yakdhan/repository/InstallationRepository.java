package com.example.yakdhan.repository;

import com.example.yakdhan.Modele.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstallationRepository extends JpaRepository<Installation, Integer> {
    // Additional query methods can be defined here if needed
}
