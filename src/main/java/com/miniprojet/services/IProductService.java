package com.miniprojet.services;

import java.util.List;
import java.util.Optional;

import com.miniprojet.dto.request.ProductRequestDto;
import com.miniprojet.dto.response.ProductResponseDto;

public interface IProductService {
	Optional<ProductResponseDto> getProductById(int id);

	Optional<List<ProductResponseDto>> getAllProducts();

	int createProduct(ProductRequestDto productDto);

	void updateProduct(int id, ProductRequestDto productDto);

	void deleteProduct(int id);
}
