package com.img.SmartFoodInventory.repository;

import com.img.SmartFoodInventory.model.SharedItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SharedItemRepository extends JpaRepository<SharedItem, Long> {

    SharedItem getById(Long id);
    List<SharedItem> findByRequestedTrueAndConfirmedFalseAndSharer_Username(String username);
    List<SharedItem> findByRequestedTrueAndConfirmedFalseAndRecipient_Username(String username);
}





