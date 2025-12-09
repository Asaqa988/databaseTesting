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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	Random rand = new Random();

	WebDriver driver = new ChromeDriver();

	Connection con;

	Statement stmt;

	ResultSet rs;

	String firstname;

	String lastName;
	
	Date timeStamp = new Date();

	@BeforeTest

	public void mySetup() throws SQLException {

		driver.get("https://smartbuy-me.com/account/register");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "abc123");
	
		System.out.println(timeStamp);
	}

	@Test(priority = 2)

	public void UpdateTheData() throws SQLException, InterruptedException {

		String Query = "update customers set customerName='automation course' where customerNumber = 9999";

		stmt = con.createStatement();

		int rowUpdated = stmt.executeUpdate(Query);

		System.out.println(rowUpdated);

		Thread.sleep(2000);
	}

	@Test(priority = 3)

	public void ReadTheDataInsideTheBrowser() throws SQLException, IOException {

		String[] customerNumbers = { "112", "114", "119", "121" };

		int randomCustomerNumber = rand.nextInt(customerNumbers.length);
		String theSelectedCustomer = customerNumbers[randomCustomerNumber];
		String Query = "select * from customers where customerNumber =9999";

		stmt = con.createStatement();
		rs = stmt.executeQuery(Query);

		System.out.println(rs);

		while (rs.next()) {

			firstname = rs.getString("customerName");
			lastName = rs.getString("contactLastName");

			System.out.println(firstname);

		}

		WebElement theFirstName = driver.findElement(By.id("customer[first_name]"));

		theFirstName.sendKeys(firstname);

		WebElement theLastName = driver.findElement(By.id("customer[last_name]"));

		theLastName.sendKeys(lastName);
		
		takeTheScreenshotPlease();
		
		

	}
	
	@Test(priority = 1)
	
	public void insertintotable() throws SQLException {
		
		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (9999, 'mahmoud', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000);";

		stmt = con.createStatement();

		int rowUpdated = stmt.executeUpdate(Query);
	}
	
	@Test(priority = 4)
	
	public void deleteQuery() throws SQLException {
		
		String Query = "delete from customers where customerNumber = 9999";

		stmt = con.createStatement();

		int rowUpdated = stmt.executeUpdate(Query);
	}
	
	
	public void takeTheScreenshotPlease() throws IOException {
	TakesScreenshot ts = (TakesScreenshot) driver ; 
		
		File myScreenshotFile = ts.getScreenshotAs(OutputType.FILE);
		
		String filename = timeStamp.toString().replace(":", "-"); 
		
		FileUtils.copyFile(myScreenshotFile, new File("src/screenshot/"+filename+".jpg"));
	}

}
