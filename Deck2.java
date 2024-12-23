package tryuno;

//import java.util.Iterator;
import java.util.Random;

public class Deck2 {
    private Card2[] cards;
    private int top; // Pointer to the next card to draw

    public Deck2() {
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        cards = new Card2[100]; // 100 cards without wild cards
        int index = 0;

        // Manually define valid colors (RED, BLUE, GREEN, YELLOW)
        Card2.Color[] validColors = { Card2.Color.RED, Card2.Color.BLUE, Card2.Color.GREEN, Card2.Color.YELLOW };

        // Add cards for each valid color
        Card2.Color color;
        for (int x = 0; x < validColors.length; x++) {
        	color=validColors[x];
        	
            // Add one "0" card
            cards[index] = new Card2(color, Card2.Type.NUMBER, 0);
            index=index+1;

            // Add two copies of "1-9" cards
            for (int i = 1; i <= 9; i++) {
                cards[index] = new Card2(color, Card2.Type.NUMBER, i);
                index=index+1;
                cards[index] = new Card2(color, Card2.Type.NUMBER, i);
                index=index+1;
            }

            // Add two SKIP, REVERSE, and DRAW_TWO cards per color
            for (int j = 0; j < 2; j++) {
                cards[index] = new Card2(color, Card2.Type.SKIP);
                index=index+1;
                cards[index] = new Card2(color, Card2.Type.REVERSE);
                index=index+1;
                cards[index] = new Card2(color, Card2.Type.DRAW_TWO);
                index=index+1;
            }
        }

        top = 0; // Initialize the deck pointer
    }

    // Shuffle the deck using random swaps
   private void shuffle() {
        
        	Random rand = new Random();
			
        	for (int i = cards.length-1; i >= 0; i--) {
        		int j = rand.nextInt(i + 1);
        		Card2 temp = cards[i];
        		cards[i] = cards[j];
        		cards[j] = temp;
        	}
		
  }
   

    public Card2 drawCard() {
        if (top < cards.length) {
            Card2 drawnCard = cards[top];
            top=top+1; // Increment after returning the card
            return drawnCard;
        }
        System.out.println("the deckcards is empty");
        return null; // Return null if the deck is empty
    }
    public void showdeck() {
    	for (int j = 0; j < cards.length; j++) {
    		System.out.println("the"+j+"card is "+this.cards[j]);
			
		}
    }
    public static void main(String[] args) {
		Deck2 firstdeck=new Deck2();
		firstdeck.showdeck();
	}

}
