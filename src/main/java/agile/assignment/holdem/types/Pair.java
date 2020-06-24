package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Pair extends HighCard {
    protected Card pair;
    
    protected List<Card> remains;

    public Pair(Card pair, List<Card> remains, List<Card> hand) {
        super(hand);
        setPair(pair);
        setRemains(remains);
        setHandType(HandType.PAIR);
    }

    @Override
    public int highCardCompare(PokerHand targetHand) {
        var targetPair = (Pair) targetHand;
        var compareOutLoop =  this.getPair().compareTo(targetPair.getPair());
            if (compareOutLoop > 0) {
                setHighCard(pair);
                return 2;
            } else if (compareOutLoop < 0) {
                targetPair.setHighCard(targetPair.getPair());
                return -2;
            } else {
                return compareRemains(targetPair);
            }
        }

    private int compareRemains(Pair targetPair) {
        for (int i = 0; i < remains.size(); i++) {
            var compareInLoop = remains.get(i).compareTo(targetPair.remains.get(i));

            if (compareInLoop > 0) {
                setHighCard(remains.get(i));
                return 2;
            } else if (compareInLoop < 0) {
                targetPair.setHighCard(remains.get(i));
                return -2;
            } else {
                continue;
            }
        }

        return 0;
    }


}
