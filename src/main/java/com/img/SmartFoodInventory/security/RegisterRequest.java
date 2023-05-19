package com.img.SmartFoodInventory.security;

import com.img.SmartFoodInventory.util.geolocation.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Address address;

}
