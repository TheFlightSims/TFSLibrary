/*     */ package org.geotools.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ public class GeometryTypeConverterFactory implements ConverterFactory {
/*  61 */   private static final Logger LOGGER = Logging.getLogger(GeometryTypeConverterFactory.class);
/*     */   
/*  63 */   static GeometryFactory gFac = new GeometryFactory();
/*     */   
/*     */   public Converter createConverter(Class<?> source, Class<?> target, Hints hints) {
/*  68 */     if (Geometry.class.isAssignableFrom(source) && Geometry.class.isAssignableFrom(target))
/*  69 */       return new Converter() {
/*     */           public <T> List<T> convertAll(GeometryCollection gc, Class<T> target) throws Exception {
/*  80 */             List<T> result = new ArrayList<T>();
/*  81 */             for (int count = 0; count < gc.getNumGeometries(); count++) {
/*  82 */               T geo = (T)convert(gc.getGeometryN(count), target);
/*  83 */               if (geo != null)
/*  84 */                 result.add(geo); 
/*     */             } 
/*  86 */             return result;
/*     */           }
/*     */           
/*     */           public Object convert(Object source, Class<?> target) throws Exception {
/*  92 */             if (target.isAssignableFrom(source.getClass()))
/*  93 */               return source; 
/*  94 */             if (source instanceof Geometry) {
/*     */               Polygon polygon;
/*  95 */               Geometry sourceGeometry = (Geometry)source;
/*  97 */               Geometry destGeometry = null;
/* 103 */               if (MultiPoint.class.isAssignableFrom(target)) {
/*     */                 Point[] points;
/* 106 */                 if (sourceGeometry.isEmpty()) {
/* 107 */                   points = new Point[0];
/* 108 */                 } else if (source instanceof Point) {
/* 109 */                   points = new Point[] { (Point)source };
/* 110 */                 } else if (source instanceof GeometryCollection) {
/* 111 */                   points = (Point[])convertAll((GeometryCollection)source, Point.class).toArray((Object[])new Point[0]);
/*     */                 } else {
/* 113 */                   points = new Point[] { (Point)convert(source, Point.class) };
/*     */                 } 
/* 114 */                 MultiPoint multiPoint = GeometryTypeConverterFactory.gFac.createMultiPoint(points);
/* 116 */               } else if (MultiLineString.class.isAssignableFrom(target)) {
/*     */                 LineString[] lineStrings;
/* 119 */                 if (sourceGeometry.isEmpty()) {
/* 120 */                   lineStrings = new LineString[0];
/* 121 */                 } else if (source instanceof LineString) {
/* 122 */                   lineStrings = new LineString[] { (LineString)source };
/* 123 */                 } else if (source instanceof GeometryCollection) {
/* 124 */                   lineStrings = (LineString[])convertAll((GeometryCollection)source, LineString.class).toArray((Object[])new LineString[0]);
/*     */                 } else {
/* 126 */                   lineStrings = new LineString[] { (LineString)convert(source, LineString.class) };
/*     */                 } 
/* 127 */                 MultiLineString multiLineString = GeometryTypeConverterFactory.gFac.createMultiLineString(lineStrings);
/* 129 */               } else if (MultiPolygon.class.isAssignableFrom(target)) {
/*     */                 Polygon[] polygons;
/* 132 */                 if (sourceGeometry.isEmpty()) {
/* 133 */                   polygons = new Polygon[0];
/* 134 */                 } else if (source instanceof Polygon) {
/* 135 */                   polygons = new Polygon[] { (Polygon)source };
/* 136 */                 } else if (source instanceof GeometryCollection) {
/* 137 */                   polygons = (Polygon[])convertAll((GeometryCollection)source, Polygon.class).toArray((Object[])new Polygon[0]);
/*     */                 } else {
/* 139 */                   polygons = new Polygon[] { (Polygon)convert(source, Polygon.class) };
/*     */                 } 
/* 140 */                 MultiPolygon multiPolygon = GeometryTypeConverterFactory.gFac.createMultiPolygon(polygons);
/* 145 */               } else if (GeometryCollection.class.isAssignableFrom(target)) {
/* 147 */                 if (sourceGeometry.isEmpty()) {
/* 148 */                   GeometryCollection geometryCollection = GeometryTypeConverterFactory.gFac.createGeometryCollection(new Geometry[0]);
/*     */                 } else {
/* 151 */                   GeometryCollection geometryCollection = GeometryTypeConverterFactory.gFac.createGeometryCollection(new Geometry[] { (Geometry)source });
/*     */                 } 
/* 155 */               } else if (Point.class.isAssignableFrom(target)) {
/* 157 */                 if (sourceGeometry.isEmpty()) {
/* 158 */                   Point point = GeometryTypeConverterFactory.gFac.createPoint((Coordinate)null);
/* 159 */                 } else if (source instanceof MultiPoint && sourceGeometry.getNumGeometries() == 1) {
/* 160 */                   destGeometry = (Geometry)((MultiPoint)source).getGeometryN(0).clone();
/*     */                 } else {
/* 162 */                   if (GeometryTypeConverterFactory.LOGGER.isLoggable(Level.FINE))
/* 163 */                     GeometryTypeConverterFactory.LOGGER.fine("Converting Geometry " + source.toString() + " to Point. This could be unsafe"); 
/* 164 */                   Point point = ((Geometry)source).getCentroid();
/*     */                 } 
/* 169 */               } else if (LineString.class.isAssignableFrom(target)) {
/* 171 */                 if (sourceGeometry.isEmpty()) {
/* 172 */                   LineString lineString = GeometryTypeConverterFactory.gFac.createLineString(new Coordinate[0]);
/* 173 */                 } else if (source instanceof MultiLineString && sourceGeometry.getNumGeometries() == 1) {
/* 174 */                   destGeometry = (Geometry)((MultiLineString)source).getGeometryN(0).clone();
/*     */                 } else {
/* 176 */                   if (GeometryTypeConverterFactory.LOGGER.isLoggable(Level.FINE))
/* 177 */                     GeometryTypeConverterFactory.LOGGER.fine("Converting Geometry " + source.toString() + " to LineString. This could be unsafe"); 
/* 178 */                   LineString lineString = GeometryTypeConverterFactory.gFac.createLineString(getLineStringCoordinates(((Geometry)source).getCoordinates()));
/*     */                 } 
/* 182 */               } else if (Polygon.class.isAssignableFrom(target)) {
/* 184 */                 if (sourceGeometry.isEmpty()) {
/* 185 */                   LineString lineString = GeometryTypeConverterFactory.gFac.createLineString(new Coordinate[0]);
/* 186 */                 } else if (source instanceof MultiPolygon && sourceGeometry.getNumGeometries() == 1) {
/* 187 */                   destGeometry = (Geometry)((MultiPolygon)source).getGeometryN(0).clone();
/*     */                 } else {
/* 189 */                   if (GeometryTypeConverterFactory.LOGGER.isLoggable(Level.FINE))
/* 190 */                     GeometryTypeConverterFactory.LOGGER.fine("Converting Geometry " + source.toString() + " to Polygon. This could be unsafe"); 
/* 191 */                   Coordinate[] coords = getPolygonCoordinates(((Geometry)source).getCoordinates());
/* 192 */                   polygon = GeometryTypeConverterFactory.gFac.createPolygon(GeometryTypeConverterFactory.gFac.createLinearRing(coords), new com.vividsolutions.jts.geom.LinearRing[0]);
/*     */                 } 
/*     */               } 
/* 200 */               if (polygon != null) {
/* 201 */                 Map<Object, Object> newUserData = new HashMap<Object, Object>();
/* 204 */                 if (polygon.getUserData() instanceof Map) {
/* 205 */                   newUserData.putAll((Map<?, ?>)polygon.getUserData());
/* 206 */                 } else if (polygon.getUserData() instanceof CoordinateReferenceSystem) {
/* 207 */                   newUserData.put(CoordinateReferenceSystem.class, polygon.getUserData());
/*     */                 } 
/* 211 */                 if (sourceGeometry.getUserData() instanceof Map) {
/* 212 */                   newUserData.putAll((Map<?, ?>)sourceGeometry.getUserData());
/* 213 */                 } else if (sourceGeometry.getUserData() instanceof CoordinateReferenceSystem) {
/* 214 */                   newUserData.put(CoordinateReferenceSystem.class, sourceGeometry.getUserData());
/*     */                 } 
/* 218 */                 polygon.setUserData(newUserData);
/*     */               } 
/* 221 */               return polygon;
/*     */             } 
/* 224 */             return null;
/*     */           }
/*     */           
/*     */           private <T> T[] arrayCopy(T[] original, int length) {
/* 229 */             Class<?> arrayType = original.getClass().getComponentType();
/* 230 */             T[] copy = (T[])Array.newInstance(arrayType, length);
/* 231 */             System.arraycopy(original, 0, copy, 0, (original.length < length) ? original.length : length);
/* 232 */             return copy;
/*     */           }
/*     */           
/*     */           private Coordinate[] growCoordinatesNum(Coordinate[] input, int numpoints) {
/* 246 */             if (input.length < numpoints) {
/* 247 */               Coordinate[] newCoordinates = arrayCopy(input, numpoints);
/* 248 */               Arrays.fill((Object[])newCoordinates, input.length, numpoints, input[0]);
/* 250 */               input = newCoordinates;
/*     */             } 
/* 252 */             return input;
/*     */           }
/*     */           
/*     */           private Coordinate[] getLineStringCoordinates(Coordinate[] coordinates) {
/* 264 */             coordinates = growCoordinatesNum(coordinates, 2);
/* 265 */             return coordinates;
/*     */           }
/*     */           
/*     */           private Coordinate[] getPolygonCoordinates(Coordinate[] coordinates) {
/* 278 */             coordinates = growCoordinatesNum(coordinates, 4);
/* 280 */             if (!coordinates[coordinates.length - 1].equals(coordinates[0])) {
/* 281 */               Coordinate[] newCoordinates = arrayCopy(coordinates, coordinates.length + 1);
/* 282 */               newCoordinates[newCoordinates.length - 1] = newCoordinates[0];
/* 284 */               coordinates = newCoordinates;
/*     */             } 
/* 286 */             return coordinates;
/*     */           }
/*     */         }; 
/* 294 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\GeometryTypeConverterFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */