/*     */ package com.vividsolutions.jts.operation;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ public class BoundaryOp {
/*     */   private Geometry geom;
/*     */   
/*     */   private GeometryFactory geomFact;
/*     */   
/*     */   private BoundaryNodeRule bnRule;
/*     */   
/*     */   private Map endpointMap;
/*     */   
/*     */   public BoundaryOp(Geometry geom) {
/*  60 */     this(geom, BoundaryNodeRule.MOD2_BOUNDARY_RULE);
/*     */   }
/*     */   
/*     */   public BoundaryOp(Geometry geom, BoundaryNodeRule bnRule) {
/*  65 */     this.geom = geom;
/*  66 */     this.geomFact = geom.getFactory();
/*  67 */     this.bnRule = bnRule;
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/*  72 */     if (this.geom instanceof LineString)
/*  72 */       return boundaryLineString((LineString)this.geom); 
/*  73 */     if (this.geom instanceof MultiLineString)
/*  73 */       return boundaryMultiLineString((MultiLineString)this.geom); 
/*  74 */     return this.geom.getBoundary();
/*     */   }
/*     */   
/*     */   private MultiPoint getEmptyMultiPoint() {
/*  79 */     return this.geomFact.createMultiPoint((CoordinateSequence)null);
/*     */   }
/*     */   
/*     */   private Geometry boundaryMultiLineString(MultiLineString mLine) {
/*  84 */     if (this.geom.isEmpty())
/*  85 */       return (Geometry)getEmptyMultiPoint(); 
/*  88 */     Coordinate[] bdyPts = computeBoundaryCoordinates(mLine);
/*  91 */     if (bdyPts.length == 1)
/*  92 */       return (Geometry)this.geomFact.createPoint(bdyPts[0]); 
/*  95 */     return (Geometry)this.geomFact.createMultiPoint(bdyPts);
/*     */   }
/*     */   
/*     */   private Coordinate[] computeBoundaryCoordinates(MultiLineString mLine) {
/* 112 */     List bdyPts = new ArrayList();
/* 113 */     this.endpointMap = new TreeMap<>();
/* 114 */     for (int i = 0; i < mLine.getNumGeometries(); i++) {
/* 115 */       LineString line = (LineString)mLine.getGeometryN(i);
/* 116 */       if (line.getNumPoints() != 0) {
/* 118 */         addEndpoint(line.getCoordinateN(0));
/* 119 */         addEndpoint(line.getCoordinateN(line.getNumPoints() - 1));
/*     */       } 
/*     */     } 
/* 122 */     for (Iterator<Map.Entry> it = this.endpointMap.entrySet().iterator(); it.hasNext(); ) {
/* 123 */       Map.Entry entry = it.next();
/* 124 */       Counter counter = (Counter)entry.getValue();
/* 125 */       int valence = counter.count;
/* 126 */       if (this.bnRule.isInBoundary(valence))
/* 127 */         bdyPts.add(entry.getKey()); 
/*     */     } 
/* 131 */     return CoordinateArrays.toCoordinateArray(bdyPts);
/*     */   }
/*     */   
/*     */   private void addEndpoint(Coordinate pt) {
/* 136 */     Counter counter = (Counter)this.endpointMap.get(pt);
/* 137 */     if (counter == null) {
/* 138 */       counter = new Counter();
/* 139 */       this.endpointMap.put(pt, counter);
/*     */     } 
/* 141 */     counter.count++;
/*     */   }
/*     */   
/*     */   private Geometry boundaryLineString(LineString line) {
/* 146 */     if (this.geom.isEmpty())
/* 147 */       return (Geometry)getEmptyMultiPoint(); 
/* 150 */     if (line.isClosed()) {
/* 152 */       boolean closedEndpointOnBoundary = this.bnRule.isInBoundary(2);
/* 153 */       if (closedEndpointOnBoundary)
/* 154 */         return (Geometry)line.getStartPoint(); 
/* 157 */       return (Geometry)this.geomFact.createMultiPoint((Coordinate[])null);
/*     */     } 
/* 160 */     return (Geometry)this.geomFact.createMultiPoint(new Point[] { line.getStartPoint(), line.getEndPoint() });
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\BoundaryOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */