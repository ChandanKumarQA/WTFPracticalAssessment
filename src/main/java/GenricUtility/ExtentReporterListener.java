package GenricUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ExtentReporterListener implements ITestListener {

    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // Create the reports directory if it doesn't exist
        File reportDir = new File(System.getProperty("user.dir") + "/reports");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        // Initialize ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
        sparkReporter.config().setDocumentTitle("QA Assessment Automation Report");
        sparkReporter.config().setReportName("Test Execution Results");
        sparkReporter.config().setTheme(Theme.DARK);

        // Attach reporter to ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Tester", "Chandan Kumar");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a test in the report for each test method
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed");
        test.log(Status.FAIL, result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped");
        test.log(Status.SKIP, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush writes everything to the log file
        if (extent != null) {
            extent.flush();
        }
    }
}
