package org.hsqldb.persist;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.FileAccess;
import org.hsqldb.lib.FileUtil;
import org.hsqldb.lib.HashMap;
import org.hsqldb.map.ValuePool;

public class HsqlProperties {
  public static final int ANY_ERROR = 0;
  
  public static final int NO_VALUE_FOR_KEY = 1;
  
  protected String fileName;
  
  protected String fileExtension = "";
  
  protected Properties stringProps = new Properties();
  
  protected int[] errorCodes = ValuePool.emptyIntArray;
  
  protected String[] errorKeys = ValuePool.emptyStringArray;
  
  protected boolean resource = false;
  
  protected FileAccess fa;
  
  protected HashMap metaData;
  
  public static final int indexName = 0;
  
  public static final int indexType = 1;
  
  public static final int indexClass = 2;
  
  public static final int indexIsRange = 3;
  
  public static final int indexDefaultValue = 4;
  
  public static final int indexRangeLow = 5;
  
  public static final int indexRangeHigh = 6;
  
  public static final int indexValues = 7;
  
  public static final int indexLimit = 9;
  
  public HsqlProperties() {
    this.fileName = null;
  }
  
  public HsqlProperties(String paramString) {
    this(paramString, ".properties");
  }
  
  public HsqlProperties(String paramString1, String paramString2) {
    this.fileName = paramString1;
    this.fileExtension = paramString2;
    this.fa = (FileAccess)FileUtil.getFileUtil();
  }
  
  public HsqlProperties(HashMap paramHashMap, String paramString, FileAccess paramFileAccess, boolean paramBoolean) {
    this.fileName = paramString;
    this.fileExtension = ".properties";
    this.fa = paramFileAccess;
    this.metaData = paramHashMap;
  }
  
  public HsqlProperties(Properties paramProperties) {}
  
  public void setFileName(String paramString) {
    this.fileName = paramString;
  }
  
  public String setProperty(String paramString, int paramInt) {
    return setProperty(paramString, Integer.toString(paramInt));
  }
  
  public String setProperty(String paramString, boolean paramBoolean) {
    return setProperty(paramString, String.valueOf(paramBoolean));
  }
  
  public String setProperty(String paramString1, String paramString2) {
    return (String)this.stringProps.put(paramString1, paramString2);
  }
  
  public String setPropertyIfNotExists(String paramString1, String paramString2) {
    paramString2 = getProperty(paramString1, paramString2);
    return setProperty(paramString1, paramString2);
  }
  
  public Properties getProperties() {
    return this.stringProps;
  }
  
  public String getProperty(String paramString) {
    return this.stringProps.getProperty(paramString);
  }
  
  public String getProperty(String paramString1, String paramString2) {
    return this.stringProps.getProperty(paramString1, paramString2);
  }
  
  public int getIntegerProperty(String paramString, int paramInt) {
    return getIntegerProperty(this.stringProps, paramString, paramInt);
  }
  
  public static int getIntegerProperty(Properties paramProperties, String paramString, int paramInt) {
    String str = paramProperties.getProperty(paramString);
    try {
      if (str != null) {
        str = str.trim();
        paramInt = Integer.parseInt(str);
      } 
    } catch (NumberFormatException numberFormatException) {}
    return paramInt;
  }
  
  public boolean isPropertyTrue(String paramString) {
    return isPropertyTrue(paramString, false);
  }
  
  public boolean isPropertyTrue(String paramString, boolean paramBoolean) {
    String str = this.stringProps.getProperty(paramString);
    if (str == null)
      return paramBoolean; 
    str = str.trim();
    return str.toLowerCase().equals("true");
  }
  
  public void removeProperty(String paramString) {
    this.stringProps.remove(paramString);
  }
  
  public void addProperties(Properties paramProperties) {
    if (paramProperties == null)
      return; 
    Enumeration<?> enumeration = paramProperties.propertyNames();
    while (enumeration.hasMoreElements()) {
      String str1 = (String)enumeration.nextElement();
      String str2 = paramProperties.getProperty(str1);
      this.stringProps.put(str1, str2);
    } 
  }
  
  public void addProperties(HsqlProperties paramHsqlProperties) {
    if (paramHsqlProperties == null)
      return; 
    addProperties(paramHsqlProperties.stringProps);
  }
  
  public boolean propertiesFileExists() {
    if (this.fileName == null)
      return false; 
    String str = this.fileName + this.fileExtension;
    return this.fa.isStreamElement(str);
  }
  
  public boolean load() throws Exception {
    if (this.fileName == null || this.fileName.length() == 0)
      throw new FileNotFoundException(Error.getMessage(28)); 
    if (!propertiesFileExists())
      return false; 
    InputStream inputStream = null;
    String str = this.fileName + this.fileExtension;
    try {
      inputStream = this.fa.openInputStreamElement(str);
      this.stringProps.load(inputStream);
    } finally {
      if (inputStream != null)
        inputStream.close(); 
    } 
    return true;
  }
  
  public void save() throws Exception {
    if (this.fileName == null || this.fileName.length() == 0)
      throw new FileNotFoundException(Error.getMessage(28)); 
    String str = this.fileName + this.fileExtension;
    save(str);
  }
  
  public void save(String paramString) throws Exception {
    this.fa.createParentDirs(paramString);
    OutputStream outputStream = this.fa.openOutputStreamElement(paramString);
    FileAccess.FileSync fileSync = this.fa.getFileSync(outputStream);
    String str = "HSQL Database Engine 2.3.0";
    this.stringProps.store(outputStream, str);
    outputStream.flush();
    fileSync.sync();
    outputStream.close();
    fileSync = null;
    outputStream = null;
  }
  
  protected void addError(int paramInt, String paramString) {
    this.errorCodes = (int[])ArrayUtil.resizeArray(this.errorCodes, this.errorCodes.length + 1);
    this.errorKeys = (String[])ArrayUtil.resizeArray(this.errorKeys, this.errorKeys.length + 1);
    this.errorCodes[this.errorCodes.length - 1] = paramInt;
    this.errorKeys[this.errorKeys.length - 1] = paramString;
  }
  
