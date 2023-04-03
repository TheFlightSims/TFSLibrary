/*    */ package org.poly2tri.triangulation.delaunay.sweep;
/*    */ 
/*    */ import org.poly2tri.triangulation.TriangulationConstraint;
/*    */ import org.poly2tri.triangulation.TriangulationPoint;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ public class DTSweepConstraint extends TriangulationConstraint {
/* 36 */   private static final Logger logger = LoggerFactory.getLogger(DTSweepConstraint.class);
/*    */   
/*    */   public TriangulationPoint p;
/*    */   
/*    */   public TriangulationPoint q;
/*    */   
/*    */   public DTSweepConstraint(TriangulationPoint p1, TriangulationPoint p2) {
/* 51 */     this.p = p1;
/* 52 */     this.q = p2;
/* 53 */     if (p1.getY() > p2.getY()) {
/* 55 */       this.q = p1;
/* 56 */       this.p = p2;
/* 58 */     } else if (p1.getY() == p2.getY()) {
/* 60 */       if (p1.getX() > p2.getX()) {
/* 62 */         this.q = p1;
/* 63 */         this.p = p2;
/* 65 */       } else if (p1.getX() == p2.getX()) {
/*    */       
/*    */       } 
/*    */     } 
/* 72 */     this.q.addEdge(this);
/*    */   }
/*    */   
/*    */   public TriangulationPoint getP() {
/* 90 */     return this.p;
/*    */   }
/*    */   
/*    */   public TriangulationPoint getQ() {
/* 95 */     return this.q;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\triangulation\delaunay\sweep\DTSweepConstraint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */