package net.apmoller.journalink.service;

import jakarta.transaction.Transactional;
import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindByUserName(){
//        assertEquals(5, 3+1);
        assertNotNull(userRepository.findByUserName("parag"));
    }

    @Test
    public void checkUserJournalEntriesTest(){
        User user = userRepository.findByUserName("user");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2", // a = 1, b = 1, expected = 2
        "2,10,12",
        "5,5,10"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "parag",
            "admin"
    })
    public void testUsers(String name){
        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
    }

    @Test
    public void testUserService(){
        User user = new User();
        user.setUserName("chirag");
        user.setPassword("chirag");
        assertTrue(userService.saveUser(user));
    }

    @Test
    public void testShowAllUsers(){
        assertNotNull(userService.showAllUsers());
    }

    @Test
    public void testSaveAdminUser(){
        User user = new User();
        user.setUserName("admin2");
        user.setPassword("admin2");
        assertTrue(userService.saveAdminUser(user));
    }

}