package com.img.SmartFoodInventory.controller;

import com.img.SmartFoodInventory.model.SharedItem;
import com.img.SmartFoodInventory.service.SharedItemService;
import com.img.SmartFoodInventory.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/shared-items")
public class SharedItemController {

    private final SharedItemService sharedItemService;
    private final UserService userService;

    public SharedItemController(SharedItemService sharedItemService, UserService userService) {
        this.sharedItemService = sharedItemService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<SharedItem>> getAll() {
        List<SharedItem> sharedItems = sharedItemService.getAll();
        return ResponseEntity.ok(sharedItems);
    }

    @PostMapping("/share/{itemId}")
    public void shareItem(Principal principal, @PathVariable long itemId,
                          @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        String sharerUsername = principal.getName(); // Retrieve the username from the authenticated user
        sharedItemService.shareItem(sharerUsername, itemId, quantity);
    }

    @PostMapping("/accept/{sharedItemId}")
    public void acceptSharedItem(Principal principal, @PathVariable long sharedItemId) {
        String recipientUsername = principal.getName(); // Retrieve the username from the authenticated user
        sharedItemService.acceptSharedItem(recipientUsername, sharedItemId);
    }
}

