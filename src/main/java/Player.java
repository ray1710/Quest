import java.util.ArrayList;

public class Player {
    int playerNumber;
    ArrayList<Card> deck;
    int shields;

    public Player(int num)
    {
        deck=new ArrayList<>();
        shields=0;
        playerNumber=num;
    }

    public void addCard(Card card)
    {
        deck.add(card);

    }

}
