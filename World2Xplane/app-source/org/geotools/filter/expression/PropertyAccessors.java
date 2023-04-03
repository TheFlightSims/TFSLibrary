/*     */ package org.geotools.filter.expression;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.Hints;
/*     */ 
/*     */ public class PropertyAccessors {
/*     */   static final PropertyAccessorFactory[] FACTORY_CACHE;
/*     */   
/*     */   static {
/*  41 */     List<PropertyAccessorFactory> cache = new ArrayList<PropertyAccessorFactory>();
/*  45 */     cache.add(new NullPropertyAccessorFactory());
/*  46 */     cache.add(new SimpleFeaturePropertyAccessorFactory());
/*  47 */     cache.add(new DirectPropertyAccessorFactory());
/*  48 */     Iterator factories = FactoryRegistry.lookupProviders(PropertyAccessorFactory.class);
/*  50 */     while (factories.hasNext()) {
/*  51 */       Object factory = factories.next();
/*  52 */       if (factory instanceof SimpleFeaturePropertyAccessorFactory || factory instanceof DirectPropertyAccessorFactory || factory instanceof NullPropertyAccessorFactory)
/*     */         continue; 
/*  56 */       cache.add((PropertyAccessorFactory)factory);
/*     */     } 
/*  58 */     FACTORY_CACHE = cache.<PropertyAccessorFactory>toArray(new PropertyAccessorFactory[cache.size()]);
/*     */   }
/*     */   
/*     */   public static PropertyAccessor findPropertyAccessor(Object object, String xpath, Class<?> target, Hints hints) {
/*  88 */     if (object == null)
/*  89 */       return null; 
/*  91 */     for (PropertyAccessorFactory factory : FACTORY_CACHE) {
/*  92 */       PropertyAccessor accessor = factory.createPropertyAccessor(object.getClass(), xpath, target, hints);
/*  94 */       if (accessor != null && accessor.canHandle(object, xpath, target))
/*  95 */         return accessor; 
/*     */     } 
/*  98 */     return null;
/*     */   }
/*     */   
/*     */   public static List<PropertyAccessor> findPropertyAccessors(Object object, String xpath, Class<?> target, Hints hints) {
/* 120 */     if (object == null)
/* 121 */       return null; 
/* 123 */     List<PropertyAccessor> list = new ArrayList<PropertyAccessor>();
/* 125 */     for (PropertyAccessorFactory factory : FACTORY_CACHE) {
/* 126 */       PropertyAccessor accessor = factory.createPropertyAccessor(object.getClass(), xpath, target, hints);
/* 128 */       if (accessor != null && accessor.canHandle(object, xpath, target))
/* 129 */         list.add(accessor); 
/*     */     } 
/* 132 */     return list;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\PropertyAccessors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */