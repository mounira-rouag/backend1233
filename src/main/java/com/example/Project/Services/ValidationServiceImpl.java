package com.example.Project.Services;

import com.example.Project.Repositories.ValidationRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl {
    private final ValidationRepository validationRepo;

    public ValidationServiceImpl(ValidationRepository validationRepo) {
        this.validationRepo = validationRepo;
    }

    public String getVersionNameForValidation(int validationId) {
        return validationRepo.findVersionNameByValidationId(validationId);
    }
}
