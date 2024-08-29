package de.supercode.lohnt_er_sich.service;

import de.supercode.lohnt_er_sich.dto.CategoryDTO;
import de.supercode.lohnt_er_sich.entity.Category;
import de.supercode.lohnt_er_sich.entity.Friend;
import de.supercode.lohnt_er_sich.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryByName(String category) {
        return categoryRepository.findByName(category);
    }


    public boolean isCategoryExist(String category) {
         return getCategoryByName(category).isPresent();
    }

    public Category createCategory(CategoryDTO category) {
        if (getCategoryByName(category.getName()).isPresent())  return null;

        Category newCategory = new Category();
        newCategory.setName(category.getName());
        return categoryRepository.save(newCategory);

    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addFriendToCategory(Friend friend, Category category) {
            category.addFriend(friend);
            categoryRepository.save(category);

    }

    public void removeFriendFromCategory(Friend friend, Category category) {
            category.removeFriend(friend);
            categoryRepository.save(category);

    }

}
