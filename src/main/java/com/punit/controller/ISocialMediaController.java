package com.punit.controller;

import com.punit.model.Post;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
interface ISocialMediaController {

    @PostMapping(value = "/post",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post);

    @PostMapping(value = "/follow/{followerId}/{followeeId}",
            produces = {MediaType.ALL_VALUE})
    public ResponseEntity<String> follow(@PathVariable String followerId, @PathVariable String followeeId);


    @DeleteMapping(value = "/follow/{followerId}/{followeeId}",
            produces = {MediaType.ALL_VALUE})
    public ResponseEntity unfollow(@PathVariable String followerId, @PathVariable String followeeId);


    @GetMapping(value = "/newsfeed/{user}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Post>> getNewsFeed(@PathVariable String user);

    @GetMapping(value = "/hello", consumes = {MediaType.ALL_VALUE},
            produces = {MediaType.ALL_VALUE})
    public String testHello();
}
