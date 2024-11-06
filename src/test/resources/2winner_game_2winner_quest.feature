Feature: 2 Winners

  Scenario: A Game with 2 winners
    Given rig a new game of Quest 2Winner
    When it is Player 1's turn and draws a Q4 Card who sponsors it and builds Stages
    Then Player 2, 3, and 4 agree to participate and only Player 2 and 4 beat Stage 1
    And Player 2 and 4 agree to participate for Stage 2, 3, and 4 and both players beat it
    And Player 2 and 4 gained 4 Shields
    And Player 2 draws a 3 stage quest, Player 3 decides to sponsor it and builds stages
    And Player 1 declines to Participate
    And Player 2 and Player 4 participate and win all stages
    And Player 2 and Player 4 are winners
