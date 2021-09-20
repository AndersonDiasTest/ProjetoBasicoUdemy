package br.com.udemy.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.MovimentacaoPage;

public class MovimentacaoTest extends BaseTest {

	private HomePage homePage = new HomePage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();

	@Test
	public void inserirMovimentacaoComSucesso() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("teste");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Movimenta��o adicionada com sucesso!", 
				movimentacaoPage.obterTextoMsgAlertas());
	}
	// evoluir cen�rios negativos para testes parametrizaveis

	@Test
	public void dataMovimentacaoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Data da Movimenta��o � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void dataPagamentoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Data do pagamento � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void descricaoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Descri��o � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void interessadoObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Interessado � obrigat�rio", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void valorObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Valor � obrigat�rio\nValor deve ser um n�mero", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void valorDeveSerNumero() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("valor com texto");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Valor deve ser um n�mero", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void inserirMovimentacaoFutura() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(getDataFutura());
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

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