Feature: A1 Scenario

  Scenario: A Rigged Game of Quest Starts
    Given rig a new game of Quest A1
    When it is Player 1's turn
    And Draws a Q4 Event Card
    And Player 1 3 and 4 Decline to be a Sponsor While Player 2 Accepts to be Sponsor
    And Player 2 Sets up A1 Stage
    And Player 1 3 and 4 Agree to Participate In Stage 1 and get new Cards
    And Player 1 builds Attack Deck for Stage 1
    And Player 3 builds Attack Deck for Stage 1
    And Player 4 builds Attack Deck for Stage 1
    And Player 1 3 and 4 Attack and Beat Stage 1
    And Player 1 3 and 4 Agree to Participate In Stage 2 and get new Cards
    And Player 1 builds Attack Deck for Stage 2
    And Player 3 builds Attack Deck for Stage 2
    And Player 4 builds Attack Deck for Stage 2
    And Player 1 3 and 4 Attack and only Player 3 and Player 4 Beat Stage 2
    And Player 3 and 4 Agree to Participate In Stage 3 and get new Cards
    And Player 3 builds Attack Deck for Stage 3
    And Player 4 builds Attack Deck for Stage 3
    And Player 3 and 4 Attack Beat Stage 3
    And Player 3 and 4 Agree to Participate In Stage 4 and get new Cards
    And Player 3 builds Attack Deck for Stage 4
    And Player 4 builds Attack Deck for Stage 4
    And Player 3 and 4 Attack and only Player 4 Beat Stage 4
    Then Player 3 has 0 Shields And 5 Cards Left in Deck
    And Player 4 has 4 Shields And 4 Cards Left in Deck
    And Player 2 has 12 Cards

















