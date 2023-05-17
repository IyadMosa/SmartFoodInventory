package com.img.SmartFoodInventory.repository;

import com.img.SmartFoodInventory.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser getById(Long id);

    MyUser getByUsername(String username);

}
