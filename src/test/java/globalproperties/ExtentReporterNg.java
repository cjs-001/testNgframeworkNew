package globalproperties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	
	public static ExtentReports reports()
	{
		String path = System.getProperty("user.dir")+"\\reports\\indx.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Rahulshetty website");
		ExtentReports report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester", "Charan");
		return report;
	}
}
