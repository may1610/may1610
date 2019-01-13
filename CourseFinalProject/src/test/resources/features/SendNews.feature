Feature: Admin send news to subscribers on testmaster.vn

  @tags case1
  Scenario: Not able to send news when provide lack of information
    Given the user is on send news page
    When the user does not provide news information
    Then they should see the error message "This is required field"

  @tags case2
  Scenario: Send news
    Given the user is on send news page
    When the user provides news information and sends
    Then they should see the success message "The News has been sent successfully."

  @tags case3
  Scenario: Send news
    Given the user is on send news page
    When the user provides news information and sends on schedule
    Then they should see the success message "The News has been sent successfully."