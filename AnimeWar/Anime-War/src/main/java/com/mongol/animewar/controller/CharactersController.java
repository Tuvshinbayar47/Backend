package com.mongol.animewar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.mongol.animewar.model.Characters;
import com.mongol.animewar.repository.CharactersRepository;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/characters")
public class CharactersController {

    @Autowired
    private CharactersRepository repo;

    // GET all characters
    @GetMapping
    public List<Characters> getAll() {
        return repo.findAll();
    }

    // GET one character by ID
    @GetMapping("/{id}")
    public Characters getById(@PathVariable int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Character not found"));
    }

    // POST create new character
    @PostMapping
    public Characters create(@RequestBody Characters character) {
        return repo.save(character);
    }

    // PUT update character
    @PutMapping("/{id}")
    public Characters update(@PathVariable int id, @RequestBody Characters updatedChar) {
        Characters ch = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        ch.setName(updatedChar.getName());
        ch.setWorld(updatedChar.getWorld());
        return repo.save(ch);
    }

    // DELETE character
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "Deleted character with ID: " + id;
    }
}
