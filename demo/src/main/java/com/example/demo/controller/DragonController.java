package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Dragon;
import com.example.demo.repository.DragonRepository;
    @CrossOrigin(origins = "http://localhost:8081")
    @RestController
    @RequestMapping("/api")
    public class DragonController {
        @Autowired
        DragonRepository dragonRepository;
        @GetMapping("/dragons")
        public ResponseEntity<List<Dragon>> getAllDragons(@RequestParam(required = false) String species) {
            try {
                List<Dragon> dragons = new ArrayList<Dragon>();
                if (species == null)
                    dragonRepository.findAll().forEach(dragons::add);
                else
                    dragonRepository.findBySpeciesContaining(species);
                if (dragons.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(dragons, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping("/dragons/{id}")
        public ResponseEntity<Dragon> getTutorialById(@PathVariable("id") long id) {
            Optional<Dragon> dragonData = dragonRepository.findById(id);
            if (dragonData.isPresent()) {
                return new ResponseEntity<>(dragonData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        @PostMapping("/dragons")
        public ResponseEntity<Dragon> createDragon(@RequestBody Dragon dragon) {
            try {
                Dragon _dragon = dragonRepository
                        .save(new Dragon(dragon.getSpecies(), dragon.getDescription(), false));
                return new ResponseEntity<>(_dragon, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PutMapping("/dragons/{id}")
        public ResponseEntity<Dragon> updateDragon(@PathVariable("id") long id, @RequestBody Dragon dragon) {
            Optional<Dragon> dragonData = dragonRepository.findById(id);
            if (dragonData.isPresent()) {
                Dragon _dragon = dragonData.get();
                _dragon.setSpecies(dragon.getSpecies());
                _dragon.setDescription(dragon.getDescription());
                _dragon.setThreat(dragon.isThreat());
                return new ResponseEntity<>(dragonRepository.save(_dragon), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        @DeleteMapping("/dragons/{id}")
        public ResponseEntity<HttpStatus> deleteDragon(@PathVariable("id") long id) {
            try {
                dragonRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @DeleteMapping("/dragons")
        public ResponseEntity<HttpStatus> deleteAllDragons() {
            try {
                dragonRepository.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @GetMapping("/dragons/threat")
        public ResponseEntity<List<Dragon>> findByThreat() {
            try {
                List<Dragon> dragons = dragonRepository.findByThreat(true);
                if (dragons.isEmpty()) {
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(dragons, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
