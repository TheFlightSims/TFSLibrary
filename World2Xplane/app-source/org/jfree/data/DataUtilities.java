/*     */ package org.jfree.data;
/*     */ 
/*     */ public abstract class DataUtilities {
/*     */   public static double calculateColumnTotal(Values2D data, int column) {
/*  67 */     double total = 0.0D;
/*  68 */     int rowCount = data.getRowCount();
/*  69 */     for (int r = 0; r < rowCount; r++) {
/*  70 */       Number n = data.getValue(r, column);
/*  71 */       if (n != null)
/*  72 */         total += n.doubleValue(); 
/*     */     } 
/*  75 */     return total;
/*     */   }
/*     */   
/*     */   public static double calculateRowTotal(Values2D data, int row) {
/*  88 */     double total = 0.0D;
/*  89 */     int columnCount = data.getColumnCount();
/*  90 */     for (int c = 0; c < columnCount; c++) {
/*  91 */       Number n = data.getValue(row, c);
/*  92 */       if (n != null)
/*  93 */         total += n.doubleValue(); 
/*     */     } 
/*  96 */     return total;
/*     */   }
/*     */   
/*     */   public static Number[] createNumberArray(double[] data) {
/* 108 */     if (data == null)
/* 109 */       throw new IllegalArgumentException("Null 'data' argument."); 
/* 111 */     Number[] result = new Number[data.length];
/* 112 */     for (int i = 0; i < data.length; i++)
/* 113 */       result[i] = new Double(data[i]); 
/* 115 */     return result;
/*     */   }
/*     */   
/*     */   public static Number[][] createNumberArray2D(double[][] data) {
/* 127 */     if (data == null)
/* 128 */       throw new IllegalArgumentException("Null 'data' argument."); 
/* 130 */     int l1 = data.length;
/* 131 */     Number[][] result = new Number[l1][];
/* 132 */     for (int i = 0; i < l1; i++)
/* 133 */       result[i] = createNumberArray(data[i]); 
/* 135 */     return result;
/*     */   }
/*     */   
/*     */   public static KeyedValues getCumulativePercentages(KeyedValues data) {
/* 149 */     if (data == null)
/* 150 */       throw new IllegalArgumentException("Null 'data' argument."); 
/* 152 */     DefaultKeyedValues result = new DefaultKeyedValues();
/* 153 */     double total = 0.0D;
/* 154 */     for (int i = 0; i < data.getItemCount(); i++) {
/* 155 */       Number v = data.getValue(i);
/* 156 */       if (v != null)
/* 157 */         total += v.doubleValue(); 
/*     */     } 
/* 160 */     double runningTotal = 0.0D;
/* 161 */     for (int j = 0; j < data.getItemCount(); j++) {
/* 162 */       Number v = data.getValue(j);
/* 163 */       if (v != null)
/* 164 */         runningTotal += v.doubleValue(); 
/* 166 */       result.addValue(data.getKey(j), new Double(runningTotal / total));
/*     */     } 
/* 168 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\DataUtilities.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */