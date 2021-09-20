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
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("teste");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Movimentação adicionada com sucesso!", 
				movimentacaoPage.obterTextoMsgAlertas());
	}
	// evoluir cenários negativos para testes parametrizaveis

	@Test
	public void dataMovimentacaoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Data da Movimentação é obrigatório", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void dataPagamentoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Data do pagamento é obrigatório", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void descricaoObrigatoria() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Descrição é obrigatório", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void interessadoObrigatorio() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Interessado é obrigatório", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void valorObrigatorio() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Valor é obrigatório\nValor deve ser um número", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void valorDeveSerNumero() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao("17/09/2021");
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("valor com texto");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Valor deve ser um número", 
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void inserirMovimentacaoFutura() {
		homePage.clicaLink("Criar Movimentação");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(getDataFutura());
		movimentacaoPage.setDataPagamento("17/10/2021");
		movimentacaoPage.setDescricao("Texto descrição");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Data da Movimentação deve ser menor ou igual à data atual",
				movimentacaoPage.obterTextoMsgAlertas());
	}

	@Test
	public void removerMovimentação() {
		homePage.clicaLink("Resumo Mensal");
		movimentacaoPage.clicaBotaoTabelaMovimentacao("Descrição", "Texto descrição", "Ações");
		Assert.assertEquals("Movimentação removida com sucesso!",
				movimentacaoPage.obterTextoMsgAlertas());
	}
	
	
}

//Data da Movimentação é obrigatório
//Data do pagamento é obrigatório
//Descrição é obrigatório
//Interessado é obrigatório
//Valor é obrigatório
//Valor deve ser um número