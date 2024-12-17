package com.management.product.service;

import com.management.product.dtos.ProductDto;
import com.management.product.entities.Product;
import com.management.product.mapper.ProductMapper;
import com.management.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements  ProductService{

    private final ProductMapper productMapper;

    private  final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        return productMapper.fromEntity(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productMapper.toProductDtoList(productRepository.findAll());
    }

    @Override
    public Optional<ProductDto> getProduct(Long idProduct) {
        return productRepository.findById(idProduct).map(productMapper::fromEntity);
    }

    @Override
    public ProductDto updateProductById(Long idProduct, ProductDto productDto) {
        Product product =  productRepository.findById(idProduct).orElseThrow(()-> new RuntimeException("Product not founded with idProduct :"+idProduct));
        product.setCodeProduct(productDto.getCodeProduct());
        product.setNameProduct(productDto.getNameProduct());
        product.setCategoryProduct(productDto.getCategoryProduct());
        product.setImageProduct(productDto.getImageProduct());
        product.setDescriptionProduct(productDto.getDescriptionProduct());
        product.setPriceProduct(productDto.getPriceProduct());
        product.setQuantityProduct(productDto.getQuantityProduct());
        product.setInternalReferenceProduct(productDto.getInternalReferenceProduct());
        product.setShellIdProduct(productDto.getShellIdProduct());
        product.setInventoryStatus(productDto.getInventoryStatus());
        product.setRatingProduct(productDto.getRatingProduct());
        return productMapper.fromEntity(productRepository.save(product));
    }

    @Override
    public boolean removeProductById(Long idProduct) {
        Product product =  productRepository.findById(idProduct).orElseThrow(()-> new RuntimeException("Product not founded with idProduct :"+idProduct));
        productRepository.deleteById(idProduct);
        return true;
    }

    @Override
    public boolean isAdmin() {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Utilisateur connecté : " + currentAuth.getName());
        return "admin@admin.com".equals(currentAuth.getName());
    }


}
