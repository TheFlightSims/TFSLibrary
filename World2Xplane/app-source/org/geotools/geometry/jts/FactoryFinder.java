/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class FactoryFinder {
/*     */   public static synchronized GeometryFactory getGeometryFactory(Hints hints) throws FactoryRegistryException {
/*  74 */     return JTSFactoryFinder.getGeometryFactory(hints);
/*     */   }
/*     */   
/*     */   public static synchronized Set getGeometryFactories() {
/*  83 */     return JTSFactoryFinder.getGeometryFactories();
/*     */   }
/*     */   
/*     */   public static synchronized PrecisionModel getPrecisionModel(Hints hints) throws FactoryRegistryException {
/*  97 */     return JTSFactoryFinder.getPrecisionModel(hints);
/*     */   }
/*     */   
/*     */   public static synchronized Set getPrecisionModels() {
/* 106 */     return JTSFactoryFinder.getPrecisionModels();
/*     */   }
/*     */   
/*     */   public static synchronized CoordinateSequenceFactory getCoordinateSequenceFactory(Hints hints) throws FactoryRegistryException {
/* 120 */     return JTSFactoryFinder.getCoordinateSequenceFactory(hints);
/*     */   }
/*     */   
/*     */   public static synchronized Set getCoordinateSequenceFactories() {
/* 130 */     return JTSFactoryFinder.getCoordinateSequenceFactories();
/*     */   }
/*     */   
/*     */   public static void scanForPlugins() {
/* 144 */     JTSFactoryFinder.scanForPlugins();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\FactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */