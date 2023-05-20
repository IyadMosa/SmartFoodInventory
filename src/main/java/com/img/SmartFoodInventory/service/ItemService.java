package com.img.SmartFoodInventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.img.SmartFoodInventory.dto.ItemDTO;
import com.img.SmartFoodInventory.dto.UserDTO;
import com.img.SmartFoodInventory.model.Item;
import com.img.SmartFoodInventory.model.MyUser;
import com.img.SmartFoodInventory.repository.ItemRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    private final ModelMapper modelMapper;

    public ItemService(ItemRepository itemRepository, UserService userService, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public ItemDTO add(ItemDTO itemDTO, String username) {

        MyUser user = userService.findByUsername(username);

        Item item = modelMapper.map(itemDTO, Item.class);
        item.setUser(user);
        item.setAddedAt(new Date());

        Item savedFoodItem = itemRepository.save(item);

        return modelMapper.map(savedFoodItem, ItemDTO.class);
    }

    public List<ItemDTO> getAllByUsername(String username) {
        MyUser user = userService.findByUsername(username);
        List<Item> foodItems = itemRepository.findByUser(user);
        return foodItems.stream()
                .map(foodItem -> modelMapper.map(foodItem, ItemDTO.class))
                .collect(Collectors.toList());
    }


    public void deleteItem(Long itemId) throws NotFoundException {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            itemRepository.delete(item);
        } else {
            throw new NotFoundException("Item not found with ID: " + itemId);
        }
    }

    public void loadItems(List<UserDTO> users) throws IOException {
        // read the JSON file from the classpath
        Resource resource = new ClassPathResource("data/stock.json");
        InputStream inputStream = resource.getInputStream();

        // convert the JSON file to a list of Item objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<ItemDTO> items = objectMapper.readValue(inputStream, new TypeReference<List<ItemDTO>>() {
        });

        int userCount = users.size();
        for (int i = 0; i < items.size(); i++) {
            ItemDTO item = items.get(i);
            UserDTO user = users.get(i % userCount);
            add(item, user.getUsername());
        }
    }

    public Item getById(long itemId) {
        return itemRepository.getById(itemId);
    }

    public void save(Item item) {
        itemRepository.save(item);
    }
}
