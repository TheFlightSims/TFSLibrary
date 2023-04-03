package org.hsqldb.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MainInvoker {
  private static String[] emptyStringArray = new String[0];
  
  public static String LS = System.getProperty("line.separator");
  
  private static String SYNTAX_MSG = "    java org.hsqldb.util.MainInvoker [package1.Class1 [arg1a arg1b...] \"\"]... \\\n    packageX.ClassX [argXa argXb...]\nOR\n    java org.hsqldb.util.MainInvoker --help\n\nNote that you can only invoke classes in 'named' (non-default) packages.  Delimit multiple classes with empty strings.";
  
  private static void syntaxFailure() {
    System.err.println(SYNTAX_MSG);
    System.exit(2);
  }
  
  public static void main(String[] paramArrayOfString) {
    if (paramArrayOfString.length > 0 && paramArrayOfString[0].equals("--help")) {
      System.err.println(SYNTAX_MSG);
      System.exit(0);
    } 
    ArrayList<String> arrayList = new ArrayList();
    byte b = -1;
    try {
      while (++b < paramArrayOfString.length) {
        if (paramArrayOfString[b].length() < 1) {
          if (arrayList.size() < 1)
            syntaxFailure(); 
          invoke(arrayList.remove(0), arrayList.<String>toArray(emptyStringArray));
          arrayList.clear();
          continue;
        } 
        arrayList.add(paramArrayOfString[b]);
      } 
      if (arrayList.size() < 1)
        syntaxFailure(); 
      invoke(arrayList.remove(0), arrayList.<String>toArray(emptyStringArray));
    } catch (Exception exception) {
      exception.printStackTrace();
      System.exit(1);
    } 
  }
  
  public static void invoke(String paramString, String[] paramArrayOfString) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    Class[] arrayOfClass = { emptyStringArray.getClass() };
    Object[] arrayOfObject = { (paramArrayOfString == null) ? emptyStringArray : paramArrayOfString };
    Class<?> clazz = Class.forName(paramString);
    Method method = clazz.getMethod("main", arrayOfClass);
    method.invoke(null, arrayOfObject);
  }
  
  static {
    if (!LS.equals("\n"))
      SYNTAX_MSG = SYNTAX_MSG.replaceAll("\n", LS); 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\MainInvoker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */