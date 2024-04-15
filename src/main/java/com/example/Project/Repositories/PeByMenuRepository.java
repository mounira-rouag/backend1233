package com.example.Project.Repositories;

import com.example.Project.Models.Menu;
import com.example.Project.Models.PEByMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeByMenuRepository extends JpaRepository<PEByMenu,Integer> {

    PEByMenu findByIdMenu(Integer idmenu);
}
