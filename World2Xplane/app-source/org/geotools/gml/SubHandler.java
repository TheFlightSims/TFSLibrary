/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ 
/*     */ public abstract class SubHandler {
/*     */   public static final int GEOMETRY_START = 1;
/*     */   
/*     */   public static final int GEOMETRY_END = 2;
/*     */   
/*     */   public static final int GEOMETRY_SUB = 3;
/*     */   
/*  39 */   private String srs = null;
/*     */   
/*     */   protected String getSRS() {
/*  45 */     return this.srs;
/*     */   }
/*     */   
/*     */   protected int getSRID() {
/*  55 */     if (this.srs == null)
/*  55 */       return 0; 
/*  56 */     String[] split = this.srs.split("\\:");
/*     */     try {
/*  58 */       return Integer.parseInt(split[split.length - 1]);
/*  60 */     } catch (NumberFormatException ignore) {
/*  61 */       return 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSRS(String SRS) {
/*  66 */     this.srs = SRS;
/*     */   }
/*     */   
/*     */   public abstract void addCoordinate(Coordinate paramCoordinate);
/*     */   
/*     */   public void subGeometry(String message, int type) {}
/*     */   
/*     */   public abstract boolean isComplete(String paramString);
/*     */   
/*     */   public abstract Geometry create(GeometryFactory paramGeometryFactory);
/*     */   
/*     */   public String toString() {
/* 112 */     String name = getClass().getName();
/* 113 */     int index = name.lastIndexOf('.');
/* 115 */     return name.substring(index + 1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */