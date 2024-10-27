package com.miniprojet.servicesTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.miniprojet.dao.models.ProductEntity;
import com.miniprojet.dao.repositories.IProductRepository;
import com.miniprojet.services.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
	@Spy
	private ModelMapper modelMapper;
	@Mock
	private IProductRepository productRepository;
	@InjectMocks
	private ProductServiceImpl productService;
	private EasyRandom generator;

	private static final int SEARCH_PRODUCT_ID = 2;

	@BeforeEach
	void setUp() {
		generator = new EasyRandom();
	}

	@Test
	void shouldGetProductById() {
		ProductEntity ProductEntity = generator.nextObject(ProductEntity.class);

		when(productRepository.findById(SEARCH_PRODUCT_ID)).thenReturn(Optional.of(ProductEntity));

		productService.getProductById(SEARCH_PRODUCT_ID);

		verify(productRepository, times(1)).findById(Mockito.anyInt());
	}

	@Test
	void shouldDeleteProduct() {
		ProductEntity ProductEntity = generator.nextObject(ProductEntity.class);

		when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(ProductEntity));

		productService.deleteProduct(ProductEntity.getId());

		verify(productRepository, times(1)).delete(ProductEntity);
	}

}
