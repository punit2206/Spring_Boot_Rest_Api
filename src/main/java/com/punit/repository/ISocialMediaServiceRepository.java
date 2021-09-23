package com.punit.repository;

import com.punit.model.Post;

import java.util.*;

public interface ISocialMediaServiceRepository {

    public static final Map<String, Map<String, String>> dataMap = new HashMap();
    // Map for Users and their post in ordered and sorted way
    public static final Map<String, TreeSet<Post>> postDataMap = new HashMap();


    public Optional<Map> getFollowersByUserId(String userId);

    public Map<String, Map<String, String>> getDataMap();

    public Map<String, TreeSet<Post>> getPostMap();

    public Optional<Set> getUserPostsByUserId(String userId);


}
