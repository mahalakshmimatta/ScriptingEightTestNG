package methods;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppIndependentMethods {

    /***********************************************
     * Method Name      : clickObject()
     * purpose          : To click the given element
     * parms            :
     * return type      :boolean
     * call Statement   :
     **********************************************/
    public static boolean clickObject(WebDriver oBrowser, By objBy){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.click();
                System.out.println("The Element '"+objBy+"' clicked successful");
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception in 'clickObject()' method. "+e);
            return false;
        }finally{
            oEle = null;
        }
    }


    /***********************************************
     * Method Name      : setObject()
     * purpose          : To enter the value in the given element
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean setObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.sendKeys(strData);
                System.out.println("The value '"+strData+"' is entered in the Element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception in 'setObject()' method. "+e);
            return false;
        }finally{
            oEle = null;
        }
    }



    /***********************************************
     * Method Name      : clearAndSetObject()
     * purpose          : To enter the value in the given element by clearing the existing data
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean clearAndSetObject(WebDriver oBrowser, By objBy, String strData){
        WebElement oEle = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                oEle.clear();
                oEle.sendKeys(strData);
                System.out.println("The value '"+strData+"' is entered in the Element '"+objBy+"' successful");
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception in 'clearAndSetObject()' method. "+e);
            return false;
        }finally{
            oEle = null;
        }
    }


    /***********************************************
     * Method Name      : compareText()
     * purpose          : To compare actual & expected values
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean compareText(WebDriver oBrowser, String actual, String expected){
        try{
            if(actual.equalsIgnoreCase(expected)){
                System.out.println("The actual '"+actual+"' & expected '"+expected+"' values are matching");
                return true;
            }else{
                System.out.println("Mis-match in actual '"+actual+"' & expected '"+expected+"' values");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'compareText()' method. "+e);
            return false;
        }
    }


    /***********************************************
     * Method Name      : verifyText()
     * purpose          : To verify text in the application
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean verifyText(WebDriver oBrowser, By objBy, String objectType, String expected){
        WebElement oEle = null;
        Select oSelect = null;
        String actual = null;
        try{
            oEle = oBrowser.findElement(objBy);
            if(oEle.isDisplayed()){
                switch(objectType.toLowerCase()){
                    case "text":
                        actual = oEle.getText();
                        break;
                    case "value":
                        actual = oEle.getAttribute("value");
                        break;
                    case "dropdown":
                        oSelect = new Select(oEle);
                        actual = oSelect.getFirstSelectedOption().getText();
                        break;
                    default:
                        System.out.println("Invalid object type '"+objectType+"' was specified.");
                        return false;
                }

                if(compareText(oBrowser, actual, expected)) return true;
                else return false;
            }
            return true;
        }catch(Exception e){
            System.out.println("Exception in 'verifyText()' method. "+e);
            return false;
        }finally{
            oEle = null;
            oSelect = null;
        }
    }


    /***********************************************
     * Method Name      : verifyElementPresent()
     * purpose          : To verify element in the application
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean verifyElementPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                System.out.println("The Element '"+objBy+"' present in the application DOM");
                return true;
            }else{
                System.out.println("The Element '"+objBy+"' NOT present in the application DOM");
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyElementPresent()' method. "+e);
            return false;
        }
    }


    /***********************************************
     * Method Name      : verifyElementNotPresent()
     * purpose          : To verify element in the application
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean verifyElementNotPresent(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                System.out.println("The Element '"+objBy+"' Still present in the application DOM");
                return false;
            }else{
                System.out.println("The Element '"+objBy+"' NOT present in the application DOM");
                return true;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyElementNotPresent()' method. "+e);
            return false;
        }
    }


    /***********************************************
     * Method Name      : verifyElementNotPresent()
     * purpose          : To verify element in the application
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static boolean verifyOptionalElement(WebDriver oBrowser, By objBy){
        List<WebElement> oEles = null;
        try{
            oEles = oBrowser.findElements(objBy);
            if(oEles.size() > 0){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("Exception in 'verifyOptionalElement()' method. "+e);
            return false;
        }
    }


    /**************************************************
     * Method Name      : launchBrowser()
     * Purpose          : it opens the Chrome, FireFox and Edge browser
     * Author           :Sg Tester1
     * Reviewer         :
     * Date of Creation :
     * return Type      : WebDriver
     * call Statement   : WebDriver oBrowser = launchBrowser("Chrome/FireFox/Edge")
     * arguments        : String browserName
     ***************************************************/
    public static WebDriver launchBrowser(String browserName){
        WebDriver oDriver = null;
        try{
            switch(browserName.toLowerCase()){
                case "chrome":
                    oDriver = new ChromeDriver();
                    break;
                case "firefox":
                    oDriver = new FirefoxDriver();
                    break;
                case "edge":
                    oDriver = new EdgeDriver();
                    break;
                default:
                    System.out.println("Invalid browser name '"+browserName+"' was mentioned.");
                    return null;
            }
            if(oDriver!=null){
                oDriver.manage().window().maximize();
                System.out.println("The '"+browserName+"' browser launched successful");
                return oDriver;
            }else{
                System.out.println("Failed to launch the '"+browserName+"' browser.");
                return null;
            }
        }catch(Exception e){
            System.out.println("Exception in 'launchBrowser()' method. "+e);
            return null;
        }
    }

    /***********************************************
     * Method Name      : getExcelData()
     * purpose          : To read the data from excel file and store them in Map object based on logical name
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static Map<String, String> getExcelData(String fileName, String sheetName, String logicalName){
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        Row row1 = null;
        Row row2 = null;
        Cell cell1 = null;
        Cell cell2 = null;
        String strKey = null;
        String strValue = null;
        Map<String, String> objData = null;
        int rowNum = 0;
        int colNum = 0;
        Calendar cal = null;
        String sDay = null;
        String sMonth = null;
        String sYear = null;
        try{
            objData = new HashMap<String, String>();
            fin = new FileInputStream(System.getProperty("user.dir")+ "\\TestData\\"+fileName);
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("The sheet '"+sheetName+"' doesnot exist");
                return null;
            }

            //find the rowNum in which the given logicalName present
            int rowCount = sh.getPhysicalNumberOfRows();
            for(int r=1; r<rowCount; r++){
                row1 = sh.getRow(r);
                cell1 = row1.getCell(0);
                if(cell1.getStringCellValue().equalsIgnoreCase(logicalName)){
                    rowNum = r;
                    break;
                }
            }

            if(rowNum==0){
                System.out.println("Failed to find the logicalName '"+logicalName+"' in the excel sheet '"+sheetName+"'");
                return null;
            }else{
                row1 = sh.getRow(0);
                row2 = sh.getRow(rowNum);
                colNum = row1.getPhysicalNumberOfCells();
                for(int c=0; c<colNum; c++){
                    cell1 = row1.getCell(c);
                    cell2 = row2.getCell(c);
                    strKey = cell1.getStringCellValue();

                    //format the cell2 values
                    switch(cell2.getCellType()){
                        case BLANK:
                            strValue = "";
                            break;
                        case STRING:
                            strValue = cell2.getStringCellValue();
                            break;
                        case BOOLEAN:
                            strValue = String.valueOf(cell2.getBooleanCellValue());
                            break;
                        case NUMERIC:
                            if(DateUtil.isCellDateFormatted(cell2)==true){
                                double dt = cell2.getNumericCellValue();
                                cal = Calendar.getInstance();
                                cal.setTime(DateUtil.getJavaDate(dt));

                                if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                                    sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
                                }else{
                                    sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                                }

                                if((cal.get(Calendar.MONTH)+1) < 10){
                                    sMonth = "0" + (cal.get(Calendar.MONTH)+1);
                                }else{
                                    sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
                                }

                                strValue = sDay + "/" + sMonth +"/"+ String.valueOf(cal.get(Calendar.YEAR));
                            }else{
                                strValue = String.valueOf(cell2.getNumericCellValue());
                            }
                            break;
                        default:
                            System.out.println("Invalid cell datatype present in excel file");
                            return null;
                    }
                    objData.put(strKey, strValue);
                }
            }
            return objData;
        }catch(Exception e){
            System.out.println("Exception in 'getExcelData()' method. "+e);
            return null;
        }finally{
            try{
                fin.close();
                fin = null;
                cell1 = null;
                cell2 = null;
                row1 = null;
                row2 = null;
                sh = null;
                wb.close();
                wb = null;
            }catch(Exception e){}
        }
    }

    /***********************************************
     * Method Name      : getCellData()
     * purpose          :
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static String getCellData(String fileName, String sheetName, String colName, int rowNum){
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        Row row = null;
        Cell cell = null;
        int colNum = 0;
        String cellData = null;
        Calendar cal = null;
        String sDay = null;
        String sMonth = null;
        String sYear = null;
        boolean isColumnExist = false;
        try{
            fin = new FileInputStream(System.getProperty("user.dir")+"\\reflection\\"+fileName);
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("The sheet '"+sheetName+"' doesnot exist");
                return null;
            }

            //find column number based on columnName specified.
            row = sh.getRow(0);
            int colCount = row.getPhysicalNumberOfCells();
            for(int c=0; c<colCount; c++){
                cell = row.getCell(c);
                if(cell.getStringCellValue().equalsIgnoreCase(colName)){
                    colNum = c;
                    isColumnExist = true;
                    break;
                }
            }

            if(isColumnExist){
                row = sh.getRow(rowNum);
                cell = row.getCell(colNum);

                //format the cell data
                switch(cell.getCellType()){
                    case BLANK:
                        cellData = "";
                        break;
                    case STRING:
                        cellData = cell.getStringCellValue();
                        break;
                    case BOOLEAN:
                        cellData = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case NUMERIC:
                        if(DateUtil.isCellDateFormatted(cell)==true){
                            double dt = cell.getNumericCellValue();
                            cal = Calendar.getInstance();
                            cal.setTime(DateUtil.getJavaDate(dt));

                            if(cal.get(Calendar.DAY_OF_MONTH) < 10){
                                sDay = "0" + cal.get(Calendar.DAY_OF_MONTH);
                            }else{
                                sDay = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
                            }

                            if((cal.get(Calendar.MONTH)+1) < 10){
                                sMonth = "0" + (cal.get(Calendar.MONTH)+1);
                            }else{
                                sMonth = String.valueOf((cal.get(Calendar.MONTH)+1));
                            }

                            cellData = sDay + "/" + sMonth +"/"+ String.valueOf(cal.get(Calendar.YEAR));
                        }else{
                            cellData = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    default:
                        System.out.println("Invalid cell datatype present in excel file");
                        return null;
                }
                return cellData;
            }else{
                System.out.println("Failed to get the mentioned column Name '"+colName+"' in the excelFile");
                return null;
            }

        }catch(Exception e){
            System.out.println("Exception in 'getCellData()' method. "+e);
            return null;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                cell = null;
                row = null;
                sh = null;
                wb.close();
                wb = null;
            }catch(Exception e){}
        }
    }


    /***********************************************
     * Method Name      : getCellData()
     * purpose          :
     * parms            :
     * return type      :
     * call Statement   :
     **********************************************/
    public static int getRowCount(String fileName, String sheetName){
        FileInputStream fin = null;
        Workbook wb = null;
        Sheet sh = null;
        try{
            fin = new FileInputStream(System.getProperty("user.dir")+"\\reflection\\"+fileName);
            wb = new XSSFWorkbook(fin);
            sh = wb.getSheet(sheetName);
            if(sh==null){
                System.out.println("The sheet '"+sheetName+"' doesnot exist");
                return -1;
            }

            return sh.getPhysicalNumberOfRows()-1;
        }catch(Exception e){
            System.out.println("Exception in 'getRowCount()' method. "+e);
            return -1;
        }
        finally
        {
            try{
                fin.close();
                fin = null;
                sh = null;
                wb.close();
                wb = null;
            }catch(Exception e){}
        }
    }
}
