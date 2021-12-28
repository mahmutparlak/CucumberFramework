package com.hrms.stepdefinitions;

import com.hrms.testbase.BaseClass;
import com.hrms.utils.CommonMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    @Before
    public void startTest() {
        BaseClass.setUp();
    }

    @After
    public void endTest(Scenario scenario) {
        byte[] screenshot;

        if(scenario.isFailed()) {
            screenshot = CommonMethods.takeScreenShot("failed/" + scenario.getName());
        } else {
            screenshot = CommonMethods.takeScreenShot("passed/" + scenario.getName());
        }

        scenario.attach(screenshot, "image/png", scenario.getName());

        BaseClass.tearDown();
    }
}
