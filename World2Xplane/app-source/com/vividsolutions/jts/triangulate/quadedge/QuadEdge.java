/*     */ package com.vividsolutions.jts.triangulate.quadedge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ 
/*     */ public class QuadEdge {
/*     */   private QuadEdge rot;
/*     */   
/*     */   private Vertex vertex;
/*     */   
/*     */   private QuadEdge next;
/*     */   
/*     */   public static QuadEdge makeEdge(Vertex o, Vertex d) {
/*  70 */     QuadEdge q0 = new QuadEdge();
/*  71 */     QuadEdge q1 = new QuadEdge();
/*  72 */     QuadEdge q2 = new QuadEdge();
/*  73 */     QuadEdge q3 = new QuadEdge();
/*  75 */     q0.rot = q1;
/*  76 */     q1.rot = q2;
/*  77 */     q2.rot = q3;
/*  78 */     q3.rot = q0;
/*  80 */     q0.setNext(q0);
/*  81 */     q1.setNext(q3);
/*  82 */     q2.setNext(q2);
/*  83 */     q3.setNext(q1);
/*  85 */     QuadEdge base = q0;
/*  86 */     base.setOrig(o);
/*  87 */     base.setDest(d);
/*  88 */     return base;
/*     */   }
/*     */   
/*     */   public static QuadEdge connect(QuadEdge a, QuadEdge b) {
/* 100 */     QuadEdge e = makeEdge(a.dest(), b.orig());
/* 101 */     splice(e, a.lNext());
/* 102 */     splice(e.sym(), b);
/* 103 */     return e;
/*     */   }
/*     */   
/*     */   public static void splice(QuadEdge a, QuadEdge b) {
/* 120 */     QuadEdge alpha = a.oNext().rot();
/* 121 */     QuadEdge beta = b.oNext().rot();
/* 123 */     QuadEdge t1 = b.oNext();
/* 124 */     QuadEdge t2 = a.oNext();
/* 125 */     QuadEdge t3 = beta.oNext();
/* 126 */     QuadEdge t4 = alpha.oNext();
/* 128 */     a.setNext(t1);
/* 129 */     b.setNext(t2);
/* 130 */     alpha.setNext(t3);
/* 131 */     beta.setNext(t4);
/*     */   }
/*     */   
/*     */   public static void swap(QuadEdge e) {
/* 140 */     QuadEdge a = e.oPrev();
/* 141 */     QuadEdge b = e.sym().oPrev();
/* 142 */     splice(e, a);
/* 143 */     splice(e.sym(), b);
/* 144 */     splice(e, a.lNext());
/* 145 */     splice(e.sym(), b.lNext());
/* 146 */     e.setOrig(a.dest());
/* 147 */     e.setDest(b.dest());
/*     */   }
/*     */   
/* 154 */   private Object data = null;
/*     */   
/*     */   public QuadEdge getPrimary() {
/* 176 */     if (orig().getCoordinate().compareTo(dest().getCoordinate()) <= 0)
/* 177 */       return this; 
/* 179 */     return sym();
/*     */   }
/*     */   
/*     */   public void setData(Object data) {
/* 188 */     this.data = data;
/*     */   }
/*     */   
/*     */   public Object getData() {
/* 197 */     return this.data;
/*     */   }
/*     */   
/*     */   public void delete() {
/* 209 */     this.rot = null;
/*     */   }
/*     */   
/*     */   public boolean isLive() {
/* 218 */     return (this.rot != null);
/*     */   }
/*     */   
/*     */   public void setNext(QuadEdge next) {
/* 228 */     this.next = next;
/*     */   }
/*     */   
/*     */   public final QuadEdge rot() {
/* 242 */     return this.rot;
/*     */   }
/*     */   
/*     */   public final QuadEdge invRot() {
/* 251 */     return this.rot.sym();
/*     */   }
/*     */   
/*     */   public final QuadEdge sym() {
/* 260 */     return this.rot.rot;
/*     */   }
/*     */   
/*     */   public final QuadEdge oNext() {
/* 269 */     return this.next;
/*     */   }
/*     */   
/*     */   public final QuadEdge oPrev() {
/* 278 */     return this.rot.next.rot;
/*     */   }
/*     */   
/*     */   public final QuadEdge dNext() {
/* 287 */     return sym().oNext().sym();
/*     */   }
/*     */   
/*     */   public final QuadEdge dPrev() {
/* 296 */     return invRot().oNext().invRot();
/*     */   }
/*     */   
/*     */   public final QuadEdge lNext() {
/* 305 */     return invRot().oNext().rot();
/*     */   }
/*     */   
/*     */   public final QuadEdge lPrev() {
/* 314 */     return this.next.sym();
/*     */   }
/*     */   
/*     */   public final QuadEdge rNext() {
/* 323 */     return this.rot.next.invRot();
/*     */   }
/*     */   
/*     */   public final QuadEdge rPrev() {
/* 332 */     return sym().oNext();
/*     */   }
/*     */   
/*     */   void setOrig(Vertex o) {
/* 344 */     this.vertex = o;
/*     */   }
/*     */   
/*     */   void setDest(Vertex d) {
/* 353 */     sym().setOrig(d);
/*     */   }
/*     */   
/*     */   public final Vertex orig() {
/* 362 */     return this.vertex;
/*     */   }
/*     */   
/*     */   public final Vertex dest() {
/* 371 */     return sym().orig();
/*     */   }
/*     */   
/*     */   public double getLength() {
/* 380 */     return orig().getCoordinate().distance(dest().getCoordinate());
/*     */   }
/*     */   
/*     */   public boolean equalsNonOriented(QuadEdge qe) {
/* 391 */     if (equalsOriented(qe))
/* 392 */       return true; 
/* 393 */     if (equalsOriented(qe.sym()))
/* 394 */       return true; 
/* 395 */     return false;
/*     */   }
/*     */   
/*     */   public boolean equalsOriented(QuadEdge qe) {
/* 406 */     if (orig().getCoordinate().equals2D(qe.orig().getCoordinate()) && dest().getCoordinate().equals2D(qe.dest().getCoordinate()))
/* 408 */       return true; 
/* 409 */     return false;
/*     */   }
/*     */   
/*     */   public LineSegment toLineSegment() {
/* 420 */     return new LineSegment(this.vertex.getCoordinate(), dest().getCoordinate());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 430 */     Coordinate p0 = this.vertex.getCoordinate();
/* 431 */     Coordinate p1 = dest().getCoordinate();
/* 432 */     return WKTWriter.toLineString(p0, p1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\triangulate\quadedge\QuadEdge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */