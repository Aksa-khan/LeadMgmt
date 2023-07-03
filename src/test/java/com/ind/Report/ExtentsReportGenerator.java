package com.ind.Report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentsReportGenerator {
	
	public static ExtentReports extent;

	public static ExtentReports extentreprts() {

		String str = ".//Reports//Report";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(str);

		reporter.config().setDocumentTitle("Salesforce Sales App report");

		reporter.config().setReportName("Sales App Report");

		reporter.config().setTheme(Theme.STANDARD);

		ExtentReports extent = new ExtentReports();

		extent.attachReporter(reporter);

		extent.setSystemInfo("Project Name", "Salesforce Service App");
		extent.setSystemInfo("Application URL", "https://www.login.salesforce.com/");
		extent.setSystemInfo("Testing Author", "Aksha Khan");
		extent.setSystemInfo("Employee Id", "CZ016");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("Environment", "Test Environment");

		

		return extent;
	
	
	
	
	}	
	


}
