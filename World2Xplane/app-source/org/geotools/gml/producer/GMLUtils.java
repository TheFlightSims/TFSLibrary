/*     */ package org.geotools.gml.producer;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ final class GMLUtils {
/*  40 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml.producer");
/*     */   
/*     */   public static final String GML_URL = "http://www.opengis.net/gml";
/*     */   
/*     */   protected static final int POINT = 1;
/*     */   
/*     */   protected static final int LINESTRING = 2;
/*     */   
/*     */   protected static final int POLYGON = 3;
/*     */   
/*     */   protected static final int MULTIPOINT = 4;
/*     */   
/*     */   protected static final int MULTILINESTRING = 5;
/*     */   
/*     */   protected static final int MULTIPOLYGON = 6;
/*     */   
/*     */   protected static final int MULTIGEOMETRY = 7;
/*     */   
/*     */   public static String getGeometryName(Geometry geometry) {
/*  81 */     LOGGER.entering("GMLUtils", "getGeometryName", geometry);
/*  83 */     String returnValue = null;
/*  85 */     if (geometry instanceof com.vividsolutions.jts.geom.Point) {
/*  86 */       returnValue = "Point";
/*  87 */     } else if (geometry instanceof com.vividsolutions.jts.geom.LineString) {
/*  88 */       returnValue = "LineString";
/*  89 */     } else if (geometry instanceof com.vividsolutions.jts.geom.Polygon) {
/*  90 */       returnValue = "Polygon";
/*  91 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiPoint) {
/*  92 */       returnValue = "MultiPoint";
/*  93 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiLineString) {
/*  94 */       returnValue = "MultiLineString";
/*  95 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiPolygon) {
/*  96 */       returnValue = "MultiPolygon";
/*  97 */     } else if (geometry instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/*  98 */       returnValue = "GeometryCollection";
/*     */     } else {
/* 101 */       returnValue = null;
/*     */     } 
/* 104 */     LOGGER.exiting("GMLUtils", "getGeometryName", returnValue);
/* 106 */     return returnValue;
/*     */   }
/*     */   
/*     */   public static int getGeometryType(Geometry geometry) {
/* 119 */     int returnValue = -1;
/* 121 */     if (geometry instanceof com.vividsolutions.jts.geom.Point) {
/* 123 */       returnValue = 1;
/* 124 */     } else if (geometry instanceof com.vividsolutions.jts.geom.LineString) {
/* 126 */       returnValue = 2;
/* 127 */     } else if (geometry instanceof com.vividsolutions.jts.geom.Polygon) {
/* 129 */       returnValue = 3;
/* 130 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiPoint) {
/* 132 */       returnValue = 4;
/* 133 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiLineString) {
/* 134 */       returnValue = 5;
/* 135 */     } else if (geometry instanceof com.vividsolutions.jts.geom.MultiPolygon) {
/* 136 */       returnValue = 6;
/* 137 */     } else if (geometry instanceof com.vividsolutions.jts.geom.GeometryCollection) {
/* 138 */       returnValue = 7;
/*     */     } else {
/* 140 */       returnValue = -1;
/*     */     } 
/* 147 */     return returnValue;
/*     */   }
/*     */   
/*     */   public static String getMemberName(int geometryType) {
/* 153 */     switch (geometryType) {
/*     */       case 4:
/* 155 */         return "pointMember";
/*     */       case 5:
/* 158 */         return "lineStringMember";
/*     */       case 6:
/* 161 */         return "polygonMember";
/*     */     } 
/* 164 */     return "geometryMember";
/*     */   }
/*     */   
/*     */   public static String encodeXML(String inData) {
/* 184 */     if (inData == null)
/* 185 */       return null; 
/* 191 */     if (inData.indexOf('&') == -1 && inData.indexOf('<') == -1 && inData.indexOf('>') == -1 && inData.indexOf('\'') == -1 && inData.indexOf('"') == -1)
/* 194 */       return inData; 
/* 198 */     int length = inData.length();
/* 204 */     StringBuffer buffer = new StringBuffer(2 * length);
/* 209 */     for (int i = 0; i < length; i++) {
/* 210 */       char charToCompare = inData.charAt(i);
/* 213 */       if (charToCompare == '&') {
/* 214 */         buffer.append("&amp;");
/* 215 */       } else if (charToCompare == '<') {
/* 216 */         buffer.append("&lt;");
/* 217 */       } else if (charToCompare == '>') {
/* 218 */         buffer.append("&gt;");
/* 219 */       } else if (charToCompare == '"') {
/* 220 */         buffer.append("&quot;");
/* 221 */       } else if (charToCompare == '\'') {
/* 222 */         buffer.append("&apos;");
/*     */       } else {
/* 224 */         buffer.append(charToCompare);
/*     */       } 
/*     */     } 
/* 231 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\producer\GMLUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */