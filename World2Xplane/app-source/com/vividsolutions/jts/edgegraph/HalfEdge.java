/*     */ package com.vividsolutions.jts.edgegraph;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geomgraph.Quadrant;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ 
/*     */ public class HalfEdge {
/*     */   private Coordinate orig;
/*     */   
/*     */   private HalfEdge sym;
/*     */   
/*     */   private HalfEdge next;
/*     */   
/*     */   public static HalfEdge create(Coordinate p0, Coordinate p1) {
/*  42 */     HalfEdge e0 = new HalfEdge(p0);
/*  43 */     HalfEdge e1 = new HalfEdge(p1);
/*  44 */     e0.init(e1);
/*  45 */     return e0;
/*     */   }
/*     */   
/*     */   public static HalfEdge init(HalfEdge e0, HalfEdge e1) {
/*  63 */     if (e0.sym != null || e1.sym != null || e0.next != null || e1.next != null)
/*  65 */       throw new IllegalStateException("Edges are already initialized"); 
/*  66 */     e0.init(e1);
/*  67 */     return e0;
/*     */   }
/*     */   
/*     */   public HalfEdge(Coordinate orig) {
/*  80 */     this.orig = orig;
/*     */   }
/*     */   
/*     */   protected void init(HalfEdge e) {
/*  85 */     setSym(e);
/*  86 */     e.setSym(this);
/*  88 */     setNext(e);
/*  89 */     e.setNext(this);
/*     */   }
/*     */   
/*     */   public Coordinate orig() {
/*  97 */     return this.orig;
/*     */   }
/*     */   
/*     */   public Coordinate dest() {
/* 104 */     return this.sym.orig;
/*     */   }
/*     */   
/*     */   public HalfEdge sym() {
/* 113 */     return this.sym;
/*     */   }
/*     */   
/*     */   private void setSym(HalfEdge e) {
/* 122 */     this.sym = e;
/*     */   }
/*     */   
/*     */   public HalfEdge next() {
/* 134 */     return this.next;
/*     */   }
/*     */   
/*     */   public HalfEdge prev() {
/* 144 */     return (this.sym.next()).sym;
/*     */   }
/*     */   
/*     */   public void setNext(HalfEdge e) {
/* 149 */     this.next = e;
/*     */   }
/*     */   
/*     */   public HalfEdge oNext() {
/* 153 */     return this.sym.next;
/*     */   }
/*     */   
/*     */   public HalfEdge find(Coordinate dest) {
/* 166 */     HalfEdge oNext = this;
/*     */     while (true) {
/* 168 */       if (oNext == null)
/* 168 */         return null; 
/* 169 */       if (oNext.dest().equals2D(dest))
/* 170 */         return oNext; 
/* 171 */       oNext = oNext.oNext();
/* 172 */       if (oNext == this)
/* 173 */         return null; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean equals(Coordinate p0, Coordinate p1) {
/* 184 */     return (this.orig.equals2D(p0) && this.sym.orig.equals(p1));
/*     */   }
/*     */   
/*     */   public void insert(HalfEdge e) {
/* 196 */     if (oNext() == this) {
/* 198 */       insertAfter(e);
/*     */       return;
/*     */     } 
/* 203 */     int ecmp = compareTo(e);
/* 204 */     HalfEdge ePrev = this;
/*     */     while (true) {
/* 206 */       HalfEdge oNext = ePrev.oNext();
/* 207 */       int cmp = oNext.compareTo(e);
/* 208 */       if (cmp != ecmp || oNext == this) {
/* 209 */         ePrev.insertAfter(e);
/*     */         return;
/*     */       } 
/* 212 */       ePrev = oNext;
/* 213 */       if (ePrev == this) {
/* 214 */         Assert.shouldNeverReachHere();
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void insertAfter(HalfEdge e) {
/* 225 */     Assert.equals(this.orig, e.orig());
/* 226 */     HalfEdge save = oNext();
/* 227 */     this.sym.setNext(e);
/* 228 */     e.sym().setNext(save);
/*     */   }
/*     */   
/*     */   public int compareTo(Object obj) {
/* 238 */     HalfEdge e = (HalfEdge)obj;
/* 239 */     int comp = compareAngularDirection(e);
/* 240 */     return comp;
/*     */   }
/*     */   
/*     */   public int compareAngularDirection(HalfEdge e) {
/* 268 */     double dx = deltaX();
/* 269 */     double dy = deltaY();
/* 270 */     double dx2 = e.deltaX();
/* 271 */     double dy2 = e.deltaY();
/* 274 */     if (dx == dx2 && dy == dy2)
/* 275 */       return 0; 
/* 277 */     double quadrant = Quadrant.quadrant(dx, dy);
/* 278 */     double quadrant2 = Quadrant.quadrant(dx2, dy2);
/* 281 */     if (quadrant > quadrant2)
/* 281 */       return 1; 
/* 282 */     if (quadrant < quadrant2)
/* 282 */       return -1; 
/* 286 */     return CGAlgorithms.computeOrientation(e.orig, e.dest(), dest());
/*     */   }
/*     */   
/*     */   public double deltaX() {
/* 294 */     return this.sym.orig.x - this.orig.x;
/*     */   }
/*     */   
/*     */   public double deltaY() {
/* 301 */     return this.sym.orig.y - this.orig.y;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 310 */     return "HE(" + this.orig.x + " " + this.orig.y + ", " + this.sym.orig.x + " " + this.sym.orig.y + ")";
/*     */   }
/*     */   
/*     */   public int degree() {
/* 324 */     int degree = 0;
/* 325 */     HalfEdge e = this;
/*     */     while (true) {
/* 327 */       degree++;
/* 328 */       e = e.oNext();
/* 329 */       if (e == this)
/* 330 */         return degree; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public HalfEdge prevNode() {
/* 342 */     HalfEdge e = this;
/* 343 */     while (e.degree() == 2) {
/* 344 */       e = e.prev();
/* 345 */       if (e == this)
/* 346 */         return null; 
/*     */     } 
/* 348 */     return e;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\edgegraph\HalfEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */