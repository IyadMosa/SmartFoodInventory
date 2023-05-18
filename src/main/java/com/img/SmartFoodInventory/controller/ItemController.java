package com.img.SmartFoodInventory.controller;

import com.img.SmartFoodInventory.dto.ItemDTO;
import com.img.SmartFoodInventory.service.ItemService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody ItemDTO itemDto, Principal principal) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        ItemDTO createdItem = itemService.add(itemDto, username);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @GetMapping
    public List<ItemDTO> getAll( Principal principal) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        return itemService.getAllByUsername(username);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable Long itemId) throws NotFoundException {
        itemService.deleteItem(itemId);
        return ResponseEntity.ok().build();
    }
}
