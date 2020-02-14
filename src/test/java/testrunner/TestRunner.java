package testrunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "html:target/cucumber-reports/CucumberReport.html",
                "junit:target/junit-reports/report.xml"},
        features = "src/test/resources/features",
        glue = {"stepdefinitions"},
        tags = {"not @WIP"}
)
public class TestRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestRunner.class);

    @BeforeClass
    public static void setup() {
        //Helper functionality to print out all RestAssured requests and it's methods
        final List<Filter> filters = new ArrayList<>();
        filters.add((requestSpec, responseSpec, ctx) -> {
            final Response next = ctx.next(requestSpec, responseSpec);
            LOGGER.info("{}: {} -> {}", requestSpec.getMethod(), requestSpec.getURI(), next.getStatusCode());
            return next;
        });
        RestAssured.filters(filters);
    }
}

