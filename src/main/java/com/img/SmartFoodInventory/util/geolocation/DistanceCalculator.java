package com.img.SmartFoodInventory.util.geolocation;

import java.util.ArrayList;
import java.util.List;

public class DistanceCalculator {

    //Calculate the distance between two points specified by their latitude and longitude coordinates. The result is returned in kilometers.
    public static double calculateDistance(Geolocation point1, Geolocation point2) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double latDiff = Math.toRadians(point2.getLatitude() - point1.getLatitude());
        double lonDiff = Math.toRadians(point2.getLongitude() - point1.getLongitude());

        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2)
                + Math.cos(Math.toRadians(point1.getLatitude())) * Math.cos(Math.toRadians(point2.getLatitude()))
                * Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double multiplier = Math.pow(10, 2);

        double distance = earthRadius * c;
        return Math.round(distance * multiplier) / multiplier;
    }

    public static List<Geolocation> calculateCoordinatesWithinRadius(Geolocation point, double radius) {
        final double earthRadius = 6371.0; // Radius of the Earth in kilometers

        List<Geolocation> coordinates = new ArrayList<>();

        double lat = Math.toRadians(point.getLatitude());
        double lon = Math.toRadians(point.getLongitude());

        double d = radius / earthRadius; // Angular distance in radians

        // Generate coordinates within the given radius
        for (double angle = 0; angle <= 2 * Math.PI; angle += 0.1) {
            double dx = Math.asin(Math.sin(lat) * Math.cos(d) +
                    Math.cos(lat) * Math.sin(d) * Math.cos(angle));
            double dy = lon + Math.atan2(Math.sin(angle) * Math.sin(d) * Math.cos(lat),
                    Math.cos(d) - Math.sin(lat) * Math.sin(dx));
            coordinates.add(new Geolocation(Math.toDegrees(dx), Math.toDegrees(dy)));
        }

        return coordinates;
    }

    public static boolean isPointWithinRadius(Geolocation firstPoint, Geolocation secondPoint, double radius) {
        double distance = calculateDistance(firstPoint, secondPoint);
        return distance <= radius;
    }

}
