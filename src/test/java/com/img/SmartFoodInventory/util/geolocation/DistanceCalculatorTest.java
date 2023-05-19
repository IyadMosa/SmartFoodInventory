package com.img.SmartFoodInventory.util.geolocation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DistanceCalculatorTest {
    @Test
    public void testCalculateDistance_SamePoint() {
        // Arrange
        Geolocation point1 = new Geolocation(37.7749, -122.4194); // San Francisco
        Geolocation point2 = new Geolocation(37.7749, -122.4194); // San Francisco

        // Act
        double distance = DistanceCalculator.calculateDistance(point1, point2);

        // Assert
        Assertions.assertEquals(0.0, distance, 0.001); // Expected distance: 0 km
    }

    @Test
    public void testCalculateDistance_DifferentPoints() {
        // Arrange
        Geolocation point1 = new Geolocation(37.7749, -122.4194); // San Francisco
        Geolocation point2 = new Geolocation(34.0522, -118.2437); // Los Angeles

        // Act
        double distance = DistanceCalculator.calculateDistance(point1, point2);

        // Assert
        Assertions.assertEquals(559.12, distance, 0.01); // Expected distance: 559.12 km
    }
    @Test
    public void testCalculateDistance_Jerusalem_Bethlehem() {
        // Arrange
        Geolocation point1 = new Geolocation(31.7857, 35.2007); // Jerusalem
        Geolocation point2 = new Geolocation(31.705751, 	35.202661); // Bethlehem

        // Act
        double distance = DistanceCalculator.calculateDistance(point1, point2);

        // Assert
        Assertions.assertEquals(8.89, distance, 0.5); // Expected distance: 8.89- 9.8 km
    }
    @Test
    public void testCalculateCoordinatesWithinRadius() {
        // Arrange
        Geolocation point1 = new Geolocation(31.7857, 35.2007); // Jerusalem
        double radius = 5.0;           // Radius in kilometers

        // Act
        List<Geolocation> coordinates = DistanceCalculator.calculateCoordinatesWithinRadius(point1, radius);

        // Assert
        Assertions.assertEquals(63, coordinates.size()); // Expected number of coordinates: 63
    }

    @Test
    public void testIsPointWithinRadius() {
        // Arrange
        Geolocation firstPoint = new Geolocation(31.7857, 35.2007); // Jerusalem
        Geolocation secondPoint = new Geolocation(31.705751, 35.202661);// Bethlehem
        double radius = 10.0;

        // Act
        boolean result = DistanceCalculator.isPointWithinRadius(firstPoint, secondPoint, radius);

        // Assert
        Assertions.assertTrue(result);
    }
    @Test
    public void testIsPointWithinRadius_not() {
        // Arrange
        Geolocation firstPoint = new Geolocation(31.7857, 35.2007); // Jerusalem
        Geolocation secondPoint = new Geolocation(32.08530, 34.78177);// TelAviv
        double radius = 10.0;

        // Act
        boolean result = DistanceCalculator.isPointWithinRadius(firstPoint, secondPoint, radius);

        // Assert
        Assertions.assertFalse(result);
    }

    @Test
    public void testIsPointWithinRadius_100km() {
        // Arrange
        Geolocation firstPoint = new Geolocation(32.79404, 	34.98957); //Haifa
        Geolocation secondPoint = new Geolocation(32.08530, 34.78177);// TelAviv
        double radius = 100.0;

        // Act
        boolean result = DistanceCalculator.isPointWithinRadius(firstPoint, secondPoint, radius);

        // Assert
        Assertions.assertTrue(result);
    }

}