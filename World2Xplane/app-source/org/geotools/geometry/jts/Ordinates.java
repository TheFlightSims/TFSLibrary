/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ 
/*     */ class Ordinates {
/*     */   int curr;
/*     */   
/*     */   double[] ordinates;
/*     */   
/*     */   public Ordinates() {
/*  38 */     this.ordinates = new double[10];
/*  39 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   public Ordinates(int capacity) {
/*  43 */     this.ordinates = new double[capacity];
/*  44 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   public CoordinateSequence toCoordinateSequence(CoordinateSequenceFactory csfac) {
/*  51 */     CoordinateSequence cs = csfac.create(size(), 2);
/*  52 */     for (int i = 0; i <= this.curr; i++) {
/*  53 */       cs.setOrdinate(i, 0, this.ordinates[i * 2]);
/*  54 */       cs.setOrdinate(i, 1, this.ordinates[i * 2 + 1]);
/*     */     } 
/*  57 */     return cs;
/*     */   }
/*     */   
/*     */   int size() {
/*  65 */     return this.curr + 1;
/*     */   }
/*     */   
/*     */   void add(double x, double y) {
/*  74 */     this.curr++;
/*  75 */     if (this.curr * 2 + 1 >= this.ordinates.length) {
/*  76 */       int newSize = this.ordinates.length * 3 / 2;
/*  77 */       if (newSize < 10)
/*  78 */         newSize = 10; 
/*  80 */       double[] resized = new double[newSize];
/*  81 */       System.arraycopy(this.ordinates, 0, resized, 0, this.ordinates.length);
/*  82 */       this.ordinates = resized;
/*     */     } 
/*  84 */     this.ordinates[this.curr * 2] = x;
/*  85 */     this.ordinates[this.curr * 2 + 1] = y;
/*     */   }
/*     */   
/*     */   void clear() {
/*  92 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   double getOrdinate(int coordinate, int ordinate) {
/*  96 */     return this.ordinates[coordinate * 2 + ordinate];
/*     */   }
/*     */   
/*     */   public void init(CoordinateSequence cs) {
/* 100 */     clear();
/* 101 */     for (int i = 0; i < cs.size(); i++)
/* 102 */       add(cs.getOrdinate(i, 0), cs.getOrdinate(i, 1)); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     StringBuilder sb = new StringBuilder("Ordinates[");
/* 109 */     for (int i = 0; i <= this.curr; i++) {
/* 110 */       sb.append(this.ordinates[i * 2]);
/* 111 */       sb.append(" ");
/* 112 */       sb.append(this.ordinates[i * 2 + 1]);
/* 113 */       if (i < this.curr)
/* 114 */         sb.append(";"); 
/*     */     } 
/* 117 */     sb.append("]");
/* 118 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\Ordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */