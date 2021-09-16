package br.com.udemy.page;

import org.openqa.selenium.By;

import br.com.udemy.core.BasePage;

public class LoginPage extends BasePage {

	public void setUsuario(String email) {
		preenche("email", email);
	}
	
	public void setSenha(String senha) {
		preenche("senha", senha);
	}
	
	public void submit() {
		clicaRadioCheckboxEBotao(By.xpath("//*[@id='senha']/../../button"));
	}
	
	public void logar (String email, String senha) {
		setUsuario(email);
		setSenha(senha);
		submit();
	}

}
