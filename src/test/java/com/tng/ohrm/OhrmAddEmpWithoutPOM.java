package com.tng.ohrm;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.wd.util.DriverFactory;

public class OhrmAddEmpWithoutPOM {
	WebDriver driver;

	@BeforeClass
	public void openApplication() {
		driver = DriverFactory.getDriverFor("chrome");
		// driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		driver.get("https://qtpsudhakar-trials641.orangehrmlive.com/auth/login");
	}

	@Test
	public void login() {
		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		driver.findElement(By.id("txtPassword")).sendKeys("qtpsudhakar");
		driver.findElement(By.id("btnLogin")).click();
	}

	@Test(dependsOnMethods="login")
	public void addEmployee() {
		driver.findElement(By.linkText("PIM")).click();
		driver.findElement(By.linkText("Add Employee")).click();
		driver.findElement(By.id("firstName")).sendKeys("selenium");
		driver.findElement(By.id("lastName")).sendKeys("hq");
		driver.findElement(By
			.xpath("//div[@id='location_inputfileddiv']//input[@class='select-dropdown']")).click();
		
		WebElement lstItem = driver.findElement(By
				.xpath("//span[contains(text(),'Canadian Development Center')]"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", lstItem);
		lstItem.click();
		driver.findElement(By.linkText("SAVE")).click();
	}
	
	@AfterClass
	public void closeApplication() {
		driver.quit();
	}
}









