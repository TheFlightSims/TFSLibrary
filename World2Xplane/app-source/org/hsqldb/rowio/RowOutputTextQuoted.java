package org.hsqldb.rowio;

import org.hsqldb.lib.StringConverter;

public class RowOutputTextQuoted extends RowOutputText {
  public RowOutputTextQuoted(String paramString1, String paramString2, String paramString3, boolean paramBoolean, String paramString4) {
    super(paramString1, paramString2, paramString3, paramBoolean, paramString4);
  }
  
  protected String checkConvertString(String paramString1, String paramString2) {
    if (this.allQuoted || paramString1.length() == 0 || paramString1.indexOf('"') != -1 || (paramString2.length() > 0 && paramString1.indexOf(paramString2) != -1) || hasUnprintable(paramString1))
      paramString1 = StringConverter.toQuotedString(paramString1, '"', true); 
    return paramString1;
  }
  
  private static boolean hasUnprintable(String paramString) {
    byte b = 0;
    int i = paramString.length();
    while (b < i) {
      if (Character.isISOControl(paramString.charAt(b)))
        return true; 
      b++;
    } 
    return false;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rowio\RowOutputTextQuoted.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */