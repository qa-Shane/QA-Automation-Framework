package api.utils.user;

import java.util.Map;

public record UserAPI(int id, String email, String username, String password, Map<String, String> name, Address address, String phone) {}