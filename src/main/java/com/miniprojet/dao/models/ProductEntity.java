package com.miniprojet.dao.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.miniprojet.dto.shared.InventoryStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 255)
	private String code;

	@Column(length = 255)
	private String name;

	@Column(length = 2056)
	private String description;

	private String image;

	private String category;

	private Float price;

	private Integer quantity;

	private String internalReference;

	private Integer shellId;

	@Enumerated(EnumType.STRING)
	private InventoryStatus inventoryStatus;

	private Float rating;

	private Long createdAt;

	private Long updatedAt;

}
