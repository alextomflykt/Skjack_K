/**
 * 
 */
import java.util.ArrayList;
import java.util.Random;
/**
 * @author alexflykt
 *
 */

public class KortGiveAndTake {
	
	private static int randNum;
	private static Random rand = new Random();
	
	//temp-give gives a random card to temp-take.
	public static void kortDraw(ArrayList<Integer> tempGive, ArrayList<Integer> tempTake) {	
		randNum = rand.nextInt(tempGive.size());
		tempTake.add(tempGive.get(randNum));
		tempGive.remove(randNum);
		
	}
}
