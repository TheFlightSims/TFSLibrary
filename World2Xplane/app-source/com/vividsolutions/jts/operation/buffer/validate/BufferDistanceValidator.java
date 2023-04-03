/*     */ package com.vividsolutions.jts.operation.buffer.validate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.distance.DiscreteHausdorffDistance;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.geom.util.PolygonExtracter;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import com.vividsolutions.jts.operation.distance.DistanceOp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class BufferDistanceValidator {
/*     */   private static boolean VERBOSE = false;
/*     */   
/*     */   private static final double MAX_DISTANCE_DIFF_FRAC = 0.012D;
/*     */   
/*     */   private Geometry input;
/*     */   
/*     */   private double bufDistance;
/*     */   
/*     */   private Geometry result;
/*     */   
/*     */   private double minValidDistance;
/*     */   
/*     */   private double maxValidDistance;
/*     */   
/*     */   private double minDistanceFound;
/*     */   
/*     */   private double maxDistanceFound;
/*     */   
/*     */   private boolean isValid = true;
/*     */   
/*  77 */   private String errMsg = null;
/*     */   
/*  78 */   private Coordinate errorLocation = null;
/*     */   
/*  79 */   private Geometry errorIndicator = null;
/*     */   
/*     */   public BufferDistanceValidator(Geometry input, double bufDistance, Geometry result) {
/*  83 */     this.input = input;
/*  84 */     this.bufDistance = bufDistance;
/*  85 */     this.result = result;
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/*  90 */     double posDistance = Math.abs(this.bufDistance);
/*  91 */     double distDelta = 0.012D * posDistance;
/*  92 */     this.minValidDistance = posDistance - distDelta;
/*  93 */     this.maxValidDistance = posDistance + distDelta;
/*  96 */     if (this.input.isEmpty() || this.result.isEmpty())
/*  97 */       return true; 
/*  99 */     if (this.bufDistance > 0.0D) {
/* 100 */       checkPositiveValid();
/*     */     } else {
/* 103 */       checkNegativeValid();
/*     */     } 
/* 105 */     if (VERBOSE)
/* 106 */       System.out.println("Min Dist= " + this.minDistanceFound + "  err= " + (1.0D - this.minDistanceFound / this.bufDistance) + "  Max Dist= " + this.maxDistanceFound + "  err= " + (this.maxDistanceFound / this.bufDistance - 1.0D)); 
/* 112 */     return this.isValid;
/*     */   }
/*     */   
/*     */   public String getErrorMessage() {
/* 117 */     return this.errMsg;
/*     */   }
/*     */   
/*     */   public Coordinate getErrorLocation() {
/* 122 */     return this.errorLocation;
/*     */   }
/*     */   
/*     */   public Geometry getErrorIndicator() {
/* 136 */     return this.errorIndicator;
/*     */   }
/*     */   
/*     */   private void checkPositiveValid() {
/* 141 */     Geometry bufCurve = this.result.getBoundary();
/* 142 */     checkMinimumDistance(this.input, bufCurve, this.minValidDistance);
/* 143 */     if (!this.isValid)
/*     */       return; 
/* 145 */     checkMaximumDistance(this.input, bufCurve, this.maxValidDistance);
/*     */   }
/*     */   
/*     */   private void checkNegativeValid() {
/* 153 */     if (!(this.input instanceof Polygon) && !(this.input instanceof com.vividsolutions.jts.geom.MultiPolygon) && !(this.input instanceof com.vividsolutions.jts.geom.GeometryCollection))
/*     */       return; 
/* 159 */     Geometry inputCurve = getPolygonLines(this.input);
/* 160 */     checkMinimumDistance(inputCurve, this.result, this.minValidDistance);
/* 161 */     if (!this.isValid)
/*     */       return; 
/* 163 */     checkMaximumDistance(inputCurve, this.result, this.maxValidDistance);
/*     */   }
/*     */   
/*     */   private Geometry getPolygonLines(Geometry g) {
/* 168 */     List lines = new ArrayList();
/* 169 */     LinearComponentExtracter lineExtracter = new LinearComponentExtracter(lines);
/* 170 */     List polys = PolygonExtracter.getPolygons(g);
/* 171 */     for (Iterator<Polygon> i = polys.iterator(); i.hasNext(); ) {
/* 172 */       Polygon poly = i.next();
/* 173 */       poly.apply((GeometryComponentFilter)lineExtracter);
/*     */     } 
/* 175 */     return g.getFactory().buildGeometry(lines);
/*     */   }
/*     */   
/*     */   private void checkMinimumDistance(Geometry g1, Geometry g2, double minDist) {
/* 187 */     DistanceOp distOp = new DistanceOp(g1, g2, minDist);
/* 188 */     this.minDistanceFound = distOp.distance();
/* 191 */     if (this.minDistanceFound < minDist) {
/* 192 */       this.isValid = false;
/* 193 */       Coordinate[] pts = distOp.nearestPoints();
/* 194 */       this.errorLocation = distOp.nearestPoints()[1];
/* 195 */       this.errorIndicator = (Geometry)g1.getFactory().createLineString(pts);
/* 196 */       this.errMsg = "Distance between buffer curve and input is too small (" + this.minDistanceFound + " at " + WKTWriter.toLineString(pts[0], pts[1]) + " )";
/*     */     } 
/*     */   }
/*     */   
/*     */   private void checkMaximumDistance(Geometry input, Geometry bufCurve, double maxDist) {
/* 218 */     DiscreteHausdorffDistance haus = new DiscreteHausdorffDistance(bufCurve, input);
/* 219 */     haus.setDensifyFraction(0.25D);
/* 220 */     this.maxDistanceFound = haus.orientedDistance();
/* 222 */     if (this.maxDistanceFound > maxDist) {
/* 223 */       this.isValid = false;
/* 224 */       Coordinate[] pts = haus.getCoordinates();
/* 225 */       this.errorLocation = pts[1];
/* 226 */       this.errorIndicator = (Geometry)input.getFactory().createLineString(pts);
/* 227 */       this.errMsg = "Distance between buffer curve and input is too large (" + this.maxDistanceFound + " at " + WKTWriter.toLineString(pts[0], pts[1]) + ")";
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\validate\BufferDistanceValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */