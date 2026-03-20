package com.example.tdd_lab.catalog;
import com.example.tdd_lab.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {

    @Test
    void createProductRequiresValidFields() {
        assertThrows(IllegalArgumentException.class, () -> new Product("", "Phone", 100));
    }

    @Test
    void catalogCanAddAndFindProductBySku() {
        Catalog catalog = new Catalog();
        Product product = new Product("SKU1", "Phone", 100);

        catalog.add(product);

        assertEquals(product, catalog.findBySku("SKU1"));
    }

    @Test
    void findMissingSkuReturnsNull() {
        Catalog catalog = new Catalog();
        assertNull(catalog.findBySku("MISSING"));
    }
}