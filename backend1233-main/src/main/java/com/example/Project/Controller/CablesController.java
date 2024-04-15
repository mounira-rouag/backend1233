package com.example.Project.Controller;

import com.example.Project.Models.CDC;
import com.example.Project.Models.Cables;
import com.example.Project.Models.Dev;
import com.example.Project.Models.Validation;
import com.example.Project.Repositories.CablesRepository;
import com.example.Project.Repositories.DevRepository;
import com.example.Project.Services.CablesServices;
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
    final private CablesServices cablesServices;

    public CablesController(CablesRepository cableRepo, DevRepository devRepo, CablesServices cablesServices) {
        this.cableRepo = cableRepo;
        this.devRepo = devRepo;
        this.cablesServices = cablesServices;
    }

    @GetMapping("/all")
    public List<Cables> getAllCables() {
       List<Cables> cables=cableRepo.findAll();

        String specificName = "PAS ADAPTATEUR"; // Specify the name of the adapter to exclude

        List<Cables> filteredCables = cables.stream()
                .filter(cable -> !cable.getAtalName().equals(specificName) )
                .collect(Collectors.toList());

        for (Cables cable : filteredCables) {
            switch (cable.getNumDico()) {
                case 256:
                    cable.setName("Adaptateur PSA CAN");
                    break;
                case 257:
                    cable.setName("Adaptateur OBD1");
                    break;

                case 305:
                    cable.setName("Adaptateur OBD1n");
                    break;
                case 352:
                    cable.setName("Adaptateur VAG/N");
                    break;
                // ... other cases
                default:
                    cable.setName("Adaptateur SEAT/SKODA");
            }
        }
        return filteredCables;
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
            for (Cables cable : cables) {
                switch (cable.getNumDico()) {
                    case 256:
                        cable.setName("Adaptateur PSA CAN");
                        break;
                    case 257:
                        cable.setName("Adaptateur OBD1");
                        break;

                    case 305:
                        cable.setName("Adaptateur OBD1n");
                        break;
                    case 352:
                        cable.setName("Adaptateur VAG/N");
                        break;
                    // ... other cases
                    default:
                        cable.setName("Adaptateur SEAT/SKODA");
                }
            }

        }

        return cables;
    }


}
