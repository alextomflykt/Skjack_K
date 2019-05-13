import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
 * Information about how the game of Flip8 works is, while not neccesary, very helpful if you want to-
 * truly understand what's happening both in the application and in the code.
 * 
 * 
 * General info about Flip8:
 * 
 * 	Uses a standard deck of cards minus the jokers.
 * 	Is a lot like the widely acclaimed game "Uno" by Hasbro.
 * 	Even though the two games are quite alike each other, Flip8 is not a ripp-off of Uno since it has existed since acient times.
 * 	The goal of the game is to get rid of all of your cards before your opponent.
 * 
 * The rules of Flip8 are as follows:
 * 
 * 	Preperation:
 * 
 * 		Each player gets handed six cards in the beginning of the game.
 * 		Do not show your opponent your cards. However you are allowed to look att your cards yourself.
 * 		The main pile then get's put face down between the players.
 * 		The top card of the main pile (I will henceforth refer to the main pile as the talong) is placed face up just beside the talong.
 * 		If the card that you just placed is either an ace of any suit or an eight of any suit, it is immedietly reshuffled into the talong.
 * 		The card lying beside the talong (that later will evolve to be a pile of cards) will henceforth be reffered to as the dump.
 * 
 * 	Playing:
 * 		
 * 		You play a card by placing it on top of the dump.
 * 		For a card to be allowed to be played it must either match the value or the suit of the card on top of the dump.
 * 		Exceptions to the rule above:
 * 
 * 			You are allowed to play an Eight on top of any card except another eight and an ace. If it is your last card -
 * 			you are simply not allowed to play it. What you'll do instead will be explained later.
 * 			You are not allowed to play an Ace on top of an Eight. However You are allowed to play an ace on top of another ace.
 * 			Just like the eight, you're not allowed to play an ace if it's your last card.
 * 		
 * 		Whenever a player playes an ace all other players has to take one card from the talong and then the player must continue by-
 * 		placing another card on top of their ace.
 * 		Whenever an eight gets played the player who played it has to demand the suit of the next card to be played.
 * 		For clarification of the rule above, the next card that is played MUST be of the suit that was demanded.
 * 		If you have multiple cards of the same value you are allowed to play all of them on the same turn as long as the first -
 * 		card played agrees with the previously stated rules. This means that you're still not allowed to play two eights on the same turn.
 * 		In the scenario that you can't play any of your cards, you must at least take one but up to three cards from the talong.
 * 		If a player has taken three cards on one turn, the turn passes over to the next player.
 * 		In the scenario that the talong is about to be emptied (with one card remaining), one of the players must take all the cards from-
 * 		the dump except the top one and reshuffle them back into the talong.
 * 		The first one to get an empty hand is declared winner.
 */
