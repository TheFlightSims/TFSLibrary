/*     */ package com.vividsolutions.jts.simplify;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.util.GeometryTransformer;
/*     */ 
/*     */ public class VWSimplifier {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double distanceTolerance;
/*     */   
/*     */   public static Geometry simplify(Geometry geom, double distanceTolerance) {
/*  77 */     VWSimplifier simp = new VWSimplifier(geom);
/*  78 */     simp.setDistanceTolerance(distanceTolerance);
/*  79 */     return simp.getResultGeometry();
/*     */   }
/*     */   
/*     */   private boolean isEnsureValidTopology = true;
/*     */   
/*     */   public VWSimplifier(Geometry inputGeom) {
/*  93 */     this.inputGeom = inputGeom;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/* 106 */     if (distanceTolerance < 0.0D)
/* 107 */       throw new IllegalArgumentException("Tolerance must be non-negative"); 
/* 108 */     this.distanceTolerance = distanceTolerance;
/*     */   }
/*     */   
/*     */   public void setEnsureValid(boolean isEnsureValidTopology) {
/* 127 */     this.isEnsureValidTopology = isEnsureValidTopology;
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry() {
/* 138 */     if (this.inputGeom.isEmpty())
/* 139 */       return (Geometry)this.inputGeom.clone(); 
/* 141 */     return (new VWTransformer(this.isEnsureValidTopology)).transform(this.inputGeom);
/*     */   }
/*     */   
/*     */   class VWTransformer extends GeometryTransformer {
/*     */     private boolean isEnsureValidTopology = true;
/*     */     
/*     */     public VWTransformer(boolean isEnsureValidTopology) {
/* 150 */       this.isEnsureValidTopology = isEnsureValidTopology;
/*     */     }
/*     */     
/*     */     protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 155 */       Coordinate[] inputPts = coords.toCoordinateArray();
/* 156 */       Coordinate[] newPts = null;
/* 157 */       if (inputPts.length == 0) {
/* 158 */         newPts = new Coordinate[0];
/*     */       } else {
/* 161 */         newPts = VWLineSimplifier.simplify(inputPts, VWSimplifier.this.distanceTolerance);
/*     */       } 
/* 163 */       return this.factory.getCoordinateSequenceFactory().create(newPts);
/*     */     }
/*     */     
/*     */     protected Geometry transformPolygon(Polygon geom, Geometry parent) {
/* 172 */       if (geom.isEmpty())
/* 173 */         return null; 
/* 174 */       Geometry rawGeom = super.transformPolygon(geom, parent);
/* 176 */       if (parent instanceof MultiPolygon)
/* 177 */         return rawGeom; 
/* 179 */       return createValidArea(rawGeom);
/*     */     }
/*     */     
/*     */     protected Geometry transformLinearRing(LinearRing geom, Geometry parent) {
/* 190 */       boolean removeDegenerateRings = parent instanceof Polygon;
/* 191 */       Geometry simpResult = super.transformLinearRing(geom, parent);
/* 192 */       if (removeDegenerateRings && !(simpResult instanceof LinearRing))
/* 193 */         return null; 
/* 195 */       return simpResult;
/*     */     }
/*     */     
/*     */     protected Geometry transformMultiPolygon(MultiPolygon geom, Geometry parent) {
/* 203 */       Geometry rawGeom = super.transformMultiPolygon(geom, parent);
/* 204 */       return createValidArea(rawGeom);
/*     */     }
/*     */     
/*     */     private Geometry createValidArea(Geometry rawAreaGeom) {
/* 221 */       if (this.isEnsureValidTopology)
/* 222 */         return rawAreaGeom.buffer(0.0D); 
/* 223 */       return rawAreaGeom;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\VWSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */