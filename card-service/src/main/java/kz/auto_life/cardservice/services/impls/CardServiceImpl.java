package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.exceptions.AmountException;
import kz.auto_life.cardservice.exceptions.InvalidCredentialsException;
import kz.auto_life.cardservice.mappers.CardMapper;
import kz.auto_life.cardservice.models.Card;
import kz.auto_life.cardservice.models.Transaction;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.ResponseMessage;
import kz.auto_life.cardservice.payload.WithdrawRequest;
import kz.auto_life.cardservice.repositories.CardRepository;
import kz.auto_life.cardservice.services.CardService;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
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
    private final ServletContext servletContext;

    public CardServiceImpl(CardRepository cardRepository, TransactionService transactionService, CardMapper cardMapper, ServletContext servletContext) {
        this.cardRepository = cardRepository;
        this.transactionService = transactionService;
        this.cardMapper = cardMapper;
        this.servletContext = servletContext;
    }

    public UUID getUserId() {
        try {
            return UUID.fromString(String.valueOf(servletContext.getAttribute("userId")));
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CardResponse saveUserToCard() {
        log.info("Fetching user with id '{}' from the database", getUserId());
        log.info("Saving user to card");

        Card card = new Card();
        card.setFullName("RED MAD ROBOT");
        card.setCardNumber("9999-9999-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD)) + "-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD)));
        card.setMonth((int) ((Math.random() * (MAX_MONTH - MIN_MONTH) + MIN_MONTH)));
        card.setYear(Calendar.getInstance().get(Calendar.YEAR) + YEAR);
        card.setCvv(String.valueOf((int) ((Math.random() * (MAX_CVV - MIN_CVV) + MIN_CVV))));
        card.setAmount((BigDecimal.valueOf(AMOUNT)));
        card.setUserId(getUserId());
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
        Card card = cardRepository.findById(request.getCardId()).orElseThrow(() -> new InvalidCredentialsException(new ResponseMessage("Invalid card")));
        if (getUserId().equals(card.getUserId())) {
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
                            transaction.setServiceDescription(x.getDescription());
                            transaction.setServiceAmount(x.getAmount());
                            transaction.setCurrency(CURRENCY_KZ);
                            transaction.setUserId(getUserId());
                            transactionService.save(transaction);
                        });

                cardRepository.save(card);
                return "success";
            } else {
                throw new AmountException(new ResponseMessage("Insufficient funds"));
            }
        } else {
            throw new InvalidCredentialsException(new ResponseMessage("Invalid card, please try again"));
        }
    }

    @Override
    public List<CardResponse> getAll() {
        log.info("Fetching all cards of user with id '{}'", getUserId());
        return cardRepository.findAllByUserId(getUserId())
                .stream()
                .map(cardMapper)
                .toList();
    }
}
