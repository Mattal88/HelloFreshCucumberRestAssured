package stepdefinitions;

import com.jayway.restassured.response.Response;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * Created by matshaik on 4/9/2017.
 */
public class StepDefinition {

    public final String BASE_URI = "http://services.groupkt.com/country";
    public final String ALL_COUNTRIES_URL = "/get/all";
    public final String PARTICULAR_COUNTRY_URL = "/get/iso2code/{COUNTRY_ISO2CODE}";

    public String url;
    public static Response response;
    public Map<String,String> countryDetails;


    @Before
    public void setup(){
    }


    @When("^The User Calls all countries with a GET call$")
    public void theUserCallsAllCountriesWithAGETCall() throws Throwable {
        url=BASE_URI+ALL_COUNTRIES_URL;
    }


    @Then("^The User GET A Response CODE of (\\d+)$")
    public void theUserGETAResponseCODEOf(int arg0) throws Throwable {
        response =given().when().get(url);
        response.then().statusCode(200);
    }

    @And("^A JsonArray called result having information on all countries and having US,DE abd GB are returned in response$")
    public void aJsonArrayCalledResultHavingInformationOnAllCountriesAndHavingUSDEAbdGBAreReturnedInResponse() throws Throwable {
           response.then().body("RestResponse.result.alpha2_code",hasItems("US","DE","GB"));
    }

    @When("^User does a GET call with \"([^\"]*)\" on individual country url$")
    public void userDoesAGETCallWithOnIndividualCountryUrl(String arg0) throws Throwable {
        String countryUrl=PARTICULAR_COUNTRY_URL.replaceFirst("\\{COUNTRY_ISO2CODE\\}",arg0);
        response = given().when().get(BASE_URI+countryUrl);
    }

    @Then("^The User GETS a Response CODE of (\\d+)$")
    public void theUserGETSAResponseCODEOf(int arg0) throws Throwable {
        response.then().statusCode(arg0);
    }

    @Then("^The Response payload consists valid information for the \"([^\"]*)\" country including \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\"$")
    public void the_Response_payload_consists_valid_information_for_the_country_including_and(String arg1, String arg2, String arg3, String arg4) throws Throwable {
        response.then().assertThat().body("RestResponse.result.name",equalTo(arg2))
                .assertThat().body("RestResponse.result.alpha2_code",equalTo(arg3))
                .assertThat().body("RestResponse.result.alpha3_code",equalTo(arg4));
    }

    @When("^User calls the particular country url$")
    public void user_calls_the_particular_country_url() throws Throwable {
        String invalidCountryCode = "Inexistent";
        String countryUrl=PARTICULAR_COUNTRY_URL.replaceFirst("\\{COUNTRY_ISO2CODE\\}",invalidCountryCode);
        url = BASE_URI+countryUrl;
    }

    @When("^with an in-existent country code$")
    public void with_an_in_existent_country_code() throws Throwable {
        response = given().when().get(url);
    }


    @Then("^The User Gets a (\\d+) Response code and the response has message with json array \"([^\"]*)\"$")
    public void the_User_Gets_a_Response_code_and_the_response_has_message_with_json_array(int arg1, String arg2) throws Throwable {
        response.then().statusCode(arg1);
        Assert.assertEquals(response.getBody().asString().contains(arg2),true);
    }

    @When("^User Gives a payload with \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" And Posts to Particular Country URL$")
    public void user_Gives_a_payload_with_and_And_Posts_to_Particular_Country_URL(String arg1, String arg2, String arg3) throws Throwable {
        countryDetails = new HashMap<String, String>();
        countryDetails.put("name",arg1);
        countryDetails.put("alpha2_code",arg2);
        countryDetails.put("alpha3_code",arg3);
        String countryUrl=PARTICULAR_COUNTRY_URL.replaceFirst("\\{COUNTRY_ISO2CODE\\}",arg2);

        response = given().contentType("application/json").body(countryDetails).when().post(BASE_URI+countryUrl);
    }

    @Then("^The user GETS a response (\\d+)$")
    public void the_user_GETS_a_response(int arg1) throws Throwable {
        response.then().statusCode(arg1);
    }

    @Then("^The user validates the country present by doing a GET all countries and validate the entry in response \"([^\"]*)\"$")
    public void the_user_validates_the_country_present_by_doing_a_GET_all_countries_and_validate_the_entry_in_response(String arg1) throws Throwable {
        response =given().when().get(BASE_URI+ALL_COUNTRIES_URL);
        response.then().body("RestResponse.result.alpha2_code",hasItems(arg1));
    }

    @When("^a User Gives a payload with \"([^\"]*)\" , \"([^\"]*)\" and \"([^\"]*)\" And Posts to Particular Country URL where the inputs are bad, the country is already present$")
    public void a_User_Gives_a_payload_with_and_And_Posts_to_Particular_Country_URL_where_the_inputs_are_bad_the_country_is_already_present(String arg1, String arg2, String arg3) throws Throwable {
        countryDetails = new HashMap<String, String>();
        countryDetails.put("name",arg1);
        countryDetails.put("alpha2_code",arg2);
        countryDetails.put("alpha3_code",arg3);
        String countryUrl=PARTICULAR_COUNTRY_URL.replaceFirst("\\{COUNTRY_ISO2CODE\\}",arg2);

        response = given().contentType("application/json").body(countryDetails).when().post(BASE_URI+countryUrl);
    }

    @Then("^The User receives a (\\d+) response for BAD REQUEST made$")
    public void the_User_receives_a_response_for_BAD_REQUEST_made(int arg1) throws Throwable {
        response.then().statusCode(arg1);
    }


}
