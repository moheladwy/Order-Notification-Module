package org.fcai.OrderNotificationModule.Controllers;

import org.fcai.OrderNotificationModule.DTOs.ProductDto;
import org.fcai.OrderNotificationModule.DTOs.UpdateProductQuantityDto;
import org.fcai.OrderNotificationModule.Exceptions.ProductNotFoundException;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final DbContext context;

    @Autowired
    public ProductController(DbContext context) {
        this.context = context;
    }

    // DONE.
    @GetMapping("/get-all")
    public List<Product> getAllProducts() {
        Map<Product, Integer> products = context.productRepository.getAll();
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productList.add(entry.getKey());
        }

        return productList;
    }

    // DONE.
    @GetMapping("/get-by-id/{id}")
    public Product getProductById(@PathVariable int id) {
        Product product = context.productRepository.getById(id);
        if (product == null)
            throw new ProductNotFoundException(id);
        return product;
    }

    // DONE.
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = new Product(
                    productDto.id,
                    productDto.name,
                    productDto.description,
                    productDto.vendor,
                    productDto.price
            );
            context.productRepository.add(product, productDto.quantity);
        } catch (NullPointerException | IllegalArgumentException e) {
            System.err.println("Failed to add product: " + e.getMessage());
            throw e;
        }
    }

    // DONE.
    @PutMapping("/update-quantity")
    public void updateProductQuantity(@RequestBody UpdateProductQuantityDto updateProductQuantityDto) {
        try {
            context.productRepository.updateQuantity(updateProductQuantityDto.id, updateProductQuantityDto.quantity);
        } catch (ProductNotFoundException e) {
            System.err.println("Failed to update product quantity: " + e.getMessage());
            throw e;
        }
    }

    // DONE.
    @DeleteMapping("/remove/{id}")
    public void removeProduct(@PathVariable int id) {
        try {
            context.productRepository.remove(id);
        } catch (ProductNotFoundException e) {
            System.err.println("Failed to remove product: " + e.getMessage());
            throw e;
        }
    }
}
