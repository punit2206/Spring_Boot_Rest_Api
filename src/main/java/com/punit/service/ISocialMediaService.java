package com.punit.service;

import com.punit.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ISocialMediaService {

    /*public List<Post> getPostsForUser(String userId);*/


    public boolean followUser(String folowee, String follower);

    public boolean unFollowUser(String folowee, String follower);

    public Optional<Post> doPostByUser(Post post);

    public Optional<List<Post>> getNewsFeed(String userId, int offSet);

}
