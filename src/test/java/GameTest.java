import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Game game;

    @BeforeEach
    public void setGame()
    {
        game=new Game();
    }

    /**
     * Tests to check if each adventure card is in the deck
     */
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
    /**
     * Tests to check if each event card is in the deck
     */
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

    /**
     * Checks if each player got the same number of cards
     */
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

    /**
     * Tests that player one will go first
     */
    @Test
    public void testPlayerTurnAndDeck()
    {
        game.distributeCards();

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream(" ".getBytes());
        System.setIn(in);

        game.nextPlayer(1,new Scanner(in));
        System.setIn(sysInBackup);

        assertEquals(game.currentPlayer.playerNumber,1);

    }

    /**
     * Checks if size of EventDeck goes down
     * Also checks if the CurrentEventCard is not null
     */
    @Test
    public void testUpdatedEventDeck()
    {
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n".getBytes());
        System.setIn(in);
        game.SetEventNewCard();



        System.setIn(sysInBackup);
        assertEquals(game.eventDeck.size(),16);
        assertNotNull(game.currentEventCard);
    }


    /**
     * Tests Plague Card
     */
    @Test
    public void testPlagueCard()
    {
        game.currentPlayer=game.playerOne;
        game.currentPlayer.shields=8;
        game.currentEventCard=new Card("Plague","Event",0);
        game.playEventCard();
        assertEquals(game.currentPlayer.shields,6);
    }

    /**
     * Tests Queens Favour Card
     */

    @Test
    public void testQueensFavorsCard()
    {
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        game.currentPlayer.deck.remove(11);
        game.currentPlayer.deck.remove(10);
        game.currentEventCard=new Card("Queen’s favor","Event",0);
        game.playEventCard();
        assertEquals(game.currentPlayer.deck.size(),12);
    }

    /**
     * Tests Prosperity  Card
     */

    @Test
    public void testProsperityCard()
    {
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        game.playerOne.deck.remove(11);
        game.playerOne.deck.remove(10);
        game.playerTwo.deck.remove(11);
        game.playerTwo.deck.remove(10);
        game.playerThree.deck.remove(11);
        game.playerThree.deck.remove(10);
        game.playerFour.deck.remove(11);
        game.playerFour.deck.remove(10);
        game.currentEventCard=new Card("Prosperity","Event",0);
        game.playEventCard();
        assertEquals(game.playerOne.deck.size(),12);
        assertEquals(game.playerTwo.deck.size(),12);
        assertEquals(game.playerThree.deck.size(),12);
        assertEquals(game.playerFour.deck.size(),12);
    }

    /**
     * tests the number of cards to be discarded
     */
    @Test
    public void testTotalNumOfTrim()
    {
        Scanner s =new Scanner(in);
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        //Will make player get 14 cards
        game.currentEventCard=new Card("Queen’s favor","Event",0);
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n".getBytes());
        System.setIn(in);

        game.playEventCard();

        System.setIn(sysInBackup);

        assertEquals(game.currentPlayer.deck.size(),12);
    }

    /**
     *tests if the deck reduces in size;
     */
    @Test
    public void testTrimDeck()
    {
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        //Will make player get 14 cards
        game.currentEventCard=new Card("Queen’s favor","Event",0);
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n".getBytes());
        System.setIn(in);
        game.playEventCard();



        System.setIn(sysInBackup);

        assertEquals(game.currentPlayer.deck.size(),12);
    }

    /**
     * Testing to make sure that after eventCard, each player card is trimmed, without calling trimCards();
     */
    @Test
    public void trimAfterEvent()
    {
        game.distributeCards();
        game.currentPlayer=game.playerOne;
        //Everyone will pick up 2 cards
        game.currentEventCard=new Card("Prosperity","Event",0);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n3\n5\n1\n1\n3\n5\n1\n".getBytes());
        System.setIn(in);

        game.playEventCard();
        System.setIn(sysInBackup);

        assertEquals(game.playerOne.deck.size(),12);
        assertEquals(game.playerTwo.deck.size(),12);
        assertEquals(game.playerThree.deck.size(),12);
        assertEquals(game.playerFour.deck.size(),12);
    }

    /**
     * Checks if the currentPlayer is changes from one to two
     */
    @Test
    public void testEndOfPlayersTurn()
    {
        game.distributeCards();

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream(" ".getBytes());
        System.setIn(in);

        game.nextPlayer(2,new Scanner(in));
        System.setIn(sysInBackup);

        assertEquals(game.currentPlayer.playerNumber,2);
    }

    /**
     * Checks if players shields are above 7, and make them a winner
     */
    @Test
    public void checkIfWinners()
    {
        game.playerOne.shields=5;
        game.playerTwo.shields=6;
        game.playerThree.shields=9;
        game.playerFour.shields=7;

        //Player 3 and 4 should win

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        try {
            game.checkWinner();
            String capturedOutput = outputStream.toString();
            String expectedOutput = "Player 3, Player 4, has won";
            System.out.print(expectedOutput);
            assertTrue(capturedOutput.contains(expectedOutput));

        } finally {
            System.setOut(originalOut);
        }

    }

    /**
     * Tests if the right sponsor is chosen
     */
    @Test
    public void testIfOfferedSponsor()
    {
        game.currentPlayer=game.playerOne;
        game.currentEventCard=new Card("Q4","Event",4);


        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("N\nN\nN\nY\n".getBytes());
        System.setIn(in);

        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);

        assertEquals(game.sponsor,game.playerFour);
        assertEquals(game.eligiblePlayers.size(),3);
        assertEquals(game.eligiblePlayers.get(0),game.playerOne);
        assertEquals(game.eligiblePlayers.get(1),game.playerTwo);
        assertEquals(game.eligiblePlayers.get(2),game.playerThree);

        //Check if sponsor is null if everyone says N
        game.sponsor=null;
        game.eligiblePlayers.clear();

        sysInBackup = in;
        in = new ByteArrayInputStream("N\nN\nN\nN\n".getBytes());
        System.setIn(in);

        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);

        assertEquals(game.sponsor,null);
        assertEquals(game.eligiblePlayers.size(),0);
    }


    /**
     * Test if the stage created passes all the requirements
     */
    @Test
    public void testStageBuild()
    {
        //make player 1 the sponsor
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Add cards to sponsor deck
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("F15","Foe",15));
        game.sponsor.addCard(new Card("D5","Weapon",5));
        game.sponsor.addCard(new Card("H10","Weapon",10));


        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n3\nQuit\n1\n2\nQuit\n".getBytes());
        System.setIn(in);

        game.SetStages(new Scanner(in));
        System.setIn(sysInBackup);

        //Check if playerDeck is empty, since we use all cards
        assertEquals(game.sponsor.deck.size(),0);
        //Checks if we have two cards in the first and second stage
        assertEquals(game.stage.get(0).size(),2);
        assertEquals(game.stage.get(1).size(),2);

        //Check if the right cards are in the right stages
        assertEquals(game.stage.get(0).get(0).name,"F5");
        assertEquals(game.stage.get(0).get(1).name,"D5");
        assertEquals(game.stage.get(1).get(0).name,"F15");
        assertEquals(game.stage.get(1).get(1).name,"H10");

    }

    /**
     * Checks if there is another foe card in the stage (RESP 11)
     */
    @Test
    public void testSoleFoeStage()
    {
        //make player 1 the sponsor
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Give out cards
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("F10","Foe",10));
        game.sponsor.addCard(new Card("F15","Foe",15));
        game.sponsor.addCard(new Card("D5","Weapon",5));
        game.sponsor.addCard(new Card("H10","Weapon",10));


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n4\nQuit\n1\n3\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.SetStages(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Only allowed one foe card";
            String expectedOutput2 = "{{F5, D5}{F10, H10}}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));

        } finally {
            System.setOut(originalOut);
        }
    }
    /**
     * Checks for repeated weapons (RESP-12)
     */
    @Test
    public void testRepeatedWeapon()
    {
        //make player 1 the sponsor
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Give out cards
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("F10","Foe",10));
        game.sponsor.addCard(new Card("F15","Foe",15));
        game.sponsor.addCard(new Card("D5","Weapon",5));
        game.sponsor.addCard(new Card("D5","Weapon",5));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n4\n1\nQuit\n1\n3\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.SetStages(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "No Repeated Weapon Cards";
            String expectedOutput2 = "{{D5, F5}{F10, D5}}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));

        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    public void testEmptyStage()
    {
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Give out cards
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("F10","Foe",10));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\n1\nQuit\n1\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.SetStages(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "No Empty Stage";
            String expectedOutput2 = "{{F5}{F10}}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));

        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    public void testGreaterThenPreviousValue()
    {
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Give out cards
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("H10","Weapon",10));
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("D5","Weapon",5));
        game.sponsor.addCard(new Card("L20","Weapon",20));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\nQuit\n1\n2\nQuit\n1\n3\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.SetStages(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Insufficient value for this stage";
            String expectedOutput2 = "{{F5, H10}{F5, L20}}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void checkFoeCardOnStage()
    {
        game.sponsor=game.playerOne;

        //Make the event card a Q2 card
        game.currentEventCard=new Card("Q2","Event",2);

        //Give out cards
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("D5","Weapon",5));
        game.sponsor.addCard(new Card("F5","Foe",5));
        game.sponsor.addCard(new Card("D10","Weapon",10));


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("2\nQuit\n1\nQuit\n1\n2\nQuit\n1\n2\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.SetStages(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Must have Foe Card on Stage";
            String expectedOutput2 = "{{D5, F5}{F5, D10}}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));
        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    public void testEligiblePlayers() {
        //Sets up the event card
        game.currentEventCard = new Card("Q2", "Event", 2);

        //Set the sponsor
        InputStream sysInBackup = in;
        ByteArrayInputStream input = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(input);


        game.SetSponsor(new Scanner(input));
        System.setIn(sysInBackup);

        //Set the stage
        game.stage.add(new ArrayList<>());
        game.stage.get(0).add(new Card("F5", "Foe", 5));
        game.stage.get(0).add(new Card("D5", "Weapon", 5));

        //Players deck must add up to at least 10 in round 1;
        game.playerTwo.addCard(new Card("F10", "Foe", 10));
        game.playerThree.addCard(new Card("F10", "Foe", 10));
        game.playerFour.addCard(new Card("F5", "Foe", 5));


        //Call function to test eligible players
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);
        try {
            game.SetEligiblePlayers(0);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Player 2, Player 3 are eligible participants";
            assertTrue(capturedOutput.contains(expectedOutput1));
        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    public void askIfWantToParticipate()
    {
        //Set the event card
        game.currentEventCard = new Card("Q2", "Event", 2);

        //Give out cards
        game.distributeCards();


        //Set the sponsor
        InputStream sysInBackup = in;
        ByteArrayInputStream input = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(input);


        game.SetSponsor(new Scanner(input));
        System.setIn(sysInBackup);

        //Create rounds
        game.stage.add(new ArrayList<>());
        game.stage.get(0).add(new Card("F5", "Foe", 5));
        game.stage.get(0).add(new Card("D5", "Weapon", 5));

        game.stage.add(new ArrayList<>());
        game.stage.get(1).add(new Card("F5", "Foe", 5));
        game.stage.get(0).add(new Card("H5", "Weapon", 10));

        //All Players should be eligible
        game.SetEligiblePlayers(1);

        //Ask players if they want to participate
        sysInBackup = in;
        ByteArrayInputStream inputtwo = new ByteArrayInputStream("Y\n1\nN\nY\n1\n".getBytes());
        System.setIn(inputtwo);

        game.askForParticipation(new Scanner(inputtwo));
        System.setIn(sysInBackup);

        assertEquals(game.eligiblePlayers.size(),2);

        //Should not change
        game.SetEligiblePlayers(1);

        assertEquals(game.eligiblePlayers.size(),2);
    }

    @Test
    public void testDrawCardAfterEligible()
    {
        game.distributeCards();

        //Set Sponsor
        InputStream sysInBackup = in;
        ByteArrayInputStream input = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(input);
        game.SetSponsor(new Scanner(input));
        System.setIn(sysInBackup);

        //Remove last card in each non sponsor
        game.playerTwo.removeCard(11);
        game.playerThree.removeCard(11);
        game.playerFour.removeCard(11);

        //
        sysInBackup = in;
        input = new ByteArrayInputStream("Y\nY\nY\n".getBytes());
        System.setIn(input);
        game.askForParticipation(new Scanner(input));
        System.setIn(sysInBackup);

        assertEquals(game.playerTwo.deck.size(),12);
        assertEquals(game.playerThree.deck.size(),12);
        assertEquals(game.playerFour.deck.size(),12);
    }

    @Test
    public void testDrawCardAfterEligibleTrim()
    {
        game.distributeCards();

        //Set Sponsor
        InputStream sysInBackup = in;
        ByteArrayInputStream input = new ByteArrayInputStream("Y\n".getBytes());
        System.setIn(input);
        game.SetSponsor(new Scanner(input));
        System.setIn(sysInBackup);

        //Remove last card in each non sponsor
        game.playerTwo.removeCard(11);
        game.playerThree.removeCard(11);

        //For player 4, we can test the trim

        //
        sysInBackup = in;
        input = new ByteArrayInputStream("Y\nY\nY\n1\n".getBytes());
        System.setIn(input);
        game.askForParticipation(new Scanner(input));
        System.setIn(sysInBackup);

        assertEquals(game.playerTwo.deck.size(),12);
        assertEquals(game.playerThree.deck.size(),12);
        assertEquals(game.playerFour.deck.size(),12);
    }

    @Test
    public void testNoParticipants() {
        game.distributeCards();

        game.currentEventCard = new Card("Q2", "Event", 2);

        //Assign Sponsor, and then eligiblePlayers

        game.sponsor = game.playerOne;

        game.stage.add(new ArrayList<>());

        game.stage.get(0).add(new Card("TEST", "TEST", 0));
        game.stage.get(0).add(new Card("TEST", "TEST", 0));


        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerThree);
        game.eligiblePlayers.add(game.playerFour);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("N\nN\nN\n1\n1\n1\n1\n1\n".getBytes());
        System.setIn(in);

        try {
            game.ResolveQuest(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Quest Resolved, No Players";
            assertTrue(capturedOutput.contains(expectedOutput1));
        } finally {
            System.setOut(originalOut);
        }


    }

    @Test
    public void testDisplayAttackInfo()
    {
        game.distributeCards();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\nQuit\n".getBytes());
        System.setIn(in);

        try {
            game.playerOne.buildAttackDeck(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Enter Card Position for Attack, Enter Quit when done";
            String expectedOutput2="Deck:";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testSelectAttackCard()
    {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\nQuit\n".getBytes());
        System.setIn(in);

        game.playerOne.deck.add(new Card("TEST","TEST",0));

        try {
            game.playerOne.buildAttackDeck(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "{TEST}";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertEquals(game.playerOne.deck.size(),0);
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void testRepeatedWeaponAttack()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n3\nQuit\n".getBytes());
        System.setIn(in);

        game.playerOne.deck.add(new Card("H10","Weapon",10));
        game.playerOne.deck.add(new Card("H10","Weapon",10));
        game.playerOne.deck.add(new Card("F10","Foe",10));

        try {
            game.playerOne.buildAttackDeck(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "{H10, F10}";
            String expectedOutput2 = "No Duplicate Weapons";
            assertTrue(capturedOutput.contains(expectedOutput1));
            assertTrue(capturedOutput.contains(expectedOutput2));
        } finally {
            System.setOut(originalOut);
        }

    }

    @Test
    public void testSponsorDraw()
    {
        game.distributeCards();

        game.currentEventCard = new Card("Q2", "Event", 2);

        //Assign Sponsor, and then eligiblePlayers

        game.sponsor = game.playerOne;

        game.stage.add(new ArrayList<>());


        //Should get 6 extra cards
        game.stage.get(0).add(new Card("TEST", "TEST", 5));
        game.stage.get(0).add(new Card("TEST", "TEST", 5));

        game.stage.add(new ArrayList<>());

        game.stage.get(1).add(new Card("TEST", "TEST", 5));
        game.stage.get(1).add(new Card("TEST", "TEST", 5));


        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerThree);
        game.eligiblePlayers.add(game.playerFour);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("N\nN\nN\n1\n1\n1\n1\n1\n1\n".getBytes());
        System.setIn(in);

        try {
            game.ResolveQuest(new Scanner(in));
            System.setIn(sysInBackup);
            String capturedOutput = outputStream.toString();
            String expectedOutput1 = "Gained 6 extra cards";
            assertEquals(game.adventureDeck.size(),46);
            assertTrue(capturedOutput.contains(expectedOutput1));
        } finally {
            System.setOut(originalOut);
        }


    }

    @Test
    public void testResolveAttack()
    {
        //For simplicity sake, we will have one round only
        game.currentEventCard = new Card("Q2", "Event", 1);

        //Distribute Cards so that each player is eligible no matter what

        game.distributeCards();

        //Assign Sponsor, and then eligiblePlayers

        game.sponsor = game.playerOne;

        game.stage.add(new ArrayList<>());


        game.stage.get(0).add(new Card("F5", "Foe", 5));
        game.stage.get(0).add(new Card("D5", "Weapon", 5));


        //Set EligiblePlayers
        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerThree);
        game.eligiblePlayers.add(game.playerFour);

        //Player two should pass since 15>10
        game.playerTwo.attackDeck.add(new Card("F10","Foe",10));
        game.playerTwo.attackDeck.add(new Card("D5","Weapon",5));

        //Player three should pass since 10=10
        game.playerThree.attackDeck.add(new Card("F10","Foe",10));

        //Player four should fail since 0<10
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n1\nY\n1\nY\n1\n1\n1\n1\n".getBytes());
        System.setIn(in);

        game.ResolveQuest(new Scanner(in));

        //Player four should not be eligible
        assertEquals(game.eligiblePlayers.get(0),game.playerTwo);
        assertEquals(game.eligiblePlayers.get(1),game.playerThree);

        //All attack decks should be empty
        assertEquals(game.playerTwo.attackDeck.size(),0);
        assertEquals(game.playerThree.attackDeck.size(),0);
        assertEquals(game.playerFour.attackDeck.size(),0);
    }

    @Test
    public void endOfQuest()
    {
        //For simplicity sake, we will have one round only
        game.currentEventCard = new Card("Q2", "Event", 1);

        //Distribute Cards so that each player is eligible no matter what

        game.distributeCards();

        //Assign Sponsor, and then eligiblePlayers

        game.sponsor = game.playerOne;

        game.stage.add(new ArrayList<>());


        //Make it so that anyone can beat this stage
        game.stage.get(0).add(new Card("F5", "Foe", 5));


        //Set EligiblePlayers
        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerThree);
        game.eligiblePlayers.add(game.playerFour);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n1\nY\n1\nY\n1\n1\nQuit\n1\nQuit\n1\nQuit\n".getBytes());
        System.setIn(in);

        game.ResolveQuest(new Scanner(in));
        System.setIn(sysInBackup);

        //Check shields, should be 1 instead of 0
        assertEquals(game.playerTwo.shields,1);
        assertEquals(game.playerThree.shields,1);
        assertEquals(game.playerFour.shields,1);
    }


}



