package org.hsqldb.types;

import java.sql.Array;
import java.util.Comparator;
import org.hsqldb.Session;
import org.hsqldb.SessionInterface;
import org.hsqldb.SortAndSlice;
import org.hsqldb.error.Error;
import org.hsqldb.jdbc.JDBCArray;
import org.hsqldb.jdbc.JDBCArrayBasic;
import org.hsqldb.lib.ArraySort;

public class ArrayType extends Type {
  public static final int defaultArrayCardinality = 1024;
  
  public static final int defaultLargeArrayCardinality = 2147483647;
  
  final Type dataType;
  
  final int maxCardinality;
  
  public ArrayType(Type paramType, int paramInt) {
    super(50, 50, 0L, 0);
    if (paramType == null)
      paramType = Type.SQL_ALL_TYPES; 
    this.dataType = paramType;
    this.maxCardinality = paramInt;
  }
  
  public int displaySize() {
    return 7 + (this.dataType.displaySize() + 1) * this.maxCardinality;
  }
  
  public int getJDBCTypeCode() {
    return 2003;
  }
  
  public Class getJDBCClass() {
    return Array.class;
  }
  
  public String getJDBCClassName() {
    return "java.sql.Array";
  }
  
  public int getJDBCScale() {
    return 0;
  }
  
  public int getJDBCPrecision() {
    return 0;
  }
  
  public int getSQLGenericTypeCode() {
    return 0;
  }
  
