import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends ParameterClass {

	@BeforeTest

	public void mySetup() throws SQLException {

		TheSetupforDataBaseAndSelenium();

	}

	@BeforeMethod
	public void ThisissomethingThatRunBeforeEachTest() throws SQLException {
		stmt = con.createStatement();

	}

	@Test(priority = 1)

	public void ReadTheDatafromDataBase() throws SQLException, IOException, InterruptedException {

		rs = stmt.executeQuery(QueryToRead);

		while (rs.next()) {

			firstname = rs.getString("contactFirstName").replace(" ", "_");

			lastName = rs.getString("contactLastName").replace(" ", "_");

			email = firstname + lastName + rand.nextInt(98947) + "@gmail.com";

			address = rs.getString("addressLine1");

			PostalCode = rs.getString("postalCode");

			City = rs.getString("city");

		}

	}

	@Test(priority = 2)

	public void fillTheDatainTheSignUpPage() throws InterruptedException, IOException {
		WebElement firstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		WebElement lastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		WebElement EmailInput = driver.findElement(By.id("AccountFrm_email"));
		WebElement Address = driver.findElement(By.id("AccountFrm_address_1"));
		WebElement CountrySelect = driver.findElement(By.id("AccountFrm_country_id"));
		WebElement StateSelect = driver.findElement(By.id("AccountFrm_zone_id"));
		WebElement PostalCodeInput = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		WebElement PasswordConfirmInput = driver.findElement(By.id("AccountFrm_confirm"));
		WebElement CityInput = driver.findElement(By.id("AccountFrm_city"));
		WebElement AcceptTermsAndConditions = driver.findElement(By.id("AccountFrm_agree"));

		WebElement CountinueButton = driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		firstNameInput.sendKeys(firstname);
		lastNameInput.sendKeys(lastName);
		EmailInput.sendKeys(email);
		Address.sendKeys(address);
		
		ScrollAndScreenShot(100,"1"); 

		// select to country
		Select mySelectorForThecountry = new Select(CountrySelect);
		mySelectorForThecountry.selectByIndex(3);
		Thread.sleep(2000);

		// select to state
		Select mySelectorForTheState = new Select(StateSelect);
		mySelectorForTheState.selectByIndex(6);

		PostalCodeInput.sendKeys(PostalCode);
		CityInput.sendKeys(City);
		LoginName = firstname + lastName+randomLoginNumber3;
		
		ScrollAndScreenShot(600,"2"); 


		LoginNameInput.sendKeys(LoginName);
		PasswordInput.sendKeys(password);
		PasswordConfirmInput.sendKeys(password);
		AcceptTermsAndConditions.click();
		
		ScrollAndScreenShot(1000,"3"); 

		CountinueButton.click();
		Thread.sleep(2000);

		Boolean ActualValue = driver.getPageSource().contains("Welcome back");

		Assert.assertEquals(ActualValue, expectedValueForThecreatedAccount);
		
	}

	@Test(priority = 2, enabled = false)

	public void insertintotable() throws SQLException {

		stmt.executeUpdate(QuerytoInsert);
	}

	@Test(priority = 3, enabled = false)

	public void UpdateTheData() throws SQLException, InterruptedException {

		int rowUpdated = stmt.executeUpdate(QueryToUpdate);

	}

	@Test(priority = 4, enabled = false)

	public void deleteQuery() throws SQLException {

		int rowUpdated = stmt.executeUpdate(QueryToDelete);
	}

}
