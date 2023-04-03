/*    */ package org.jfree.ui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.text.NumberFormat;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.table.DefaultTableCellRenderer;
/*    */ 
/*    */ public class NumberCellRenderer extends DefaultTableCellRenderer {
/*    */   public NumberCellRenderer() {
/* 66 */     setHorizontalAlignment(4);
/*    */   }
/*    */   
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 87 */     setFont(null);
/* 88 */     NumberFormat nf = NumberFormat.getNumberInstance();
/* 89 */     setText(nf.format(value));
/* 90 */     if (isSelected) {
/* 91 */       setBackground(table.getSelectionBackground());
/*    */     } else {
/* 94 */       setBackground((Color)null);
/*    */     } 
/* 96 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\NumberCellRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */