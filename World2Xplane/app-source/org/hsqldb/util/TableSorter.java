package org.hsqldb.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class TableSorter extends AbstractTableModel {
  protected TableModel tableModel;
  
  public static final int DESCENDING = -1;
  
  public static final int NOT_SORTED = 0;
  
  public static final int ASCENDING = 1;
  
  private static Directive EMPTY_DIRECTIVE = new Directive(-1, 0);
  
  public static final Comparator COMPARABLE_COMPARATOR = new Comparator() {
      public int compare(Object param1Object1, Object param1Object2) {
        return (param1Object1 == param1Object2) ? 0 : ((param1Object1 == null) ? ((param1Object2 == null) ? 0 : -1) : ((param1Object2 == null) ? 1 : ((Comparable<Object>)param1Object1).compareTo(param1Object2)));
      }
    };
  
  public static final Comparator LEXICAL_COMPARATOR = new Comparator() {
      public int compare(Object param1Object1, Object param1Object2) {
        return param1Object1.toString().compareTo(param1Object2.toString());
      }
    };
  
  private Row[] viewToModel;
  
  private int[] modelToView;
  
  private JTableHeader tableHeader;
  
  private MouseListener mouseListener = new MouseHandler();
  
  private TableModelListener tableModelListener = new TableModelHandler();
  
  private Map columnComparators = new HashMap<Object, Object>();
  
  private List sortingColumns = new ArrayList();
  
  public TableSorter() {}
  
  public TableSorter(TableModel paramTableModel) {
    this();
    setTableModel(paramTableModel);
  }
  
  public TableSorter(TableModel paramTableModel, JTableHeader paramJTableHeader) {
    this();
    setTableHeader(paramJTableHeader);
    setTableModel(paramTableModel);
  }
  
  private void clearSortingState() {
    this.viewToModel = null;
    this.modelToView = null;
  }
  
  public TableModel getTableModel() {
    return this.tableModel;
  }
  
  public void setTableModel(TableModel paramTableModel) {
    if (this.tableModel != null)
      this.tableModel.removeTableModelListener(this.tableModelListener); 
    this.tableModel = paramTableModel;
    if (this.tableModel != null)
      this.tableModel.addTableModelListener(this.tableModelListener); 
    clearSortingState();
    fireTableStructureChanged();
  }
  
  public JTableHeader getTableHeader() {
    return this.tableHeader;
  }
  
  public void setTableHeader(JTableHeader paramJTableHeader) {
    if (this.tableHeader != null) {
      this.tableHeader.removeMouseListener(this.mouseListener);
      TableCellRenderer tableCellRenderer = this.tableHeader.getDefaultRenderer();
      if (tableCellRenderer instanceof SortableHeaderRenderer)
        this.tableHeader.setDefaultRenderer(((SortableHeaderRenderer)tableCellRenderer).tableCellRenderer); 
    } 
    this.tableHeader = paramJTableHeader;
    if (this.tableHeader != null) {
      this.tableHeader.addMouseListener(this.mouseListener);
      this.tableHeader.setDefaultRenderer(new SortableHeaderRenderer(this.tableHeader.getDefaultRenderer()));
    } 
  }
  
  public boolean isSorting() {
    return (this.sortingColumns.size() != 0);
  }
  
  private Directive getDirective(int paramInt) {
    for (byte b = 0; b < this.sortingColumns.size(); b++) {
      Directive directive = this.sortingColumns.get(b);
      if (directive.column == paramInt)
        return directive; 
    } 
    return EMPTY_DIRECTIVE;
  }
  
  public int getSortingStatus(int paramInt) {
    return (getDirective(paramInt)).direction;
  }
  
  private void sortingStatusChanged() {
    clearSortingState();
    fireTableDataChanged();
    if (this.tableHeader != null)
      this.tableHeader.repaint(); 
  }
  
  public void setSortingStatus(int paramInt1, int paramInt2) {
    Directive directive = getDirective(paramInt1);
    if (directive != EMPTY_DIRECTIVE)
      this.sortingColumns.remove(directive); 
    if (paramInt2 != 0)
      this.sortingColumns.add(new Directive(paramInt1, paramInt2)); 
    sortingStatusChanged();
  }
  
  protected Icon getHeaderRendererIcon(int paramInt1, int paramInt2) {
    Directive directive = getDirective(paramInt1);
    return (directive == EMPTY_DIRECTIVE) ? null : new Arrow((directive.direction == -1), paramInt2, this.sortingColumns.indexOf(directive));
  }
  
  private void cancelSorting() {
    this.sortingColumns.clear();
    sortingStatusChanged();
  }
  
  public void setColumnComparator(Class<?> paramClass, Comparator paramComparator) {
    if (paramComparator == null) {
      this.columnComparators.remove(paramClass);
    } else {
      this.columnComparators.put(paramClass, paramComparator);
    } 
  }
  
  protected Comparator getComparator(int paramInt) {
    Class<?> clazz = this.tableModel.getColumnClass(paramInt);
    Comparator comparator = (Comparator)this.columnComparators.get(clazz);
    return (comparator != null) ? comparator : (Comparable.class.isAssignableFrom(clazz) ? COMPARABLE_COMPARATOR : LEXICAL_COMPARATOR);
  }
  
  private Row[] getViewToModel() {
    if (this.viewToModel == null) {
      int i = this.tableModel.getRowCount();
      this.viewToModel = new Row[i];
      for (byte b = 0; b < i; b++)
        this.viewToModel[b] = new Row(b); 
      if (isSorting())
        Arrays.sort((Object[])this.viewToModel); 
    } 
    return this.viewToModel;
  }
  
  public int modelIndex(int paramInt) {
    return (getViewToModel()[paramInt]).modelIndex;
  }
  
  private int[] getModelToView() {
    if (this.modelToView == null) {
      int i = (getViewToModel()).length;
      this.modelToView = new int[i];
      for (byte b = 0; b < i; b++)
        this.modelToView[modelIndex(b)] = b; 
    } 
    return this.modelToView;
  }
  
  public int getRowCount() {
    return (this.tableModel == null) ? 0 : this.tableModel.getRowCount();
  }
  
  public int getColumnCount() {
    return (this.tableModel == null) ? 0 : this.tableModel.getColumnCount();
  }
  
  public String getColumnName(int paramInt) {
    return this.tableModel.getColumnName(paramInt);
  }
  
  public Class getColumnClass(int paramInt) {
    return this.tableModel.getColumnClass(paramInt);
  }
  
  public boolean isCellEditable(int paramInt1, int paramInt2) {
    return this.tableModel.isCellEditable(modelIndex(paramInt1), paramInt2);
  }
  
  public Object getValueAt(int paramInt1, int paramInt2) {
    return this.tableModel.getValueAt(modelIndex(paramInt1), paramInt2);
  }
  
  public void setValueAt(Object paramObject, int paramInt1, int paramInt2) {
    this.tableModel.setValueAt(paramObject, modelIndex(paramInt1), paramInt2);
  }
  
  private static class Directive {
    private int column;
    
    private int direction;
    
    public Directive(int param1Int1, int param1Int2) {
      this.column = param1Int1;
      this.direction = param1Int2;
    }
  }
  
  private class SortableHeaderRenderer implements TableCellRenderer {
    private TableCellRenderer tableCellRenderer;
    
    public SortableHeaderRenderer(TableCellRenderer param1TableCellRenderer) {
      this.tableCellRenderer = param1TableCellRenderer;
    }
    
    public Component getTableCellRendererComponent(JTable param1JTable, Object param1Object, boolean param1Boolean1, boolean param1Boolean2, int param1Int1, int param1Int2) {
      Component component = this.tableCellRenderer.getTableCellRendererComponent(param1JTable, param1Object, param1Boolean1, param1Boolean2, param1Int1, param1Int2);
      if (component instanceof JLabel) {
        JLabel jLabel = (JLabel)component;
        jLabel.setHorizontalTextPosition(2);
        int i = param1JTable.convertColumnIndexToModel(param1Int2);
        jLabel.setIcon(TableSorter.this.getHeaderRendererIcon(i, jLabel.getFont().getSize()));
      } 
      return component;
    }
  }
  
  private static class Arrow implements Icon {
    private boolean descending;
    
    private int size;
    
    private int priority;
    
    public Arrow(boolean param1Boolean, int param1Int1, int param1Int2) {
      this.descending = param1Boolean;
      this.size = param1Int1;
      this.priority = param1Int2;
    }
    
    public void paintIcon(Component param1Component, Graphics param1Graphics, int param1Int1, int param1Int2) {
      Color color = (param1Component == null) ? Color.GRAY : param1Component.getBackground();
      int i = (int)((this.size / 2) * Math.pow(0.8D, this.priority));
      int j = this.descending ? i : -i;
      param1Int2 = param1Int2 + 5 * this.size / 6 + (this.descending ? -j : 0);
      byte b = this.descending ? 1 : -1;
      param1Graphics.translate(param1Int1, param1Int2);
      param1Graphics.setColor(color.darker());
      param1Graphics.drawLine(i / 2, j, 0, 0);
      param1Graphics.drawLine(i / 2, j + b, 0, b);
      param1Graphics.setColor(color.brighter());
      param1Graphics.drawLine(i / 2, j, i, 0);
      param1Graphics.drawLine(i / 2, j + b, i, b);
      if (this.descending) {
        param1Graphics.setColor(color.darker().darker());
      } else {
        param1Graphics.setColor(color.brighter().brighter());
      } 
      param1Graphics.drawLine(i, 0, 0, 0);
      param1Graphics.setColor(color);
      param1Graphics.translate(-param1Int1, -param1Int2);
    }
    
    public int getIconWidth() {
      return this.size;
    }
    
    public int getIconHeight() {
      return this.size;
    }
  }
  
  private class MouseHandler extends MouseAdapter {
    private MouseHandler() {}
    
    public void mouseClicked(MouseEvent param1MouseEvent) {
      JTableHeader jTableHeader = (JTableHeader)param1MouseEvent.getSource();
      TableColumnModel tableColumnModel = jTableHeader.getColumnModel();
      int i = jTableHeader.columnAtPoint(param1MouseEvent.getPoint());
      int j = tableColumnModel.getColumn(i).getModelIndex();
      if (j != -1) {
        int k = TableSorter.this.getSortingStatus(j);
        if (!param1MouseEvent.isControlDown())
          TableSorter.this.cancelSorting(); 
        k += param1MouseEvent.isShiftDown() ? -1 : 1;
        k = (k + 4) % 3 - 1;
        TableSorter.this.setSortingStatus(j, k);
      } 
    }
  }
  
  private class TableModelHandler implements TableModelListener {
    private TableModelHandler() {}
    
    public void tableChanged(TableModelEvent param1TableModelEvent) {
      if (!TableSorter.this.isSorting()) {
        TableSorter.this.clearSortingState();
        TableSorter.this.fireTableChanged(param1TableModelEvent);
        return;
      } 
      if (param1TableModelEvent == null || param1TableModelEvent.getFirstRow() == -1) {
        TableSorter.this.cancelSorting();
        TableSorter.this.fireTableChanged(param1TableModelEvent);
        return;
      } 
      int i = param1TableModelEvent.getColumn();
      if (param1TableModelEvent.getFirstRow() == param1TableModelEvent.getLastRow() && i != -1 && TableSorter.this.getSortingStatus(i) == 0 && TableSorter.this.modelToView != null) {
        int j = TableSorter.this.getModelToView()[param1TableModelEvent.getFirstRow()];
        TableSorter.this.fireTableChanged(new TableModelEvent(TableSorter.this, j, j, i, param1TableModelEvent.getType()));
        return;
      } 
      TableSorter.this.clearSortingState();
      TableSorter.this.fireTableDataChanged();
    }
  }
  
  private class Row implements Comparable {
    private int modelIndex;
    
    public Row(int param1Int) {
      this.modelIndex = param1Int;
    }
    
    public int compareTo(Object param1Object) {
      int i = this.modelIndex;
      int j = ((Row)param1Object).modelIndex;
      for (TableSorter.Directive directive : TableSorter.this.sortingColumns) {
        int k = directive.column;
        Object object1 = TableSorter.this.tableModel.getValueAt(i, k);
        Object object2 = TableSorter.this.tableModel.getValueAt(j, k);
        int m = 0;
        if (object1 == null && object2 == null) {
          m = 0;
        } else if (object1 == null) {
          m = -1;
        } else if (object2 == null) {
          m = 1;
        } else {
          m = TableSorter.this.getComparator(k).compare(object1, object2);
        } 
        if (m != 0)
          return (directive.direction == -1) ? -m : m; 
      } 
      return 0;
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\hsqld\\util\TableSorter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */