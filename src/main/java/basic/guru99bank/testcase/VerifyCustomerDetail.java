package basic.guru99bank.testcase;

import basic.guru99bank.InitApplication;
import basic.guru99bank.pages.*;
import org.testng.annotations.Test;

import java.util.HashMap;

public class VerifyCustomerDetail extends InitApplication {
    LoginPage loginPage = launchApplication();
    @Test
    public void verifyCustomerDetail(){
        ManagerHomepage managerHomepage = loginPage.login("mngr576613","sAsAqYr");
        NewCustomerPage newCustomerPage = managerHomepage.goToNewCustomer();
        //Data
        HashMap<String,String> data = new HashMap<>();
        data.put("name","Rosie");
        data.put("gender","female");
        data.put("dob","10-09-1999");
        data.put("address","Cau Giay");
        data.put("city","Ha Noi");
        data.put("state","Dich Vong");
        data.put("pin","100001");
        data.put("phone","09229929");
        data.put("email","rosie25@test.com");
        data.put("password","123456");
        //submit new customer
        CustomerDetailPage customerDetailPage = newCustomerPage.addNewCustomer(data.get("name"),data.get("gender"),data.get("dob"),data.get("address"),
                data.get("city"),data.get("state"),data.get("pin"),data.get("phone"),data.get("email"),data.get("password"));
        //Verify customer information on detail page
        String customerID = customerDetailPage.getCustomerId();
        System.out.println("Customer ID is: " + customerID);
        customerDetailPage.verifyCustomerDetailInfo(data.get("name"),data.get("gender"),data.get("dob"),data.get("address"),
                data.get("city"),data.get("state"),data.get("pin"),data.get("phone"),data.get("email"));
        //Go to search customer id
        SearchCustomerPage searchCustomerPage = managerHomepage.goToEditCustomer();
        // Input Customer ID
        EditCustomerPage editCustomerPage = searchCustomerPage.searchCustomerId(customerID);
        // Verify customer information in edit page
        editCustomerPage.verifyCustomerDetailInfo(data.get("name"),data.get("gender"),data.get("dob"),data.get("address"),
                data.get("city"),data.get("state"),data.get("pin"),data.get("phone"),data.get("email"));
        //Change information
        HashMap<String,String> changes = new HashMap<>();
        changes.put("address","Pham Van Bach");
        changes.put("city","Ho Chi Minh");
        changes.put("state","Quan 1");
        changes.put("pin","100002");
        changes.put("phone","092299887");
        changes.put("email","rosie26@test.com");
        CustomerDetailPage customerDetailPage1 = editCustomerPage.editCustomer(changes.get("address"), changes.get("city"),changes.get("state"),
                changes.get("pin"),changes.get("phone"),changes.get("email"));
        //Verify information on detail
        customerDetailPage1.verifyCustomerDetailInfo(data.get("name"),data.get("gender"),data.get("dob"),changes.get("address"), changes.get("city"),changes.get("state"),
                changes.get("pin"),changes.get("phone"),changes.get("email"));
    }
}
