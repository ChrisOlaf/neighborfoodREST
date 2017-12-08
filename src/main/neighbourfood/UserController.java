package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allusers")
    public Iterable<User> naytaKaikki(){
        return userRepository.findAll();
    }

    @PostMapping("/adduser")
    public void lisaaYksi(@RequestBody User user){
        userRepository.save(user);
    }

    @PostMapping("/removeuser")
    public void poistaYksi(@RequestBody User user){
        userRepository.delete(user);
    }


    // Verifies user login and returns either user information, or in case of wrong user details, empty user.
    @PostMapping("/verify")
    public User checkLogin(@RequestBody Verify verify){
        List<User> users = (List<User>)userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if(verify.getEmail().equals(users.get(i).getEmail())
                    &&
                    verify.getPassword().equals(users.get(i).getPassword()))
                return users.get(i);
        }
        User user = new User();
        return user;
    }

    @GetMapping("/getuser")
    public User getUser(@RequestParam int i){
        User user = userRepository.findOne(i);
        user.setPassword("ei saa kertoa");
        return user;
    }
}

class Verify{
    private String email;
    private String password;

    public Verify() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Verify(String email, String password) {
        this.email = email;
        this.password = password;


    }
}
