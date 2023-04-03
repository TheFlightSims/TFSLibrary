/*     */ package org.jfree.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.text.DateFormat;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ 
/*     */ public class DateCellRenderer extends DefaultTableCellRenderer {
/*     */   private DateFormat formatter;
/*     */   
/*     */   public DateCellRenderer() {
/*  66 */     this(DateFormat.getDateTimeInstance());
/*     */   }
/*     */   
/*     */   public DateCellRenderer(DateFormat formatter) {
/*  76 */     this.formatter = formatter;
/*  77 */     setHorizontalAlignment(0);
/*     */   }
/*     */   
/*     */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/*  98 */     setFont(null);
/*  99 */     setText(this.formatter.format(value));
/* 100 */     if (isSelected) {
/* 101 */       setBackground(table.getSelectionBackground());
/*     */     } else {
/* 104 */       setBackground((Color)null);
/*     */     } 
/* 106 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\DateCellRenderer.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */