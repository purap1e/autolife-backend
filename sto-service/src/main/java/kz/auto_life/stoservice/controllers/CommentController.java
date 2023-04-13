package kz.auto_life.stoservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.stoservice.models.Comment;
import kz.auto_life.stoservice.payload.CommentRequest;
import kz.auto_life.stoservice.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@Tag(name = "Comment API")
@RestController
@RequestMapping("/api/sto/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "добавление комментария к СТО",
            description = "добавление комментария к СТО")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentRequest request) {
        return ResponseEntity.status(201).body(commentService.save(request));
    }

    @Operation(summary = "вывод всех комментариев по id СТО",
            description = "вывод всех комментариев по id СТО")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public Set<Comment> getALl(@RequestParam UUID id) {
        return commentService.getAll(id);
    }
}
