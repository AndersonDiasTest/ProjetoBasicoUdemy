package br.com.udemy.page;

import org.openqa.selenium.By;

import br.com.udemy.core.BasePage;

public class ResumoMensalPage extends BasePage {

	public void setAno(String ano) {
		selecionaOpcaoCombo("ano", ano);		
	}

	public void clicaBotaoBuscar() {
		clicaBotao(By.xpath("//select[@id='ano']/../../input"));
	}

}
