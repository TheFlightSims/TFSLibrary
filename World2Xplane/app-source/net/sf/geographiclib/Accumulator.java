/*     */ package net.sf.geographiclib;
/*     */ 
/*     */ public class Accumulator {
/*     */   private double _s;
/*     */   
/*     */   private double _t;
/*     */   
/*     */   public Accumulator(double y) {
/*  47 */     this._s = y;
/*  47 */     this._t = 0.0D;
/*     */   }
/*     */   
/*     */   public Accumulator(Accumulator a) {
/*  53 */     this._s = a._s;
/*  53 */     this._t = a._t;
/*     */   }
/*     */   
/*     */   public void Set(double y) {
/*  59 */     this._s = y;
/*  59 */     this._t = 0.0D;
/*     */   }
/*     */   
/*     */   public double Sum() {
/*  65 */     return this._s;
/*     */   }
/*     */   
/*     */   public double Sum(double y) {
/*  74 */     Accumulator a = new Accumulator(this);
/*  75 */     a.Add(y);
/*  76 */     return a._s;
/*     */   }
/*     */   
/*     */   public void Add(double y) {
/*  87 */     Pair r = GeoMath.sum(y, this._t);
/*  87 */     y = r.first;
/*  87 */     double u = r.second;
/*  88 */     r = GeoMath.sum(y, this._s);
/*  88 */     this._s = r.first;
/*  88 */     this._t = r.second;
/* 116 */     if (this._s == 0.0D) {
/* 117 */       this._s = u;
/*     */     } else {
/* 119 */       this._t += u;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void Negate() {
/* 126 */     this._s = -this._s;
/* 126 */     this._t = -this._t;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\net\sf\geographiclib\Accumulator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */