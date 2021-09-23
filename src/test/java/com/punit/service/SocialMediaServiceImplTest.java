package com.punit.service;

import com.punit.SocialNetworkApplication;
import com.punit.model.Post;
import com.punit.repository.ISocialMediaServiceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialNetworkApplication.class)
class SocialMediaServiceImplTest {


    @Autowired
    ISocialMediaServiceRepository repo;

    @Autowired
    ISocialMediaService service;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void followUser() {

        Assert.assertTrue(service.followUser("user1", "user2"));

    }

    @Test
    void unFollowUser() {

        Assert.assertTrue(service.unFollowUser("user1", "user2"));
    }

    @Test
    void doPostByUser() {

        Post post = new Post("punit", Calendar.getInstance().getTime(), "Hello I am punit", "1234");

        Assert.assertTrue(service.doPostByUser(post).isPresent());

    }

    @Test
    void getNewsFeed() {


        Assert.assertNotNull(service.getNewsFeed("user1", 10));


    }
}