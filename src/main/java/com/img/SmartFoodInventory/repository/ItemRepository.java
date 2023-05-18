package com.img.SmartFoodInventory.repository;

import com.img.SmartFoodInventory.model.Item;
import com.img.SmartFoodInventory.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByUser(MyUser user);

}
