/*     */ package org.geotools.filter;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import org.geotools.filter.capability.FunctionNameImpl;
/*     */ import org.opengis.filter.capability.FunctionName;
/*     */ import org.opengis.filter.expression.Expression;
/*     */ import org.opengis.parameter.Parameter;
/*     */ 
/*     */ public class AreaFunction extends FunctionExpressionImpl {
/*  44 */   public static FunctionName NAME = (FunctionName)new FunctionNameImpl("Area", FunctionNameImpl.parameter("area", Double.class), new Parameter[] { FunctionNameImpl.parameter("geometry", Geometry.class) });
/*     */   
/*     */   public AreaFunction() {
/*  50 */     super(NAME);
/*     */   }
/*     */   
/*     */   public Object evaluate(Object feature) {
/*  55 */     Expression geom = getParameters().get(0);
/*  56 */     Geometry g = (Geometry)geom.evaluate(feature);
/*  58 */     return new Double(getArea(g));
/*     */   }
/*     */   
/*     */   public int getArgCount() {
/*  62 */     return 1;
/*     */   }
/*     */   
/*     */   protected double getArea(GeometryCollection geometryCollection1) {
/*  74 */     double area = 0.0D;
/*  76 */     int numberOfGeometries1 = geometryCollection1.getNumGeometries();
/*  78 */     for (int i = 0; i < numberOfGeometries1; i++)
/*  79 */       area += getArea(geometryCollection1.getGeometryN(i)); 
/*  81 */     return area;
/*     */   }
/*     */   
/*     */   protected double getPerimeter(GeometryCollection geometryCollection) {
/*  91 */     double perimeter = 0.0D;
/*  92 */     int numberOfGeometries = geometryCollection.getNumGeometries();
/*  95 */     for (int i = 0; i < numberOfGeometries; i++)
/*  96 */       perimeter += getPerimeter(geometryCollection.getGeometryN(i)); 
/*  98 */     return perimeter;
/*     */   }
/*     */   
/*     */   public double getArea(Geometry geometry) {
/* 113 */     double area = 0.0D;
/* 114 */     if (geometry instanceof GeometryCollection) {
/* 115 */       area += getArea((GeometryCollection)geometry);
/* 116 */     } else if (geometry instanceof MultiPolygon) {
/* 117 */       area += getArea((MultiPolygon)geometry);
/* 118 */     } else if (geometry instanceof Polygon) {
/* 119 */       area += getArea((Polygon)geometry);
/*     */     } else {
/* 121 */       area += 0.0D;
/*     */     } 
/* 123 */     return area;
/*     */   }
/*     */   
/*     */   public double getPerimeter(Geometry geometry) {
/* 138 */     double perimeter = 0.0D;
/* 139 */     if (geometry instanceof GeometryCollection) {
/* 140 */       perimeter += getPerimeter((GeometryCollection)geometry);
/* 141 */     } else if (geometry instanceof MultiPolygon) {
/* 142 */       perimeter += getPerimeter((GeometryCollection)geometry);
/* 143 */     } else if (geometry instanceof Polygon) {
/* 144 */       perimeter += getPerimeter((Polygon)geometry);
/* 145 */     } else if (geometry instanceof MultiLineString) {
/* 146 */       perimeter += getPerimeter((MultiLineString)geometry);
/* 147 */     } else if (geometry instanceof LineString) {
/* 148 */       perimeter += getPerimeter((LineString)geometry);
/*     */     } else {
/* 150 */       perimeter += 0.0D;
/*     */     } 
/* 152 */     return perimeter;
/*     */   }
/*     */   
/*     */   protected double getArea(MultiPolygon multiPolygon) {
/* 161 */     double area = 0.0D;
/* 162 */     int numberOfGeometries = multiPolygon.getNumGeometries();
/* 163 */     for (int i = 0; i < numberOfGeometries; i++)
/* 164 */       area += getArea(multiPolygon.getGeometryN(i)); 
/* 166 */     return area;
/*     */   }
/*     */   
/*     */   protected double getperimeter(MultiPolygon multiPolygon) {
/* 176 */     double perimeter = 0.0D;
/* 177 */     int numberOfGeometries = multiPolygon.getNumGeometries();
/* 178 */     for (int i = 0; i < numberOfGeometries; i++)
/* 179 */       perimeter += getPerimeter(multiPolygon.getGeometryN(i)); 
/* 181 */     return perimeter;
/*     */   }
/*     */   
/*     */   protected double getArea(Polygon polygon) {
/* 190 */     double area = 0.0D;
/* 191 */     double interiorArea = 0.0D;
/* 192 */     Coordinate[] exteriorRingCoordinates = polygon.getExteriorRing().getCoordinates();
/* 193 */     int numberOfExteriorRingCoordinates = exteriorRingCoordinates.length;
/* 195 */     double minx = Double.POSITIVE_INFINITY;
/* 196 */     double maxx = Double.NEGATIVE_INFINITY;
/* 197 */     double miny = Double.POSITIVE_INFINITY;
/* 198 */     double maxy = Double.NEGATIVE_INFINITY;
/*     */     int i;
/* 199 */     for (i = 0; i < numberOfExteriorRingCoordinates; i++) {
/* 200 */       minx = Math.min(minx, (exteriorRingCoordinates[i]).x);
/* 201 */       maxx = Math.max(maxx, (exteriorRingCoordinates[i]).x);
/* 202 */       miny = Math.min(miny, (exteriorRingCoordinates[i]).y);
/* 203 */       maxy = Math.max(maxy, (exteriorRingCoordinates[i]).y);
/*     */     } 
/* 208 */     for (i = 0; i < numberOfExteriorRingCoordinates - 1; i++)
/* 209 */       area += ((exteriorRingCoordinates[i + 1]).x - minx - (exteriorRingCoordinates[i]).x - minx) * ((exteriorRingCoordinates[i + 1]).y - miny + (exteriorRingCoordinates[i]).y - miny) / 2.0D; 
/* 214 */     area = Math.abs(area);
/* 217 */     int numberOfInteriorRings = polygon.getNumInteriorRing();
/* 220 */     for (int j = 0; j < numberOfInteriorRings; j++) {
/* 221 */       interiorArea = 0.0D;
/* 222 */       Coordinate[] interiorRingCoordinates = polygon.getInteriorRingN(j).getCoordinates();
/* 223 */       int numberOfInteriorRingCoordinates = interiorRingCoordinates.length;
/* 224 */       minx = Double.POSITIVE_INFINITY;
/* 225 */       maxx = Double.NEGATIVE_INFINITY;
/* 226 */       miny = Double.POSITIVE_INFINITY;
/* 227 */       maxy = Double.NEGATIVE_INFINITY;
/*     */       int k;
/* 228 */       for (k = 0; k < numberOfInteriorRingCoordinates; k++) {
/* 229 */         minx = Math.min(minx, (interiorRingCoordinates[k]).x);
/* 230 */         maxx = Math.max(maxx, (interiorRingCoordinates[k]).x);
/* 231 */         miny = Math.min(miny, (interiorRingCoordinates[k]).y);
/* 232 */         maxy = Math.max(maxy, (interiorRingCoordinates[k]).y);
/*     */       } 
/* 234 */       for (k = 0; k < numberOfInteriorRingCoordinates - 1; k++)
/* 235 */         interiorArea += ((interiorRingCoordinates[k + 1]).x - minx - (interiorRingCoordinates[k]).x - minx) * ((interiorRingCoordinates[k + 1]).y - miny + (interiorRingCoordinates[k]).y - miny) / 2.0D; 
/* 240 */       area -= Math.abs(interiorArea);
/*     */     } 
/* 242 */     return area;
/*     */   }
/*     */   
/*     */   protected double getPerimeter(Polygon polygon) {
/* 251 */     double perimeter = 0.0D;
/* 252 */     LineString lineString = polygon.getExteriorRing();
/* 253 */     perimeter += getPerimeter(lineString);
/* 254 */     int numberOfHoles = polygon.getNumInteriorRing();
/* 255 */     for (int i = 0; i < numberOfHoles; i++)
/* 256 */       perimeter += getPerimeter(polygon.getInteriorRingN(i)); 
/* 258 */     return perimeter;
/*     */   }
/*     */   
/*     */   protected double getPerimeter(MultiLineString multiLineString) {
/* 268 */     double perimeter = 0.0D;
/* 269 */     int numberOfGeometries = multiLineString.getNumGeometries();
/* 270 */     for (int i = 0; i < numberOfGeometries; i++)
/* 271 */       perimeter += getPerimeter(multiLineString.getGeometryN(i)); 
/* 273 */     return perimeter;
/*     */   }
/*     */   
/*     */   protected double getPerimeter(LineString lineString) {
/* 282 */     double perimeter = 0.0D;
/* 283 */     int numberOfPoints = lineString.getNumPoints();
/* 284 */     Coordinate[] coordinates = lineString.getCoordinates();
/* 285 */     for (int i = 0; i < numberOfPoints - 1; i++)
/* 286 */       perimeter += coordinates[i].distance(coordinates[i + 1]); 
/* 288 */     return perimeter;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\AreaFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */