package com.img.SmartFoodInventory.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.img.SmartFoodInventory.util.Constants.DATE_STANDER_FORMAT;

@Data
@NoArgsConstructor
public class ItemDTO {
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
}
