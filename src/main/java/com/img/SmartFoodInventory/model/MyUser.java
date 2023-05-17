package com.img.SmartFoodInventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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
}
