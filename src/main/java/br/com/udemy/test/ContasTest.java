package br.com.udemy.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.ContasPage;
import br.com.udemy.page.HomePage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContasTest extends BaseTest{

	private HomePage homePage = new HomePage();
	private ContasPage contaPage = new ContasPage();
	
//	@Before
//	public void inicializaMassa() {
//		homePage.clicaReset();
//	}
	
	@Test
	public void t1_inserirContaComSucesso () {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Primeira Conta");
		contaPage.salvar();
		
		Assert.assertEquals("Primeira Conta", contaPage.recuperaTextoTabelaContas("Primeira Conta"));
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta adicionada com sucesso!", contaPage.obterTextoMsgAlertas());
	}
	
	@Test
	public void t2_alterarContaComSucesso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoAlterarConta("Conta para alterar");
		contaPage.setNomeConta("Conta alterada");
		contaPage.salvar();
		
		Assert.assertEquals("https://seubarriga.wcaquino.me/salvarConta", contaPage.recuperaUrlAtual());
		Assert.assertEquals("Conta alterada com sucesso!", contaPage.obterTextoMsgAlertas());
		Assert.assertEquals("Primeira Conta alterada", contaPage.recuperaTextoTabelaContas("Primeira Conta alterada"));
	}
	
	@Test
	public void t3_inserirContaComMesmoNome() {
		homePage.clicaMenuAdicionarContas();
		contaPage.setNomeConta("Conta mesmo nome");
		contaPage.salvar();
		
		Assert.assertEquals("Já existe uma conta com esse nome!", 
				contaPage.obterTextoMsgAlertas());
	}
	

	
}
