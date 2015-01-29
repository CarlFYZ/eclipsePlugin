package popupmenu;
/**
 * The actual calculation is 
 * (13*12)/2  * 2 * (4 * 6) * 5!  / (52!/(52-5)!) = 0.00144406
 * how may type of a full house (like 11122,11133 ...) * (the other way around i.e 2 ) * (how many different cards * full permutation of 5 of 5 cards
 * / full permutation of 5 of 52 cards  
 * 
 * @author fengyuanz
 *
 */
public class CardGameExperiment 
{
	
	/**
	 * Use first digit to denote 4 suits, spades, hearts, diamonds and clubs
	 * Second and third digits to denote the number
	 */
	static int[] allCards = new int[] {
		101,102,103,104,105,106,107,108,109,110,111,112,113,
		201,202,203,204,205,206,207,208,209,210,211,212,213,
		301,302,303,304,305,306,307,308,309,310,311,312,313,
		401,402,403,404,405,406,407,408,409,410,411,412,413};
	
	
	
	
	public static void main(String[] args)
	{
		int total =20000000;
		int count = 0;
		for (int i = 0;  i< total; i ++ )
		{
			if (oneGameIsFullhouse())
			{
				count ++;
			}
		}
		System.out.println(count + "/" + total );
		System.out.println((double)count / total );
		
	}


	/**
	 * Play one game
	 * @return true if full house, false otherwise
	 */
	protected static boolean oneGameIsFullhouse()
	{
		int a = -1; 
		int b = -1;
		int aCount = 0;
		int bCount = 0;
		
		// Shuffle and get five cards
		shuffle();
		int cards[] = new int[5];
		System.arraycopy(allCards, 0, cards, 0, 5);
		
		//System.out.println(cards[0] + ", " + cards[1] + ", " + cards[2] + ", " + cards[3] + ", " + cards[4]);
		// Only care about two different numbers, if more than 3 it's not a full house
		for (int i = 0; i < 5; i++)
		{
			if (a == -1)
			{
				a = cards[i] % 100;
				aCount++;
			} else if (a == cards[i] % 100)
			{
				aCount++;

			} else if (b == -1)
			{

				b = cards[i] % 100;
				bCount++;

			} else if (b == cards[i] % 100)
			{
				bCount++;
			}

		}
		//System.out.println(aCount + "" + bCount);
		if ((aCount == 3 && bCount == 2 ) || ( aCount == 2 && bCount == 3 ))
		{
			return true;
		}
		return false;
		
	}
	

	


	protected static void shuffle()
	{
		for (int i =0 ; i< 13; i++ )
		{
			int temp = -1;
			int index = (int) Math.floor(Math.random() * 52.0);
			temp = allCards[i];
			allCards[i] = allCards[index];
			allCards[index] = temp;
		}
		
	}
	
	
}
