package org.hsqldb.types;

import java.sql.Clob;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCClobClient;

public final class ClobType extends CharacterType {
  public static final long maxClobPrecision = 1099511627776L;
  
  public static final int defaultClobSize = 1073741824;
  
  public static final int defaultShortClobSize = 16777216;
  
  public ClobType(long paramLong) {
    super(40, paramLong);
  }
  
  public int displaySize() {
    return (this.precision > 2147483647L) ? Integer.MAX_VALUE : (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    return 2005;
  }
  
  public Class getJDBCClass() {
    return Clob.class;
  }
  
  public String getJDBCClassName() {
    return "java.sql.Clob";
  }
  
  public int getSQLGenericTypeCode() {
    return this.typeCode;
  }
  
  public String getDefinition() {
    long l = this.precision;
    String str = null;
    if (this.precision % 1024L == 0L)
      if (this.precision % 1073741824L == 0L) {
        l = this.precision / 1073741824L;
        str = "G";
      } else if (this.precision % 1048576L == 0L) {
        l = this.precision / 1048576L;
        str = "M";
      } else {
        l = this.precision / 1024L;
        str = "K";
      }  
    StringBuffer stringBuffer = new StringBuffer(16);
    stringBuffer.append(getNameString());
    stringBuffer.append('(');
    stringBuffer.append(l);
    if (str != null)
      stringBuffer.append(str); 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public long getMaxPrecision() {
    return 1099511627776L;
  }
  
  public boolean isLobType() {
    return true;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    return compare(paramSession, paramObject1, paramObject2, 40);
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2, int paramInt) {
    return (paramObject1 == paramObject2) ? 0 : ((paramObject1 == null) ? -1 : ((paramObject2 == null) ? 1 : ((paramObject2 instanceof String) ? paramSession.database.lobManager.compare(this.collation, (ClobData)paramObject1, (String)paramObject2) : paramSession.database.lobManager.compare(this.collation, (ClobData)paramObject1, (ClobData)paramObject2))));
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof ClobData)
      return paramObject; 
    if (paramObject instanceof String) {
      ClobDataID clobDataID = paramSessionInterface.createClob(((String)paramObject).length());
      clobDataID.setString(paramSessionInterface, 0L, (String)paramObject);
      return clobDataID;
    } 
    throw Error.error(5561);
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : Long.toString(((ClobData)paramObject).getId());
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : convertToString(paramObject);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof JDBCClobClient)
      return ((JDBCClobClient)paramObject).getClob(); 
    throw Error.error(5561);
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof ClobDataID) {
      ClobDataID clobDataID = (ClobDataID)paramObject;
      return new JDBCClobClient(paramSessionInterface, clobDataID);
    } 
    throw Error.error(5561);
  }
  
  public long position(SessionInterface paramSessionInterface, Object paramObject1, Object paramObject2, Type paramType, long paramLong) {
    if (paramType.typeCode == 40)
      return ((ClobData)paramObject1).position(paramSessionInterface, (ClobData)paramObject2, paramLong); 
    if (paramType.isCharacterType())
      return ((ClobData)paramObject1).position(paramSessionInterface, (String)paramObject2, paramLong); 
    throw Error.runtimeError(201, "ClobType");
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\ClobType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */