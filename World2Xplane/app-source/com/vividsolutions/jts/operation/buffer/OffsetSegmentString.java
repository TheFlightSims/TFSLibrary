/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ class OffsetSegmentString {
/*  48 */   private static final Coordinate[] COORDINATE_ARRAY_TYPE = new Coordinate[0];
/*     */   
/*     */   private ArrayList ptList;
/*     */   
/*  51 */   private PrecisionModel precisionModel = null;
/*     */   
/*  58 */   private double minimimVertexDistance = 0.0D;
/*     */   
/*     */   public OffsetSegmentString() {
/*  62 */     this.ptList = new ArrayList();
/*     */   }
/*     */   
/*     */   public void setPrecisionModel(PrecisionModel precisionModel) {
/*  67 */     this.precisionModel = precisionModel;
/*     */   }
/*     */   
/*     */   public void setMinimumVertexDistance(double minimimVertexDistance) {
/*  72 */     this.minimimVertexDistance = minimimVertexDistance;
/*     */   }
/*     */   
/*     */   public void addPt(Coordinate pt) {
/*  77 */     Coordinate bufPt = new Coordinate(pt);
/*  78 */     this.precisionModel.makePrecise(bufPt);
/*  80 */     if (isRedundant(bufPt))
/*     */       return; 
/*  82 */     this.ptList.add(bufPt);
/*     */   }
/*     */   
/*     */   public void addPts(Coordinate[] pt, boolean isForward) {
/*  88 */     if (isForward) {
/*  89 */       for (int i = 0; i < pt.length; i++)
/*  90 */         addPt(pt[i]); 
/*     */     } else {
/*  94 */       for (int i = pt.length - 1; i >= 0; i--)
/*  95 */         addPt(pt[i]); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isRedundant(Coordinate pt) {
/* 110 */     if (this.ptList.size() < 1)
/* 111 */       return false; 
/* 112 */     Coordinate lastPt = this.ptList.get(this.ptList.size() - 1);
/* 113 */     double ptDist = pt.distance(lastPt);
/* 114 */     if (ptDist < this.minimimVertexDistance)
/* 115 */       return true; 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public void closeRing() {
/* 121 */     if (this.ptList.size() < 1)
/*     */       return; 
/* 122 */     Coordinate startPt = new Coordinate(this.ptList.get(0));
/* 123 */     Coordinate lastPt = this.ptList.get(this.ptList.size() - 1);
/* 124 */     Coordinate last2Pt = null;
/* 125 */     if (this.ptList.size() >= 2)
/* 126 */       last2Pt = this.ptList.get(this.ptList.size() - 2); 
/* 127 */     if (startPt.equals(lastPt))
/*     */       return; 
/* 128 */     this.ptList.add(startPt);
/*     */   }
/*     */   
/*     */   public void reverse() {}
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 146 */     Coordinate[] coord = (Coordinate[])this.ptList.toArray((Object[])COORDINATE_ARRAY_TYPE);
/* 147 */     return coord;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 152 */     GeometryFactory fact = new GeometryFactory();
/* 153 */     LineString line = fact.createLineString(getCoordinates());
/* 154 */     return line.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\OffsetSegmentString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */