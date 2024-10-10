package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.DTOs.CategoryDTO;
import com.example.ecommerce.E_Commerce.DTOs.ProductDTO;
import com.example.ecommerce.E_Commerce.DTOs.ProductResponseDto;
import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.Models.Product;
import com.example.ecommerce.E_Commerce.exceptions.ApiException;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.CategoryRepository;
import com.example.ecommerce.E_Commerce.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FileServiceImpl fileService;
    @Value("${project.image}")
    private String path;
    @Override
    public ProductDTO addProduct(Long categoryId, ProductDTO productDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        boolean isProductNotPresent = true;
        List<Product> products = category.getProductList();
        for (Product value : products) {
            if (value.getProductName().equals(productDTO.getProductName())) {
                isProductNotPresent = false;
                break;
            }
        }
        if (isProductNotPresent) {
            Product product = modelMapper.map(productDTO, Product.class);
            product.setImage("Not available");
            product.setCategory(category);
            double specialPrice = product.getPrice() - (product.getDiscount() * 0.01) * product.getPrice();
            product.setSpecialPrice(specialPrice);
            Product saved = productRepository.save(product);
            return modelMapper.map(product, ProductDTO.class);
        }
        else
        {
            throw new ApiException("Product already exists!!!!");
        }
    }

    @Override
    public ProductResponseDto getAllProducts()
    {   // check if product size =0
        List<Product> productList=productRepository.findAll();
        if(productList.isEmpty())
        {
            throw new ApiException("Product List is Empty");
        }
        List<ProductDTO> productDTOS=productList.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        return new ProductResponseDto(productDTOS);

    }

    @Override
    public ProductResponseDto searchByCategory(Long categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        List<ProductDTO>products=productRepository.findByCategoryOrderByPriceAsc(category)
                .stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        return new ProductResponseDto(products);

    }

    @Override
    public ProductResponseDto searchByKeyword(String keyword) {
        List<ProductDTO>products=productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%')
                .stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        return new ProductResponseDto(products);
    }

    @Override
    public ProductDTO updateProduct(Long productId, ProductDTO productDTO) {
        Product product1=productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFoundException("product","productId",productId));
        Product product=modelMapper.map(productDTO,Product.class);
        product1.setProductName(product.getProductName());
        product1.setDescription(product.getDescription());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());
        product1.setDiscount(product.getDiscount());
        double specialPrice=product.getPrice()-(product.getDiscount()*0.01)*product.getPrice();
        product1.setSpecialPrice(specialPrice);
        productRepository.save(product1);

        return  modelMapper.map(product1,ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","ProductId",productId));

        productRepository.delete(product);
        return modelMapper.map(product,ProductDTO.class);
    }

    @Override
    public ProductDTO updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new ResourceNotFoundException("Product","productId",productId));

        String fileName=fileService.uploadImage(path,image);
        product.setImage(fileName);
        product=productRepository.save(product);

        return modelMapper.map(product,ProductDTO.class);
    }

}
