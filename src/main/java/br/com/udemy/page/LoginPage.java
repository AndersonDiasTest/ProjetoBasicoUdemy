package br.com.udemy.page;

import static br.com.udemy.core.DriverFactory.getDriver;

import br.com.udemy.core.BasePage;

public class LoginPage extends BasePage {

	public void acessarTelaInicial() {
		getDriver().get("https://seubarriga.wcaquino.me");
	}
	
	public void setEmail(String email) {
		preenche("email", email);
	}
	
	public void setSenha(String senha) {
		preenche("senha", senha);
	}
	
	public void entrar() {
		clicaBotaoPorTexto("Entrar");
	}
	
	public void logar (String email, String senha) {
		acessarTelaInicial();
		setEmail(email);
		setSenha(senha);
		entrar();
	}

}
