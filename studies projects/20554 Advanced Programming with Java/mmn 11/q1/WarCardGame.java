/*
 *creator: Elroi Izaks
 *email: elroiizaks@gmail.com
 *version: 9/4/22
 */

//WarCardGame class represents a game of "war" (playing cards game).
//the game is automatically played, and every turn are declared. 

package q1;

import javax.swing.JOptionPane;

public class WarCardGame {

	static private final int NUM_OF_FACE_DOWN_CARDS = 2; // number of face down cards in war-situation (ineffective
															// cards)
/**
 * main function. an automatically game of "war"
 */
	public static void main(String[] args) {

		// the arena deck. from here the cards initially Divides, and the played cards
		// are laying in here
		DeckOfCards arenaDeck = new DeckOfCards(true);
		arenaDeck.shuffle();
		DeckOfCards playerOne = new DeckOfCards(false);
		DeckOfCards playerTwo = new DeckOfCards(false);

		arenaDeck.dealCards(playerOne, playerTwo);

		// the game is played as long as the two players have cards. the first to end is
		// deck lose (even if is deck ended while war-situation)
		while (!(playerOne.deckIsEmpty() || playerTwo.deckIsEmpty())) {
			Card playerOneCardInWar = playerOne.dealCard(arenaDeck);
			Card playerTwoCardInWar = playerTwo.dealCard(arenaDeck);

			// the current status (for printing)
			String statusWar = "player one: " + playerOneCardInWar + "\nplayer two: " + playerTwoCardInWar + "\n";

			switch (playerOneCardInWar.isStronger(playerTwoCardInWar)) {// Who's card is stronger
			case 1:
				JOptionPane.showMessageDialog(null, statusWar + "player One win!");
				arenaDeck.dealCards(playerOne);
				System.out.println("a have " + playerOne.numberOfCards() + " cards\tb have " + playerTwo.numberOfCards()
						+ " cards.");//optional. may be removed
				break;
			case -1:
				JOptionPane.showMessageDialog(null, statusWar + "player Two win!");
				arenaDeck.dealCards(playerTwo);
				System.out.println("a have " + playerOne.numberOfCards() + " cards\tb have " + playerTwo.numberOfCards()
						+ " cards.");//optional. may be removed
				break;
			case 0:// war-situation
				JOptionPane.showMessageDialog(null, statusWar + "tie- war!");
				for (int i = 0; i < NUM_OF_FACE_DOWN_CARDS && !playerOne.deckIsEmpty()
						&& !playerTwo.deckIsEmpty(); i++) {// lay NUM_OF_FACE_DOWN_CARDS face down cards, as long as there is
															// enough in both decks
					playerOneCardInWar = playerOne.dealCard(arenaDeck);
					playerTwoCardInWar = playerTwo.dealCard(arenaDeck);
				}
			}
		}
		//announce the winner
		if (playerOne.deckIsEmpty())
			JOptionPane.showMessageDialog(null, "player two win the tournament!");
		if (playerTwo.deckIsEmpty())
			JOptionPane.showMessageDialog(null, "player one win the tournament!");
	}
}
