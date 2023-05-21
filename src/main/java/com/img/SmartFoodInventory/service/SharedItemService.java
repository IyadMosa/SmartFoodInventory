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


    @Transactional
    public synchronized void shareItem(String sharerUsername, long itemId, int quantity) {
        Item item = itemService.getById(itemId);
        if (item == null) {
            return;
        }

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
    public synchronized void acceptSharedItem(String recipientUsername, long sharedItemId) {

        SharedItem sharedItem = sharedItemRepository.getById(sharedItemId);
        if (sharedItem == null || sharedItem.isConsumed()) {
            return;
        }

        // Update the recipient user
        MyUser recipient = userService.findByUsername(recipientUsername);
        recipient.setPoints(recipient.getPoints() - 1);
        // Update the item owner - user
        sharedItem.getItem().setUser(recipient);
        recipient.getItems().add(sharedItem.getItem());

        // Update the shared item
        sharedItem.setConsumed(true);
        sharedItem.setConsumingDate(new Date());

        // Update the sharer user points
        MyUser sharer = sharedItem.getSharer();
        sharer.setPoints(sharer.getPoints() + 1);

        // Save the changes to the UserRepository and SharedItemRepository
        userService.save(recipient);
        userService.save(sharer);
        itemService.save(sharedItem.getItem());
        sharedItemRepository.save(sharedItem);
    }

    public List<SharedItem> getAll(String username, int radiusKm) {
        MyUser user = userService.findByUsername(username);

        List<SharedItem> sharedItems = sharedItemRepository.findAll();
        // Filter shared items based on location
        List<SharedItem> itemsInRange = sharedItems.stream()
                .filter(sharedItem -> {
                    Geolocation sharerLocation = sharedItem.getSharer().getGeolocation();
                    return DistanceCalculator.isPointWithinRadius(user.getGeolocation(), sharerLocation, radiusKm);
                })
                .collect(Collectors.toList());
        return itemsInRange;
    }
}
