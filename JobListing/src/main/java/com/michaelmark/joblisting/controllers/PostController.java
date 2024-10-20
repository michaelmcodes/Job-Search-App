package com.michaelmark.joblisting.controllers;

import com.michaelmark.joblisting.repository.PostRepository;
import com.michaelmark.joblisting.model.Post;
import com.michaelmark.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3030")
public class PostController {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository srepo;

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    @GetMapping("posts/{text}")
    public List<Post> search(@PathVariable String text) {

        return srepo.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return repo.save(post);
    }
}
