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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ParameterClass {

	Random rand = new Random();

	WebDriver driver = new ChromeDriver();

	Connection con;

	Statement stmt;

	ResultSet rs;

	String firstname;

	String lastName;

	Date timeStamp = new Date();

	String email;
	String address;

	String PostalCode;
	String City ; 
	
	String password = "abc123!@#"; 
	String LoginName ; 

	Boolean expectedValueForThecreatedAccount = true ; 
	
	int randomLoginNumber = rand.nextInt(32371);
	int randomLoginNumber2 = rand.nextInt(332171);
	String randomLoginNumber3 =  Integer.toString(randomLoginNumber*randomLoginNumber2);; 


	
	String[] customerNumbers = { "112", "114", "119", "121" };

	int randomCustomerNumber = rand.nextInt(customerNumbers.length);
	String theSelectedCustomer = customerNumbers[randomCustomerNumber];
	String QueryToRead = "select * from customers where customerNumber ="+theSelectedCustomer;
	String QueryToDelete = "delete from customers where customerNumber = 9999";
	String QuerytoInsert = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit) VALUES (9999, 'mahmoud', 'Schmitt', 'Carine', '40.32.2555', '54, rue Royale', NULL, 'Nantes', NULL, '44000', 'France', 1370, 21000);";
	String QueryToUpdate = "update customers set customerName='automation course' where customerNumber = 9999";


	
	
	public void TheSetupforDataBaseAndSelenium() throws SQLException {
		

		driver.get("https://automationteststore.com/index.php?rt=account/create");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "abc123");
	}
	
	
	
	
	public void ScrollAndScreenShot (int theValueWhereToStop,String screenoshotorder) throws IOException, InterruptedException {
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver ; 
		
		
		js.executeScript("window.scrollTo(0,arguments[0])",theValueWhereToStop);
		Thread.sleep(1000);
		
		TakesScreenshot ts = (TakesScreenshot) driver;

		File myScreenshotFile = ts.getScreenshotAs(OutputType.FILE);

		String filename = timeStamp.toString().replace(":", "-")+screenoshotorder;

		FileUtils.copyFile(myScreenshotFile, new File("src/screenshot/" + filename + ".jpg"));
		
		
	}
	
	
}
