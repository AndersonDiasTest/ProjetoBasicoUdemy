package br.com.udemy.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.udemy.core.DriverFactory;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;
import br.com.udemy.test.OrganizeTest;

@RunWith(Suite.class)
@SuiteClasses({ /*ContasTest.class, MovimentacaoTest.class,*/ OrganizeTest.class })
public class SuiteGeral {
	
	@BeforeClass
	public static void inicializaMassa() {
		LoginPage loginPage = new LoginPage();
		loginPage.logar("udemy@mail", "pass");
		HomePage homePage = new HomePage();
		homePage.clicaReset();
		homePage.logoff();
		DriverFactory.killDriver();
	}

	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
}
