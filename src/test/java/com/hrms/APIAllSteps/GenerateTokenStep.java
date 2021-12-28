package com.hrms.APIAllSteps;

import com.hrms.utils.APIConstants;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class GenerateTokenStep {

    static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        RequestSpecification generateTokenRequest = given().header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"email\": \"mahoo@yahoo.com\",\n" +
                        "  \"password\": \"maho123\"\n" +
                        "}");

        Response generateTokenResponse = generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);
        generateTokenResponse.prettyPrint();

        token = "Bearer " + generateTokenResponse.jsonPath().getString("token");
    }
}
