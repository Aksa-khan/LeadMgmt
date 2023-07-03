package com.ind.Page;

import com.ind.Base.TestBase;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadPage extends TestBase {
	
	@FindBy(xpath="(//span[@class='slds-truncate'])[4]")
	WebElement Lead;
	
	@FindBy(linkText = "New")
	WebElement Newlead;
	
	@FindBy(xpath="(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[1]")
	WebElement salutation;
	
	@FindBy(xpath="(//input[@class='slds-input'])[3]")
	WebElement firstname;
	
	@FindBy(xpath="(//input[@class='slds-input'])[4]")
	WebElement lastname;
	
	@FindBy(xpath="(//input[@class='slds-input'])[6]")
	WebElement company;
	
	@FindBy(xpath="(//button[@class='slds-combobox__input slds-input_faux slds-combobox__input-value'])[4]")
	WebElement Status;
	
	@FindBy(xpath="(//textarea[@class='slds-textarea'])[1]")
	WebElement street;
	
    @FindBy(xpath="(//input[@class='slds-input'])[13]")
    WebElement city;
    
    @FindBy(xpath="(//input[@class='slds-input'])[14]")
    WebElement state;
    
    @FindBy(xpath="(//input[@class='slds-input'])[15]")
    WebElement zipcode;
	
    @FindBy(xpath="(//input[@class='slds-input'])[16]")
    WebElement country;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
	WebElement save;
    
    @FindBy(xpath="(//span[@class=\"slds-text-align_right slds-p-left_x-small\"])[1]")
    WebElement Addtask;
    
    @FindBy(xpath="//input[@class='slds-combobox__input slds-input slds-combobox__input-value']")
    WebElement tasksub;
    
    @FindBy(xpath="//a[@class='select']")
    WebElement taskstatus;
    
    @FindBy(xpath="//button[@class='slds-button slds-button--brand cuf-publisherShareButton uiButton']")
    WebElement tasksave;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_icon-border-filled']")
    WebElement picklist;
    
    //@FindBy(xpath="//a[@name='Convert']")
    @FindBy(xpath="//a[@name='Convert']")
    WebElement convert;
    
    @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
    WebElement converted;
    
  @FindBy(xpath="//lightning-formatted-name[@slot='primaryField']")
  WebElement verifylead;
  
  @FindBy(xpath="//button[@class='slds-button slds-button_brand']")
  WebElement gotolead;
    
    public LeadPage()
    {
    	PageFactory.initElements(driver, this);
    }
    
    public void newlead(String FirstName,String LastName,String Company,String Street,String City,String State,String Zipcode,String Country) throws InterruptedException 
    {
    	
        TestBase.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		JavascriptExecutor js=(JavascriptExecutor)TestBase.driver;
	     js.executeScript("arguments[0].click();",Lead);
    	
    	Newlead.click();
    	Thread.sleep(5000);
    	JavascriptExecutor js3=(JavascriptExecutor)TestBase.driver;
	     js3.executeScript("arguments[0].click();",salutation);	
    	salutation.sendKeys(Keys.ARROW_DOWN);
    	salutation.sendKeys(Keys.ENTER);
    	
    	firstname.sendKeys(FirstName);
    	
    	lastname.sendKeys(LastName);
    	
        company.sendKeys(Company);
    	
//        Status.click();
        JavascriptExecutor js1=(JavascriptExecutor)TestBase.driver;
	     js1.executeScript("arguments[0].click();",Status);	
        Status.sendKeys(Keys.ARROW_DOWN);
//        Status.sendKeys(Keys.ARROW_DOWN);
        Status.sendKeys(Keys.ENTER);
    
        street.sendKeys(Street);
        
        city.sendKeys(City);
        
        state.sendKeys(State);
        
        zipcode.sendKeys(Zipcode);
        
        country.sendKeys(Country);
        
        save.click();
        Thread.sleep(5000);
         JavascriptExecutor js4=(JavascriptExecutor)TestBase.driver;
	     js4.executeScript("arguments[0].click();",Addtask);	
	     
        tasksub.click();
        tasksub.sendKeys(Keys.ARROW_DOWN);
        tasksub.sendKeys(Keys.ARROW_DOWN);
        tasksub.sendKeys(Keys.ENTER);
        

        JavascriptExecutor js5=(JavascriptExecutor)TestBase.driver;
	    js5.executeScript("arguments[0].click();",tasksave);	
	    
	    Thread.sleep(2000);
        
	    JavascriptExecutor js6=(JavascriptExecutor)TestBase.driver;
	     js6.executeScript("arguments[0].click();",picklist);	
	    
	     Thread.sleep(5000);
	     JavascriptExecutor js7=(JavascriptExecutor)TestBase.driver;
	     js7.executeScript("arguments[0].click();",convert);	
	     Thread.sleep(10000);
	     JavascriptExecutor js8=(JavascriptExecutor)TestBase.driver;
	     js8.executeScript("arguments[0].click();",converted);	    
	     
	     
    }	
	
    public String verifyedlead() {
		return verifylead.getText();
	}

}
