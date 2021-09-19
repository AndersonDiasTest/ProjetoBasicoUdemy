package br.com.udemy.page;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.udemy.core.BasePage;

public class MovimentacaoPage extends BasePage {

	public void setTipoMovimentacao(String campo, String valor) {
		selecionaOpcaoCombo(campo, valor);
	}

	public void setDataMovimentacao(String campo, String valor) {
		preenche(campo, valor);		
	}
	
	public void setDataPagamento(String campo, String valor) {
		preenche(campo, valor);		
	}

	public void setDescricao(String campo, String valor) {
		preenche(campo, valor);	
	}
	
	public void setInteressado(String campo, String valor) {
		preenche(campo, valor);	
	}
	
	public void setValor(String campo, String valor) {
		preenche(campo, valor);	
	}
	
	public void setConta(String campo, String valor) {
		selecionaOpcaoCombo(campo, valor);
	}
	
	public void setSituacao(String campo) {
		clicaRadioCheckboxEBotao(campo);
	}
	
	public void submitCriarMovimentacao() {
		clicaRadioCheckboxEBotao(By.xpath("//*[@id='status_pendente']/../../../../div/button"));
	}
	
	/****************** ações na tabela *********************/
	
	public void clicaBotaoTabelaMovimentacao(String colunaBusca, String valor, String colunaBotao) {
		//procurar a coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='tabelaExtrato']"));
		int idColuna = obterIdColuna(colunaBusca, tabela);
		
		//procurar a linha do registro
		int idLinha = obterIdLinha(valor, tabela, idColuna);
		
		//procurar a coluna do botão
		int idColunaBotao = obterIdColuna(colunaBotao, tabela);
		
		//clicar no botão
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//a[1]")).click();
	}
	
	/****************** recuperar valores *********************/
	
	public String obterTextoMsgAlertas() {
		return obterTexto(By.xpath("//body/div[@role='alert']"));
	}
	
}
