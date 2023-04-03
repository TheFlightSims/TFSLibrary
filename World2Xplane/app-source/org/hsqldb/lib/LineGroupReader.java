package org.hsqldb.lib;

import java.io.LineNumberReader;
import org.hsqldb.map.ValuePool;

public class LineGroupReader {
  private static final String[] defaultContinuations = new String[] { " ", "*" };
  
  private static final String[] defaultIgnoredStarts = new String[] { "--" };
  
  static final String LS = System.getProperty("line.separator", "\n");
  
  LineNumberReader reader;
  
  String nextStartLine = null;
  
  int startLineNumber = 0;
  
  int nextStartLineNumber = 0;
  
  final String[] sectionContinuations;
  
  final String[] sectionStarts;
  
  final String[] ignoredStarts;
  
  public LineGroupReader(LineNumberReader paramLineNumberReader) {
    this.sectionContinuations = defaultContinuations;
    this.sectionStarts = ValuePool.emptyStringArray;
    this.ignoredStarts = defaultIgnoredStarts;
    this.reader = paramLineNumberReader;
    try {
      getSection();
    } catch (Exception exception) {}
  }
  
  public LineGroupReader(LineNumberReader paramLineNumberReader, String[] paramArrayOfString) {
    this.sectionStarts = paramArrayOfString;
    this.sectionContinuations = ValuePool.emptyStringArray;
    this.ignoredStarts = ValuePool.emptyStringArray;
    this.reader = paramLineNumberReader;
    try {
      getSection();
    } catch (Exception exception) {}
  }
  
  public HsqlArrayList getSection() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList((Object[])new String[8], 0);
    if (this.nextStartLine != null) {
      hsqlArrayList.add(this.nextStartLine);
      this.startLineNumber = this.nextStartLineNumber;
    } 
    while (true) {
      boolean bool = false;
      String str = null;
      try {
        str = this.reader.readLine();
      } catch (Exception exception) {}
      if (str == null) {
        this.nextStartLine = null;
        return hsqlArrayList;
      } 
      str = str.substring(0, StringUtil.rightTrimSize(str));
      if (str.length() == 0 || isIgnoredLine(str))
        continue; 
      if (isNewSectionLine(str))
        bool = true; 
      if (bool) {
        this.nextStartLine = str;
        this.nextStartLineNumber = this.reader.getLineNumber();
        return hsqlArrayList;
      } 
      hsqlArrayList.add(str);
    } 
  }
  
  public HashMappedList getAsMap() {
    HashMappedList hashMappedList = new HashMappedList();
    while (true) {
      HsqlArrayList hsqlArrayList = getSection();
      if (hsqlArrayList.size() < 1)
        return hashMappedList; 
      String str1 = (String)hsqlArrayList.get(0);
      String str2 = convertToString(hsqlArrayList, 1);
      hashMappedList.put(str1, str2);
    } 
  }
  
  private boolean isNewSectionLine(String paramString) {
    if (this.sectionStarts.length == 0) {
      for (byte b1 = 0; b1 < this.sectionContinuations.length; b1++) {
        if (paramString.startsWith(this.sectionContinuations[b1]))
          return false; 
      } 
      return true;
    } 
    for (byte b = 0; b < this.sectionStarts.length; b++) {
      if (paramString.startsWith(this.sectionStarts[b]))
        return true; 
    } 
    return false;
  }
  
  private boolean isIgnoredLine(String paramString) {
    for (byte b = 0; b < this.ignoredStarts.length; b++) {
      if (paramString.startsWith(this.ignoredStarts[b]))
        return true; 
    } 
    return false;
  }
  
  public int getStartLineNumber() {
    return this.startLineNumber;
  }
  
  public void close() {
    try {
      this.reader.close();
    } catch (Exception exception) {}
  }
  
  public static String convertToString(HsqlArrayList paramHsqlArrayList, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = paramInt; i < paramHsqlArrayList.size(); i++)
      stringBuffer.append(paramHsqlArrayList.get(i)).append(LS); 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\lib\LineGroupReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */