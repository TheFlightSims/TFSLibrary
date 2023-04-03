/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.RobustCGAlgorithms;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class SubHandlerPolygon extends SubHandler {
/*  44 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*     */   
/*  45 */   protected static CGAlgorithms cga = (CGAlgorithms)new RobustCGAlgorithms();
/*     */   
/*  48 */   private GeometryFactory geometryFactory = new GeometryFactory();
/*     */   
/*  51 */   private SubHandlerLinearRing currentHandler = new SubHandlerLinearRing();
/*     */   
/*  54 */   private LinearRing outerBoundary = null;
/*     */   
/*  57 */   private ArrayList innerBoundaries = new ArrayList();
/*     */   
/*  63 */   private int location = 0;
/*     */   
/*  66 */   private int INNER_BOUNDARY = 1;
/*     */   
/*  69 */   private int OUTER_BOUNDARY = 2;
/*     */   
/*     */   public void subGeometry(String message, int type) {
/*  92 */     if (message.equals("LinearRing")) {
/*  93 */       if (type == 2) {
/*  94 */         if (this.location == this.INNER_BOUNDARY) {
/*  95 */           LinearRing ring = (LinearRing)this.currentHandler.create(this.geometryFactory);
/*  96 */           Coordinate[] points = ring.getCoordinates();
/* 102 */           if (CGAlgorithms.isCCW(points)) {
/* 103 */             LOGGER.finer("good hole found");
/* 107 */             this.innerBoundaries.add(ring);
/*     */           } else {
/* 111 */             LOGGER.finer("bad hole found - fixing");
/* 112 */             Coordinate[] newPoints = new Coordinate[points.length];
/* 114 */             int i = 0, j = points.length - 1;
/* 115 */             for (; i < points.length; i++, j--)
/* 116 */               newPoints[i] = points[j]; 
/*     */             try {
/* 120 */               ring = this.geometryFactory.createLinearRing(newPoints);
/* 121 */               this.innerBoundaries.add(ring);
/* 122 */             } catch (TopologyException e) {
/* 123 */               LOGGER.warning("Caught Topology exception in GMLPolygonHandler");
/* 125 */               ring = null;
/*     */             } 
/*     */           } 
/* 128 */         } else if (this.location == this.OUTER_BOUNDARY) {
/* 133 */           this.outerBoundary = (LinearRing)this.currentHandler.create(this.geometryFactory);
/* 135 */           Coordinate[] points = this.outerBoundary.getCoordinates();
/* 137 */           if (CGAlgorithms.isCCW(points)) {
/* 138 */             LOGGER.finer("bad outer ring - rebuilding");
/* 140 */             Coordinate[] newPoints = new Coordinate[points.length];
/* 142 */             int i = 0, j = points.length - 1;
/* 143 */             for (; i < points.length; i++, j--)
/* 144 */               newPoints[i] = points[j]; 
/*     */             try {
/* 148 */               this.outerBoundary = this.geometryFactory.createLinearRing(newPoints);
/* 151 */             } catch (TopologyException e) {
/* 152 */               LOGGER.warning("Caught Topology exception in GMLPolygonHandler");
/* 154 */               this.outerBoundary = null;
/*     */             } 
/*     */           } 
/*     */         } 
/* 158 */       } else if (type == 1) {
/* 159 */         this.currentHandler = new SubHandlerLinearRing();
/*     */       } 
/* 161 */     } else if (message.equals("outerBoundaryIs")) {
/* 164 */       LOGGER.finer("new outer Boundary");
/* 165 */       this.location = this.OUTER_BOUNDARY;
/* 166 */     } else if (message.equals("innerBoundaryIs")) {
/* 167 */       LOGGER.finer("new InnerBoundary");
/* 168 */       this.location = this.INNER_BOUNDARY;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCoordinate(Coordinate coordinate) {
/* 178 */     this.currentHandler.addCoordinate(coordinate);
/*     */   }
/*     */   
/*     */   public boolean isComplete(String message) {
/* 193 */     if (message.equals("Polygon")) {
/* 194 */       if (this.outerBoundary != null)
/* 195 */         return true; 
/* 197 */       return false;
/*     */     } 
/* 205 */     return false;
/*     */   }
/*     */   
/*     */   public Geometry create(GeometryFactory geometryFactory) {
/* 217 */     for (int i = 0; i < this.innerBoundaries.size(); i++) {
/* 218 */       LinearRing hole = this.innerBoundaries.get(i);
/* 219 */       if (hole.crosses((Geometry)this.outerBoundary)) {
/* 220 */         LOGGER.warning("Topology Error building polygon");
/* 222 */         return null;
/*     */       } 
/*     */     } 
/* 226 */     LinearRing[] rings = (LinearRing[])this.innerBoundaries.toArray((Object[])new LinearRing[this.innerBoundaries.size()]);
/* 228 */     Polygon polygon = geometryFactory.createPolygon(this.outerBoundary, rings);
/* 229 */     polygon.setUserData(getSRS());
/* 230 */     polygon.setSRID(getSRID());
/* 231 */     return (Geometry)polygon;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerPolygon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */