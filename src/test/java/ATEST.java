import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.in;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATEST {

    @Test
    public void JP()
    {
        //Create Game
        Game game=new Game();

        //Give out cards
        game.distributeCards();

        //Rig hands
        //P1
        game.playerOne.deck.set(0,new Card("F5","Foe",5));
        game.playerOne.deck.set(1,new Card("F5","Foe",5));
        game.playerOne.deck.set(2,new Card("F15","Foe",15));
        game.playerOne.deck.set(3,new Card("F15","Foe",15));
        game.playerOne.deck.set(4,new Card("D5","Weapon",5));
        game.playerOne.deck.set(5,new Card("S10","Weapon",10));
        game.playerOne.deck.set(6,new Card("S10","Weapon",10));
        game.playerOne.deck.set(7,new Card("H10","Weapon",10));
        game.playerOne.deck.set(8,new Card("H10","Weapon",10));
        game.playerOne.deck.set(9,new Card("B15","Weapon",15));
        game.playerOne.deck.set(10,new Card("B15","Weapon",15));
        game.playerOne.deck.set(11,new Card("L20","Weapon",20));

        //P2
        game.playerTwo.deck.set(0,new Card("F5","Foe",5));
        game.playerTwo.deck.set(1,new Card("F5","Foe",5));
        game.playerTwo.deck.set(2,new Card("F15","Foe",15));
        game.playerTwo.deck.set(3,new Card("F15","Foe",15));
        game.playerTwo.deck.set(4,new Card("F40","Foe",40));
        game.playerTwo.deck.set(5,new Card("D5","Weapon",5));
        game.playerTwo.deck.set(6,new Card("S10","Weapon",10));
        game.playerTwo.deck.set(7,new Card("H10","Weapon",10));
        game.playerTwo.deck.set(8,new Card("H10","Weapon",10));
        game.playerTwo.deck.set(9,new Card("B15","Weapon",15));
        game.playerTwo.deck.set(10,new Card("B15","Weapon",15));
        game.playerTwo.deck.set(11,new Card("E30","Weapon",40));

        //P3
        game.playerThree.deck.set(0,new Card("F5","Foe",5));
        game.playerThree.deck.set(1,new Card("F5","Foe",5));
        game.playerThree.deck.set(2,new Card("F5","Foe",5));
        game.playerThree.deck.set(3,new Card("F15","Foe",15));
        game.playerThree.deck.set(4,new Card("D5","Weapon",5));
        game.playerThree.deck.set(5,new Card("S10","Weapon",10));
        game.playerThree.deck.set(6,new Card("S10","Weapon",10));
        game.playerThree.deck.set(7,new Card("S10","Weapon",10));
        game.playerThree.deck.set(8,new Card("H10","Weapon",10));
        game.playerThree.deck.set(9,new Card("H10","Weapon",10));
        game.playerThree.deck.set(10,new Card("B15","Weapon",15));
        game.playerThree.deck.set(11,new Card("L20","Weapon",20));

        //P4
        game.playerFour.deck.set(0,new Card("F5","Foe",5));
        game.playerFour.deck.set(1,new Card("F15","Foe",15));
        game.playerFour.deck.set(2,new Card("F15","Foe",15));
        game.playerFour.deck.set(3,new Card("F40","Foe",40));
        game.playerFour.deck.set(4,new Card("D5","Weapon",5));
        game.playerFour.deck.set(5,new Card("D5","Weapon",5));
        game.playerFour.deck.set(6,new Card("S10","Weapon",10));
        game.playerFour.deck.set(7,new Card("H10","Weapon",10));
        game.playerFour.deck.set(8,new Card("H10","Weapon",10));
        game.playerFour.deck.set(9,new Card("B15","Weapon",15));
        game.playerFour.deck.set(10,new Card("L20","Weapon",20));
        game.playerFour.deck.set(11,new Card("E30","Weapon",30));

        game.currentPlayer=game.playerOne;


        //P1 draws event card
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream(" ".getBytes());
        System.setIn(in);
        game.SetEventNewCard(new Scanner(in));
        System.setIn(sysInBackup);
        game.currentEventCard=new Card("Q4","Event",4);

        //Ask for Sponsor
        sysInBackup = in;
        in = new ByteArrayInputStream("N\nY\n".getBytes());
        System.setIn(in);

        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);

        //Set Stage Cards
        sysInBackup = in;
        in = new ByteArrayInputStream("1\n8\nQuit\n2\n6\nQuit\n2\n4\n6\nQuit\n2\n4\nQuit".getBytes());
        System.setIn(in);

        game.SetStages(new Scanner(in));
        System.setIn(sysInBackup);



        //Stage 1

        //Ask For participation
        game.SetEligiblePlayers(0);

        sysInBackup = in;
        in = new ByteArrayInputStream("Y\n1\nY\n1\nY\n1".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerOne.deck.add(3,new Card("F30","Foe",30));
        game.playerThree.deck.add(4,new Card("S10","Weapon",10));
        game.playerFour.deck.add(8,new Card("B15","Weapon",15));

        game.playerOne.deck.remove(12);
        game.playerThree.deck.remove(12);
        game.playerFour.deck.remove(12);





        //Build Attack Decks
        sysInBackup = in;
        ByteArrayInputStream in1 = new ByteArrayInputStream("5\n6\nQuit\n".getBytes());
        ByteArrayInputStream in2 = new ByteArrayInputStream("5\n4\nQuit\n".getBytes());
        ByteArrayInputStream in3 = new ByteArrayInputStream("4\n7\nQuit\n".getBytes());

        System.setIn(in);

        game.playerOne.buildAttackDeck(new Scanner(in1));
        game.playerThree.buildAttackDeck(new Scanner(in2));
        game.playerFour.buildAttackDeck(new Scanner(in3));

        System.setIn(sysInBackup);


        game.playerOne.displayAttackDeck();
        game.playerThree.displayAttackDeck();
        game.playerFour.displayAttackDeck();




        sysInBackup = in;
        in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(0,new Scanner(in));
        System.setIn(sysInBackup);
        //Already discards hand

        //Stage 2
        //Ask For participation
        game.SetEligiblePlayers(1);

        sysInBackup = in;
        in = new ByteArrayInputStream("Y\nY\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerOne.deck.add(1,new Card("F10","Foe",10));
        game.playerThree.deck.add(10,new Card("L20","Weapon",20));
        game.playerFour.deck.add(9,new Card("L20","Weapon",20));

        game.playerOne.deck.remove(11);
        game.playerThree.deck.remove(11);
        game.playerFour.deck.remove(11);



        sysInBackup = in;
        in1 = new ByteArrayInputStream("7\n6\nQuit\n".getBytes());
        in2 = new ByteArrayInputStream("9\n4\nQuit\n".getBytes());
        in3 = new ByteArrayInputStream("6\n7\nQuit\n".getBytes());

        System.setIn(in);

        game.playerOne.buildAttackDeck(new Scanner(in1));
        game.playerThree.buildAttackDeck(new Scanner(in2));
        game.playerFour.buildAttackDeck(new Scanner(in3));

        System.setIn(sysInBackup);

        sysInBackup = in;
        in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(1,new Scanner(in));
        System.setIn(sysInBackup);

        assertEquals(game.playerOne.shields,0);
        assertEquals(game.playerOne.deck.get(0).name,"F5");
        assertEquals(game.playerOne.deck.get(1).name,"F10");
        assertEquals(game.playerOne.deck.get(2).name,"F15");
        assertEquals(game.playerOne.deck.get(3).name,"F15");
        assertEquals(game.playerOne.deck.get(4).name,"F30");
        assertEquals(game.playerOne.deck.get(5).name,"H10");
        assertEquals(game.playerOne.deck.get(6).name,"B15");
        assertEquals(game.playerOne.deck.get(7).name,"B15");
        assertEquals(game.playerOne.deck.get(8).name,"L20");

        //Stage 3

        game.SetEligiblePlayers(2);

        sysInBackup = in;
        in = new ByteArrayInputStream("Y\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);


        game.playerThree.deck.add(7,new Card("B15","Weapon",15));
        game.playerFour.deck.add(4,new Card("S10","Weapon",10));

        game.playerThree.deck.remove(10);
        game.playerFour.deck.remove(10);



        sysInBackup = in;
        in2 = new ByteArrayInputStream("9\n6\n4\nQuit\n".getBytes());
        in3 = new ByteArrayInputStream("7\n5\n8\nQuit\n".getBytes());

        System.setIn(in);

        game.playerThree.buildAttackDeck(new Scanner(in2));
        game.playerFour.buildAttackDeck(new Scanner(in3));

        System.setIn(sysInBackup);

        sysInBackup = in;
        in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(2,new Scanner(in));
        System.setIn(sysInBackup);
        //Stage 4

        game.SetEligiblePlayers(2);

        sysInBackup = in;
        in = new ByteArrayInputStream("Y\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);


        game.playerThree.deck.add(3,new Card("F30","Foe",30));
        game.playerFour.deck.add(6,new Card("L20","Weapon",20));

        game.playerThree.deck.remove(8);
        game.playerFour.deck.remove(8);







        sysInBackup = in;
        in2 = new ByteArrayInputStream("7\n6\n8\nQuit\n".getBytes());
        in3 = new ByteArrayInputStream("4\n5\n6\n8\nQuit\n".getBytes());

        System.setIn(in);

        game.playerThree.buildAttackDeck(new Scanner(in2));
        game.playerFour.buildAttackDeck(new Scanner(in3));

        System.setIn(sysInBackup);

        game.playerThree.displayAttackDeck();
        game.playerFour.displayAttackDeck();

        sysInBackup = in;
        in = new ByteArrayInputStream("Quit\nQuit\n1\n1\n1\n1\n1\n1\n1".getBytes());
        System.setIn(in);

        game.startRound(3,new Scanner(in));
        System.setIn(sysInBackup);

        game.playerThree.displayDeck();



        assertEquals(game.playerThree.shields,0);
        assertEquals(game.playerThree.deck.get(0).name,"F5");
        assertEquals(game.playerThree.deck.get(1).name,"F5");
        assertEquals(game.playerThree.deck.get(2).name,"F15");
        assertEquals(game.playerThree.deck.get(3).name,"F30");
        assertEquals(game.playerThree.deck.get(4).name,"S10");

        assertEquals(game.playerFour.shields,4);
        assertEquals(game.playerFour.deck.get(0).name,"F15");
        assertEquals(game.playerFour.deck.get(1).name,"F15");
        assertEquals(game.playerFour.deck.get(2).name,"F40");
        assertEquals(game.playerFour.deck.get(3).name,"L20");

        assertEquals(game.playerTwo.deck.size(),12);






    }

}
