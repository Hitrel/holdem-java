package agile.assignment.holdem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HoldemTests {



    String s = "Black:2H,3D,5S,9C,KD White:2C,3H,4S,8C,AH";
    List<String> parseString  = new ArrayList<>() {{
        add("Black:2H,3D,5S,9C,KD");
        add("White:2C,3H,4S,8C,AH");
    }};

    List<Card> black = new ArrayList<>() {{
        add(new Card(Decors.Heart, 2));
        add(new Card(Decors.Diamond, 3));
        add(new Card(Decors.Spade, 5));
        add(new Card(Decors.Club, 9));
        add(new Card(Decors.Diamond, 13));
        sort(Comparator.reverseOrder());
    }};

    List<Card> white = new ArrayList<>() {{
        add(new Card(Decors.Club, 2));
        add(new Card(Decors.Heart, 3));
        add(new Card(Decors.Spade, 4));
        add(new Card(Decors.Club, 8));
        add(new Card(Decors.Heart, 14));
        sort(Comparator.reverseOrder());
    }};
    @Test
    @DisplayName("ParseString")
    void parseString() {
        var resolve = CardResolver.parse(s);
        assertEquals(resolve, parseString);
    }

    @Test
    @DisplayName("ParseNumber")
    void parseNumberPattern() throws Exception {
        var resolver = new CardResolver();
        assertEquals(2,resolver.parseNumber("2S") );
        assertEquals(10,resolver.parseNumber("10D") );
        assertEquals(13, resolver.parseNumber("KC"));
        assertEquals(14, resolver.parseNumber("AH"));
    }

    @Test
    @DisplayName("ParseDecors")
    void parseDecorsPattern() throws Exception {
        var resolve = new CardResolver();
        assertEquals(Decors.Spade, resolve.parseDecors("10S"));
        assertEquals(Decors.Heart, resolve.parseDecors("KH"));
        assertEquals(Decors.Club, resolve.parseDecors("2C"));
        assertEquals(Decors.Diamond, resolve.parseDecors("JD"));
    }

    @Test
    @DisplayName("ParseBlack")
    void parseBlack() throws Exception {
        var resolve = new CardResolver();
        assertEquals(black, resolve.parseHand(parseString.get(0)));
    }

    @Test
    @DisplayName("TotalParse")
    void parseBothTwoHand() throws Exception {
        var resolve = CardResolver.cardsResolve(s);

        assertEquals(white, resolve.getWhite());
        assertEquals(black, resolve.getBlack());
    }

}
