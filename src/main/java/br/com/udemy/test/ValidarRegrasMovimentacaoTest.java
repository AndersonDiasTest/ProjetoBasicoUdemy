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
			{"Receita", "", "20/09/2021", "Texto descrição", "Texto interessado", "2000", "teste", "Pago", "Data da Movimentação é obrigatório"},//data movimentação obrigatória
			{"Receita", "20/09/2021", "", "Texto descrição", "Texto interessado", "2000", "teste", "Pago", "Data do pagamento é obrigatório"},//data pagamento obrigatória
			{"Receita", "20/09/2021", "20/09/2021", "", "Texto interessado", "2000", "teste", "Pago", "Descrição é obrigatório"},//descrição obrigatória
			{"Receita", "20/09/2021", "20/09/2021", "Texto descrição", "", "2000", "teste", "Pago", "Interessado é obrigatório"},//interessado obrigatorio
			{"Receita", "20/09/2021", "20/09/2021", "Texto descrição", "Texto interessado", "", "teste", "Pago", "Valor é obrigatório\nValor deve ser um número"},//valorObrigatorio
			{"Receita", "20/09/2021", "20/09/2021", "Texto descrição", "Texto interessado", "valor em texto", "teste", "Pago", "Valor deve ser um número"},//valor deve ser numero
			{"Receita", getDataFutura(), "20/09/2021", "Texto descrição", "Texto interessado", "2000", "teste", "Pago", "Data da Movimentação deve ser menor ou igual à data atual"}//inserir movimentação futura
		});
	}
	
	@Test
	public void validarRegras() {
		homePage.clicaLink("Criar Movimentação");
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
