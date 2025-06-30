package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter; // UI of the report
	public ExtentReports extent; // populate common information on the report like tester name, browser name, OS
									// name, projectname, module name
	public ExtentTest test; // creating test case entries in the report and update status of the test
							// methods
	String repName;

	public void onStart(ITestContext testContext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/"+repName);

		sparkReporter.config().setDocumentTitle("RestAssured Petstore Automation Report");
		sparkReporter.config().setReportName("PetStore Users API  Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Application", "Pet Store");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("USer Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser Name", "Chrome");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}

	}

	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getName()); // create a new entity in the test
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in report
		test.log(Status.PASS, "Test case PASSED is " + result.getName()+" got successfully executed"); // update p/f/s status

	}

	public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName()); // create a new entity in the test
		test.assignCategory(result.getMethod().getGroups());  //to display groups in report
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test case FAILED is " + result.getName()); // update p/f/s status
		test.log(Status.INFO, "Test FAILED cause is  " + result.getThrowable().getMessage());
	}
		
		

	public void onTestSkipped(ITestResult result) {
		

		test = extent.createTest(result.getName()); // create a new entity in the test
		test.createNode(result.getName());
		test.assignCategory(result.getMethod().getGroups());  //to display groups in report
		test.log(Status.SKIP, "Test case SKIPPED is " + result.getName()+" got skipped"); // update p/f/s status
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		

	}

	public void onFinish(ITestContext context) {
		extent.flush();
		
		String pathOfExtentReport= System.getProperty("user.dir")+"/reports/"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
			
		}catch(IOException e ) {
			e.printStackTrace();
		}

	}

}
