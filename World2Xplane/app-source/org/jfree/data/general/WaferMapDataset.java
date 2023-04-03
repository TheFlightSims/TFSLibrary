/*     */ package org.jfree.data.general;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.jfree.data.DefaultKeyedValues2D;
/*     */ 
/*     */ public class WaferMapDataset extends AbstractDataset {
/*     */   private DefaultKeyedValues2D data;
/*     */   
/*     */   private int maxChipX;
/*     */   
/*     */   private int maxChipY;
/*     */   
/*     */   private double chipSpace;
/*     */   
/*     */   private Double maxValue;
/*     */   
/*     */   private Double minValue;
/*     */   
/*     */   private static final double DEFAULT_CHIP_SPACE = 1.0D;
/*     */   
/*     */   public WaferMapDataset(int maxChipX, int maxChipY) {
/*  90 */     this(maxChipX, maxChipY, null);
/*     */   }
/*     */   
/*     */   public WaferMapDataset(int maxChipX, int maxChipY, Number chipSpace) {
/* 102 */     this.maxValue = new Double(Double.NEGATIVE_INFINITY);
/* 103 */     this.minValue = new Double(Double.POSITIVE_INFINITY);
/* 104 */     this.data = new DefaultKeyedValues2D();
/* 106 */     this.maxChipX = maxChipX;
/* 107 */     this.maxChipY = maxChipY;
/* 108 */     if (chipSpace == null) {
/* 109 */       this.chipSpace = 1.0D;
/*     */     } else {
/* 112 */       this.chipSpace = chipSpace.doubleValue();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addValue(Number value, Comparable chipx, Comparable chipy) {
/* 125 */     setValue(value, chipx, chipy);
/*     */   }
/*     */   
/*     */   public void addValue(int v, int x, int y) {
/* 136 */     setValue(new Double(v), new Integer(x), new Integer(y));
/*     */   }
/*     */   
/*     */   public void setValue(Number value, Comparable chipx, Comparable chipy) {
/* 147 */     this.data.setValue(value, chipx, chipy);
/* 148 */     if (isMaxValue(value))
/* 149 */       this.maxValue = (Double)value; 
/* 151 */     if (isMinValue(value))
/* 152 */       this.minValue = (Double)value; 
/*     */   }
/*     */   
/*     */   public int getUniqueValueCount() {
/* 162 */     return getUniqueValues().size();
/*     */   }
/*     */   
/*     */   public Set getUniqueValues() {
/* 171 */     Set unique = new TreeSet();
/* 173 */     for (int r = 0; r < this.data.getRowCount(); r++) {
/* 174 */       for (int c = 0; c < this.data.getColumnCount(); c++) {
/* 175 */         Number value = this.data.getValue(r, c);
/* 176 */         if (value != null)
/* 177 */           unique.add(value); 
/*     */       } 
/*     */     } 
/* 181 */     return unique;
/*     */   }
/*     */   
/*     */   public Number getChipValue(int chipx, int chipy) {
/* 193 */     return getChipValue(new Integer(chipx), new Integer(chipy));
/*     */   }
/*     */   
/*     */   public Number getChipValue(Comparable chipx, Comparable chipy) {
/* 205 */     int rowIndex = this.data.getRowIndex(chipx);
/* 206 */     if (rowIndex < 0)
/* 207 */       return null; 
/* 209 */     int colIndex = this.data.getColumnIndex(chipy);
/* 210 */     if (colIndex < 0)
/* 211 */       return null; 
/* 213 */     return this.data.getValue(rowIndex, colIndex);
/*     */   }
/*     */   
/*     */   public boolean isMaxValue(Number check) {
/* 224 */     if (check.doubleValue() > this.maxValue.doubleValue())
/* 225 */       return true; 
/* 227 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isMinValue(Number check) {
/* 238 */     if (check.doubleValue() < this.minValue.doubleValue())
/* 239 */       return true; 
/* 241 */     return false;
/*     */   }
/*     */   
/*     */   public Number getMaxValue() {
/* 250 */     return this.maxValue;
/*     */   }
/*     */   
/*     */   public Number getMinValue() {
/* 259 */     return this.minValue;
/*     */   }
/*     */   
/*     */   public int getMaxChipX() {
/* 268 */     return this.maxChipX;
/*     */   }
/*     */   
/*     */   public void setMaxChipX(int maxChipX) {
/* 277 */     this.maxChipX = maxChipX;
/*     */   }
/*     */   
/*     */   public int getMaxChipY() {
/* 286 */     return this.maxChipY;
/*     */   }
/*     */   
/*     */   public void setMaxChipY(int maxChipY) {
/* 295 */     this.maxChipY = maxChipY;
/*     */   }
/*     */   
/*     */   public double getChipSpace() {
/* 304 */     return this.chipSpace;
/*     */   }
/*     */   
/*     */   public void setChipSpace(double space) {
/* 313 */     this.chipSpace = space;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\general\WaferMapDataset.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */