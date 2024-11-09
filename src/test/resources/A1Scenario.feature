Feature: A1 Scenario

  Scenario: A Rigged Game of Quest Starts
    Given rig a new game of Quest A1
    When it is P1 Turn
    Then Draws a Q4 Event Card
    And P2 becomes Sponsor
    And Sponsor sets up stages "1\n8\nQuit\n2\n6\nQuit\n2\n4\n6\nQuit\n2\n4\nQuit\n"
    And P1 P3 and P4 Agree to Participate In Stage 1 and get new Cards
    And Eligible Players build Attack and attack Stage 1 "5\n6\nQuit\n5\n4\nQuit\n4\n7\nQuit\n"
    And P1 beat Stage 1
    And P3 beat Stage 1
    And P4 beat Stage 1
    And P1 P3 and P4 Agree to Participate In Stage 2 and get new Cards
    And Eligible Players build Attack and attack Stage 2 "7\n6\nQuit\n9\n4\nQuit\n6\n7\nQuit\n"
    And P1 did not Beat Stage 2
    And P1 has 9 Cards
    And P3 beat Stage 2
    And P4 beat Stage 2
    And P3 and P4 Agree to Participate In Stage 3 and get new Cards
    And Eligible Players build Attack and attack Stage 3 "9\n6\n4\nQuit\n7\n5\n8\nQuit\n"
    And P3 beat Stage 3
    And P4 beat Stage 3
    And P3 and P4 Agree to Participate In Stage 4 and get new Cards
    And Eligible Players build Attack and attack Stage 4 "7\n6\n8\nQuit\n4\n5\n6\n8\nQuit\n1\n1\n1\n1\n1\n1\n1"
    And P1 has 0 Shields
    And P2 has 0 Shields
    And P3 has 0 Shields
    And P4 has 4 Shields
    And P3 has 5 Cards
    And P4 has 4 Cards



















