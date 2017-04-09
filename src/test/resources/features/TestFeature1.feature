Feature: GET Calls for all countries url http://services.groupkt.com/country/get/all and particular country url http://services.groupkt.com/country/get/iso2code/{COUNTRY_ISO2CODE}
  Validate the response and response payloads


  Scenario: Get all countries and validate that US, DE and GB were returned in the response
    When The User Calls all countries with a GET call
    Then The User GET A Response CODE of 200
    And A JsonArray called result having information on all countries and having US,DE abd GB are returned in response

  Scenario Outline: Get each country (US, DE and GB) individually and validate the response on particular country url
    When User does a GET call with "<COUNTRY_ISO2CODE>" on individual country url
    Then The User GETS a Response CODE of 200
    And The Response payload consists valid information for the "<COUNTRY_ISO2CODE>" country including "<name>" , "<alpha2_code>" and "<alpha3_code>"
  Examples:
  |COUNTRY_ISO2CODE|name|alpha2_code|alpha3_code|
  |US|United States of America|US   |USA|
  |DE|Germany                 |DE   |DEU|
  |GB|United Kingdom of Great Britain and Northern Ireland|GB|GBR|

  Scenario: GET Information of an in-existent Country
    When User calls the particular country url
    But with an in-existent country code
    Then The User Gets a 200 Response code and the response has message with json array "No matching country found for requested code"


  Scenario Outline: POST method to add new countries
    When User Gives a payload with "<name>" , "<alpha2_code>" and "<alpha3_code>" And Posts to Particular Country URL
    Then The user GETS a response whether The new country is added to the list and if the payload items are inappropriate type or Country is already present a BAD Request is given in response to the user
   Examples:
     |name|alpha2_code|alpha3_code|
     |central country|CE|CES      |
     |another country|  |  anc    |
     |               |SU|SUT      |
     |2134           |TH|THR      |
     |United Kingdom |GB|GBR      |