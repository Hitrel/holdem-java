package agile.assignment.holdem.types;

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
