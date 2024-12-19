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
import org.zalando.problem.Problem;

import java.util.List;
import java.util.Optional;

import static org.zalando.problem.Status.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements  ProductService{

    public static final String PRODUCT_NOT_FOUNDED = "product  not founded";
    public static final String DETAIL_PRODUCT_NOT_FOUNDED = "the product with id  %s not founded";
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
    public Optional<ProductDto> getProductById(Long idProduct) {
        return productRepository.findById(idProduct).map(productMapper::fromEntity);
    }


    @Override
    public ProductDto updateProductById(Long idProduct, ProductDto productDto) {
        Product product =  productRepository.findById(idProduct)
                .orElseThrow(()-> Problem.builder()
                        .withTitle(PRODUCT_NOT_FOUNDED)
                        .withStatus(NOT_FOUND)
                        .withDetail(String.format(DETAIL_PRODUCT_NOT_FOUNDED, idProduct))
                        .build());
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
    public void  removeProductById(Long idProduct) {
        Product product =  productRepository.findById(idProduct)
                .orElseThrow(()-> Problem.builder()
                        .withTitle(PRODUCT_NOT_FOUNDED)
                        .withStatus(NOT_FOUND)
                        .withDetail(String.format(DETAIL_PRODUCT_NOT_FOUNDED, idProduct))
                        .build());
        productRepository.deleteById(idProduct);
    }

    @Override
    public boolean isAdmin() {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        return "admin@admin.com".equals(currentAuth.getName());
    }


}
