@Login
Feature: Login pada aplikasi CURA Healthcare Service
  Sebagai pengguna aplikasi CURA
  Saya ingin melakukan login
  Agar dapat mengakses halaman Make Appointment

  Scenario Outline: Memeriksa login dengan berbagai kombinasi kredensial
    Given I open the CURA application
    When I open the login form
    And I enter username "<username>" and password "<password>"
    And I submit login
    Then the login result should be "<expectedResult>"

    Examples:
      | username | password           | expectedResult |
      | John Doe | ThisIsNotAPassword | SUCCESS        |
      | Jane Doe | ThisIsNotAPassword | FAILURE        |
      | John Doe | WrongPassword123   | FAILURE        |