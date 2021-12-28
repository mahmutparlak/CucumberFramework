package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
        glue = "com/hrms/stepdefinirions",
        dryRun = false,
        tags = "@smoke",
        plugin = {"Pretty",
                "html:target/cucumber-default-reports",
                "rerun:target/FailedTest.txt",
                "json:target/cucumber.json"
        }
)
public class SmokeRunner {
}
