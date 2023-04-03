package org.hsqldb.types;

import java.sql.Blob;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCBlobClient;

public final class BlobType extends BinaryType {
  public static final long maxBlobPrecision = 1099511627776L;
  
  public static final int defaultBlobSize = 1073741824;
  
  public static final int defaultShortBlobSize = 16777216;
  
  public BlobType(long paramLong) {
    super(30, paramLong);
  }
  
  public int displaySize() {
    return (this.precision > 2147483647L) ? Integer.MAX_VALUE : (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    return 2004;
  }
  
  public Class getJDBCClass() {
    return Blob.class;
  }
  
  public String getJDBCClassName() {
    return "java.sql.Blob";
  }
  
  public String getNameString() {
    return "BLOB";
  }
  
  public String getFullNameString() {
    return "BINARY LARGE OBJECT";
  }
  
  public String getDefinition() {
    long l = this.precision;
    String str = null;
    if (this.precision % 1073741824L == 0L) {
      l = this.precision / 1073741824L;
      str = "G";
    } else if (this.precision % 1048576L == 0L) {
      l = this.precision / 1048576L;
      str = "M";
    } else if (this.precision % 1024L == 0L) {
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
  
  public boolean isBinaryType() {
    return true;
  }
  
  public boolean isLobType() {
    return true;
  }
  
  public boolean acceptsPrecision() {
    return true;
  }
  
  public long getMaxPrecision() {
    return 1099511627776L;
  }
  
  public boolean requiresPrecision() {
    return false;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2) ? 0 : ((paramObject1 == null) ? -1 : ((paramObject2 == null) ? 1 : ((paramObject2 instanceof BinaryData) ? paramSession.database.lobManager.compare((BlobData)paramObject1, ((BlobData)paramObject2).getBytes()) : paramSession.database.lobManager.compare((BlobData)paramObject1, (BlobData)paramObject2))));
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    return paramObject;
  }
  
  public Object castToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    if (paramObject == null)
      return null; 
    if (paramType.typeCode == 30) {
      BlobData blobData = (BlobData)paramObject;
      long l = blobData.length(paramSessionInterface);
      if (l > this.precision) {
        l = this.precision;
        paramSessionInterface.addWarning(Error.error(1004));
        return blobData.getBlob(paramSessionInterface, 0L, l);
      } 
      return paramObject;
    } 
    if (paramType.typeCode == 60 || paramType.typeCode == 61) {
      BlobData blobData = (BlobData)paramObject;
      long l = blobData.length(paramSessionInterface);
      if (l > this.precision) {
        l = this.precision;
        paramSessionInterface.addWarning(Error.error(1004));
      } 
      BlobDataID blobDataID = paramSessionInterface.createBlob(blobData.length(paramSessionInterface));
      blobDataID.setBytes(paramSessionInterface, 0L, blobData.getBytes(), 0, (int)l);
      return blobDataID;
    } 
    throw Error.error(5561);
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    BlobData blobData = null;
    if (paramObject == null)
      return null; 
    if (paramType.typeCode == 30) {
      blobData = (BlobData)paramObject;
      long l = blobData.length(paramSessionInterface);
      if (l > this.precision)
        throw Error.error(3401); 
      return paramObject;
    } 
    if (paramType.typeCode == 40) {
      paramObject = Type.SQL_VARCHAR.convertToType(paramSessionInterface, paramObject, paramType);
      paramType = Type.SQL_VARCHAR;
    } 
    if (paramType.typeCode == 12 || paramType.typeCode == 1) {
      paramObject = paramSessionInterface.getScanner().convertToBinary((String)paramObject);
      paramType = Type.SQL_VARBINARY;
    } 
    if (paramType.typeCode == 60 || paramType.typeCode == 61) {
      blobData = (BlobData)paramObject;
      long l = blobData.length(paramSessionInterface);
      if (l > this.precision)
        throw Error.error(3401); 
      BlobDataID blobDataID = paramSessionInterface.createBlob(blobData.length(paramSessionInterface));
      blobDataID.setBytes(paramSessionInterface, 0L, blobData.getBytes());
      return blobDataID;
    } 
    throw Error.error(5561);
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof JDBCBlobClient)
      return ((JDBCBlobClient)paramObject).getBlob(); 
    throw Error.error(5561);
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof BlobDataID) {
      BlobDataID blobDataID = (BlobDataID)paramObject;
      return new JDBCBlobClient(paramSessionInterface, blobDataID);
    } 
    throw Error.error(5561);
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    if (paramObject instanceof byte[])
      return new BinaryData((byte[])paramObject, false); 
    throw Error.error(5561);
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : Long.toString(((BlobData)paramObject).getId());
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : convertToString(paramObject);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\BlobType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */