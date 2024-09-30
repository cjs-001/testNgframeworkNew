package testcomponents;

import java.io.IOException;
import java.text.Format.Field;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import globalproperties.ExtentReporterNg;

public class Listeners extends BaseTest implements ITestListener{
	
	
	ExtentReports report = ExtentReporterNg.reports();
	ExtentTest test;
	ThreadLocal<ExtentTest> thread= new ThreadLocal<ExtentTest>();
	
	
	@Override		
    public void onTestStart(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        	test=report.createTest(arg0.getMethod().getMethodName())	;
        	thread.set(test);
    }	
	
	

    @Override		
    public void onTestSuccess(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        	thread.get().log(Status.PASS, "test passed");	
    }
	
    @Override		
    public void onTestFailure(ITestResult arg0) {					
        // TODO Auto-generated method stub	
    	
    	test.fail(arg0.getThrowable());
    	
//    	try {
//    		
//    		 ITestContext context = arg0.getTestContext();
//    		    driver = (WebDriver) context.getAttribute("WebDriver");
//    		//driver= (WebDriver) arg0.getTestClass().getRealClass().getDeclaredField("driver").get(arg0.getInstance());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//    	
//    	String filePath = null;
//		try {
//			filePath = takeSS(arg0.getMethod().getMethodName(), driver);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	test.addScreenCaptureFromPath(filePath,arg0.getMethod().getMethodName());
    }
    
	@Override		
    public void onFinish(ITestContext arg0) {					
        // TODO Auto-generated method stub				
		report.flush();	
    }		

    @Override		
    public void onStart(ITestContext arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    		

    @Override		
    public void onTestSkipped(ITestResult arg0) {					
        // TODO Auto-generated method stub				
        		
    }		

    

}
