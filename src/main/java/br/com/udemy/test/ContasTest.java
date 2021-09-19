package br.com.udemy.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.ContasPage;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;

public class ContasTest extends BaseTest{

	private LoginPage loginPage = new LoginPage();
	private HomePage homePage = new HomePage();
	private ContasPage contaPage = new ContasPage();

	@Before
	public void inicializa () {
//		getDriver().manage().window().maximize();
		loginPage.logar("udemy@mail", "pass");
	}
	
	@Test
	public void inserirContaComSucesso () {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Primeira Conta");
		contaPage.submit();
		
		Assert.assertEquals("Primeira Conta", contaPage.recuperaTextoTabela("Conta", "Primeira Conta", "Conta"));
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta adicionada com sucesso!", contaPage.obterTextoMsgAlertas());
	}
	
	@Test
	public void alterarContaComSucesso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoAlterarConta("Primeira Conta");
		contaPage.setNomeConta("Primeira Conta alterada");
		contaPage.submit();
		
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta alterada com sucesso!", contaPage.obterTextoMsgAlertas());
		Assert.assertEquals("Primeira Conta alterada", contaPage.recuperaTextoTabela("Conta", "Primeira Conta alterada", "Conta"));
	}
	
	@Test
	public void inserirContaComMesmoNome() {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Primeira Conta alterada");
		contaPage.submit();
		
		Assert.assertEquals("Já existe uma conta com esse nome!", 
				contaPage.obterTextoMsgAlertas());
	}
	
	@Test
	public void removerContaEmUso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoRemoverConta("Primeira Conta alterada");
		Assert.assertEquals("Conta em uso na movimentações", 
				contaPage.obterTextoMsgAlertas());
	}
	
	@Test
	public void validarSaldoContas() {
		Assert.assertEquals("2.00", 
				contaPage.recuperaTextoTabelaSaldo("Conta", "Primeira Conta alterada", "Saldo"));
	}
	
}