  public static HsqlProperties argArrayToProps(String[] paramArrayOfString, String paramString) {
    HsqlProperties hsqlProperties = new HsqlProperties();
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      String str = paramArrayOfString[b];
      if (str.equals("--help") || str.equals("-help")) {
        hsqlProperties.addError(1, str.substring(1));
      } else if (str.startsWith("--")) {
        String str1 = (b + 1 < paramArrayOfString.length) ? paramArrayOfString[b + 1] : "";
        hsqlProperties.setProperty(paramString + "." + str.substring(2), str1);
        b++;
      } else if (str.charAt(0) == '-') {
        String str1 = (b + 1 < paramArrayOfString.length) ? paramArrayOfString[b + 1] : "";
        hsqlProperties.setProperty(paramString + "." + str.substring(1), str1);
        b++;
      } 
    } 
    return hsqlProperties;
  }
  
  public static HsqlProperties delimitedArgPairsToProps(String paramString1, String paramString2, String paramString3, String paramString4) {
    HsqlProperties hsqlProperties = new HsqlProperties();
    for (int i = 0;; i = j + paramString3.length()) {
      int j = paramString1.indexOf(paramString3, i);
      if (j == -1)
        j = paramString1.length(); 
      int k = paramString1.substring(0, j).indexOf(paramString2, i);
      if (k == -1) {
        hsqlProperties.addError(1, paramString1.substring(i, j).trim());
      } else {
        String str1 = paramString1.substring(i, k).trim();
        String str2 = paramString1.substring(k + paramString2.length(), j).trim();
        if (paramString4 != null)
          str1 = paramString4 + "." + str1; 
        hsqlProperties.setProperty(str1, str2);
      } 
      if (j == paramString1.length())
        return hsqlProperties; 
    } 
  }
  
  public Enumeration propertyNames() {
    return this.stringProps.propertyNames();
  }
  
  public boolean isEmpty() {
    return this.stringProps.isEmpty();
  }
  
  public String[] getErrorKeys() {
    return this.errorKeys;
  }
  
  public void validate() {}
  
  public static Object[] getMeta(String paramString1, int paramInt, String paramString2) {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = ValuePool.getInt(paramInt);
    arrayOfObject[2] = "String";
    arrayOfObject[4] = paramString2;
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt, boolean paramBoolean) {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt);
    arrayOfObject[2] = "Boolean";
    arrayOfObject[4] = paramBoolean ? Boolean.TRUE : Boolean.FALSE;
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfint) {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = "Integer";
    arrayOfObject[4] = ValuePool.getInt(paramInt2);
    arrayOfObject[7] = paramArrayOfint;
    return arrayOfObject;
  }
  
  public static Object[] getMeta(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    Object[] arrayOfObject = new Object[9];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = ValuePool.getInt(paramInt1);
    arrayOfObject[2] = "Integer";
    arrayOfObject[4] = ValuePool.getInt(paramInt2);
    arrayOfObject[3] = Boolean.TRUE;
    arrayOfObject[5] = ValuePool.getInt(paramInt3);
    arrayOfObject[6] = ValuePool.getInt(paramInt4);
    return arrayOfObject;
  }
  
  public static String validateProperty(String paramString1, String paramString2, Object[] paramArrayOfObject) {
    if (paramArrayOfObject[2].equals("Boolean")) {
      paramString2 = paramString2.toLowerCase();
      return (paramString2.equals("true") || paramString2.equals("false")) ? null : ("invalid boolean value for property: " + paramString1);
    } 
    if (paramArrayOfObject[2].equals("String"))
      return null; 
    if (paramArrayOfObject[2].equals("Integer")) {
      int i = Integer.parseInt(paramString2);
      if (Boolean.TRUE.equals(paramArrayOfObject[3])) {
        int j = ((Integer)paramArrayOfObject[5]).intValue();
        int k = ((Integer)paramArrayOfObject[6]).intValue();
        if (i < j || k < i)
          return "value outside range for property: " + paramString1; 
      } 
      if (paramArrayOfObject[7] != null) {
        int[] arrayOfInt = (int[])paramArrayOfObject[7];
        if (ArrayUtil.find(arrayOfInt, i) == -1)
          return "value not supported for property: " + paramString1; 
      } 
      return null;
    } 
    return null;
  }
  
  public boolean validateProperty(String paramString, int paramInt) {
    Object[] arrayOfObject = (Object[])this.metaData.get(paramString);
    if (arrayOfObject == null)
      return false; 
    if (arrayOfObject[2].equals("Integer")) {
      if (Boolean.TRUE.equals(arrayOfObject[3])) {
        int i = ((Integer)arrayOfObject[5]).intValue();
        int j = ((Integer)arrayOfObject[6]).intValue();
        if (paramInt < i || j < paramInt)
          return false; 
      } 
      if (arrayOfObject[7] != null) {
        int[] arrayOfInt = (int[])arrayOfObject[7];
        if (ArrayUtil.find(arrayOfInt, paramInt) == -1)
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('[');
    int i = this.stringProps.size();
    Enumeration<?> enumeration = this.stringProps.propertyNames();
    for (byte b = 0; b < i; b++) {
      String str = (String)enumeration.nextElement();
      stringBuffer.append(str);
      stringBuffer.append('=');
      stringBuffer.append(this.stringProps.get(str));
      if (b + 1 < i) {
        stringBuffer.append(',');
        stringBuffer.append(' ');
      } 
      stringBuffer.append(']');
    } 
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\HsqlProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */