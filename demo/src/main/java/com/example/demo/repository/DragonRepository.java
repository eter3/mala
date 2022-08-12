package com.example.demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Dragon;
    public interface DragonRepository extends JpaRepository<Dragon, Long> {
        List<Dragon> findByThreat(boolean threat);
        List<Dragon> findBySpeciesContaining(String species);
    }
