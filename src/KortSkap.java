import java.util.ArrayList;
import java.util.Random;

public class KortSkap {
	private static KortGiveAndTake kGAT = new KortGiveAndTake();
	private static Random rand = new Random();
	//tempGive gives, tempTake takes.
	public static ArrayList<Integer> talong = new ArrayList<Integer>();
	public static ArrayList<Integer> aiHand= new ArrayList<Integer>();
	public static ArrayList<Integer> hand = new ArrayList<Integer>();
	private static int Size = 6;
	

	
	public static void kortSkap() {
		int value, suit;
		value = 0;
		suit = 1;
		//This loop creates a standard deck of 52 cards.
		for(int k=0; k<52; k++) {
			value++;
			talong.add(value*10+suit);
			if (value == 13) {
				value=0;
				suit+=1;
			}
			
		}

		for(int kort = 0; kort < Size; kort++) {
			KortGiveAndTake.kortDraw(talong, hand);
			KortGiveAndTake.kortDraw(talong, aiHand);
		}
		
	}	

}


