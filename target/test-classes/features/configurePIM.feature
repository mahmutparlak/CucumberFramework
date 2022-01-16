Feature: Configure PIM - Optional Fields

  @configuration
  Scenario: Unchecking unnecessary checkboxes
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM button
    Then click on configuration dropdown
    And click on Optional Fields
    Then click on edit button
    And uncheck unnecessary checkboxes
      | Show SSN field in Personal Details |
      | Show SIN field in Personal Details |
    And click on save button