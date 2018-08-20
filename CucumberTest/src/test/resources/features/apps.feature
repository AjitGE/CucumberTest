Feature: Login Action

  @apps 

  Scenario: Successful Login with Valid Credentials
    Given user loads the data from "excel" located at "C:\\Users\\716676\git\\CucumberTest\\CucumberTest\\src\\test\\resources\\Fixture\\Book1.xlsx"
    Given user launches "chrome" browser with url "#url"
    When User Navigate to LogIn Page
    And User enters UserName and Password
    Then Message displayed Login Successfully

  Scenario: Successful LogOut
    When User LogOut from the Application
    Then Message displayed Logout Successfully
