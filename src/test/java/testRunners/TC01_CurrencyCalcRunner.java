package testRunners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features/TestSuite_01"},
        glue = {"stepDefinitions", "AppHooks"},
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)

public class TC01_CurrencyCalcRunner {
    @BeforeClass
    public static void beforeRun() throws IOException {
        //Create Directory if not exist
        Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/output/screenshots/"));
        //delete all failed scenario screenshot from screenshots folder
        FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/report/screenshots/"));
    }

}