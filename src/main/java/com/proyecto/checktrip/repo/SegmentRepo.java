package com.proyecto.checktrip.repo;

import com.proyecto.checktrip.entities.Segment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SegmentRepo extends JpaRepository<Segment,Integer> {
}
