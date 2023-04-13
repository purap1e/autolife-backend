package kz.auto_life.stoservice.payload;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentRequest {
    private UUID stoId;
    private String comment;
    private double grade;
}
