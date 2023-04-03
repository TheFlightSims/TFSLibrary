package org.hsqldb.result;

import java.io.IOException;
import org.hsqldb.ColumnBase;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.Type;

public final class ResultMetaData {
  public static final int RESULT_METADATA = 1;
  
  public static final int SIMPLE_RESULT_METADATA = 2;
  
  public static final int UPDATE_RESULT_METADATA = 3;
  
  public static final int PARAM_METADATA = 4;
  
  public static final int GENERATED_INDEX_METADATA = 5;
  
  public static final int GENERATED_NAME_METADATA = 6;
  
  private int type;
  
  public String[] columnLabels;
  
  public Type[] columnTypes;
  
  private int columnCount;
  
  private int extendedColumnCount;
  
  public static final ResultMetaData emptyResultMetaData = newResultMetaData(0);
  
  public static final ResultMetaData emptyParamMetaData = newParameterMetaData(0);
  
  public int[] colIndexes;
  
  public ColumnBase[] columns;
  
  public byte[] paramModes;
  
  public byte[] paramNullable;
  
  private ResultMetaData(int paramInt) {
    this.type = paramInt;
  }
  
  public static ResultMetaData newUpdateResultMetaData(Type[] paramArrayOfType) {
    ResultMetaData resultMetaData = new ResultMetaData(3);
    resultMetaData.columnTypes = new Type[paramArrayOfType.length];
    resultMetaData.columnCount = paramArrayOfType.length;
    resultMetaData.extendedColumnCount = paramArrayOfType.length;
    ArrayUtil.copyArray(paramArrayOfType, resultMetaData.columnTypes, paramArrayOfType.length);
    return resultMetaData;
  }
  
  public static ResultMetaData newSimpleResultMetaData(Type[] paramArrayOfType) {
    ResultMetaData resultMetaData = new ResultMetaData(2);
    resultMetaData.columnTypes = paramArrayOfType;
    resultMetaData.columnCount = paramArrayOfType.length;
    resultMetaData.extendedColumnCount = paramArrayOfType.length;
    return resultMetaData;
  }
  
  public static ResultMetaData newResultMetaData(int paramInt) {
    Type[] arrayOfType = new Type[paramInt];
    return newResultMetaData(arrayOfType, null, paramInt, paramInt);
  }
  
  public static ResultMetaData newSingleColumnMetaData(String paramString) {
    ResultMetaData resultMetaData = newResultMetaData(1);
    resultMetaData.columns[0] = new ColumnBase(null, null, null, paramString);
    resultMetaData.columns[0].setType((Type)Type.SQL_VARCHAR_DEFAULT);
    resultMetaData.prepareData();
    return resultMetaData;
  }
  
  public static ResultMetaData newResultMetaData(Type[] paramArrayOfType, int[] paramArrayOfint, int paramInt1, int paramInt2) {
    ResultMetaData resultMetaData = new ResultMetaData(1);
    resultMetaData.columnLabels = new String[paramInt1];
    resultMetaData.columns = new ColumnBase[paramInt1];
    resultMetaData.columnTypes = paramArrayOfType;
    resultMetaData.colIndexes = paramArrayOfint;
    resultMetaData.columnCount = paramInt1;
    resultMetaData.extendedColumnCount = paramInt2;
    return resultMetaData;
  }
  
  public static ResultMetaData newParameterMetaData(int paramInt) {
    ResultMetaData resultMetaData = new ResultMetaData(4);
    resultMetaData.columnTypes = new Type[paramInt];
    resultMetaData.columnLabels = new String[paramInt];
    resultMetaData.paramModes = new byte[paramInt];
    resultMetaData.paramNullable = new byte[paramInt];
    resultMetaData.columnCount = paramInt;
    resultMetaData.extendedColumnCount = paramInt;
    return resultMetaData;
  }
  
  public static ResultMetaData newGeneratedColumnsMetaData(int[] paramArrayOfint, String[] paramArrayOfString) {
    if (paramArrayOfint != null) {
      ResultMetaData resultMetaData = new ResultMetaData(5);
      resultMetaData.columnCount = paramArrayOfint.length;
      resultMetaData.extendedColumnCount = paramArrayOfint.length;
      resultMetaData.colIndexes = new int[paramArrayOfint.length];
      for (byte b = 0; b < paramArrayOfint.length; b++)
        resultMetaData.colIndexes[b] = paramArrayOfint[b] - 1; 
      return resultMetaData;
    } 
    if (paramArrayOfString != null) {
      ResultMetaData resultMetaData = new ResultMetaData(6);
      resultMetaData.columnLabels = new String[paramArrayOfString.length];
      resultMetaData.columnCount = paramArrayOfString.length;
      resultMetaData.extendedColumnCount = paramArrayOfString.length;
      resultMetaData.columnLabels = paramArrayOfString;
      return resultMetaData;
    } 
    return null;
  }
  
  public void prepareData() {
    if (this.columns != null)
      for (byte b = 0; b < this.columnCount; b++) {
        if (this.columnTypes[b] == null)
          this.columnTypes[b] = this.columns[b].getDataType(); 
      }  
  }
  
  public int getColumnCount() {
    return this.columnCount;
  }
  
  public int getExtendedColumnCount() {
    return this.extendedColumnCount;
  }
  
  public void resetExtendedColumnCount() {
    this.extendedColumnCount = this.columnCount;
  }
  
  public Type[] getParameterTypes() {
    return this.columnTypes;
  }
  
  public String[] getGeneratedColumnNames() {
    return this.columnLabels;
  }
  
  public int[] getGeneratedColumnIndexes() {
    return this.colIndexes;
  }
  
  public boolean isTableColumn(int paramInt) {
    String str1 = this.columns[paramInt].getNameString();
    String str2 = this.columns[paramInt].getTableNameString();
    return (str2 != null && str2.length() > 0 && str1 != null && str1.length() > 0);
  }
  
  private static void decodeTableColumnAttrs(int paramInt, ColumnBase paramColumnBase) {
    paramColumnBase.setNullability((byte)(paramInt & 0x3));
    paramColumnBase.setIdentity(((paramInt & 0x4) != 0));
    paramColumnBase.setWriteable(((paramInt & 0x8) != 0));
    paramColumnBase.setSearchable(((paramInt & 0x10) != 0));
  }
  
  private static int encodeTableColumnAttrs(ColumnBase paramColumnBase) {
    int i = paramColumnBase.getNullability();
    if (paramColumnBase.isIdentity())
      i = i | 0x4; 
    if (paramColumnBase.isWriteable())
      i |= 0x8; 
    if (paramColumnBase.isSearchable())
      i |= 0x10; 
    return i;
  }
  
  private void decodeParamColumnAttrs(int paramInt1, int paramInt2) {
    this.paramNullable[paramInt2] = (byte)(paramInt1 & 0x3);
    this.paramModes[paramInt2] = (byte)(paramInt1 >> 4 & 0xF);
  }
  
  private int encodeParamColumnAttrs(int paramInt) {
    int i = this.paramModes[paramInt] << 4;
    i |= this.paramNullable[paramInt];
    return i;
  }
  
  ResultMetaData(RowInputBinary paramRowInputBinary) throws IOException {
    byte b;
    this.type = paramRowInputBinary.readInt();
    this.columnCount = paramRowInputBinary.readInt();
    switch (this.type) {
      case 2:
      case 3:
        this.columnTypes = new Type[this.columnCount];
        for (b = 0; b < this.columnCount; b++)
          this.columnTypes[b] = readDataTypeSimple(paramRowInputBinary); 
        return;
      case 5:
        this.colIndexes = new int[this.columnCount];
        for (b = 0; b < this.columnCount; b++)
          this.colIndexes[b] = paramRowInputBinary.readInt(); 
        return;
      case 6:
        this.columnLabels = new String[this.columnCount];
        for (b = 0; b < this.columnCount; b++)
          this.columnLabels[b] = paramRowInputBinary.readString(); 
        return;
      case 4:
        this.columnTypes = new Type[this.columnCount];
        this.columnLabels = new String[this.columnCount];
        this.paramModes = new byte[this.columnCount];
        this.paramNullable = new byte[this.columnCount];
        for (b = 0; b < this.columnCount; b++) {
          this.columnTypes[b] = readDataType(paramRowInputBinary);
          this.columnLabels[b] = paramRowInputBinary.readString();
          decodeParamColumnAttrs(paramRowInputBinary.readByte(), b);
        } 
        return;
      case 1:
        this.extendedColumnCount = paramRowInputBinary.readInt();
        this.columnTypes = new Type[this.extendedColumnCount];
        this.columnLabels = new String[this.columnCount];
        this.columns = new ColumnBase[this.columnCount];
        if (this.columnCount != this.extendedColumnCount)
          this.colIndexes = new int[this.columnCount]; 
        for (b = 0; b < this.extendedColumnCount; b++) {
          Type type = readDataType(paramRowInputBinary);
          this.columnTypes[b] = type;
        } 
        for (b = 0; b < this.columnCount; b++) {
          this.columnLabels[b] = paramRowInputBinary.readString();
          String str1 = paramRowInputBinary.readString();
          String str2 = paramRowInputBinary.readString();
          String str3 = paramRowInputBinary.readString();
          String str4 = paramRowInputBinary.readString();
          ColumnBase columnBase = new ColumnBase(str1, str2, str3, str4);
          columnBase.setType(this.columnTypes[b]);
          decodeTableColumnAttrs(paramRowInputBinary.readByte(), columnBase);
          this.columns[b] = columnBase;
        } 
        if (this.columnCount != this.extendedColumnCount)
          for (b = 0; b < this.columnCount; b++)
            this.colIndexes[b] = paramRowInputBinary.readInt();  
        return;
    } 
    throw Error.runtimeError(201, "ResultMetaData");
  }
  
  Type readDataTypeSimple(RowInputBinary paramRowInputBinary) throws IOException {
    int i = paramRowInputBinary.readType();
    boolean bool = (i == 50) ? true : false;
    if (bool) {
      i = paramRowInputBinary.readType();
      return (Type)Type.getDefaultArrayType(i);
    } 
    return Type.getDefaultType(i);
  }
  
  Type readDataType(RowInputBinary paramRowInputBinary) throws IOException {
    ArrayType arrayType;
    int i = paramRowInputBinary.readType();
    boolean bool = (i == 50) ? true : false;
    if (bool)
      i = paramRowInputBinary.readType(); 
    long l = paramRowInputBinary.readLong();
    int j = paramRowInputBinary.readInt();
    Type type = Type.getType(i, Type.SQL_VARCHAR.getCharacterSet(), Type.SQL_VARCHAR.getCollation(), l, j);
    if (bool)
      arrayType = new ArrayType(type, 1024); 
    return (Type)arrayType;
  }
  
  void writeDataType(RowOutputInterface paramRowOutputInterface, Type paramType) {
    paramRowOutputInterface.writeType(paramType.typeCode);
    if (paramType.isArrayType())
      paramRowOutputInterface.writeType((paramType.collectionBaseType()).typeCode); 
    paramRowOutputInterface.writeLong(paramType.precision);
    paramRowOutputInterface.writeInt(paramType.scale);
  }
  
  void writeDataTypeCodes(RowOutputInterface paramRowOutputInterface, Type paramType) {
    paramRowOutputInterface.writeType(paramType.typeCode);
    if (paramType.isArrayType())
      paramRowOutputInterface.writeType((paramType.collectionBaseType()).typeCode); 
  }
  
  void write(RowOutputInterface paramRowOutputInterface) throws IOException {
    byte b;
    paramRowOutputInterface.writeInt(this.type);
    paramRowOutputInterface.writeInt(this.columnCount);
    switch (this.type) {
      case 2:
      case 3:
        for (b = 0; b < this.columnCount; b++)
          writeDataTypeCodes(paramRowOutputInterface, this.columnTypes[b]); 
        return;
      case 5:
        for (b = 0; b < this.columnCount; b++)
          paramRowOutputInterface.writeInt(this.colIndexes[b]); 
        return;
      case 6:
        for (b = 0; b < this.columnCount; b++)
          paramRowOutputInterface.writeString(this.columnLabels[b]); 
        return;
      case 4:
        for (b = 0; b < this.columnCount; b++) {
          writeDataType(paramRowOutputInterface, this.columnTypes[b]);
          paramRowOutputInterface.writeString(this.columnLabels[b]);
          paramRowOutputInterface.writeByte(encodeParamColumnAttrs(b));
        } 
        return;
      case 1:
        paramRowOutputInterface.writeInt(this.extendedColumnCount);
        for (b = 0; b < this.extendedColumnCount; b++) {
          if (this.columnTypes[b] == null) {
            ColumnBase columnBase = this.columns[b];
            this.columnTypes[b] = columnBase.getDataType();
          } 
          writeDataType(paramRowOutputInterface, this.columnTypes[b]);
        } 
        for (b = 0; b < this.columnCount; b++) {
          ColumnBase columnBase = this.columns[b];
          paramRowOutputInterface.writeString(this.columnLabels[b]);
          paramRowOutputInterface.writeString(columnBase.getCatalogNameString());
          paramRowOutputInterface.writeString(columnBase.getSchemaNameString());
          paramRowOutputInterface.writeString(columnBase.getTableNameString());
          paramRowOutputInterface.writeString(columnBase.getNameString());
          paramRowOutputInterface.writeByte(encodeTableColumnAttrs(columnBase));
        } 
        if (this.columnCount != this.extendedColumnCount)
          for (b = 0; b < this.colIndexes.length; b++)
            paramRowOutputInterface.writeInt(this.colIndexes[b]);  
        return;
    } 
    throw Error.runtimeError(201, "ResultMetaData");
  }
  
  public ResultMetaData getNewMetaData(int[] paramArrayOfint) {
    ResultMetaData resultMetaData = newResultMetaData(paramArrayOfint.length);
    ArrayUtil.projectRow((Object[])this.columnLabels, paramArrayOfint, (Object[])resultMetaData.columnLabels);
    ArrayUtil.projectRow((Object[])this.columnTypes, paramArrayOfint, (Object[])resultMetaData.columnTypes);
    ArrayUtil.projectRow((Object[])this.columns, paramArrayOfint, (Object[])resultMetaData.columns);
    return resultMetaData;
  }
  
  public boolean areTypesCompatible(ResultMetaData paramResultMetaData) {
    if (this.columnCount != paramResultMetaData.columnCount)
      return false; 
    for (byte b = 0; b < this.columnCount; b++) {
      if (!this.columnTypes[b].canConvertFrom(paramResultMetaData.columnTypes[b]))
        return false; 
    } 
    return true;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\result\ResultMetaData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */