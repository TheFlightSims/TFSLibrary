/*    */ package com.vividsolutions.jts.operation;
/*    */ 
/*    */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*    */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*    */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.PrecisionModel;
/*    */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*    */ 
/*    */ public class GeometryGraphOperation {
/* 48 */   protected final LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*    */   
/*    */   protected PrecisionModel resultPrecisionModel;
/*    */   
/*    */   protected GeometryGraph[] arg;
/*    */   
/*    */   public GeometryGraphOperation(Geometry g0, Geometry g1) {
/* 58 */     this(g0, g1, BoundaryNodeRule.OGC_SFS_BOUNDARY_RULE);
/*    */   }
/*    */   
/*    */   public GeometryGraphOperation(Geometry g0, Geometry g1, BoundaryNodeRule boundaryNodeRule) {
/* 67 */     if (g0.getPrecisionModel().compareTo(g1.getPrecisionModel()) >= 0) {
/* 68 */       setComputationPrecision(g0.getPrecisionModel());
/*    */     } else {
/* 70 */       setComputationPrecision(g1.getPrecisionModel());
/*    */     } 
/* 72 */     this.arg = new GeometryGraph[2];
/* 73 */     this.arg[0] = new GeometryGraph(0, g0, boundaryNodeRule);
/* 74 */     this.arg[1] = new GeometryGraph(1, g1, boundaryNodeRule);
/*    */   }
/*    */   
/*    */   public GeometryGraphOperation(Geometry g0) {
/* 78 */     setComputationPrecision(g0.getPrecisionModel());
/* 80 */     this.arg = new GeometryGraph[1];
/* 81 */     this.arg[0] = new GeometryGraph(0, g0);
/*    */   }
/*    */   
/*    */   public Geometry getArgGeometry(int i) {
/* 84 */     return this.arg[i].getGeometry();
/*    */   }
/*    */   
/*    */   protected void setComputationPrecision(PrecisionModel pm) {
/* 88 */     this.resultPrecisionModel = pm;
/* 89 */     this.li.setPrecisionModel(this.resultPrecisionModel);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\GeometryGraphOperation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */