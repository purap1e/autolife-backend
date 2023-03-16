package kz.auto_life.cardservice.payload;

import lombok.Getter;

import java.util.List;

@Getter
public class WithdrawRequest {
    private List<WithdrawAttributes> attributes;
    private Long cardId;
    private Long serviceId;
}
