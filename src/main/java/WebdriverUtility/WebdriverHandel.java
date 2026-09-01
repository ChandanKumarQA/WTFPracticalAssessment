package WebdriverUtility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.google.common.io.Files;



public class WebdriverHandel {
	
	
	
	
	public void takeAScreenShot(WebDriver driver, String fileName) throws WebDriverException, IOException{
		JavaUtility jUtils = new JavaUtility();
		String dateTime = jUtils.generateSystemDateAndTime();
		TakesScreenshot ts = (TakesScreenshot) driver;
		Files.copy(ts.getScreenshotAs(OutputType.FILE), new File("./src/test/resources/screenshots/"+fileName+dateTime+".png"));
	}

}
