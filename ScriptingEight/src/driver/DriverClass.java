package driver;

import methods.AppIndependentMethods;

import java.lang.reflect.Method;

public class DriverClass {
    public static void main(String[] args) {
        Class cls = null;
        Object obj = null;
        Method script = null;
        int count = 0;
        try{
            int rows = AppIndependentMethods.getRowCount("Controller.xlsx", "Controller");
            for(int r=0; r<rows; r++){
                String scriptName = AppIndependentMethods.getCellData("Controller.xlsx", "Controller", "scriptName", r+1);
                String className = AppIndependentMethods.getCellData("Controller.xlsx", "Controller", "className", r+1);
                String executeTest = AppIndependentMethods.getCellData("Controller.xlsx", "Controller", "ExecuteTest", r+1);
                if(executeTest.equalsIgnoreCase("Yes")){
                    cls = Class.forName(className);
                    obj = cls.getDeclaredConstructor().newInstance();
                    script = obj.getClass().getMethod(scriptName);
                    script.invoke(obj);
                    count++;
                }
            }
            if(count==0){
                System.out.println("No script is selected for execution. Please select atleast one test");
            }
        }catch(Exception e){
            System.out.println("Exception in 'DriverClass'"+e);
        }finally{

        }
    }
}
