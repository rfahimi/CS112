/**
 * The AceyHand class represents a hand of playing cards in Acey.
 */
class AceyHand extends Hand {
    private int difference;

    /**
     * Constructs an empty AceyHand object.
     */
    public AceyHand() {
        super(3);
    }

    /**
     * Adds a card to the AceyHand object.
     *
     * @param card The card to add.
     */
    public void addCard(Card card) {
        super.addCard(card);
        if (numCards == 2) {
            difference = Math.abs(hand[1].rank.toInt() - hand[0].rank.toInt());
        }
    }

    public int getFavorableCards(String decision, int dealtCardsInt, Card[] dealtCardsArray) {
        int favorableCards = 0;

        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int lower = Math.min(firstRank, secondRank);
        int higher = Math.max(firstRank, secondRank);

        int lowRange, highRange;
        if (decision.equals("mid")) {
            lowRange = lower + 1;
            highRange = higher - 1;
        } else if (decision.equals("high")) {
            lowRange = 2;
            highRange = lower - 1;
        } else { // decision == "low"
            lowRange = higher + 1;
            highRange = 14;
        }

        for (int i = 2; i <= 14; i++) {
            if (i == firstRank || i == secondRank) {
                continue;
            }

            if (i >= lowRange && i <= highRange) {
                favorableCards += 28;
                for (int j = 0; j < dealtCardsInt; j++) {
                    if (i == dealtCardsArray[j].rank.toInt()) {
                        favorableCards--;
                    }
                }
            }
        }

        return favorableCards;
    }

    /**
     * Checks if the AceyHand object is a high hand.
     *
     * @return true if the AceyHand object is a high hand, false otherwise.
     */
    public boolean isHigh() {
        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int thirdRank = getThirdCard().rank.toInt();
        return numCards == 3 && firstRank == secondRank
                && thirdRank > firstRank;
    }

    /**
     * Checks if the AceyHand object is a low hand.
     *
     * @return true if the AceyHand object is a low hand, false otherwise.
     */
    public boolean isLow() {
        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int thirdRank = getThirdCard().rank.toInt();
        return numCards == 3 && firstRank == secondRank
                && thirdRank < firstRank;
    }

    /**
     * Checks if the AceyHand object is a mid hand.
     *
     * @return true if the AceyHand object is a mid hand, false otherwise.
     */
    public boolean isMid() {
        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int thirdRank = getThirdCard().rank.toInt();
        return numCards == 3 && (firstRank < thirdRank && thirdRank < secondRank)
                || (secondRank < thirdRank && thirdRank < firstRank);
    }

    public boolean thirdCardMatchesCard() {
        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int thirdRank = getThirdCard().rank.toInt();
        return firstRank == thirdRank || secondRank == thirdRank;
    }

    public boolean thirdCardMatchesCards() {
        int firstRank = getFirstCard().rank.toInt();
        int secondRank = getSecondCard().rank.toInt();
        int thirdRank = getThirdCard().rank.toInt();
        return firstRank == thirdRank && secondRank == thirdRank;
    }

    // Getters

    public Card getFirstCard() {
        return hand[0];
    }

    public Card getSecondCard() {
        return hand[1];
    }

    public Card getThirdCard() {
        return hand[2];
    }

    public int getDifference() {
        return difference;
    }
}
