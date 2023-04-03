/*     */ package com.vividsolutions.jts.operation.union;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.Puntal;
/*     */ import com.vividsolutions.jts.geom.util.GeometryExtracter;
/*     */ import com.vividsolutions.jts.operation.overlay.snap.SnapIfNeededOverlayOp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class UnaryUnionOp {
/*     */   public static Geometry union(Collection geoms) {
/*  90 */     UnaryUnionOp op = new UnaryUnionOp(geoms);
/*  91 */     return op.union();
/*     */   }
/*     */   
/*     */   public static Geometry union(Collection geoms, GeometryFactory geomFact) {
/* 108 */     UnaryUnionOp op = new UnaryUnionOp(geoms, geomFact);
/* 109 */     return op.union();
/*     */   }
/*     */   
/*     */   public static Geometry union(Geometry geom) {
/* 122 */     UnaryUnionOp op = new UnaryUnionOp(geom);
/* 123 */     return op.union();
/*     */   }
/*     */   
/* 126 */   private List polygons = new ArrayList();
/*     */   
/* 127 */   private List lines = new ArrayList();
/*     */   
/* 128 */   private List points = new ArrayList();
/*     */   
/* 130 */   private GeometryFactory geomFact = null;
/*     */   
/*     */   public UnaryUnionOp(Collection geoms, GeometryFactory geomFact) {
/* 141 */     this.geomFact = geomFact;
/* 142 */     extract(geoms);
/*     */   }
/*     */   
/*     */   public UnaryUnionOp(Collection geoms) {
/* 154 */     extract(geoms);
/*     */   }
/*     */   
/*     */   public UnaryUnionOp(Geometry geom) {
/* 164 */     extract(geom);
/*     */   }
/*     */   
/*     */   private void extract(Collection geoms) {
/* 169 */     for (Iterator<Geometry> i = geoms.iterator(); i.hasNext(); ) {
/* 170 */       Geometry geom = i.next();
/* 171 */       extract(geom);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void extract(Geometry geom) {
/* 177 */     if (this.geomFact == null)
/* 178 */       this.geomFact = geom.getFactory(); 
/* 185 */     GeometryExtracter.extract(geom, Polygon.class, this.polygons);
/* 186 */     GeometryExtracter.extract(geom, LineString.class, this.lines);
/* 187 */     GeometryExtracter.extract(geom, Point.class, this.points);
/*     */   }
/*     */   
/*     */   public Geometry union() {
/* 202 */     if (this.geomFact == null)
/* 203 */       return null; 
/* 212 */     Geometry unionPoints = null;
/* 213 */     if (this.points.size() > 0) {
/* 214 */       Geometry ptGeom = this.geomFact.buildGeometry(this.points);
/* 215 */       unionPoints = unionNoOpt(ptGeom);
/*     */     } 
/* 218 */     Geometry unionLines = null;
/* 219 */     if (this.lines.size() > 0) {
/* 220 */       Geometry lineGeom = this.geomFact.buildGeometry(this.lines);
/* 221 */       unionLines = unionNoOpt(lineGeom);
/*     */     } 
/* 224 */     Geometry unionPolygons = null;
/* 225 */     if (this.polygons.size() > 0)
/* 226 */       unionPolygons = CascadedPolygonUnion.union(this.polygons); 
/* 233 */     Geometry unionLA = unionWithNull(unionLines, unionPolygons);
/* 234 */     Geometry union = null;
/* 235 */     if (unionPoints == null) {
/* 236 */       union = unionLA;
/* 237 */     } else if (unionLA == null) {
/* 238 */       union = unionPoints;
/*     */     } else {
/* 240 */       union = PointGeometryUnion.union((Puntal)unionPoints, unionLA);
/*     */     } 
/* 242 */     if (union == null)
/* 243 */       return (Geometry)this.geomFact.createGeometryCollection(null); 
/* 245 */     return union;
/*     */   }
/*     */   
/*     */   private Geometry unionWithNull(Geometry g0, Geometry g1) {
/* 259 */     if (g0 == null && g1 == null)
/* 260 */       return null; 
/* 262 */     if (g1 == null)
/* 263 */       return g0; 
/* 264 */     if (g0 == null)
/* 265 */       return g1; 
/* 267 */     return g0.union(g1);
/*     */   }
/*     */   
/*     */   private Geometry unionNoOpt(Geometry g0) {
/* 284 */     Point point = this.geomFact.createPoint((Coordinate)null);
/* 285 */     return SnapIfNeededOverlayOp.overlayOp(g0, (Geometry)point, 2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operatio\\union\UnaryUnionOp.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */