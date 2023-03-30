package kz.auto_life.shopservice.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentDto {
    private UUID itemId;
    private String comment;
}
