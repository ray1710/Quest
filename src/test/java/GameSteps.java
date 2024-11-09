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
import static java.lang.System.setIn;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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




    @When("it is P{int} Turn")
    public void player_turn(int num) {
        if(num==1)
        {
            game.currentPlayer=game.playerOne;
        }
        else if(num==2)
        {
            game.currentPlayer=game.playerTwo;
        }
        else if(num==3)
        {
            game.currentPlayer=game.playerThree;
        }
        else if (num==4)
        {
            game.currentPlayer=game.playerFour;
        }
    }

    @Then("Draws a Q{int} Event Card")
    public void draw_Q_Card(int num) {
        if(num==2)
        {
            game.currentEventCard=new Card("Q2","Event",2);
        }
        else if(num==3)
        {
            game.currentEventCard=new Card("Q3","Event",3);
        }
        else if(num==4)
        {
            game.currentEventCard=new Card("Q4","Event",4);
        }
        else if (num==5)
        {
            game.currentEventCard=new Card("Q5","Event",5);
        }
    }



    @Then("P{int} becomes Sponsor")
    public void player_becomes_sponsor(int num) {
        InputStream sysInBackup = in;
        ByteArrayInputStream in = null;

        if(num==1)
        {
           in=new ByteArrayInputStream("Y\n".getBytes());
        }
        else if(num==2)
        {
            in=new ByteArrayInputStream("N\nY\n".getBytes());
        }
        else if(num==3)
        {
            in=new ByteArrayInputStream("N\nN\nY\n".getBytes());
        }
        else if(num==4)
        {
            in=new ByteArrayInputStream("N\nN\nN\nY\n".getBytes());
        }
        game.SetSponsor(new Scanner(in));
        System.setIn(sysInBackup);
    }



    @Then("Sponsor sets up stages {string}")
    public void Sponsor_Sets_Up_Stages(String input) {
        InputStream sysInBackup = System.in;
        String formattedInput = input.replace("\\n", "\n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(formattedInput.getBytes());
        System.setIn(inputStream);

        game.SetStages(new Scanner(System.in));
        System.setIn(sysInBackup);
    }




    @Then("P1 P3 and P4 Agree to Participate In Stage 1 and get new Cards")
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

    @Then("Eligible Players build Attack and attack Stage {int} {string}")
    public void Eligible_Player_Build_Attack_Stage(int round, String input)
    {
        InputStream sysInBackup = System.in;
        String formattedInput = input.replace("\\n", "\n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(formattedInput.getBytes());
        System.setIn(inputStream);

        game.startRound(round-1,new Scanner(System.in));
        System.setIn(sysInBackup);
    }

    @Then("P{int} beat Stage {int}")
    public void player_beat_stage(int player, int round)
    {
        if(player==1)
        {
            assertTrue(game.eligiblePlayers.contains(game.playerOne));
        }
        else if(player==2)
        {
            assertTrue(game.eligiblePlayers.contains(game.playerTwo));
        }
        else if(player==3)
        {
            assertTrue(game.eligiblePlayers.contains(game.playerThree));
        }
        else if(player==4)
        {
            assertTrue(game.eligiblePlayers.contains(game.playerFour));
        }

    }

    @Then("P{int} did not Beat Stage {int}")
    public void player_not_beat_stage(int player, int round)
    {
        if(player==1)
        {
            assertFalse(game.eligiblePlayers.contains(game.playerOne));
        }
        else if(player==2)
        {
            assertFalse(game.eligiblePlayers.contains(game.playerTwo));
        }
        else if(player==3)
        {
            assertFalse(game.eligiblePlayers.contains(game.playerThree));
        }
        else if(player==4)
        {
            assertFalse(game.eligiblePlayers.contains(game.playerFour));
        }

    }



    @Then("P1 P3 and P4 Agree to Participate In Stage 2 and get new Cards")
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

    @Then("P3 and P4 Agree to Participate In Stage 3 and get new Cards")
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


    @Then("P3 and P4 Agree to Participate In Stage 4 and get new Cards")
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

    @Then("P{int} has {int} Shields")
    public void player_and_shields(int player,int shields)
    {
        if(player==1)
        {
            assertEquals(game.playerOne.shields,shields);
        }
        else if(player==2)
        {
            assertEquals(game.playerTwo.shields,shields);
        }
        else if(player==3)
        {
            assertEquals(game.playerThree.shields,shields);
        }
        else if(player==4)
        {
            assertEquals(game.playerFour.shields,shields);
        }

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

    @Then("P2, P3, and P4 agree to participate for stage 1")
    public void player_2_3_and_4_agree_to_participate()
    {
        game.SetEligiblePlayers(0);

        InputStream sysInBackup = in;
        ByteArrayInputStream in = new ByteArrayInputStream("Y\n13\nY\n13\nY\n13\n".getBytes());
        System.setIn(in);

        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);
    }
    @Then("P2 and P4 agree to participate for stage 2")
    public void P2_P4_agree_to_participate_for_stage_2()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in;



        //Player 2 and 4 agree to participate for Stage 2
        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);

        game.playerTwo.deck.set(11,new Card("H10","Weapon",10));
        game.playerFour.deck.set(11,new Card("F10","Foe",10));
    }
    @Then("P2 and P4 agree to participate for stage 3")
    public void P2_P4_agree_to_participate_for_stage_3()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in;

        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        game.playerTwo.deck.set(11,new Card("F10","Foe",10));
        game.playerFour.deck.set(10,new Card("L20","Weapon",20));

        System.setIn(sysInBackup);
    }
    @Then("P2 and P4 agree to participate for stage 4")
    public void P2_P4_agree_to_participate_for_stage_4()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in;

        in=new ByteArrayInputStream("Y\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        game.playerTwo.deck.set(10,new Card("D5","Weapon",5));
        game.playerFour.deck.set(9,new Card("H10","Weapon",10));

        System.setIn(sysInBackup);

    }
    @Then("P2 and P4 agree to participate for stage 1")
    public void P2_and_P4_agree_to_participate_for_stage_1()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("N\nY\nY\n".getBytes());
        game.askForParticipation(new Scanner(in));

        System.setIn(sysInBackup);

        game.playerTwo.deck.set(10,new Card("D5","Weapon",5));
        game.playerFour.deck.set(8,new Card("D5","Weapon",5));
    }
    @Then("P2 and P4 agree to participate for stage 2.0")
    public void P2_and_P4_agree_to_participate_for_stage_2()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);
    }
    @Then("P2 and P4 agree to participate for stage 3.0")
    public void P2_and_P4_agree_to_participate_for_stage_3()
    {
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);
    }

    @Then("{string} are winners")
    public void Player_are_winners(String winners)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        System.setIn(in);

        try {
            game.checkWinner();
            String capturedOutput = outputStream.toString();
            assertTrue(capturedOutput.contains(winners));

        } finally {
            System.setOut(originalOut);
        }
    }


    @Given("rig a new game of Quest 1Winner with Events")
    public void rig_a_new_game_of_Quest_1Winner_with_Events()
    {
        game=new Game();
        game.distributeCards();
        game.playerOne.deck.set(0,new Card("F5","Foe",5));
        game.playerOne.deck.set(1,new Card("F25","Foe",25));
        game.playerOne.deck.set(2,new Card("F5","Foe",5));
        game.playerOne.deck.set(3,new Card("F5","Foe",5));
        game.playerOne.deck.set(4,new Card("F5","Foe",5));
        game.playerOne.deck.set(5,new Card("F25","Foe",25));
        game.playerOne.deck.set(6,new Card("L20","Weapon",20));
        game.playerOne.deck.set(7,new Card("H10","Weapon",10));
        game.playerOne.deck.set(8,new Card("F20","Foe",20));
        game.playerOne.deck.set(9,new Card("D5","Weapon",5));
        game.playerOne.deck.set(10,new Card("F10","Foe",10));
        game.playerOne.deck.set(11,new Card("B15","Weapon",15));

        //P2
        game.playerTwo.deck.set(0,new Card("D5","Weapon",5));
        game.playerTwo.deck.set(1,new Card("F70","Foe",70));
        game.playerTwo.deck.set(2,new Card("F30","Foe",30));
        game.playerTwo.deck.set(3,new Card("F10","Foe",10));
        game.playerTwo.deck.set(4,new Card("H10","Weapon",10));
        game.playerTwo.deck.set(5,new Card("F5","Foe",5));
        game.playerTwo.deck.set(6,new Card("S10","Weapon",10));
        game.playerTwo.deck.set(7,new Card("D5","Weapon",5));
        game.playerTwo.deck.set(8,new Card("F5","Foe",5));
        game.playerTwo.deck.set(9,new Card("L20","Weapon",20));
        game.playerTwo.deck.set(10,new Card("H10","Weapon",10));
        game.playerTwo.deck.set(11,new Card("F15","Foe",15));

        //P3
        game.playerThree.deck.set(0,new Card("F10","Foe",10));
        game.playerThree.deck.set(1,new Card("F35","Foe",35));
        game.playerThree.deck.set(2,new Card("F5","Foe",5));
        game.playerThree.deck.set(3,new Card("F10","Foe",10));
        game.playerThree.deck.set(4,new Card("B15","Weapon",15));
        game.playerThree.deck.set(5,new Card("F50","Foe",50));
        game.playerThree.deck.set(6,new Card("H10","Weapon",10));
        game.playerThree.deck.set(7,new Card("F25","Foe",25));
        game.playerThree.deck.set(8,new Card("F20","Foe",20));
        game.playerThree.deck.set(9,new Card("F25","Foe",25));
        game.playerThree.deck.set(10,new Card("S10","Foe",10));
        game.playerThree.deck.set(11,new Card("S10","Foe",10));

        //P4
        game.playerFour.deck.set(0,new Card("S10","Weapon",10));
        game.playerFour.deck.set(1,new Card("F15","Foe",15));
        game.playerFour.deck.set(2,new Card("F15","Foe",15));
        game.playerFour.deck.set(3,new Card("F10","Foe",10));
        game.playerFour.deck.set(4,new Card("L20","Weapon",20));
        game.playerFour.deck.set(5,new Card("S10","Weapon",10));
        game.playerFour.deck.set(6,new Card("S10","Weapon",10));
        game.playerFour.deck.set(7,new Card("E30","Weapon",30));
        game.playerFour.deck.set(8,new Card("B15","Weapon",15));
        game.playerFour.deck.set(9,new Card("B15","Weapon",15));
        game.playerFour.deck.set(10,new Card("S10","Weapon",10));
        game.playerFour.deck.set(11,new Card("F15","Foe",15));
    }


    @Then("P2, P3, and P4 agree to participate for stage 1 in Scenario 3")
    public void P2_P3_and_P4_agree_to_participate_stage_1()
    {
        //Stage 1
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\n2\nY\n2\nY\n2".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);
    }
    @Then("P2, P3, and P4 agree to participate for stage 2 in Scenario 3")
    public void P2_P3_and_P4_agree_to_participate_stage_2() {
        //Stage 2
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\nY\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerTwo.deck.set(11,new Card("B15","Weapon",15));
        game.playerThree.deck.set(11,new Card("S10","Weapon",10));
        game.playerFour.deck.set(11,new Card("D5","Weapon",5));
    }
    @Then("P2, P3, and P4 agree to participate for stage 3 in Scenario 3")
    public void P2_P3_and_P4_agree_to_participate_stage_3() {
        //Stage 3
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\nY\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerTwo.displayDeck();
        game.playerThree.displayDeck();
        game.playerFour.displayDeck();



        game.playerTwo.deck.set(11,new Card("H10","Weapon",10));
        game.playerThree.deck.set(11,new Card("S10","Weapon",10));
        game.playerFour.deck.set(11,new Card("D5","Weapon",5));


    }
    @Then("P2, P3, and P4 agree to participate for stage 4 in Scenario 3")
    public void P2_P3_and_P4_agree_to_participate_stage_4() {
        //Stage 3
        InputStream sysInBackup=in;
        ByteArrayInputStream in=new ByteArrayInputStream("Y\nY\nY\n".getBytes());

        game.askForParticipation(new Scanner(in));
        System.setIn(sysInBackup);

        game.playerTwo.displayDeck();
        game.playerThree.displayDeck();
        game.playerFour.displayDeck();



        game.playerTwo.deck.set(11,new Card("D5","Weapon",5));
        game.playerThree.deck.set(11,new Card("F10","Foe",10));
        game.playerFour.deck.set(11,new Card("D5","Weapon",5));
    }
    @Then("Draws a {string} Event Card")
    public void draws_a_non_q_card(String card)
    {
        game.currentEventCard=new Card(card,"Event",0);
    }
    @Then("Current Event Card is Played {string}")
    public void play_current_event_card(String indexes)
    {
        String formattedInput = indexes.replace("\\n", "\n");
        ByteArrayInputStream inputStream = new ByteArrayInputStream(formattedInput.getBytes());
        System.setIn(inputStream);
        game.playEventCard();
    }























}


