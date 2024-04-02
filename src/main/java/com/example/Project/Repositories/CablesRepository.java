package com.example.Project.Repositories;

import com.example.Project.Models.Cables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CablesRepository extends JpaRepository<Cables,Integer> {
}
