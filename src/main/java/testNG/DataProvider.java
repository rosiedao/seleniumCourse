package testNG;

import org.testng.annotations.*;

public class DataProvider {
    @org.testng.annotations.DataProvider
    public Object[][] getData(){
        Object[][] data = new Object[3][2];
        //1st set
        data[0][0] = "Username1";
        data[0][01] = "Password1";
        //2nd set
        data[1][0] = "Username2";
        data[1][1] = "Password2";
        //3rd set
        data[2][0] = "Username3";
        data[2][1] = "Password3";
        return data;
    }
    @Test(dataProvider = "getData")
    public void printData(String username, String password){
        System.out.println(username + ", " + password);
    }
    //Define variable name for parameters
    @Parameters({"urlName","key"})
    @Test
    public void UsingParameters(String urlName, int key){
        System.out.println(urlName + ", " + key);
    }
}
