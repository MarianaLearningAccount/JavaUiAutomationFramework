Feature: Register Flow Feature File

  @Regression @Smoke
  Scenario: Access the Account Page after successful registration
    Given Home Page is accessed
    And Register Page is accessed from Home Page menu
    When the registration form is completed with valid random data
    And the PrivacyToggle is enabled
    And continueButton is clicked
    Then the new page url contains "account/success" keyword

  Scenario: User remains on Register Page when continue button is not clocked during the register flow
    Given Home Page is accessed
    And Register Page is accessed from Home Page menu
    When the registration form is completed with valid random data
    And the PrivacyToggle is enabled
    Then the new page url contains "account/register" keyword

  @Regression
  Scenario: User remains on Register Page when privacy conditions are not accepted during the registration flow
    Given Home Page is accessed
    And Register Page is accessed from Home Page menu
    When the registration form is completed with valid random data
    And continueButton is clicked
    Then the current url contains the following keyword: "account".

  @run
  Scenario Outline: Error messages are displayed when trying to register with invalid <attribute> data
    Given Home Page is accessed
    And Register Page is accessed from Home Page menu
    And the registration form is completed with the following data:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | random      |
      | password  | Random      |
    When continueButton is clicked
    Then The following error messages are displayed
      | Warning: You must agree to the Privacy Policy.   |
      | <attribute> must be between 1 and 32 characters. |
    Examples:
      | attribute  | firstName         | lastName       |
      | First Name | huodgiudgigrdugir | random         |
      | Last Name  | random            | jbhkdghieugyig |