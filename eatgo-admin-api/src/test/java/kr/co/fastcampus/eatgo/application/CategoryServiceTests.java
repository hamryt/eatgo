package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;
import kr.co.fastcampus.eatgo.domain.Region;
import kr.co.fastcampus.eatgo.domain.RegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class CategoryServiceTests {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories(){
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("korean").build());

        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> categories = categoryService.getCategories();

        Category category = categories.get(0);
        assertEquals(category.getName(), "korean");
    }

    @Test
    public void addCategory(){
        Category category = categoryService.addCategory("korean");

        verify(categoryRepository).save(any());

        assertEquals(category.getName(), "korean");
    }

}