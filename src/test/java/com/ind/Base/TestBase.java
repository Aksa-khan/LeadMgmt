package com.ind.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.annotations.Parameters;
import java.security.PublicKey;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import net.rcarz.jiraclient.Issue;
import com.ind.Utilities.JiraPolicy;
import com.ind.Utilities.JiraServiceProvider;


import io.github.bonigarcia.wdm.WebDriverManager;


public class TestBase {
	
	static XSSFReader reader;
    public static WebDriver driver;
    public static Properties p;
    public  static EventFiringWebDriver e_driver;
	
	public TestBase()
    {
        try {
            p=new Properties();
        //    FileInputStream f=new FileInputStream(System.getProperty("user.dir")+"C:\\Users\\Hp\\Desktop\\Salesforce Automation jan2022\\Automation_Salesforce-main\\Payroll360\\src\\main\\java\\Properties\\Config.properties" );
            
            FileInputStream f=new FileInputStream("C:\\Users\\Hp\\Office Workspace\\IndusindBnk\\src\\test\\java\\com\\ind\\Configuration\\Config.properties" );

            p.load(f);
            ;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
        
        
    }
	
	//@Parameters("browser")
    public static void launch(String browser)
    {   
    	
    	
    	if (browser.equalsIgnoreCase("chrome")) {
    		
//    		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Hp\\Downloads\\chromedriver_win32\\chromedriver.exe");
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--disable-notifications");
//            driver=new ChromeDriver(options);
    		
    		 WebDriverManager.chromedriver().setup();
    		ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
           driver=new ChromeDriver(options);
    		
    		
			}
    	
    	else if (browser.equalsIgnoreCase("firefox")) {
		
//		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Hp\\Downloads\\geckodriver-v0.32.0-win32\\geckodriver.exe");
//        firefoxoptions  options= new FireFoxOptions();
//       options.addArguments("--disable-notifications");
//       driver=new FireFoxDriver(options); 
    		
    		  WebDriverManager.firefoxdriver().setup();
             FirefoxOptions options = new FirefoxOptions();
             options.addArguments("--disable-notifications");
             driver=new FirefoxDriver(options);
    		
    		
    		
             
		}
			
		
   	
       else if (browser.equalsIgnoreCase("MSedge")) {
    		
    	  WebDriverManager.edgedriver().setup();
          EdgeOptions options = new EdgeOptions();
//           options.addArguments("--disable-notifications");
          driver=new EdgeDriver(options);
    	   //System.setProperty("webdriver.edge.driver", "C:\\Users\\Hp\\Downloads\\edgedriver_win64\\msedgedriver.exe");
    	   //driver = new EdgeDriver();
    	   
    	  
		}
    	
    	 
         driver.manage().deleteAllCookies();
         driver.get(p.getProperty("url"));
         driver.manage().window().maximize();
         driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
         driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
         e_driver = new EventFiringWebDriver(driver);
         //eventListener = new WebEventListener();
         //    e_driver.register((WebDriverEventListener) eventListener);
             driver = e_driver;
        
    }
    
   
    
    
    public static void takeScreenshotAtEndOfTest(String filename) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        FileUtils.copyFile(scrFile, new File(currentDir + "/Screenshots/" + filename + ".png"));
    }
    
    public static Issue generateJiraTicket( ITestResult result)
	{
		JiraPolicy jiraPolicy = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(JiraPolicy.class);
		System.out.println("JiraPolicy : "+jiraPolicy);
		boolean isTicketReady = jiraPolicy.logTicketReady();
		Issue NewIssue=null;
		

		if(isTicketReady)
		{
			//raise jira ticket:
			System.out.println("is ticket ready for jira :"+isTicketReady);
			JiraServiceProvider jiraSp= new JiraServiceProvider(TestBase.p.getProperty("jira_url"),TestBase.p.getProperty("jira_Mail") ,TestBase.p.getProperty("jira_API_key") , "LM");
			String issueSummary = result.getMethod().getConstructorOrMethod().getMethod().getName() + "Lead is not created due to duplicity";

			String issueDescription1 = result.getThrowable().getMessage()+"\n";
			String issueDescription="Lead Creation Fails due to duplicate record";
			//issueDescription.concat(ExceptionUtils.getFullStackTrace(result.getThrowable()));
			issueDescription.concat(" beacuase "+result.getThrowable().getMessage());

			NewIssue= jiraSp.CreateJiraTicket("Bug",issueSummary, issueDescription, "Aksha Khan");
		}
		return NewIssue;
	}
	

	

}
