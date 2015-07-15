package com.jusoft.ehcache.tutorial.feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.*;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.jusoft.ehcache.tutorial.step.crud", features = "src/integration/resources", strict = true)
public class CrudITest {
}
