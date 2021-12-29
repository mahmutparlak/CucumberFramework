Feature: Syntax HRMS API Workflow

  Background:
    Given a JWT is generated

  @APIWorkFlow
  Scenario: Creating an employee
    Given a request is prepared to create an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee is created contains key "Message" and value "Employee Created"
    And the employee ID "Employee.employee_id" is stored as a global variable to be used for other calls

  @APIWorkFlow
  Scenario: Retrieving created employee
    Given a request is prepared to retrieve the created employee
    When a GET call is made to retrieve the created employee
    Then the status code for retrieving the created employee is 200
    And the retrieved employeeID matches the globally stored employee ID
    And the retrieved data matches the data used to created an employee

  @Progression
  Scenario: Retrieving all employees and verifying that created employee details display in the response
    Given a request is prepared to retrieve all employees
    When a GET call is made to retrieve all employees
    Then the status code for retrieving all employees is 200
    And the retrieved data contains the globally stored employee ID
    And the retrieved data matches the data that was used to create an employee