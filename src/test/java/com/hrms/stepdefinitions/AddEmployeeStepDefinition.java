package com.hrms.stepdefinitions;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.Constants;
import com.hrms.utils.ExcelReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AddEmployeeStepDefinition extends CommonMethods {

    @When("click on PIM button")
    public void click_on_pim_button() {
        click(dashboardPage.PIMBtn);
    }

    @When("click on add employee button")
    public void click_on_add_employee_button() {
        click(pimPage.addEmployeeBtn);
    }

    @Then("enter first and last name")
    public void enter_first_and_last_name() {
        sendText(addEmployeePage.firstNameTxtBx, "Mahmut");
        sendText(addEmployeePage.lastNameTxtBx, "Parlak");
    }

    @Then("click on save button")
    public void click_on_save_button() {
        click(addEmployeePage.saveBtn);
    }

    @Then("verify employee is added successfully")
    public void verify_employee_is_added_successfully() {
        String addedEmployeeFullName = personalDetailPage.employeeFullNameField.getText();
        Assert.assertEquals("Added employee matched", "Mahmut Parlak", addedEmployeeFullName);
    }

    @Then("enter first name {string}, middle name {string} and last name {string}")
    public void enter_first_name_middle_name_and_last_name(String firstName, String middleName, String lastName) {
        //like dataProvider in parameter it will take info from there.
        sendText(addEmployeePage.firstNameTxtBx, firstName);
        sendText(addEmployeePage.middleNameTxtBx, middleName);
        sendText(addEmployeePage.lastNameTxtBx, lastName);
    }

    @Then("verify that {string} is added successfully")
    public void verify_that_is_added_successfully(String fullName) {
        String addedEmployeeFullName = personalDetailPage.employeeFullNameField.getText();
        Assert.assertEquals("Added employee matched", fullName, addedEmployeeFullName);
    }

    @When("enter {string}, {string} and {string}")
    public void enter_and(String FirstName, String MiddleName, String LastName) {
        sendText(addEmployeePage.firstNameTxtBx, FirstName);
        sendText(addEmployeePage.middleNameTxtBx, MiddleName);
        sendText(addEmployeePage.lastNameTxtBx, LastName);
    }

    @Then("verify {string}, {string} and {string} is added successfully")
    public void verify_and_is_added_successfully(String FirstName, String MiddleName, String LastName) {
        String fullName = FirstName + " " + MiddleName + " " + LastName;
        String addedEmployeeFullName = personalDetailPage.employeeFullNameField.getText();
        Assert.assertEquals("Added employee matched", fullName, addedEmployeeFullName);
    }

    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable employees) {

        List<Map<String, String>> employeeNames = employees.asMaps();

        for (Map<String, String> employeeName : employeeNames) {
            String firstName = employeeName.get("FirstName");
            String middleName = employeeName.get("MiddleName");
            String lastName = employeeName.get("LastName");

            sendText(addEmployeePage.firstNameTxtBx, firstName);
            sendText(addEmployeePage.middleNameTxtBx, middleName);
            sendText(addEmployeePage.lastNameTxtBx, lastName);
            click(addEmployeePage.saveBtn);

            String actualEmpFullName = personalDetailPage.employeeFullNameField.getText();
            String expectedEmpFullName = firstName + " " + middleName + " " + lastName;

            Assert.assertEquals("Employee is not verified", expectedEmpFullName, actualEmpFullName);

            click(pimPage.addEmployeeBtn);
        }
    }

    @When("add multiple employees from excel {string} sheet and verify they are added")
    public void add_multiple_employees_from_excel_sheet_and_verify_they_are_added(String sheetName) {

        List<Map<String, String>> excelData = ExcelReader.excelIntoListMap(Constants.TEST_DATA_FILEPATH, sheetName);

        for (Map<String, String> excelEmp : excelData) {
            String firstName = excelEmp.get("FirstName");
            String middleName = excelEmp.get("MiddleName");
            String lastName = excelEmp.get("LastName");
            String employeeID = excelEmp.get("EmployeeID");

            addEmployeePage.firstNameTxtBx.sendKeys(firstName);
            addEmployeePage.middleNameTxtBx.sendKeys(middleName);
            addEmployeePage.lastNameTxtBx.sendKeys(lastName);
            addEmployeePage.employeeIDTxtBx.sendKeys(employeeID);
            addEmployeePage.saveBtn.click();

            String actualEmpFullName = personalDetailPage.employeeFullNameField.getText();
            String expectedEmpFullName = firstName + " " + middleName + " " + lastName;

            Assert.assertEquals("Employee is not verified", expectedEmpFullName, actualEmpFullName);

            click(pimPage.addEmployeeBtn);
        }
    }
}
