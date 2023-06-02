package com.img.SmartFoodInventory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.img.SmartFoodInventory.util.geolocation.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.img.SmartFoodInventory.util.Constants.DATE_STANDER_FORMAT;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_STANDER_FORMAT)
    private Date joinAt;
    private int points;
    private Address address;
    private String deviceToken;


    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
