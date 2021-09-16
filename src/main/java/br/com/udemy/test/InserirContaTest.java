package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.ContasPage;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;

public class InserirContaTest extends BaseTest{

	private LoginPage loginPage;
	private HomePage homePage;
	private ContasPage contaPage;

	@Before
	public void inicializa () {
		getDriver().get("https://seubarriga.wcaquino.me");
		getDriver().manage().window().maximize();
		loginPage = new LoginPage();
		homePage = new HomePage();
		contaPage = new ContasPage();
		loginPage.logar("udemy@mail", "pass");
	}
	
	@Test
	public void inserirContaComSucesso () {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Primeira Conta");
		contaPage.submit();
		
		Assert.assertEquals("Primeira Conta", contaPage.recuperaTextoTabela("Conta", "Primeira Conta", "Conta"));
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta adicionada com sucesso!", contaPage.obterTextoAlertaCadastroContaSucesso());
		
	}
}
