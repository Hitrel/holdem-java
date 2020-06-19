package agile.assignment.holdem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Comparable<Card> {
    Decors decors;
    int numeralPattern;

    @Override
    public int compareTo(Card target) {
        return numeralPattern - target.getNumeralPattern();
    }
}
