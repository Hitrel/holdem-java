package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class HighCard extends PokerHand {
    protected Card highCard;

    public HighCard(List<Card> hand) {
        super(hand);
        hand.sort(Comparator.reverseOrder());
        setHighCard(hand.get(0));
        setHandType(HandType.HIGH_CARD);
    }

    @Override
    public int highCardCompare(PokerHand targetHand) {
        var selfHand = getHand();
        var target = targetHand.getHand();

        for(int i = 0; i <= 5; i++) {
            var compare = selfHand.get(i).compareTo(target.get(i));
            if (compare > 0) {
                setHighCard(selfHand.get(i));
                return 2;
            } else if (compare < 0) {
                ((HighCard) targetHand).setHighCard(targetHand.getHand().get(i));
                return -2;
            } else {
                continue;
            }
        }

        return 0; // If travel all card is same return 0 for tie.
    }
}
