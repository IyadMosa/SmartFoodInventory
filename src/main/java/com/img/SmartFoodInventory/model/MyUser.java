package com.img.SmartFoodInventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.img.SmartFoodInventory.util.geolocation.Address;
import com.img.SmartFoodInventory.util.geolocation.Geolocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.img.SmartFoodInventory.util.Constants.DATE_STANDER_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_STANDER_FORMAT)
    private Date joinAt;
    private int points;
    @Embedded
    @NotNull
    private Address address;
    @Embedded
    @NotNull
    private Geolocation geolocation;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();
}
