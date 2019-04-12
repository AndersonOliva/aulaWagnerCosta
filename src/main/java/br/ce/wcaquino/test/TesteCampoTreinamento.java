package br.ce.wcaquino.test;
import static br.ce.wcaquino.core.DriverFactory.getDriver;
import static br.ce.wcaquino.core.DriverFactory.killDriver;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.DSL;

public class TesteCampoTreinamento {
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file://C:/Users/aoliva/Desktop/AUTOMA%C3%87%C3%83O/Cursos/componentes.html");
		dsl = new DSL();
	}
	
	@After
	public void finalizar() {
		killDriver();
	}
	
	@Test
	public void testeTextField() {
		dsl.escreve("elementosForm:nome", "Teste de Escrita");
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escreve("elementosForm:sugestoes", "Teste\n\teste conteudo");
		Assert.assertEquals("Teste\n\teste conteudo", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		Assert.assertEquals("Mestrado", "elementosForm:escolaridade");
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		//Verificar se qtde de item bate com valor total passado
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for(WebElement option:options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esportes?");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);

		List<WebElement> allSelecteOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelecteOptions.size());
		//Desmarcar o valor selecionado
		combo.deselectByVisibleText("Corrida");
		allSelecteOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelecteOptions.size());
	}
	
	@Test
	public void deveInteragirComBotoes() {
		WebElement botao = getDriver().findElement(By.id("buttonSimple"));
		botao.click();
		
		Assert.assertEquals("Obrigado!", botao.getAttribute("Value"));
	}
	
	@Test
	@Ignore
	//Teste sem valida��o de assert - Ignore essa execu��o
	public void deveInteragirComLinks() {
		getDriver().findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", getDriver().findElement(By.id("resultado")).getText());
	}
	
	@Test
	public void deveBuscarTextoNaPagina() {
		//Pega o cote�do do body e verificar se tem essa string dentro
		Assert.assertTrue(getDriver().findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		//Pega o cote�do do h3 e verificar se tem essa string dentro
		Assert.assertEquals("Campo de Treinamento", getDriver().findElement(By.tagName("h3")).getText());

		//Pega o cote�do da classe
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", getDriver().findElement(By.className("facilAchar")).getText());
	}
	
	@Test
	public void testTextFieldDuplo() {
		dsl.escreve("elementosForm:nome", "Wagner");
		Assert.assertEquals("Wagner", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escreve("elementosForm:nome", "Julio");
		Assert.assertEquals("Julio", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
//		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
		
}
