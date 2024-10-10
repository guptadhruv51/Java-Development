package com.example.ecommerce.E_Commerce.Service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public ProductResponseDto getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder)
    {   // check if product size =0
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage=productRepository.findAll(pageable);
        List<Product> productList=productPage.getContent();

        if(productList.isEmpty())
        {
            throw new ApiException("No Products have been created yet");
        }
        List<ProductDTO> productDTOS=productList.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setContent(productDTOS);
        productResponseDto.setPageNumber(productPage.getNumber());
        productResponseDto.setPageSize(productPage.getSize());
        productResponseDto.setTotalPages(productPage.getTotalPages());
        productResponseDto.setTotalElements(productPage.getTotalElements());
        productResponseDto.setLastPage(productPage.isLast());
        return productResponseDto;

    }

    @Override
    public ProductResponseDto searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder)
    {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage=  productRepository.findByCategoryOrderByPriceAsc(category,pageable);
        List<Product> products=productPage.getContent();
        if(products.size()==0)
        {
            throw new ApiException("No products for the categoryId "+categoryId);
        }
        List<ProductDTO>productDTOS=products.stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();
        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setContent(productDTOS);
        productResponseDto.setPageNumber(productPage.getNumber());
        productResponseDto.setPageSize(productPage.getSize());
        productResponseDto.setTotalPages(productPage.getTotalPages());
        productResponseDto.setTotalElements(productPage.getTotalElements());
        productResponseDto.setLastPage(productPage.isLast());
        return productResponseDto;

    }

    @Override
    public ProductResponseDto searchByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder)
    {
        Sort sortByAndOrder=sortOrder.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
        Page<Product> productPage=productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%',pageable);
        List<Product> products=productPage.getContent();
        if(products.size()==0)
        {
            throw new ApiException("No Products found for the keyword "+keyword);
        }
        List<ProductDTO>productDTOS=products
                .stream()
                .map(product -> modelMapper.map(product,ProductDTO.class))
                .toList();

        ProductResponseDto productResponseDto=new ProductResponseDto();
        productResponseDto.setContent(productDTOS);
        productResponseDto.setPageNumber(productPage.getNumber());
        productResponseDto.setPageSize(productPage.getSize());
        productResponseDto.setTotalPages(productPage.getTotalPages());
        productResponseDto.setTotalElements(productPage.getTotalElements());
        productResponseDto.setLastPage(productPage.isLast());
        return productResponseDto;
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
