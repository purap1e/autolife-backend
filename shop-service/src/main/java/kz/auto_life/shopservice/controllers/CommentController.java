package kz.auto_life.shopservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.auto_life.shopservice.models.Comment;
import kz.auto_life.shopservice.payload.CommentDto;
import kz.auto_life.shopservice.services.CommentService;
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
@RequiredArgsConstructor
@RequestMapping("/api/shop/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "добавление комментария к товару",
            description = "добавление комментария к товару")
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody CommentDto commentDto) {
        return ResponseEntity.status(201).body(commentService.save(commentDto));
    }

    @Operation(summary = "вывод всех комментариев по id товару",
            description = "вывод всех комментариев по id товару")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping
    public Set<Comment> getALl(@RequestParam UUID id) {
        return commentService.getAll(id);
    }
}
