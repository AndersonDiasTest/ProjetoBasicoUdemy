package br.com.udemy.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static WebDriver browser;

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		if (browser == null) {

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
		}
		return browser;
	}

	public static void killDriver() {
		if (browser != null) {
			browser.quit();
			browser = null;
		}
	}

}
