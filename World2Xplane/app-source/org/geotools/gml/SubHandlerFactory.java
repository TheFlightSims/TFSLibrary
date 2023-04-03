/*    */ package org.geotools.gml;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Vector;
/*    */ import java.util.logging.Logger;
/*    */ import org.geotools.util.logging.Logging;
/*    */ 
/*    */ public class SubHandlerFactory {
/* 34 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.gml");
/*    */   
/* 37 */   private static final Collection BASE_GEOMETRY_TYPES = new Vector(Arrays.asList((Object[])new String[] { "MultiPoint", "MultiLineString", "MultiPolygon" }));
/*    */   
/*    */   public SubHandlerFactory() {
/* 44 */     LOGGER.entering("SubHandlerFactory", "new");
/* 45 */     LOGGER.exiting("SubHandlerFactory", "new");
/*    */   }
/*    */   
/*    */   public SubHandler create(String type) {
/* 59 */     LOGGER.entering("SubHandlerFactory", "create", type);
/* 61 */     SubHandler returnValue = null;
/* 63 */     if (type.equals("Point")) {
/* 64 */       returnValue = new SubHandlerPoint();
/* 65 */     } else if (type.equals("LineString")) {
/* 66 */       returnValue = new SubHandlerLineString();
/* 67 */     } else if (type.equals("LinearRing")) {
/* 68 */       returnValue = new SubHandlerLinearRing();
/* 69 */     } else if (type.equals("Polygon")) {
/* 70 */       returnValue = new SubHandlerPolygon();
/* 71 */     } else if (type.equals("Box")) {
/* 72 */       returnValue = new SubHandlerBox();
/* 73 */     } else if (BASE_GEOMETRY_TYPES.contains(type)) {
/* 74 */       returnValue = new SubHandlerMulti();
/*    */     } else {
/* 76 */       returnValue = null;
/*    */     } 
/* 79 */     LOGGER.exiting("SubHandlerFactory", "create", returnValue);
/* 81 */     return returnValue;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\SubHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */