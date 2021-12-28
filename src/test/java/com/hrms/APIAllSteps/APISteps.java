package com.hrms.APIAllSteps;

import com.hrms.utils.APIConstants;
import com.hrms.utils.APIPayloadCommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jfr.ContentType;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class APISteps {

    RequestSpecification createEmployeeRequest;
    Response createEmployeeResponse;
    static String createdEmployeeID;

    @Given("a request is prepared to create an employee")
    public void a_request_is_prepared_to_create_an_employee() {
        createEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", GenerateTokenStep.token).body(APIPayloadCommonMethods.createEmployeePayload()).log().all();
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        createEmployeeResponse = createEmployeeRequest.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }

   @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(int code) {
        createEmployeeResponse.then().assertThat().statusCode(code);
    }

    @Then("the employee is created contains key {string} and value {string}")
    public void the_employee_is_created_contains_key_and_value(String key, String value) {
        createEmployeeResponse.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee ID {string} is stored as a global variable to be used for other calls")
    public void the_employee_id_is_stored_as_a_global_variable_to_be_used_for_other_calls(String value) {
        createdEmployeeID = createEmployeeResponse.jsonPath().getString(value);
    }

    @Given("a request is prepared to retrieve the created employee")
    public void a_request_is_prepared_to_retrieve_the_created_employee() {
        createEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", GenerateTokenStep.token).queryParam("employee_id", createdEmployeeID);
    }

    @When("a GET call is made to retrieve the created employee")
    public void a_get_call_is_made_to_retrieve_the_created_employee() {
        createEmployeeResponse = createEmployeeRequest.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }

    @Then("the status code for retrieving the created employee is {int}")
    public void the_status_code_for_retrieving_the_created_employee_is(int statusCodeForRetrievingEmployee) {
        createEmployeeResponse.then().assertThat().statusCode(statusCodeForRetrievingEmployee);
    }

    @Then("the retrieved employeeID matches the globally stored employee ID")
    public void the_retrieved_employee_id_matches_the_globally_stored_employee_id() {
        createEmployeeResponse.then().assertThat().body("employee.employee_id", equalTo(createdEmployeeID));
    }

    @Given("a request is prepared to retrieve all employees")
    public void a_request_is_prepared_to_retrieve_all_employees() {

    }

    @When("a GET call is made to retrieve all employees")
    public void a_get_call_is_made_to_retrieve_all_employees() {

    }

    @Then("the status code for retrieving all employees is {int}")
    public void the_status_code_for_retrieving_all_employees_is(Integer int1) {

    }

    @Then("the retrieved data contains the globally stored employee ID")
    public void the_retrieved_data_contains_the_globally_stored_employee_id() {

    }

    @Then("the retrieved data matches the data that was used to create an employee")
    public void the_retrieved_data_matches_the_data_that_was_used_to_create_an_employee() {

    }
}
