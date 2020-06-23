package agile.assignment.holdem.types;

import agile.assignment.holdem.Card;
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


    public static PokerHand resolve(List<Card> hand) throws Exception {
        var new_hand = PokerHand.handResolve(hand);

        return new_hand;
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
    public abstract int compareTo(PokerHand targetHand);
}


