import java.util.ArrayList;

public class Player {
    int playerNumber;
    ArrayList<Card> deck;
    int shields;

    /**
     * Sets up player
     * @param num player number
     */
    public Player(int num)
    {
        deck=new ArrayList<>();
        shields=0;
        playerNumber=num;
    }

    /**
     * Adds card to players deck
     * @param card the card that will be added
     */
    public void addCard(Card card)
    {
        deck.add(card);
    }

    boolean removeCard(int i)
    {
        if((i<0)||(i>deck.size()))
        {
            System.out.print("Invalid Num");
            return false;
        }
        deck.remove(i);
        return true;

    }


    /**
     * Displays deck
     */
    public void displayDeck()
    {
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
        System.out.println("");


    }


}
