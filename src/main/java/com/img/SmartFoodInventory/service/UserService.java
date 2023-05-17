package com.img.SmartFoodInventory.service;

import com.img.SmartFoodInventory.dto.UserDTO;
import com.img.SmartFoodInventory.model.MyUser;
import com.img.SmartFoodInventory.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        MyUser user = userRepository.getByUsername(name);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public UserDTO createUser(UserDTO userDTO) {
        MyUser user = mapToUserEntity(userDTO);
        MyUser savedUser = userRepository.save(user);
        return mapToUserDTO(savedUser);
    }

    public UserDTO getUserById(Long userId) throws NotFoundException {
        MyUser user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
        return mapToUserDTO(user);
    }

    public UserDTO getUserByName(String name)  {
        MyUser user = userRepository.getByUsername(name);
        if(user == null){
            return null;
        }
        return mapToUserDTO(user);
    }

    // Other service methods...

    private MyUser mapToUserEntity(UserDTO userDTO) {
        MyUser user = new MyUser();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setJoinAt(userDTO.getJoinAt());
        user.setPoints(userDTO.getPoints());

        return user;
    }

    private UserDTO mapToUserDTO(MyUser user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setJoinAt(user.getJoinAt());
        userDTO.setPoints(user.getPoints());

        return userDTO;
    }

    public void setUserAsLoggedStatus(String username, boolean b) {
    }


    public List<UserDTO> getAllUsers() {
        List<MyUser> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToUserDTO)
                .collect(Collectors.toList());
    }
}
