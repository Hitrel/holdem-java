package agile.assignment.holdem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import agile.assignment.holdem.card.Card;
import agile.assignment.holdem.card.CardResolver;
import agile.assignment.holdem.card.Decors;
import agile.assignment.holdem.types.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        assertEquals(10,resolver.parseNumber("TD") );
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

    @Test
    @DisplayName("HighCard Compare")
    void highCardCompare() throws Exception {
        var highCard1 = new HighCard(new ArrayList<>() {{
            add(new Card(Decors.Heart, 2));
            add(new Card(Decors.Spade, 3));
            add(new Card(Decors.Spade, 4));
            add(new Card(Decors.Club, 8));
            add(new Card(Decors.Club, 13));
            sort(Comparator.reverseOrder());
        }});


        var highCard2 = new HighCard(new ArrayList<>() {{
            add(new Card(Decors.Club, 11));
            add(new Card(Decors.Spade, 9));
            add(new Card(Decors.Spade, 5));
            add(new Card(Decors.Heart, 6));
            add(new Card(Decors.Heart, 8));
            sort(Comparator.reverseOrder());
        }});

        Assertions.assertEquals(2, highCard1.compareTo(highCard2));
    }

    @Test
    @DisplayName("Pair Compare")
    void comparePair() {
        var pair1 = new Pair(
                new Card(Decors.Club, 11),
                new ArrayList<>() {{
                    add(new Card(Decors.Spade, 5));
                    add(new Card(Decors.Heart, 6));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
        }},
                new ArrayList<>() {{
            add(new Card(Decors.Club, 11));
            add(new Card(Decors.Spade, 11));
            add(new Card(Decors.Spade, 5));
            add(new Card(Decors.Heart, 6));
            add(new Card(Decors.Heart, 8));
            sort(Comparator.reverseOrder());
        }});

        var pair2 = new Pair(
                new Card(Decors.Club, 10),
                new ArrayList<>() {{
                    add(new Card(Decors.Spade, 5));
                    add(new Card(Decors.Heart, 6));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 10));
                    add(new Card(Decors.Spade, 10));
                    add(new Card(Decors.Spade, 5));
                    add(new Card(Decors.Heart, 6));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});
        Assertions.assertEquals(2, pair1.compareTo(pair2));
    }

    @Test
    @DisplayName("Two Compare")
    void compareTwoPair() {
        var twoPair1 = new TwoPair(
                new Card(Decors.Club, 11),
                new Card(Decors.Heart, 3),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 8));
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 11));
                    add(new Card(Decors.Spade, 11));
                    add(new Card(Decors.Spade, 3));
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});
        var twoPair2 = new TwoPair(
                new Card(Decors.Club, 11),
                new Card(Decors.Heart, 4),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 8));
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 11));
                    add(new Card(Decors.Spade, 11));
                    add(new Card(Decors.Spade, 4));
                    add(new Card(Decors.Heart, 4));
                    add(new Card(Decors.Heart, 9));
                    sort(Comparator.reverseOrder());
                }});
    }

    @Test
    @DisplayName("Three of a kind Compare")
    void compareThreeOfAKind() {
        var threeOfAKind1 = new ThreeOfAKind(
                new Card(Decors.Club, 11),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 8));
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 11));
                    add(new Card(Decors.Spade, 11));
                    add(new Card(Decors.Heart, 11));
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});
        var threeOfAKind2 = new ThreeOfAKind(
                new Card(Decors.Club, 8),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 7));
                    sort(Comparator.reverseOrder());
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 8));
                    add(new Card(Decors.Spade, 8));
                    add(new Card(Decors.Heart, 7));
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});

        Assertions.assertEquals(2, threeOfAKind1.compareTo(threeOfAKind2));
    }

    @Test
    @DisplayName("Four of a kind Compare")
    void compareFourOfAKind() {
        var fourOfAKind1 = new FourOfAKind(
                new Card(Decors.Club, 8),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 3));
                    sort(Comparator.reverseOrder());
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 8));
                    add(new Card(Decors.Spade, 8));
                    add(new Card(Decors.Diamond, 8));
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});

        var fourOfAKind2 = new FourOfAKind(
                new Card(Decors.Club, 7),
                new ArrayList<>() {{
                    add(new Card(Decors.Heart, 3));
                    sort(Comparator.reverseOrder());
                }},
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 7));
                    add(new Card(Decors.Spade, 7));
                    add(new Card(Decors.Diamond, 7));
                    add(new Card(Decors.Heart, 3));
                    add(new Card(Decors.Heart, 7));
                    sort(Comparator.reverseOrder());
                }});
        Assertions.assertEquals(2, fourOfAKind1.compareTo(fourOfAKind2));
    }

    @Test
    @DisplayName("FullHouse Compare")
    void compareFullHouse() {
        var fullHouse1 = new FullHouse(
                new Card(Decors.Club, 11),
                new Card(Decors.Heart, 8),
                new ArrayList<>(),
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 11));
                    add(new Card(Decors.Spade, 11));
                    add(new Card(Decors.Diamond, 11));
                    add(new Card(Decors.Spade, 8));
                    add(new Card(Decors.Heart, 8));
                    sort(Comparator.reverseOrder());
                }});
        var fullHouse2 = new FullHouse(
                new Card(Decors.Club, 13),
                new Card(Decors.Heart, 6),
                new ArrayList<>(),
                new ArrayList<>() {{
                    add(new Card(Decors.Club, 13));
                    add(new Card(Decors.Spade, 13));
                    add(new Card(Decors.Diamond, 13));
                    add(new Card(Decors.Spade, 6));
                    add(new Card(Decors.Heart, 6));
                    sort(Comparator.reverseOrder());
                }});

        Assertions.assertEquals(-2, fullHouse1.compareTo(fullHouse2));
    }

    @Test
    @DisplayName("Straight Compare")
    void compareStraight() {
        var straight1 = new Straight(new ArrayList<>() {{
            add(new Card(Decors.Heart, 2));
            add(new Card(Decors.Spade, 3));
            add(new Card(Decors.Spade, 4));
            add(new Card(Decors.Club, 5));
            add(new Card(Decors.Club, 14));
            sort(Comparator.reverseOrder());
        }});

        var straight2 = new Straight(new ArrayList<>() {{
            add(new Card(Decors.Diamond, 2));
            add(new Card(Decors.Heart, 3));
            add(new Card(Decors.Club, 4));
            add(new Card(Decors.Diamond, 5));
            add(new Card(Decors.Heart, 6));
            sort(Comparator.reverseOrder());
        }});

        Assertions.assertEquals(-2, straight1.compareTo(straight2));
    }

    @Test
    @DisplayName("Straight Flush Compare")
    void compareStraightFlush() {
        var straightFlush1 = new StraightFlush(new ArrayList<>() {{
            add(new Card(Decors.Heart, 2));
            add(new Card(Decors.Heart, 3));
            add(new Card(Decors.Heart, 4));
            add(new Card(Decors.Heart, 5));
            add(new Card(Decors.Heart, 14));
            sort(Comparator.reverseOrder());
        }});

        var straightFlush2 = new StraightFlush(new ArrayList<>() {{
            add(new Card(Decors.Diamond, 2));
            add(new Card(Decors.Diamond, 3));
            add(new Card(Decors.Diamond, 4));
            add(new Card(Decors.Diamond, 5));
            add(new Card(Decors.Diamond, 6));
            sort(Comparator.reverseOrder());
        }});

        Assertions.assertEquals(-2, straightFlush1.compareTo(straightFlush2));
    }

    @Test
    @DisplayName("Flush Compare")
    void compareFlush() {
        var flush1 = new Flush(new ArrayList<>() {{
            add(new Card(Decors.Heart, 2));
            add(new Card(Decors.Heart, 8));
            add(new Card(Decors.Heart, 4));
            add(new Card(Decors.Heart, 5));
            add(new Card(Decors.Heart, 14));
            sort(Comparator.reverseOrder());
        }});

        var flush2 = new Flush(new ArrayList<>() {{
            add(new Card(Decors.Diamond, 2));
            add(new Card(Decors.Diamond, 3));
            add(new Card(Decors.Diamond, 8));
            add(new Card(Decors.Diamond, 5));
            add(new Card(Decors.Diamond, 6));
            sort(Comparator.reverseOrder());
        }});

        Assertions.assertEquals(2, flush1.compareTo(flush2));
    }

    @Test
    @DisplayName("Royal Flush Compare")
    void compareRoyalFlush() {
        var royalFlush1 = new RoyalFlush(new ArrayList<>() {{
            add(new Card(Decors.Diamond, 10));
            add(new Card(Decors.Diamond, 11));
            add(new Card(Decors.Diamond, 12));
            add(new Card(Decors.Diamond, 13));
            add(new Card(Decors.Diamond, 14));
            sort(Comparator.reverseOrder());
        }});

        var royalFlush2 = new RoyalFlush(new ArrayList<>() {{
            add(new Card(Decors.Heart, 10));
            add(new Card(Decors.Heart, 11));
            add(new Card(Decors.Heart, 12));
            add(new Card(Decors.Heart, 13));
            add(new Card(Decors.Heart, 14));
            sort(Comparator.reverseOrder());
        }});

        Assertions.assertEquals(0, royalFlush1.compareTo(royalFlush2));
    }

    @Test
    @DisplayName("build HighCard")
    void buildHighCard() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:2H,3D,8S,TC,AD");
        var black = PokerHand.handResolve(resolver.getBlack());
        System.out.println(black.getHand());
        Assertions.assertEquals(HandType.HIGH_CARD, black.getHandType());
    }

    @Test
    @DisplayName("build Pair")
    void buildPair() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:2H,2D,3D,TS,JD");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.PAIR, black.getHandType());
    }

    @Test
    @DisplayName("build TwoPair")
    void buildTwoPair() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:3H,3S,4H,4D,5S");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.TWO_PAIRS, black.getHandType());
    }

    @Test
    @DisplayName("build Three of a kind")
    void buildThreeOfAKind() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:5H,5D,5C,6S,7H");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.THREE_OF_A_KIND, black.getHandType());
    }

    @Test
    @DisplayName("build Straight")
    void buildStraight() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:2H,3D,4C,5H,AD");
        resolver.parseHand("White:5H,6D,7S,8H,9D");

        var black = PokerHand.handResolve(resolver.getBlack());
        Assertions.assertEquals(HandType.STRAIGHT, black.getHandType());

        var white = PokerHand.handResolve(resolver.getWhite());
        Assertions.assertEquals(HandType.STRAIGHT, white.getHandType());
    }

    @Test
    @DisplayName("build Flush")
    void buildFlush() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:2H,4H,7H,9H,TH");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.FLUSH, black.getHandType());
    }

    @Test
    @DisplayName("build Full House")
    void buildFullHouse() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:6H,6D,6S,8H,8C");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.FULL_HOUSE, black.getHandType());
    }

    @Test
    @DisplayName("build Four of a kind")
    void buildFourOfAKind() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:JH,JD,JC,JS,5S");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.FOUR_OF_A_KIND, black.getHandType());
    }

    @Test
    @DisplayName("build Straight Flush")
    void buildStraightFlush() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:2H,3H,4H,5H,AH");
        resolver.parseHand("White:5D,6D,7D,8D,9D");

        var black = PokerHand.handResolve(resolver.getBlack());
        Assertions.assertEquals(HandType.STRAIGHT_FLUSH, black.getHandType());

        var white = PokerHand.handResolve(resolver.getWhite());
        Assertions.assertEquals(HandType.STRAIGHT_FLUSH, white.getHandType());
    }

    @Test
    @DisplayName("build Royal Flush")
    void buildRoyalFlush() throws Exception {
        var resolver = new CardResolver();
        resolver.parseHand("Black:TH,JH,QH,KH,AH");
        var black = PokerHand.handResolve(resolver.getBlack());

        Assertions.assertEquals(HandType.ROYAL_FLUSH, black.getHandType());
    }
}
