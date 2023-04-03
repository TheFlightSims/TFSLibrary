package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.SessionInterface;
import org.hsqldb.error.Error;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;

public abstract class RowSetNavigator implements RangeIterator {
  SessionInterface session;
  
  long id;
  
  int size;
  
  int mode;
  
  boolean isIterator;
  
  int currentPos = -1;
  
  int rangePosition;
  
  public void setId(long paramLong) {
    this.id = paramLong;
  }
  
  public long getId() {
    return this.id;
  }
  
  public abstract Object[] getCurrent();
  
  public Object getCurrent(int paramInt) {
    Object[] arrayOfObject = getCurrent();
    return (arrayOfObject == null) ? null : arrayOfObject[paramInt];
  }
  
  public void setCurrent(Object[] paramArrayOfObject) {}
  
  public long getRowid() {
    return 0L;
  }
  
  public Object getRowidObject() {
    return null;
  }
  
  public abstract Row getCurrentRow();
  
  public abstract void add(Object[] paramArrayOfObject);
  
  public abstract boolean addRow(Row paramRow);
  
  public abstract void removeCurrent();
  
  public void reset() {
    this.currentPos = -1;
  }
  
  public abstract void clear();
  
  public abstract void release();
  
  public void setSession(SessionInterface paramSessionInterface) {
    this.session = paramSessionInterface;
  }
  
  public SessionInterface getSession() {
    return this.session;
  }
  
  public int getSize() {
    return this.size;
  }
  
  public boolean isEmpty() {
    return (this.size == 0);
  }
  
  public Object[] getNext() {
    return next() ? getCurrent() : null;
  }
  
  public boolean next() {
    if (hasNext()) {
      this.currentPos++;
      return true;
    } 
    if (this.size != 0)
      this.currentPos = this.size; 
    return false;
  }
  
  public boolean hasNext() {
    return (this.currentPos < this.size - 1);
  }
  
  public Row getNextRow() {
    throw Error.runtimeError(201, "RowSetNavigator");
  }
  
  public boolean setRowColumns(boolean[] paramArrayOfboolean) {
    throw Error.runtimeError(201, "RowSetNavigator");
  }
  
  public long getRowId() {
    throw Error.runtimeError(201, "RowSetNavigator");
  }
  
  public boolean beforeFirst() {
    reset();
    this.currentPos = -1;
    return true;
  }
  
  public boolean afterLast() {
    if (this.size == 0)
      return false; 
    reset();
    this.currentPos = this.size;
    return true;
  }
  
  public boolean first() {
    beforeFirst();
    return next();
  }
  
  public boolean last() {
    if (this.size == 0)
      return false; 
    if (isAfterLast())
      beforeFirst(); 
    while (hasNext())
      next(); 
    return true;
  }
  
  public int getRowNumber() {
    return this.currentPos;
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
    if (paramInt < this.currentPos)
      beforeFirst(); 
    while (paramInt > this.currentPos)
      next(); 
    return true;
  }
  
  public boolean relative(int paramInt) {
    int i = this.currentPos + paramInt;
    if (i < 0) {
      beforeFirst();
      return false;
    } 
    return absolute(i);
  }
  
  public boolean previous() {
    return relative(-1);
  }
  
  public boolean isFirst() {
    return (this.size > 0 && this.currentPos == 0);
  }
  
  public boolean isLast() {
    return (this.size > 0 && this.currentPos == this.size - 1);
  }
  
  public boolean isBeforeFirst() {
    return (this.size > 0 && this.currentPos == -1);
  }
  
  public boolean isAfterLast() {
    return (this.size > 0 && this.currentPos == this.size);
  }
  
  public void writeSimple(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException {
    throw Error.runtimeError(201, "RowSetNavigator");
  }
  
  public void readSimple(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException {
    throw Error.runtimeError(201, "RowSetNavigator");
  }
  
  public abstract void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException;
  
  public abstract void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException;
  
  public boolean isMemory() {
    return true;
  }
  
  public int getRangePosition() {
    return this.rangePosition;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */