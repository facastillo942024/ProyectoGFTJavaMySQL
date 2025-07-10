package com.gftfabiancastillo.demoFabianCastillo.repository;

import com.gftfabiancastillo.demoFabianCastillo.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long>  {

}
