package reports;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Reports{
public WebDriver driver;
	@Test
	public void script1() throws InterruptedException, IOException
	{
		
	
ExtentHtmlReporter fil=new ExtentHtmlReporter("./extentReports/Reprt.html");
ExtentReports rep=new ExtentReports();
rep.attachReporter(fil);
ExtentTest wrk = rep.createTest("sname");
System.setProperty("webdriver.gecko.driver","./softwares/geckodriver.exe");
driver = new FirefoxDriver();
driver.manage().window().maximize();
wrk.log(Status.INFO,"browser launched");
Thread.sleep(2000);
driver.get("");
wrk.log(Status.INFO,"website");
String title = driver.getTitle();
if(title.equals("TIGER"))
{
	wrk.log(Status.PASS,"You win");
	}
else
{
	wrk.log(Status.FAIL,"failure muscle");
	wrk.addScreenCaptureFromPath(scrnsht());
	
	}
driver.quit();
rep.flush();

}
	public String scrnsht() throws IOException
	{
		String s1="./screenshot/tiger.jpeg";
		TakesScreenshot scrn=(TakesScreenshot)driver;
		File sht = scrn.getScreenshotAs(OutputType.FILE);
		File t = new File(s1);
		FileHandler.copy(sht, t);
		return "."+s1;
		
		
	}
	
}