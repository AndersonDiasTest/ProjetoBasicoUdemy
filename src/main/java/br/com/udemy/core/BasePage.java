package br.com.udemy.core;

import static br.com.udemy.core.DriverFactory.getDriver;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

/****************** ações no formulário *********************/
	
	public void preenche(String campo, String texto) {
		getDriver().findElement(By.id(campo)).clear();
		getDriver().findElement(By.id(campo)).sendKeys(texto);
	}

	public void clicaRadioCheckboxEBotao(By by) {
		getDriver().findElement(by).click();		
	}
	
	public void clicaRadioCheckboxEBotao(String campo) {
		clicaRadioCheckboxEBotao(By.id(campo));		
	}

	public void selecionaOpcaoCombo(String campo, String valorVisivel) {
		WebElement elemento =  getDriver().findElement(By.id(campo));
		Select combo = new Select(elemento);
		combo.selectByVisibleText(valorVisivel);
		//combo.selectByValue("superior"); (necessário refatorar metodo)
		//combo.selectByIndex(7); (necessário refatorar metodo)
		//combo.deselectByVisibleText("O que eh esporte?");
	}
	
	public void clicaBotao(String botao) {
		getDriver().findElement(By.id(botao)).click();
	}
	
	public void clicaLink(String link) {
		getDriver().findElement(By.linkText(link)).click();	
	}

	public String recuperaUrlAtual() {
		return getDriver().getCurrentUrl();
	}
	
	public void clicaBotaoPorTexto(String texto) {
		clicaRadioCheckboxEBotao(By.xpath("//button[.='" + texto + "']"));
	}
	
	/****************** busca valores ***********************/
	
	public String recuperaValorCampo(String campo) {
		return getDriver().findElement(By.id(campo)).getAttribute("value");
	}

	public boolean estaSelecionado(String item) {
		return getDriver().findElement(By.id(item)).isSelected();
	}

	public String validaRetornoCadastro(String campo) {
		return getDriver().findElement(By.id(campo)).getText();
	}
	
	public String recuperaOpcaoUnicaComboSelecionada(String campo) {
		WebElement elemento =  getDriver().findElement(By.id(campo));
		Select combo = new Select(elemento);
		return combo.getFirstSelectedOption().getText();
	}
	
	public boolean recuperaOpcaoMultiplaComboSelecionada(String campo, String valor) {
		WebElement elemento =  getDriver().findElement(By.id(campo));
		Select combo = new Select(elemento);
		List<WebElement> opcoes = combo.getAllSelectedOptions();
		
		boolean resultado = false;
		for(WebElement option: opcoes) {
			if(option.getText().equals(valor)) {
				resultado = true;
			}
		}
		return resultado;
	}
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}

	public String obterTexto(String campo) {
		return obterTexto(By.id(campo));
	}

	/****************** ações de alerta *********************/
	
	public void passarParaAlerta() {
		Alert alerta = getDriver().switchTo().alert();
		alerta.getText();
	}
	
	public String obterTextoAlerta() {
		Alert alerta = getDriver().switchTo().alert();
		return alerta.getText();
	}
	
	//tem funcionado para todos os alertas

	
	/****************** ações na tabela *********************/
	
	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela) {
		//procurar a coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='" + idTabela + "']"));
		int idColuna = obterIdColuna(colunaBusca, tabela);
		
		//procurar a linha do registro
		int idLinha = obterIdLinha(valor, tabela, idColuna);
		
		//procurar a coluna do botão
		int idColunaBotao = obterIdColuna(colunaBotao, tabela);
		
		//retornar celula
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula;
	}
	
	public String recuperaTextoTabela(String colunaBusca, String valor, String colunaBotao) {
		//procurar a coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='tabelaContas']"));
		int idColuna = obterIdColuna(colunaBusca, tabela);
		
		//procurar a linha do registro
		int idLinha = obterIdLinha(valor, tabela, idColuna);
		
		//procurar a coluna do botão
		int idColunaBotao = obterIdColuna(colunaBotao, tabela);
		
		//clicar no botão
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula.getText();
		
	}
	
	public String recuperaTextoTabelaSaldo(String colunaBusca, String valor, String colunaBotao) {
		//procurar a coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//table[@id='tabelaSaldo']"));
		int idColuna = obterIdColuna(colunaBusca, tabela);
		
		//procurar a linha do registro
		int idLinha = obterIdLinha(valor, tabela, idColuna);
		
		//procurar a coluna do botão
		int idColunaBotao = obterIdColuna(colunaBotao, tabela);
		
		//clicar no botão
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula.getText();
		
	}

	protected int obterIdLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for (int i = 0; i < linhas.size(); ++i) {
//			String variavel = linhas.get(i).getText();
			if (linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIdColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		
		int idColuna = -1;
		for (int i = 0; i < colunas.size(); ++i) {
//			String variavel = colunas.get(i).getText();
			if (colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	
}
