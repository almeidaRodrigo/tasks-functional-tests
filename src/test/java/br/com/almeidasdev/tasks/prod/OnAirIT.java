package br.com.almeidasdev.tasks.prod;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OnAirIT {

	@Test
	public void onAir() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities("chrome","96", Platform.LINUX);
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.4:4444"), cap );
		
		try {
			driver.navigate().to("http://192.168.1.4:9999/tasks/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			String version = driver.findElement(By.id("version")).getText();
			assertTrue(version.startsWith("build"));
		}finally {
			driver.quit();
		}
	}
	
}
