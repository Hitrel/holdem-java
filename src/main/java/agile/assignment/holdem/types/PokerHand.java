package agile.assignment.holdem.types;

import agile.assignment.holdem.card.Card;
import jdk.jshell.spi.ExecutionControl.NotImplementedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.lang.model.type.ErrorType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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


    public static HighCard handResolve(List<Card> hand) throws Exception{
        hand.sort(Comparator.reverseOrder());
            var handType = resolveHandType(hand);
            return switch (handType) {
                case PAIR -> buildPair(hand);
                case TWO_PAIRS -> buildTwoPair(hand);
                case THREE_OF_A_KIND -> buildThreeOfAKind(hand);
                case FULL_HOUSE -> buildFullHouse(hand);
                case FOUR_OF_A_KIND -> buildFourofAKind(hand);
                case HIGH_CARD -> resolveHighCard(hand);
                default -> throw new IllegalStateException();
            };
    }

    public static HandType resolveHandType(List<Card> hand) throws Exception {
        int level = 0;
        for (int i = 0; i < hand.size(); i++) {
            for (int j = i+1; j < hand.size(); j++) {
                if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                    level += 1;
                }
            }
        }

        return switch (level) {
            case 0 -> HandType.HIGH_CARD;
            case 1 -> HandType.PAIR;
            case 2 -> HandType.TWO_PAIRS;
            case 3 -> HandType.THREE_OF_A_KIND;
            case 4 -> HandType.FULL_HOUSE;
            case 6 -> HandType.FOUR_OF_A_KIND;
            default -> throw new IllegalStateException();
        };
    }

    private static HighCard buildHighCard(List<Card> hand) {
        return new HighCard(hand);
    }

    private static Pair buildPair(List<Card> hand) {
       Card pair = null;
       List<Card> remains = new ArrayList<>();
       hand.addAll(hand);

       for (int i = 0; i < hand.size(); i++) {
           int repeat = 0;
           for (int j = i+1; j < hand.size(); j++) {
               if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                   repeat += 1;
               }
           }

           if (repeat == 1) {
               pair = hand.get(i);
               for (var card : remains) {
                   if (card.getNumeralPattern() == pair.getNumeralPattern()) {
                       remains.remove(card);
                   }
               }


           }
       }

       return new Pair(pair, remains, hand);
    }

    private static TwoPair buildTwoPair(List<Card> hand) {
        List<Card> pairs = new ArrayList<>();
        List<Card> remains = new ArrayList<>();
        remains.addAll(hand);

        for (int i = 0; i < hand.size(); i++) {
            int repeat = 0;
            for (int j = i+1; j < hand.size(); j++) {
                if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                    repeat += 1;
                }
            }

            if (repeat == 1) {
                pairs.add(hand.get(i));
                for (Iterator<Card> it = remains.iterator(); it.hasNext();) {
                    var card = it.next();
                    for (var pair : pairs) {
                        if (card.getNumeralPattern() == pair.getNumeralPattern()) {
                            it.remove();
                            break;
                        }
                    }
                }
            }
        }

        pairs.sort(Comparator.reverseOrder());
        return new TwoPair(pairs.get(0), pairs.get(1), remains, hand);
    }

    private static ThreeOfAKind buildThreeOfAKind(List<Card> hand) {
        Card threeKind = null;
        List<Card> remains = new ArrayList<>();
        remains.addAll(hand);

        for (int i = 0; i < hand.size(); i++) {
            int repeat = 0;
            for (int j = i+1; j < hand.size(); j++) {
                if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                    repeat += 1;
                }
            }

            if (repeat == 2) {
                threeKind = hand.get(i);
                for (Iterator<Card> it = remains.iterator(); it.hasNext(); ) {
                    var card = it.next();
                    if (card.getNumeralPattern() == threeKind.getNumeralPattern()) {
                        it.remove();
                    }
                }
            }
        }

        return new ThreeOfAKind(threeKind, remains, hand);
    }

    private static FullHouse buildFullHouse(List<Card> hand) {
        Card threeKind = null;
        Card pair = null;
        List<Card> remains = new ArrayList<>();
        remains.addAll(hand);

        for (int i = 0; i < hand.size(); i++) {
            int repeat = 0;
            for (int j = i+1; j < hand.size(); j++) {
                if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                    repeat += 1;
                }
            }

            if (repeat == 2) {
                threeKind = hand.get(i);
                for (Iterator<Card> it = remains.iterator(); it.hasNext();) {
                    var card = it.next();
                    if (card.getNumeralPattern() == threeKind.getNumeralPattern()) {
                        it.remove();
                    }
                }
            }

            if (repeat == 1 && remains.contains(hand.get(i))) {
                pair = hand.get(i);
                for (Iterator<Card> it = remains.iterator(); it.hasNext();) {
                    var card = it.next();
                    if (card.getNumeralPattern() == pair.getNumeralPattern()) {
                        it.remove();
                    }
                }
            }

        }

        return new FullHouse(threeKind, pair, remains, hand);
    }

    private static FourOfAKind buildFourofAKind(List<Card> hand) {
        Card fourKind = null;
        List<Card> remains = new ArrayList<>();
        remains.addAll(hand);

        for (int i = 0; i < hand.size(); i++) {
            int repeat = 0;
            for (int j = i+1; j < hand.size(); j++) {
                if (hand.get(i).getNumeralPattern() == hand.get(j).getNumeralPattern()) {
                    repeat += 1;
                }
            }

            if (repeat == 3) {
                fourKind = hand.get(i);
                for (Iterator<Card> it = remains.iterator(); it.hasNext();) {
                    var card = it.next();
                    if (card.getNumeralPattern() == fourKind.getNumeralPattern()) {
                        it.remove();
                    }
                }
            }
        }

        return new FourOfAKind(fourKind, remains, hand);
    }

    private static Flush buildFlush(List<Card> hand) {
        return new Flush(hand);
    }

    private static Straight buildStraight(List<Card> hand) {
        return new Straight(hand);
    }

    private static StraightFlush buildStraightFlush(List<Card> hand) {
        return new StraightFlush(hand);
    }

    private static RoyalFlush buildRoyalFlush(List<Card> hand) {
        return new RoyalFlush(hand);
    }


    private static HighCard resolveHighCard(List<Card> hand) {
        boolean isFlush = false;
        try {
            for (int i = 0; i < hand.size() - 1; i++) {
                if (hand.get(i).getDecors() != hand.get(i+1).getDecors()) {
                    throw new IllegalStateException();
                }
            }

            isFlush = true;
        } catch (IllegalStateException ignored) { }

        boolean isStraight = false;
        try {
            for (int i = 0; i < hand.size() - 1; i++) {
                if(hand.get(i).getNumeralPattern() != 14 && hand.get(i+1).getNumeralPattern() != hand.get(i).getNumeralPattern()-1) {
                    throw new IllegalStateException();
                } else if (hand.get(i).getNumeralPattern() == 14 && hand.get(i+1).getNumeralPattern() != 13 && hand.get(i+1).getNumeralPattern() != 5) {
                    throw new IllegalStateException();
                }
            }

            isStraight = true;
        } catch (IllegalStateException ignore){ }

        if (isStraight && isFlush) {
            if (hand.get(0).getNumeralPattern() == 14 && hand.get(1).getNumeralPattern() == 13) {
                return buildRoyalFlush(hand);
            } else {
                return buildStraightFlush(hand);
            }
        } else if (isFlush) {
            return buildFlush(hand);
        } else if (isStraight) {
            return buildStraight(hand);
        } else {
            return buildHighCard(hand);
        }
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


