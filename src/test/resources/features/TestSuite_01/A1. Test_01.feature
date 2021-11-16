#Author: Md. Borhan Uddin Sarker<borhan7420@gmail.com>
#Keywords Summary : currency conversion calculation
# Date: 16 Nov 2020

Feature: Online currency exchange calculator

  Background: Go to online currency exchange calculator
    Given User go to online currency exchange calculator

  Scenario: User fills BUY amount, SELL amount box is being emptied
    And Verify sell input field value showing "100"
    When User input "200" as buy input field
    Then Verify for inputting buy value sell input field emptied

  Scenario: User fills SELL amount, BUY amount box is being emptied
    When User input "200" as buy input field
    And Verify buy input field value showing "200"
    When User input "300" as sell input field
    Then Verify for inputting sell value buy input field emptied

  Scenario: Bank provider's exchange amount for selling (X) is lower than the amount, provided by PS (Y), then a text box is displayed, representing the loss (X-Y)
    And Verify loss amount showing for 1st currency
    And Verify loss amount showing for 2nd currency
    And Verify loss amount showing for 3rd currency
    And Verify loss amount showing for 4th currency
    Then Verify loss amount showing for 5th currency


  Scenario: User selects country, rates must be updated and currency option should be changed to the respective default currency for that country
    When User change country
    And Verify respective default currency set for selected country