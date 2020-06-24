package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;

import java.util.List;

public class ThreeOfAKind extends Pair {
    protected Card kind;


    public ThreeOfAKind(Card kind, List<Card> remains, List<Card> hand) {
        super(kind, remains, hand);
        setHandType(HandType.THREE_OF_A_KIND);
    }

}
