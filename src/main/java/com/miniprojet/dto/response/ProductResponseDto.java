package com.miniprojet.dto.response;

import com.miniprojet.dto.shared.InventoryStatus;

import lombok.Data;

@Data
public class ProductResponseDto {

	private int id;

	private String code;

	private String name;

	private String description;

	private String image;

	private String category;

	private Float price;

	private Integer quantity;

	private String internalReference;

	private Integer shellId;

	private InventoryStatus inventoryStatus;

	private Float rating;

	private Long createdAt;

	private Long updatedAt;

}
