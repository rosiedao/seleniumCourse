package testNG;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener{
    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
    }
}
