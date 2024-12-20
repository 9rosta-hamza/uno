package tryuno;

public class Card2 {
    public enum Color { RED, BLUE, GREEN, YELLOW }
    public enum Type { NUMBER, SKIP, REVERSE, DRAW_TWO }

    private final Color color;
    private final Type type;
    private final int value; // Holds the number for NUMBER type cards, or -1 for special cards

    // Constructor for a numbered card
    public Card2(Color color, Type type, int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    // Constructor for special cards (SKIP, REVERSE, DRAW_TWO)
    public Card2(Color color, Type type) {
     this(color,type,-1);
    }

    public Color getColor() { return color; }
    public Type getType() { return type; }
    public int getValue() { return value; }
    public boolean matches(Card2 otherCard) {
    	if(this.getValue()!=-1) {
    		
    		return this.color == otherCard.color || this.value == otherCard.value;
    	}else {
    		return this.color == otherCard.color || this.type == otherCard.type;
    	}
    }

    @Override
    public String toString() {
        if (type == Type.NUMBER) {
            return color + " " + value; // For numbered cards, show the color and value
        } else {
            return color + " " + type; // For special cards, show color and type
        }
    }
    public static void main(String[] args) {
		Card2 card1=new Card2(Color.BLUE,Type.DRAW_TWO);
		System.out.println(card1);
	}

}

