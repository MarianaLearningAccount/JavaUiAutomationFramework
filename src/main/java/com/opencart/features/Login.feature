Feature: Login Feature Test Suite

  Scenario Outline: An error message is displayed when login is performed with invalid data
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When loginButton is clicked
    Then The following error messages are displayed
      | Warning: No match for E-mail Address and/or Password. |
    Examples:
      | attribute | email             | password            |
      | email     | invalid@gmail.com | nevermind           |
      | password  | andrei@gmail.com  | jkvjfljlivk24465326 |

  Scenario Outline: A valid user is able to log into the system
    Given The "https://andreisecuqa.host/index.php?route=account/login&language=en-gb" is accessed
    And the following data is entered into the login form:
      | <email>    |
      | <password> |
    When loginButton is clicked
    Then the current url contains the following keyword: "account".
    Examples:
      | email           | password  |
      | andre@gmail.com | jfgfjfjgh |
