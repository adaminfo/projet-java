package com.miniprojet.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miniprojet.dao.models.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Integer> {
	/**
	 * Find product by title
	 * 
	 * @param label The title to look for
	 * @return The @see Product
	 */
	Optional<ProductEntity> findByCode(String label);
}
