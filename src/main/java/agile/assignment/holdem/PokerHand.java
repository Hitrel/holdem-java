package agile.assignment.holdem;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
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
        //TODO handResolve
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

class HighCard extends PokerHand {
    protected Card highCard;

    @Override
    public int compareTo(PokerHand targetHand) {
        // TODO HighCard compare
        return 0;
    }
}

class Pair extends PokerHand {
    protected Card pair;

    @Override
    public int compareTo(PokerHand targetHand) {
        // TODO Pair compare
        return 0;
    }
}

class TwoPair extends Pair{
    protected Card highPair;

    @Override
    public int compareTo(PokerHand targetHand) {
        // TODO TwoPair compare
        return 0;
    }
}

class ThreeOfAKind extends PokerHand {
    protected Card kind;

    @Override
    public int compareTo(PokerHand targetHand) {
        // TODO Three of a Kind compare
        return 0;
    }
}

class Straight extends HighCard { }

class Flush extends HighCard { }

class FullHouse extends PokerHand {
    protected Card ThreeKind;
    protected Card Pair;

    @Override
    public int compareTo(PokerHand targetHand) {
        // TODO Full House compare
        return 0;
    }
}

class FourOfAKind extends ThreeOfAKind { }

class StraightFlush extends Straight { }

class RoyalFlush extends PokerHand {
    @Override
    public int compareTo(PokerHand targetHand) {
        if (targetHand.getHandType() != HandType.ROYAL_FLUSH) {
            return 1;
        } else {
            return 0;
        }
    }
}