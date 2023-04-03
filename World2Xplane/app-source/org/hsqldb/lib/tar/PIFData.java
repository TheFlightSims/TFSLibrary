package org.hsqldb.lib.tar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PIFData extends HashMap<String, String> {
  static final long serialVersionUID = 3086795680582315773L;
  
  private static Pattern pifRecordPattern = Pattern.compile("\\d+ +([^=]+)=(.*)");
  
  private Long sizeObject = null;
  
  public Long getSize() {
    return this.sizeObject;
  }
  
  public PIFData(InputStream paramInputStream) throws TarMalformatException, IOException {
    BufferedReader bufferedReader = null;
    try {
      bufferedReader = new BufferedReader(new InputStreamReader(paramInputStream, "UTF-8"));
      byte b = 0;
      String str1;
      while ((str1 = bufferedReader.readLine()) != null) {
        b++;
        Matcher matcher = pifRecordPattern.matcher(str1);
        if (!matcher.matches())
          throw new TarMalformatException(RB.pif_malformat.getString(b, str1)); 
        String str2 = matcher.group(1);
        String str3 = matcher.group(2);
        if (str3 == null || str3.length() < 1) {
          remove(str2);
          continue;
        } 
        put(str2, str3);
      } 
    } finally {
      try {
        paramInputStream.close();
      } finally {
        bufferedReader = null;
      } 
    } 
    String str = get("size");
    if (str != null)
      try {
        this.sizeObject = Long.valueOf(str);
      } catch (NumberFormatException numberFormatException) {
        throw new TarMalformatException(RB.pif_malformat_size.getString(new String[] { str }));
      }  
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\tar\PIFData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */