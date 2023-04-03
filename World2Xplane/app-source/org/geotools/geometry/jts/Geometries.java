/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ 
/*     */ public enum Geometries {
/*  86 */   POINT((Class)Point.class, 2001),
/*  88 */   LINESTRING((Class)LineString.class, 2002),
/*  90 */   POLYGON((Class)Polygon.class, 2003),
/*  92 */   MULTIPOINT((Class)MultiPoint.class, 2004),
/*  94 */   MULTILINESTRING((Class)MultiLineString.class, 2005),
/*  96 */   MULTIPOLYGON((Class)MultiPolygon.class, 2006),
/*  98 */   GEOMETRY(Geometry.class, 2007),
/* 100 */   GEOMETRYCOLLECTION((Class)GeometryCollection.class, 2008);
/*     */   
/*     */   private final Class<? extends Geometry> binding;
/*     */   
/*     */   private final int sqlType;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final String simpleName;
/*     */   
/*     */   Geometries(Class<? extends Geometry> type, int sqlType) {
/* 108 */     this.binding = type;
/* 109 */     this.sqlType = sqlType;
/* 110 */     this.name = type.getSimpleName();
/* 111 */     this.simpleName = this.name.startsWith("Multi") ? this.name.substring(5) : this.name;
/*     */   }
/*     */   
/*     */   public Class<? extends Geometry> getBinding() {
/* 120 */     return this.binding;
/*     */   }
/*     */   
/*     */   public Integer getSQLType() {
/* 129 */     return Integer.valueOf(this.sqlType);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 139 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 148 */     return this.name;
/*     */   }
/*     */   
/*     */   public String getSimpleName() {
/* 159 */     return this.simpleName;
/*     */   }
/*     */   
/*     */   public static Geometries get(Geometry geom) {
/* 171 */     if (geom != null)
/* 172 */       return getForBinding((Class)geom.getClass()); 
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public static Geometries getForBinding(Class<? extends Geometry> geomClass) {
/* 186 */     for (Geometries gt : values()) {
/* 187 */       if (gt.binding == geomClass)
/* 188 */         return gt; 
/*     */     } 
/* 193 */     Geometries match = null;
/* 195 */     for (Geometries gt : values()) {
/* 196 */       if (gt != GEOMETRY && gt != GEOMETRYCOLLECTION)
/* 200 */         if (gt.binding.isAssignableFrom(geomClass))
/* 201 */           if (match == null) {
/* 202 */             match = gt;
/*     */           } else {
/* 205 */             return null;
/*     */           }   
/*     */     } 
/* 210 */     if (match == null) {
/* 212 */       if (GeometryCollection.class.isAssignableFrom(geomClass))
/* 213 */         return GEOMETRYCOLLECTION; 
/* 215 */       if (Geometry.class.isAssignableFrom(geomClass))
/* 216 */         return GEOMETRY; 
/*     */     } 
/* 220 */     return match;
/*     */   }
/*     */   
/*     */   public static Geometries getForName(String name) {
/* 231 */     for (Geometries gt : values()) {
/* 232 */       if (gt.getName().equalsIgnoreCase(name))
/* 233 */         return gt; 
/*     */     } 
/* 236 */     return null;
/*     */   }
/*     */   
/*     */   public static Geometries getForSQLType(int sqlType) {
/* 247 */     for (Geometries gt : values()) {
/* 248 */       if (gt.sqlType == sqlType)
/* 249 */         return gt; 
/*     */     } 
/* 253 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\Geometries.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */