package com.gftfabiancastillo.demoFabianCastillo.repository;

import com.gftfabiancastillo.demoFabianCastillo.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
