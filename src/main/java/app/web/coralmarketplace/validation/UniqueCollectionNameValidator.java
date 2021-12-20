package app.web.coralmarketplace.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import app.web.coralmarketplace.service.CollectionService;

public class UniqueCollectionNameValidator implements ConstraintValidator<UniqueCollectionNameConstraint, String> {

    @Autowired
    private CollectionService collectionService;

    @Override
    public void initialize(UniqueCollectionNameConstraint referenceUniqueConstraint) {}

    @Override
    public boolean isValid(String name, ConstraintValidatorContext cxt) {
        if (name == null || name.isBlank()) {
            return true;
        }

        return collectionService.getByName(name) == null;
    }

}
