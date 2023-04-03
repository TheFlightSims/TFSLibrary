package org.hsqldb.navigator;

import org.hsqldb.Row;

public interface RangeIterator extends RowIterator {
  boolean isBeforeFirst();
  
  boolean next();
  
  Row getCurrentRow();
  
  Object[] getCurrent();
  
  Object getCurrent(int paramInt);
  
  void setCurrent(Object[] paramArrayOfObject);
  
  Object getRowidObject();
  
  void reset();
  
  int getRangePosition();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RangeIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */