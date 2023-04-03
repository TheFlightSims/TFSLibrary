package org.hsqldb.util;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

class GridSwing extends AbstractTableModel {
  JTable jtable = null;
  
  Object[] headers = new Object[0];
  
  Vector rows = new Vector();
  
  public String getColumnName(int paramInt) {
    return this.headers[paramInt].toString();
  }
  
  public Class getColumnClass(int paramInt) {
    if (this.rows.size() > 0) {
      Object object = getValueAt(0, paramInt);
      if (object != null)
        return (object instanceof java.sql.Timestamp || object instanceof java.sql.Time) ? Object.class : object.getClass(); 
    } 
    return super.getColumnClass(paramInt);
  }
  
  public int getColumnCount() {
    return this.headers.length;
  }
  
  public int getRowCount() {
    return this.rows.size();
  }
  
  public Object[] getHead() {
    return this.headers;
  }
  
  public Vector getData() {
    return this.rows;
  }
  
  public Object getValueAt(int paramInt1, int paramInt2) {
    if (paramInt1 >= this.rows.size())
      return null; 
    Object[] arrayOfObject = this.rows.elementAt(paramInt1);
    return (paramInt2 >= arrayOfObject.length) ? null : arrayOfObject[paramInt2];
  }
  
  public void setHead(Object[] paramArrayOfObject) {
    this.headers = new Object[paramArrayOfObject.length];
    for (byte b = 0; b < paramArrayOfObject.length; b++)
      this.headers[b] = paramArrayOfObject[b]; 
  }
  
  public void addRow(Object[] paramArrayOfObject) {
    Object[] arrayOfObject = new Object[paramArrayOfObject.length];
    for (byte b = 0; b < paramArrayOfObject.length; b++) {
      arrayOfObject[b] = paramArrayOfObject[b];
      if (arrayOfObject[b] == null);
    } 
    this.rows.addElement(arrayOfObject);
  }
  
  public void clear() {
    this.rows.removeAllElements();
  }
  
  public void setJTable(JTable paramJTable) {
    this.jtable = paramJTable;
  }
  
  public void fireTableChanged(TableModelEvent paramTableModelEvent) {
    super.fireTableChanged(paramTableModelEvent);
    autoSizeTableColumns(this.jtable);
  }
  
  public static void autoSizeTableColumns(JTable paramJTable) {
    TableModel tableModel = paramJTable.getModel();
    TableColumn tableColumn = null;
    Component component = null;
    int i = 0;
    int j = Integer.MIN_VALUE;
    int k = 0;
    TableCellRenderer tableCellRenderer = paramJTable.getTableHeader().getDefaultRenderer();
    for (byte b = 0; b < paramJTable.getColumnCount(); b++) {
      tableColumn = paramJTable.getColumnModel().getColumn(b);
      component = tableCellRenderer.getTableCellRendererComponent(paramJTable, tableColumn.getHeaderValue(), false, false, 0, 0);
      i = (component.getPreferredSize()).width + 10;
      j = Integer.MIN_VALUE;
      for (byte b1 = 0; b1 < Math.min(tableModel.getRowCount(), 30); b1++) {
        TableCellRenderer tableCellRenderer1 = paramJTable.getCellRenderer(b1, b);
        component = tableCellRenderer1.getTableCellRendererComponent(paramJTable, tableModel.getValueAt(b1, b), false, false, b1, b);
        k = (component.getPreferredSize()).width;
        if (k >= j)
          j = k; 
      } 
      tableColumn.setPreferredWidth(Math.max(i, j) + 10);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\GridSwing.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */