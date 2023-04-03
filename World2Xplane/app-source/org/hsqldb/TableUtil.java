package org.hsqldb;

import org.hsqldb.lib.ArrayUtil;
import org.hsqldb.types.Type;

public class TableUtil {
  static Table newSingleColumnTable(Database paramDatabase, HsqlNameManager.HsqlName paramHsqlName1, int paramInt, HsqlNameManager.HsqlName paramHsqlName2, Type paramType) {
    TableDerived tableDerived = new TableDerived(paramDatabase, paramHsqlName1, paramInt);
    ColumnSchema columnSchema = new ColumnSchema(paramHsqlName2, paramType, false, true, null);
    tableDerived.addColumn(columnSchema);
    tableDerived.createPrimaryKeyConstraint(tableDerived.getName(), new int[] { 0 }, true);
    return tableDerived;
  }
  
  static void setTableIndexesForSubquery(Table paramTable, boolean paramBoolean1, boolean paramBoolean2) {
    int[] arrayOfInt = null;
    if (paramBoolean1) {
      arrayOfInt = new int[paramTable.getColumnCount()];
      ArrayUtil.fillSequence(arrayOfInt);
    } 
    paramTable.createPrimaryKey((HsqlNameManager.HsqlName)null, paramBoolean2 ? arrayOfInt : null, false);
    if (paramBoolean2) {
      paramTable.fullIndex = paramTable.getPrimaryIndex();
    } else if (paramBoolean1) {
      paramTable.fullIndex = paramTable.createIndexForColumns((Session)null, arrayOfInt);
    } 
  }
  
  public static void addAutoColumns(Table paramTable, Type[] paramArrayOfType) {
    for (byte b = 0; b < paramArrayOfType.length; b++) {
      ColumnSchema columnSchema = new ColumnSchema(HsqlNameManager.getAutoColumnName(b), paramArrayOfType[b], true, false, null);
      paramTable.addColumnNoCheck(columnSchema);
    } 
  }
  
  public static void setColumnsInSchemaTable(Table paramTable, HsqlNameManager.HsqlName[] paramArrayOfHsqlName, Type[] paramArrayOfType) {
    for (byte b = 0; b < paramArrayOfHsqlName.length; b++) {
      HsqlNameManager.HsqlName hsqlName = paramArrayOfHsqlName[b];
      hsqlName = paramTable.database.nameManager.newColumnSchemaHsqlName(paramTable.getName(), hsqlName);
      ColumnSchema columnSchema = new ColumnSchema(hsqlName, paramArrayOfType[b], true, false, null);
      paramTable.addColumn(columnSchema);
    } 
    paramTable.setColumnStructures();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqldb\TableUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */