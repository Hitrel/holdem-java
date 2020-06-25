package agile.assignment.holdem;

import agile.assignment.holdem.card.CardResolver;
import agile.assignment.holdem.types.HighCard;
import agile.assignment.holdem.types.PokerHand;

import java.util.Scanner;

public class Holdem {
    public static void main(String[] args) throws Exception{
        var scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            var resolver = CardResolver.cardsResolve(scanner.nextLine());
            var black = PokerHand.handResolve(resolver.getBlack());
            var white = PokerHand.handResolve(resolver.getWhite());

            var compare = black.compareTo(white);

            switch (compare) {
                case 1 -> System.out.println("Black Win! " + black.getHandType().toString());
                case 2 -> System.out.println("Black Win!" + "HighCard: " + black.getHighCard());
                case -1 -> System.out.println("White Win! " + white.getHandType().toString());
                case -2 -> System.out.println("White Win! " + "HighCard: " + white.getHighCard());
                case 0 -> System.out.println("Tie");
            }
        }
    }
}
