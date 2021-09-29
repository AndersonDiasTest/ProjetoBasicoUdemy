package br.com.udemy.core;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.udemy.page.LoginPage;
import br.com.udemy.test.ContasTest;
import br.com.udemy.test.MovimentacaoTest;
import br.com.udemy.test.OrganizeTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContasTest.class,
	MovimentacaoTest.class,
	OrganizeTest.class
})
public class SuiteGeral {

	private static LoginPage loginPage = new LoginPage();

	@BeforeClass
	public static void inicializa() {
		loginPage.logar("udemy@mail", "pass");
	}
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
}
