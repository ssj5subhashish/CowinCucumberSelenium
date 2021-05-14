@cowin
Feature: Find the Vaccine centers and extract them to a spreadsheet

  Scenario Outline: Capture all the vaccine centers info from Cowin website
    Given I select the browser "<browser>"
    And I open the Cowin website
    When I pick the zipcode from the spreadsheet and store it as "zipCodes"
    Then I search the stored "zipCodes" zipcodes in the website and store it in the same spreadsheet
    Examples:
      | browser |
      | Chrome  |
      | Firefox |
      | Edge    |