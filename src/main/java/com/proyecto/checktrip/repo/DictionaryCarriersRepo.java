package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.DictionaryCarriers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryCarriersRepo extends JpaRepository<DictionaryCarriers,Integer> {
}
