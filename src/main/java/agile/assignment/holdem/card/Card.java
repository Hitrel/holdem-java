package agile.assignment.holdem.card;

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
        if (numeralPattern > target.getNumeralPattern()) {
            return 1;
        } else if (numeralPattern < target.getNumeralPattern()) {
            return -1;
        } else {
            return 0;
        }
    }


    public boolean equals(Card target) {
        return target.getDecors().equals(getDecors()) && target.getNumeralPattern() == getNumeralPattern();
    }

    @Override
    public String toString() {
        return getDecors().toString() + " " + switch (getNumeralPattern()) {
            case 10 -> "T";
            case 11 -> "J";
            case 12 -> "Q";
            case 13 -> "K";
            case 14 -> "A";
            default -> toString();
        };
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
