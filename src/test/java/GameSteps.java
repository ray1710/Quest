import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.in;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameSteps {

    private Game game;

    @Given("rig a new game of Quest A1")
    public void rig_a_new_game_of_Quest_A1() {
        game=new Game();
        game.distributeCards();
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
    }

    @When("it is Player 1's turn")
    public void player_one_turn() {
        game.currentPlayer=game.playerOne;
    }
    @When("Draws a Q4 Event Card")
    public void draw_Q4_Card() {
        game.currentEventCard=new Card("Q4","Event",4);
    }

    @When("Player 1 3 and 4 Decline to be a Sponsor While Player 2 Accepts to be Sponsor")
    public void player_2_becomes_sponsor() {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("N\nY\n".getBytes());
        System.setIn(in);
        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);
    }


    @When("Player 2 Sets up A1 Stage")
    public void Player_2_Sets_Up_A1_Stage() {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n8\nQuit\n2\n6\nQuit\n2\n4\n6\nQuit\n2\n4\nQuit".getBytes());
        System.setIn(in);

        game.SetStages(new Scanner(in));
        System.setIn(sysInBackup);


    }


    @When("Player 1 3 and 4 Agree to Participate In Stage 1 and get new Cards")
    public void Player_1_3_and_4_Agree_to_Participate_In_Stage_1(){

        game.SetEligiblePlayers(0);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n1\nY\n1\nY\n1".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerOne.deck.add(3,new Card("F30","Foe",30));
        game.playerThree.deck.add(4,new Card("S10","Weapon",10));
        game.playerFour.deck.add(8,new Card("B15","Weapon",15));

        game.playerOne.deck.remove(12);
        game.playerThree.deck.remove(12);
        game.playerFour.deck.remove(12);
    }


    @When("Player 1 builds Attack Deck for Stage 1")
    public void Player_1_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n6\nQuit\n".getBytes());
        System.setIn(in);
        game.playerOne.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);

    }


    @When("Player 3 builds Attack Deck for Stage 1")
    public void Player_3_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 4 builds Attack Deck for Stage 1")
    public void Player_4_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n7\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 1 3 and 4 Attack and Beat Stage 1")
    public void Player_1_3_and_4_Attack_and_Beat_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(0,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 1 3 and 4 Agree to Participate In Stage 2 and get new Cards")
    public void Player_1_3_and_4_Agree_to_Participate_In_Stage_2()
    {
        game.SetEligiblePlayers(1);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\nY\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerOne.deck.add(1,new Card("F10","Foe",10));
        game.playerThree.deck.add(10,new Card("L20","Weapon",20));
        game.playerFour.deck.add(9,new Card("L20","Weapon",20));

        game.playerOne.deck.remove(11);
        game.playerThree.deck.remove(11);
        game.playerFour.deck.remove(11);

    }

    @When("Player 1 builds Attack Deck for Stage 2")
    public void Player_1_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n6\nQuit\n".getBytes());
        System.setIn(in);
        game.playerOne.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);

    }


    @When("Player 3 builds Attack Deck for Stage 2")
    public void Player_3_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("9\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 4 builds Attack Deck for Stage 2")
    public void Player_4_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("6\n7\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 1 3 and 4 Attack and only Player 3 and Player 4 Beat Stage 2")
    public void Player_1_3_and_4_Attack_and_only_3_and_4_Beat_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(1,new Scanner(in));
        System.setIn(sysInBackup);

        assertTrue(!game.eligiblePlayers.contains(game.playerOne));
        Assertions.assertEquals(game.playerOne.shields,0);
        Assertions.assertEquals(game.playerOne.deck.get(0).name,"F5");
        Assertions.assertEquals(game.playerOne.deck.get(1).name,"F10");
        Assertions.assertEquals(game.playerOne.deck.get(2).name,"F15");
        Assertions.assertEquals(game.playerOne.deck.get(3).name,"F15");
        Assertions.assertEquals(game.playerOne.deck.get(4).name,"F30");
        Assertions.assertEquals(game.playerOne.deck.get(5).name,"H10");
        Assertions.assertEquals(game.playerOne.deck.get(6).name,"B15");
        Assertions.assertEquals(game.playerOne.deck.get(7).name,"B15");
        Assertions.assertEquals(game.playerOne.deck.get(8).name,"L20");
    }

    @When("Player 3 and 4 Agree to Participate In Stage 3 and get new Cards")
    public void Player_3_and_4_Agree_to_Participate_In_Stage_3()
    {
        game.SetEligiblePlayers(2);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);


        game.playerThree.deck.add(7,new Card("B15","Weapon",15));
        game.playerFour.deck.add(4,new Card("S10","Weapon",10));

        game.playerThree.deck.remove(10);
        game.playerFour.deck.remove(10);
    }

    @When("Player 3 builds Attack Deck for Stage 3")
    public void Player_3_builds_Attack_Deck_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("9\n6\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 4 builds Attack Deck for Stage 3")
    public void Player_4_builds_Attack_Deck_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n5\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 3 and 4 Attack Beat Stage 3")
    public void Player_3_and_4_Attack_and_Beat_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(2,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 3 and 4 Agree to Participate In Stage 4 and get new Cards")
    public void Player_3_and_4_Agree_to_Participate_In_Stage_4()
    {
        game.SetEligiblePlayers(3);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\nY\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);


        game.playerThree.deck.add(3,new Card("F30","Foe",30));
        game.playerFour.deck.add(6,new Card("L20","Weapon",20));

        game.playerThree.deck.remove(8);
        game.playerFour.deck.remove(8);
    }

    @When("Player 3 builds Attack Deck for Stage 4")
    public void Player_3_builds_Attack_Deck_Stage_4()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n6\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @When("Player 4 builds Attack Deck for Stage 4")
    public void Player_4_builds_Attack_Deck_Stage_4()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n5\n6\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }
    @When("Player 3 and 4 Attack and only Player 4 Beat Stage 4")
    public void Player_1_and_4_Attack_and_only_4_Beat_Stage_4()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\n1\n1\n1\n1\n1\n1\n1".getBytes());
        System.setIn(in);

        game.startRound(3,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 3 has 0 Shields And 5 Cards Left in Deck")
    public void Player_3_has_0_Shields_And_5_Cards_Left_in_Deck()
    {

        Assertions.assertEquals(game.playerThree.shields,0);
        Assertions.assertEquals(game.playerThree.deck.get(0).name,"F5");
        Assertions.assertEquals(game.playerThree.deck.get(1).name,"F5");
        Assertions.assertEquals(game.playerThree.deck.get(2).name,"F15");
        Assertions.assertEquals(game.playerThree.deck.get(3).name,"F30");
        Assertions.assertEquals(game.playerThree.deck.get(4).name,"S10");
    }

    @Then("Player 4 has 4 Shields And 4 Cards Left in Deck")
    public void Player_4_has_4_Shields_And_4_Cards_Left_in_Deck()
    {
        Assertions.assertEquals(game.playerFour.shields,4);
        Assertions.assertEquals(game.playerFour.deck.get(0).name,"F15");
        Assertions.assertEquals(game.playerFour.deck.get(1).name,"F15");
        Assertions.assertEquals(game.playerFour.deck.get(2).name,"F40");
        Assertions.assertEquals(game.playerFour.deck.get(3).name,"L20");
    }
    @Then("Player 2 has 12 Cards")
    public void Player_2_has_12_Cards()
    {
        Assertions.assertEquals(game.playerTwo.deck.size(),12);
    }




















}


