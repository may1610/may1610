@Frontend
Feature: Subscribe testmaster.vn

  @tags case1
  Scenario Outline: Not able to register with an invalid email
    Given the user is on testmaster home page
    When the user register with an invalid "<email>"
    Then they should see the error message "* Email không đúng định dạng"

    Examples: 
      | email           |
      |									|      
      | abc             |
      | abc@123.123     |
      | abc@ 123.com    |
      | abc@123. com    |
      

  @tags case2
  Scenario Outline: Registering successfully with a valid email
    Given the user is on testmaster home page
    When the user register with a valid email
    And fill in extra information "<Name>", "<Gender>", "<NewsType>"
    Then they should see the successfull message "Bạn đã đăng ký nhận bản tin thành công. Hãy kiểm tra Email để xác nhận việc đăng ký"

    Examples: 
      | Name | Gender | NewsType                      |
      | May  | Nữ     | Thông tin khai giảng khóa học |

  @tags case3
  Scenario: Showing message when provide a registered email
    Given the user is on testmaster home page
    When the user register with a registered email
    Then they should see the existed message

  @tags case4
  Scenario: Showing message when not provide name
    Given the user is on testmaster home page
    When the user register with a valid email
    And the user does not provide name
    Then they should see the required name message "This is required field"