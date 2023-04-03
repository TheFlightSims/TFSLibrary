package org.hsqldb.types;

import org.hsqldb.HsqlNameManager;
import org.hsqldb.SchemaObject;
import org.hsqldb.Session;
import org.hsqldb.lib.OrderedHashSet;
import org.hsqldb.rights.Grantee;

public class Charset implements SchemaObject {
  public static final int[][] uppercaseLetters = new int[][] { { 65, 90 } };
  
  public static final int[][] unquotedIdentifier = new int[][] { { 48, 57 }, { 65, 90 }, { 95, 95 } };
  
  public static final int[][] basicIdentifier = new int[][] { { 48, 57 }, { 65, 90 }, { 95, 95 }, { 97, 122 } };
  
  public static final Charset SQL_TEXT;
  
  public static final Charset SQL_IDENTIFIER_CHARSET;
  
  public static final Charset SQL_CHARACTER;
  
  public static final Charset ASCII_GRAPHIC;
  
  public static final Charset GRAPHIC_IRV;
  
  public static final Charset ASCII_FULL;
  
  public static final Charset ISO8BIT;
  
  public static final Charset LATIN1;
  
  public static final Charset UTF32;
  
  public static final Charset UTF16;
  
  public static final Charset UTF8;
  
  HsqlNameManager.HsqlName name;
  
  public HsqlNameManager.HsqlName base;
  
  int[][] ranges;
  
  public Charset(HsqlNameManager.HsqlName paramHsqlName) {
    this.name = paramHsqlName;
  }
  
  public int getType() {
    return 14;
  }
  
  public HsqlNameManager.HsqlName getName() {
    return this.name;
  }
  
  public HsqlNameManager.HsqlName getCatalogName() {
    return this.name.schema.schema;
  }
  
  public HsqlNameManager.HsqlName getSchemaName() {
    return this.name.schema;
  }
  
  public Grantee getOwner() {
    return this.name.schema.owner;
  }
  
  public OrderedHashSet getReferences() {
    OrderedHashSet orderedHashSet = new OrderedHashSet();
    orderedHashSet.add(this.base);
    return orderedHashSet;
  }
  
  public OrderedHashSet getComponents() {
    return null;
  }
  
  public void compile(Session paramSession, SchemaObject paramSchemaObject) {}
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ').append("CHARACTER").append(' ').append("SET").append(' ');
    if ("INFORMATION_SCHEMA".equals(this.name.schema.name)) {
      stringBuffer.append(this.name.getStatementName());
    } else {
      stringBuffer.append(this.name.getSchemaQualifiedStatementName());
    } 
    stringBuffer.append(' ').append("AS").append(' ').append("GET");
    stringBuffer.append(' ');
    if ("INFORMATION_SCHEMA".equals(this.base.schema.name)) {
      stringBuffer.append(this.base.getStatementName());
    } else {
      stringBuffer.append(this.base.getSchemaQualifiedStatementName());
    } 
    return stringBuffer.toString();
  }
  
  public long getChangeTimestamp() {
    return 0L;
  }
  
  public static boolean isInSet(String paramString, int[][] paramArrayOfint) {
    int i = paramString.length();
    for (byte b = 0; b < i; b++) {
      char c = paramString.charAt(b);
      byte b1 = 0;
      while (true) {
        if (b1 < paramArrayOfint.length) {
          if (c > paramArrayOfint[b1][1]) {
            b1++;
            continue;
          } 
          if (c < paramArrayOfint[b1][0])
            return false; 
          break;
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public static boolean startsWith(String paramString, int[][] paramArrayOfint) {
    char c = paramString.charAt(0);
    byte b = 0;
    while (b < paramArrayOfint.length) {
      if (c > paramArrayOfint[b][1]) {
        b++;
        continue;
      } 
      return !(c < paramArrayOfint[b][0]);
    } 
    return false;
  }
  
  public static Charset getDefaultInstance() {
    return SQL_TEXT;
  }
  
  static {
    HsqlNameManager.HsqlName hsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_TEXT", false, 14);
    SQL_TEXT = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_IDENTIFIER", false, 14);
    SQL_IDENTIFIER_CHARSET = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("SQL_CHARACTER", false, 14);
    SQL_CHARACTER = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("LATIN1", false, 14);
    LATIN1 = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("ASCII_GRAPHIC", false, 14);
    ASCII_GRAPHIC = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("GRAPHIC_IRV", false, 14);
    GRAPHIC_IRV = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("ASCII_FULL", false, 14);
    ASCII_FULL = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("ISO8BIT", false, 14);
    ISO8BIT = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF32", false, 14);
    UTF32 = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF16", false, 14);
    UTF16 = new Charset(hsqlName);
    hsqlName = HsqlNameManager.newInfoSchemaObjectName("UTF8", false, 14);
    UTF8 = new Charset(hsqlName);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\Charset.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */