/*
 *creator: Elroi Izaks
 *email: elroiizaks@gmail.com
 *version: 9/4/22
 */

//DeckOfCards class represents a deck of playing cards
//the deck may be full or empty (in here, 'deck' = group of cards)

package q1;

import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {

	private static final SecureRandom randomNumbers = new SecureRandom();
	private static final int NUMBER_OF_CARDS = 52;
	private static final boolean ACE_IS_STRONGEST = false; // true if prefer that the ace is the stronger. else - the
															// weakest

	private ArrayList<Card> deck = new ArrayList<Card>();

	/**
	* constructor
	* @param  fillRegularDeck  if needed traditional deck- true. else - the deck will be empty
	*/
	public DeckOfCards(boolean fillRegularDeck) {
		if (fillRegularDeck == true) {
			String[] faces = { "Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack",
					"Queen", "King" };
			String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

			// populate deck with Card objects
			for (int count = 0; count < NUMBER_OF_CARDS; count++)
				// ACE_IS_STRONGEST change the hierarchy order of the values
				deck.add(new Card(faces[count % 13], suits[count / 13], (count + (ACE_IS_STRONGEST ? 12 : 0)) % 13));
		}
	}
	
	/**
	* shuffle the deck with one-pass algorithm
	*/
	public void shuffle() {

		for (int first = 0; first < this.numberOfCards(); first++) {

			int second = randomNumbers.nextInt(this.numberOfCards());

			Card temp = deck.get(first);
			deck.set(first, deck.get(second));
			deck.set(second, temp);
		}
	}

	/**
	 * deal one card to the otherDeck. the card will be deleted from the original deck.
	 * @param otherDeck the deck to deal a card to
	 * @return the passes card. if deck empty, function return null
	 */
	public Card dealCard(DeckOfCards otherDeck) {

		if (this.numberOfCards() == 0)
			return null;
		Card removed = this.deck.get(0);
		otherDeck.setNewCard(removed);
		this.deck.remove(0);

		return removed;
	}

	
	/**
	 *deal all the cards to the otherDeck. the cards will be deleted from the original deck 
	 * @param otherDeck the deck to deal the cards
	 */
	public void dealCards(DeckOfCards otherDeck) {
		int temp = this.numberOfCards();
		for (int i = 0; i < temp; i++) {
			this.dealCard(otherDeck);
		}
	}


	/**
	 * deal all the cards between two decks, one card at a time.
	 * if the original number of cards in the deck is odd, the first deck get the extra card
	 * the cards will be deleted from the original deck
	 * @param one first deck
	 * @param two second deck
	 */
	public void dealCards(DeckOfCards one, DeckOfCards two) {
		int temp = this.numberOfCards();
		for (int i = 0; i < temp; i++) {
			this.dealCard(one);
			i++;
			if (i == temp) break;
			this.dealCard(two);
		}
	}


	/**
	 * check if the deck is empty
	 * @return true if deck is empty
	 */
	public boolean deckIsEmpty() {
		if (this.numberOfCards() == 0)
			return true;
		return false;
	}

	/**
	 * return the number of cards in the deck
	 * @return number of cards in the deck
	 */
	public int numberOfCards() {
		return this.deck.size();
	}


	/**
	 * enter given new card to the end of the deck
	 * @param newCard the card to enter the end of the deck
	 */
	private void setNewCard(Card newCard) {
		deck.add(newCard);
	}
}