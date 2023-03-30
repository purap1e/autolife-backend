package kz.auto_life.shopservice.services;

import kz.auto_life.shopservice.models.Comment;
import kz.auto_life.shopservice.payload.CommentDto;

import java.util.Set;
import java.util.UUID;

public interface CommentService {
    Comment save(CommentDto commentDto);
    Set<Comment> getAll(UUID itemId);
}
