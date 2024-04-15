package com.example.Project.Controller;

import com.example.Project.Models.Cables;
import com.example.Project.Models.Dev;
import com.example.Project.Models.Validation;
import com.example.Project.Repositories.DevRepository;
import com.example.Project.Repositories.ValidationRepository;
import com.example.Project.Services.ValidationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/validation")
public class ValidationController {

    private final ValidationRepository validationRepo;
    private final DevRepository devRepo;
private final ValidationServiceImpl validationServiceImpl;
    public ValidationController(ValidationRepository validationRepo, DevRepository devRepo, ValidationServiceImpl validationServiceImpl) {
        this.validationRepo = validationRepo;
        this.devRepo = devRepo;
        this.validationServiceImpl = validationServiceImpl;
    }


    @GetMapping("/all")
    public List<Validation> getAllValidations() {
        List<Validation> validations= validationRepo.findAll();
        for (Validation validation  : validations) {
            switch (validation.getTypeValid()) {
                case "Véhicule":
                    validation.setName("Test Integration");
                    break;

                default:
                    validation.setName("Test Unitaire");
            }
        }
        return validations;
    }

    @PostMapping("/create/{iddev}")
    public Validation saveValidation(@RequestBody Validation validation , @PathVariable int iddev){
        Dev dev=devRepo.findById(iddev).get();
        dev.getValidations().add(validation);
        return validationRepo.save(validation);
    }

    @GetMapping("/{id}")
    public Validation getValidationById(@PathVariable int id){

        return validationRepo.findById(id).get();
    }
    @GetMapping("/by-dev/{iddev}")
    public List<Validation> getValidationByDev(@PathVariable int iddev) {

        Optional<Dev> devOptional = devRepo.findById(iddev);

        List<Validation> validations = Collections.emptyList();
        if (devOptional.isPresent()) {
            Dev dev = devOptional.get();


            validations = dev.getValidations();
            for (Validation validation  : validations) {
                switch (validation.getTypeValid()) {
                    case "Véhicule":
                        validation.setName("Test Integration");
                        break;

                    default:
                        validation.setName("Test Unitaire");
                }
            }
        }
        return validations;
    }
    @GetMapping("/version-name/{idvalidation}")
    public String getVersionNameFromValidation(@PathVariable int idvalidation){

        return validationServiceImpl.getVersionNameForValidation(idvalidation);
    }

}
