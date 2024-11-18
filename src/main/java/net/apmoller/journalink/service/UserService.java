package net.apmoller.journalink.service;

import net.apmoller.journalink.entity.JournalEntry;
import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){

        return userRepository.findById(id);
    }

    public boolean saveUser(User userEntry){
        try {
            userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
            userEntry.setRoles(List.of("USER"));
            userRepository.save(userEntry);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void saveNewUser(User userEntry){
        userRepository.save(userEntry);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }

    public void saveAdminUser(User userEntry){
        userEntry.setPassword(passwordEncoder.encode(userEntry.getPassword()));
        userEntry.setRoles(List.of("USER", "ADMIN"));
        userRepository.save(userEntry);
    }

}
