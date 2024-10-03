package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.Config.Config;
import com.example.ecommerce.E_Commerce.DTOs.CategoryDTO;
import com.example.ecommerce.E_Commerce.DTOs.CategoryResponseDto;
import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.exceptions.ApiException;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService
{
    //private List<Category> categories=new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponseDto getAllCategories()
    {
        List<Category> categoryList=categoryRepository.findAll();
        if(categoryList.isEmpty())
        {
                throw new ApiException("No Categories have been created yet");
        }
        List<CategoryDTO> categoryDTOS=categoryList
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        CategoryResponseDto categoryResponseDto=new CategoryResponseDto();
        categoryResponseDto.setContent(categoryDTOS);
        return categoryResponseDto;
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO)
    {
        Category category=modelMapper.map(categoryDTO,Category.class);
        Category categoryFromDb=categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!=null)
        {
            throw new ApiException("Category with name "+categoryFromDb.getCategoryName()+" already exists !!!!");
        }

        Category savedCategory=categoryRepository.save(category);
        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId)
    {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        //Category category1=category.orElseThrow();
//        Category category=categories.
//                stream().filter(c->c.getCategoryId()
//                        .equals(categoryId)).findFirst()
//                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Resource Not Found"));
////        if(category==null)
////            return "Category not found";

        categoryRepository.delete(category);
       return  modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId)
    {
        //List<Category> categories=categoryRepository.findAll();
        Optional<Category> categories=categoryRepository.findById(categoryId);

        Category savedCategory=categories.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        categoryDTO.setCategoryId(categoryId);

        savedCategory=categoryRepository.save(modelMapper.map(categoryDTO,Category.class));
        return modelMapper.map(savedCategory,CategoryDTO.class);


//        Optional<Category> optionalCategory =categories.stream().filter(c->c.getCategoryId().equals(categoryId))
//             .findFirst();
//
//     if(savedCategory.isPresent())
//     {
//         Category existingCategory=optionalCategory.get();
//         existingCategory.setCategoryName(existingCategory.getCategoryName());
//         return categoryRepository.save(existingCategory);
//     }
//     else {
//         throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Category Not Found");
//     }
    }
}
