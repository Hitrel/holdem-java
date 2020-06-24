package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;

import java.util.List;

public class Flush extends HighCard {
    public Flush(List<Card> hand) {
        super(hand);
        setHandType(HandType.FLUSH);
    }
}
