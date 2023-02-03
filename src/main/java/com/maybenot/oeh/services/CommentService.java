package com.maybenot.oeh.services;

import com.amazonaws.services.s3.transfer.Copy;
import com.maybenot.oeh.entities.Comment;
import com.maybenot.oeh.entities.Place;
import com.maybenot.oeh.exception.ResourceNotFoundException;
import com.maybenot.oeh.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private static final String COMMENT = "Comment";

    @Autowired
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(String commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, commentId));
    }

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    public List<Comment> getCommentsByUser(String userId) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findCommentByUserId(userId).ifPresent(comments::addAll);
        return comments;
    }

    public List<Comment> getCommentsByPlace(String placeId) {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findCommentByPlaceId(placeId).ifPresent(comments::addAll);
        return comments;
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(String commentId, String text) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, commentId));
        comment.setText(text);
        commentRepository.save(comment);
    }

    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(COMMENT, commentId));
        commentRepository.delete(comment);
    }
}
