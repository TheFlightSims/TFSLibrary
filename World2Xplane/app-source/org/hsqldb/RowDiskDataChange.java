package org.hsqldb;

import java.io.IOException;
import org.hsqldb.persist.PersistentStore;
import org.hsqldb.rowio.RowInputBinary;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputBinary;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.ArrayType;
import org.hsqldb.types.Type;

public class RowDiskDataChange extends RowAVLDisk {
  public static final int COL_POS_ROW_NUM = 0;
  
  public static final int COL_POS_ROW_ID = 1;
  
  public static final int COL_POS_TABLE_ID = 2;
  
  public static final int COL_POS_SCHEMA_NAME = 3;
  
  public static final int COL_POS_TABLE_NAME = 4;
  
  public static final int COL_POS_IS_UPDATE = 5;
  
  static final Type[] arrayType = new Type[] { (Type)new ArrayType((Type)Type.SQL_INTEGER, 2147483647) };
  
  Table targetTable;
  
  Object[] updateData;
  
  int[] updateColMap;
  
  public RowDiskDataChange(TableBase paramTableBase, Object[] paramArrayOfObject, PersistentStore paramPersistentStore, Table paramTable) {
    super(paramTableBase, paramArrayOfObject, paramPersistentStore);
    this.targetTable = paramTable;
  }
  
  public RowDiskDataChange(Session paramSession, TableBase paramTableBase, RowInputInterface paramRowInputInterface) throws IOException {
    super(paramTableBase, paramRowInputInterface);
    this.targetTable = paramTableBase.database.schemaManager.getTable(paramSession, (String)this.rowData[4], (String)this.rowData[3]);
    if (((Boolean)this.rowData[5]).booleanValue()) {
      this.updateData = paramRowInputInterface.readData(this.targetTable.colTypes);
      RowInputBinary rowInputBinary = (RowInputBinary)paramRowInputInterface;
      if (rowInputBinary.readNull()) {
        this.updateColMap = null;
      } else {
        this.updateColMap = rowInputBinary.readIntArray();
      } 
    } else {
      this.updateData = null;
      this.updateColMap = null;
    } 
  }
  
  public void write(RowOutputInterface paramRowOutputInterface) {
    writeNodes(paramRowOutputInterface);
    if (this.hasDataChanged) {
      paramRowOutputInterface.writeData(this, this.table.colTypes);
      if (this.updateData != null) {
        Type[] arrayOfType = this.targetTable.colTypes;
        paramRowOutputInterface.writeData(arrayOfType.length, arrayOfType, this.updateData, null, null);
        RowOutputBinary rowOutputBinary = (RowOutputBinary)paramRowOutputInterface;
        if (this.updateColMap == null) {
          rowOutputBinary.writeNull((Type)Type.SQL_ARRAY_ALL_TYPES);
        } else {
          rowOutputBinary.writeArray(this.updateColMap);
        } 
      } 
      paramRowOutputInterface.writeEnd();
      this.hasDataChanged = false;
    } 
  }
  
  public Object[] getUpdateData() {
    return this.updateData;
  }
  
  public int[] getUpdateColumnMap() {
    return this.updateColMap;
  }
  
  public void setTargetTable(Table paramTable) {
    this.targetTable = paramTable;
  }
  
  public void setUpdateData(Object[] paramArrayOfObject) {
    this.updateData = paramArrayOfObject;
  }
  
  public void setUpdateColumnMap(int[] paramArrayOfint) {
    this.updateColMap = paramArrayOfint;
  }
  
  public int getRealSize(RowOutputInterface paramRowOutputInterface) {
    RowOutputBinary rowOutputBinary = (RowOutputBinary)paramRowOutputInterface;
    int i = paramRowOutputInterface.getSize(this);
    if (this.updateData != null) {
      i += rowOutputBinary.getSize(this.updateData, this.targetTable.getColumnCount(), this.targetTable.getColumnTypes());
      if (this.updateColMap != null)
        i += rowOutputBinary.getSize(this.updateColMap); 
    } 
    return i;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\RowDiskDataChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */