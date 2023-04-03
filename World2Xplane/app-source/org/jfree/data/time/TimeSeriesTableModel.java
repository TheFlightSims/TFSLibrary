/*     */ package org.jfree.data.time;
/*     */ 
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import org.jfree.data.general.SeriesChangeEvent;
/*     */ import org.jfree.data.general.SeriesChangeListener;
/*     */ 
/*     */ public class TimeSeriesTableModel extends AbstractTableModel implements SeriesChangeListener {
/*     */   private TimeSeries series;
/*     */   
/*     */   private boolean editable;
/*     */   
/*     */   private RegularTimePeriod newTimePeriod;
/*     */   
/*     */   private Number newValue;
/*     */   
/*     */   public TimeSeriesTableModel() {
/*  76 */     this(new TimeSeries("Untitled"));
/*     */   }
/*     */   
/*     */   public TimeSeriesTableModel(TimeSeries series) {
/*  85 */     this(series, false);
/*     */   }
/*     */   
/*     */   public TimeSeriesTableModel(TimeSeries series, boolean editable) {
/*  95 */     this.series = series;
/*  96 */     this.series.addChangeListener(this);
/*  97 */     this.editable = editable;
/*     */   }
/*     */   
/*     */   public int getColumnCount() {
/* 107 */     return 2;
/*     */   }
/*     */   
/*     */   public Class getColumnClass(int column) {
/* 118 */     if (column == 0)
/* 119 */       return String.class; 
/* 122 */     if (column == 1)
/* 123 */       return Double.class; 
/* 126 */     return null;
/*     */   }
/*     */   
/*     */   public String getColumnName(int column) {
/* 140 */     if (column == 0)
/* 141 */       return "Period:"; 
/* 144 */     if (column == 1)
/* 145 */       return "Value:"; 
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public int getRowCount() {
/* 160 */     return this.series.getItemCount();
/*     */   }
/*     */   
/*     */   public Object getValueAt(int row, int column) {
/* 173 */     if (row < this.series.getItemCount()) {
/* 174 */       if (column == 0)
/* 175 */         return this.series.getTimePeriod(row); 
/* 178 */       if (column == 1)
/* 179 */         return this.series.getValue(row); 
/* 182 */       return null;
/*     */     } 
/* 187 */     if (column == 0)
/* 188 */       return this.newTimePeriod; 
/* 191 */     if (column == 1)
/* 192 */       return this.newValue; 
/* 195 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isCellEditable(int row, int column) {
/* 211 */     if (this.editable) {
/* 212 */       if (column == 0 || column == 1)
/* 213 */         return true; 
/* 216 */       return false;
/*     */     } 
/* 220 */     return false;
/*     */   }
/*     */   
/*     */   public void setValueAt(Object value, int row, int column) {
/* 233 */     if (row < this.series.getItemCount()) {
/* 236 */       if (column == 1)
/*     */         try {
/* 238 */           Double v = Double.valueOf(value.toString());
/* 239 */           this.series.update(row, v);
/* 242 */         } catch (NumberFormatException nfe) {
/* 243 */           System.err.println("Number format exception");
/*     */         }  
/* 248 */     } else if (column == 0) {
/* 250 */       this.newTimePeriod = null;
/* 252 */     } else if (column == 1) {
/* 253 */       this.newValue = Double.valueOf(value.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void seriesChanged(SeriesChangeEvent event) {
/* 265 */     fireTableDataChanged();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\time\TimeSeriesTableModel.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */