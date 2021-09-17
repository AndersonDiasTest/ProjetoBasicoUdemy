package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.ContasPage;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;

public class ContasTest extends BaseTest{

	private LoginPage loginPage;
	private HomePage homePage;
	private ContasPage contaPage;

	@Before
	public void inicializa () {
		getDriver().get("https://seubarriga.wcaquino.me");
//		getDriver().manage().window().maximize();
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
	
	@Test
	public void alterarContaComSucesso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoTabela("Conta", "Primeira Conta", "Ações");
		contaPage.limparCampo("nome");
		contaPage.setNomeConta("Primeira Conta alterada");
		contaPage.submit();
		
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta alterada com sucesso!", contaPage.obterTextoAlertaCadastroContaSucesso());
		Assert.assertEquals("Primeira Conta alterada", contaPage.recuperaTextoTabela("Conta", "Primeira Conta alterada", "Conta"));
	}
	
	@Test
	public void inserirContaComMesmoNome() {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Primeira Conta alterada");
		contaPage.submit();
		
		Assert.assertEquals("Já existe uma conta com esse nome!", contaPage.obterTextoAlertaCadastroContaSucesso());
	}
	
}
