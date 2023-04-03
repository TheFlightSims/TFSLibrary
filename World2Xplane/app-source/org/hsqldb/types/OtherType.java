package org.hsqldb.types;

import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.lib.StringConverter;

public final class OtherType extends Type {
  static final OtherType otherType = new OtherType();
  
  private OtherType() {
    super(1111, 1111, 0L, 0);
  }
  
  public int displaySize() {
    return (this.precision > 2147483647L) ? Integer.MAX_VALUE : (int)this.precision;
  }
  
  public int getJDBCTypeCode() {
    return this.typeCode;
  }
  
  public Class getJDBCClass() {
    return Object.class;
  }
  
  public String getJDBCClassName() {
    return "java.lang.Object";
  }
  
  public int getSQLGenericTypeCode() {
    return this.typeCode;
  }
  
  public int typeCode() {
    return this.typeCode;
  }
  
  public String getNameString() {
    return "OTHER";
  }
  
  public String getDefinition() {
    return "OTHER";
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this.typeCode == paramType.typeCode)
      return this; 
    throw Error.error(5562);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    return this;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    return (paramObject1 == null) ? -1 : ((paramObject2 == null) ? 1 : 0);
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    return paramObject;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    return paramObject;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject instanceof java.io.Serializable)
      return paramObject; 
    throw Error.error(5561);
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : StringConverter.byteArrayToHexString(((JavaObjectData)paramObject).getBytes());
  }
  
  public String convertToSQLString(Object paramObject) {
    return (paramObject == null) ? "NULL" : StringConverter.byteArrayToSQLHexString(((JavaObjectData)paramObject).getBytes());
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    return (paramObject == null) ? null : ((JavaObjectData)paramObject).getObject();
  }
  
  public boolean canConvertFrom(Type paramType) {
    return (paramType.typeCode == this.typeCode) ? true : ((paramType.typeCode == 0));
  }
  
  public boolean isObjectType() {
    return true;
  }
  
  public static OtherType getOtherType() {
    return otherType;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\OtherType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */