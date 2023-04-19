package Entity;


import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private int cardId;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "name_on_card")
    private String nameOnCard;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "card_value_issued")
    private int cardValueIssued;

    @Column(name = "card_value_today")
    private int cardValueToday;

    @Column(name = "operation_mode")
    private String operationMode;

    public Card() {}

    public Card(String cardNumber, String nameOnCard, String expiryDate, int cvv,
                int cardValueIssued, int cardValueToday, String operationMode) {
        this.cardNumber = cardNumber;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardValueIssued = cardValueIssued;
        this.cardValueToday = cardValueToday;
        this.operationMode = operationMode;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public int getCardValueIssued() {
        return cardValueIssued;
    }

    public void setCardValueIssued(int cardValueIssued) {
        this.cardValueIssued = cardValueIssued;
    }

    public int getCardValueToday() {
        return cardValueToday;
    }

    public void setCardValueToday(int cardValueToday) {
        this.cardValueToday = cardValueToday;
    }

    public String getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(String operationMode) {
        this.operationMode = operationMode;
    }
}
