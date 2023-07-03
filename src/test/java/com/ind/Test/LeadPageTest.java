package com.ind.Test;

import org.testng.Assert;
import com.ind.Base.TestBase;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import com.ind.Page.LeadPage;
import com.ind.Page.LoginPage;
import com.ind.Utilities.ExcelUtilities;
import com.ind.Utilities.JiraPolicy;

public class LeadPageTest extends TestBase {
	
	LoginPage loginpage;
	LeadPage leadpage;
	String websheet="Lead";
	
	public LeadPageTest() {
		super();
	}
	
//	@BeforeMethod
//	public void login() throws InterruptedException
//	{
//		
//		launch();
//		loginpage=new LoginPage();
//	    leadpage=new LeadPage();
//	    loginpage.login(p.getProperty("username1"),p.getProperty("Password1"));
//	   
//	}
	
	@Parameters("browser")
	//@BeforeMethod
	@BeforeTest
    public void login(String browser) throws InterruptedException
    {
        
        launch(browser);
        loginpage=new LoginPage();
        leadpage=new LeadPage();
        loginpage.login(p.getProperty("username1"),p.getProperty("Password1"));
        //loginpage.llogin();
//        Assert.assertEquals(loginpage.loginverify(),"Setup","Login fail");
//        System.out.println("Assertion pass");    
        }
	
	@DataProvider
	public Object getLead() {
		Object[][] obj1=ExcelUtilities.getExcel(websheet);
		return obj1;
	}
	
@JiraPolicy(logTicketReady = true)
	@Test(dataProvider="getLead")
	public void createlead(String FirstName,String LastName,String Company,String Street,String City,String State,String Zipcode,String Country ) throws InterruptedException, IOException
	{
	
		leadpage.newlead(FirstName, LastName, Company, Street, City, State, Zipcode, Country);
		 String fullname="Mr. "+FirstName+" "+LastName;
		 Assert.assertEquals(leadpage.verifyedlead(),fullname,"Lead creation is failed");
			System.out.println("Assertion pass");
	
	}


}
