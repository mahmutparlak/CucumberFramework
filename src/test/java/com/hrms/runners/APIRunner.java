package com.hrms.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", //path for .feature location
        glue = "com/hrms/APIAllSteps", //path for actual code location
        dryRun = false,      //When set as true, it will run over the feature steps and reveal unimplemented steps.
        //If there is no unimplemented steps, dry run will do noting.
        //When set as false, it will run all the features.
        tags = "@APIWorkFlow",  //can add one or more tag in tags part.
        //If wants to run more than one tag: tags = {"@smoke", "@whatever"}
        plugin = {"pretty",  //will print executed steps inside console
                "html:target/cucumber-default-reports", //generate default html report?
                "rerun:target/FailedTests.txt", //generates a txt file with failed test only.
                "json:target/cucumber.json" //generates json report. It is data storage. Json data starts
                // and ends with [] it termed json array. each result is inside
                // {} termed json object. It is based on keys and objects.
        }
)

public class APIRunner {

}
