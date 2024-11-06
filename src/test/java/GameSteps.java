import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
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
    @Then("Draws a Q4 Event Card")
    public void draw_Q4_Card() {
        game.currentEventCard=new Card("Q4","Event",4);
    }

    @Then("Player 1 3 and 4 Decline to be a Sponsor While Player 2 Accepts to be Sponsor")
    public void player_2_becomes_sponsor() {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("N\nY\n".getBytes());
        System.setIn(in);
        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);
    }


    @Then("Player 2 Sets up A1 Stage")
    public void Player_2_Sets_Up_A1_Stage() {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("1\n8\nQuit\n2\n6\nQuit\n2\n4\n6\nQuit\n2\n4\nQuit".getBytes());
        System.setIn(in);

        game.SetStages(new Scanner(in));
        System.setIn(sysInBackup);


    }


    @Then("Player 1 3 and 4 Agree to Participate In Stage 1 and get new Cards")
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


    @Then("Player 1 builds Attack Deck for Stage 1")
    public void Player_1_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n6\nQuit\n".getBytes());
        System.setIn(in);
        game.playerOne.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);

    }


    @Then("Player 3 builds Attack Deck for Stage 1")
    public void Player_3_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("5\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 4 builds Attack Deck for Stage 1")
    public void Player_4_builds_Attack_Deck_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n7\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 1 3 and 4 Attack and Beat Stage 1")
    public void Player_1_3_and_4_Attack_and_Beat_Stage_1()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(0,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 1 3 and 4 Agree to Participate In Stage 2 and get new Cards")
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

    @Then("Player 1 builds Attack Deck for Stage 2")
    public void Player_1_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n6\nQuit\n".getBytes());
        System.setIn(in);
        game.playerOne.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);

    }


    @Then("Player 3 builds Attack Deck for Stage 2")
    public void Player_3_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("9\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 4 builds Attack Deck for Stage 2")
    public void Player_4_builds_Attack_Deck_Stage_2()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("6\n7\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 1 3 and 4 Attack and only Player 3 and Player 4 Beat Stage 2")
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

    @Then("Player 3 and 4 Agree to Participate In Stage 3 and get new Cards")
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

    @Then("Player 3 builds Attack Deck for Stage 3")
    public void Player_3_builds_Attack_Deck_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("9\n6\n4\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 4 builds Attack Deck for Stage 3")
    public void Player_4_builds_Attack_Deck_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n5\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 3 and 4 Attack Beat Stage 3")
    public void Player_3_and_4_Attack_and_Beat_Stage_3()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Quit\nQuit\nQuit\n".getBytes());
        System.setIn(in);

        game.startRound(2,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 3 and 4 Agree to Participate In Stage 4 and get new Cards")
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

    @Then("Player 3 builds Attack Deck for Stage 4")
    public void Player_3_builds_Attack_Deck_Stage_4()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("7\n6\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerThree.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 4 builds Attack Deck for Stage 4")
    public void Player_4_builds_Attack_Deck_Stage_4()
    {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("4\n5\n6\n8\nQuit\n".getBytes());
        System.setIn(in);
        game.playerFour.buildAttackDeck(new Scanner(in));
        System.setIn(sysInBackup);
    }
    @Then("Player 3 and 4 Attack and only Player 4 Beat Stage 4")
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

    @Given("rig a new game of Quest 2Winner")
    public void rig_a_new_game_of_Quest_2Winner()
    {
        game=new Game();
        game.distributeCards();
        game.playerOne.deck.set(0,new Card("F20","Foe",20));
        game.playerOne.deck.set(1,new Card("D5","Weapon",5));
        game.playerOne.deck.set(2,new Card("F5","Foe",5));
        game.playerOne.deck.set(3,new Card("F30","Foe",30));
        game.playerOne.deck.set(4,new Card("B15","Weapon",15));
        game.playerOne.deck.set(5,new Card("F20","Foe",20));
        game.playerOne.deck.set(6,new Card("F70","Foe",70));
        game.playerOne.deck.set(7,new Card("H10","Weapon",10));
        game.playerOne.deck.set(8,new Card("D5","Weapon",5));
        game.playerOne.deck.set(9,new Card("H10","Weapon",10));
        game.playerOne.deck.set(10,new Card("D5","Weapon",5));
        game.playerOne.deck.set(11,new Card("F50","Foe",50));

        //P2
        game.playerTwo.deck.set(0,new Card("F30","Foe",30));
        game.playerTwo.deck.set(1,new Card("B15","Weapon",15));
        game.playerTwo.deck.set(2,new Card("F15","Weapon",15));
        game.playerTwo.deck.set(3,new Card("L20","Weapon",20));
        game.playerTwo.deck.set(4,new Card("S10","Weapon",10));
        game.playerTwo.deck.set(5,new Card("F10","Foe",10));
        game.playerTwo.deck.set(6,new Card("S10","Weapon",10));
        game.playerTwo.deck.set(7,new Card("F15","Foe",15));
        game.playerTwo.deck.set(8,new Card("H10","Weapon",10));
        game.playerTwo.deck.set(9,new Card("D5","Weapon",5));
        game.playerTwo.deck.set(10,new Card("F50","Foe",50));
        game.playerTwo.deck.set(11,new Card("H10","Weapon",10));

        //P3
        game.playerThree.deck.set(0,new Card("B15","Weapon",15));
        game.playerThree.deck.set(1,new Card("B15","Weapon",15));
        game.playerThree.deck.set(2,new Card("F5","Foe",5));
        game.playerThree.deck.set(3,new Card("F20","Foe",20));
        game.playerThree.deck.set(4,new Card("F20","Foe",20));
        game.playerThree.deck.set(5,new Card("S10","Weapon",10));
        game.playerThree.deck.set(6,new Card("S10","Weapon",10));
        game.playerThree.deck.set(7,new Card("F15","Foe",15));
        game.playerThree.deck.set(8,new Card("H10","Weapon",10));
        game.playerThree.deck.set(9,new Card("F5","Foe",5));
        game.playerThree.deck.set(10,new Card("L20","Weapon",20));
        game.playerThree.deck.set(11,new Card("F25","Foe",25));

        //P4
        game.playerFour.deck.set(0,new Card("H10","Weapon",10));
        game.playerFour.deck.set(1,new Card("B15","Weapon",15));
        game.playerFour.deck.set(2,new Card("F15","Foe",15));
        game.playerFour.deck.set(3,new Card("F10","Foe",10));
        game.playerFour.deck.set(4,new Card("F10","Foe",10));
        game.playerFour.deck.set(5,new Card("F15","Foe",15));
        game.playerFour.deck.set(6,new Card("F25","Foe",25));
        game.playerFour.deck.set(7,new Card("F5","Foe",5));
        game.playerFour.deck.set(8,new Card("S10","Weapon",10));
        game.playerFour.deck.set(9,new Card("H10","Weapon",10));
        game.playerFour.deck.set(10,new Card("F25","Foe",25));
        game.playerFour.deck.set(11,new Card("S10","Weapon",10));
    }

    @When("it is Player 1's turn and draws a Q4 Card who sponsors it and builds Stages")
    public void it_is_Player_1_turn_and_draws_a_Q4_Card_who_sponsors_it_and_builds_Stages()
    {
        game.currentPlayer=game.playerOne;
        game.currentEventCard=new Card("Q4","Event",4);
        game.sponsor=game.playerOne;
        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerThree);
        game.eligiblePlayers.add(game.playerFour);



        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("3\nQuit\n1\nQuit\n4\n1\nQuit\n1\nQuit\n".getBytes());
        System.setIn(in);

        game.SetStages(new Scanner(in));

        System.setIn(sysInBackup);
    }

    @Then("Player 2, 3, and 4 agree to participate and only Player 2 and 4 beat Stage 1")
    public void player_2_3_and_4_agree_to_participate_and_only_player_2_and_4_beat_Stage_1()
    {
        game.SetEligiblePlayers(0);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n13\nY\n13\nY\n13\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);


        in = new ByteArrayInputStream("10\nQuit\nQuit\n8\nQuit\n".getBytes());
        game.startRound(0,new Scanner(in));
        System.setIn(in);

        assertTrue(!game.eligiblePlayers.contains(game.playerThree));



    }

    @Then("Player 2 and 4 agree to participate for Stage 2, 3, and 4 and both players beat it")
    public void Player_2_and_4_agree_to_participate_for_Stage_2_3_and_4_and_both_players_beat_it()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in;



        //Player 2 and 4 agree to participate for Stage 2
        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);

        game.playerTwo.deck.set(11,new Card("H10","Weapon",10));
        game.playerFour.deck.set(11,new Card("F10","Foe",10));

        //Player 2 and 4 play stage 2
        in=new ByteArrayInputStream("4\nQuit\n1\n8\nQuit\n".getBytes());

        game.startRound(1,new Scanner(in));
        System.setIn(sysInBackup);

        //Player 2 and 4 agree to participate for Stage 3

        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        game.playerTwo.deck.set(11,new Card("F10","Foe",10));
        game.playerFour.deck.set(10,new Card("L20","Weapon",20));

        System.setIn(sysInBackup);

        //Player 2 and 4 play stage 3
        in=new ByteArrayInputStream("2\n4\nQuit\n1\n7\nQuit\n".getBytes());

        game.startRound(2,new Scanner(in));
        System.setIn(sysInBackup);


        //Player 2 and 4 agree to participate for Stage 4

        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        game.playerTwo.deck.set(10,new Card("D5","Weapon",5));
        game.playerFour.deck.set(9,new Card("H10","Weapon",10));

        System.setIn(sysInBackup);

        //Player 2 and 4 play stage 3
        in=new ByteArrayInputStream("1\nQuit\n5\n7\nQuit\n8\n8\n8\n8\n8\n".getBytes());


        game.startRound(3,new Scanner(in));

        System.setIn(sysInBackup);
    }

    @Then("Player 2 and 4 gained 4 Shields")
    public void player_2_and_4_gained_4_shields()
    {
        assertEquals(game.playerTwo.shields,4);
        assertEquals(game.playerFour.shields,4);
    }

    @Then("Player 2 draws a 3 stage quest, Player 3 decides to sponsor it and builds stages")
    public void player_2_draws_a_3_stage_quest_player_3_decides_to_sponsor_it_and_builds_stages()
    {
        game.currentPlayer=game.playerTwo;
        game.currentEventCard=new Card("Q3","Event",3);
        game.sponsor=game.playerThree;

        game.eligiblePlayers.add(game.playerOne);
        game.eligiblePlayers.add(game.playerTwo);
        game.eligiblePlayers.add(game.playerFour);

        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("3\nQuit\n9\nQuit\n7\nQuit\n".getBytes());

        game.SetStages(new Scanner(in));

        System.setIn(sysInBackup);
    }

    @Then("Player 1 declines to Participate")
    public void Player_1_declines_to_Participate()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("N\nY\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);

        game.playerTwo.deck.set(10,new Card("D5","Weapon",5));
        game.playerFour.deck.set(8,new Card("D5","Weapon",5));

        assertTrue(!game.eligiblePlayers.contains(game.playerOne));
    }

    @Then("Player 2 and Player 4 participate and win all stages")
    public void Player_2_and_Player_4_participate_and_win_all_stages()
    {
        //Player 2 and 4 play stage 1
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("11\nQuit\n9\nQuit\n".getBytes());

        game.startRound(0,new Scanner(in));
        System.setIn(sysInBackup);

        //Player 2 and 4 agree to participate in next stage
        in=new ByteArrayInputStream("Y\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));

        //Player 2 and 4 play in stage 2
        in=new ByteArrayInputStream("10\nQuit\n8\nQuit\n".getBytes());

        game.startRound(1,new Scanner(in));
        System.setIn(sysInBackup);

        //Player 2 and 4 agree to participate in next stage
        in=new ByteArrayInputStream("Y\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));

        //Player 2 and 4 play in stage 3
        in=new ByteArrayInputStream("3\n5\nQuit\n7\nQuit\n1\n1\n1\n1\n1\n1\n1".getBytes());

        game.startRound(2,new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("Player 2 and Player 4 are winners")
    public void Player_2_and_Player_4_are_winners()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        try {
            game.checkWinner();
            String capturedOutput = outputStream.toString();
            String expectedOutput = "Player 2, Player 4, has won";
            System.out.print(expectedOutput);
            assertTrue(capturedOutput.contains(expectedOutput));

        } finally {
            System.setOut(originalOut);
        }
    }














}


