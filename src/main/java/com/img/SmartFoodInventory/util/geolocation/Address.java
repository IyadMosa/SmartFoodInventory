package com.img.SmartFoodInventory.util.geolocation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String country;
    private String city;
    private String street;
    private String zipCode;


    public String toStringWithPlus() {
        StringBuilder sb = new StringBuilder();

        if (country != null) {
            sb.append(country.replace(" ", "+")).append("+");
        }
        if (city != null) {
            sb.append(city.replace(" ", "+")).append("+");
        }
        if (street != null) {
            sb.append(street.replace(" ", "+")).append("+");
        }

        // Remove the trailing "+" sign if any
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '+') {
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }
}
