Feature: Add Employee Functionality

  Background: //to reduce code duplication until first different method we can store all the common methods
  //in Background for scenario in the same feature file
    When enter valid credentials
    And click on login button
    Then verify dashboard is displayed
    When click on PIM button
    And click on add employee button

  @addEmployeeWithoutLoginDetails
  Scenario: Add employee without login details
    Then enter first and last name
    And click on save button
    Then verify employee is added successfully

  @addEmployeeWithLoginDetails
  Scenario: Add employee with login details
    Then enter first, middle and last name
    And click on login detail checkbox
    Then enter required login details
    And click on save button
    Then get employee details
    And verify employee details

  @parameter
  Scenario: Add employee without login details but middle name
    Then enter first name "Marta", middle name "Mary" and last name "Ostash"
    And click on save button
    Then verify that "Marta Mary Ostash" is added successfully

    @examples
  Scenario Outline:  Adding multiple without login details
    When enter "<FirstName>", "<MiddleName>" and "<LastName>"
    And click on save button
    Then verify "<FirstName>", "<MiddleName>" and "<LastName>" is added successfully

    Examples:
      | FirstName | MiddleName | LastName |
      | Mahmutt   | Y          | Parlak   |
      | Hilal     | Y          | Parlak   |
      | Kaan      | H          | Parlak   |

      @dataTableWithHeader
      Scenario: Adding multiple employees at one execution
        When add multiple employees and verify they are added successfully
          | FirstName | MiddleName | LastName |
          | Mahmuttt  | Y          | Parlak   |
          | Hilall    | Y          | Parlak   |
          | Kaann     | H          | Parlak   |

        @ExcelTask
        Scenario: Adding multiple employee from excel
          When add multiple employees from excel "AddEmployee" sheet and verify they are added

          Scenario: Adding employee and validation in database
            Then enter first name "Kafa", middle name "Bin" and last name "Besyuz"
            And click on save button
            Then verify that "Kafa Bin Besyuz" is added successfully