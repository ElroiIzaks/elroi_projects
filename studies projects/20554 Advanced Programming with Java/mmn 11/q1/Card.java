/*
 *creator: Elroi Izaks
 *email: elroiizaks@gmail.com
 *version: 9/4/22
 */

//Card class represents a playing card
//the different cards have strength respectively to there number

package q1;

public class Card {
	private final String face; // face of card ("Ace", "Deuce", ...)
	private final String suit; // suit of card ("Hearts", "Diamonds", ...)
	private final int value;


	/**
	 * three-argument constructor initializes card's face, suit and value
	 * @param cardFace the face of the card ("Ace", "Deuce", ...)
	 * @param cardSuit the suit of the card ("Hearts", "Diamonds", ...)
	 * @param cardValue the value of the card (can be anything, for more open options, like jokers)
	 */
	public Card(String cardFace, String cardSuit, int cardValue) {
		this.face = cardFace; // initialize face of card
		this.suit = cardSuit; // initialize suit of card
		this.value = cardValue; // initialize value of card by hierarchy (can be change by the constant
								// ACE_IS_STRONGEST in DeckOfCards class)
	}

	// return String representation of Card
	/**
	 * to_string function
	 * format: "ace of diamond"
	 */
	public String toString() {
		return face + " of " + suit;
	}

	/**
	 * return who is stronger between two cards, compared by values
	 * @param other the card to compare with
	 * @return 1- if this card is stronger; 0- if equals; (-1)- if the other card is stronger
	 */
	public int isStronger(Card other) {
		if (this.value > other.value)
			return 1;
		if (this.value == other.value)
			return 0;
		return -1;
	}
}