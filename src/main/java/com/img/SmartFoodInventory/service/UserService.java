package com.img.SmartFoodInventory.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.img.SmartFoodInventory.dto.UserDTO;
import com.img.SmartFoodInventory.model.MyUser;
import com.img.SmartFoodInventory.repository.UserRepository;
import com.img.SmartFoodInventory.util.geolocation.GeocodingAPI;
import com.img.SmartFoodInventory.util.geolocation.Geolocation;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MyUser user = userRepository.getByUsername(name);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public UserDTO createUser(UserDTO userDTO) {
        Geolocation geolocation = new Geolocation();
        try {
            geolocation = GeocodingAPI.getOpenCageGeolocation(userDTO.getAddress());
        } catch (Exception e) {

        }
        MyUser user = modelMapper.map(userDTO, MyUser.class);
        user.setJoinAt(new Date());
        user.setGeolocation(geolocation);
        MyUser savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO getUserByName(String name) {
        MyUser user = userRepository.getByUsername(name);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    public List<UserDTO> getAllUsers() {
        List<MyUser> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public MyUser findByUsername(String username) {
        MyUser user = userRepository.getByUsername(username);
        return user;
    }

    public List<UserDTO> loadUsers() throws IOException {
        // read the JSON file from the classpath
        Resource resource = new ClassPathResource("data/users.json");
        InputStream inputStream = resource.getInputStream();

        // convert the JSON file to a list of Item objects
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserDTO> users = objectMapper.readValue(inputStream, new TypeReference<List<UserDTO>>() {
        });

        List<UserDTO> updatedUsers = users.stream().flatMap(user -> {
            try {
                Thread.sleep(2000);
                return Stream.of(createUser(user));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Stream.empty();
            }
        }).collect(Collectors.toList());

        return updatedUsers;
    }

    public void save(MyUser user) {
        userRepository.save(user);
    }
}
