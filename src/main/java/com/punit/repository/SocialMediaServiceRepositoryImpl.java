package com.punit.repository;

import com.punit.model.Post;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class SocialMediaServiceRepositoryImpl implements ISocialMediaServiceRepository {


    @Override
    public Optional<Map> getFollowersByUserId(String userId) {
        return Optional.ofNullable(dataMap.get(userId));
    }

    @Override
    public Map<String, Map<String, String>> getDataMap() {
        return dataMap;
    }

    @Override
    public Optional<Set> getUserPostsByUserId(String userId) {

        return Optional.ofNullable(postDataMap.get(userId));

    }

    @Override
    public Map<String, TreeSet<Post>> getPostMap() {
        return postDataMap;
    }


}
