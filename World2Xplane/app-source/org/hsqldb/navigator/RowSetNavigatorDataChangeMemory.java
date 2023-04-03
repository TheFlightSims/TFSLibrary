package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.error.Error;
import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.lib.OrderedLongKeyHashMap;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

public class RowSetNavigatorDataChangeMemory implements RowSetNavigatorDataChange {
  public static RowSetNavigatorDataChangeMemory emptyRowSet = new RowSetNavigatorDataChangeMemory(null);
  
  int size;
  
  int currentPos = -1;
  
  OrderedLongKeyHashMap list;
  
  Session session;
  
  public RowSetNavigatorDataChangeMemory(Session paramSession) {
    this.session = paramSession;
    this.list = new OrderedLongKeyHashMap(64, true);
  }
  
  public void release() {
    beforeFirst();
    this.list.clear();
    this.size = 0;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public int getRowPosition() {
    return this.currentPos;
  }
  
  public boolean next() {
    if (this.currentPos < this.size - 1) {
      this.currentPos++;
      return true;
    } 
    this.currentPos = this.size - 1;
    return false;
  }
  
  public boolean beforeFirst() {
    this.currentPos = -1;
    return true;
  }
  
  public Row getCurrentRow() {
    return (Row)this.list.getValueByIndex(this.currentPos);
  }
  
  public Object[] getCurrentChangedData() {
    return (Object[])this.list.getSecondValueByIndex(this.currentPos);
  }
  
  public int[] getCurrentChangedColumns() {
    return (int[])this.list.getThirdValueByIndex(this.currentPos);
  }
  
  public void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException {}
  
  public void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException {}
  
  public void endMainDataSet() {}
  
  public boolean addRow(Row paramRow) {
    int i = this.list.getLookup(paramRow.getId());
    if (i == -1) {
      this.list.put(paramRow.getId(), paramRow, null);
      this.size++;
      return true;
    } 
    if (this.list.getSecondValueByIndex(i) != null) {
      if (this.session.database.sqlEnforceTDCD)
        throw Error.error(3900); 
      this.list.setSecondValueByIndex(i, null);
      this.list.setThirdValueByIndex(i, null);
      return true;
    } 
    return false;
  }
  
  public Object[] addRow(Session paramSession, Row paramRow, Object[] paramArrayOfObject, Type[] paramArrayOfType, int[] paramArrayOfint) {
    long l = paramRow.getId();
    int i = this.list.getLookup(l);
    if (i == -1) {
      this.list.put(l, paramRow, paramArrayOfObject);
      this.list.setThirdValueByIndex(this.size, paramArrayOfint);
      this.size++;
      return paramArrayOfObject;
    } 
    Object[] arrayOfObject1 = ((Row)this.list.getFirstByLookup(i)).getData();
    Object[] arrayOfObject2 = (Object[])this.list.getSecondValueByIndex(i);
    if (arrayOfObject2 == null) {
      if (paramSession.database.sqlEnforceTDCD)
        throw Error.error(3900); 
      return null;
    } 
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      int j = paramArrayOfint[b];
      if (paramArrayOfType[j].compare(paramSession, paramArrayOfObject[j], arrayOfObject2[j]) != 0)
        if (paramArrayOfType[j].compare(paramSession, arrayOfObject1[j], arrayOfObject2[j]) != 0) {
          if (paramSession.database.sqlEnforceTDCU)
            throw Error.error(3900); 
        } else {
          arrayOfObject2[j] = paramArrayOfObject[j];
        }  
    } 
    int[] arrayOfInt = (int[])this.list.getThirdValueByIndex(i);
    arrayOfInt = ArrayUtil.union(arrayOfInt, paramArrayOfint);
    this.list.setThirdValueByIndex(i, arrayOfInt);
    return arrayOfObject2;
  }
  
  public boolean containsDeletedRow(Row paramRow) {
    int i = this.list.getLookup(paramRow.getId());
    if (i == -1)
      return false; 
    Object[] arrayOfObject = (Object[])this.list.getSecondValueByIndex(i);
    return (arrayOfObject == null);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigatorDataChangeMemory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */