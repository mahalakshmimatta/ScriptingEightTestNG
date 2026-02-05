package testScripts;

import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import org.openqa.selenium.WebDriver;
import java.util.Map;

public class TestScript {

    public static void TS_LoginAndLogout(){
        WebDriver oBrowser = null;
        Map<String, String> objData = null;
        try{
            objData = AppIndependentMethods.getExcelData("TestData1.xlsx", "testData", "TestId01");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            AppDependentMethods.navigateURL(oBrowser, objData.get("URL"));
            AppDependentMethods.loginToApplication(oBrowser, objData.get("userName"), objData.get("password"));
            AppDependentMethods.logoutFromApplication(oBrowser);
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' test script. "+e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }


    public static void TS_LoginCreateAndDeleteUserAndLogout(){
        WebDriver oBrowser = null;
        Map<String, String> objData = null;
        try{
            objData = AppIndependentMethods.getExcelData("TestData1.xlsx", "testData", "TestId02");
            oBrowser = AppIndependentMethods.launchBrowser(objData.get("browserName"));
            AppDependentMethods.navigateURL(oBrowser, objData.get("URL"));
            AppDependentMethods.loginToApplication(oBrowser, objData.get("userName"), objData.get("password"));
            String userName = AppDependentMethods.createUser(oBrowser, objData);
            AppDependentMethods.deleteUser(oBrowser, userName);
            AppDependentMethods.logoutFromApplication(oBrowser);
        }catch(Exception e){
            System.out.println("Exception in 'TS_LoginAndLogout()' test script. "+e);
        }finally{
            oBrowser.close();
            oBrowser = null;
        }
    }
}
