package org.softuni.productshop.unit.validation;

import org.junit.Before;
import org.junit.Test;
import org.softuni.productshop.domain.entities.Category;
import org.softuni.productshop.domain.entities.Product;
import org.softuni.productshop.domain.models.service.CategoryServiceModel;
import org.softuni.productshop.domain.models.service.ProductServiceModel;
import org.softuni.productshop.validation.ProductValidationService;
import org.softuni.productshop.validation.implementations.ProductValidationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductValidationServiceTests {

    private ProductValidationService productValidationService;

    @Before
    public void setupTest() {
        this.productValidationService = new ProductValidationServiceImpl();
    }

    @Test
    public void isValidWithProduct_whenValid_true() {
        Product product = new Product();
        product.setCategories(List.of(new Category()));
        boolean result = this.productValidationService.isValid(new Product());
        assertTrue(result);
    }

    @Test
    public void isValidWithProduct_whenNull_false() {
        Product product = null;
        boolean result = this.productValidationService.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenNull_false() {
        ProductServiceModel product = null;
        boolean result = this.productValidationService.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenValid_true() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        boolean result = this.productValidationService.isValid(product);
        assertTrue(result);
    }

    @Test
    public void t1() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(null);

        boolean result = this.productValidationService.isValid(product);
        assertFalse(result);
    }

    @Test
    public void t2() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(new ArrayList<>());

        boolean result = this.productValidationService.isValid(product);
        assertFalse(result);
    }

}
