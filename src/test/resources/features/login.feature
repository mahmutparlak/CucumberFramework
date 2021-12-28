@featureTag @login # feature level tag
Feature: Login Functionality

  @smoke @validCredentials # scenario level tag
  Scenario: Login with valid credentials
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed

  @regression @invalidCredentials @whatever # can have multiple tags and can add any name
  Scenario: Login with invalid credentials
    When enter invalid credentials
    And click on login button
    Then verify error message

  @regression
  Scenario: Login without username
    When entering just password
    And click on login button
    Then get error message and verify

  Scenario: Login without password
    When entering just username
    And click on login button
    Then get password error message and verify
