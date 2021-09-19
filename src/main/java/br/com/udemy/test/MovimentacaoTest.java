package br.com.udemy.test;

import static br.com.udemy.core.DriverFactory.getDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

	private static String getDataFutura() {
		Date data = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		gc.set(Calendar.DATE, gc.get(Calendar.DATE) + 1);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(gc.getTime());
	}

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

		Assert.assertEquals("Movimenta��o adicionada com sucesso!", 
				movimentacaoPage.obterTextoMsgAlertas());
	}
	// evoluir cen�rios negativos para testes parametrizaveis

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

		Assert.assertEquals("Data da Movimenta��o � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
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

		Assert.assertEquals("Data do pagamento � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
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

		Assert.assertEquals("Descri��o � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
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

		Assert.assertEquals("Interessado � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
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

		Assert.assertEquals("Valor � obrigat�rio\nValor deve ser um n�mero", 
				movimentacaoPage.obterTextoMsgAlertas());
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

		Assert.assertEquals("Valor deve ser um n�mero", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void inserirMovimentacaoFutura() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("tipo", "Receita");
		movimentacaoPage.setDataMovimentacao("data_transacao", getDataFutura());
		movimentacaoPage.setDataPagamento("data_pagamento", "17/10/2021");
		movimentacaoPage.setDescricao("descricao", "Texto descri��o");
		movimentacaoPage.setInteressado("interessado", "Texto interessado");
		movimentacaoPage.setValor("valor", "2000");
		movimentacaoPage.setConta("conta", "Primeira Conta alterada");
		movimentacaoPage.setSituacao("status_pago");
		movimentacaoPage.submitCriarMovimentacao();

		Assert.assertEquals("Data da Movimenta��o deve ser menor ou igual � data atual",
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void removerMovimenta��o() {
		homePage.clicaLink("Resumo Mensal");
		movimentacaoPage.clicaBotaoTabelaMovimentacao("Descri��o", "Texto descri��o", "A��es");
		Assert.assertEquals("Movimenta��o removida com sucesso!",
				movimentacaoPage.obterTextoMsgAlertas());
	}
	
	
}

//Data da Movimenta��o � obrigat�rio
//Data do pagamento � obrigat�rio
//Descri��o � obrigat�rio
//Interessado � obrigat�rio
//Valor � obrigat�rio
//Valor deve ser um n�mero