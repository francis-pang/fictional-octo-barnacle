package crackingthecodinginterview.hard;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Write a method to shuffle a deck of cards. It must be a perfect shuffle-in other words, each of the 52!
 * permutations of the deck has to be equally likely. Assume that you are given a random number generator which is
 * perfect.
 */
public class Shuffle {
  private final int DECK_SIZE = 52;

  public void shuffleADeckOfCards() {
    List<PokerCard> deck = createDeck();
    shuffleCard(deck);
  }

  private List<PokerCard> createDeck() {
    List<PokerCard> deck = new LinkedList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        deck.add(new PokerCard(suit, rank));
      }
    }
    return deck;
  }

  private void shuffleCard(List<PokerCard> deck) {
    if (deck.size() == 0) {
      return;
    }
    int drawStartPosition = ThreadLocalRandom.current().nextInt(1, deck.size());
    int drawEndPosition = ThreadLocalRandom.current().nextInt(drawStartPosition, deck.size());
    ListIterator<PokerCard> pokerCardListIterator = deck.listIterator(drawStartPosition + 1);
    pokerCardListIterator.set(deck.get(drawEndPosition));
    List<PokerCard> drawnCards = deck.subList(drawStartPosition + 1, drawEndPosition);
    shuffleCard(drawnCards);
    deck.addAll(drawnCards);
  }

  class PokerCard {
    public Suit suit;
    public Rank rank;

    public PokerCard(Suit suit, Rank rank) {
      this.suit = suit;
      this.rank = rank;
    }
  }

  enum Suit {
    DIAMOND, CLUB, HEART, SPADE
  }

  enum Rank {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
  }
}
