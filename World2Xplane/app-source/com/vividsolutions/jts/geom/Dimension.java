/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ public class Dimension {
/*     */   public static final int P = 0;
/*     */   
/*     */   public static final int L = 1;
/*     */   
/*     */   public static final int A = 2;
/*     */   
/*     */   public static final int FALSE = -1;
/*     */   
/*     */   public static final int TRUE = -2;
/*     */   
/*     */   public static final int DONTCARE = -3;
/*     */   
/*     */   public static final char SYM_FALSE = 'F';
/*     */   
/*     */   public static final char SYM_TRUE = 'T';
/*     */   
/*     */   public static final char SYM_DONTCARE = '*';
/*     */   
/*     */   public static final char SYM_P = '0';
/*     */   
/*     */   public static final char SYM_L = '1';
/*     */   
/*     */   public static final char SYM_A = '2';
/*     */   
/*     */   public static char toDimensionSymbol(int dimensionValue) {
/* 118 */     switch (dimensionValue) {
/*     */       case -1:
/* 120 */         return 'F';
/*     */       case -2:
/* 122 */         return 'T';
/*     */       case -3:
/* 124 */         return '*';
/*     */       case 0:
/* 126 */         return '0';
/*     */       case 1:
/* 128 */         return '1';
/*     */       case 2:
/* 130 */         return '2';
/*     */     } 
/* 132 */     throw new IllegalArgumentException("Unknown dimension value: " + dimensionValue);
/*     */   }
/*     */   
/*     */   public static int toDimensionValue(char dimensionSymbol) {
/* 146 */     switch (Character.toUpperCase(dimensionSymbol)) {
/*     */       case 'F':
/* 148 */         return -1;
/*     */       case 'T':
/* 150 */         return -2;
/*     */       case '*':
/* 152 */         return -3;
/*     */       case '0':
/* 154 */         return 0;
/*     */       case '1':
/* 156 */         return 1;
/*     */       case '2':
/* 158 */         return 2;
/*     */     } 
/* 160 */     throw new IllegalArgumentException("Unknown dimension symbol: " + dimensionSymbol);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\Dimension.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */