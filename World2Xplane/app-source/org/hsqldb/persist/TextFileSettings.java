package org.hsqldb.persist;

import org.hsqldb.Database;
import org.hsqldb.error.Error;

public class TextFileSettings {
  public static final String NL = System.getProperty("line.separator");
  
  public String fs;
  
  public String vs;
  
  public String lvs;
  
  public String stringEncoding;
  
  public boolean isQuoted;
  
  public boolean isAllQuoted;
  
  public boolean ignoreFirst;
  
  Database database;
  
  String dataFileName;
  
  int maxCacheRows;
  
  int maxCacheBytes;
  
  static final byte[] BYTES_LINE_SEP = NL.getBytes();
  
  static final char DOUBLE_QUOTE_CHAR = '"';
  
  static final char BACKSLASH_CHAR = '\\';
  
  static final char LF_CHAR = '\n';
  
  static final char CR_CHAR = '\r';
  
  TextFileSettings(Database paramDatabase, String paramString) {
    this.database = paramDatabase;
    HsqlProperties hsqlProperties = HsqlProperties.delimitedArgPairsToProps(paramString, "=", ";", "textdb");
    HsqlDatabaseProperties hsqlDatabaseProperties = paramDatabase.getProperties();
    switch (hsqlProperties.errorCodes.length) {
      case 0:
        this.dataFileName = null;
        break;
      case 1:
        this.dataFileName = hsqlProperties.errorKeys[0].trim();
        break;
      default:
        throw Error.error(302);
    } 
    this.fs = hsqlDatabaseProperties.getStringProperty("textdb.fs");
    this.fs = hsqlProperties.getProperty("textdb.fs", this.fs);
    this.vs = hsqlDatabaseProperties.getStringProperty("textdb.vs");
    this.vs = hsqlProperties.getProperty("textdb.vs", this.vs);
    this.lvs = hsqlDatabaseProperties.getStringProperty("textdb.lvs");
    this.lvs = hsqlProperties.getProperty("textdb.lvs", this.lvs);
    if (this.vs == null)
      this.vs = this.fs; 
    if (this.lvs == null)
      this.lvs = this.fs; 
    this.fs = translateSep(this.fs);
    this.vs = translateSep(this.vs);
    this.lvs = translateSep(this.lvs);
    if (this.fs.length() == 0 || this.vs.length() == 0 || this.lvs.length() == 0)
      throw Error.error(303); 
    this.ignoreFirst = hsqlDatabaseProperties.isPropertyTrue("textdb.ignore_first");
    this.ignoreFirst = hsqlProperties.isPropertyTrue("textdb.ignore_first", this.ignoreFirst);
    this.isQuoted = hsqlDatabaseProperties.isPropertyTrue("textdb.quoted");
    this.isQuoted = hsqlProperties.isPropertyTrue("textdb.quoted", this.isQuoted);
    this.isAllQuoted = hsqlDatabaseProperties.isPropertyTrue("textdb.all_quoted");
    this.isAllQuoted = hsqlProperties.isPropertyTrue("textdb.all_quoted", this.isAllQuoted);
    this.stringEncoding = hsqlDatabaseProperties.getStringProperty("textdb.encoding");
    this.stringEncoding = hsqlProperties.getProperty("textdb.encoding", this.stringEncoding);
    int i = hsqlDatabaseProperties.getIntegerProperty("textdb.cache_scale");
    i = hsqlProperties.getIntegerProperty("textdb.cache_scale", i);
    int j = hsqlDatabaseProperties.getIntegerProperty("textdb.cache_size_scale");
    j = hsqlProperties.getIntegerProperty("textdb.cache_size_scale", j);
    this.maxCacheRows = (1 << i) * 3;
    this.maxCacheRows = hsqlDatabaseProperties.getIntegerProperty("textdb.cache_rows", this.maxCacheRows);
    this.maxCacheRows = hsqlProperties.getIntegerProperty("textdb.cache_rows", this.maxCacheRows);
    this.maxCacheBytes = (1 << j) * this.maxCacheRows / 1024;
    if (this.maxCacheBytes < 4)
      this.maxCacheBytes = 4; 
    this.maxCacheBytes = hsqlDatabaseProperties.getIntegerProperty("textdb.cache_size", this.maxCacheBytes);
    this.maxCacheBytes = hsqlProperties.getIntegerProperty("textdb.cache_size", this.maxCacheBytes);
    this.maxCacheBytes *= 1024;
  }
  
  String getFileName() {
    return this.dataFileName;
  }
  
  int getMaxCacheRows() {
    return this.maxCacheRows;
  }
  
  int getMaxCacheBytes() {
    return this.maxCacheBytes;
  }
  
  private static String translateSep(String paramString) {
    return translateSep(paramString, false);
  }
  
  private static String translateSep(String paramString, boolean paramBoolean) {
    if (paramString == null)
      return null; 
    int i = paramString.indexOf('\\');
    if (i != -1) {
      int j = 0;
      char[] arrayOfChar = paramString.toCharArray();
      char c = Character.MIN_VALUE;
      int k = paramString.length();
      StringBuffer stringBuffer = new StringBuffer(k);
      do {
        stringBuffer.append(arrayOfChar, j, i - j);
        j = ++i;
        if (i >= k) {
          stringBuffer.append('\\');
          break;
        } 
        if (!paramBoolean)
          c = arrayOfChar[i]; 
        if (c == 'n') {
          stringBuffer.append('\n');
          j++;
        } else if (c == 'r') {
          stringBuffer.append('\r');
          j++;
        } else if (c == 't') {
          stringBuffer.append('\t');
          j++;
        } else if (c == '\\') {
          stringBuffer.append('\\');
          j++;
        } else if (c == 'u') {
          stringBuffer.append((char)Integer.parseInt(paramString.substring(++j, j + 4), 16));
          j += 4;
        } else if (paramString.startsWith("semi", i)) {
          stringBuffer.append(';');
          j += 4;
        } else if (paramString.startsWith("space", i)) {
          stringBuffer.append(' ');
          j += 5;
        } else if (paramString.startsWith("quote", i)) {
          stringBuffer.append('"');
          j += 5;
        } else if (paramString.startsWith("apos", i)) {
          stringBuffer.append('\'');
          j += 4;
        } else {
          stringBuffer.append('\\');
          stringBuffer.append(arrayOfChar[i]);
          j++;
        } 
      } while ((i = paramString.indexOf('\\', j)) != -1);
      stringBuffer.append(arrayOfChar, j, k - j);
      paramString = stringBuffer.toString();
    } 
    return paramString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\persist\TextFileSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */