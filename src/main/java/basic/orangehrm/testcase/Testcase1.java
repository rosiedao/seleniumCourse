package basic.orangehrm.testcase;

import basic.orangehrm.InitApplication;
import basic.orangehrm.pages.*;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.testng.AssertJUnit.assertEquals;

public class Testcase1 extends InitApplication {
    LoginPage loginPage = launchApplication();
    @Test
    public void addEmployee(){
        DashboardPage dashboardPage = loginPage.login("Admin","admin123");
        PIMPage pimPage = dashboardPage.goToPIMPage();
        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();
        HashMap<String, String> data = new HashMap<>();
        data.put("firstname","Selena");
        data.put("middleName","Huyen");
        data.put("lastname","Le");
        data.put("employeeId","4546");
        data.put("username","selena3");
        data.put("isEnable","Yes");
        data.put("password","admin123");
        data.put("confirmPassword","admin123");
        data.put("filePath",System.getProperty("user.dir") + "/src/images/sample.png");

        PersonalDetailPage personalDetailPage = addEmployeePage.addEmployee(data.get("firstname"),data.get("middleName"),
                data.get("lastname"),data.get("username"),data.get("isEnable"),data.get("password"), data.get("confirmPassword"),
                data.get("filePath"));
        //Verify information in personal detail
        //assertEquals(addEmployeePage.getEmployeeId(),personalDetailPage.getEmployeeId());
        //personalDetailPage.verifyInputtedInformation(data.get("firstname"),data.get("middleName"),
                //data.get("lastname"));
    }
    @Test
    public void testcase1(){
        DashboardPage dashboardPage = loginPage.login("Admin","admin123");
        PIMPage pimPage = dashboardPage.goToPIMPage();
        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();
        HashMap<String, String> data = new HashMap<>();
        data.put("firstname","Selena");
        data.put("middleName","Huyen");
        data.put("lastname","Le");
        data.put("employeeId","4561");
        data.put("username","selena1");
        data.put("isEnable","Yes");
        data.put("password","admin123");
        data.put("confirmPassword","admin123");
        data.put("filePath",System.getProperty("user.dir") + "/src/images/sample.png");

        PersonalDetailPage personalDetailPage = addEmployeePage.addEmployee(data.get("firstname"),data.get("middleName"),
                data.get("lastname"),data.get("username"),data.get("isEnable"),data.get("password"), data.get("confirmPassword"),
                data.get("filePath"));
        //Verify information in personal detail
        assertEquals(addEmployeePage.getEmployeeId(),personalDetailPage.getEmployeeId());
        personalDetailPage.verifyInputtedInformation(data.get("firstname"),data.get("middleName"),
                data.get("lastname"));
        //Go to immigration
        ImmigrationListPage immigrationListPage = personalDetailPage.goToImmigrationPage();
        // click button add
        immigrationListPage.goToAddNewImmigration();
        // fill form
        HashMap<String,String> input = new HashMap<>();
        input.put("document","passport");
        input.put("number","567");
        input.put("comments","line 1 \n line 2");
        immigrationListPage.addNewImmigration(input.get("document"),input.get("number"),input.get("comments"));
        // click edit icon
        immigrationListPage.goToImmigrationEdit();
        // verify information in detail page
        immigrationListPage.verifyImmigrationDetail(input.get("document"),input.get("number"),input.get("comments"));
        //logout
        LoginPage loginPage1 = dashboardPage.logout();
        //login with new account
        loginPage1.login(data.get("username"),data.get("password"));
    }
    @Test
    public void testLogout(){
        DashboardPage dashboardPage = loginPage.login("Admin","admin123");
        PIMPage pimPage = dashboardPage.goToPIMPage();
        AddEmployeePage addEmployeePage = pimPage.goToAddEmployeePage();
        HashMap<String, String> data = new HashMap<>();
        data.put("firstname","Selena");
        data.put("middleName","Huyen");
        data.put("lastname","Le");
        data.put("employeeId","0112");
        data.put("username","rosie17");
        data.put("isEnable","Yes");
        data.put("password","admin123");
        data.put("confirmPassword","admin123");
        data.put("filePath",System.getProperty("user.dir") + "/src/images/sample.png");

        PersonalDetailPage personalDetailPage = addEmployeePage.addEmployee(data.get("firstname"),data.get("middleName"),
                data.get("lastname"),data.get("username"),data.get("isEnable"),data.get("password"), data.get("confirmPassword"),
                data.get("filePath"));
        //Verify information in personal detail
        assertEquals(addEmployeePage.getEmployeeId(),personalDetailPage.getEmployeeId());
        personalDetailPage.verifyInputtedInformation(data.get("firstname"),data.get("middleName"),
                data.get("lastname"));
        //logout
        LoginPage loginPage1 = dashboardPage.logout();
        //login with new account
        loginPage1.login(data.get("username"),data.get("password"));
        PersonalDetailPage personalDetailPage1 = dashboardPage.goToMyInfo();
        personalDetailPage1.verifyInputtedInformation(data.get("firstname"),data.get("middleName"),
                data.get("lastname"));
    }
    @Test
    public void logoutAndLoginAgain(){
        DashboardPage dashboardPage = loginPage.login("Admin","admin123");
        LoginPage loginPage1 = dashboardPage.logout();
        //login with new account
        loginPage1.login("Admin","admin123");
        PersonalDetailPage personalDetailPage1 = dashboardPage.goToMyInfo();
        personalDetailPage1.verifyInputtedInformation("Selena","Huyen",
                "Le");
    }
}
