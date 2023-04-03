/*     */ package org.geotools.filter.function;
/*     */ 
/*     */ public final class RangedClassifier extends Classifier {
/*     */   Comparable<?>[] min;
/*     */   
/*     */   Comparable<?>[] max;
/*     */   
/*     */   public RangedClassifier(Comparable[] min, Comparable[] max) {
/*  50 */     this.min = (Comparable<?>[])min;
/*  51 */     this.max = (Comparable<?>[])max;
/*  53 */     this.titles = new String[min.length];
/*  54 */     for (int i = 0; i < this.titles.length; i++)
/*  55 */       this.titles[i] = generateTitle(min[i], max[i]); 
/*     */   }
/*     */   
/*     */   private String generateTitle(Comparable<?> min, Comparable<?> max) {
/*  66 */     if (min == null && max == null)
/*  67 */       return "Other"; 
/*  69 */     if (min == null)
/*  70 */       return "Below " + truncateZeros(String.valueOf(max)); 
/*  72 */     if (max == null)
/*  73 */       return "Above " + truncateZeros(String.valueOf(min)); 
/*  76 */     return truncateZeros(String.valueOf(min)) + ".." + truncateZeros(String.valueOf(max));
/*     */   }
/*     */   
/*     */   private String truncateZeros(String str) {
/*  85 */     if (str.indexOf(".") > -1) {
/*  86 */       while (str.endsWith("0"))
/*  87 */         str = str.substring(0, str.length() - 1); 
/*  89 */       if (str.endsWith("."))
/*  90 */         str = str.substring(0, str.length() - 1); 
/*     */     } 
/*  93 */     return str;
/*     */   }
/*     */   
/*     */   public int getSize() {
/*  97 */     return Math.min(this.min.length, this.max.length);
/*     */   }
/*     */   
/*     */   public Object getMin(int slot) {
/* 101 */     return this.min[slot];
/*     */   }
/*     */   
/*     */   public Object getMax(int slot) {
/* 105 */     return this.max[slot];
/*     */   }
/*     */   
/*     */   public int classify(Object value) {
/* 109 */     return classify((Comparable)value);
/*     */   }
/*     */   
/*     */   private int classify(Comparable<?> val) {
/* 114 */     Comparable<?> value = val;
/* 115 */     if (val instanceof Integer)
/* 116 */       value = new Double(((Integer)val).intValue()); 
/* 119 */     int last = this.min.length - 1;
/* 120 */     for (int i = 0; i <= last; i++) {
/* 121 */       Comparable<?> localMin = this.min[i];
/* 122 */       Comparable<?> localMax = this.max[i];
/* 124 */       if ((localMin == null || localMin.compareTo((T)value) < 1) && (localMax == null || localMax.compareTo(value) > 0))
/* 126 */         return i; 
/*     */     } 
/* 129 */     if (compareTo(this.max[last], value) == 0)
/* 130 */       return last; 
/* 132 */     return -1;
/*     */   }
/*     */   
/*     */   private int compareTo(Comparable compare, Comparable<Comparable> value) {
/* 136 */     if (compare == null && value == null)
/* 137 */       return 0; 
/* 139 */     if (compare == null)
/* 140 */       return -1; 
/* 142 */     if (value == null)
/* 143 */       return 1; 
/* 145 */     return value.compareTo(compare);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\function\RangedClassifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */