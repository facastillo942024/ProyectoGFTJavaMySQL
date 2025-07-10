package com.gftfabiancastillo.demoFabianCastillo.repository;

import com.gftfabiancastillo.demoFabianCastillo.model.Fondo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FondoRepository extends JpaRepository<Fondo, String> {

}
