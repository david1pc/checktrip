package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Dictionaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionariesRepo extends JpaRepository<Dictionaries,Integer> {
}
