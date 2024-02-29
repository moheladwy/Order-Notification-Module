package org.fcai.OrderNotificationModule.Helpers;

import org.fcai.OrderNotificationModule.DTOs.ProductDto;
import org.fcai.OrderNotificationModule.Models.Product;
import org.fcai.OrderNotificationModule.Repositories.DbContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor {
    private final DbContext context;

    @Autowired
    public ProductProcessor(DbContext context) {
        this.context = context;
    }

    public Product createProduct(ProductDto productDto) {
        int id = context.productRepository.getNextId();
        return new Product(
                id,
                productDto.name,
                productDto.description,
                productDto.vendor,
                productDto.price
        );
    }
}
