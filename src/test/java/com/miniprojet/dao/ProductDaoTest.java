package com.miniprojet.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.miniprojet.dto.request.ProductRequestDto;
import com.miniprojet.dto.response.ProductResponseDto;
import com.miniprojet.dto.shared.InventoryStatus;
import com.miniprojet.exceptions.ResourceNotFoundException;
import com.miniprojet.services.IProductService;
import com.miniprojet.utils.Messages;

class ProductDaoTest extends AbstractConfig {

	@Autowired
	private IProductService productService;

	@Test
	public void testSaveProductWithH2() {
		ProductRequestDto productDto = new ProductRequestDto("P00X", "Le Petit Prince", "Un livre sur un jeune prince",
				"image1.jpg", "Litterature", 15.99f, 100, "REF001", 1, InventoryStatus.INSTOCK, 4.5f, 11L, 20L);

		int productSaveId = productService.createProduct(productDto);

		Optional<ProductResponseDto> productResponse = productService.getProductById(productSaveId);

		assertEquals("Le Petit Prince", productResponse.get().getName());
		assertEquals("Un livre sur un jeune prince", productResponse.get().getDescription());
	}

	@Test
	public void testDeleteProductWithH2() {
		ProductRequestDto productDto = new ProductRequestDto("P00X", "Le Petit Prince", "Un livre sur un jeune prince",
				"image1.jpg", "Littérature", 15.99f, 100, "REF001", 1, InventoryStatus.INSTOCK, 4.5f, 11L, 20L);

		int productSaveId = productService.createProduct(productDto);

		productService.deleteProduct(productSaveId);

		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {

			productService.getProductById(productSaveId);

		});

		String expectedMessage = Messages.format("RESOURCE_NOT_FOUND", productSaveId);

		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

}
