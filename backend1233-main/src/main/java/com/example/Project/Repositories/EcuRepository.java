package com.example.Project.Repositories;

import com.example.Project.Models.Ecu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EcuRepository extends JpaRepository<Ecu,Integer> {
    @Query("SELECT DISTINCT typeInj FROM Ecu")
    List<String> findAllDistinctTypeInj();
}
