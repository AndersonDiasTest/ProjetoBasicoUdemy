package br.com.udemy.core;

import static br.com.udemy.core.DriverFactory.getDriver;
import static br.com.udemy.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void finaliza() throws IOException {
		
		TakesScreenshot print = (TakesScreenshot) getDriver();
		File arquivo = print.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target/screenshot/" + testName.getMethodName() + ".jpg"));
		
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}
