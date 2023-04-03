package org.hsqldb.navigator;

import java.io.IOException;
import org.hsqldb.Row;
import org.hsqldb.Session;
import org.hsqldb.result.ResultMetaData;
import org.hsqldb.rowio.RowInputInterface;
import org.hsqldb.rowio.RowOutputInterface;
import org.hsqldb.types.Type;

public interface RowSetNavigatorDataChange {
  void release();
  
  int getSize();
  
  int getRowPosition();
  
  boolean next();
  
  boolean beforeFirst();
  
  Row getCurrentRow();
  
  Object[] getCurrentChangedData();
  
  int[] getCurrentChangedColumns();
  
  void write(RowOutputInterface paramRowOutputInterface, ResultMetaData paramResultMetaData) throws IOException;
  
  void read(RowInputInterface paramRowInputInterface, ResultMetaData paramResultMetaData) throws IOException;
  
  void endMainDataSet();
  
  boolean addRow(Row paramRow);
  
  Object[] addRow(Session paramSession, Row paramRow, Object[] paramArrayOfObject, Type[] paramArrayOfType, int[] paramArrayOfint);
  
  boolean containsDeletedRow(Row paramRow);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\navigator\RowSetNavigatorDataChange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */