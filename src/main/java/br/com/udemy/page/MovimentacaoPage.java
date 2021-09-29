package br.com.udemy.page;

import static br.com.udemy.core.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.udemy.core.BasePage;

public class MovimentacaoPage extends BasePage {

	public void setTipoMovimentacao(String valor) {
		selecionaOpcaoCombo("tipo", valor);
	}

	public void setDataMovimentacao(String valor) {
		preenche("data_transacao", valor);		
	}
	
	public void setDataPagamento(String valor) {
		preenche("data_pagamento", valor);		
	}

	public void setDescricao(String valor) {
		preenche("descricao", valor);	
	}
	
	public void setInteressado(String valor) {
		preenche("interessado", valor);	
	}
	
	public void setValor(String valor) {
		preenche("valor", valor);	
	}
	
	public void setConta(String valor) {
		selecionaOpcaoCombo("conta", valor);
	}
	
	public void setSituacaoPago() {
		clicaRadioCheckboxEBotao("status_pago");
	}
	
	public void setSituacaoPendente() {
		clicaRadioCheckboxEBotao("status_pendente");
	}
	
	public void salvarMovimentacao() {
		clicaBotaoPorTexto("Salvar");
	}
	
	/****************** ações na tabela *********************/
	
	public void clicaBotaoRemoverMovimentacao(String valorBusca) {
		WebElement celula = obterCelula("Descrição", valorBusca, "Ações", "tabelaExtrato");
		celula.findElement(By.xpath(".//a[1]")).click();
	}
	
	public boolean verificaSeHaMovimentacoes() {
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='tabelaExtrato']"));
		int idColuna = obterIdColuna("Descrição", tabela);
		
		boolean movimentacoes = verificarSeHaRegistros(tabela, idColuna);
		return movimentacoes;
	}
	
	/****************** recuperar valores *********************/
	
	//verificar necessidade de separar em alertas de sucesso e de negação
	public String obterTextoMsgAlertas() {
		return obterTexto(By.xpath("//body/div[@role='alert']"));
	}
	
}
