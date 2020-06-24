package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PokerHand implements Comparable<PokerHand> {
    protected List<Card> hand;

    protected HandType handType;

    public PokerHand(List<Card> hand) {
        setHand(hand);
    }


    public static PokerHand resolve(List<Card> hand) throws Exception {

        return PokerHand.handResolve(hand);
    }

    public static PokerHand handResolve(List<Card> hand) throws Exception{


        throw new NotImplementedException("");
    }

    @Deprecated
    public static int cardPatternCompare(List<Card> hand, List<Card> targetHand) {
        var compare = hand.get(0).compareTo(targetHand.get(1));

        if (compare > 0) {
            return 2;
        } else if (compare == 0) {
            return 0;
        } else {
            return -2;
        }


    }

    @Override
    public int compareTo(PokerHand targetHand) {
        if (targetHand.getHandType().code > getHandType().code) {
            return -1;
        } else if (targetHand.getHandType().code < getHandType().code) {
            return  1;
        } else {
            return highCardCompare(targetHand);
        }
    }

    public abstract int highCardCompare(PokerHand targetHand);
}


