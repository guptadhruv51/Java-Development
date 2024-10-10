package com.example.ecommerce.E_Commerce.controller;

import com.example.ecommerce.E_Commerce.Config.AppConstants;
import com.example.ecommerce.E_Commerce.DTOs.ProductDTO;
import com.example.ecommerce.E_Commerce.DTOs.ProductResponseDto;
import com.example.ecommerce.E_Commerce.Service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable Long categoryId) {
        ProductDTO savedproductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedproductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponseDto> getAllProducts(
            @RequestParam(name="pageNumber",required = false,defaultValue = AppConstants.PAGE_Number) Integer pageNumber,
            @RequestParam(name="pageSize",required = false,defaultValue = AppConstants.PAGE_Size) Integer pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = AppConstants.Sort_Products_By) String sortBy,
            @RequestParam(name="=sortOrder",required = false,defaultValue = AppConstants.sortOrder) String sortOrder
    ) {
        ProductResponseDto productResponseDto = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponseDto> getProductsByCategory(
            @RequestParam(name="pageNumber",required = false,defaultValue = AppConstants.PAGE_Number) Integer pageNumber,
            @RequestParam(name="pageSize",required = false,defaultValue = AppConstants.PAGE_Size) Integer pageSize,
            @RequestParam(name="sortBy",required = false,defaultValue = AppConstants.Sort_Products_By) String sortBy,
            @RequestParam(name="=sortOrder",required = false,defaultValue = AppConstants.sortOrder) String sortOrder
            ,@Valid @PathVariable Long categoryId) {
        ProductResponseDto productResponseDto = productService.searchByCategory(categoryId,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponseDto> getProductsByKeyword(@PathVariable String keyword,
                                                                   @RequestParam(name="pageNumber",required = false,defaultValue = AppConstants.PAGE_Number) Integer pageNumber,
                                                                   @RequestParam(name="pageSize",required = false,defaultValue = AppConstants.PAGE_Size) Integer pageSize,
                                                                   @RequestParam(name="sortBy",required = false,defaultValue = AppConstants.Sort_Products_By) String sortBy,
                                                                   @RequestParam(name="=sortOrder",required = false,defaultValue = AppConstants.sortOrder) String sortOrder)
    {
        ProductResponseDto productResponseDto = productService.searchByKeyword(keyword,pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(productResponseDto, HttpStatus.FOUND);
    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long productId)
    {
        ProductDTO savedproductDTO=productService.updateProduct(productId,productDTO);
        return new ResponseEntity<>(savedproductDTO,HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long productId)
    {
        ProductDTO productDTO=productService.deleteProduct(productId);
        return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable Long productId,
                                                         @RequestParam("Image")MultipartFile image) throws IOException {
        ProductDTO updatedProduct=productService.updateProductImage(productId,image);
        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

}
