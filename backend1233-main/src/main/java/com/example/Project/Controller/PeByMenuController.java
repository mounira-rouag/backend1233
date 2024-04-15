package com.example.Project.Controller;

import com.example.Project.Models.Menu;
import com.example.Project.Models.PEByMenu;
import com.example.Project.Repositories.MenuRepository;
import com.example.Project.Repositories.PeByMenuRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/PeByMenu")
public class PeByMenuController {
    private  final PeByMenuRepository peByMenuRepo;
    private  final MenuRepository menuRepo;

    public PeByMenuController(PeByMenuRepository peByMenuRepo, MenuRepository menuRepo) {
        this.peByMenuRepo = peByMenuRepo;
        this.menuRepo = menuRepo;
    }

    @PostMapping("creat")
    public PEByMenu savePeByMenu(@RequestBody PEByMenu pebymenu){

        return peByMenuRepo.save(pebymenu);
    }
    @GetMapping("/all")
    public List<PEByMenu> getAllMenus() {
        return peByMenuRepo.findAll();
    }


    @GetMapping("/{id}")
    public PEByMenu finMenuById(@PathVariable int id){
        Menu menu=menuRepo.findById(id).get();
        return peByMenuRepo.findByIdMenu(id);
    }

}
