package com.img.SmartFoodInventory.util.geolocation;

import com.img.SmartFoodInventory.util.CustomTrustManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class GeocodingAPI {
    private static final String GOOGLE_API_ENDPOINT = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String OPENCAGE_API_ENDPOINT = "https://api.opencagedata.com/geocode/v1/json";


    public static Geolocation getGeolocationByZipCode(String apiKey, String zipCode) throws Exception {

        String urlStr = GOOGLE_API_ENDPOINT + "?address=" + zipCode + "&key=" + apiKey;
        String response = callAPI(urlStr);

        String key = "location";
        // Parse the JSON response
        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("results");
        if (results.length() > 0) {
            JSONObject location = results.getJSONObject(0).getJSONObject("geometry").getJSONObject(key);
            double latitude = location.getDouble("lat");
            double longitude = location.getDouble("lng");

            return new Geolocation(latitude, longitude);

        }

        return null;
    }

    public static Geolocation getOpenCageGeolocation(Address address) throws Exception {
        String apiKey = "5bbe527daf2141ef8bf69506471fa868";
        if (address == null) {
            return new Geolocation();
        }
        String addressStr = getAddressString(address);
        String urlStr = OPENCAGE_API_ENDPOINT + "?q=" + addressStr + "&key=" + apiKey;
        String response = callAPI(urlStr);
        return parseJsonAndGetGeolocation(response);
    }

    private static Geolocation parseJsonAndGetGeolocation(String response) {
        // Parse the JSON response
        JSONObject jsonObject = new JSONObject(response);
        JSONArray results = jsonObject.getJSONArray("results");
        if (results.length() > 0) {
            JSONObject location = results.getJSONObject(0).getJSONObject("geometry");
            double latitude = location.getDouble("lat");
            double longitude = location.getDouble("lng");

            return new Geolocation(latitude, longitude);
        }

        return null;
    }

    private static String callAPI(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        CustomTrustManager.disableSSLValidation();
        // Send HTTP GET request
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        StringBuilder response = new StringBuilder();
        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        return response.toString();
    }

    private static String getAddressString(Address address) {
        String addressStr = null;
        if (StringUtils.hasLength(address.getZipCode())) {
            addressStr = URLEncoder.encode(address.getZipCode(), StandardCharsets.UTF_8);
        } else {
            addressStr = address.toStringWithPlus();
        }
        return addressStr;
    }

}
