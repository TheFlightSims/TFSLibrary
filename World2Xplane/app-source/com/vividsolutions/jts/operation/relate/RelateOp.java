/*     */ package com.vividsolutions.jts.operation.relate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import com.vividsolutions.jts.operation.GeometryGraphOperation;
/*     */ 
/*     */ public class RelateOp extends GeometryGraphOperation {
/*     */   private RelateComputer relate;
/*     */   
/*     */   public static IntersectionMatrix relate(Geometry a, Geometry b) {
/*  82 */     RelateOp relOp = new RelateOp(a, b);
/*  83 */     IntersectionMatrix im = relOp.getIntersectionMatrix();
/*  84 */     return im;
/*     */   }
/*     */   
/*     */   public static IntersectionMatrix relate(Geometry a, Geometry b, BoundaryNodeRule boundaryNodeRule) {
/*  98 */     RelateOp relOp = new RelateOp(a, b, boundaryNodeRule);
/*  99 */     IntersectionMatrix im = relOp.getIntersectionMatrix();
/* 100 */     return im;
/*     */   }
/*     */   
/*     */   public RelateOp(Geometry g0, Geometry g1) {
/* 113 */     super(g0, g1);
/* 114 */     this.relate = new RelateComputer(this.arg);
/*     */   }
/*     */   
/*     */   public RelateOp(Geometry g0, Geometry g1, BoundaryNodeRule boundaryNodeRule) {
/* 126 */     super(g0, g1, boundaryNodeRule);
/* 127 */     this.relate = new RelateComputer(this.arg);
/*     */   }
/*     */   
/*     */   public IntersectionMatrix getIntersectionMatrix() {
/* 138 */     return this.relate.computeIM();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\RelateOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */