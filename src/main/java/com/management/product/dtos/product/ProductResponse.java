package com.management.product.dtos.product;

import com.management.product.enums.InventoryStatus;
import lombok.*;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long idProduct;
    private String   codeProduct;
    private String   nameProduct;
    private Integer  priceProduct;
    private Integer  quantityProduct;
    private InventoryStatus inventoryStatus;
}
