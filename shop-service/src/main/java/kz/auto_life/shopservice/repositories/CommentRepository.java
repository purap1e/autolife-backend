package kz.auto_life.shopservice.repositories;

import kz.auto_life.shopservice.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    Set<Comment> findAllByItemId(UUID id);
}
