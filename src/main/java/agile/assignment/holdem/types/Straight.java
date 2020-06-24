package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;

import java.util.Comparator;
import java.util.List;

public class Straight extends HighCard {
    public Straight(List<Card> hand) {
        super(hand);
        setHandType(HandType.STRAIGHT);
    }

    private void resetHighCard() {
        int sum = 0;

        for (var card : getHand()) {
            sum += card.getNumeralPattern();
        }

        if (sum == 28) {
            hand.add(hand.remove(0));
            setHighCard(hand.get(0));
        }
    }
    @Override
    public int highCardCompare(PokerHand targetHand) {
        resetHighCard();
        var compare = getHighCard().compareTo(((HighCard) targetHand).getHighCard());

        if (compare > 0) {
            return 2;
        } else if (compare < 0) {
            return -2;
        } else {
            return 0;
        }
    }
}
