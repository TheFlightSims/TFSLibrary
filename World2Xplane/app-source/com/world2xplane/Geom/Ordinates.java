/*     */ package com.world2xplane.Geom;
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
/*  60 */     this.ordinates = new double[10];
/*  61 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   public Ordinates(int capacity) {
/*  65 */     this.ordinates = new double[capacity];
/*  66 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   public CoordinateSequence toCoordinateSequence(CoordinateSequenceFactory csfac) {
/*  73 */     CoordinateSequence cs = csfac.create(size(), 2);
/*  74 */     for (int i = 0; i <= this.curr; i++) {
/*  75 */       cs.setOrdinate(i, 0, this.ordinates[i * 2]);
/*  76 */       cs.setOrdinate(i, 1, this.ordinates[i * 2 + 1]);
/*     */     } 
/*  79 */     return cs;
/*     */   }
/*     */   
/*     */   int size() {
/*  87 */     return this.curr + 1;
/*     */   }
/*     */   
/*     */   void add(double x, double y) {
/*  96 */     this.curr++;
/*  97 */     if (this.curr * 2 + 1 >= this.ordinates.length) {
/*  98 */       int newSize = this.ordinates.length * 3 / 2;
/*  99 */       if (newSize < 10)
/* 100 */         newSize = 10; 
/* 102 */       double[] resized = new double[newSize];
/* 103 */       System.arraycopy(this.ordinates, 0, resized, 0, this.ordinates.length);
/* 104 */       this.ordinates = resized;
/*     */     } 
/* 106 */     this.ordinates[this.curr * 2] = x;
/* 107 */     this.ordinates[this.curr * 2 + 1] = y;
/*     */   }
/*     */   
/*     */   void clear() {
/* 114 */     this.curr = -1;
/*     */   }
/*     */   
/*     */   double getOrdinate(int coordinate, int ordinate) {
/* 118 */     return this.ordinates[coordinate * 2 + ordinate];
/*     */   }
/*     */   
/*     */   public void init(CoordinateSequence cs) {
/* 122 */     clear();
/* 123 */     for (int i = 0; i < cs.size(); i++)
/* 124 */       add(cs.getOrdinate(i, 0), cs.getOrdinate(i, 1)); 
/*     */   }
/*     */   
/*     */   public String toString() {
/* 130 */     StringBuilder sb = new StringBuilder("Ordinates[");
/* 131 */     for (int i = 0; i <= this.curr; i++) {
/* 132 */       sb.append(this.ordinates[i * 2]);
/* 133 */       sb.append(" ");
/* 134 */       sb.append(this.ordinates[i * 2 + 1]);
/* 135 */       if (i < this.curr)
/* 136 */         sb.append(";"); 
/*     */     } 
/* 139 */     sb.append("]");
/* 140 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Ordinates.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */