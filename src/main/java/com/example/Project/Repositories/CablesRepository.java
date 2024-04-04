package com.example.Project.Repositories;

import com.example.Project.Models.Cables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CablesRepository extends JpaRepository<Cables,Integer> {


}
