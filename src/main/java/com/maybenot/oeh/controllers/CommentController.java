package com.maybenot.oeh.controllers;

import com.maybenot.oeh.entities.Comment;
import com.maybenot.oeh.services.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Get comment by ID")
    @GetMapping(path = "comment/{commentId}")
    public Comment getComment(@PathVariable("commentId") String commentId) {
        return commentService.getComment(commentId);
    }

    @ApiOperation(value = "Get all comments")
    @GetMapping(path = "comments")
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @ApiOperation(value = "Get comments by user")
    @GetMapping(path = "commentByUser/{userId}")
    public List<Comment> getCommentsByUser(@PathVariable("userId") String userId) {
        return commentService.getCommentsByUser(userId);
    }

    @ApiOperation(value = "Get comments by place")
    @GetMapping(path = "commentByPlace/{placeId}")
    public List<Comment> getCommentsByPlace(@PathVariable("placeId") String placeId) {
        return commentService.getCommentsByPlace(placeId);
    }

    @ApiOperation(value = "Post a comment")
    @PostMapping(path = "postComment")
    public void addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
    }

    @ApiOperation(value = "Delete a comment by ID")
    @DeleteMapping(path = "deleteComment/{commentId}")
    public void deleteComment(@PathVariable("commentId") String commentId) {
        commentService.deleteComment(commentId);
    }
}
