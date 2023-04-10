package kz.auto_life.shopservice.services.impls;

import kz.auto_life.shopservice.exceptions.InvalidCredentialsException;
import kz.auto_life.shopservice.models.Comment;
import kz.auto_life.shopservice.models.Item;
import kz.auto_life.shopservice.payload.CommentDto;
import kz.auto_life.shopservice.payload.ResponseMessage;
import kz.auto_life.shopservice.repositories.CommentRepository;
import kz.auto_life.shopservice.repositories.ItemRepository;
import kz.auto_life.shopservice.services.CommentService;
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

    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final ServletContext servletContext;

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
    public Comment save(CommentDto commentDto) {
        Comment comment = new Comment();
        Item item = itemRepository.findById(commentDto.getItemId()).orElseThrow(() -> new InvalidCredentialsException(new ResponseMessage("Item not found")));

        comment.setComment(commentDto.getComment());
        comment.setName(getName());
        comment.setUserId(getUserId());
        comment.setItemId(item.getId());
        return commentRepository.save(comment);
    }

    @Override
    public Set<Comment> getAll(UUID itemId) {
        return commentRepository.findAllByItemId(itemId);
    }
}
