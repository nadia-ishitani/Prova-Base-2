package ProvaBase2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "DadosReportIssueSuccessful.csv")

public class TestReportIssueSuccessful {
	

	//Testar mensagem "Operation successful."
		
		
	private WebDriver driver;
		
	@Before
	public void inicializa() {
		
		DriverFactory.getDriver().get("https://mantis-prova.base2.com.br/login_page.php");	
		DriverFactory.getDriver().findElement(By.name("username")).sendKeys("nadia.melo");
		DriverFactory.getDriver().findElement(By.name("password")).sendKeys("123");
		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Login']")).click();
			
		DriverFactory.getDriver().findElement(By.xpath("//a[@href='/bug_report_page.php']")).click();
		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Select Project']")).click();
	}
		
	@Rule
	public TestName testName = new TestName();
		
	@After
	public void finaliza() throws IOException {;
		TakesScreenshot ss = ((TakesScreenshot)DriverFactory.getDriver());
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File(testName.getMethodName() + ".jpg"));
			
		DriverFactory.killDriver();
	}
		
		
	@Test
	public void testeReportIssueSuccessful(@Param(name="category") String category, @Param(name="summary") String summary, @Param(name="description") String description) {
			
		WebElement element = DriverFactory.getDriver().findElement(By.name("category_id"));
		Select combo = new Select(element);
		combo.selectByVisibleText(category);
			
		DriverFactory.getDriver().findElement(By.name("summary")).sendKeys(summary);
		
		DriverFactory.getDriver().findElement(By.name("description")).sendKeys(description);
			
		DriverFactory.getDriver().findElement(By.id("report_stay")).click();
		
		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Submit Report']")).click();
			
		Assert.assertTrue(DriverFactory.getDriver().getPageSource().contains("Operation successful."));
	}
		
	
	
}
