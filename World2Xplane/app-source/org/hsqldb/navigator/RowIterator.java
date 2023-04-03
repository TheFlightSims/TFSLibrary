package org.hsqldb.navigator;

import org.hsqldb.Row;

public interface RowIterator {
  Row getNextRow();
  
  Object[] getNext();
  
  boolean hasNext();
  
  void removeCurrent();
  
  boolean setRowColumns(boolean[] paramArrayOfboolean);
  
  void release();
  
  long getRowId();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */