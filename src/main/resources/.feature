Feature: Bonus Task: User Sessions

  Scenario: Retrieve user information when the session is valid
    Given I have a valid session with username and password
    When I request user information
    Then the response should contain valid user data


  Scenario: Retrieve user information when the session is not valid
    Given I have an invalid session
    When I request user information
    Then the response should be unauthorized