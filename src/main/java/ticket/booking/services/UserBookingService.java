package ticket.booking.services;
import com.fasterxml.jackson.core.type.TypeReference;
import ticket.booking.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.util.Optional;

public class UserBookingService {
    private User user;
    private List<User> userList;
    private static final String USERS_PATH = "src\\main\\java\\ticket\\booking\\localDb\\users.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USERS_PATH), new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USERS_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBookings() {
        Optional<User> userFetched = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        if(userFetched.isPresent()){
            userFetched.get().printTickets();
        }
    }

    public Boolean cancelBookings(String ticketId) {
        return Boolean.FALSE;
    }
}
