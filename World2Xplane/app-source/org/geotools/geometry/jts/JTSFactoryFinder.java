/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryFinder;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.resources.LazySet;
/*     */ 
/*     */ public class JTSFactoryFinder extends FactoryFinder {
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/*  65 */     assert Thread.holdsLock(JTSFactoryFinder.class);
/*  66 */     if (registry == null) {
/*  67 */       registry = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { GeometryFactory.class }));
/*  68 */       registry.registerServiceProvider(new GeometryFactory(), GeometryFactory.class);
/*     */     } 
/*  70 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized GeometryFactory getGeometryFactory(Hints hints) throws FactoryRegistryException {
/*  89 */     hints = mergeSystemHints(hints);
/*  90 */     return (GeometryFactory)getServiceRegistry().getServiceProvider(GeometryFactory.class, null, hints, (Hints.Key)Hints.JTS_GEOMETRY_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized GeometryFactory getGeometryFactory() throws FactoryRegistryException {
/* 102 */     return getGeometryFactory((Hints)null);
/*     */   }
/*     */   
/*     */   public static synchronized Set getGeometryFactories() {
/* 110 */     return (Set)new LazySet(getServiceRegistry().getServiceProviders(GeometryFactory.class, null, null));
/*     */   }
/*     */   
/*     */   public static synchronized PrecisionModel getPrecisionModel(Hints hints) throws FactoryRegistryException {
/* 124 */     hints = mergeSystemHints(hints);
/* 125 */     return (PrecisionModel)getServiceRegistry().getServiceProvider(PrecisionModel.class, null, hints, Hints.JTS_PRECISION_MODEL);
/*     */   }
/*     */   
/*     */   public static synchronized Set getPrecisionModels() {
/* 135 */     return (Set)new LazySet(getServiceRegistry().getServiceProviders(PrecisionModel.class, null, null));
/*     */   }
/*     */   
/*     */   public static synchronized CoordinateSequenceFactory getCoordinateSequenceFactory(Hints hints) throws FactoryRegistryException {
/* 149 */     hints = mergeSystemHints(hints);
/* 150 */     return (CoordinateSequenceFactory)getServiceRegistry().getServiceProvider(CoordinateSequenceFactory.class, null, hints, (Hints.Key)Hints.JTS_COORDINATE_SEQUENCE_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized Set getCoordinateSequenceFactories() {
/* 161 */     return (Set)new LazySet(getServiceRegistry().getServiceProviders(CoordinateSequenceFactory.class, null, null));
/*     */   }
/*     */   
/*     */   public static void scanForPlugins() {
/* 175 */     if (registry != null)
/* 176 */       registry.scanForPlugins(); 
/*     */   }
/*     */   
/*     */   private static final class Registry extends FactoryCreator {
/*     */     public Registry() {
/* 191 */       super(Arrays.asList((Class<?>[][])new Class[] { GeometryFactory.class, PrecisionModel.class, CoordinateSequenceFactory.class }));
/*     */     }
/*     */     
/*     */     protected Object createServiceProvider(Class<?> category, Class implementation, Hints hints) throws FactoryRegistryException {
/* 211 */       if (GeometryFactory.class.isAssignableFrom(category) && GeometryFactory.class.equals(implementation))
/* 214 */         return new GeometryFactory(JTSFactoryFinder.getPrecisionModel(hints), getSRID(hints), JTSFactoryFinder.getCoordinateSequenceFactory(hints)); 
/* 217 */       return super.createServiceProvider(category, implementation, hints);
/*     */     }
/*     */     
/*     */     private static int getSRID(Hints hints) {
/* 224 */       if (hints != null) {
/* 225 */         Integer SRID = (Integer)hints.get(Hints.JTS_SRID);
/* 226 */         if (SRID != null)
/* 227 */           return SRID.intValue(); 
/*     */       } 
/* 230 */       return 0;
/*     */     }
/*     */     
/*     */     protected boolean isAcceptable(Object provider, Class<?> category, Hints hints) {
/* 248 */       if (GeometryFactory.class.isAssignableFrom(category)) {
/* 249 */         GeometryFactory factory = (GeometryFactory)provider;
/* 250 */         CoordinateSequenceFactory sequence = factory.getCoordinateSequenceFactory();
/* 251 */         PrecisionModel precision = factory.getPrecisionModel();
/* 252 */         if (!isAcceptable(sequence, hints.get(Hints.JTS_COORDINATE_SEQUENCE_FACTORY)) || !isAcceptable(precision, hints.get(Hints.JTS_PRECISION_MODEL)))
/* 255 */           return false; 
/* 257 */         int SRID = getSRID(hints);
/* 258 */         if (SRID != 0 && SRID != factory.getSRID())
/* 259 */           return false; 
/*     */       } 
/* 262 */       return super.isAcceptable(provider, category, hints);
/*     */     }
/*     */     
/*     */     private static boolean isAcceptable(Object actual, Object requested) {
/* 282 */       if (requested == null || requested.equals(actual))
/* 283 */         return true; 
/* 289 */       if (requested.getClass().isArray()) {
/* 290 */         int length = Array.getLength(requested);
/* 291 */         for (int i = 0; i < length; i++) {
/* 292 */           if (!isAcceptable(actual, Array.get(requested, i)))
/* 293 */             return false; 
/*     */         } 
/* 296 */         return true;
/*     */       } 
/* 302 */       if (actual != null && requested instanceof Class)
/* 303 */         return ((Class)requested).isAssignableFrom(actual.getClass()); 
/* 305 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\JTSFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */