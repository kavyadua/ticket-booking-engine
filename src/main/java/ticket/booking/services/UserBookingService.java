package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import ticket.booking.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.io.File;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "../localDb/users.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }




}
