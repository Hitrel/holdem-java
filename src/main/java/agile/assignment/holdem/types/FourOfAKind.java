package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class FourOfAKind extends ThreeOfAKind {
    public FourOfAKind(Card kind, List<Card> remains, List<Card> hand) {
        super(kind, remains, hand);

        setHandType(HandType.FOUR_OF_A_KIND);
    }
}
