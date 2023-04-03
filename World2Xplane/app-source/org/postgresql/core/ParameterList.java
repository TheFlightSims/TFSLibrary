package org.postgresql.core;

import java.io.InputStream;
import java.sql.SQLException;

public interface ParameterList {
  void registerOutParameter(int paramInt1, int paramInt2) throws SQLException;
  
  int getParameterCount();
  
  int getInParameterCount();
  
  int getOutParameterCount();
  
  int[] getTypeOIDs();
  
  void setIntParameter(int paramInt1, int paramInt2) throws SQLException;
  
  void setLiteralParameter(int paramInt1, String paramString, int paramInt2) throws SQLException;
  
  void setStringParameter(int paramInt1, String paramString, int paramInt2) throws SQLException;
  
  void setBytea(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) throws SQLException;
  
  void setBytea(int paramInt1, InputStream paramInputStream, int paramInt2) throws SQLException;
  
  void setNull(int paramInt1, int paramInt2) throws SQLException;
  
  ParameterList copy();
  
  void clear();
  
  String toString(int paramInt);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\ParameterList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */