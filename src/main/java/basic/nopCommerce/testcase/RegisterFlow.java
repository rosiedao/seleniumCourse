package basic.nopCommerce.testcase;

import basic.nopCommerce.InitApplication;
import basic.nopCommerce.pages.Homepage;
import basic.nopCommerce.pages.MyAccountPage;
import basic.nopCommerce.pages.RegisterPage;
import org.testng.annotations.Test;

import java.util.HashMap;

public class RegisterFlow extends InitApplication {
    Homepage homepage = launchApplication();
    @Test
    public void register(){
        RegisterPage registerPage = homepage.goToRegister();
        registerPage.verifyDateFieldOptions();
        //Register
        HashMap<String, String> data = new HashMap<>();
        data.put("gender","male");
        data.put("firstname","rosie");
        data.put("lastname","dao");
        data.put("dob","10/01/1992");
        data.put("company","FPT");
        data.put("email","rosie6@test.com");
        data.put("newsletter","N");
        data.put("password","admin123");
        data.put("confirmPassword","admin123");
        Homepage homepage1 = registerPage.register(data.get("gender"),data.get("firstname"),data.get("lastname"),data.get("dob"),data.get("email"),
                data.get("company"),data.get("newsletter"),data.get("password"),data.get("confirmPassword"));
        homepage1.verifyRegisterSuccess();
        MyAccountPage myAccountPage  = homepage1.goToMyAccount();
        myAccountPage.verifyCustomerInfo(data.get("gender"),data.get("firstname"),data.get("lastname"),data.get("dob"),data.get("email"),
                data.get("company"),data.get("newsletter"));
    }
}
