package Service.CardServiceImpl;

import Entity.Card;
import Repository.CardRepository;
import Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(int cardId) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        return optionalCard.orElse(null);
    }

    @Override
    public void addCard(Card card) {
        cardRepository.save(card);
    }

    @Override
    public void updateCard(int cardId, Card card) {
        Optional<Card> optionalCard = cardRepository.findById(cardId);
        if (optionalCard.isPresent()) {
            Card existingCard = optionalCard.get();
            existingCard.setCardNumber(card.getCardNumber());
            existingCard.setNameOnCard(card.getNameOnCard());
            existingCard.setExpiryDate(card.getExpiryDate());
            existingCard.setCvv(card.getCvv());
            existingCard.setCardValueIssued(card.getCardValueIssued());
            existingCard.setCardValueToday(card.getCardValueToday());
            existingCard.setOperationMode(card.getOperationMode());
            cardRepository.save(existingCard);
        }
    }

    @Override
    public void deleteCard(int cardId) {
        cardRepository.deleteById(cardId);
    }
}
