package com.example.demo.model;

import jakarta.persistence.*;


    @Entity
    @Table(name = "Dragons")
    public class Dragon {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @Column(name = "species")
        private String species;
        @Column(name = "description")
        private String description;
        @Column(name = "threat")
        private boolean threat;
        public Dragon() {
        }
        public Dragon(String species, String description, boolean threat) {
            this.species = species;
            this.description = description;
            this.threat = threat;
        }
        public long getId() {
            return id;
        }
        public String getSpecies() {
            return species;
        }
        public void setSpecies(String title) {
            this.species = title;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public boolean isThreat() {
            return threat;
        }
        public void setThreat(boolean isPublished) {
            this.threat = isPublished;
        }
        @Override
        public String toString() {
            return "Dragon [id=" + id + ", title=" + species + ", desc=" + description + ", published=" + threat + "]";
        }
    }
