package br.com.udemy.page;

import org.openqa.selenium.By;

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
	
	public String obterTextoAlertaMovimentacaoCriadaSucesso() {
		return obterTexto(By.xpath("//body/div[@role='alert']"));
	}
}
