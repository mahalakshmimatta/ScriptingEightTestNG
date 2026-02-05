package methods;

import locators.ObjectLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class AppDependentMethods implements ObjectLocators {

    /**************************************************
     * Method Name      : navigateURL()
     * Purpose          : it will navigates the actiTime URL
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : boolean blnRes = navigateURL(oBrowser, "http://localhost/login.do");
     * arguments        : WebDriver oBrowser, String strURL
     ***************************************************/
    public static boolean navigateURL(WebDriver oBrowser, String strURL){
        try{
            oBrowser.navigate().to(strURL);
            Thread.sleep(2000);
            boolean blnRes = AppIndependentMethods.verifyElementPresent(oBrowser, obj_LoginLogo_Image);
            if(blnRes) {
                System.out.println("URL is loaded successful");
                return true;
            } else {
                System.out.println("Failed to load the URL");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'navigateURL()' method. "+e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : loginToApplication()
     * Purpose          : login to actiTime application
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : boolean blnRes = logonToApplication(oBrowser, "admin", "manager);
     * arguments        : WebDriver oBrowser, String userName, String password
     ***************************************************/
    public static boolean loginToApplication(WebDriver oBrowser, String userName, String password){
        String strText = null;
        try{
            AppIndependentMethods.setObject(oBrowser, obj_UserName_Edit, userName);
            AppIndependentMethods.setObject(oBrowser, obj_Password_Edit, password);
            AppIndependentMethods.clickObject(oBrowser, obj_Login_Button);
            Thread.sleep(2000);
            boolean blnRes = AppIndependentMethods.verifyText(oBrowser, obj_Hompage_PageText, "Text", "Enter Time-Track");
            if(blnRes){
                System.out.println("Login to actiTime successful");
                if(AppIndependentMethods.verifyOptionalElement(oBrowser, obj_Shortcut_Dialog)){
                    AppIndependentMethods.clickObject(oBrowser, getObj_Shortcut_Close_Button);
                }
                return true;
            }else{
                System.out.println("Failed to login to actiTime");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'loginToApplication()' method. "+e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : createUser()
     * Purpose          : to create the user in the application
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : String userName = createUser(oBrowser);
     * arguments        : WebDriver oBrowser
     ***************************************************/
    public static String createUser(WebDriver oBrowser, Map<String, String> objData){
        String userName = null;
        try {
            navigateToAddUserPage(oBrowser);
            AppIndependentMethods.setObject(oBrowser, obj_User_FirstName_Edit, objData.get("user_FirstName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_LastName_Edit, objData.get("user_LastName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_Email_Edit, objData.get("user_Email"));
            AppIndependentMethods.setObject(oBrowser, obj_User_UserName_Edit, objData.get("user_UserName"));
            AppIndependentMethods.setObject(oBrowser, obj_User_Password_Edit, objData.get("user_Password"));
            AppIndependentMethods.setObject(oBrowser, obj_User_RetypePassword_Edit, objData.get("user_RetypePassword"));
            userName = objData.get("user_LastName")+", "+objData.get("user_FirstName");
            AppIndependentMethods.clickObject(oBrowser, obj_CreateUser_Button);
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyElementPresent(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)))){
                System.out.println("The New user is created successful");
                return userName;
            }else{
                System.out.println("Failed to create the new user");
                return null;
            }
        }catch(Exception e){
            System.out.println("Exception in 'createUser()' method. "+e);
            return null;
        }
    }


    /**************************************************
     * Method Name      : navigateToAddUserPage()
     * Purpose          : navigates to Add User page
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : boolean blnRes = navigateToAddUserPage(oBrowser);
     * arguments        : WebDriver oBrowser
     ***************************************************/
    public static boolean navigateToAddUserPage(WebDriver oBrowser){
        String strText = null;
        try{
            AppIndependentMethods.clickObject(oBrowser, obj_USERS_Menu);
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyText(oBrowser, obj_UserPage_Header, "Text", "User List")){
                System.out.println("'User List' page has opened successful");
            }else {
                System.out.println("Failed to open the 'User List' page");
                return false;
            }

            AppIndependentMethods.clickObject(oBrowser, obj_AddUser_Button);
            Thread.sleep(2000);

            if(AppIndependentMethods.verifyText(oBrowser, obj_AddUser_Header, "Text", "Add User")){
                System.out.println("'Add User' page has opened successful");
                return true;
            } else {
                System.out.println("Failed to open the 'Add User' page");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'navigateToAddUserPage()' method. "+e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : deleteUser()
     * Purpose          : navigates to Add User page
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : boolean blnRes = deleteUser(oBrowser, "test, user1");
     * arguments        : WebDriver oBrowser, String userName
     ***************************************************/
    public static boolean deleteUser(WebDriver oBrowser, String userName){
        try{
            AppIndependentMethods.clickObject(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)));
            Thread.sleep(2000);
            AppIndependentMethods.clickObject(oBrowser, obj_DeleteUser_Button);
            Thread.sleep(1000);
            oBrowser.switchTo().alert().accept();
            Thread.sleep(1000);

            if(AppIndependentMethods.verifyElementNotPresent(oBrowser, By.xpath(String.format(obj_UserName_Link, userName)))){
                System.out.println("User was deleted successful");
                return true;
            }else{
                System.out.println("Failed to delete the user");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'deleteUser()' method. "+e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : logoutFromApplication()
     * Purpose          : logs out from actiTime application
     * Author           : Sg Tester1
     * return Type      : boolean
     * call Statement   : boolean blnRes = logoutFromApplication(oBrowser);
     * arguments        : WebDriver oBrowser
     ***************************************************/
    public static boolean logoutFromApplication(WebDriver oBrowser){
        try{
            AppIndependentMethods.clickObject(oBrowser, obj_Logout_Link);
            Thread.sleep(2000);
            if(AppIndependentMethods.verifyText(oBrowser, obj_LoginPage_Header, "Text", "Please identify yourself")){
                System.out.println("Logout form the actiTime was successful");
                return true;
            }else{
                System.out.println("Failed to logout from the actiTime");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'logoutFromApplication()' method. "+e);
            return false;
        }
    }
}
