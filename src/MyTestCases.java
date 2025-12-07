import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {
	
	
	
	WebDriver driver = new ChromeDriver(); 
	
	Connection con ; 
	
	Statement stmt ;
	
	ResultSet rs ; 
	
	String firstname ; 
	
	String lastName ; 
	
	@BeforeTest
	
public void mySetup() throws SQLException {
		
		driver.get("https://smartbuy-me.com/account/register");
		
		driver.manage().window().maximize(); 
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","abc123");
	}
	
	
	
	
	
	@Test(priority = 1)
	
	public void UpdateTheData() throws SQLException, InterruptedException {
		
		String Query = "update customers set customerName='abu soso' where customerNumber = 103"; 
		
		stmt = con.createStatement(); 
		
		int rowUpdated = stmt.executeUpdate(Query); 
		
		System.out.println(rowUpdated);
		
		Thread.sleep(2000);
	}
	
	
	
	@Test(priority = 2)
	
	public void ReadTheDataInsideTheBrowser() throws SQLException {
		
		
		
		String Query = "select * from customers where customerNumber = 103" ; 
		
		stmt=con.createStatement(); 
		rs = stmt.executeQuery(Query); 
		
		System.out.println(rs);
		
		
		while(rs.next()) {
			
			firstname = rs.getString("customerName"); 
		lastName= rs.getString("contactLastName"); 


		}
		
		
		
		
		
		
		
		WebElement theFirstName =  driver.findElement(By.id("customer[first_name]"));
		
		theFirstName.sendKeys(firstname);
		
		WebElement theLastName = driver.findElement(By.id("customer[last_name]"));
		
		theLastName.sendKeys(lastName);
		
		
	}
	
	
	
	

}
