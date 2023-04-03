package org.hsqldb;

import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;
import org.hsqldb.rights.Grantee;

public final class HsqlNameManager {
  private static final HsqlNameManager staticManager = new HsqlNameManager();
  
  private static final HsqlName[] autoColumnNames;
  
  private static final String[] autoNoNameColumnNames;
  
  private int serialNumber = 1;
  
  private int sysNumber = 10000;
  
  private HsqlName catalogName;
  
  private boolean sqlRegularNames;
  
  HsqlName subqueryTableName;
  
  public HsqlNameManager() {
    this.sqlRegularNames = true;
  }
  
  public HsqlNameManager(Database paramDatabase) {
    this.catalogName = new HsqlName(this, "PUBLIC", 1, false);
    this.sqlRegularNames = paramDatabase.sqlRegularNames;
    this.subqueryTableName = new HsqlName(this, "SYSTEM_SUBQUERY", false, 3);
    this.subqueryTableName.schema = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
  }
  
  public HsqlName getCatalogName() {
    return this.catalogName;
  }
  
  public void setSqlRegularNames(boolean paramBoolean) {
    this.sqlRegularNames = paramBoolean;
  }
  
  public static HsqlName newSystemObjectName(String paramString, int paramInt) {
    return new HsqlName(staticManager, paramString, paramInt, false);
  }
  
  public static HsqlName newInfoSchemaColumnName(String paramString, HsqlName paramHsqlName) {
    HsqlName hsqlName = new HsqlName(staticManager, paramString, false, 9);
    hsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    hsqlName.parent = paramHsqlName;
    return hsqlName;
  }
  
  public static HsqlName newInfoSchemaTableName(String paramString) {
    HsqlName hsqlName = new HsqlName(staticManager, paramString, 3, false);
    hsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    return hsqlName;
  }
  
  public static HsqlName newInfoSchemaObjectName(String paramString, boolean paramBoolean, int paramInt) {
    HsqlName hsqlName = new HsqlName(staticManager, paramString, paramInt, paramBoolean);
    hsqlName.schema = SqlInvariants.INFORMATION_SCHEMA_HSQLNAME;
    return hsqlName;
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName, String paramString, int paramInt) {
    HsqlName hsqlName = new HsqlName(this, paramString, paramInt, false);
    hsqlName.schema = paramHsqlName;
    return hsqlName;
  }
  
  public HsqlName newHsqlName(String paramString, boolean paramBoolean, int paramInt) {
    return new HsqlName(this, paramString, paramBoolean, paramInt);
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName, String paramString, boolean paramBoolean, int paramInt) {
    HsqlName hsqlName = new HsqlName(this, paramString, paramBoolean, paramInt);
    hsqlName.schema = paramHsqlName;
    return hsqlName;
  }
  
  public HsqlName newHsqlName(HsqlName paramHsqlName1, String paramString, boolean paramBoolean, int paramInt, HsqlName paramHsqlName2) {
    HsqlName hsqlName = new HsqlName(this, paramString, paramBoolean, paramInt);
    hsqlName.schema = paramHsqlName1;
    hsqlName.parent = paramHsqlName2;
    return hsqlName;
  }
  
  public HsqlName newColumnSchemaHsqlName(HsqlName paramHsqlName, SimpleName paramSimpleName) {
    return newColumnHsqlName(paramHsqlName, paramSimpleName.name, paramSimpleName.isNameQuoted);
  }
  
  public HsqlName newColumnHsqlName(HsqlName paramHsqlName, String paramString, boolean paramBoolean) {
    HsqlName hsqlName = new HsqlName(this, paramString, paramBoolean, 9);
    hsqlName.schema = paramHsqlName.schema;
    hsqlName.parent = paramHsqlName;
    return hsqlName;
  }
  
  public HsqlName getSubqueryTableName() {
    return this.subqueryTableName;
  }
  
  public HsqlName newAutoName(String paramString, HsqlName paramHsqlName1, HsqlName paramHsqlName2, int paramInt) {
    return newAutoName(paramString, (String)null, paramHsqlName1, paramHsqlName2, paramInt);
  }
  
  public HsqlName newSpecificRoutineName(HsqlName paramHsqlName) {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(paramHsqlName.name).append('_').append(++this.sysNumber);
    HsqlName hsqlName = new HsqlName(this, stringBuffer.toString(), 24, paramHsqlName.isNameQuoted);
    hsqlName.parent = paramHsqlName;
    hsqlName.schema = paramHsqlName.schema;
    return hsqlName;
  }
  
  public static HsqlName getColumnName(SimpleName paramSimpleName) {
    return new HsqlName(staticManager, paramSimpleName.name, paramSimpleName.isNameQuoted, 9);
  }
  
  public static HsqlName getAutoColumnName(int paramInt) {
    return (paramInt < autoColumnNames.length) ? autoColumnNames[paramInt] : new HsqlName(staticManager, "C_" + (paramInt + 1), 9, false);
  }
  
  public static String getAutoColumnNameString(int paramInt) {
    return (paramInt < autoColumnNames.length) ? (autoColumnNames[paramInt]).name : ("C" + (paramInt + 1));
  }
  
  public static String getAutoNoNameColumnString(int paramInt) {
    return (paramInt < autoColumnNames.length) ? autoNoNameColumnNames[paramInt] : String.valueOf(paramInt);
  }
  
  public static String getAutoSavepointNameString(long paramLong, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer("S");
    stringBuffer.append(paramLong).append('_').append(paramInt);
    return stringBuffer.toString();
  }
  
  public HsqlName newAutoName(String paramString1, String paramString2, HsqlName paramHsqlName1, HsqlName paramHsqlName2, int paramInt) {
    StringBuffer stringBuffer = new StringBuffer();
    if (paramString1 != null) {
      if (paramString1.length() != 0) {
        stringBuffer.append("SYS_");
        stringBuffer.append(paramString1);
        stringBuffer.append('_');
        if (paramString2 != null) {
          stringBuffer.append(paramString2);
          stringBuffer.append('_');
        } 
        stringBuffer.append(++this.sysNumber);
      } 
    } else {
      stringBuffer.append(paramString2);
    } 
    HsqlName hsqlName = new HsqlName(this, stringBuffer.toString(), paramInt, false);
    hsqlName.schema = paramHsqlName1;
    hsqlName.parent = paramHsqlName2;
    return hsqlName;
  }
  
  public static SimpleName getSimpleName(String paramString, boolean paramBoolean) {
    return new SimpleName(paramString, paramBoolean);
  }
  
  static {
    staticManager.serialNumber = Integer.MIN_VALUE;
    autoColumnNames = new HsqlName[32];
    autoNoNameColumnNames = new String[32];
    for (byte b = 0; b < autoColumnNames.length; b++) {
      autoColumnNames[b] = new HsqlName(staticManager, "C" + (b + 1), 0, false);
      autoNoNameColumnNames[b] = String.valueOf(b);
    } 
  }
  
  public static final class HsqlName extends SimpleName {
    static HsqlName[] emptyArray = new HsqlName[0];
    
    HsqlNameManager manager;
    
    public String statementName;
    
    public String comment;
    
    public HsqlName schema;
    
    public HsqlName parent;
    
    public Grantee owner;
    
    public final int type;
    
    private final int hashCode;
    
    static final String[] sysPrefixes = new String[] { "SYS_IDX_", "SYS_PK_", "SYS_REF_", "SYS_CT_", "SYS_FK_" };
    
    private HsqlName(HsqlNameManager param1HsqlNameManager, int param1Int) {
      this.manager = param1HsqlNameManager;
      this.type = param1Int;
      this.hashCode = this.manager.serialNumber++;
    }
    
    private HsqlName(HsqlNameManager param1HsqlNameManager, String param1String, boolean param1Boolean, int param1Int) {
      this(param1HsqlNameManager, param1Int);
      rename(param1String, param1Boolean);
    }
    
    private HsqlName(HsqlNameManager param1HsqlNameManager, String param1String, int param1Int, boolean param1Boolean) {
      this(param1HsqlNameManager, param1Int);
      this.name = param1String;
      this.statementName = param1String;
      this.isNameQuoted = param1Boolean;
      if (this.isNameQuoted)
        this.statementName = StringConverter.toQuotedString(param1String, '"', true); 
    }
    
    public String getStatementName() {
      return this.statementName;
    }
    
