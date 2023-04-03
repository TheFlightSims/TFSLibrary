/*     */ package com.vividsolutions.jts.operation.overlay;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.PointLocator;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import com.vividsolutions.jts.geomgraph.Node;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class PointBuilder {
/*     */   private OverlayOp op;
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*  47 */   private List resultPointList = new ArrayList();
/*     */   
/*     */   public PointBuilder(OverlayOp op, GeometryFactory geometryFactory, PointLocator ptLocator) {
/*  50 */     this.op = op;
/*  51 */     this.geometryFactory = geometryFactory;
/*     */   }
/*     */   
/*     */   public List build(int opCode) {
/*  63 */     extractNonCoveredResultNodes(opCode);
/*  69 */     return this.resultPointList;
/*     */   }
/*     */   
/*     */   private void extractNonCoveredResultNodes(int opCode) {
/*  85 */     for (Iterator<Node> nodeit = this.op.getGraph().getNodes().iterator(); nodeit.hasNext(); ) {
/*  86 */       Node n = nodeit.next();
/*  89 */       if (n.isInResult())
/*     */         continue; 
/*  92 */       if (n.isIncidentEdgeInResult())
/*     */         continue; 
/*  94 */       if (n.getEdges().getDegree() == 0 || opCode == 1) {
/* 100 */         Label label = n.getLabel();
/* 101 */         if (OverlayOp.isResultOfOp(label, opCode))
/* 102 */           filterCoveredNodeToPoint(n); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void filterCoveredNodeToPoint(Node n) {
/* 120 */     Coordinate coord = n.getCoordinate();
/* 121 */     if (!this.op.isCoveredByLA(coord)) {
/* 122 */       Point pt = this.geometryFactory.createPoint(coord);
/* 123 */       this.resultPointList.add(pt);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\overlay\PointBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */