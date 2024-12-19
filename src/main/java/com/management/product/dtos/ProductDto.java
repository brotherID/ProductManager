package com.management.product.dtos;

import com.management.product.enums.InventoryStatus;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String   codeProduct;
    private String   nameProduct;
    private String   descriptionProduct;
    private String   imageProduct;
    private String   categoryProduct;
    private Integer  priceProduct;
    private Integer  quantityProduct;
    private String   internalReferenceProduct;
    private Integer  shellIdProduct;
    private InventoryStatus inventoryStatus;
    private Integer  ratingProduct;
}
