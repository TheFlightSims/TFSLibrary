package org.hsqldb.rights;

import org.hsqldb.Database;
import org.hsqldb.HsqlException;
import org.hsqldb.HsqlNameManager;
import org.hsqldb.Routine;
import org.hsqldb.Schema;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.HashMappedList;
import org.hsqldb.lib.HsqlArrayList;
import org.hsqldb.result.Result;

public final class UserManager {
  private HashMappedList userList;
  
  private GranteeManager granteeManager;
  
  Routine pwCheckFunction;
  
  Routine extAuthenticationFunction;
  
  public UserManager(Database paramDatabase) {
    this.granteeManager = paramDatabase.getGranteeManager();
    this.userList = new HashMappedList();
  }
  
  public User createUser(Session paramSession, HsqlNameManager.HsqlName paramHsqlName, String paramString, boolean paramBoolean) {
    User user = this.granteeManager.addUser(paramHsqlName);
    if (paramSession == null) {
      user.setPassword(paramString, paramBoolean);
    } else {
      try {
        setPassword(paramSession, user, paramString, paramBoolean);
      } catch (HsqlException hsqlException) {
        this.granteeManager.removeNewUser(paramHsqlName);
        throw hsqlException;
      } 
    } 
    boolean bool = this.userList.add(paramHsqlName.name, user);
    return user;
  }
  
  public void setPassword(Session paramSession, User paramUser, String paramString, boolean paramBoolean) {
    if (!paramBoolean && !checkComplexity(paramSession, paramString))
      throw Error.error(391); 
    paramUser.setPassword(paramString, paramBoolean);
  }
  
  public boolean checkComplexity(Session paramSession, String paramString) {
    if (paramSession == null || this.pwCheckFunction == null)
      return true; 
    Result result = this.pwCheckFunction.invoke(paramSession, new Object[] { paramString }, null, true);
    Boolean bool = (Boolean)result.getValueObject();
    return !(bool == null || !bool.booleanValue());
  }
  
  public void dropUser(String paramString) {
    boolean bool1 = GranteeManager.isReserved(paramString);
    if (bool1)
      throw Error.error(4002, paramString); 
    boolean bool2 = this.granteeManager.removeGrantee(paramString);
    if (!bool2)
      throw Error.error(4001, paramString); 
    User user = (User)this.userList.remove(paramString);
    if (user == null)
      throw Error.error(4001, paramString); 
  }
  
  public void createFirstUser(String paramString1, String paramString2) {
    boolean bool = true;
    if (paramString1.equalsIgnoreCase("SA")) {
      paramString1 = "SA";
      bool = false;
    } 
    HsqlNameManager.HsqlName hsqlName = this.granteeManager.database.nameManager.newHsqlName(paramString1, bool, 11);
    User user = createUser(null, hsqlName, paramString2, false);
    user.isLocalOnly = true;
    this.granteeManager.grant(hsqlName.name, "DBA", this.granteeManager.getDBARole());
  }
  
  public User getUser(String paramString1, String paramString2) {
    if (paramString1 == null)
      paramString1 = ""; 
    if (paramString2 == null)
      paramString2 = ""; 
    User user = (User)this.userList.get(paramString1);
    boolean bool = (user != null && user.isLocalOnly) ? true : false;
    if (this.extAuthenticationFunction == null || bool) {
      user = get(paramString1);
      user.checkPassword(paramString2);
      return user;
    } 
    Result result = this.extAuthenticationFunction.invokeJavaMethodDirect((Object[])new String[] { this.granteeManager.database.getUniqueName(), paramString1, paramString2 });
    if (result.isError())
      throw Error.error(4001, result.getMainString()); 
    Object[] arrayOfObject = (Object[])result.getValueObject();
    if (user == null) {
      HsqlNameManager.HsqlName hsqlName = this.granteeManager.database.nameManager.newHsqlName(paramString1, true, 11);
      user = createUser(null, hsqlName, "", false);
      user.isExternalOnly = true;
    } 
    if (arrayOfObject == null) {
      user.updateAllRights();
      return user;
    } 
    user.clearPrivileges();
    byte b;
    for (b = 0; b < arrayOfObject.length; b++) {
      try {
        Grantee grantee = this.granteeManager.getRole((String)arrayOfObject[b]);
        user.grant(grantee);
      } catch (HsqlException hsqlException) {}
    } 
    user.updateAllRights();
    for (b = 0; b < arrayOfObject.length; b++) {
      Schema schema = this.granteeManager.database.schemaManager.findSchema((String)arrayOfObject[b]);
      if (schema != null) {
        user.setInitialSchema(schema.getName());
        break;
      } 
    } 
    return user;
  }
  
