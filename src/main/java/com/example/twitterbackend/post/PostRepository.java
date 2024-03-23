package com.example.twitterbackend.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT * FROM post AS p JOIN user AS u ON u.id = p.user_id WHERE p.user_id = ?1",
            nativeQuery = true
    )
    List<Post> findAllByUserId(Integer userId);
}
