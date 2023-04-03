/*     */ package org.geotools.gml;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class SubHandlerBox extends SubHandler {
/*  37 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*     */   
/*  40 */   Envelope e = new Envelope();
/*     */   
/*     */   public SubHandlerBox() {
/*  46 */     LOGGER.entering("SubHandlerBox", "new");
/*  47 */     LOGGER.exiting("SubHandlerBox", "new");
/*     */   }
/*     */   
/*     */   public void addCoordinate(Coordinate c) {
/*  56 */     LOGGER.entering("SubHandlerBox", "addCoordinate", c);
/*  57 */     this.e.expandToInclude(c);
/*  58 */     LOGGER.exiting("SubHandlerBox", "addCoordinate");
/*     */   }
/*     */   
/*     */   public boolean isComplete(String message) {
/*  69 */     LOGGER.entering("SubHandlerBox", "isComplete", message);
/*  70 */     LOGGER.exiting("SubHandlerBox", "isComplete", Boolean.TRUE);
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   public Geometry create(GeometryFactory geometryFactory) {
/*  85 */     LOGGER.entering("SubHandlerBox", "create", geometryFactory);
/*  87 */     Coordinate[] c = new Coordinate[5];
/*  88 */     c[0] = new Coordinate(this.e.getMinX(), this.e.getMinY());
/*  89 */     c[1] = new Coordinate(this.e.getMinX(), this.e.getMaxY());
/*  90 */     c[2] = new Coordinate(this.e.getMaxX(), this.e.getMaxY());
/*  91 */     c[3] = new Coordinate(this.e.getMaxX(), this.e.getMinY());
/*  92 */     c[4] = new Coordinate(this.e.getMinX(), this.e.getMinY());
/*  94 */     LinearRing r = null;
/*     */     try {
/*  97 */       r = geometryFactory.createLinearRing(c);
/*  98 */     } catch (TopologyException e) {
/*  99 */       System.err.println("Topology Exception in GMLBoxHandler");
/* 101 */       return null;
/*     */     } 
/* 104 */     Polygon polygon = geometryFactory.createPolygon(r, null);
/* 105 */     LOGGER.exiting("SubHandlerBox", "create", polygon);
/* 106 */     polygon.setUserData(getSRS());
/* 107 */     polygon.setSRID(getSRID());
/* 109 */     return (Geometry)polygon;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerBox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */