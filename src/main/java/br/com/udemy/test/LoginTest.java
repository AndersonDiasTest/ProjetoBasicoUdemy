package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;


public class LoginTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	
	@Before
	public void inicializa() {
		getDriver();
//		getDriver().manage().window().maximize();
		getDriver().get("https://seubarriga.wcaquino.me");
		loginPage = new LoginPage();
		homePage = new HomePage();
	}
	
	@Test
	public void login() {
		loginPage.setEmail("udemy@mail");
		loginPage.setSenha("pass");
		loginPage.submit();
		Assert.assertEquals("Bem vindo, Anderson!", homePage.obterTextoSucessoLogin());
	}
}
