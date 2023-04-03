package org.hsqldb.rights;

import org.hsqldb.HsqlNameManager;
import org.hsqldb.error.Error;
import org.hsqldb.lib.MD5;
import org.hsqldb.lib.StringConverter;

public class User extends Grantee {
  private String password;
  
  public boolean isLocalOnly;
  
  public boolean isExternalOnly;
  
  private HsqlNameManager.HsqlName initialSchema = null;
  
  User(HsqlNameManager.HsqlName paramHsqlName, GranteeManager paramGranteeManager) {
    super(paramHsqlName, paramGranteeManager);
    if (paramGranteeManager != null)
      updateAllRights(); 
  }
  
  public String getSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CREATE").append(' ').append("USER");
    stringBuffer.append(' ').append(this.granteeName.statementName).append(' ');
    stringBuffer.append("PASSWORD").append(' ').append("DIGEST");
    stringBuffer.append(' ').append('\'').append(this.password).append('\'');
    return stringBuffer.toString();
  }
  
  public String getPasswordDigest() {
    return this.password;
  }
  
  public void setPassword(String paramString, boolean paramBoolean) {
    if (!paramBoolean)
      paramString = MD5.encode(paramString, null); 
    this.password = paramString;
  }
  
  public void checkPassword(String paramString) {
    String str = MD5.encode(paramString, null);
    if (!str.equals(this.password))
      throw Error.error(4000); 
  }
  
  public HsqlNameManager.HsqlName getInitialSchema() {
    return this.initialSchema;
  }
  
  public HsqlNameManager.HsqlName getInitialOrDefaultSchema() {
    if (this.initialSchema != null)
      return this.initialSchema; 
    HsqlNameManager.HsqlName hsqlName = this.granteeManager.database.schemaManager.findSchemaHsqlName(getName().getNameString());
    return (hsqlName == null) ? this.granteeManager.database.schemaManager.getDefaultSchemaHsqlName() : hsqlName;
  }
  
  public void setInitialSchema(HsqlNameManager.HsqlName paramHsqlName) {
    this.initialSchema = paramHsqlName;
  }
  
  public String getInitialSchemaSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ALTER").append(' ');
    stringBuffer.append("USER").append(' ');
    stringBuffer.append(getName().getStatementName()).append(' ');
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("INITIAL").append(' ');
    stringBuffer.append("SCHEMA").append(' ');
    stringBuffer.append(this.initialSchema.getStatementName());
    return stringBuffer.toString();
  }
  
  public String getLocalUserSQL() {
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append("ALTER").append(' ');
    stringBuffer.append("USER").append(' ');
    stringBuffer.append(getName().getStatementName()).append(' ');
    stringBuffer.append("SET").append(' ').append("LOCAL");
    stringBuffer.append(' ').append("TRUE");
    return stringBuffer.toString();
  }
  
  public static String getSetUserPasswordDigestSQL(User paramUser, String paramString, boolean paramBoolean) {
    if (!paramBoolean)
      paramString = MD5.encode(paramString, null); 
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append("ALTER").append(' ');
    stringBuffer.append("USER").append(' ');
    stringBuffer.append(paramUser.getName().getStatementName()).append(' ');
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("PASSWORD").append(' ').append("DIGEST");
    stringBuffer.append(' ').append('\'').append(paramString).append('\'');
    return stringBuffer.toString();
  }
  
  public static String getSetCurrentPasswordDigestSQL(String paramString, boolean paramBoolean) {
    if (!paramBoolean)
      paramString = MD5.encode(paramString, null); 
    StringBuffer stringBuffer = new StringBuffer(64);
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("PASSWORD").append(' ').append("DIGEST");
    stringBuffer.append(' ').append('\'').append(paramString).append('\'');
    return stringBuffer.toString();
  }
  
  public String getConnectUserSQL() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("SET").append(' ');
    stringBuffer.append("SESSION").append(' ');
    stringBuffer.append("AUTHORIZATION").append(' ');
    stringBuffer.append(StringConverter.toQuotedString(getName().getNameString(), '\'', true));
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rights\User.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */