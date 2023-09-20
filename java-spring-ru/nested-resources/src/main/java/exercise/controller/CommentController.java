package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping("/{postId}/comments")
    public Iterable<Comment> getComments(@PathVariable(name = "postId") Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public Comment getComment(
        @PathVariable(name = "postId") Long postId,
        @PathVariable(name = "commentId") Long commentId
    ) {
        Optional<Comment> comment = commentRepository.findByIdAndPostId(commentId, postId);
        if (comment.isEmpty()) {
            throw new ResourceNotFoundException("No one comment with this id was found");
        }
        return comment.get();
    }

    @PostMapping("/{postId}/comments")
    public void newComment(
        @PathVariable(name = "postId") Long postId,
        @RequestBody Comment newComment) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new ResourceNotFoundException("No posts was found by id");
        }
        newComment.setPost(post.get());
        commentRepository.save(newComment);
    }

    @PatchMapping("/{postId}/comments/{commentId}")
    public void updateComment(
        @PathVariable(name = "postId") Long postId,
        @PathVariable(name = "commentId") Long commentId,
        @RequestBody Comment newComment
    ) {
        Optional<Comment> commentOptional = commentRepository.findByIdAndPostId(commentId, postId);

        if (commentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No one comment with this id was found");
        }

        Comment comment = commentOptional.get();

        comment.setId(commentId);
        comment.setContent(newComment.getContent());

        commentRepository.save(comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public void deleteComment(
        @PathVariable(name = "postId") Long postId,
        @PathVariable(name = "commentId") Long commentId
    ) {
        Optional<Comment> commentOptional = commentRepository.findByIdAndPostId(commentId, postId);

        if (commentOptional.isEmpty()) {
            throw new ResourceNotFoundException("No one comment with this id was found");
        }

        commentRepository.deleteById(commentId);
    }
    // END
}
