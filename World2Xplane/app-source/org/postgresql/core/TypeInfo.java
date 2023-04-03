package org.postgresql.core;

import java.sql.SQLException;
import java.util.Iterator;

public interface TypeInfo {
  void addCoreType(String paramString1, Integer paramInteger1, Integer paramInteger2, String paramString2, Integer paramInteger3);
  
  void addDataType(String paramString, Class paramClass) throws SQLException;
  
  int getSQLType(int paramInt) throws SQLException;
  
  int getSQLType(String paramString) throws SQLException;
  
  int getPGType(String paramString) throws SQLException;
  
  String getPGType(int paramInt) throws SQLException;
  
  int getPGArrayElement(int paramInt) throws SQLException;
  
  int getPGArrayType(String paramString) throws SQLException;
  
  char getArrayDelimiter(int paramInt) throws SQLException;
  
  Iterator getPGTypeNamesWithSQLTypes();
  
  Class getPGobject(String paramString);
  
  String getJavaClass(int paramInt) throws SQLException;
  
  String getTypeForAlias(String paramString);
  
  int getPrecision(int paramInt1, int paramInt2);
  
  int getScale(int paramInt1, int paramInt2);
  
  boolean isCaseSensitive(int paramInt);
  
  boolean isSigned(int paramInt);
  
  int getDisplaySize(int paramInt1, int paramInt2);
  
  int getMaximumPrecision(int paramInt);
  
  boolean requiresQuoting(int paramInt) throws SQLException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\postgresql\core\TypeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */