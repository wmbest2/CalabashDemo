Feature: Player Details Feature
    As a User I want to view the details of a player

    Scenario: As a user I want to see a players name 
        Given I am on the Player List screen
        When I view the details for player scottferg
        Then I should see the name scottferg 

    Scenario: As a user I want to see a players currently logged coordinates
        Given I am on the Player List screen
        When I view the details for player scottferg
        Then I should see the coordinates 10, 11, -234
