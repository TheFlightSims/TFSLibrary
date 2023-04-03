/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ public class IntersectionMatrix implements Cloneable {
/*     */   private int[][] matrix;
/*     */   
/*     */   public IntersectionMatrix() {
/*  81 */     this.matrix = new int[3][3];
/*  82 */     setAll(-1);
/*     */   }
/*     */   
/*     */   public IntersectionMatrix(String elements) {
/*  92 */     this();
/*  93 */     set(elements);
/*     */   }
/*     */   
/*     */   public IntersectionMatrix(IntersectionMatrix other) {
/* 103 */     this();
/* 104 */     this.matrix[0][0] = other.matrix[0][0];
/* 105 */     this.matrix[0][1] = other.matrix[0][1];
/* 106 */     this.matrix[0][2] = other.matrix[0][2];
/* 107 */     this.matrix[1][0] = other.matrix[1][0];
/* 108 */     this.matrix[1][1] = other.matrix[1][1];
/* 109 */     this.matrix[1][2] = other.matrix[1][2];
/* 110 */     this.matrix[2][0] = other.matrix[2][0];
/* 111 */     this.matrix[2][1] = other.matrix[2][1];
/* 112 */     this.matrix[2][2] = other.matrix[2][2];
/*     */   }
/*     */   
/*     */   public void add(IntersectionMatrix im) {
/* 124 */     for (int i = 0; i < 3; i++) {
/* 125 */       for (int j = 0; j < 3; j++)
/* 126 */         setAtLeast(i, j, im.get(i, j)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isTrue(int actualDimensionValue) {
/* 140 */     if (actualDimensionValue >= 0 || actualDimensionValue == -2)
/* 141 */       return true; 
/* 143 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean matches(int actualDimensionValue, char requiredDimensionSymbol) {
/* 158 */     if (requiredDimensionSymbol == '*')
/* 159 */       return true; 
/* 161 */     if (requiredDimensionSymbol == 'T' && (actualDimensionValue >= 0 || actualDimensionValue == -2))
/* 163 */       return true; 
/* 165 */     if (requiredDimensionSymbol == 'F' && actualDimensionValue == -1)
/* 166 */       return true; 
/* 168 */     if (requiredDimensionSymbol == '0' && actualDimensionValue == 0)
/* 169 */       return true; 
/* 171 */     if (requiredDimensionSymbol == '1' && actualDimensionValue == 1)
/* 172 */       return true; 
/* 174 */     if (requiredDimensionSymbol == '2' && actualDimensionValue == 2)
/* 175 */       return true; 
/* 177 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean matches(String actualDimensionSymbols, String requiredDimensionSymbols) {
/* 192 */     IntersectionMatrix m = new IntersectionMatrix(actualDimensionSymbols);
/* 193 */     return m.matches(requiredDimensionSymbols);
/*     */   }
/*     */   
/*     */   public void set(int row, int column, int dimensionValue) {
/* 207 */     this.matrix[row][column] = dimensionValue;
/*     */   }
/*     */   
/*     */   public void set(String dimensionSymbols) {
/* 218 */     for (int i = 0; i < dimensionSymbols.length(); i++) {
/* 219 */       int row = i / 3;
/* 220 */       int col = i % 3;
/* 221 */       this.matrix[row][col] = Dimension.toDimensionValue(dimensionSymbols.charAt(i));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAtLeast(int row, int column, int minimumDimensionValue) {
/* 238 */     if (this.matrix[row][column] < minimumDimensionValue)
/* 239 */       this.matrix[row][column] = minimumDimensionValue; 
/*     */   }
/*     */   
/*     */   public void setAtLeastIfValid(int row, int column, int minimumDimensionValue) {
/* 256 */     if (row >= 0 && column >= 0)
/* 257 */       setAtLeast(row, column, minimumDimensionValue); 
/*     */   }
/*     */   
/*     */   public void setAtLeast(String minimumDimensionSymbols) {
/* 272 */     for (int i = 0; i < minimumDimensionSymbols.length(); i++) {
/* 273 */       int row = i / 3;
/* 274 */       int col = i % 3;
/* 275 */       setAtLeast(row, col, Dimension.toDimensionValue(minimumDimensionSymbols.charAt(i)));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setAll(int dimensionValue) {
/* 288 */     for (int ai = 0; ai < 3; ai++) {
/* 289 */       for (int bi = 0; bi < 3; bi++)
/* 290 */         this.matrix[ai][bi] = dimensionValue; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int get(int row, int column) {
/* 310 */     return this.matrix[row][column];
/*     */   }
/*     */   
/*     */   public boolean isDisjoint() {
/* 321 */     return (this.matrix[0][0] == -1 && this.matrix[0][1] == -1 && this.matrix[1][0] == -1 && this.matrix[1][1] == -1);
/*     */   }
/*     */   
/*     */   public boolean isIntersects() {
/* 335 */     return !isDisjoint();
/*     */   }
/*     */   
/*     */   public boolean isTouches(int dimensionOfGeometryA, int dimensionOfGeometryB) {
/* 349 */     if (dimensionOfGeometryA > dimensionOfGeometryB)
/* 351 */       return isTouches(dimensionOfGeometryB, dimensionOfGeometryA); 
/* 353 */     if ((dimensionOfGeometryA == 2 && dimensionOfGeometryB == 2) || (dimensionOfGeometryA == 1 && dimensionOfGeometryB == 1) || (dimensionOfGeometryA == 1 && dimensionOfGeometryB == 2) || (dimensionOfGeometryA == 0 && dimensionOfGeometryB == 2) || (dimensionOfGeometryA == 0 && dimensionOfGeometryB == 1))
/* 358 */       return (this.matrix[0][0] == -1 && (isTrue(this.matrix[0][1]) || isTrue(this.matrix[1][0]) || isTrue(this.matrix[1][1]))); 
/* 363 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isCrosses(int dimensionOfGeometryA, int dimensionOfGeometryB) {
/* 392 */     if ((dimensionOfGeometryA == 0 && dimensionOfGeometryB == 1) || (dimensionOfGeometryA == 0 && dimensionOfGeometryB == 2) || (dimensionOfGeometryA == 1 && dimensionOfGeometryB == 2))
/* 395 */       return (isTrue(this.matrix[0][0]) && isTrue(this.matrix[0][2])); 
/* 398 */     if ((dimensionOfGeometryA == 1 && dimensionOfGeometryB == 0) || (dimensionOfGeometryA == 2 && dimensionOfGeometryB == 0) || (dimensionOfGeometryA == 2 && dimensionOfGeometryB == 1))
/* 401 */       return (isTrue(this.matrix[0][0]) && isTrue(this.matrix[2][0])); 
/* 404 */     if (dimensionOfGeometryA == 1 && dimensionOfGeometryB == 1)
/* 405 */       return (this.matrix[0][0] == 0); 
/* 407 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isWithin() {
/* 418 */     return (isTrue(this.matrix[0][0]) && this.matrix[0][2] == -1 && this.matrix[1][2] == -1);
/*     */   }
/*     */   
/*     */   public boolean isContains() {
/* 431 */     return (isTrue(this.matrix[0][0]) && this.matrix[2][0] == -1 && this.matrix[2][1] == -1);
/*     */   }
/*     */   
/*     */   public boolean isCovers() {
/* 447 */     boolean hasPointInCommon = (isTrue(this.matrix[0][0]) || isTrue(this.matrix[0][1]) || isTrue(this.matrix[1][0]) || isTrue(this.matrix[1][1]));
/* 453 */     return (hasPointInCommon && this.matrix[2][0] == -1 && this.matrix[2][1] == -1);
/*     */   }
/*     */   
/*     */   public boolean isCoveredBy() {
/* 469 */     boolean hasPointInCommon = (isTrue(this.matrix[0][0]) || isTrue(this.matrix[0][1]) || isTrue(this.matrix[1][0]) || isTrue(this.matrix[1][1]));
/* 475 */     return (hasPointInCommon && this.matrix[0][2] == -1 && this.matrix[1][2] == -1);
/*     */   }
/*     */   
/*     */   public boolean isEquals(int dimensionOfGeometryA, int dimensionOfGeometryB) {
/* 499 */     if (dimensionOfGeometryA != dimensionOfGeometryB)
/* 500 */       return false; 
/* 502 */     return (isTrue(this.matrix[0][0]) && this.matrix[0][2] == -1 && this.matrix[1][2] == -1 && this.matrix[2][0] == -1 && this.matrix[2][1] == -1);
/*     */   }
/*     */   
/*     */   public boolean isOverlaps(int dimensionOfGeometryA, int dimensionOfGeometryB) {
/* 524 */     if ((dimensionOfGeometryA == 0 && dimensionOfGeometryB == 0) || (dimensionOfGeometryA == 2 && dimensionOfGeometryB == 2))
/* 526 */       return (isTrue(this.matrix[0][0]) && isTrue(this.matrix[0][2]) && isTrue(this.matrix[2][0])); 
/* 530 */     if (dimensionOfGeometryA == 1 && dimensionOfGeometryB == 1)
/* 531 */       return (this.matrix[0][0] == 1 && isTrue(this.matrix[0][2]) && isTrue(this.matrix[2][0])); 
/* 535 */     return false;
/*     */   }
/*     */   
/*     */   public boolean matches(String requiredDimensionSymbols) {
/* 549 */     if (requiredDimensionSymbols.length() != 9)
/* 550 */       throw new IllegalArgumentException("Should be length 9: " + requiredDimensionSymbols); 
/* 552 */     for (int ai = 0; ai < 3; ai++) {
/* 553 */       for (int bi = 0; bi < 3; bi++) {
/* 554 */         if (!matches(this.matrix[ai][bi], requiredDimensionSymbols.charAt(3 * ai + bi)))
/* 556 */           return false; 
/*     */       } 
/*     */     } 
/* 560 */     return true;
/*     */   }
/*     */   
/*     */   public IntersectionMatrix transpose() {
/* 569 */     int temp = this.matrix[1][0];
/* 570 */     this.matrix[1][0] = this.matrix[0][1];
/* 571 */     this.matrix[0][1] = temp;
/* 572 */     temp = this.matrix[2][0];
/* 573 */     this.matrix[2][0] = this.matrix[0][2];
/* 574 */     this.matrix[0][2] = temp;
/* 575 */     temp = this.matrix[2][1];
/* 576 */     this.matrix[2][1] = this.matrix[1][2];
/* 577 */     this.matrix[1][2] = temp;
/* 578 */     return this;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 589 */     StringBuffer buf = new StringBuffer("123456789");
/* 590 */     for (int ai = 0; ai < 3; ai++) {
/* 591 */       for (int bi = 0; bi < 3; bi++)
/* 592 */         buf.setCharAt(3 * ai + bi, Dimension.toDimensionSymbol(this.matrix[ai][bi])); 
/*     */     } 
/* 595 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\IntersectionMatrix.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */