Feature: Sign up in gmail

  Scenario Outline: Verify Gmail password validation error while signup to Gmail
    Given User visits gmail signup page
    When User inputs "<passwordLessThanEight>" less than eight chars
    Then  Use eight characters or more for your password
    When User inputs "<passwordNotStrong>" more than eight chars but it is not strong
    Then  Please choose a stronger password
    Examples:
      | passwordLessThanEight | passwordNotStrong |
      | 1234              | 12345678          |
      | abcd              | 1234abcd          |
      | 0000              | asdf5678          |