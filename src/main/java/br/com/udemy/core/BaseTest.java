package br.com.udemy.core;

import static br.com.udemy.core.DriverFactory.getDriver;
import static br.com.udemy.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {

	@Rule
	public TestName testName = new TestName();
	
	@After
	public void finaliza() throws IOException {
		
		TakesScreenshot print = (TakesScreenshot) getDriver();
		File arquivo = print.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(arquivo, new File("target/screenshot/" + testName.getMethodName() + ".jpg"));
		
		if (Propriedades.FECHAR_BROWSER) {
			killDriver();
		}
	}
}

//1. Inserir Conta
//2. Alterar Conta
//3. Inserir Conta com o mesmo nome
//4. Inserir Movimentação
//5. Campos obrigatórios da movimentação*
//6. Movimentação Futura
//7. Remover Movimentação
//8. Remover Conta com Movimentação
//9. Saldo das Contas
//10. Resumo Mensal