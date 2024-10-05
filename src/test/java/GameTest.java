import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {

    Game game;

    @BeforeEach
    public void setGame()
    {
        game=new Game();
    }

    @Test
    public void testAdventureDeck()
    {
        //Check adventureDeck size
        assertEquals(game.adventureDeck.size(),100);

        //Check if each card is in deck
        int i=0;
        //Foe Cards
        //F5
        for (i=0;i<8;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F5");
        }
        //F10
        for(i=8;i<14;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F10");
        }
        //F15
        for(i=15;i<23;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F15");
        }
        //F20
        for(i=23;i<30;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F20");
        }
        //F25
        for(i=30;i<37;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F25");
        }
        //F30
        for(i=37;i<41;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F30");
        }
        //F35
        for(i=41;i<45;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F35");
        }
        //F40
        for(i=45;i<47;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F40");
        }
        //F50
        for(i=47;i<49;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F50");
        }
        //F70
        for(i=49;i<50;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"F70");
        }

        //Weapon Cards
        //D5
        for(i=50;i<56;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"D5");
        }
        //H10
        for(i=56;i<68;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"H10");
        }
        //S10
        for(i=68;i<84;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"S10");
        }
        //B15
        for(i=84;i<92;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"B15");
        }
        //L20
        for(i=92;i<98;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"L20");

        }
        //E30
        for(i=98;i<100;i++)
        {
            assertEquals(game.adventureDeck.get(i).name,"E30");
        }
    }

    @Test
    public void testEventDeck()
    {
        //Check eventDeck size
        assertEquals(game.eventDeck.size(),17);

        int i=0;

        //Q2
        for(i=0;i<3;i++)
        {
            assertEquals(game.eventDeck.get(i).name,"Q2");
        }
        //Q3
        for(i=3;i<7;i++)
        {
            assertEquals(game.eventDeck.get(i).name,"Q3");
        }
        //Q4
        for(i=7;i<10;i++)
        {
            assertEquals(game.eventDeck.get(i).name,"Q4");
        }
        //Q5
        for(i=10;i<12;i++)
        {
            assertEquals(game.eventDeck.get(i).name,"Q5");
        }
        //check for other event cards
        assertEquals(game.eventDeck.get(12).name,"Plague");
        assertEquals(game.eventDeck.get(13).name,"Queen’s favor");
        assertEquals(game.eventDeck.get(14).name,"Queen’s favor");
        assertEquals(game.eventDeck.get(15).name,"Prosperity");
        assertEquals(game.eventDeck.get(16).name,"Prosperity");
    }

    @Test
    public void checkPlayerCards()
    {
        //Give out cards;
        game.distributeCards();
        //Check size of Player Decks and Adventure
        assertEquals(game.playerOne.deck.size(),12);
        assertEquals(game.playerTwo.deck.size(),12);
        assertEquals(game.playerThree.deck.size(),12);
        assertEquals(game.playerFour.deck.size(),12);
        assertEquals(game.adventureDeck.size(),52);
    }

}
