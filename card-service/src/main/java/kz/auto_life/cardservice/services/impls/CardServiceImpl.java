package kz.auto_life.cardservice.services.impls;

import kz.auto_life.cardservice.enums.ServiceTypes;
import kz.auto_life.cardservice.filters.CustomAuthorizationFilter;
import kz.auto_life.cardservice.models.Card;
import kz.auto_life.cardservice.models.Transaction;
import kz.auto_life.cardservice.payload.CardRequest;
import kz.auto_life.cardservice.payload.CardResponse;
import kz.auto_life.cardservice.payload.WithdrawAttributes;
import kz.auto_life.cardservice.payload.WithdrawRequest;
import kz.auto_life.cardservice.repositories.CardRepository;
import kz.auto_life.cardservice.services.CardService;
import kz.auto_life.cardservice.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    private static final int MAX = 2_000_000;
    private static final int MIN = 100_000;
    private final CardRepository cardRepository;
    private final TransactionService transactionService;

    public CardServiceImpl(CardRepository cardRepository, TransactionService transactionService) {
        this.cardRepository = cardRepository;
        this.transactionService = transactionService;
    }

    @Override
    @Transactional
    public CardResponse saveUserToCard(CardRequest request) {
        log.info("Fetching user with id '{}' from the database", CustomAuthorizationFilter.userId);
        log.info("Saving user to card");

        Card card = new Card();
        card.setFullName(request.getFullName());
        card.setCardNumber(request.getCardNumber());
        card.setMonth(request.getCardMonth());
        card.setYear(request.getCardYear());
        card.setCvv(request.getCvv());
        card.setAmount((int) ((Math.random() * (MAX - MIN) + MIN)));
        card.setUserId(Long.parseLong(CustomAuthorizationFilter.userId));
        cardRepository.saveAndFlush(card);

        String lastNumbersOfCard = "************" + card.getCardNumber().substring(12);
        return CardResponse.builder().cardId(card.getId()).lastNumbersOfCard(lastNumbersOfCard).build();
    }

    @Override
    public String withdraw(WithdrawRequest request) {
        Card card = cardRepository.findById(request.getCardId()).orElseThrow(() -> new RuntimeException("card not found"));

        int sum = request.getAttributes().stream()
                .mapToInt(WithdrawAttributes::getAmount)
                .sum();
        if (card.getAmount() - sum >= 0) {
            card.setAmount(card.getAmount() - sum);

            request.getAttributes()
                    .forEach(x -> {
                        Transaction transaction = new Transaction();
                        transaction.setServiceId(x.getId());
                        transaction.setServiceType(String.valueOf(ServiceTypes.TAX));
                        transaction.setServiceDescription("");
                        transaction.setServiceAmount(x.getAmount());
                        transaction.setUserId(Long.parseLong(CustomAuthorizationFilter.userId));
                        transactionService.save(transaction);
                    });

            cardRepository.save(card);
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public List<CardResponse> getAll() {
        log.info("Fetching all cards of user with id '{}'", CustomAuthorizationFilter.userId);
        return cardRepository.findAllByUserId(Long.parseLong(CustomAuthorizationFilter.userId))
                .stream()
                .map(card -> new CardResponse(
                        card.getId(),
                        "************" + card.getCardNumber().substring(12)))
                .toList();
    }
}
