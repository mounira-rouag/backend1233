package com.example.Project.Controller;

import com.example.Project.Models.CDC;
import com.example.Project.Models.Cables;
import com.example.Project.Models.Dev;
import com.example.Project.Repositories.CablesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/cable")
@RestController
public class CablesController {

    final private CablesRepository cableRepo;

    public CablesController(CablesRepository cableRepo) {
        this.cableRepo = cableRepo;
    }

    @GetMapping("/all")
    public List<Cables> getAllCables() {
        return cableRepo.findAll();
    }

    @PostMapping("cretae")
    public Cables saveCable(@RequestBody Cables cable){
        return cableRepo.save(cable);
    }

    @GetMapping("/{id}")
    public Cables getCablesById(@PathVariable int id){
        return cableRepo.findById(id).get();
    }


}