public class Vend8 {
	//Import of random and scanner.
	public static Random rand = new Random();
	static Scanner scan = new Scanner(System.in);
	/*
	 * Declaration of variables that are going to be used throughout the application.
	 * 
	 * ArrayLists:
	 * 
	 * talong is a variable describing the main pile of cards from which all the other cards are taken from.
	 * hand - representating the player's cards.
	 * dump - describing the cards that have already been played.
	 * aiHand - the hand of the player's ai opponent.
	 * 
	 * 
	 * Integers:
	 * 
	 * cCard (chosen card) - a card that is being considered by either the AI or the Player.
	 * dValue (dump value) - the value of the card on top of the dump deck/pile.
	 * dSuit (dump suit) - the suit of the card on top of the dump deck/pile.
	 * addedC (added cards) - the number of times the player/AI has taken a card from the talong.
	 * cCardSuit (chosen card suit) - the suit of the card that is being considered by either the player or the AI.
	 * cCardValue (chosen card value) - the value of - you get the picture.
	 * cOption (chosen option) - the action of which the player's whishes to take.
	 * dumpT (dump top) - the card at the top of the dump pile.
	 * dumpOrgS (dump original size) - the amount of cards that are in the dump pile before the ai plays a card.
	 * turns - the amount of turns the current game has taken.
	 * 
	 * 
	 * Booleans:
	 * 
	 * autoPlay - checks if the player wants an autopilot.
	 * turn - checks who's turn it is.
	 */
	static ArrayList<Integer> talong, hand, dump, aiHand;
	static int cCard, dValue, dSuit, addedC, cCardSuit, cCardValue, cOption, dumpT, dumpOrgS, turns;
	static boolean autoPlay, turn;
	//Prepares the game in accordence to the rules of flip 8, among some other less important things like resetting the turn counter.
	private static void intililize() {
		//Resetting the turn counter.
		turns = 1;
		//Creating all the card piles.
		talong = new ArrayList<Integer>();
		hand = new ArrayList<Integer>();
		dump = new ArrayList<Integer>();
		aiHand = new ArrayList<Integer>();
		//Actually declaring the booleans.
		autoPlay = false;	
		turn = true;
		//The value of a card is pretty self explanitory except for when it comes to ace, knight, queen and king. 1 = ace, 11 = knight, queen = 12 and king = 13
		//Suits work the same way, 1 = hears, 2 = spades, 3 = diamonds and 4 = clubs.
		
		//randI stand for random integer.
		int randI;
		//This loop hands out cards to the player and removes them from the talong.
		for(int k=0; k<6; k++) {
			randI = rand.nextInt(talong.size());
			hand.add(talong.get(randI));
			talong.remove(randI);
		}
		//This prepares the dump.
		randI = rand.nextInt(talong.size());
		dump.add(talong.get(randI));
		talong.remove(randI);
		//This loop hands out cards to the AI and also removes them from the talong.
		for(int k=0; k<6; k++) {
			randI = rand.nextInt(talong.size());
			aiHand.add(talong.get(randI));
			talong.remove(randI);
		}
	}
	//This application re-uses a lot of variables so this method is used to reset the values so that they can be used again in a loop.
	//It also resets the talong when needed.
	private static void valueReset() {
		dumpT = dump.get(dump.size()-(1));
		cCard = 0;
		dValue = (int) dumpT/10;
		dSuit = dumpT - dValue*10;
		addedC = 0;
		cCardSuit = 0;
		cCardValue = 0;
		cOption = 0;
		dumpOrgS = dump.size();
		//Reset of talong.
		if (talong.size() < 2) {
			for(int k = 0; k < dump.size()-1; k++) {
				talong.add(dump.get(k));
				dump.remove(k);
			}
		}
	}
	//This is the very well thought through advanced AI I've been mentioning.
	private static void ai() {
		valueReset();
		/*
		 * Because I added an optional auto pilot for the player the AI ended up being used at two places in the loop, therefore-
		 * I needed it to be able to handle both the player's hand and the AI's hand. I solved this by simply using a temporary hand-
		 * which copies the hand of the player/AI, playes the card(s) and then dumps the result back to the respective hand.
		 */
		//handTemp = temporary hand.
		ArrayList<Integer> handTemp = new ArrayList<Integer>();
		//Prints out the number of turns the match has been going on for.
		System.out.println("\nturn: " + turns + " ");
		turns++;
		//In both cases the coming two loops simply copies either the player's hand or the Ai's.
		//Player's turn.
		if (turn == true) {
			System.out.print("Players turn.\n");
			for (int k: hand) {
				handTemp.add(k);
			}	
		}
		//AI's turn
		if (turn == false) {
			System.out.print("Computer's turn.\n");
			for (int k: aiHand) {
				handTemp.add(k);
			}
		}
		//This loop assumes that the player/AI is going to take 3 cards and breaks out of it if it notices that a card has been played.
		for(int k1 = 0; k1 < 3; k1++) {
			valueReset();
			//Looking for cards to be played loop.
			for(int k: handTemp) {
				//cCardSuit = chosen card suit.
				cCardSuit = k - (int) (k/10)*10;
				cCardValue = (int)(k/10);
				//Checks if the current card (the chosen card) is able to be played.
				if(dValue == (int)(k/10) || cCardSuit == dSuit) {
					//Plays it.
					dump.add(k);
					handTemp.remove(handTemp.indexOf(k));
					System.out.println(k);
					//Checks for other cards of the same value and then playes them.
					for (int k2=0; k2 < handTemp.size(); k2++) {
						if (cCardValue == (int)(handTemp.get(k2)/10)) {
							dump.add(handTemp.get(k2));
							System.out.println(handTemp.get(k2));
							handTemp.remove(k2);
						}
						//Checks if illegal cards have been played. 
						if (cCardValue == 8 && (int)(handTemp.get(k2-1)) == 8 || (int)(handTemp.get(k2-1)) == 1) {
							
						}
					}
					
					//Breaks out of the "looking for cards to be played loop".
					break;
				}
			}
			//Checks if a card has been played, if it has the application breaks out of the "picking up cards loop".
			if (dumpOrgS < dump.size()) {
				break;
			}
			//If the previous check fails that means that the PLayer/AI has to take a card.
			System.out.print("Takes card.\n");
			int randI = rand.nextInt(talong.size());
			handTemp.add(talong.get(randI));
			talong.remove(randI);
		}
		//Checks who's turn it is and then copies over the temporary hand to either the AI or the player.
		if (turn == true) {
			hand.clear();
			for (int k: handTemp) {
				hand.add(k);
			}
		} else {
			aiHand.clear();
			for (int k: handTemp) {
				aiHand.add(k);
			}
		}
		valueReset();
		//Clears the temporary hand so that it can be used again in a loop.
		//Reason I can't have this in the valueReset method is because I use the two in the same loop.
		handTemp.clear();
	}
	//Checks if someone has won.
	private static void win() {
		//If someone has 0 cards that means that someone has won.
		if (hand.size() == 0 || aiHand.size() == 0) {
			//winMsg = win message.
			String winMsg;
			//Checks who's won.
			if (turn == true) {
				winMsg = "\nYou win.";
			} else {
				winMsg = "\nDefeat.";
			}
			System.out.println(winMsg);
			//Gives option to either restart the Application (resetting the game and continuing the loop) or quitting and then wait's for player input.
			System.out.println("\nRestart? y/n");
			scan.nextLine();
			if (scan.nextLine().equals("y")) {
				intililize();
			} else {
				System.out.print("Bye!");
				System.exit(0);
			}
		}
	}
	//In case you actually want to play the game for some odd reason.
	private static void manualPlay() {
		/*
		 * Ha ha! You see, I could explain what this code does, but It's getting late and it's just so much code, I really-
		 * can't be bothered, go figure it out yourself, you're a smart person and I believe in you.
		 */
		System.out.println("Your turn");
		valueReset();
		while(dValue != (int)(cCard/10) && dSuit != cCard - (int)(cCard/10) * 10 && addedC < 3 && cOption != 3) {
			dumpT = dump.get(dump.size()-(1));
			dValue = (int) dumpT/10;
			dSuit = dumpT - dValue*10;
			System.out.println("\n	Discard pile: " + dumpT);
			System.out.println(1 + ": Add card");
			System.out.println(2 + ": Ai's hand (debugging tool)");
			System.out.println(3 + ": Autoplay (debugging tool)");
			for(int k: hand) {
				System.out.println(hand.indexOf(k)+4 + ": " + k);
			}
			System.out.println(hand.size()+(4) + ": Quit \n");
			cOption = scan.nextInt();
			if (cOption == hand.size()+(4)) {
				System.out.println("Bye!");
				System.exit(0);
			}
			if (cOption == 2) {
				System.out.print("AI's hand: " + aiHand.toString());
			}
			if (cOption == 1) {
				int randI = rand.nextInt(talong.size()-(1));
				hand.add(talong.get(randI));
				talong.remove(randI);
				addedC++;
			}
			if (cOption > 3 && cOption < hand.size()+4) {
				cCard = hand.get(cOption-4);
				cCardSuit = cCard - (int) (cCard/10)*10;
				cCardValue = (int)(cCard/10);
			}
			if ((int) cCard/10 == (int)dumpT/10 || cCardSuit == dSuit) {
				dump.add(cCard);
				hand.remove(cOption-4);
				for (int k=0; k < hand.size(); k++) {
					if (cCardValue == (int)(hand.get(k)/10)) {
						System.out.print("Do you also want to play: " + hand.get(k) + "?  y/n : ");
						scan.nextLine();
						if (scan.nextLine().equals("y")) {
							dump.add(hand.get(k));
							System.out.println(hand.get(k));
							hand.remove(k);
						}
					}
				}
			}
			if ((int) cCard/10 != (int)dumpT/10 && cCardSuit != dSuit && cOption > 3 && cOption < hand.size()+4) {
				System.out.println("The card chosen either didn't match the suit or the value. Try again. \n");
			}
		}
	}
	//This is the main method, the place where magic happens.
	public static void main(String[] args) {
		//Preperation.
		intililize();
		//Information.
		System.out.println("Match either the suit or the value of the card in the discard pile.");
		//Actually playing.
		while(true) {
			if (autoPlay == false) {
				manualPlay();
				/*
				 * Checks if autoplay is on and then skips one iteration of the loop in order to avoid a bug-
				 * where when you restart the game autoplay is on for one turn before returning the player to normal manual play.
				 */
				if (cOption == 3) {
					autoPlay = true;
					continue;
				}
			} else {
				ai();
			}
			//win checks if someone has won and is also one of two ways to quit the application, the other one being the quit option in manualplay.
			win();
			turn = false;
			
			//AI's turn.
			ai();
			win();
			turn = true;
			//AI's turn ends right about here.
		}
	}
}
