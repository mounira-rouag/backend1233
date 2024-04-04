package com.example.Project.Controller;

import com.example.Project.Models.CDC;
import com.example.Project.Models.Cables;
import com.example.Project.Models.Dev;
import com.example.Project.Models.Validation;
import com.example.Project.Repositories.CablesRepository;
import com.example.Project.Repositories.DevRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/api/cable")
@RestController
public class CablesController {

    final private CablesRepository cableRepo;
    final private DevRepository devRepo;

    public CablesController(CablesRepository cableRepo, DevRepository devRepo) {
        this.cableRepo = cableRepo;
        this.devRepo = devRepo;
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

    @GetMapping("/by-dev/{devid}")
    public List<Cables> getCablesByDev(@PathVariable int devid) {
        Optional<Dev> devOptional = devRepo.findById(devid);

        List<Cables> cables = Collections.emptyList();
        if (devOptional.isPresent()) {
            Dev dev = devOptional.get();

            // Eagerly fetch validations
            cables = dev.getCables();

            // Filter out the "no adapter" adapter
            cables = cables.stream()
                    .filter(cable -> !"PAS ADAPTATEUR".equals(cable.getActiaName()))
                    .collect(Collectors.toList());
        }

        return cables;
    }
}
