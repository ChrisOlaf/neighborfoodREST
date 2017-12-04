package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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



}
