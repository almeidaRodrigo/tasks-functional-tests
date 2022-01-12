package br.com.almeidasdev.tasks.functional;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TasksTest {
	
	private RemoteWebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriverManager.chromedriver().setup();
		
		DesiredCapabilities cap = new DesiredCapabilities("chrome","96", Platform.LINUX);
		
		//ChromeDriver driver = new ChromeDriver();
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444"), cap );
		driver.navigate().to("http://192.168.1.4:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		RemoteWebDriver driver = acessarAplicacao();
		
		try {
			//clicar em AddTodo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever tarefa
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/12/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Success!", message);
		} finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		RemoteWebDriver driver = acessarAplicacao();
		
		try {
			//clicar em AddTodo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/12/2022");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the task description", message);
		} finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		RemoteWebDriver driver = acessarAplicacao();
		
		try {
			//clicar em AddTodo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever tarefa
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/12/2020");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Due date must not be in past", message);
		} finally {
			//fechar browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		RemoteWebDriver driver = acessarAplicacao();
		
		try {
			//clicar em AddTodo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever tarefa
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			assertEquals("Fill the due date", message);
		} finally {
			//fechar browser
			driver.quit();
		}
	}


	
}
