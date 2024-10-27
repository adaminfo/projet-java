package com.miniprojet.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.miniprojet.dao.models.ProductEntity;
import com.miniprojet.dao.repositories.IProductRepository;
import com.miniprojet.dto.request.ProductRequestDto;
import com.miniprojet.dto.response.ProductResponseDto;
import com.miniprojet.exceptions.BadRequestException;
import com.miniprojet.exceptions.ResourceNotFoundException;
import com.miniprojet.services.IProductService;
import com.miniprojet.utils.Messages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final IProductRepository productRepository;
	private final ModelMapper modelMapper;
	private static final String PRODUCT_MESSAGE = "RESOURCE_NOT_FOUND";

	@Override
	public Optional<ProductResponseDto> getProductById(int id) {

		ProductEntity productEntity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.format(PRODUCT_MESSAGE, id)));
		return Optional.of(modelMapper.map(productEntity, ProductResponseDto.class));
	}

	@Override
	public Optional<List<ProductResponseDto>> getAllProducts() {

		return Optional.of(productRepository.findAll().stream().map(el -> modelMapper.map(el, ProductResponseDto.class))
				.collect(Collectors.toList()));

	}

	@Override
	public int createProduct(ProductRequestDto productDto) {

		if (productRepository.findByCode(productDto.getCode()).isPresent()) {
			throw new BadRequestException(Messages.format("BAD_REQUEST_CODE", productDto.getCode()));
		}

		ProductEntity productEntity = modelMapper.map(productDto, ProductEntity.class);

		return productRepository.save(productEntity).getId();
	}

	@Override
	public void updateProduct(int id, ProductRequestDto productDto) {

		ProductEntity oldProductEntity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.format(PRODUCT_MESSAGE, id)));

		if (!oldProductEntity.getCode().equals(productDto.getCode())
				&& productRepository.findByCode(productDto.getCode()).isPresent()) {
			throw new BadRequestException(Messages.format("BAD_REQUEST_CODE", productDto.getCode()));
		}

		if (productDto.getCode() != null) {
			oldProductEntity.setCode(productDto.getCode());
		}
		if (productDto.getName() != null) {
			oldProductEntity.setName(productDto.getName());
		}
		if (productDto.getDescription() != null) {
			oldProductEntity.setDescription(productDto.getDescription());
		}
		if (productDto.getImage() != null) {
			oldProductEntity.setImage(productDto.getImage());
		}
		if (productDto.getCategory() != null) {
			oldProductEntity.setCategory(productDto.getCategory());
		}
		if (productDto.getPrice() != null) {
			oldProductEntity.setPrice(productDto.getPrice());
		}
		if (productDto.getQuantity() != null) {
			oldProductEntity.setQuantity(productDto.getQuantity());
		}
		if (productDto.getInternalReference() != null) {
			oldProductEntity.setInternalReference(productDto.getInternalReference());
		}
		if (productDto.getShellId() != null) {
			oldProductEntity.setShellId(productDto.getShellId());
		}
		if (productDto.getInventoryStatus() != null) {
			oldProductEntity.setInventoryStatus(productDto.getInventoryStatus());
		}
		if (productDto.getRating() != null) {
			oldProductEntity.setRating(productDto.getRating());
		}
		if (productDto.getCreatedAt() != null) {
			oldProductEntity.setCreatedAt(productDto.getCreatedAt());
		}
		if (productDto.getUpdatedAt() != null) {
			oldProductEntity.setUpdatedAt(productDto.getUpdatedAt());
		}

		productRepository.save(oldProductEntity);
	}

	@Override
	public void deleteProduct(int id) {

		ProductEntity productEntity = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(Messages.format(PRODUCT_MESSAGE, id)));
		productRepository.delete(productEntity);
	}
}
