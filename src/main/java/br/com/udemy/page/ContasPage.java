package br.com.udemy.page;

import org.openqa.selenium.By;

import br.com.udemy.core.BasePage;

public class ContasPage extends BasePage {
	
	public void setNomeConta(String nomeConta) {
		preenche("nome", nomeConta);
	}

	public void submit() {
		clicaRadioCheckboxEBotao(By.xpath("//input[@id='nome']/../../../div[2]"));
	}
	
	public String obterTextoAlertaCadastroContaSucesso() {
		return obterTexto(By.xpath("//body/div[@role='alert']"));
	}
	
	public void teste() {
		
	}
}
