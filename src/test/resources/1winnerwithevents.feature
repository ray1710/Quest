Feature: 1 winner with events

  Scenario: A rigged game with 1 winner starts
    Given rig a new game of Quest 1Winner with Events
    When it is P1 Turn
    Then Draws a Q4 Event Card
    And P1 becomes Sponsor
    And Sponsor sets up stages "4\nQuit\n10\nQuit\n8\nQuit\n2\nQuit"
    And P2, P3, and P4 agree to participate for stage 1 in Scenario 3
    And Eligible Players build Attack and attack Stage 1 "1\nQuit\n6\nQuit\n12\nQuit"
    And P2 beat Stage 1
    And P3 beat Stage 1
    And P4 beat Stage 1
    And P2, P3, and P4 agree to participate for stage 2 in Scenario 3
    And Eligible Players build Attack and attack Stage 2 "3\nQuit\n9\nQuit\n1\nQuit"
    And P2 beat Stage 2
    And P3 beat Stage 2
    And P4 beat Stage 2
    And P2, P3, and P4 agree to participate for stage 3 in Scenario 3
    And Eligible Players build Attack and attack Stage 3 "7\nQuit\n7\nQuit\n3\nQuit"
    And P2 beat Stage 3
    And P3 beat Stage 3
    And P4 beat Stage 3
    And P2, P3, and P4 agree to participate for stage 4 in Scenario 3
    And Eligible Players build Attack and attack Stage 4 "9\n10\n11\nQuit\n2\n6\nQuit\n5\nQuit\n13\n13\n13\n13\n13\n13\n13"
    And P2 has 4 Shields
    And P3 has 4 Shields
    And P4 has 4 Shields
    And it is P2 Turn
    And Draws a "Plague" Event Card
    And Current Event Card is Played ""
    And P2 has 2 Shields
    And it is P3 Turn
    And Draws a "Prosperity" Event Card
    And Current Event Card is Played "13\n13\n13\n13\n13\n13\n13"
    And it is P4 Turn
    And Draws a "Queen’s favor" Event Card
    And Current Event Card is Played "13\n13\n"
    And it is P1 Turn
    And Draws a Q3 Event Card
    And P1 becomes Sponsor
    And Sponsor sets up stages "1\nQuit\n1\nQuit\n1\nQuit"
    And P2, P3, and P4 agree to participate for stage 1 in Scenario 3
    And Eligible Players build Attack and attack Stage 1 "1\nQuit\n1\nQuit\nQuit"
    And P2 beat Stage 1
    And P3 beat Stage 1
    And P4 did not Beat Stage 1
    And P2, P3, and P4 agree to participate for stage 2 in Scenario 3
    And Eligible Players build Attack and attack Stage 2 "1\nQuit\n1\nQuit\n1\nQuit"
    And P2 beat Stage 2
    And P3 beat Stage 2
    And P2, P3, and P4 agree to participate for stage 3 in Scenario 3
    And Eligible Players build Attack and attack Stage 3 "1\nQuit\n1\nQuit\n13\n13\n13\n13\n13"
    And "Player 3" are winners
    And P2 has 5 Shields
    And P3 has 7 Shields
    And P4 has 4 Shields









