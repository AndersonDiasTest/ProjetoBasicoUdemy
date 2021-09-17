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
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Movimentação adicionada com sucesso!", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	//evoluir cenários negativos para testes parametrizaveis
	
	@Test
	public void dataMovimentacaoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Data da Movimentação é obrigatório", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void dataPagamentoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Data do pagamento é obrigatório", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void descricaoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Descrição é obrigatório", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void interessadoObrigatorio() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Interessado é obrigatório", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void valorObrigatorio() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Valor é obrigatório\nValor deve ser um número", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
	
	@Test
	public void valorDeveSerNumero() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", "17/09/2021");
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descrição");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "valor com texto");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();
		
		Assert.assertEquals("Valor deve ser um número", movimentacaoPage.obterTextoAlertaMovimentacaoCriadaSucesso());
	}
}



//Data da Movimentação é obrigatório
//Data do pagamento é obrigatório
//Descrição é obrigatório
//Interessado é obrigatório
//Valor é obrigatório
//Valor deve ser um número