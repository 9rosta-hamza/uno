package tryuno;

public class Player2 {
    private final String name;
    private Card2[] hand = new Card2[30]; // Fixed-size hand of 7 cards
    private int cardCount=0 ; // Track number of cards in hand

    public Player2(String name, Deck2 deck) {
        this.name = name;
        for (int i = 0; i < 7; i++) {
            hand[i] = deck.drawCard(); // Draw initial 7 cards from the deck
            cardCount++;
        }
    }

    public String getName() { return name; }

    // Check if the player has won (i.e., no cards left)
    public boolean hasWon() { return cardCount == 0; }
    

    // Draw a card from the deck and add it to the player's hand
    public void drawplayerCard(Deck2 deck,boolean desicion) {
        Card2 newcard=deck.drawCard();
        if (newcard != null) {
        	 hand[cardCount] = newcard; // Add new card to hand
             cardCount=cardCount+1;
        } else {
            System.out.println(name + " cannot draw a card because the deck is empty!,the game is end with draw");
            desicion=true;
            
        }
           
        
    }
    public int getcardCount() {
    	return cardCount;
    }
    public Card2 getlastdrawncard(){
    	return hand[cardCount-1];
    }
    

    // Choose a card to play based on matching with the top card
    public Card2 chooseCardToPlay(int w,Card2 topCard) {
        if (w>=0&&w<cardCount) {
        	 if (hand[w] != null && hand[w].matches(topCard)) {
                 Card2 chosen = hand[w];
                 removeCard(w); // Remove the chosen card from hand
                 return chosen;
             }
		} 
           
        
        return null; // No matching card found
    }
public boolean search(Card2 topCard) {
	for (int i = 0; i < cardCount; i++) {
		if (hand[i].matches(topCard)) {
			return true;
		}
	}
	return false;
}
    // Remove a card from hand by shifting the remaining cards
    private void removeCard(int index) {
        for (int i = index; i < cardCount-1; i++) {
            hand[i] = hand[i + 1]; // Shift cards left
        }
        hand[cardCount-1] = null; // Decrease card count and nullify the last card
        cardCount=cardCount-1;
        
    }

    // Display the player's hand
    public void showHand() {
        System.out.print(name + "'s hand: ");
        for (int i = 0; i < cardCount; i++) {
            System.out.print(
            		"  "+(i+1) +""+ "| "+ hand[i] + " | ");
        }
        System.out.println();
    }

}
