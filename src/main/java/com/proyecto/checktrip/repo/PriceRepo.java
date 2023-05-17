package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price,Integer> {
}
