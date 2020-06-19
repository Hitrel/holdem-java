package agile.assignment.holdem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokerHand implements Comparable<PokerHand> {
    List<Card> hand;

    HandType handType;

    public static PokerHand resolve(List<Card> hand) {
        var new_hand = new PokerHand();

        new_hand.setHand(hand);
        new_hand.handResolve();

        return new_hand;
    }

    public void handResolve() {

    }

    @Override
    public int compareTo(PokerHand targeHand) {
        return 0;
    }
}