  public String getNameString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(this.dataType.getNameString()).append(' ');
    stringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      stringBuffer.append('[').append(this.maxCardinality).append(']'); 
    return stringBuffer.toString();
  }
  
  public String getFullNameString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(this.dataType.getFullNameString()).append(' ');
    stringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      stringBuffer.append('[').append(this.maxCardinality).append(']'); 
    return stringBuffer.toString();
  }
  
  public String getDefinition() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(this.dataType.getDefinition()).append(' ');
    stringBuffer.append("ARRAY");
    if (this.maxCardinality != 1024)
      stringBuffer.append('[').append(this.maxCardinality).append(']'); 
    return stringBuffer.toString();
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
      int j = this.dataType.compare(paramSession, arrayOfObject1[b], arrayOfObject2[b]);
      if (j != 0)
        return j; 
    } 
    return (arrayOfObject1.length > arrayOfObject2.length) ? 1 : ((arrayOfObject1.length < arrayOfObject2.length) ? -1 : 0);
  }
  
  public Object convertToTypeLimits(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject == null)
      return null; 
    Object[] arrayOfObject1 = (Object[])paramObject;
    if (arrayOfObject1.length > this.maxCardinality)
      throw Error.error(3491); 
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (byte b = 0; b < arrayOfObject1.length; b++)
      arrayOfObject2[b] = this.dataType.convertToTypeLimits(paramSessionInterface, arrayOfObject1[b]); 
    return arrayOfObject2;
  }
  
  public Object convertToType(SessionInterface paramSessionInterface, Object paramObject, Type paramType) {
    if (paramObject == null)
      return null; 
    if (paramType == null)
      return paramObject; 
    if (!paramType.isArrayType())
      throw Error.error(5562); 
    Object[] arrayOfObject1 = (Object[])paramObject;
    if (arrayOfObject1.length > this.maxCardinality)
      throw Error.error(3491); 
    Type type = paramType.collectionBaseType();
    if (this.dataType.equals(type))
      return paramObject; 
    Object[] arrayOfObject2 = new Object[arrayOfObject1.length];
    for (byte b = 0; b < arrayOfObject1.length; b++)
      arrayOfObject2[b] = this.dataType.convertToType(paramSessionInterface, arrayOfObject1[b], type); 
    return arrayOfObject2;
  }
  
  public Object convertJavaToSQL(SessionInterface paramSessionInterface, Object paramObject) {
    Object[] arrayOfObject;
    boolean bool = false;
    if (paramObject == null)
      return null; 
    if (paramObject instanceof Object[]) {
      arrayOfObject = (Object[])paramObject;
      bool = true;
    } else if (paramObject instanceof JDBCArray) {
      arrayOfObject = ((JDBCArray)paramObject).getArrayInternal();
    } else if (paramObject instanceof JDBCArrayBasic) {
      arrayOfObject = (Object[])((JDBCArrayBasic)paramObject).getArray();
      bool = true;
    } else if (paramObject instanceof Array) {
      try {
        arrayOfObject = (Object[])((Array)paramObject).getArray();
        bool = true;
      } catch (Exception exception) {
        throw Error.error(5561);
      } 
    } else {
      throw Error.error(5561);
    } 
    if (bool) {
      Object[] arrayOfObject1 = new Object[arrayOfObject.length];
      for (byte b = 0; b < arrayOfObject.length; b++) {
        arrayOfObject1[b] = this.dataType.convertJavaToSQL(paramSessionInterface, arrayOfObject[b]);
        arrayOfObject1[b] = this.dataType.convertToTypeLimits(paramSessionInterface, arrayOfObject[b]);
      } 
      return arrayOfObject1;
    } 
    return arrayOfObject;
  }
  
  public Object convertSQLToJava(SessionInterface paramSessionInterface, Object paramObject) {
    if (paramObject instanceof Object[]) {
      Object[] arrayOfObject = (Object[])paramObject;
      return new JDBCArray(arrayOfObject, collectionBaseType(), this, paramSessionInterface);
    } 
    throw Error.error(5561);
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
    stringBuffer.append("ARRAY");
    stringBuffer.append('[');
    for (byte b = 0; b < arrayOfObject.length; b++) {
      if (b > 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.dataType.convertToSQLString(arrayOfObject[b]));
    } 
    stringBuffer.append(']');
    return stringBuffer.toString();
  }
  
  public boolean canConvertFrom(Type paramType) {
    if (paramType == null)
      return true; 
    if (!paramType.isArrayType())
      return false; 
    Type type = paramType.collectionBaseType();
    return this.dataType.canConvertFrom(type);
  }
  
  public int canMoveFrom(Type paramType) {
    return (paramType == this) ? 0 : (!paramType.isArrayType() ? -1 : ((this.maxCardinality >= ((ArrayType)paramType).maxCardinality) ? this.dataType.canMoveFrom(paramType) : ((this.dataType.canMoveFrom(paramType) == -1) ? -1 : 1)));
  }
  
  public boolean canBeAssignedFrom(Type paramType) {
    if (paramType == null)
      return true; 
    Type type = paramType.collectionBaseType();
    return (type != null && this.dataType.canBeAssignedFrom(type));
  }
  
  public Type collectionBaseType() {
    return this.dataType;
  }
  
  public int arrayLimitCardinality() {
    return this.maxCardinality;
  }
  
  public boolean isArrayType() {
    return true;
  }
  
  public Type getAggregateType(Type paramType) {
    if (paramType == null)
      return this; 
    if (paramType == SQL_ALL_TYPES)
      return this; 
    if (this == paramType)
      return this; 
    if (!paramType.isArrayType())
      throw Error.error(5562); 
    Type type1 = paramType.collectionBaseType();
    if (this.dataType.equals(type1))
      return (((ArrayType)paramType).maxCardinality > this.maxCardinality) ? paramType : this; 
    Type type2 = this.dataType.getAggregateType(type1);
    int i = (((ArrayType)paramType).maxCardinality > this.maxCardinality) ? ((ArrayType)paramType).maxCardinality : this.maxCardinality;
    return new ArrayType(type2, i);
  }
  
  public Type getCombinedType(Session paramSession, Type paramType, int paramInt) {
    ArrayType arrayType = (ArrayType)getAggregateType(paramType);
    if (paramType == null)
      return arrayType; 
    if (paramInt != 36)
      return arrayType; 
    if (arrayType.maxCardinality == Integer.MAX_VALUE)
      return arrayType; 
    long l = ((ArrayType)paramType).maxCardinality + this.maxCardinality;
    if (l > 2147483647L)
      l = 2147483647L; 
    return new ArrayType(this.dataType, (int)l);
  }
  
  public int cardinality(Session paramSession, Object paramObject) {
    return (paramObject == null) ? 0 : ((Object[])paramObject).length;
  }
  
  public Object concat(Session paramSession, Object paramObject1, Object paramObject2) {
    if (paramObject1 == null || paramObject2 == null)
      return null; 
    int i = ((Object[])paramObject1).length + ((Object[])paramObject2).length;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramObject1, 0, arrayOfObject, 0, ((Object[])paramObject1).length);
    System.arraycopy(paramObject2, 0, arrayOfObject, ((Object[])paramObject1).length, ((Object[])paramObject2).length);
    return arrayOfObject;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == this) ? true : ((paramObject instanceof Type) ? ((((Type)paramObject).typeCode != 50) ? false : ((this.maxCardinality == ((ArrayType)paramObject).maxCardinality && this.dataType.equals(((ArrayType)paramObject).dataType)))) : false);
  }
  
  public int hashCode(Object paramObject) {
    if (paramObject == null)
      return 0; 
    int i = 0;
    Object[] arrayOfObject = (Object[])paramObject;
    for (byte b = 0; b < arrayOfObject.length && b < 4; b++)
      i += this.dataType.hashCode(arrayOfObject[b]); 
    return i;
  }
  
  public void sort(Session paramSession, Object paramObject, SortAndSlice paramSortAndSlice) {
    Object[] arrayOfObject = (Object[])paramObject;
    Type.TypedComparator typedComparator = paramSession.getComparator();
    typedComparator.setType(this.dataType, paramSortAndSlice);
    ArraySort.sort(arrayOfObject, 0, arrayOfObject.length, (Comparator)typedComparator);
  }
  
  public int deDuplicate(Session paramSession, Object paramObject, SortAndSlice paramSortAndSlice) {
    Object[] arrayOfObject = (Object[])paramObject;
    Type.TypedComparator typedComparator = paramSession.getComparator();
    typedComparator.setType(this.dataType, paramSortAndSlice);
    return ArraySort.deDuplicate(arrayOfObject, 0, arrayOfObject.length, (Comparator)typedComparator);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\types\ArrayType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */