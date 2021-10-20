package br.com.udemy.core;

public class Propriedades {

	public static boolean FECHAR_BROWSER = true;
	public static Browsers browser = Browsers.CHROME;
	
	public static String NOME_CONTA_ALTERADA = "Conta alterada" + System.nanoTime();
	
	public enum Browsers {
		CHROME,
		FIREFOX,
		EDGE
	}
}