  public HashMappedList getUsers() {
    return this.userList;
  }
  
  public boolean exists(String paramString) {
    return !(this.userList.get(paramString) == null);
  }
  
  public User get(String paramString) {
    User user = (User)this.userList.get(paramString);
    if (user == null)
      throw Error.error(4001, paramString); 
    return user;
  }
  
  public HsqlArrayList listVisibleUsers(Session paramSession) {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    boolean bool = paramSession.isAdmin();
    String str = paramSession.getUsername();
    if (this.userList == null || this.userList.size() == 0)
      return hsqlArrayList; 
    for (byte b = 0; b < this.userList.size(); b++) {
      User user = (User)this.userList.get(b);
      if (user != null) {
        String str1 = user.getName().getNameString();
        if (bool) {
          hsqlArrayList.add(user);
        } else if (str.equals(str1)) {
          hsqlArrayList.add(user);
        } 
      } 
    } 
    return hsqlArrayList;
  }
  
  public User getSysUser() {
    return GranteeManager.systemAuthorisation;
  }
  
  public synchronized void removeSchemaReference(String paramString) {
    for (byte b = 0; b < this.userList.size(); b++) {
      User user = (User)this.userList.get(b);
      HsqlNameManager.HsqlName hsqlName = user.getInitialSchema();
      if (hsqlName != null && paramString.equals(hsqlName.name))
        user.setInitialSchema((HsqlNameManager.HsqlName)null); 
    } 
  }
  
  public void setPasswordCheckFunction(Routine paramRoutine) {
    this.pwCheckFunction = paramRoutine;
  }
  
  public void setExtAuthenticationFunction(Routine paramRoutine) {
    this.extAuthenticationFunction = paramRoutine;
  }
  
  public String[] getInitialSchemaSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList(this.userList.size());
    for (byte b = 0; b < this.userList.size(); b++) {
      User user = (User)this.userList.get(b);
      if (!user.isSystem) {
        HsqlNameManager.HsqlName hsqlName = user.getInitialSchema();
        if (hsqlName != null)
          hsqlArrayList.add(user.getInitialSchemaSQL()); 
      } 
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
  
  public String[] getAuthenticationSQL() {
    HsqlArrayList hsqlArrayList = new HsqlArrayList();
    if (this.pwCheckFunction != null) {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("SET").append(' ').append("DATABASE");
      stringBuffer.append(' ').append("PASSWORD").append(' ');
      stringBuffer.append("CHECK").append(' ').append("FUNCTION");
      stringBuffer.append(' ');
      stringBuffer.append(this.pwCheckFunction.getSQLBodyDefinition());
      hsqlArrayList.add(stringBuffer.toString());
    } 
    if (this.extAuthenticationFunction != null) {
      StringBuffer stringBuffer = new StringBuffer();
      stringBuffer.append("SET").append(' ').append("DATABASE");
      stringBuffer.append(' ').append("AUTHENTICATION").append(' ');
      stringBuffer.append("FUNCTION").append(' ');
      stringBuffer.append(this.extAuthenticationFunction.getSQLBodyDefinition());
      hsqlArrayList.add(stringBuffer.toString());
    } 
    String[] arrayOfString = new String[hsqlArrayList.size()];
    hsqlArrayList.toArray(arrayOfString);
    return arrayOfString;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\rights\UserManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */