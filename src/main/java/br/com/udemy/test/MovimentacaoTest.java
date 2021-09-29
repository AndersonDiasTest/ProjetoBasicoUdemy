package br.com.udemy.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.MovimentacaoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTest extends BaseTest {

	private HomePage homePage = new HomePage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();

	@Test
	public void t1_inserirMovimentacaoComSucesso() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
		movimentacaoPage.setDataPagamento(formataData(new Date()));
		movimentacaoPage.setDescricao("Texto descri��o");
		movimentacaoPage.setInteressado("Texto interessado");
		movimentacaoPage.setValor("2000");
		movimentacaoPage.setConta("Primeira Conta alterada");
		movimentacaoPage.setSituacaoPago();
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals("Movimenta��o adicionada com sucesso!", 
				movimentacaoPage.obterTextoMsgAlertas());
	}
	// evoluir cen�rios negativos para testes parametrizaveis

	@Test
	public void t2_dataMovimentacaoObrigatoria() {
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
	public void t3_dataPagamentoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
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
	public void t4_descricaoObrigatoria() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
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
	public void t5_interessadoObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
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
	public void t6_valorObrigatorio() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
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
	public void t7_valorDeveSerNumero() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(new Date()));
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
	public void t8_inserirMovimentacaoFutura() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao("Receita");
		movimentacaoPage.setDataMovimentacao(formataData(getDataFutura()));
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

}