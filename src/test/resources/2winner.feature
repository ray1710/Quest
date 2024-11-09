Feature: 2 winner Scenario

  Scenario: A Game with 2 winners start
    Given rig a new game of Quest 2Winner
    When it is P1 Turn
    Then Draws a Q4 Event Card
    And P1 becomes Sponsor
    And Sponsor sets up stages "3\nQuit\n1\nQuit\n4\n1\nQuit\n1\nQuit\n"
    And P2, P3, and P4 agree to participate for stage 1
    And Eligible Players build Attack and attack Stage 1 "10\nQuit\nQuit\n8\nQuit\n"
    And P2 beat Stage 1
    And P3 did not Beat Stage 1
    And P4 beat Stage 1
    And P2 and P4 agree to participate for stage 2
    And Eligible Players build Attack and attack Stage 2 "4\nQuit\n1\n8\nQuit\n"
    And P2 beat Stage 2
    And P4 beat Stage 2
    And P2 and P4 agree to participate for stage 3
    And Eligible Players build Attack and attack Stage 3 "2\n4\nQuit\n1\n7\nQuit\n"
    And P2 beat Stage 3
    And P4 beat Stage 3
    And P2 and P4 agree to participate for stage 4
    And Eligible Players build Attack and attack Stage 4 "1\nQuit\n5\n7\nQuit\n8\n8\n8\n8\n8\n"
    And P1 has 0 Shields
    And P2 has 4 Shields
    And P3 has 0 Shields
    And P4 has 4 Shields
    And it is P2 Turn
    And Draws a Q3 Event Card
    And P3 becomes Sponsor
    And Sponsor sets up stages "3\nQuit\n9\nQuit\n7\nQuit\n"
    And P2 and P4 agree to participate for stage 1
    And Eligible Players build Attack and attack Stage 1 "11\nQuit\n9\nQuit\n"
    And P2 beat Stage 1
    And P4 beat Stage 1
    And P2 and P4 agree to participate for stage 2.0
    And Eligible Players build Attack and attack Stage 2 "10\nQuit\n8\nQuit\n"
    And P2 beat Stage 2
    And P4 beat Stage 2
    And P2 and P4 agree to participate for stage 3.0
    And Eligible Players build Attack and attack Stage 3 "3\n5\nQuit\n7\nQuit\n1\n1\n1\n1\n1\n1\n1"
    And "Player 2, Player 4" are winners
    And P2 has 7 Shields
    And P4 has 7 Shields












