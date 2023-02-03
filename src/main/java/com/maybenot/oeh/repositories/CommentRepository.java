package com.maybenot.oeh.repositories;

import com.maybenot.oeh.entities.Comment;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableScan
public interface CommentRepository extends CrudRepository<Comment, String> {

    Optional<List<Comment>> findCommentByUserId(String userId);
    Optional<List<Comment>> findCommentByPlaceId(String placeId);
}
