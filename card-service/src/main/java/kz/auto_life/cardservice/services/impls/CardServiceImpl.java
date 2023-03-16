package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.enums.ServiceTypes;
import kz.auto_life.cardservice.filters.CustomAuthorizationFilter;
import kz.auto_life.cardservice.mappers.CardMapper;
import kz.auto_life.cardservice.models.Card;
import kz.auto_life.cardservice.models.Transaction;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawAttributes;
import kz.auto_life.cardservice.payload.WithdrawRequest;
import kz.auto_life.cardservice.repositories.CardRepository;
import kz.auto_life.cardservice.services.CardService;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    private static final int MAX_AMOUNT = 2_000_000;
    private static final int MIN_AMOUNT = 100_000;
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
        card.setCardNumber("9999-9999-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD))  + "-" + (int) ((Math.random() * (MAX_CARD - MIN_CARD) + MIN_CARD)));
        card.setMonth((int) ((Math.random() * (MAX_MONTH - MIN_MONTH) + MIN_MONTH)));
        card.setYear(Calendar.getInstance().get(Calendar.YEAR) + YEAR);
        card.setCvv(String.valueOf((int) ((Math.random() * (MAX_CVV - MIN_CVV) + MIN_CVV))));
        card.setAmount((int) ((Math.random() * (MAX_AMOUNT - MIN_AMOUNT) + MIN_AMOUNT)));
        card.setUserId(Long.parseLong(CustomAuthorizationFilter.userId));
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
        if (Long.parseLong(CustomAuthorizationFilter.userId) == card.getUserId()) {
            int sum = request.getAttributes().stream()
                    .mapToInt(WithdrawAttributes::getAmount)
                    .sum();
            if (card.getAmount() - sum >= 0) {
                card.setAmount(card.getAmount() - sum);

                request.getAttributes()
                        .forEach(x -> {
                            Transaction transaction = new Transaction();
                            transaction.setServiceId(request.getServiceId());
                            transaction.setReferenceId(x.getId());
                            if (request.getServiceId() == 1) {
                                transaction.setServiceType(String.valueOf(ServiceTypes.TAX));
                            } else {
                                transaction.setServiceType(String.valueOf(ServiceTypes.FINE));
                            }
                            transaction.setServiceDescription(String.format("Vehicle with grnz '%s'", x.getGrnz()));
                            transaction.setServiceAmount(x.getAmount());
                            transaction.setCurrency(CURRENCY_KZ);
                            transaction.setUserId(Long.parseLong(CustomAuthorizationFilter.userId));
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
        return cardRepository.findAllByUserId(Long.parseLong(CustomAuthorizationFilter.userId))
                .stream()
                .map(cardMapper)
                .toList();
    }
}
