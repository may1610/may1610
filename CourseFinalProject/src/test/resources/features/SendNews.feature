@Backend
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
  Scenario: Send news on schedule
    Given the user is on send news page
    When the user provides news information and sends on schedule
    Then they should see the success message "The News has been sent successfully."
    
  @tags case4
  Scenario: See merge tag list displayed after the first letter is typed on editor
    Given the user is on send news page
    When the user type letter "E" into news content
    Then they should see merge tag list displayed

 @tags case5
  Scenario: See merge tag list displayed after the first letter is typed on editor
    Given the user is on send news page
    When the user type letter "E" into news content
    And the user select merge tag "Email"
    Then they should see the merge tag will be marked as block
 
 @tags case6
  Scenario: Attach file
    Given the user is on send news page
    When the user add files    
    Then they should see the list of files with corresponding file icon    