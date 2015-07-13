package com.jusoft.ehcache.tutorial.feature;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.*;

/**
 * Created by carnicj on 13/07/2015.
 */
@RunWith(Cucumber.class)
@CucumberOptions(glue = "com.jusoft.ehcache.tutorial.step.crud", features = "src/integration/resources", strict = true)
public class CrudFeature {
}
