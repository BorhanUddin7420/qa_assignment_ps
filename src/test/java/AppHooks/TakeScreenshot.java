package AppHooks;


import com.google.common.io.Files;
import com.qa.managers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class TakeScreenshot extends DriverManager {


    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed()) {

            String screenshotName = scenario.getName().replaceAll(" ", "_");
            byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(sourcePath, "image/png", screenshotName);

        }
    }


    @After(order = 0)
    public void afterScenarioLocal(Scenario scenario) {
        if (scenario.isFailed()) {

            String screenshotName = scenario.getName().replaceAll(" ", "_");
            try {
                //Tham takes a screenshot from the driver at save it to the specified location
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);


                //Building up the destination path for the screenshot to save
                //Also make sure to create a folder 'screenshots' with in the cucumber-report folder
                File destinationPath = new File(System.getProperty("I.dir") + "/report/screenshots/" + screenshotName + ".png");

                //Copy taken screenshot from source location to destination location
                Files.copy(scrFile, destinationPath);

            } catch (IOException e) {
            }
        }
    }

}
