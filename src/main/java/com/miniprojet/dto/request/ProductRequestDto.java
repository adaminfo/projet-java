package com.miniprojet.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.miniprojet.dto.shared.InventoryStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRequestDto {

	@NotNull(message = "Code could not be null")
	@Pattern(regexp = ".*\\S.*", message = "Code could not be blank")
	@Size(min = 0, max = 255, message = "The value entered for field code is too long. The maximum length authorized is 255")
	private String code;

	@Size(min = 0, max = 255, message = "The value entered for field name is too long. The maximum length authorized is 255")
	private String name;

	@Size(min = 0, max = 2056, message = "The value entered for field description is too long. The maximum length authorized is 2056")
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
