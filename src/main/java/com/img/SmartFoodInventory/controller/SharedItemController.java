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

    @PostMapping("/share/{itemId}")
    public ResponseEntity<String> shareItem(Principal principal, @PathVariable long itemId,
                                            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        String sharerUsername = principal.getName(); // Retrieve the username from the authenticated user
        sharedItemService.shareItem(sharerUsername, itemId, quantity);
        return ResponseEntity.ok("Item shared successfully");

    }

    @GetMapping("/available/{radius}")
    public ResponseEntity<List<SharedItem>> getAvailableSharedItems(Principal principal, @PathVariable int radius) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        List<SharedItem> sharedItems = sharedItemService.getAvailableSharedItems(username,radius);
        return ResponseEntity.ok(sharedItems);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SharedItem>> getAllSharedItems() {
        List<SharedItem> sharedItems = sharedItemService.getAllSharedItems();
        return ResponseEntity.ok(sharedItems);
    }


    @PostMapping("/request/{sharedItemId}")
    public ResponseEntity<String> requestSharedItem(Principal principal, @PathVariable long sharedItemId) {
        String recipientUsername = principal.getName(); // Retrieve the username from the authenticated user
        sharedItemService.requestSharedItem(recipientUsername, sharedItemId);
        return ResponseEntity.ok("Request shared item successfully");
    }

    @PostMapping("/confirm/{sharedItemId}")
    public ResponseEntity<String> confirmSharedItem(Principal principal, @PathVariable long sharedItemId) {
        String sharerUsername = principal.getName(); // Retrieve the username from the authenticated user
        sharedItemService.confirmSharedItem(sharerUsername, sharedItemId);
        return ResponseEntity.ok("Confirm shared item successfully");
    }


    @GetMapping("/requested")
    public ResponseEntity<List<SharedItem>> getRequestedSharedItems(Principal principal) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        List<SharedItem> sharedItems = sharedItemService.getRequestedSharedItems(username);
        return ResponseEntity.ok(sharedItems);
    }

    @GetMapping("/to-confirm")
    public ResponseEntity<List<SharedItem>> getToConfirmSharedItems(Principal principal) {
        String username = principal.getName(); // Retrieve the username from the authenticated user
        List<SharedItem> sharedItems = sharedItemService.getToConfirmSharedItems(username);
        return ResponseEntity.ok(sharedItems);
    }
}

