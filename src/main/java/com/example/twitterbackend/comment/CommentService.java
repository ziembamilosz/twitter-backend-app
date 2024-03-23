package com.example.twitterbackend.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return ResponseEntity.ok(comments);
    }

    public ResponseEntity<Comment> getCommentById(Integer id) {
        Optional<Comment> possibleComment = commentRepository.findById(id);
        return possibleComment
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Comment>> getAllCommentsUnderPost(Integer postId) {
        List<Comment> commentsUnderPost = commentRepository.findAllByPostId(postId);
        return ResponseEntity.ok(commentsUnderPost);
    }

    public ResponseEntity<Comment> saveComment(Comment comment) {
        if (!isBodyValid(comment.getBody())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        if (comment.getUser() == null) {
            // @TODO add proper header
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        if (comment.getPost() == null) {
            // @TODO add proper header
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        Comment savedComment = commentRepository.save(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedComment);
    }

    private boolean isBodyValid(String body) {
        return !body.isEmpty();
    }
}
