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
import br.com.udemy.page.ResumoMensalPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizeTest extends BaseTest {

	private HomePage homePage = new HomePage();
	private ContasPage contaPage = new ContasPage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
	private ResumoMensalPage resumoPage = new ResumoMensalPage();

	@Test
	public void t1_removerContaEmUso() {
		homePage.clicaMenuListarContas();
		contaPage.clicaBotaoRemoverConta("Conta com movimentacao");
		Assert.assertEquals("Conta em uso na movimentações", contaPage.obterTextoMsgAlertas());
	}

	@Test
	public void t2_validarSaldoContas() {
		homePage.clicaLink("Home");
		Assert.assertEquals("534.00", homePage.recuperaSaldoConta("Conta para saldo"));
	}

	@Test
	public void t3_removerMovimentacao() {
		homePage.clicaLink("Resumo Mensal");
		movimentacaoPage.clicaBotaoRemoverMovimentacao("Movimentacao para exclusao");
		Assert.assertEquals("Movimentação removida com sucesso!", movimentacaoPage.obterTextoMsgAlertas());
	}

	// Não pode haver registros na tela Resumo Mensal para os testes passarem
	@Test
	public void t4_testResumoMensal() {
		homePage.clicaLink("Resumo Mensal");
		resumoPage.setAno("2010");
		resumoPage.clicaBotaoBuscar();

		Assert.assertEquals("Seu Barriga - Extrato", getDriver().getTitle());

//		try {
//			DriverFactory.getDriver().findElement(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
//			Assert.fail();
//		} catch (NoSuchElementException e) {
//			// TODO: handle exception
//		}
		java.util.List<WebElement> elementos = DriverFactory.getDriver()
				.findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		Assert.assertEquals(0, elementos.size());
	}

	@Test
	public void t5_semMovimentacoesParaRemover() {
		homePage.clicaLink("Resumo Mensal");
		resumoPage.setAno("2010");
		resumoPage.clicaBotaoBuscar();
		if (movimentacaoPage.verificaSeHaMovimentacoes()) {
			System.out.println("Há movimentações");
		} else {
			System.out.println("Não há movimentações");
		}

		Assert.assertFalse(movimentacaoPage.verificaSeHaMovimentacoes());
	}
}
