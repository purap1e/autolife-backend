package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.filters.CustomAuthorizationFilter;
import kz.auto_life.cardservice.mappers.CardMapper;
import kz.auto_life.cardservice.modules.Card;
import kz.auto_life.cardservice.modules.Transaction;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawRequest;
import kz.auto_life.cardservice.repositories.CardRepository;
import kz.auto_life.cardservice.services.CardService;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    private static final int AMOUNT = 1_000_000;
    private static final int MIN_CARD = 999;
    private static final int MAX_CARD = 9999;
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 13;
    private static final int MIN_CVV = 99;
    private static final int MAX_CVV = 999;
    private static final int YEAR = 3;
    private static final String CURRENCY_KZ = "KZT";
    private final CardRepository cardRepository;
    private final TransactionService transactionService;
    private final CardMapper cardMapper;

    public CardServiceImpl(CardRepository cardRepository, TransactionService transactionService, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.transactionService = transactionService;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardResponse saveUserToCard() {
        log.info("Fetching user with id '{}' from the database", CustomAuthorizationFilter.userId);
        log.info("Saving user to card");

        Card card = new Card();
        card.setFullName("RED MAD ROBOT");
        card.setCardNumber("9999-9999-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD)) + "-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD)));
        card.setMonth((int) ((Math.random() * (MAX_MONTH - MIN_MONTH) + MIN_MONTH)));
        card.setYear(Calendar.getInstance().get(Calendar.YEAR) + YEAR);
        card.setCvv(String.valueOf((int) ((Math.random() * (MAX_CVV - MIN_CVV) + MIN_CVV))));
        card.setAmount((BigDecimal.valueOf(AMOUNT)));
        card.setUserId(UUID.fromString(CustomAuthorizationFilter.userId));
        cardRepository.save(card);

        String lastNumbersOfCard = "************" + card.getCardNumber().substring(15);
        return CardResponse.builder()
                .cardId(card.getId())
                .name(card.getFullName())
                .amount(card.getAmount())
                .month(card.getMonth())
                .year(card.getYear())
                .lastNumbersOfCard(lastNumbersOfCard).build();
    }

    @Override
    public String withdraw(WithdrawRequest request) {
        Card card = cardRepository.findById(request.getCardId()).orElseThrow(() -> new RuntimeException("card not found"));
        if (UUID.fromString(CustomAuthorizationFilter.userId).equals(card.getUserId())) {
            List<BigDecimal> decimals = new LinkedList<>();

            request.getAttributes().forEach(x -> decimals.add(x.getAmount()));

            BigDecimal sum = BigDecimal.ZERO;
            for (BigDecimal amt : decimals) {
                sum = sum.add(amt);
            }

            if (card.getAmount().compareTo(sum) >= 0) {
                card.setAmount(card.getAmount().subtract(sum));

                request.getAttributes()
                        .forEach(x -> {
                            Transaction transaction = new Transaction();
                            transaction.setServiceId(request.getServiceId());
                            transaction.setReferenceId(x.getId());
                            transaction.setServiceDescription(String.format("Vehicle with grnz '%s'", x.getGrnz()));
                            transaction.setServiceAmount(x.getAmount());
                            transaction.setCurrency(CURRENCY_KZ);
                            transaction.setUserId(UUID.fromString(CustomAuthorizationFilter.userId));
                            transactionService.save(transaction);
                        });

                cardRepository.save(card);
                return "success";
            }
        }
        return "error";
    }

    @Override
    public List<CardResponse> getAll() {
        log.info("Fetching all cards of user with id '{}'", CustomAuthorizationFilter.userId);
        return cardRepository.findAllByUserId(UUID.fromString(CustomAuthorizationFilter.userId))
                .stream()
                .map(cardMapper)
                .toList();
    }
}
