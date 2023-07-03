package com.ind.Page;


import com.ind.Base.TestBase;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends TestBase {
	
	@FindBy(xpath="//input[@id='username']")
    WebElement username;
    
    
    @FindBy(xpath="//input[@id='password']")
    WebElement Password;
    
    @FindBy(xpath="//input[@id='Login']")
    WebElement loginBtn; 
    
    @FindBy(xpath="//div[@class='appName slds-context-bar__label-action slds-context-bar__app-name']")
    WebElement Homeverify;

    @FindBy(xpath="//div[@class='slds-icon-waffle']")
    WebElement Applauncher;
    
//    @FindBy(xpath="(//p[@class='slds-truncate'])[1]")
//    WebElement Serviceapp;
    
    @FindBy(xpath="//button[@class='slds-button']")
    WebElement viewall;
    
    @FindBy(xpath="(//p[@class='slds-truncate'])[8]")
    WebElement sales;
    
  
    
    public LoginPage()
    {
        PageFactory.initElements(driver, this);
    }
    
    public void login(String un,String pass) throws InterruptedException
    {
        TestBase.driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        
       username.sendKeys(un);
       Thread.sleep(5000);
        Password.sendKeys(pass);
        Thread.sleep(5000);
        loginBtn.click();
        
        //Applauncher.click();
        //viewall.click();
       // Thread.sleep(5000);
       // sales.click();
        
    }
    
//    public void llogin() {
//    	
//    	TestBase.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//    	username.sendKeys("salesservice@salesforce.com");
//    	
//    Password.sendKeys("Automation@2022");
//    loginBtn.click();
//		
//	}
    
    public String loginverify() {
        return Homeverify.getText();
    }

}
