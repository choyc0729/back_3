package com.example.board_like.controller;

import com.example.board_like.model.Category;
import com.example.board_like.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = Arrays.asList(
                //ew Category("Technology"),
                //new Category("Health"),
                //new Category("Science"),
                //new Category("Education"),
                //new Category("Entertainment")
        );

        for (Category category : categories) {
            if (!categoryRepository.existsByCategoryName(category.getCategoryName())) {
                categoryRepository.save(category);
            }
        }
    }
}
