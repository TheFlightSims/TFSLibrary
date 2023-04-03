/*    */ package org.jfree.ui.about;
/*    */ 
/*    */ import javax.swing.table.TableColumn;
/*    */ import javax.swing.table.TableColumnModel;
/*    */ import org.jfree.ui.SortableTable;
/*    */ 
/*    */ public class SystemProperties {
/*    */   public static SortableTable createSystemPropertiesTable() {
/* 74 */     SystemPropertiesTableModel properties = new SystemPropertiesTableModel();
/* 75 */     SortableTable table = new SortableTable(properties);
/* 77 */     TableColumnModel model = table.getColumnModel();
/* 78 */     TableColumn column = model.getColumn(0);
/* 79 */     column.setPreferredWidth(200);
/* 80 */     column = model.getColumn(1);
/* 81 */     column.setPreferredWidth(350);
/* 83 */     table.setAutoResizeMode(2);
/* 84 */     return table;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\about\SystemProperties.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */