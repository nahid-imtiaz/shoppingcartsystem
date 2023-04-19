package Service;

import Entity.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    Card getCardById(int cardId);
    void addCard(Card card);
    void updateCard(int cardId, Card card);
    void deleteCard(int cardId);
}


