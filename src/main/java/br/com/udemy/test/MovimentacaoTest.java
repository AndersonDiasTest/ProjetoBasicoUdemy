package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.LoginPage;
import br.com.udemy.page.MovimentacaoPage;

public class MovimentacaoTest extends BaseTest {

	private LoginPage loginPage;
	private HomePage homePage;
	private MovimentacaoPage movimentacaoPage;

	@Before
	public void inicializa() {
		getDriver().get("https://seubarriga.wcaquino.me");
		homePage = new HomePage();
		movimentacaoPage = new MovimentacaoPage();
		loginPage = new LoginPage();
		loginPage.logar("udemy@mail", "pass");
	}

	@Test
	public void inserirMovimentacaoComSucesso() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Movimenta��o adicionada com sucesso!", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	//evoluir cen�rios negativos para testes parametrizaveis
	
	@Test
	public void dataMovimentacaoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Data da Movimenta��o � obrigat�rio", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void dataPagamentoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Data do pagamento � obrigat�rio", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void descricaoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Descri��o � obrigat�rio", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void interessadoObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Interessado � obrigat�rio", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void valorObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Valor � obrigat�rio\nValor deve ser um n�mero", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void valorDeveSerNumero() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "valor com texto");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Valor deve ser um n�mero", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
}



//Data da Movimenta��o � obrigat�rio
//Data do pagamento � obrigat�rio
//Descri��o � obrigat�rio
//Interessado � obrigat�rio
//Valor � obrigat�rio
//Valor deve ser um n�mero