package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.udemy.core.BaseTest;
import br.com.udemy.core.DriverFactory;
import br.com.udemy.page.ContasPage;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.MovimentacaoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizeTest extends BaseTest {

	private HomePage homePage = new HomePage();
	private ContasPage contaPage = new ContasPage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
	
	@Test
	public void t1_removerContaEmUso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoRemoverConta("Primeira Conta alterada");
		Assert.assertEquals("Conta em uso na movimentações", 
				contaPage.obterTextoMsgAlertas());
	}
	
	@Test
	public void t2_validarSaldoContas() {
		homePage.clicaLink("Home");
		Assert.assertEquals("2000.00", 
				homePage.recuperaSaldoConta("Primeira Conta alterada"));
	}
	
	@Test
	public void t3_removerMovimentacao() {
		homePage.clicaLink("Resumo Mensal");
		movimentacaoPage.clicaBotaoRemoverMovimentacao("Texto descrição");
		Assert.assertEquals("Movimentação removida com sucesso!",
				movimentacaoPage.obterTextoMsgAlertas());
	}
	
	//N�o pode haver registros na tela Resumo Mensal para os testes passarem
	@Test
	public void t4_testResumoMensal() {
		homePage.clicaLink("Resumo Mensal");
		
		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());
		
//		try {
//			DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
//			Assert.fail();
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//		}
		java.util.List<WebElement> elementos = DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		Assert.assertEquals(0, elementos.size());
	}
	
	@Test
	public void t5_semMovimentacoesParaRemover() {
		homePage.clicaLink("Resumo Mensal");
		if (movimentacaoPage.verificaSeHaMovimentacoes()) {
			System.out.println("Há movimentações");
		} else {
			System.out.println("Não há movimentações");
		}
		
		Assert.assertFalse(movimentacaoPage.verificaSeHaMovimentacoes());
	}
}
