/*     */ package com.vividsolutions.jts.geom.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GeometryTransformer {
/*     */   private Geometry inputGeom;
/*     */   
/*  86 */   protected GeometryFactory factory = null;
/*     */   
/*     */   private boolean pruneEmptyGeometry = true;
/*     */   
/*     */   private boolean preserveGeometryCollectionType = true;
/*     */   
/*     */   private boolean preserveCollections = false;
/*     */   
/*     */   private boolean preserveType = false;
/*     */   
/*     */   public Geometry getInputGeometry() {
/* 119 */     return this.inputGeom;
/*     */   }
/*     */   
/*     */   public final Geometry transform(Geometry inputGeom) {
/* 123 */     this.inputGeom = inputGeom;
/* 124 */     this.factory = inputGeom.getFactory();
/* 126 */     if (inputGeom instanceof Point)
/* 127 */       return transformPoint((Point)inputGeom, null); 
/* 128 */     if (inputGeom instanceof MultiPoint)
/* 129 */       return transformMultiPoint((MultiPoint)inputGeom, null); 
/* 130 */     if (inputGeom instanceof LinearRing)
/* 131 */       return transformLinearRing((LinearRing)inputGeom, null); 
/* 132 */     if (inputGeom instanceof LineString)
/* 133 */       return transformLineString((LineString)inputGeom, null); 
/* 134 */     if (inputGeom instanceof MultiLineString)
/* 135 */       return transformMultiLineString((MultiLineString)inputGeom, null); 
/* 136 */     if (inputGeom instanceof Polygon)
/* 137 */       return transformPolygon((Polygon)inputGeom, null); 
/* 138 */     if (inputGeom instanceof MultiPolygon)
/* 139 */       return transformMultiPolygon((MultiPolygon)inputGeom, null); 
/* 140 */     if (inputGeom instanceof GeometryCollection)
/* 141 */       return transformGeometryCollection((GeometryCollection)inputGeom, null); 
/* 143 */     throw new IllegalArgumentException("Unknown Geometry subtype: " + inputGeom.getClass().getName());
/*     */   }
/*     */   
/*     */   protected final CoordinateSequence createCoordinateSequence(Coordinate[] coords) {
/* 155 */     return this.factory.getCoordinateSequenceFactory().create(coords);
/*     */   }
/*     */   
/*     */   protected final CoordinateSequence copy(CoordinateSequence seq) {
/* 165 */     return (CoordinateSequence)seq.clone();
/*     */   }
/*     */   
/*     */   protected CoordinateSequence transformCoordinates(CoordinateSequence coords, Geometry parent) {
/* 182 */     return copy(coords);
/*     */   }
/*     */   
/*     */   protected Geometry transformPoint(Point geom, Geometry parent) {
/* 186 */     return (Geometry)this.factory.createPoint(transformCoordinates(geom.getCoordinateSequence(), (Geometry)geom));
/*     */   }
/*     */   
/*     */   protected Geometry transformMultiPoint(MultiPoint geom, Geometry parent) {
/* 191 */     List<Geometry> transGeomList = new ArrayList();
/* 192 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 193 */       Geometry transformGeom = transformPoint((Point)geom.getGeometryN(i), (Geometry)geom);
/* 194 */       if (transformGeom != null && 
/* 195 */         !transformGeom.isEmpty())
/* 196 */         transGeomList.add(transformGeom); 
/*     */     } 
/* 198 */     return this.factory.buildGeometry(transGeomList);
/*     */   }
/*     */   
/*     */   protected Geometry transformLinearRing(LinearRing geom, Geometry parent) {
/* 215 */     CoordinateSequence seq = transformCoordinates(geom.getCoordinateSequence(), (Geometry)geom);
/* 216 */     if (seq == null)
/* 217 */       return (Geometry)this.factory.createLinearRing((CoordinateSequence)null); 
/* 218 */     int seqSize = seq.size();
/* 220 */     if (seqSize > 0 && seqSize < 4 && !this.preserveType)
/* 221 */       return (Geometry)this.factory.createLineString(seq); 
/* 222 */     return (Geometry)this.factory.createLinearRing(seq);
/*     */   }
/*     */   
/*     */   protected Geometry transformLineString(LineString geom, Geometry parent) {
/* 234 */     return (Geometry)this.factory.createLineString(transformCoordinates(geom.getCoordinateSequence(), (Geometry)geom));
/*     */   }
/*     */   
/*     */   protected Geometry transformMultiLineString(MultiLineString geom, Geometry parent) {
/* 239 */     List<Geometry> transGeomList = new ArrayList();
/* 240 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 241 */       Geometry transformGeom = transformLineString((LineString)geom.getGeometryN(i), (Geometry)geom);
/* 242 */       if (transformGeom != null && 
/* 243 */         !transformGeom.isEmpty())
/* 244 */         transGeomList.add(transformGeom); 
/*     */     } 
/* 246 */     return this.factory.buildGeometry(transGeomList);
/*     */   }
/*     */   
/*     */   protected Geometry transformPolygon(Polygon geom, Geometry parent) {
/* 250 */     boolean isAllValidLinearRings = true;
/* 251 */     Geometry shell = transformLinearRing((LinearRing)geom.getExteriorRing(), (Geometry)geom);
/* 253 */     if (shell == null || !(shell instanceof LinearRing) || shell.isEmpty())
/* 256 */       isAllValidLinearRings = false; 
/* 259 */     ArrayList<Geometry> holes = new ArrayList();
/* 260 */     for (int i = 0; i < geom.getNumInteriorRing(); i++) {
/* 261 */       Geometry hole = transformLinearRing((LinearRing)geom.getInteriorRingN(i), (Geometry)geom);
/* 262 */       if (hole != null && !hole.isEmpty()) {
/* 265 */         if (!(hole instanceof LinearRing))
/* 266 */           isAllValidLinearRings = false; 
/* 268 */         holes.add(hole);
/*     */       } 
/*     */     } 
/* 271 */     if (isAllValidLinearRings)
/* 272 */       return (Geometry)this.factory.createPolygon((LinearRing)shell, holes.<LinearRing>toArray(new LinearRing[0])); 
/* 275 */     List<Geometry> components = new ArrayList();
/* 276 */     if (shell != null)
/* 276 */       components.add(shell); 
/* 277 */     components.addAll(holes);
/* 278 */     return this.factory.buildGeometry(components);
/*     */   }
/*     */   
/*     */   protected Geometry transformMultiPolygon(MultiPolygon geom, Geometry parent) {
/* 283 */     List<Geometry> transGeomList = new ArrayList();
/* 284 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 285 */       Geometry transformGeom = transformPolygon((Polygon)geom.getGeometryN(i), (Geometry)geom);
/* 286 */       if (transformGeom != null && 
/* 287 */         !transformGeom.isEmpty())
/* 288 */         transGeomList.add(transformGeom); 
/*     */     } 
/* 290 */     return this.factory.buildGeometry(transGeomList);
/*     */   }
/*     */   
/*     */   protected Geometry transformGeometryCollection(GeometryCollection geom, Geometry parent) {
/* 294 */     List<Geometry> transGeomList = new ArrayList();
/* 295 */     for (int i = 0; i < geom.getNumGeometries(); i++) {
/* 296 */       Geometry transformGeom = transform(geom.getGeometryN(i));
/* 297 */       if (transformGeom != null && (
/* 298 */         !this.pruneEmptyGeometry || !transformGeom.isEmpty()))
/* 299 */         transGeomList.add(transformGeom); 
/*     */     } 
/* 301 */     if (this.preserveGeometryCollectionType)
/* 302 */       return (Geometry)this.factory.createGeometryCollection(GeometryFactory.toGeometryArray(transGeomList)); 
/* 303 */     return this.factory.buildGeometry(transGeomList);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryTransformer.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */