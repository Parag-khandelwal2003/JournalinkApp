package net.apmoller.journalink.service;

import jakarta.transaction.Transactional;
import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        String username = "testUser";
        User mockUser = new User();
        mockUser.setUserName("testUser");
        mockUser.setPassword("testUser");
        mockUser.setRoles(List.of("USER"));
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(mockUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(username, userDetails.getUsername());

    }

}
