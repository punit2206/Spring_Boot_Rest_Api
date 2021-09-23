package com.punit.controller;

import com.punit.SocialNetworkApplication;
import com.punit.repository.SocialMediaServiceRepositoryImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SocialNetworkApplication.class)
@AutoConfigureMockMvc
class SocialMediaControllerImplTest {


    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private SocialMediaServiceRepositoryImpl repository;


    @Before
    void setUp() {


        MockitoAnnotations.initMocks(this.getClass());

    }

    @Test
    void createPost() throws Exception {

        mockMvc.perform(post("/socialmedia/post").content("{\"postOwner\":\"c\",\"postTime\":\"2021-09-22T11:16:56.448+00:00\",\"postContent\":\"c post 1\",\"postId\":1}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void follow() throws Exception {
        mockMvc.perform(post("/socialmedia/follow/user1/user2")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.ALL_VALUE));

    }

    @Test
    void unfollow() throws Exception {

        mockMvc.perform(delete("/socialmedia/follow/user1/user2")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.ALL_VALUE));

    }

    @Test
    void getNewsFeed() throws Exception {


        mockMvc.perform(get("/newsfeed/c")
                .contentType(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.ALL_VALUE));

    }

}