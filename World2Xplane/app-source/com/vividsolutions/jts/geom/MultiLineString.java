/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ import com.vividsolutions.jts.operation.BoundaryOp;
/*     */ 
/*     */ public class MultiLineString extends GeometryCollection implements Lineal {
/*     */   private static final long serialVersionUID = 8166665132445433741L;
/*     */   
/*     */   public MultiLineString(LineString[] lineStrings, PrecisionModel precisionModel, int SRID) {
/*  63 */     super((Geometry[])lineStrings, new GeometryFactory(precisionModel, SRID));
/*     */   }
/*     */   
/*     */   public MultiLineString(LineString[] lineStrings, GeometryFactory factory) {
/*  76 */     super((Geometry[])lineStrings, factory);
/*     */   }
/*     */   
/*     */   public int getDimension() {
/*  80 */     return 1;
/*     */   }
/*     */   
/*     */   public int getBoundaryDimension() {
/*  84 */     if (isClosed())
/*  85 */       return -1; 
/*  87 */     return 0;
/*     */   }
/*     */   
/*     */   public String getGeometryType() {
/*  91 */     return "MultiLineString";
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/*  95 */     if (isEmpty())
/*  96 */       return false; 
/*  98 */     for (int i = 0; i < this.geometries.length; i++) {
/*  99 */       if (!((LineString)this.geometries[i]).isClosed())
/* 100 */         return false; 
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */   
/*     */   public Geometry getBoundary() {
/* 115 */     return (new BoundaryOp(this)).getBoundary();
/*     */   }
/*     */   
/*     */   public Geometry reverse() {
/* 129 */     int nLines = this.geometries.length;
/* 130 */     LineString[] revLines = new LineString[nLines];
/* 131 */     for (int i = 0; i < this.geometries.length; i++)
/* 132 */       revLines[nLines - 1 - i] = (LineString)this.geometries[i].reverse(); 
/* 134 */     return getFactory().createMultiLineString(revLines);
/*     */   }
/*     */   
/*     */   public boolean equalsExact(Geometry other, double tolerance) {
/* 138 */     if (!isEquivalentClass(other))
/* 139 */       return false; 
/* 141 */     return super.equalsExact(other, tolerance);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\MultiLineString.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */