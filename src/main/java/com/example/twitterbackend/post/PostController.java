package com.example.twitterbackend.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Post>> getAllPostsOfUser(@PathVariable Integer userId)  {
//        return postService.getAllPostsOfUser(userId);
//    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return postService.savePost(post);
    }


}
