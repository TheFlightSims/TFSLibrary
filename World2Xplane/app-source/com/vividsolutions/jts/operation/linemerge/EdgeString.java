/*     */ package com.vividsolutions.jts.operation.linemerge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.CoordinateList;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EdgeString {
/*     */   private GeometryFactory factory;
/*     */   
/*  54 */   private List directedEdges = new ArrayList();
/*     */   
/*  55 */   private Coordinate[] coordinates = null;
/*     */   
/*     */   public EdgeString(GeometryFactory factory) {
/*  61 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public void add(LineMergeDirectedEdge directedEdge) {
/*  68 */     this.directedEdges.add(directedEdge);
/*     */   }
/*     */   
/*     */   private Coordinate[] getCoordinates() {
/*  72 */     if (this.coordinates == null) {
/*  73 */       int forwardDirectedEdges = 0;
/*  74 */       int reverseDirectedEdges = 0;
/*  75 */       CoordinateList coordinateList = new CoordinateList();
/*  76 */       for (Iterator<LineMergeDirectedEdge> i = this.directedEdges.iterator(); i.hasNext(); ) {
/*  77 */         LineMergeDirectedEdge directedEdge = i.next();
/*  78 */         if (directedEdge.getEdgeDirection()) {
/*  79 */           forwardDirectedEdges++;
/*     */         } else {
/*  82 */           reverseDirectedEdges++;
/*     */         } 
/*  84 */         coordinateList.add(((LineMergeEdge)directedEdge.getEdge()).getLine().getCoordinates(), false, directedEdge.getEdgeDirection());
/*     */       } 
/*  88 */       this.coordinates = coordinateList.toCoordinateArray();
/*  89 */       if (reverseDirectedEdges > forwardDirectedEdges)
/*  90 */         CoordinateArrays.reverse(this.coordinates); 
/*     */     } 
/*  94 */     return this.coordinates;
/*     */   }
/*     */   
/*     */   public LineString toLineString() {
/* 101 */     return this.factory.createLineString(getCoordinates());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\EdgeString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */