package br.com.udemy.test;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.com.udemy.core.BaseTest;
import br.com.udemy.page.HomePage;
import br.com.udemy.page.MovimentacaoPage;

@RunWith(Parameterized.class)
public class ValidarRegrasMovimentacaoTest extends BaseTest{

	public HomePage homePage = new HomePage();
	public MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
	
	@Parameter
	public String tipoMovimentacao;
	@Parameter(value=1)
	public String datamovimentacao;
	@Parameter(value=2)
	public String dataPagamento;
	@Parameter(value=3)
	public String descricao;
	@Parameter(value=4)
	public String interessado;
	@Parameter(value=5)
	public String valor;
	@Parameter(value=6)
	public String conta;
	@Parameter(value=7)
	public String situacao;
	@Parameter(value=8)
	public String msg;

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][]{
			{"Receita", "", "20/09/2021", "Texto descri��o", "Texto interessado", "2000", "teste", "Pago", "Data da Movimenta��o � obrigat�rio"},//data movimenta��o obrigat�ria
			{"Receita", "20/09/2021", "", "Texto descri��o", "Texto interessado", "2000", "teste", "Pago", "Data do pagamento � obrigat�rio"},//data pagamento obrigat�ria
			{"Receita", "20/09/2021", "20/09/2021", "", "Texto interessado", "2000", "teste", "Pago", "Descri��o � obrigat�rio"},//descri��o obrigat�ria
			{"Receita", "20/09/2021", "20/09/2021", "Texto descri��o", "", "2000", "teste", "Pago", "Interessado � obrigat�rio"},//interessado obrigatorio
			{"Receita", "20/09/2021", "20/09/2021", "Texto descri��o", "Texto interessado", "", "teste", "Pago", "Valor � obrigat�rio\nValor deve ser um n�mero"},//valorObrigatorio
			{"Receita", "20/09/2021", "20/09/2021", "Texto descri��o", "Texto interessado", "valor em texto", "teste", "Pago", "Valor deve ser um n�mero"},//valor deve ser numero
			{"Receita", getDataFutura(), "20/09/2021", "Texto descri��o", "Texto interessado", "2000", "teste", "Pago", "Data da Movimenta��o deve ser menor ou igual � data atual"}//inserir movimenta��o futura
		});
	}
	
	@Test
	public void validarRegras() {
		homePage.clicaLink("Criar Movimenta��o");
		movimentacaoPage.setTipoMovimentacao(tipoMovimentacao);
		movimentacaoPage.setDataMovimentacao(datamovimentacao);
		movimentacaoPage.setDataPagamento(dataPagamento);
		movimentacaoPage.setDescricao(descricao);
		movimentacaoPage.setInteressado(interessado);
		movimentacaoPage.setValor(valor);
		movimentacaoPage.setConta(conta);
		if (situacao.equals("Pago")) {
			movimentacaoPage.setSituacaoPago();
		} else if (situacao.equals("Pendente")) {
			movimentacaoPage.setSituacaoPendente();
		}
		movimentacaoPage.salvarMovimentacao();

		Assert.assertEquals(msg, 
				movimentacaoPage.obterTextoMsgAlertas());
	}
}
