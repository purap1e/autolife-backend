package kz.auto_life.stoservice.services;

import kz.auto_life.stoservice.models.Comment;
import kz.auto_life.stoservice.payload.CommentRequest;

import java.util.Set;
import java.util.UUID;

public interface CommentService {
    Comment save(CommentRequest request);

    Set<Comment> getAll(UUID stoId);
}
