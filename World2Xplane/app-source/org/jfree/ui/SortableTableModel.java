/*     */ package org.jfree.ui;
/*     */ 
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public abstract class SortableTableModel extends AbstractTableModel {
/*  66 */   private int sortingColumn = -1;
/*     */   
/*     */   private boolean ascending = true;
/*     */   
/*     */   public int getSortingColumn() {
/*  77 */     return this.sortingColumn;
/*     */   }
/*     */   
/*     */   public boolean isAscending() {
/*  88 */     return this.ascending;
/*     */   }
/*     */   
/*     */   public void setAscending(boolean flag) {
/*  98 */     this.ascending = flag;
/*     */   }
/*     */   
/*     */   public void sortByColumn(int column, boolean ascending) {
/* 108 */     if (isSortable(column))
/* 109 */       this.sortingColumn = column; 
/*     */   }
/*     */   
/*     */   public boolean isSortable(int column) {
/* 121 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\ui\SortableTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */