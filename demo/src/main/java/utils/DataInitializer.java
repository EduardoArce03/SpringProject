package utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Category;
import com.example.demo.repositories.CategoryRepository;

@Component
public class DataInitializer implements CommandLineRunner{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("EJECUTANDO HDP");
        // TODO Auto-generated method stub
        Category category = new Category();
        category.setName("xd");
        categoryRepository.save(category);

        Category category2 = new Category();
        category2.setName("pavlo");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setName("jijija");
        categoryRepository.save(category3);
    }
    
}