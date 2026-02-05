package locators;

import org.openqa.selenium.By;

public interface ObjectLocators {
    By obj_LoginLogo_Image = By.xpath("//img[contains(@src, 'timer.png')]");
    By obj_UserName_Edit = By.xpath("//input[@id='username']");
    By obj_Password_Edit = By.xpath("//input[@name='pwd']");
    By obj_Login_Button = By.xpath("//a[@id='loginButton']");
    By obj_Hompage_PageText = By.xpath("//td[@class='pagetitle']");
    By obj_Shortcut_Dialog = By.xpath("//div[@style='display: block;']");
    By getObj_Shortcut_Close_Button = By.id("gettingStartedShortcutsMenuCloseId");
    By obj_User_FirstName_Edit = By.xpath("//input[@name='firstName']");
    By obj_User_LastName_Edit = By.xpath("//input[@name='lastName']");
    By obj_User_Email_Edit = By.xpath("//input[@name='email']");
    By obj_User_UserName_Edit = By.xpath("//input[@name='username']");
    By obj_User_Password_Edit = By.xpath("//input[@name='password']");
    By obj_User_RetypePassword_Edit = By.xpath("//input[@name='passwordCopy']");
    By obj_CreateUser_Button = By.xpath("//span[text()='Create User']");
    String obj_UserName_Link = "//div[@class='name']/span[text()='%s']";
    By obj_USERS_Menu = By.xpath("//div[text()='USERS']");
    By obj_UserPage_Header = By.xpath("//div[@class='pagetitle']/span");
    By obj_AddUser_Button = By.xpath("//div[text()='Add User']");
    By obj_AddUser_Header = By.id("userDataLightBox_titlePlaceholder");
    By obj_DeleteUser_Button = By.xpath("//button[contains(text(), 'Delete User')]");
    By obj_Logout_Link = By.xpath("//a[@id='logoutLink']");
    By obj_LoginPage_Header = By.id("headerContainer");
    By obj_dummylocator=By.xpath("");
}
