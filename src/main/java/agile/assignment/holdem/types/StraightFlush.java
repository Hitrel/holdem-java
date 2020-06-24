package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;

import java.util.List;

public class StraightFlush extends Straight {
    public StraightFlush(List<Card> hand) {
        super(hand);
        setHandType(HandType.STRAIGHT_FLUSH);
    }
}
