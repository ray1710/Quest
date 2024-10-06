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

    public void displayDeck()
    {
        System.out.println("Deck");
        for(int i=0;i<deck.size();i++)
        {
            System.out.println(deck.get(i).name);
        }

    }


}
