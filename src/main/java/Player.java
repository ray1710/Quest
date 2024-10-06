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
        System.out.println("Player " +this.playerNumber+ " Deck");
        System.out.print("{");
        for(int i=0;i<deck.size();i++)
        {
            if(i==deck.size()-1)
            {
                System.out.print(deck.get(i).name);
            }
            else
            {
                System.out.print(deck.get(i).name+", ");
            }
        }
        System.out.print("}");


    }


}
