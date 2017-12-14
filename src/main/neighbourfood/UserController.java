package main.neighbourfood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allusers")
    public Iterable<User> naytaKaikki() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}/reviews")
    public List<Review> reviewsForUser(@PathVariable(name = "id")int target_id) {
        User target = userRepository.findOne(target_id);
        return userRepository.findAllReviewsForUser(target);
    }

    @GetMapping("/user/{id}")
    public User yksiKayttaja(@PathVariable(name="id") int user_id){
        return userRepository.findOne(user_id);
    }

    @GetMapping("/allchefs")
    public Iterable<User> allChefs() {
        List<User> kaikki = (List<User>)  userRepository.findAll();
        List<User> kokit = new ArrayList<>();
        for (User kokki: kaikki) {
            if("chef".equals(kokki.getUserStatus())){
                kokit.add(kokki);
            }
        }
        Iterable<User> kokkeja = (Iterable<User>) kokit;
        return kokkeja;
    }

    @PostMapping("/adduser")
    public User lisaaYksi(@RequestBody User user) {
        String eINPUT = user.getEmail();
        String eREGEX = "[a-zåöä0-9]{1,}[a-zåöä0-9 \\- ._]{0,}@[a-z0-9]{2,20}\\.(fi|com|net|info|org)";
        Pattern patternE = Pattern.compile(eREGEX);
        Matcher matcherE = patternE.matcher(eINPUT);

        String nINPUT = user.getName() + user.getLastName();
        String nREGEX = "^[a-zåäöA-ZÅÄÖ \\-\\.\\']*$";
        Pattern patternN = Pattern.compile(nREGEX);
        Matcher matcherN = patternN.matcher(nINPUT);

        String pwINPUT = user.getPassword();
        String pwREGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$";
        Pattern patternPW = Pattern.compile(pwREGEX);
        Matcher matcherPW = patternPW.matcher(pwINPUT);

        if (nINPUT.length() < 5) {
            User x = new User();
            x.setName("lyhyt");
            return x;
        }

        if (user.getPhoneNumber().length() < 5) {
            User x = new User();
            x.setName("numero");
            return x;
        }

        while (!matcherN.lookingAt() || !matcherN.matches()) {
            patternN = Pattern.compile(nREGEX);
            matcherN = patternN.matcher(nINPUT);
            if (!matcherN.lookingAt() || !matcherN.matches()) {
                User x = new User();
                x.setName("virhe");
                return x;
            }
        }

        while (!matcherPW.lookingAt() || !matcherPW.matches()) {
            patternPW = Pattern.compile(pwREGEX);
            matcherPW = patternPW.matcher(pwINPUT);
            if (!matcherPW.lookingAt() || !matcherPW.matches()) {
                User x = new User();
                x.setName("ssana");
                return x;
            }
        }

        while (!matcherE.lookingAt() || !matcherE.matches()) {
            eINPUT = eINPUT.toLowerCase().replace(" ", "");
            patternE = Pattern.compile(eREGEX);
            matcherE = patternE.matcher(eINPUT);
            if (!matcherE.lookingAt() || !matcherE.matches()) {
                User x = new User();
                x.setEmail("virhe");
                return x;
            } else {
                user.setEmail(eINPUT);
            }
        }

        List<User> users = (List<User>) userRepository.findAll();
        for (User u : users) {
            if (user.getEmail().equals(u.getEmail())) {
                User x = new User();
                return x;
            }
        }

        userRepository.save(user);
        return user;
    }

    @PostMapping("/removeuser")
    public void poistaYksi(@RequestBody User user) {
        userRepository.delete(user);
    }


    // Verifies user login and returns either user information, or in case of wrong user details, empty user.
    @PostMapping("/verify")
    public User checkLogin(@RequestBody Verify verify) {
        List<User> users = (List<User>) userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            if (verify.getEmail().equals(users.get(i).getEmail())
                    &&
                    verify.getPassword().equals(users.get(i).getPassword()))
                return users.get(i);
        }
        User user = new User();
        return user;
    }

    @GetMapping("/getuser")
    public User getUser(@RequestParam int i) {
        User user = userRepository.findOne(i);
        user.setPassword("ei saa kertoa");
        return user;
    }

    @Transactional
    @PutMapping("/userpresentation")
    public User modifyUser(@RequestBody User user){
        int integer = user.getId();
        String presentation = user.getPresentation();

        userRepository.modifyUser(presentation, integer);

        return userRepository.findOne(integer);
    }
}

class Verify {
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
