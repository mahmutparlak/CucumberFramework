package com.hrms.stepdefinitions;

import com.hrms.pages.PIMConfigurationPage;
import com.hrms.utils.CommonMethods;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;

import java.util.List;

public class ConfigurationStepDefinition extends CommonMethods {

    @Then("click on configuration dropdown")
    public void click_on_configuration_dropdown() {
        click(pimConfigurationPage.configurationDropdown);
    }

    @Then("click on Optional Fields")
    public void click_on_optional_fields() {
        click(pimConfigurationPage.optionalFields);
    }

    @Then("click on edit button")
    public void click_on_edit_button() {
        click(pimConfigurationPage.editAndSaveBtn);
    }

    @Then("uncheck unnecessary checkboxes")
    public void uncheck_unnecessary_checkboxes(DataTable checkboxOptions) throws InterruptedException {

        List<String> expectedCheckboxOptions = checkboxOptions.asList();

        for (String expectedCheckboxOption : expectedCheckboxOptions) {
                clickRadioOrCheckboxText(pimConfigurationPage.configurePIMCheckboxes, expectedCheckboxOption);
                Thread.sleep(1000);
        }
    }
}
