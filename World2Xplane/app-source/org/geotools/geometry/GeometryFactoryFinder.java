/*     */ package org.geotools.geometry;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryFinder;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.opengis.geometry.PositionFactory;
/*     */ import org.opengis.geometry.Precision;
/*     */ import org.opengis.geometry.aggregate.AggregateFactory;
/*     */ import org.opengis.geometry.complex.ComplexFactory;
/*     */ import org.opengis.geometry.coordinate.GeometryFactory;
/*     */ import org.opengis.geometry.primitive.PrimitiveFactory;
/*     */ 
/*     */ public class GeometryFactoryFinder extends FactoryFinder {
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/*  62 */     assert Thread.holdsLock(GeometryFactoryFinder.class);
/*  63 */     if (registry == null)
/*  64 */       registry = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { Precision.class, PositionFactory.class, GeometryFactory.class, ComplexFactory.class, AggregateFactory.class, PrimitiveFactory.class })); 
/*  72 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized Precision getPrecision(Hints hints) throws FactoryRegistryException {
/*  76 */     hints = mergeSystemHints(hints);
/*  77 */     return (Precision)getServiceRegistry().getServiceProvider(Precision.class, null, hints, Hints.PRECISION);
/*     */   }
/*     */   
/*     */   public static synchronized PositionFactory getPositionFactory(Hints hints) throws FactoryRegistryException {
/*  81 */     hints = mergeSystemHints(hints);
/*  82 */     return (PositionFactory)getServiceRegistry().getServiceProvider(PositionFactory.class, null, hints, Hints.POSITION_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized GeometryFactory getGeometryFactory(Hints hints) throws FactoryRegistryException {
/*  93 */     hints = mergeSystemHints(hints);
/*  94 */     return (GeometryFactory)getServiceRegistry().getServiceProvider(GeometryFactory.class, null, hints, Hints.GEOMETRY_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized ComplexFactory getComplexFactory(Hints hints) throws FactoryRegistryException {
/*  98 */     hints = mergeSystemHints(hints);
/*  99 */     return (ComplexFactory)getServiceRegistry().getServiceProvider(ComplexFactory.class, null, hints, Hints.COMPLEX_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized AggregateFactory getAggregateFactory(Hints hints) throws FactoryRegistryException {
/* 103 */     hints = mergeSystemHints(hints);
/* 104 */     return (AggregateFactory)getServiceRegistry().getServiceProvider(AggregateFactory.class, null, hints, Hints.AGGREGATE_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized PrimitiveFactory getPrimitiveFactory(Hints hints) throws FactoryRegistryException {
/* 108 */     hints = mergeSystemHints(hints);
/* 109 */     return (PrimitiveFactory)getServiceRegistry().getServiceProvider(PrimitiveFactory.class, null, hints, Hints.PRIMITIVE_FACTORY);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\GeometryFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */