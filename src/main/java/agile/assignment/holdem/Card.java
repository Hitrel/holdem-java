package agile.assignment.holdem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Comparable<Card>, Serializable {
    Decors decors;
    int numeralPattern;

    @Override
    public int compareTo(Card target) {
        return numeralPattern - target.getNumeralPattern();
    }


    public boolean equals(Card target) {
        return target.getDecors().equals(getDecors()) && target.getNumeralPattern() == getNumeralPattern();
    }

    @Override
    public String toString() {
        return "Card{" +
                "decors=" + decors +
                ", numeralPattern=" + numeralPattern +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumeralPattern() == card.getNumeralPattern() &&
                getDecors() == card.getDecors();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDecors(), getNumeralPattern());
    }
}
