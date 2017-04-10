# The QA Automation Challenge for Hello Fresh Task 2 - Java, Rest Assured and Cucumber (API Test) - Java, Selenium Webdriver and Maven

## Consists Tests for :
<br>i)Get all countries and validate that US, DE and GB were returned in the response
<br>ii)Get each country (US, DE and GB) individually and validate the response on particular country url
<br>iii)GET Information of an in-existent Country
<br>iv) POST method to add new countries
<br> v) Post For newly added counties , where input has errors in fields or the country code is already present

## Instructions
<br> i) Maven is used to build the system for tests
<br> Clone or download the project from github and fromm root directory where pom.xml is present run

### mvn package

## Test classes for this test
<br> stepdefinitions package consists The StepDefinition.java file where the steps are defined for the Gherkin code in the feature file
<br> TestFeature1.feature file in resources folder is the feature file where the scenarios are described