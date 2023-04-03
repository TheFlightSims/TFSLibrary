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
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ public class GeometryEditor {
/*  90 */   private GeometryFactory factory = null;
/*     */   
/*     */   public GeometryEditor() {}
/*     */   
/*     */   public GeometryEditor(GeometryFactory factory) {
/* 108 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public Geometry edit(Geometry geometry, GeometryEditorOperation operation) {
/* 123 */     if (geometry == null)
/* 123 */       return null; 
/* 126 */     if (this.factory == null)
/* 127 */       this.factory = geometry.getFactory(); 
/* 129 */     if (geometry instanceof GeometryCollection)
/* 130 */       return (Geometry)editGeometryCollection((GeometryCollection)geometry, operation); 
/* 134 */     if (geometry instanceof Polygon)
/* 135 */       return (Geometry)editPolygon((Polygon)geometry, operation); 
/* 138 */     if (geometry instanceof Point)
/* 139 */       return operation.edit(geometry, this.factory); 
/* 142 */     if (geometry instanceof LineString)
/* 143 */       return operation.edit(geometry, this.factory); 
/* 146 */     Assert.shouldNeverReachHere("Unsupported Geometry class: " + geometry.getClass().getName());
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   private Polygon editPolygon(Polygon polygon, GeometryEditorOperation operation) {
/* 152 */     Polygon newPolygon = (Polygon)operation.edit((Geometry)polygon, this.factory);
/* 154 */     if (newPolygon == null)
/* 155 */       newPolygon = this.factory.createPolygon((CoordinateSequence)null); 
/* 156 */     if (newPolygon.isEmpty())
/* 158 */       return newPolygon; 
/* 161 */     LinearRing shell = (LinearRing)edit((Geometry)newPolygon.getExteriorRing(), operation);
/* 162 */     if (shell == null || shell.isEmpty())
/* 164 */       return this.factory.createPolygon(null, null); 
/* 167 */     ArrayList<LinearRing> holes = new ArrayList();
/* 168 */     for (int i = 0; i < newPolygon.getNumInteriorRing(); i++) {
/* 169 */       LinearRing hole = (LinearRing)edit((Geometry)newPolygon.getInteriorRingN(i), operation);
/* 170 */       if (hole != null && !hole.isEmpty())
/* 173 */         holes.add(hole); 
/*     */     } 
/* 176 */     return this.factory.createPolygon(shell, holes.<LinearRing>toArray(new LinearRing[0]));
/*     */   }
/*     */   
/*     */   private GeometryCollection editGeometryCollection(GeometryCollection collection, GeometryEditorOperation operation) {
/* 184 */     GeometryCollection collectionForType = (GeometryCollection)operation.edit((Geometry)collection, this.factory);
/* 188 */     ArrayList<Geometry> geometries = new ArrayList();
/* 189 */     for (int i = 0; i < collectionForType.getNumGeometries(); i++) {
/* 190 */       Geometry geometry = edit(collectionForType.getGeometryN(i), operation);
/* 191 */       if (geometry != null && !geometry.isEmpty())
/* 194 */         geometries.add(geometry); 
/*     */     } 
/* 197 */     if (collectionForType.getClass() == MultiPoint.class)
/* 198 */       return (GeometryCollection)this.factory.createMultiPoint(geometries.<Point>toArray(new Point[0])); 
/* 201 */     if (collectionForType.getClass() == MultiLineString.class)
/* 202 */       return (GeometryCollection)this.factory.createMultiLineString(geometries.<LineString>toArray(new LineString[0])); 
/* 205 */     if (collectionForType.getClass() == MultiPolygon.class)
/* 206 */       return (GeometryCollection)this.factory.createMultiPolygon(geometries.<Polygon>toArray(new Polygon[0])); 
/* 209 */     return this.factory.createGeometryCollection(geometries.<Geometry>toArray(new Geometry[0]));
/*     */   }
/*     */   
/*     */   public static interface GeometryEditorOperation {
/*     */     Geometry edit(Geometry param1Geometry, GeometryFactory param1GeometryFactory);
/*     */   }
/*     */   
/*     */   public static class NoOpGeometryOperation implements GeometryEditorOperation {
/*     */     public Geometry edit(Geometry geometry, GeometryFactory factory) {
/* 247 */       return geometry;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class CoordinateOperation implements GeometryEditorOperation {
/*     */     public final Geometry edit(Geometry geometry, GeometryFactory factory) {
/* 259 */       if (geometry instanceof LinearRing)
/* 260 */         return (Geometry)factory.createLinearRing(edit(geometry.getCoordinates(), geometry)); 
/* 264 */       if (geometry instanceof LineString)
/* 265 */         return (Geometry)factory.createLineString(edit(geometry.getCoordinates(), geometry)); 
/* 269 */       if (geometry instanceof Point) {
/* 270 */         Coordinate[] newCoordinates = edit(geometry.getCoordinates(), geometry);
/* 273 */         return (Geometry)factory.createPoint((newCoordinates.length > 0) ? newCoordinates[0] : null);
/*     */       } 
/* 277 */       return geometry;
/*     */     }
/*     */     
/*     */     public abstract Coordinate[] edit(Coordinate[] param1ArrayOfCoordinate, Geometry param1Geometry);
/*     */   }
/*     */   
/*     */   public static abstract class CoordinateSequenceOperation implements GeometryEditorOperation {
/*     */     public final Geometry edit(Geometry geometry, GeometryFactory factory) {
/* 304 */       if (geometry instanceof LinearRing)
/* 305 */         return (Geometry)factory.createLinearRing(edit(((LinearRing)geometry).getCoordinateSequence(), geometry)); 
/* 310 */       if (geometry instanceof LineString)
/* 311 */         return (Geometry)factory.createLineString(edit(((LineString)geometry).getCoordinateSequence(), geometry)); 
/* 316 */       if (geometry instanceof Point)
/* 317 */         return (Geometry)factory.createPoint(edit(((Point)geometry).getCoordinateSequence(), geometry)); 
/* 322 */       return geometry;
/*     */     }
/*     */     
/*     */     public abstract CoordinateSequence edit(CoordinateSequence param1CoordinateSequence, Geometry param1Geometry);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geo\\util\GeometryEditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */