package com.example.Project.Repositories;

import com.example.Project.Models.PointEntree;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointEntreeRepository extends JpaRepository<PointEntree,Integer> {
}
