package com.img.SmartFoodInventory.service;

import com.img.SmartFoodInventory.model.Item;
import com.img.SmartFoodInventory.model.MyUser;
import com.img.SmartFoodInventory.model.SharedItem;
import com.img.SmartFoodInventory.repository.SharedItemRepository;
import com.img.SmartFoodInventory.util.geolocation.DistanceCalculator;
import com.img.SmartFoodInventory.util.geolocation.Geolocation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SharedItemService {
    private final SharedItemRepository sharedItemRepository;

    private final UserService userService;
    private final ItemService itemService;

    public SharedItemService(SharedItemRepository sharedItemRepository, UserService userService, ItemService itemService) {
        this.sharedItemRepository = sharedItemRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    public List<SharedItem> getAvailableSharedItems(String username, int radiusKm) {
        MyUser user = userService.findByUsername(username);

        List<SharedItem> sharedItems = sharedItemRepository.findAll();
        // Filter shared items based on location
        List<SharedItem> itemsInRange = sharedItems.stream()
                .filter(sharedItem -> {
                    Geolocation sharerLocation = sharedItem.getSharer().getGeolocation();
                    return DistanceCalculator.isPointWithinRadius(user.getGeolocation(), sharerLocation, radiusKm);
                })
                .filter(sharedItem -> !sharedItem.isRequested())
                .collect(Collectors.toList());
        return itemsInRange;
    }


    @Transactional
    public synchronized void shareItem(String sharerUsername, long itemId, int quantity) {
        Item item = itemService.getById(itemId);
        if (item == null) {
            return;
        }
        item.appendMessageToTrackingLogs("Item shared by " + sharerUsername + " with quantity of " + quantity + ".");
        MyUser sharer = userService.findByUsername(sharerUsername);

        // Create a new SharedItem object
        SharedItem sharedItem = new SharedItem();
        sharedItem.setSharer(sharer);
        sharedItem.setItem(item);
        sharedItem.setQuantity(quantity);
        sharedItem.setSharingDate(new Date());

        // Save the shared item
        sharedItemRepository.save(sharedItem);
    }

    @Transactional
    public synchronized void requestSharedItem(String recipientUsername, long sharedItemId) {

        SharedItem sharedItem = sharedItemRepository.getById(sharedItemId);
        if (sharedItem == null || sharedItem.isRequested()) {
            return;
        }

        sharedItem.getItem().appendMessageToTrackingLogs("Item requested by user " + recipientUsername + ".");
        // Update the recipient user
        MyUser recipient = userService.findByUsername(recipientUsername);
        // Update the shared item
        sharedItem.setRequested(true);
        sharedItem.setRequestedDate(new Date());
        sharedItem.setRecipient(recipient);

        // Save the changes to the UserRepository and SharedItemRepository
        userService.save(recipient);
        sharedItemRepository.save(sharedItem);
    }


    public void confirmSharedItem(String sharerUsername, long sharedItemId) {
        SharedItem sharedItem = sharedItemRepository.getById(sharedItemId);
        if (sharedItem == null || sharedItem.isConfirmed()
                || !sharedItem.getSharer().getUsername().equals(sharerUsername)) {
            return;
        }

        // Update the shared item
        sharedItem.setConfirmed(true);
        sharedItem.setConfirmedDate(new Date());

        // Update the sharer user points
        MyUser sharer = sharedItem.getSharer();
        sharer.setPoints(sharer.getPoints() + 1);

        sharedItem.getItem().appendMessageToTrackingLogs("Item request confirmed by owner " + sharerUsername + ".");

        // Update the recipient user
        MyUser recipient = sharedItem.getRecipient();
        recipient.setPoints(recipient.getPoints() - 1);
        // Update the item owner - user
        sharedItem.getItem().setUser(recipient);
        recipient.getItems().add(sharedItem.getItem());
        sharedItem.getItem().appendMessageToTrackingLogs("Item transferred to new owner " + recipient.getUsername() + "." );


        // Save the changes to the UserRepository and SharedItemRepository
        userService.save(recipient);
        userService.save(sharer);
        itemService.save(sharedItem.getItem());
        sharedItemRepository.save(sharedItem);
    }

    public List<SharedItem> getRequestedSharedItems(String username) {
        return sharedItemRepository.findByRequestedTrueAndConfirmedFalseAndRecipient_Username(username);
    }

    public List<SharedItem> getToConfirmSharedItems(String username) {
        return sharedItemRepository.findByRequestedTrueAndConfirmedFalseAndSharer_Username(username);
    }

    public List<SharedItem> getAllSharedItems() {
        return sharedItemRepository.findAll();
    }
}
