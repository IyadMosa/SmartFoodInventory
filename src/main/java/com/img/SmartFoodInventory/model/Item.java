package com.img.SmartFoodInventory.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.img.SmartFoodInventory.util.Constants.DATE_STANDER_FORMAT;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    private String name;
    private String barcode;
    private String manufacturer;
    private String productionCountry;
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_STANDER_FORMAT)
    private Date addedAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_STANDER_FORMAT)
    private Date expirationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private MyUser user;
    @Lob
    private String trackingLogs;

    public void appendMessageToTrackingLogs(String message) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);
        String formattedMessage = "[" + formattedDateTime + "] " + message + "\n";

        if (trackingLogs == null) {
            trackingLogs = formattedMessage;
        } else {
            trackingLogs += formattedMessage;
        }
    }
}
