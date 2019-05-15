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




@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "DadosDeLogin.csv")

public class TesteLogin {

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("https://mantis-prova.base2.com.br/login_page.php");	
	}
	
	@Rule
	public TestName testName = new TestName();
	
	@After
	public void finaliza() throws IOException{;
		TakesScreenshot ss = ((TakesScreenshot)DriverFactory.getDriver());
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File(testName.getMethodName() + ".jpg"));
		
		DriverFactory.killDriver();
	}
	
	@Test
	public void testeLoginFail(@Param(name="username") String username, @Param(name="password") String password) {
		DriverFactory.getDriver().findElement(By.name("username")).sendKeys(username);
		DriverFactory.getDriver().findElement(By.name("password")).sendKeys(password);
		DriverFactory.getDriver().findElement(By.xpath("//input[@value='Login']")).click();
	    String texto = DriverFactory.getDriver().findElement(By.xpath("//font[@color='red']")).getText();
	    Assert.assertEquals("Your account may be disabled or blocked or the username/password you entered is incorrect.", texto);
	}

}