    public String getSchemaQualifiedStatementName() {
      switch (this.type) {
        case 22:
        case 23:
          return this.statementName;
        case 9:
          if (this.parent == null || "SYSTEM_SUBQUERY".equals(this.parent.name))
            return this.statementName; 
          stringBuffer = new StringBuffer();
          if (this.schema != null) {
            stringBuffer.append(this.schema.getStatementName());
            stringBuffer.append('.');
          } 
          stringBuffer.append(this.parent.getStatementName());
          stringBuffer.append('.');
          stringBuffer.append(this.statementName);
          return stringBuffer.toString();
      } 
      if (this.schema == null)
        return this.statementName; 
      StringBuffer stringBuffer = new StringBuffer();
      if (this.schema != null) {
        stringBuffer.append(this.schema.getStatementName());
        stringBuffer.append('.');
      } 
      stringBuffer.append(this.statementName);
      return stringBuffer.toString();
    }
    
    public void rename(HsqlName param1HsqlName) {
      rename(param1HsqlName.name, param1HsqlName.isNameQuoted);
    }
    
    public void rename(String param1String, boolean param1Boolean) {
      if (this.manager.sqlRegularNames && param1String.length() > 128)
        throw Error.error(5501, param1String); 
      this.name = new String(param1String);
      this.statementName = this.name;
      this.isNameQuoted = param1Boolean;
      if (this.isNameQuoted)
        this.statementName = StringConverter.toQuotedString(param1String, '"', true); 
      if (param1String.startsWith("SYS_")) {
        int i = param1String.lastIndexOf('_') + 1;
        try {
          int j = Integer.parseInt(param1String.substring(i));
          if (j > this.manager.sysNumber)
            this.manager.sysNumber = j; 
        } catch (NumberFormatException numberFormatException) {}
      } 
    }
    
    void rename(String param1String1, String param1String2, boolean param1Boolean) {
      StringBuffer stringBuffer = new StringBuffer(param1String1);
      stringBuffer.append('_');
      stringBuffer.append(param1String2);
      rename(stringBuffer.toString(), param1Boolean);
    }
    
    public void setSchemaIfNull(HsqlName param1HsqlName) {
      if (this.schema == null)
        this.schema = param1HsqlName; 
    }
    
    public boolean equals(Object param1Object) {
      return (param1Object instanceof HsqlName) ? ((this.hashCode == ((HsqlName)param1Object).hashCode)) : false;
    }
    
    public int hashCode() {
      return this.hashCode;
    }
    
    static int sysPrefixLength(String param1String) {
      for (byte b = 0; b < sysPrefixes.length; b++) {
        if (param1String.startsWith(sysPrefixes[b]))
          return sysPrefixes[b].length(); 
      } 
      return 0;
    }
    
    static boolean isReservedName(String param1String) {
      return (sysPrefixLength(param1String) > 0);
    }
    
    boolean isReservedName() {
      return isReservedName(this.name);
    }
    
    public String toString() {
      return getClass().getName() + super.hashCode() + "[this.hashCode()=" + this.hashCode + ", name=" + this.name + ", name.hashCode()=" + this.name.hashCode() + ", isNameQuoted=" + this.isNameQuoted + "]";
    }
    
    public int compareTo(Object param1Object) {
      return this.hashCode - param1Object.hashCode();
    }
    
    static boolean isRegularIdentifier(String param1String) {
      byte b = 0;
      int i = param1String.length();
      while (b < i) {
        char c = param1String.charAt(b);
        if ((c >= 'A' && c <= 'Z') || (c == '_' && b > 0) || (c >= '0' && c <= '9')) {
          b++;
          continue;
        } 
        return false;
      } 
      return !Tokens.isKeyword(param1String);
    }
  }
  
  public static class SimpleName {
    public String name;
    
    public boolean isNameQuoted;
    
    private SimpleName() {}
    
    private SimpleName(String param1String, boolean param1Boolean) {
      this.name = new String(param1String);
      this.isNameQuoted = param1Boolean;
    }
    
    public int hashCode() {
      return this.name.hashCode();
    }
    
    public boolean equals(Object param1Object) {
      return (param1Object instanceof SimpleName) ? ((((SimpleName)param1Object).isNameQuoted == this.isNameQuoted && ((SimpleName)param1Object).name.equals(this.name))) : false;
    }
    
    public String getStatementName() {
      return this.isNameQuoted ? StringConverter.toQuotedString(this.name, '"', true) : this.name;
    }
    
    public String getNameString() {
      return this.name;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\HsqlNameManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */