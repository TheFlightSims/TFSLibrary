package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.HsqlException;
import org.hsqldb.Row;
import org.hsqldb.error.Error;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public class RowSetNavigatorClient extends RowSetNavigator {
  public static final Object[][] emptyTable = new Object[0][];
  
  int currentOffset;
  
  int baseBlockSize;
  
  Object[][] table;
  
  public RowSetNavigatorClient() {
    this.table = emptyTable;
  }
  
  public RowSetNavigatorClient(int paramInt) {
    this.table = new Object[paramInt][];
  }
  
  public RowSetNavigatorClient(RowSetNavigator paramRowSetNavigator, int paramInt1, int paramInt2) {
    this.size = paramRowSetNavigator.size;
    this.baseBlockSize = paramInt2;
    this.currentOffset = paramInt1;
    this.table = new Object[paramInt2][];
    paramRowSetNavigator.absolute(paramInt1);
    for (byte b = 0; b < paramInt2; b++) {
      this.table[b] = paramRowSetNavigator.getCurrent();
      paramRowSetNavigator.next();
    } 
    paramRowSetNavigator.beforeFirst();
  }
  
  public void setData(Object[][] paramArrayOfObject) {
    this.table = paramArrayOfObject;
    this.size = paramArrayOfObject.length;
  }
  
  public void setData(int paramInt, Object[] paramArrayOfObject) {
    this.table[paramInt] = paramArrayOfObject;
  }
  
  public Object[] getData(int paramInt) {
    return this.table[paramInt];
  }
  
  public Object[] getCurrent() {
    if (this.currentPos < 0 || this.currentPos >= this.size)
      return null; 
    if (this.currentPos == this.currentOffset + this.table.length)
      getBlock(this.currentOffset + this.table.length); 
    return this.table[this.currentPos - this.currentOffset];
  }
  
  public Row getCurrentRow() {
    throw Error.runtimeError(201, "RowSetNavigatorClient");
  }
  
  public void removeCurrent() {
    throw Error.runtimeError(201, "RowSetNavigatorClient");
  }
  
  public void add(Object[] paramArrayOfObject) {
    ensureCapacity();
    this.table[this.size] = paramArrayOfObject;
    this.size++;
  }
  
  public boolean addRow(Row paramRow) {
    throw Error.runtimeError(201, "RowSetNavigatorClient");
  }
  
  public void clear() {
    setData(emptyTable);
    reset();
  }
  
  public void release() {
    setData(emptyTable);
    reset();
  }
  
  public boolean absolute(int paramInt) {
    if (paramInt < 0)
      paramInt += this.size; 
    if (paramInt < 0) {
      beforeFirst();
      return false;
    } 
    if (paramInt >= this.size) {
      afterLast();
      return false;
    } 
    if (this.size == 0)
      return false; 
    this.currentPos = paramInt;
    return true;
  }
  
  public void readSimple(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException {
    this.size = paramRowInputInterface.readInt();
    if (this.table.length < this.size)
      this.table = new Object[this.size][]; 
    for (byte b = 0; b < this.size; b++)
      this.table[b] = paramRowInputInterface.readData(paramResultMetaData.columnTypes); 
  }
  
  public void writeSimple(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException {
    paramRowOutputInterface.writeInt(this.size);
    for (byte b = 0; b < this.size; b++) {
      Object[] arrayOfObject = this.table[b];
      paramRowOutputInterface.writeData(paramResultMetaData.getColumnCount(), paramResultMetaData.columnTypes, arrayOfObject, null, null);
    } 
  }
  
  public void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException {
    this.id = paramRowInputInterface.readLong();
    this.size = paramRowInputInterface.readInt();
    this.currentOffset = paramRowInputInterface.readInt();
    this.baseBlockSize = paramRowInputInterface.readInt();
    if (this.table.length < this.baseBlockSize)
      this.table = new Object[this.baseBlockSize][]; 
    for (byte b = 0; b < this.baseBlockSize; b++)
      this.table[b] = paramRowInputInterface.readData(paramResultMetaData.columnTypes); 
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws HsqlException, IOException {
    int i = this.size - this.currentOffset;
    if (i > this.table.length)
      i = this.table.length; 
    paramRowOutputInterface.writeLong(this.id);
    paramRowOutputInterface.writeInt(this.size);
    paramRowOutputInterface.writeInt(this.currentOffset);
    paramRowOutputInterface.writeInt(i);
    for (byte b = 0; b < i; b++) {
      Object[] arrayOfObject = this.table[b];
      paramRowOutputInterface.writeData(paramResultMetaData.getColumnCount(), paramResultMetaData.columnTypes, arrayOfObject, null, null);
    } 
  }
  
  void getBlock(int paramInt) {
    try {
      RowSetNavigatorClient rowSetNavigatorClient = this.session.getRows(this.id, paramInt, this.baseBlockSize);
      this.table = rowSetNavigatorClient.table;
      this.currentOffset = rowSetNavigatorClient.currentOffset;
    } catch (HsqlException hsqlException) {}
  }
  
  private void ensureCapacity() {
    if (this.size == this.table.length) {
      boolean bool = (this.size == 0) ? true : (this.size * 2);
      Object[][] arrayOfObject = new Object[bool][];
      System.arraycopy(this.table, 0, arrayOfObject, 0, this.size);
      this.table = arrayOfObject;
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigatorClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */