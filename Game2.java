package tryuno;



import java.util.Scanner;

public class Game2 {
    private final Deck2 deck; // The deck of cards
    private final Player2[] players; // Array of players
    private Card2 topCard; // The top card of the discard pile
    private int currentPlayerIndex; // The current player's index
    private boolean direction; // True for clockwise, false for counter-clockwise
    private boolean decision;//the decision id there is a winner(true) or not (false)  
    private Scanner scanner;
    private Card2 playedCard ;


    // Constructor to initialize the game
    public Game2() {
        deck = new Deck2(); // Initialize the deck
        scanner = new Scanner(System.in);
        decision=false;

        // Initialize players (max of 4 players)
      
        int numPlayers;
        String playername;
        System.out.print("Enter the number of players (2-4): ");
        
        numPlayers= scanner.nextInt();
        scanner.nextLine();
        //scanner.nextLine();
        
        while (numPlayers>4||numPlayers<2) {
        	System.out.print("Enter a number of player between 2-4 ");
        	numPlayers= scanner.nextInt();
        	scanner.nextLine();
		}
		
        players = new Player2[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter Player " + (i + 1) + "'s name: ");
            playername=scanner.next();
            scanner.nextLine();
            players[i] = new Player2(playername, deck);
        }

        // Initialize the game
        currentPlayerIndex = 0;
        direction = true; // Default is clockwise
        do {
            topCard = deck.drawCard();
        } while (topCard == null || topCard.getType() != Card2.Type.NUMBER); // Ensure the starting card is a NUMBER // Draw the first card from the deck
		
		
    }

    // Method to start the game
    public void startGame() {
        while (!decision) {
            // Current player takes their turn
            Player2 currentPlayer = players[currentPlayerIndex];

            System.out.println("\nTop card: " + topCard);
            currentPlayer.showHand(); // Show current player's hand
           if ( currentPlayer.search(topCard)==true) {
			
		

            // Player selects a card to play or draws a card if they can't play
            System.out.println("selec the the card you want to play 1 2 3..");
            
            
            int cardselected=scanner.nextInt();
            scanner.nextLine();
            
            
            while ((cardselected<=0||cardselected>currentPlayer.getcardCount())) {
            	System.out.println("selec the the card you want to play 1 2 3.. ");
            	cardselected=scanner.nextInt();
            	scanner.nextLine();
       			}
            
             playedCard = currentPlayer.chooseCardToPlay((cardselected-1),topCard);
             while (playedCard==null) {
             	System.out.println("selec the that you can play it");
             	cardselected=scanner.nextInt();
             	scanner.nextLine();
             	playedCard = currentPlayer.chooseCardToPlay((cardselected-1),topCard);
        			}
           }else {
        	   playedCard=null;
           }
            

            if (playedCard != null) {
                topCard = playedCard; // Update top card with the played card
                System.out.println(currentPlayer.getName() + " played: " + playedCard);

                // Check if the player won
                if (currentPlayer.hasWon()) {
                    System.out.println(currentPlayer.getName() + " has won the game!");
                    decision=true;
                    break;
                }

                // Handle special cards like SKIP, REVERSE, etc.
                if (playedCard.getType() == Card2.Type.REVERSE) {
                    direction = !direction; // Reverse the turn order
                }
                if (playedCard.getType() == Card2.Type.SKIP) {
                    // Skip next player's turn
                    currentPlayerIndex = nextPlayerIndex();
                }
                if (playedCard.getType() == Card2.Type.DRAW_TWO) {
                    // Skip next player's turn and make them draw two cards
                    currentPlayerIndex = nextPlayerIndex();
                    players[currentPlayerIndex].drawplayerCard(deck,decision);
                    players[currentPlayerIndex].drawplayerCard(deck,decision);
                }
            } else {
                // If no valid card, draw a card
                System.out.println(currentPlayer.getName() + " draws a card.");
                currentPlayer.drawplayerCard(deck,decision);
                if(decision==true) {
                	break;
                }
                Card2 drawnCard = currentPlayer.chooseCardToPlay((currentPlayer.getcardCount())-1,topCard);
                if(drawnCard != null && drawnCard.matches(topCard)) {
                	 topCard = drawnCard; // Update top card with the played card
                     System.out.println(currentPlayer.getName() + " played:the drawn card " + drawnCard);


                     // Handle special cards like SKIP, REVERSE, etc.
                     if (drawnCard.getType() == Card2.Type.REVERSE) {
                         direction = !direction; // Reverse the turn order
                     }
                     if (drawnCard.getType() == Card2.Type.SKIP) {
                         // Skip next player's turn
                         currentPlayerIndex = nextPlayerIndex();
                     }
                     if (drawnCard.getType() == Card2.Type.DRAW_TWO) {
                         // Skip next player's turn and make them draw two cards
                         currentPlayerIndex = nextPlayerIndex();
                         players[currentPlayerIndex].drawplayerCard(deck,decision);
                         players[currentPlayerIndex].drawplayerCard(deck,decision);
                     }
                }
            }

            // Move to the next player
            currentPlayerIndex = nextPlayerIndex();
        }
    }

    // Method to get the next player index based on direction
    private int nextPlayerIndex() {
        if (direction) {
            // Clockwise
            return (currentPlayerIndex + 1) % players.length;
        } else {
        	
        		return (currentPlayerIndex - 1 + players.length) % players.length;	
        	
        }
    }

    public static void main(String[] args) {
        Game2 game = new Game2();
        game.startGame();
    }
}
