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

    @PostMapping("/verify")
    public User checkLogin(@RequestBody User user){
        List<User> users = (List<User>)userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if(user.getEmail().equals(users.get(i).getEmail())
                    &&user.getPassword().equals(users.get(i).getPassword()))
                return users.get(i);
        }
        return null;
    }

}
