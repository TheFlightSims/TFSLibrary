/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class MultiPolygon extends GeometryCollection implements Polygonal {
/*     */   private static final long serialVersionUID = -551033529766975875L;
/*     */   
/*     */   public MultiPolygon(Polygon[] polygons, PrecisionModel precisionModel, int SRID) {
/*  72 */     this(polygons, new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public MultiPolygon(Polygon[] polygons, GeometryFactory factory) {
/*  87 */     super((Geometry[])polygons, factory);
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  91 */     return 2;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/*  95 */     return 1;
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/*  99 */     return "MultiPolygon";
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 115 */     if (isEmpty())
/* 116 */       return getFactory().createMultiLineString(null); 
/* 118 */     ArrayList<Geometry> allRings = new ArrayList();
/* 119 */     for (int i = 0; i < this.geometries.length; i++) {
/* 120 */       Polygon polygon = (Polygon)this.geometries[i];
/* 121 */       Geometry rings = polygon.getBoundary();
/* 122 */       for (int j = 0; j < rings.getNumGeometries(); j++)
/* 123 */         allRings.add(rings.getGeometryN(j)); 
/*     */     } 
/* 126 */     LineString[] allRingsArray = new LineString[allRings.size()];
/* 127 */     return getFactory().createMultiLineString(allRings.<LineString>toArray(allRingsArray));
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 131 */     if (!isEquivalentClass(other))
/* 132 */       return false; 
/* 134 */     return super.equalsExact(other, tolerance);
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 146 */     int n = this.geometries.length;
/* 147 */     Polygon[] revGeoms = new Polygon[n];
/* 148 */     for (int i = 0; i < this.geometries.length; i++)
/* 149 */       revGeoms[i] = (Polygon)this.geometries[i].reverse(); 
/* 151 */     return getFactory().createMultiPolygon(revGeoms);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\MultiPolygon.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */