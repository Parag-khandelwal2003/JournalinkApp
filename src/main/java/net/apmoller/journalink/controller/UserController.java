package net.apmoller.journalink.controller;

import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.repository.UserRepository;
import net.apmoller.journalink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String index(){
        return "Welcome User!\nThis is your page. Here you will see all entries.\nAll Endpoints:\n/all-users,\n/create-user";
    }

    @GetMapping("/all-users")
    public List<User> showAllUserEntries(){
        return userService.showAllUsers();
    }

//    @PostMapping("/create-user")
//    public void createUser(@RequestBody User user){
//        userService.saveUser(user);
//    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);

        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userInDB.setRoles(user.getRoles());
        userService.saveUser(userInDB);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(@PathVariable Long uid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
