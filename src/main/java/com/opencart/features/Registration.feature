Feature: Register Flow Feature File


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

