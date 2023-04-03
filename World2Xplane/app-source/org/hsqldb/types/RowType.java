package org.hsqldb.types;

import java.sql.ResultSet;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;

public class RowType extends Type {
  final Type[] dataTypes;
  
  Type.TypedComparator comparator;
  
  public RowType(Type[] paramArrayOfType) {
    super(19, 19, 0L, 0);
    this.dataTypes = paramArrayOfType;
  }
  
  public int displaySize() {
    return 0;
  }
  
  public int getJDBCTypeCode() {
    return 0;
  }
  
  public Class getJDBCClass() {
    return ResultSet.class;
  }
  
  public String getJDBCClassName() {
    return "java.sql.ResultSet";
  }
  
  public int getJDBCScale() {
    return 0;
  }
  
  public int getJDBCPrecision() {
    return 0;
  }
  
  public int getSQLGenericTypeCode() {
    return 19;
  }
  
  public boolean isRowType() {
    return true;
  }
  
  public String getNameString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ROW");
    stringBuffer.append('(');
    for (byte b = 0; b < this.dataTypes.length; b++) {
      if (b > 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.dataTypes[b].getDefinition());
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public String getDefinition() {
    return getNameString();
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    Object[] arrayOfObject1 = (Object[])paramObject1;
    Object[] arrayOfObject2 = (Object[])paramObject2;
    int i = arrayOfObject1.length;
    if (arrayOfObject2.length < i)
      i = arrayOfObject2.length; 
    for (byte b = 0; b < i; b++) {
      int j = this.dataTypes[b].compare(paramSession, arrayOfObject1[b], arrayOfObject2[b]);
      if (j != 0)
        return j; 
    } 
    return (arrayOfObject1.length > arrayOfObject2.length) ? 1 : ((arrayOfObject1.length < arrayOfObject2.length) ? -1 : 0);
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    Object[] arrayOfObject1 = (Object[])paramObject;
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (byte b = 0; b < arrayOfObject1.length; b++)
      arrayOfObject2[b] = this.dataTypes[b].convertToTypeLimits(paramSessionInterface, arrayOfObject1[b]); 
    return arrayOfObject2;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    if (paramObject == null)
      return null; 
    if (paramType == null)
      return paramObject; 
    if (!paramType.isRowType())
      throw Error.error(5562); 
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length)
      throw Error.error(5564); 
    Object[] arrayOfObject1 = (Object[])paramObject;
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (byte b = 0; b < arrayOfObject1.length; b++)
      arrayOfObject2[b] = this.dataTypes[b].convertToType(paramSessionInterface, arrayOfObject1[b], arrayOfType[b]); 
    return arrayOfObject2;
  }
  
  public Object convertToDefaultType(SessionInterface paramSessionInterface, Object paramObject) {
    return paramObject;
  }
  
  public String convertToString(Object paramObject) {
    return (paramObject == null) ? null : convertToSQLString(paramObject);
  }
  
  public String convertToSQLString(Object paramObject) {
    if (paramObject == null)
      return "NULL"; 
    Object[] arrayOfObject = (Object[])paramObject;
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("ROW");
    stringBuffer.append('(');
    for (byte b = 0; b < arrayOfObject.length; b++) {
      if (b > 0)
        stringBuffer.append(','); 
      String str = this.dataTypes[b].convertToSQLString(arrayOfObject[b]);
      stringBuffer.append(str);
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType) {
    if (paramType == null)
      return true; 
    if (!paramType.isRowType())
      return false; 
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length)
      return false; 
    for (byte b = 0; b < this.dataTypes.length; b++) {
      if (!this.dataTypes[b].canConvertFrom(arrayOfType[b]))
        return false; 
    } 
    return true;
  }
  
