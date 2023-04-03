/*     */ package org.jfree.data.xy;
/*     */ 
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.TableModel;
/*     */ import org.jfree.data.general.DatasetChangeEvent;
/*     */ import org.jfree.data.general.DatasetChangeListener;
/*     */ 
/*     */ public class XYDatasetTableModel extends AbstractTableModel implements TableModel, DatasetChangeListener {
/*  71 */   TableXYDataset model = null;
/*     */   
/*     */   public XYDatasetTableModel() {}
/*     */   
/*     */   public XYDatasetTableModel(TableXYDataset dataset) {
/*  86 */     this();
/*  87 */     this.model = dataset;
/*  88 */     this.model.addChangeListener(this);
/*     */   }
/*     */   
/*     */   public void setModel(TableXYDataset dataset) {
/*  97 */     this.model = dataset;
/*  98 */     this.model.addChangeListener(this);
/*  99 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 108 */     if (this.model == null)
/* 109 */       return 0; 
/* 111 */     return this.model.getItemCount();
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 120 */     if (this.model == null)
/* 121 */       return 0; 
/* 123 */     return this.model.getSeriesCount() + 1;
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) {
/* 134 */     if (this.model == null)
/* 135 */       return super.getColumnName(column); 
/* 137 */     if (column < 1)
/* 138 */       return "X Value"; 
/* 141 */     return this.model.getSeriesKey(column - 1).toString();
/*     */   }
/*     */   
/*     */   public Object getValueAt(int row, int column) {
/* 155 */     if (this.model == null)
/* 156 */       return null; 
/* 158 */     if (column < 1)
/* 159 */       return this.model.getX(0, row); 
/* 162 */     return this.model.getY(column - 1, row);
/*     */   }
/*     */   
/*     */   public void datasetChanged(DatasetChangeEvent event) {
/* 174 */     fireTableDataChanged();
/*     */   }
/*     */   
/*     */   public boolean isCellEditable(int row, int column) {
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public void setValueAt(Object value, int row, int column) {
/* 197 */     if (isCellEditable(row, column));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\xy\XYDatasetTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */