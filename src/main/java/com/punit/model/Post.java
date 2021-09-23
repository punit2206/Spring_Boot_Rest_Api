package com.punit.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class Post implements Comparable {

    @NotBlank
    private String postOwner;
    private Date postTime;
    @Size(max = 40)
    private String postContent;
    private String postId;
    public Post(String postOwner, Date postTime, String postContent, String postId) {
        this.postOwner = postOwner;
        this.postTime = postTime;
        this.postContent = postContent;
        this.postId = postId;
    }

    public Post() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }


    public String getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(String postOwner) {
        this.postOwner = postOwner;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getPostContent() {
        return postContent;
    }


    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }


    @Override
    public int compareTo(Object o) {


        Post p = (Post) o;
        return p.postTime.compareTo(this.postTime);
        //return this.postTime.compareTo(p.postTime);

    }

    @Override
    public int hashCode() {
        return this.postId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Post p = (Post) obj;
        return (this.postId == p.postId && this.postOwner.equalsIgnoreCase(p.postOwner));
    }
}

