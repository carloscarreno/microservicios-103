package com.aironmountain.bitcoins.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aironmountain.bitcoins.entities.Bitcoin;

public interface BitcoinRepository extends JpaRepository<Bitcoin, Long>{
    List<Bitcoin> findByIdusuario(Long idusuario);
}
