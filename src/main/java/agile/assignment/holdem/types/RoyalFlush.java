package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;

import java.util.List;

public class RoyalFlush extends Straight {
    public RoyalFlush(List<Card> hand) {
        super(hand);
        setHandType(HandType.ROYAL_FLUSH);
    }

    @Override
    public int compareTo(PokerHand targetHand) {
        if (targetHand.getHandType() != HandType.ROYAL_FLUSH) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int highCardCompare(PokerHand targetHand) {
        return 0;
    }
}
