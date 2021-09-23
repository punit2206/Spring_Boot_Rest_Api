package com.punit.service;

import com.punit.model.Post;
import com.punit.repository.ISocialMediaServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocialMediaServiceImpl implements ISocialMediaService {


    @Autowired
    ISocialMediaServiceRepository repo;


    @Override
    public boolean followUser(String follower, String folowee) {

        try {

            Optional<Map> followerMap = repo.getFollowersByUserId(follower);

            if (followerMap.isPresent()) {
                followerMap.get().put(folowee, null);
            } else {
                Map newFolloweeMap = new HashMap();
                newFolloweeMap.put(folowee, null);
                newFolloweeMap.put(follower, null);
                followerMap.orElseGet(() -> (Map) repo.getDataMap().put(follower, newFolloweeMap));

            }
            ((Map) repo.getDataMap().get(follower)).keySet().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    @Override
    public boolean unFollowUser(String follower, String folowee) {

        try {
            Optional<Map> followerMap = repo.getFollowersByUserId(follower);
            if (followerMap.isPresent()) {
                followerMap.get().remove(folowee);
                System.out.println("Updated data");
                ((Map) followerMap.get()).keySet().forEach(System.out::println);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;

    }

    @Override
    public Optional<Post> doPostByUser(Post post) {

        try {
            Optional<Set> userPosts = repo.getUserPostsByUserId(post.getPostOwner());

            post.setPostTime(Calendar.getInstance().getTime());

            if (userPosts.isPresent())
                userPosts.get().add(post);
            else {

                TreeSet<Post> newPostSet = new TreeSet<Post>();
                newPostSet.add(post);
                repo.getPostMap().put(post.getPostOwner(), newPostSet);
            }

            repo.getPostMap().get(post.getPostOwner()).forEach(System.out::println);


            return Optional.of(post);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.ofNullable(null);
        }

    }

    @Override
    public Optional<List<Post>> getNewsFeed(String userId, int offSet) {


        if (repo.getFollowersByUserId(userId).isPresent())

            return Optional.ofNullable(((TreeSet<Post>) repo.getFollowersByUserId(userId).get().
                    keySet().
                    stream().
                    map(follower -> getLatestFeed((String) follower, offSet)).
                    filter(list -> list != null).
                    flatMap(list -> ((List<Post>) list).stream()).
                    collect(Collectors.toCollection(TreeSet<Post>::new))).
                    stream().
                    limit(offSet).
                    collect(Collectors.toList()));

        else
            return Optional.ofNullable(null);

    }


    private List<Post> getLatestFeed(String userId, int offSet) {


        return repo.getUserPostsByUserId(userId).isPresent() ?
                (List<Post>) repo.getUserPostsByUserId(userId).get().stream().limit(offSet).collect(Collectors.toList()) : null;

    }
}
