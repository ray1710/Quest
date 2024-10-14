import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Player {
    int playerNumber;
    ArrayList<Card> deck;
    ArrayList <Card> attackDeck;
    int shields;

    /**
     * Sets up player
     * @param num player number
     */
    public Player(int num)
    {
        deck=new ArrayList<>();
        attackDeck=new ArrayList<>();
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

    public boolean removeCard(int i)
    {
        if((i<0)||(i>deck.size()))
        {
            System.out.print("Invalid Num");
            return false;
        }
        deck.remove(i);
        return true;

    }

    public void buildAttackDeck(Scanner s)
    {
        ArrayList<Integer> indexes = new ArrayList<>();
        System.out.println("Enter Card Position for Attack, Enter Quit when done");
        System.out.println("Deck:");
        displayDeck();

        while(true)
        {
            String input = s.nextLine();
            if(input.equals("Quit"))
            {
                Collections.sort(indexes);
                Collections.reverse(indexes);
                for(int i = 0; i < indexes.size(); i++)
                {
                    deck.remove((int) indexes.get(i));
                }
                displayDeck();
                displayAttackDeck();
                break;
            }
            else
            {
                int index = Integer.parseInt(input);
                if(deck.get(index-1).type.equals("Weapon"))
                {
                    if (checkDuplicateWeapon(deck.get(index-1).name))
                    {
                        System.out.println("No Duplicate Weapons");
                    }
                    else
                    {
                        attackDeck.add(deck.get(index - 1));
                        indexes.add(index - 1);
                        displayAttackDeck();
                    }

                }
                else
                {
                    attackDeck.add(deck.get(index - 1));
                    indexes.add(index - 1);
                    displayAttackDeck();
                }

            }
        }
    }

    public boolean checkDuplicateWeapon(String weapon)
    {
        for(int i=0;i<attackDeck.size();i++)
        {
            if(attackDeck.get(i).name.equals(weapon))
            {
                return true;
            }
        }
        return false;

    }

    public int getAttackTotal()
    {
        int total=0;
        if (attackDeck.size() != 0) {
            for (int i = 0; i < attackDeck.size(); i++) {
                total += attackDeck.get(i).value;
            }
        }
        return total;
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

    public void displayAttackDeck()
    {
        if(attackDeck.size()==0)
        {
            System.out.println("{}");
        }
        else
        {
            System.out.print("{");
            for(int i=0;i<attackDeck.size();i++)
            {
                if(i==attackDeck.size()-1)
                {
                    System.out.print(attackDeck.get(i).name);
                }
                else
                {
                    System.out.print(attackDeck.get(i).name+", ");
                }
            }
            System.out.print("}");
            System.out.println("");

        }


    }


}
