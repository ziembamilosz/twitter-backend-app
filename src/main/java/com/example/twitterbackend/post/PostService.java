package com.example.twitterbackend.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    public ResponseEntity<Post> getPostById(Integer id) {
        Optional<Post> possiblePost = postRepository.findById(id);
        return possiblePost
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

//    public ResponseEntity<List<Post>> getAllPostsOfUser(Integer userId) {
//        List<Post> postsOfUser = postRepository.findAllByUserId(userId);
//        return ResponseEntity.ok(postsOfUser);
//    }

    public ResponseEntity<Post> savePost(Post post) {
        if (!isBodyValid(post.getBody())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Post savedPost = postRepository.save(post);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPost);
    }

    private boolean isBodyValid(String body) {
        return !body.isEmpty();
    }
}
