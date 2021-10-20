package br.com.udemy.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

//	private static WebDriver browser;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}
	};

	private DriverFactory() {
	}
	
	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
			WebDriver browser = null;
			switch (Propriedades.browser) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				browser = new ChromeDriver();
				break;

			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				browser = new FirefoxDriver();
				break;

			case EDGE:
				WebDriverManager.edgedriver().setup();
				browser = new EdgeDriver();
				break;

			default:
				break;
			}
		return browser;
	}

	public static void killDriver() {
		WebDriver browser = getDriver();
		if (browser != null) {
			browser.quit();
			browser = null;
		}
		if(threadDriver != null) {
			threadDriver.remove();
		}
	}

}
