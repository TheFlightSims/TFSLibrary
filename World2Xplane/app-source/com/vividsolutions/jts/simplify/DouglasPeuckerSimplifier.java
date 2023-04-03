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
/*     */ public class DouglasPeuckerSimplifier {
/*     */   private Geometry inputGeom;
/*     */   
/*     */   private double distanceTolerance;
/*     */   
/*     */   public static Geometry simplify(Geometry geom, double distanceTolerance) {
/*  69 */     DouglasPeuckerSimplifier tss = new DouglasPeuckerSimplifier(geom);
/*  70 */     tss.setDistanceTolerance(distanceTolerance);
/*  71 */     return tss.getResultGeometry();
/*     */   }
/*     */   
/*     */   private boolean isEnsureValidTopology = true;
/*     */   
/*     */   public DouglasPeuckerSimplifier(Geometry inputGeom) {
/*  85 */     this.inputGeom = inputGeom;
/*     */   }
/*     */   
/*     */   public void setDistanceTolerance(double distanceTolerance) {
/*  97 */     if (distanceTolerance < 0.0D)
/*  98 */       throw new IllegalArgumentException("Tolerance must be non-negative"); 
/*  99 */     this.distanceTolerance = distanceTolerance;
/*     */   }
/*     */   
/*     */   public void setEnsureValid(boolean isEnsureValidTopology) {
/* 118 */     this.isEnsureValidTopology = isEnsureValidTopology;
/*     */   }
/*     */   
/*     */   public Geometry getResultGeometry() {
/* 129 */     if (this.inputGeom.isEmpty())
/* 129 */       return (Geometry)this.inputGeom.clone(); 
/* 131 */     return (new DPTransformer(this.isEnsureValidTopology)).transform(this.inputGeom);
/*     */   }
/*     */   
/*     */   class DPTransformer extends GeometryTransformer {
/*     */     private boolean isEnsureValidTopology = true;
/*     */     
/*     */     public DPTransformer(boolean isEnsureValidTopology) {
/* 141 */       this.isEnsureValidTopology = isEnsureValidTopology;
/*     */     }
/*     */     
/*     */     protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 146 */       Coordinate[] inputPts = coords.toCoordinateArray();
/* 147 */       Coordinate[] newPts = null;
/* 148 */       if (inputPts.length == 0) {
/* 149 */         newPts = new Coordinate[0];
/*     */       } else {
/* 152 */         newPts = DouglasPeuckerLineSimplifier.simplify(inputPts, DouglasPeuckerSimplifier.this.distanceTolerance);
/*     */       } 
/* 154 */       return this.factory.getCoordinateSequenceFactory().create(newPts);
/*     */     }
/*     */     
/*     */     protected Geometry transformPolygon(Polygon geom, Geometry parent) {
/* 162 */       if (geom.isEmpty())
/* 163 */         return null; 
/* 164 */       Geometry rawGeom = super.transformPolygon(geom, parent);
/* 166 */       if (parent instanceof MultiPolygon)
/* 167 */         return rawGeom; 
/* 169 */       return createValidArea(rawGeom);
/*     */     }
/*     */     
/*     */     protected Geometry transformLinearRing(LinearRing geom, Geometry parent) {
/* 180 */       boolean removeDegenerateRings = parent instanceof Polygon;
/* 181 */       Geometry simpResult = super.transformLinearRing(geom, parent);
/* 182 */       if (removeDegenerateRings && !(simpResult instanceof LinearRing))
/* 183 */         return null; 
/* 184 */       return simpResult;
/*     */     }
/*     */     
/*     */     protected Geometry transformMultiPolygon(MultiPolygon geom, Geometry parent) {
/* 191 */       Geometry rawGeom = super.transformMultiPolygon(geom, parent);
/* 192 */       return createValidArea(rawGeom);
/*     */     }
/*     */     
/*     */     private Geometry createValidArea(Geometry rawAreaGeom) {
/* 210 */       if (this.isEnsureValidTopology)
/* 211 */         return rawAreaGeom.buffer(0.0D); 
/* 212 */       return rawAreaGeom;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\simplify\DouglasPeuckerSimplifier.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */