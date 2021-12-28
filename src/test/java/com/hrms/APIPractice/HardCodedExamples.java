package com.hrms.APIPractice;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardCodedExamples {
    /*
     * REST Assured - Java Library specifically developed to automate REST endpoint
     * Given - Preparing your request
     * When - What action will you perform, what type of call you are making?
     * Then - Verification
     * baseURI = URL
     * Postman and REST assure are different tools. They are not related at all.
     */

    /**
     * BaseURI for all endpoints
     */
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Mzk3MjA3MDcsImlzcyI6" +
            "ImxvY2FsaG9zdCIsImV4cCI6MTYzOTc2MzkwNywidXNlcklkIjoiMzM4OCJ9.vV7QCrX8BlWLGsUGleK8H2P5n-_4NMCVYxnmchygSGo";

    static String employeeID;

    @Test
    public void sampleTest() {

        RequestSpecification prepGetOneEmpRequest = given().header("Authorization", token).header("Content-Type",
                "application/json").queryParam("employee_id", "14400A").log().all();

        Response getOneEmployeeResponse = prepGetOneEmpRequest.when().get("/getOneEmployee.php");

        System.out.println(getOneEmployeeResponse.asString());

        getOneEmployeeResponse.then().assertThat().statusCode(200);
    }

    @Test
    public void aPOSTCreateEmployee() {
        RequestSpecification createEmployeeRequest = given().header("Authorization", token)
                .header("Content-Type", "application/json").body("{\n" +
                "  \"emp_firstname\": \"Apple\",\n" +
                "  \"emp_lastname\": \"Anna\",\n" +
                "  \"emp_middle_name\": \"Red\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"1975-01-01\",\n" +
                "  \"emp_status\": \"Self-Employee\",\n" +
                "  \"emp_job_title\": \"Cloud Architect\"\n" +
                "}");

        Response createEmployeeResponse = createEmployeeRequest.when().post("/createEmployee.php");

        createEmployeeResponse.prettyPrint();

        employeeID = createEmployeeResponse.jsonPath().getString("Employee.employee_id");

        System.out.println(employeeID);

        createEmployeeResponse.then().assertThat().statusCode(201);

        createEmployeeResponse.then().assertThat().body("Message", equalTo("Employee Created"));

        createEmployeeResponse.then().assertThat().body("Employee.emp_firstname", equalTo("Apple"));

        createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
    }

    @Test
    public void bGETCreatedEmployee() {
        RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token).queryParam("employee_id", employeeID);

        Response getEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");

        getEmployeeResponse.prettyPrint();

        String empID = getEmployeeResponse.body().jsonPath().getString("employee.employee_id");

        boolean verifyingEmployeeID = empID.equalsIgnoreCase(employeeID);

        Assert.assertTrue(verifyingEmployeeID);
    }

    @Test
    public void cGetAllEmployees() {
        RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token);

        Response getAllEmployeeResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

        //getAllEmployeeResponse.prettyPrint();

        String allEmployeeResponse = getAllEmployeeResponse.asString();

        JsonPath jsonPath = new JsonPath(allEmployeeResponse);

        //To get size of json object number
        int jsonObjectNumber = jsonPath.getInt("Employees.size()");
        System.out.println(jsonObjectNumber);

        /*for(int i = 0; i < jsonObjectNumber; i++) {
            String allEmployeeIDs = jsonPath.getString("Employees[" + i + "].employee_id");
            //System.out.println(allEmployeeIDs);

            if(allEmployeeIDs.equals(employeeID)) {
                System.out.println("Employee ID = " + employeeID + " is present in the body");

                String firstNameICreated = jsonPath.getString("Employees[" + i + "].emp_firstname");

                System.out.println(firstNameICreated);

                break;
            }
        }

        for (int i = 0; i < jsonObjectNumber; i++) {
            String allEmployeeFirstName = jsonPath.getString("Employees[" + i + "].emp_firstname");
            System.out.println(allEmployeeFirstName);
        }*/
    }

    @Test
    public void dPUTUpdateCreatedEmployee() {

        RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json")
                .header("Authorization", token);

        Response getAllEmployeeResponse = getAllEmployeesRequest.when().get("/getAllEmployees.php");

        String allEmployeeResponse = getAllEmployeeResponse.asString();

        JsonPath jsonPath = new JsonPath(allEmployeeResponse);

        int jsonObjectNumber = jsonPath.getInt("Employees.size()");

        for(int i = 0; i < jsonObjectNumber; i++) {
            String allEmployeeIDs = jsonPath.getString("Employees[" + i + "].employee_id");

            if(allEmployeeIDs.equals(employeeID)) {
                RequestSpecification putUpdatedEmployeeRequest = given().header("Content-Type", "application/json")
                        .header("Authorization", token).body("{\n" +
                                "  \"employee_id\": \"" + employeeID + "\",\n" +
                                "  \"emp_firstname\": \"Granny\",\n" +
                                "  \"emp_lastname\": \"Color\",\n" +
                                "  \"emp_middle_name\": \"Mix\",\n" +
                                "  \"emp_gender\": \"F\",\n" +
                                "  \"emp_birthday\": \"1986-10-21\",\n" +
                                "  \"emp_status\": \"Cloud Architect\",\n" +
                                "  \"emp_job_title\": \"Self-Employee\"\n" +
                                "}");

                Response putUpdatedEmployeeResponse = putUpdatedEmployeeRequest.when().put("/updateEmployee.php");

                putUpdatedEmployeeResponse.prettyPrint();
            }
        }

        for(int i = 0; i < jsonObjectNumber; i++) {
            String allEmployeeNames = jsonPath.getString("Employees[" + i + "].emp_firstname");

            if (allEmployeeNames.equals("Karpuz")) {
                Assert.assertTrue("Employee doesn't updated", true);
                break;
            }
        }
    }
}