  public boolean canBeAssignedFrom(Type paramType) {
    if (paramType == null)
      return true; 
    if (!paramType.isRowType())
      return false; 
    Type[] arrayOfType = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType.length)
      return false; 
    for (byte b = 0; b < this.dataTypes.length; b++) {
      if (!this.dataTypes[b].canBeAssignedFrom(arrayOfType[b]))
        return false; 
    } 
    return true;
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (paramType == this)
      return this; 
    if (!paramType.isRowType())
      throw Error.error(5562); 
    Type[] arrayOfType1 = new Type[this.dataTypes.length];
    Type[] arrayOfType2 = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType2.length)
      throw Error.error(5564); 
    for (byte b = 0; b < this.dataTypes.length; b++)
      arrayOfType1[b] = this.dataTypes[b].getAggregateType(arrayOfType2[b]); 
    return new RowType(arrayOfType1);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    if (paramInt != 36)
      return getAggregateType(paramType); 
    if (paramType == null)
      return this; 
    if (!paramType.isRowType())
      throw Error.error(5562); 
    Type[] arrayOfType1 = new Type[this.dataTypes.length];
    Type[] arrayOfType2 = ((RowType)paramType).getTypesArray();
    if (this.dataTypes.length != arrayOfType2.length)
      throw Error.error(5564); 
    for (byte b = 0; b < this.dataTypes.length; b++)
      arrayOfType1[b] = this.dataTypes[b].getAggregateType(arrayOfType2[b]); 
    return new RowType(arrayOfType1);
  }
  
  public Type[] getTypesArray() {
    return this.dataTypes;
  }
  
  public int compare(Session paramSession, Object paramObject1, Object paramObject2, SortAndSlice paramSortAndSlice) {
    if (paramObject1 == paramObject2)
      return 0; 
    if (paramObject1 == null)
      return -1; 
    if (paramObject2 == null)
      return 1; 
    Object[] arrayOfObject1 = (Object[])paramObject1;
    Object[] arrayOfObject2 = (Object[])paramObject2;
    int i = paramSortAndSlice.sortOrder.length;
    for (byte b = 0; b < i; b++) {
      paramObject1 = arrayOfObject1[paramSortAndSlice.sortOrder[b]];
      paramObject2 = arrayOfObject2[paramSortAndSlice.sortOrder[b]];
      if (paramObject1 != paramObject2) {
        if (paramSortAndSlice.sortNullsLast[b]) {
          if (paramObject1 == null)
            return 1; 
          if (paramObject2 == null)
            return -1; 
        } 
        int j = this.dataTypes[b].compare(paramSession, paramObject1, paramObject2);
        if (j != 0)
          return paramSortAndSlice.sortDescending[b] ? -j : j; 
      } 
    } 
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (paramObject instanceof Type) {
      if (((Type)paramObject).typeCode != 19)
        return false; 
      Type[] arrayOfType = ((RowType)paramObject).dataTypes;
      if (arrayOfType.length != this.dataTypes.length)
        return false; 
      byte b = 0;
      if (b < this.dataTypes.length)
        return !!this.dataTypes[b].equals(arrayOfType[b]); 
    } 
    return false;
  }
  
  public int hashCode(Object paramObject) {
    if (paramObject == null)
      return 0; 
    int i = 0;
    Object[] arrayOfObject = (Object[])paramObject;
    for (byte b = 0; b < this.dataTypes.length && b < 4; b++)
      i += this.dataTypes[b].hashCode(arrayOfObject[b]); 
    return i;
  }
  
  synchronized Type.TypedComparator getComparator(Session paramSession) {
    if (this.comparator == null) {
      Type.TypedComparator typedComparator = Type.newComparator(paramSession);
      SortAndSlice sortAndSlice = new SortAndSlice();
      sortAndSlice.prepareMultiColumn(this.dataTypes.length);
      typedComparator.setType(this, sortAndSlice);
      this.comparator = typedComparator;
    } 
    return this.comparator;
  }
  
  public static String convertToSQLString(Object[] paramArrayOfObject, Type[] paramArrayOfType, int paramInt) {
    if (paramArrayOfObject == null)
      return "NULL"; 
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('(');
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      if (b > 0)
        stringBuffer.append(','); 
      String str = paramArrayOfType[b].convertToSQLString(paramArrayOfObject[b]);
      if (paramInt > 10 && str.length() > paramInt) {
        stringBuffer.append(str.substring(0, paramInt - 4));
        stringBuffer.append(" ...");
      } else {
        stringBuffer.append(str);
      } 
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\RowType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */