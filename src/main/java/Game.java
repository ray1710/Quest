import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Game {

    ArrayList<Card> adventureDeck;
    ArrayList<Card> eventDeck;
    Player playerOne;
    Player playerTwo;
    Player playerThree;
    Player playerFour;
    Player currentPlayer;
    Card currentEventCard;
    InputStream sysInBackup = System.in; // backup System.in to restore it later




    /**
     * Sets up game, fills up decks with cards and creates players
     */
    public Game()
    {
        adventureDeck=new ArrayList<>();
        eventDeck=new ArrayList<>();

        playerOne=new Player(1);
        playerTwo=new Player(2);
        playerThree=new Player(3);
        playerFour=new Player(4);

        //Fill up ArrayLists with card objects
        //adventureDeck
        int i;
        //F5
        for (i=0;i<8;i++)
        {
            adventureDeck.add(new Card("F5","Foe",5));
        }
        //F10
        for(i=8;i<15;i++)
        {
            adventureDeck.add(new Card("F10","Foe",10));
        }
        //F15
        for(i=15;i<23;i++)
        {
            adventureDeck.add(new Card("F15","Foe",15));
        }
        //F20
        for(i=23;i<30;i++)
        {
            adventureDeck.add(new Card("F20","Foe",20));
        }
        //F25
        for(i=30;i<37;i++)
        {
            adventureDeck.add(new Card("F25","Foe",25));
        }
        //F30
        for(i=37;i<41;i++)
        {
            adventureDeck.add(new Card("F30","Foe",30));
        }
        //F35
        for(i=41;i<45;i++)
        {
            adventureDeck.add(new Card("F35","Foe",35));
        }
        //F40
        for(i=45;i<47;i++)
        {
            adventureDeck.add(new Card("F40","Foe",40));
        }
        //F50
        for(i=47;i<49;i++)
        {
            adventureDeck.add(new Card("F50","Foe",50));
        }
        //F70
        for(i=49;i<50;i++)
        {
            adventureDeck.add(new Card("F70","Foe",70));
        }
        //Weapon Cards
        //D5
        for(i=50;i<56;i++)
        {
            adventureDeck.add(new Card("D5","Weapon",5));
        }
        //H10
        for(i=56;i<68;i++)
        {
            adventureDeck.add(new Card("H10","Weapon",10));
        }
        //S10
        for(i=68;i<84;i++)
        {
            adventureDeck.add(new Card("S10","Weapon",10));
        }
        //B15
        for(i=84;i<92;i++)
        {
            adventureDeck.add(new Card("B15","Weapon",15));
        }
        //L20
        for(i=92;i<98;i++)
        {
            adventureDeck.add(new Card("L20","Weapon",20));
        }
        //E30
        for(i=98;i<100;i++)
        {
            adventureDeck.add(new Card("E30","Weapon",30));
        }

        //EventDeck
        //Q2
        for(i=0;i<3;i++)
        {
            eventDeck.add(new Card("Q2","Event",2));
        }
        //Q3
        for(i=3;i<7;i++)
        {
            eventDeck.add(new Card("Q3","Event",3));
        }
        //Q4
        for(i=7;i<10;i++)
        {
            eventDeck.add(new Card("Q4","Event",4));
        }
        //Q5
        for(i=10;i<12;i++)
        {
            eventDeck.add(new Card("Q5","Event",5));
        }
        eventDeck.add(new Card("Plague","Event",0));
        eventDeck.add(new Card("Queen’s favor","Event",0));
        eventDeck.add(new Card("Queen’s favor","Event",0));
        eventDeck.add(new Card("Prosperity","Event",0));
        eventDeck.add(new Card("Prosperity","Event",0));


    }

    /**
     * Gives cards to players
     */
    public void distributeCards()
    {
        Random r = new Random();
        //use random int to give out cards
        int randomInt;
        for(int i=0;i<12;i++)
        {
            randomInt = r.nextInt(adventureDeck.size());
            playerOne.addCard(adventureDeck.get(randomInt));
            adventureDeck.remove(randomInt);
            randomInt = r.nextInt(adventureDeck.size());
            playerTwo.addCard(adventureDeck.get(randomInt));
            adventureDeck.remove(randomInt);
            randomInt = r.nextInt(adventureDeck.size());
            playerThree.addCard(adventureDeck.get(randomInt));
            adventureDeck.remove(randomInt);
            randomInt = r.nextInt(adventureDeck.size());
            playerFour.addCard(adventureDeck.get(randomInt));
            adventureDeck.remove(randomInt);
        }


    }

    /**
     * Adds cards to a players deck
     * @param num number of cards
     * @param player player who is receiving cards
     */
    public void addAdventureCard(int num,Player player)
    {
        for(int i=0;i<num;i++)
        {
            Random r = new Random();
            //use random int to give out cards
            int randomInt=r.nextInt(adventureDeck.size());
            player.addCard(adventureDeck.get(randomInt));
            adventureDeck.remove(randomInt);
        }
    }

    /**
     * Starts game, which is a loop, and breaks when a player or players have won
     */
    public void startGame()
    {
        while(true)
        {
            currentPlayer=playerOne;
            startPlayerTurn();
            break;
        }
    }

    /**
     * Starts a players turn, makes them draw a event card
     */

    public void startPlayerTurn()
    {
        System.out.println("Player "+currentPlayer.playerNumber+" turn");
        currentPlayer.displayDeck();
        System.out.print("Getting Event Card");
        Random r = new Random();
        int randomInt=r.nextInt(eventDeck.size());
        currentEventCard=eventDeck.get(randomInt);
        eventDeck.remove(randomInt);



    }

    /**
     * Plays an event card
     */
    public void playEventCard()
    {
        if(currentEventCard.name=="Plague")
        {
            if(currentPlayer.shields==1)
            {
                currentPlayer.shields=0;
            }
            else if(currentPlayer.shields>=2)
            {
                currentPlayer.shields= currentPlayer.shields-2;
            }
        }
        else if(currentEventCard.name=="Queen’s favor")
        {
            addAdventureCard(2,currentPlayer);
        }
        else if(currentEventCard.name=="Prosperity")
        {
            addAdventureCard(2,playerOne);
            addAdventureCard(2,playerTwo);
            addAdventureCard(2,playerThree);
            addAdventureCard(2,playerFour);
        }


    }
    public int trimCards(Player player)
    {
        int n=player.deck.size()-12;
        return n;
    }





}
