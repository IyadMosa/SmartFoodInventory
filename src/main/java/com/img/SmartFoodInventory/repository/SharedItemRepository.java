package com.img.SmartFoodInventory.repository;

import com.img.SmartFoodInventory.model.SharedItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SharedItemRepository extends JpaRepository<SharedItem, Long> {

    SharedItem getById(Long id);
}





