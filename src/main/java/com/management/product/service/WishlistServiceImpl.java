package com.management.product.service;

import com.management.product.dtos.ProductDto;
import com.management.product.dtos.WishListProductDto;
import com.management.product.entities.*;
import com.management.product.mapper.ProductMapper;
import com.management.product.mapper.WishListProductMapper;
import com.management.product.repository.ProductRepository;
import com.management.product.repository.WishListProductRepository;
import com.management.product.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zalando.problem.Problem;

import java.util.List;
import java.util.Optional;

import static com.management.product.api.ProductControllerImpl.PRODUCT_NOT_FOUND;
import static org.zalando.problem.Status.NOT_FOUND;
@Service
@RequiredArgsConstructor
@Slf4j
public class WishlistServiceImpl implements  WishlistService{
    public static final String MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST = "Product with idProduct %s does not exist !";
    private final WishlistRepository wishlistRepository;
    private final WishListProductRepository wishListProductRepository;
    private final WishListProductMapper wishListProductMapper;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;



    @Override
    public WishListProductDto addProductToWishlist(Long idProduct) {
        log.info("addProductToWishlist()[idProduct :{}] Begin ...", idProduct);
        Product productToAdd = productRepository.findById(idProduct).orElseThrow(() -> {
            throw Problem.builder()
                    .withTitle(PRODUCT_NOT_FOUND)
                    .withStatus(NOT_FOUND)
                    .withDetail(String.format(MESSAGE_DETAIL_PRODUCT_ID_NOT_EXIST, idProduct))
                    .build();
        });

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        Wishlist wishlistOfAuthenticatedUser = getWishListOfAuthenticatedUser(currentAuth.getName());
        log.info("addProductToWishlist()[idProduct :{}] Done", idProduct);
        return addProductToWishList(wishlistOfAuthenticatedUser.getIdWishlist(),productToAdd.getIdProduct());
    }

    public Wishlist getWishListOfAuthenticatedUser(String email){
        return  wishlistRepository.findByEmail(email)
                .orElseGet(() -> {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setEmail(email);
                    return wishlistRepository.save(wishlist);
                });
    }

    public WishListProductDto addProductToWishList(String  idWishList, Long idProduct){
        WishListProduct  wishListProduct = new WishListProduct();
        wishListProduct.setIdWishlist(idWishList);
        wishListProduct.setIdProduct(idProduct);
        return wishListProductMapper.toWishListProductDto(wishListProductRepository.save(wishListProduct));
    }

    @Override
    public List<ProductDto> getProductsOfWishlistUser() {
        log.info("getProductsOfWishlistUser() ...");
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        List<WishListProduct> wishListProducts=wishlistRepository.findByEmail(currentAuth.getName())
                .map(Wishlist::getIdWishlist)
                .map(wishListProductRepository::findByIdWishlist)
                .orElse(List.of());
        List<ProductDto> productDtoList = wishListProducts.stream()
                .map(WishListProduct::getIdProduct)
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(productMapper::fromEntity)
                .toList();
        log.info("getProductsOfWishlistUser() Done");
        return productDtoList;
    }
}
