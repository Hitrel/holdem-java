package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TwoPair extends Pair {
    protected Card highPair;

    public TwoPair(Card highPair, Card pair, List<Card> remains, List<Card> hand) {
        super(pair, remains, hand);
        setHighPair(highPair);
        setHandType(HandType.TWO_PAIRS);
    }

    @Override
    public int highCardCompare(PokerHand targetHand) {
        var targetTwoPair = (TwoPair) targetHand;
        var compare = getHighPair().compareTo(targetTwoPair.getHighPair());
        if (compare > 0) {
            setHighCard(highPair);
            return 2;
        } else if (compare < 0) {
            targetTwoPair.setHighCard(targetTwoPair.getHighPair());
            return -2;
        } else {
            return super.highCardCompare(targetHand);
        }
    }
}
