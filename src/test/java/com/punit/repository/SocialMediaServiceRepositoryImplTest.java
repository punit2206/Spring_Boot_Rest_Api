package com.punit.repository;

import com.punit.SocialNetworkApplication;
import com.punit.model.Post;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SocialNetworkApplication.class)
class SocialMediaServiceRepositoryImplTest {

    @Autowired
    ISocialMediaServiceRepository repo;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getFollowersByUserId() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("followee1", "");
        repo.getDataMap().put("user1", map);
        Assert.assertNotNull(repo.getFollowersByUserId("user1"));
        Assert.assertEquals(map, repo.getFollowersByUserId("user1").get());


    }


    @Test
    void getUserPostsByUserId() {

        TreeSet<Post> postSet = new TreeSet<Post>();
        Post post = new Post("punit", Calendar.getInstance().getTime(), "Hello I am punit", "1234");

        TreeSet<Post> newPostSet = new TreeSet<Post>();
        newPostSet.add(post);
        repo.getPostMap().put(post.getPostOwner(), newPostSet);

        Set s = repo.getPostMap().get("punit");
        Assert.assertNotNull(s);
        Assert.assertEquals(1, s.size());

    }

}