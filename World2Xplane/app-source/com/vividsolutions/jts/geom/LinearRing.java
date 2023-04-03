/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ public class LinearRing extends LineString {
/*     */   public static final int MINIMUM_VALID_SIZE = 4;
/*     */   
/*     */   private static final long serialVersionUID = -4261142084085851829L;
/*     */   
/*     */   public LinearRing(Coordinate[] points, PrecisionModel precisionModel, int SRID) {
/*  79 */     this(points, new GeometryFactory(precisionModel, SRID));
/*  80 */     validateConstruction();
/*     */   }
/*     */   
/*     */   private LinearRing(Coordinate[] points, GeometryFactory factory) {
/*  90 */     this(factory.getCoordinateSequenceFactory().create(points), factory);
/*     */   }
/*     */   
/*     */   public LinearRing(CoordinateSequence points, GeometryFactory factory) {
/* 105 */     super(points, factory);
/* 106 */     validateConstruction();
/*     */   }
/*     */   
/*     */   private void validateConstruction() {
/* 110 */     if (!isEmpty() && !super.isClosed())
/* 111 */       throw new IllegalArgumentException("Points of LinearRing do not form a closed linestring"); 
/* 113 */     if (getCoordinateSequence().size() >= 1 && getCoordinateSequence().size() < 4)
/* 114 */       throw new IllegalArgumentException("Invalid number of points in LinearRing (found " + getCoordinateSequence().size() + " - must be 0 or >= 4)"); 
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/* 126 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 136 */     if (isEmpty())
/* 138 */       return true; 
/* 140 */     return super.isClosed();
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/* 145 */     return "LinearRing";
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 150 */     CoordinateSequence seq = (CoordinateSequence)this.points.clone();
/* 151 */     CoordinateSequences.reverse(seq);
/* 152 */     LinearRing rev = getFactory().createLinearRing(seq);
/* 153 */     return rev;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\LinearRing.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */