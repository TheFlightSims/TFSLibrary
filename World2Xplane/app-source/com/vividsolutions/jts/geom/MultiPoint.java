/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ public class MultiPoint extends GeometryCollection implements Puntal {
/*     */   private static final long serialVersionUID = -8048474874175355449L;
/*     */   
/*     */   public MultiPoint(Point[] points, PrecisionModel precisionModel, int SRID) {
/*  64 */     super((Geometry[])points, new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public MultiPoint(Point[] points, GeometryFactory factory) {
/*  73 */     super((Geometry[])points, factory);
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  77 */     return 0;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/*  81 */     return -1;
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/*  85 */     return "MultiPoint";
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/*  97 */     return getFactory().createGeometryCollection(null);
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 105 */     if (!isEquivalentClass(other))
/* 106 */       return false; 
/* 108 */     return super.equalsExact(other, tolerance);
/*     */   }
/*     */   
/*     */   protected Coordinate getCoordinate(int n) {
/* 119 */     return ((Point)this.geometries[n]).getCoordinate();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\MultiPoint.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */