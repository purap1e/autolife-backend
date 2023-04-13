package kz.auto_life.stoservice.services.impl;

import kz.auto_life.stoservice.exceptions.InvalidCredentialsException;
import kz.auto_life.stoservice.models.Comment;
import kz.auto_life.stoservice.models.Sto;
import kz.auto_life.stoservice.payload.CommentRequest;
import kz.auto_life.stoservice.payload.ResponseMessage;
import kz.auto_life.stoservice.repositories.CommentRepository;
import kz.auto_life.stoservice.repositories.StoRepository;
import kz.auto_life.stoservice.services.CommentService;
import kz.auto_life.stoservice.services.StoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final StoRepository stoRepository;
    private final StoService stoService;
    private final ServletContext servletContext;
    private final CommentRepository commentRepository;

    public String getName() {
        return String.valueOf(servletContext.getAttribute("name"));
    }

    public UUID getUserId() {
        try {
            return UUID.fromString(String.valueOf(servletContext.getAttribute("userId")));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment save(CommentRequest request) {
        log.info("Saving new comment to sto with uuid {}", request.getStoId());
        Comment comment = new Comment();
        Sto sto = stoRepository.findById(request.getStoId()).orElseThrow(() -> new InvalidCredentialsException(new ResponseMessage("sto not found")));
        stoService.updateGrade(request.getGrade(), request.getStoId());

        comment.setName(getName());
        comment.setGrade(request.getGrade());
        comment.setUserId(getUserId());
        comment.setStoId(sto.getId());
        comment.setComment(request.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public Set<Comment> getAll(UUID stoId) {
        log.info("fetching all comments for sto with uuid {}", stoId);
        return commentRepository.findAllByStoId(stoId);
    }
}
