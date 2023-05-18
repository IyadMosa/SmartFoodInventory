package com.img.SmartFoodInventory;

import com.img.SmartFoodInventory.dto.UserDTO;
import com.img.SmartFoodInventory.model.MyUser;
import com.img.SmartFoodInventory.service.ItemService;
import com.img.SmartFoodInventory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class SmartFoodInventoryApplication {

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(SmartFoodInventoryApplication.class, args);
	}

	@PostConstruct
	public void init() throws IOException {
		List<UserDTO> users =  userService.loadUsers();
		itemService.loadItems(users);
	}
}
