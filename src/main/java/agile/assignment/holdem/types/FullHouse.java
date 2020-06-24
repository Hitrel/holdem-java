package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class FullHouse extends TwoPair {

    public FullHouse(Card threeKind, Card pair, List<Card> remains, List<Card> hand) {
        super(threeKind, pair, remains, hand);
        setHandType(HandType.FULL_HOUSE);
    }

    public Card getThreeKind() {
        return getHighPair();
    }
}
