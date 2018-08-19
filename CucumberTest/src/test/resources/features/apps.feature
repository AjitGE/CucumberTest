Feature: Login Action
@apps
Scenario: Successful Login with Valid Credentials
  
	Given user loads the data from "excel" located at "C:\\Users\\Ajit\\Documents\\Book1.xlsx"
	Given user launches "chrome" browser with url "https://google.com"
	When User Navigate to LogIn Page
	And User enters UserName and Password
	Then Message displayed Login Successfully

Scenario: Successful LogOut
	When User LogOut from the Application
	Then Message displayed Logout Successfully