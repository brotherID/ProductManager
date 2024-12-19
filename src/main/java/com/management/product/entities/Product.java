package com.management.product.entities;


import com.management.product.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@RequiredArgsConstructor
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String codeProduct;
    private String nameProduct;
    private String descriptionProduct;
    private String imageProduct;
    private String categoryProduct;
    private Integer priceProduct;
    private Integer quantityProduct;
    private String internalReferenceProduct;
    private Integer shellIdProduct;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private Integer ratingProduct;
}
