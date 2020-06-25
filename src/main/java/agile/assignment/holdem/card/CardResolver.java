package agile.assignment.holdem.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardResolver {
    List<Card> black = new ArrayList<>();
    List<Card> white = new ArrayList<>();

    public static CardResolver cardsResolve(String s) throws Exception {
        var resolver = new CardResolver();
        var list = parse(s);

        resolver.parseHand(list.get(0));
        resolver.parseHand(list.get(1));

        return resolver;
    }

    public static List<String> parse(String s){
        return Arrays.asList(s.split(" "));
    }

    public List<Card> parseHand(String s) throws Exception {
        var split = s.split(":");
        List<Card> cardList = null;
        if (split[0].equals("Black")) {
            cardList = getBlack();

        } else {
            cardList = getWhite();
        }

        if (cardList.size() != 0) {
            throw new IllegalStateException(split[0] + " has been parsed");
        }

        for (var cardPattern : split[1].split(",")) {
            var newCard = new Card();
            newCard.setNumeralPattern(parseNumber(cardPattern));
            newCard.setDecors(parseDecors(cardPattern));

            cardList.add(newCard);
        }
        cardList.sort(Comparator.reverseOrder());
        return cardList;
    }

    public int parseNumber(String pattern) throws Exception{
        return switch (pattern.charAt(0)) {
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> throw new IllegalStateException("Unexpected value: " + pattern);
        };
    }

    public Decors parseDecors(String pattern) throws Exception{
        var decors = pattern.charAt(pattern.length()-1);
        return switch (decors) {
            case 'S' -> Decors.Spade;
            case 'H' -> Decors.Heart;
            case 'C' -> Decors.Club;
            case 'D' -> Decors.Diamond;
            default -> throw new IllegalStateException("Unexpected value: " + decors);
        };
    }
}
