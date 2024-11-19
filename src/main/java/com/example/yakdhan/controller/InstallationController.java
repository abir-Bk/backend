package com.example.yakdhan.controller;

import com.example.yakdhan.Modele.Installation;
import com.example.yakdhan.service.InstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/installations")
public class InstallationController {

    @Autowired
    private InstallationService installationService;

    // Create or Update Installation
    @PostMapping
    public ResponseEntity<Installation> saveInstallation(@RequestBody Installation installation) {
        Installation savedInstallation = installationService.saveInstallation(installation);
        return ResponseEntity.ok(savedInstallation);
    }

    // Get All Installations
    @GetMapping
    public ResponseEntity<List<Installation>> getAllInstallations() {
        List<Installation> installations = installationService.getAllInstallations();
        return ResponseEntity.ok(installations);
    }

    // Get Installation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Installation> getInstallationById(@PathVariable Integer id) {
        return installationService.getInstallationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete Installation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstallation(@PathVariable Integer id) {
        installationService.deleteInstallation(id);
        return ResponseEntity.noContent().build();
    }

    // Mark Installation as Complete
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Installation> markInstallationAsComplete(@PathVariable Integer id) {
        try {
            Installation updatedInstallation = installationService.markInstallationAsComplete(id);
            return ResponseEntity.ok(updatedInstallation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
