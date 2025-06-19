package com.mongol.animewar.controller;

import com.mongol.animewar.model.Villain;
import com.mongol.animewar.repository.VillainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/villains")
public class VillainController {

    @Autowired
    private VillainRepository repo;

    // GET all
    @GetMapping
    public List<Villain> getAll() {
        return repo.findAll();
    }

    // GET by ID
    @GetMapping("/{id}")
    public Villain getById(@PathVariable int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Villain not found"));
    }

    // POST
    @PostMapping
    public Villain create(@RequestBody Villain villain) {
        return repo.save(villain);
    }

    // PUT
    @PutMapping("/{id}")
    public Villain update(@PathVariable int id, @RequestBody Villain updated) {
        Villain v = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        v.setName(updated.getName());
        v.setWorld(updated.getWorld());
        return repo.save(v);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "Deleted villain with ID: " + id;
    }
}
