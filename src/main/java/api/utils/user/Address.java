package api.utils.user;

import java.util.Map;

public record Address(String city, String street, int number, String zipcode, Map<String, String> geolocation){}
