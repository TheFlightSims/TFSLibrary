/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class SubHandlerMulti extends SubHandler {
/*  48 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*     */   
/*  53 */   private static final Collection BASE_GEOMETRY_TYPES = new Vector(Arrays.asList((Object[])new String[] { "Point", "LineString", "Polygon" }));
/*     */   
/*  57 */   private GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*  60 */   private SubHandlerFactory handlerFactory = new SubHandlerFactory();
/*     */   
/*     */   private SubHandler currentHandler;
/*     */   
/*  66 */   private List geometries = new Vector();
/*     */   
/*     */   private String internalType;
/*     */   
/*     */   private boolean internalTypeSet = false;
/*     */   
/*     */   public void subGeometry(String message, int type) {
/*  87 */     LOGGER.fine("subGeometry message = " + message + " type = " + type);
/*  90 */     if (!this.internalTypeSet && 
/*  91 */       BASE_GEOMETRY_TYPES.contains(message)) {
/*  92 */       this.internalType = message;
/*  93 */       this.internalTypeSet = true;
/*  94 */       LOGGER.fine("Internal type set to " + message);
/*     */     } 
/* 101 */     if (message.equals(this.internalType)) {
/* 102 */       if (type == 1) {
/* 103 */         this.currentHandler = this.handlerFactory.create(this.internalType);
/* 104 */       } else if (type == 2) {
/* 105 */         this.geometries.add(this.currentHandler.create(this.geometryFactory));
/* 106 */       } else if (type == 3) {
/* 107 */         this.currentHandler.subGeometry(message, type);
/*     */       } 
/*     */     } else {
/* 110 */       this.currentHandler.subGeometry(message, type);
/* 111 */       LOGGER.fine(this.internalType + " != " + message);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCoordinate(Coordinate coordinate) {
/* 121 */     this.currentHandler.addCoordinate(coordinate);
/*     */   }
/*     */   
/*     */   public boolean isComplete(String message) {
/* 132 */     if (message.equals("Multi" + this.internalType))
/* 133 */       return true; 
/* 135 */     return false;
/*     */   }
/*     */   
/*     */   public Geometry create(GeometryFactory geometryFactory) {
/* 148 */     if (this.internalType.equals("Point")) {
/* 149 */       Point[] pointArray = GeometryFactory.toPointArray(this.geometries);
/* 150 */       MultiPoint multiPoint = geometryFactory.createMultiPoint(pointArray);
/* 151 */       multiPoint.setUserData(getSRS());
/* 152 */       multiPoint.setSRID(getSRID());
/* 153 */       LOGGER.fine("created " + multiPoint);
/* 155 */       return (Geometry)multiPoint;
/*     */     } 
/* 156 */     if (this.internalType.equals("LineString")) {
/* 157 */       LineString[] lineStringArray = GeometryFactory.toLineStringArray(this.geometries);
/* 159 */       MultiLineString multiLineString = geometryFactory.createMultiLineString(lineStringArray);
/* 160 */       multiLineString.setUserData(getSRS());
/* 161 */       multiLineString.setSRID(getSRID());
/* 162 */       LOGGER.fine("created " + multiLineString);
/* 164 */       return (Geometry)multiLineString;
/*     */     } 
/* 165 */     if (this.internalType.equals("Polygon")) {
/* 166 */       Polygon[] polygonArray = GeometryFactory.toPolygonArray(this.geometries);
/* 167 */       MultiPolygon multiPolygon = geometryFactory.createMultiPolygon(polygonArray);
/* 168 */       multiPolygon.setUserData(getSRS());
/* 169 */       multiPolygon.setSRID(getSRID());
/* 170 */       LOGGER.fine("created " + multiPolygon);
/* 172 */       return (Geometry)multiPolygon;
/*     */     } 
/* 174 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerMulti.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */