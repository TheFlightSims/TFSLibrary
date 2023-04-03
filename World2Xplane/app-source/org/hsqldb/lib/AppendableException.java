package org.hsqldb.lib;

import java.util.ArrayList;
import java.util.List;

public class AppendableException extends Exception {
  static final long serialVersionUID = -1002629580611098803L;
  
  public static String LS = System.getProperty("line.separator");
  
  public List<String> appendages = null;
  
  public String getMessage() {
    String str = super.getMessage();
    if (this.appendages == null)
      return str; 
    StringBuffer stringBuffer = new StringBuffer();
    if (str != null)
      stringBuffer.append(str); 
    for (String str1 : this.appendages) {
      if (stringBuffer.length() > 0)
        stringBuffer.append(LS); 
      stringBuffer.append(str1);
    } 
    return stringBuffer.toString();
  }
  
  public void appendMessage(String paramString) {
    if (this.appendages == null)
      this.appendages = new ArrayList<String>(); 
    this.appendages.add(paramString);
  }
  
  public AppendableException() {}
  
  public AppendableException(String paramString) {
    super(paramString);
  }
  
  public AppendableException(Throwable paramThrowable) {
    super(paramThrowable);
  }
  
  public AppendableException(String paramString, Throwable paramThrowable) {
    super(paramString, paramThrowable);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\AppendableException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */