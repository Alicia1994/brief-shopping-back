package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@AutoConfigureMockMvc
public class GetUsersTest {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;


    private String existingUserId = "1";
    private String userId;
    User userNew = new User();


    public void setUserNew() {
        userNew.setId(4l);
        userNew.setUsername("username");
        userNew.setEmail("email@mail");
        userNew.setPresentation("pre");
        userNew.setPassword("azerty");
        userService.saveUser(userNew);

    }

    @Before
    public void set_up() {
        setUserNew();
    }


    @Test
    void insertUser() {
        Iterable<User> userList = userService.getAllUser();
        Boolean variable = false;
        for (User user : userList) {
            if (user.getId() == userNew.getId()) {
                variable = true;
                Assertions.assertTrue(variable == true);
            }
        }
    }

    @Test
    void getAllUsers(){
        Iterable<User> users = userService.getAllUser();
        int size = 4;
        Assert.assertEquals(users, size);
        //Assert.assertThat(users).isEmpty();

    }
}

/*  @Test
    void insertUser(){
        Iterable<User> userList = userService.getAllUser();
        Boolean variable = true;
        for (User user: userList){
            if (user.getId() != userNew.getId()){
                variable = false;
            }
        }
        Assertions.assertTrue(variable);
    } */
//    @Test
//    void getAllUsers(){
//        Iterable<User> users = userService.getAllUser();
////        int size = 4;
//        Assert.assertEquals(users, userService.getAllUser());
//    }



    //        userService.saveUser(user);
    //Assert.assertTrue();
/*        Document user = new Document("id", new User());
        user.append("username", "nom")
                .append("email", "email@iii.com")
                .append("presentation", "presentation")
                .append("password", "Yulia");

        userId = user.getId("id").toHexString();*/

