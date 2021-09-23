package com.punit.controller;

import com.punit.model.Post;
import com.punit.service.ISocialMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/socialmedia", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)

public class SocialMediaControllerImpl implements ISocialMediaController {

    @Autowired
    ISocialMediaService service;

    @Override
    public ResponseEntity<Post> createPost(Post post) {

        return new ResponseEntity<Post>(service.doPostByUser(post).get(), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<String> follow(String followerId, String followeeId) {

        if (service.followUser(followerId, followeeId))
            return new ResponseEntity<String>("Follow Operation is successful", HttpStatus.CREATED);
        else
            return new ResponseEntity<String>("Follow Operation is failed", HttpStatus.NOT_MODIFIED);
    }


    @Override
    public ResponseEntity<String> unfollow(String followerId, String followeeId) {

        if (service.unFollowUser(followerId, followeeId))
            return new ResponseEntity<String>("Unfollow Operation is successful", HttpStatus.CREATED);
        else
            return new ResponseEntity<String>("Unfollow Operation is failed", HttpStatus.NOT_MODIFIED);
    }


    @Override
    public ResponseEntity<List<Post>> getNewsFeed(String user) {

        if (service.getNewsFeed(user, 20).isPresent())
            return new ResponseEntity<List<Post>>(service.getNewsFeed(user, 20).get(), HttpStatus.OK);
        else
            return new ResponseEntity<List<Post>>(new ArrayList<Post>(), HttpStatus.NOT_FOUND);

    }

    @Override
    public String testHello() {
        return "Hi I am here";
    }
}
