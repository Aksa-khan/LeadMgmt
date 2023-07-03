package com.ind.Test;

import com.ind.Base.TestBase;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



import com.ind.Page.LeadPage;
import com.ind.Page.LoginPage;

public class LoginPageTest extends TestBase {
	
LoginPage loginpage;
    
    public LoginPageTest()
    {
        super();
    }
   
//   @BeforeMethod
////   // @BeforeClass
//    public void setup() {
//       launch();
//       loginpage=new LoginPage();
////        
////         
//   }
    
    
    @Parameters("browser")
	@BeforeMethod
	//@BeforeTest
    public void setup(String browser) 
    {
        
        launch(browser);
        loginpage=new LoginPage();
        //loginpage.llogin();
//        Assert.assertEquals(loginpage.loginverify(),"Setup","Login fail");
//        System.out.println("Assertion pass");    
        }
	
    
     
//    @Test(priority=1)
//    public void verifyLoginPage() 
//    {
//        loginpage.loginverify();
//        Assert.assertEquals(loginpage.loginverify(),"Setup","Login fail");
//    }
    
    @Test(priority=1)
    public void loginT() throws InterruptedException
    {
        loginpage.login(p.getProperty("username1"),p.getProperty("Password1"));
    	//loginpage.llogin();
        
    }
    
    @AfterMethod
    //@AfterClass
    public void tearDown() 
    {
        driver.close();
    }

}
