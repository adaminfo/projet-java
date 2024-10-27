package com.miniprojet.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.miniprojet.dto.request.ProductRequestDto;
import com.miniprojet.dto.response.ProductResponseDto;
import com.miniprojet.services.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final IProductService productService;

	@Operation(summary = "Get a product by ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<ProductResponseDto>> getProductById(@PathVariable int id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@Operation(summary = "Get all products")
	@GetMapping()
	public ResponseEntity<Optional<List<ProductResponseDto>>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@Operation(summary = "Create a product")
	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody @Valid ProductRequestDto product) {
		int insertedProductId = productService.createProduct(product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(insertedProductId)
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Update a product")
	@PatchMapping("/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestBody @Valid ProductRequestDto product) {
		productService.updateProduct(id, product);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.ok().location(location).build();
	}

	@Operation(summary = "Delete a product")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
		this.productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}