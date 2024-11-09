Feature: 0 winner

  Scenario: A Game with no winner starts
    Given rig a new game of Quest 0Winner
    When it is P1 Turn
    Then Draws a Q2 Event Card
    And P1 becomes Sponsor
    And Sponsor sets up stages "2\nQuit\n1\nQuit"
    And P2, P3, and P4 agree to participate for stage 1
    And Eligible Players build Attack and attack Stage 1 "Quit\nQuit\nQuit\n"
    And P2 did not Beat Stage 1
    And P3 did not Beat Stage 1
    And P4 did not Beat Stage 1
    And the Quest is resolved
    And P1 has 0 Shields
    And P2 has 0 Shields
    And P3 has 0 Shields
    And P4 has 0 Shields
    And P1 has 12 Cards
    And P2 has 12 Cards
    And P3 has 12 Cards
    And P4 has 12 Cards



