package com.ind.Report;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ind.Base.TestBase;

import net.rcarz.jiraclient.Issue;


public class Listener extends TestBase implements ITestListener  {
	
	ExtentReports extent = ExtentsReportGenerator.extentreprts();

	ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

		public void onTestSuccess(ITestResult result) {
	
			((ExtentTest) ObjectRepo.test).log(Status.PASS, "TEST CASE IS PASS");
	
			((ExtentTest) ObjectRepo.test).addScreenCaptureFromBase64String(getCapture(), "test case passed ");
			System.out.println("---------Test is Passed-----" + result.getName());
	
		}

	public void onTestStart(ITestResult result) {
		ObjectRepo.test = extent
				.createTest(result.getTestClass().getName() + "==" + result.getMethod().getMethodName());
		extenttest.set((ExtentTest) ObjectRepo.test);
		System.out.println("------Testing is Started-----" + result.getName());

	}

	public void onTestFailure(ITestResult result) {
		
		Issue NewIssue=TestBase.generateJiraTicket(result);
		System.out.println("Issues : "+NewIssue.toString());
		System.out.println("Issues : "+NewIssue.getKey());

		((ExtentTest) ObjectRepo.test).log(Status.FAIL, "TEST CASE FAIL & EXCEPTION IS  :" + result.getThrowable());
		String src = ((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BASE64);

		((ExtentTest) ObjectRepo.test).addScreenCaptureFromBase64String(src,
				"Test case is failed :" + "because, Web Element is ditached from the current DOM or Sometimes User interface looks different");

	}

	public void onTestSkipped(ITestResult result) {
		((ExtentTest) ObjectRepo.test).log(Status.SKIP, "TEST CASE IS SKIP");

		((ExtentTest) ObjectRepo.test).addScreenCaptureFromBase64String(getCapture(), "test case skip ");
		System.out.println("--------Test Case Skipped Details are--------" + result.getName());

	}

	public void onFinish(ITestContext context) {
		extent.flush();

	}

	public String getCapture() {

		String file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
		return file;
	}

	

}
