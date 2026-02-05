package testScripts;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class TestScript {
    @Test
    public static void TS_LoginAndLogout(){
        WebDriver oBrowser = null;
        Map<String, String> objData = null;
        try{
            objData = AppIndependentMethods.getExcelData("TestData1.xlsx", "testData", "TestId01");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, objData.get("URL")), "Failed to load the URL");
            Assert.assertTrue(AppDependentMethods.loginToApplication(oBrowser, objData.get("userName"), objData.get("password")), "Failed to login to actiTime");
            Assert.assertTrue(AppDependentMethods.logoutFromApplication(oBrowser), "Failed to logout from the actiTime");
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' test script. "+e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }

    @Test
    public static void TS_LoginCreateAndDeleteUserAndLogout(){
        WebDriver oBrowser = null;
        Map<String, String> objData = null;
        try{
            objData = AppIndependentMethods.getExcelData("TestData1.xlsx", "testData", "TestId02");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            Assert.assertTrue(AppDependentMethods.navigateURL(oBrowser, objData.get("URL")),"Failed to load the URL");
            Assert.assertTrue(AppDependentMethods.loginToApplication(oBrowser, objData.get("userName"), objData.get("password")),"Failed to login to actiTime");
            String userName = AppDependentMethods.createUser(oBrowser, objData);
            Assert.assertTrue(AppDependentMethods.deleteUser(oBrowser, userName),"Failed to delete the user");
            Assert.assertTrue(AppDependentMethods.logoutFromApplication(oBrowser),"Failed to logout from the actiTime");
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' test script. "+e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }
}
