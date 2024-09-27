package com.example.ecommerce.E_Commerce.Service;

import com.example.ecommerce.E_Commerce.Models.Category;
import com.example.ecommerce.E_Commerce.exceptions.ResourceNotFoundException;
import com.example.ecommerce.E_Commerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService
{
    //private List<Category> categories=new ArrayList<>();
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category)
    {
        categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId)
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
        return "Category Id: "+categoryId+" deleted";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId)
    {
        //List<Category> categories=categoryRepository.findAll();
        Optional<Category> categories=categoryRepository.findById(categoryId);

        Category savedCategory=categories.orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
        category.setCategoryId(categoryId);
        return categoryRepository.save(category);


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
