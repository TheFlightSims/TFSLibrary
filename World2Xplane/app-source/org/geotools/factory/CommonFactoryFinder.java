/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
/*     */ import javax.imageio.spi.ServiceRegistry;
/*     */ import org.geotools.data.FeatureLockFactory;
/*     */ import org.geotools.data.FileDataStoreFactorySpi;
/*     */ import org.geotools.feature.FeatureCollections;
/*     */ import org.geotools.filter.FunctionFactory;
/*     */ import org.geotools.resources.LazySet;
/*     */ import org.geotools.styling.StyleFactory;
/*     */ import org.opengis.feature.FeatureFactory;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.filter.FilterFactory2;
/*     */ import org.opengis.filter.expression.Function;
/*     */ 
/*     */ public final class CommonFactoryFinder extends FactoryFinder {
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/*  74 */     assert Thread.holdsLock(CommonFactoryFinder.class);
/*  75 */     if (registry == null)
/*  76 */       registry = new FactoryCreator(Arrays.asList(new Class[] { StyleFactory.class, FilterFactory.class, FeatureLockFactory.class, FileDataStoreFactorySpi.class, Function.class, FunctionFactory.class, FeatureFactory.class, FeatureTypeFactory.class, FeatureCollections.class })); 
/*  89 */     return registry;
/*     */   }
/*     */   
/*     */   public static StyleFactory getStyleFactory(Hints hints) throws FactoryRegistryException {
/* 108 */     hints = mergeSystemHints(hints);
/* 109 */     return lookup(StyleFactory.class, hints, Hints.STYLE_FACTORY);
/*     */   }
/*     */   
/*     */   public static StyleFactory getStyleFactory() throws FactoryRegistryException {
/* 123 */     return getStyleFactory((Hints)null);
/*     */   }
/*     */   
/*     */   public static synchronized Set<StyleFactory> getStyleFactories(Hints hints) {
/* 133 */     hints = mergeSystemHints(hints);
/* 134 */     return (Set<StyleFactory>)new LazySet(getServiceRegistry().getServiceProviders(StyleFactory.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static synchronized Set<Function> getFunctions(Hints hints) {
/* 145 */     hints = mergeSystemHints(hints);
/* 146 */     return (Set<Function>)new LazySet(getServiceRegistry().getServiceProviders(Function.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static synchronized Set<FunctionFactory> getFunctionFactories(Hints hints) {
/* 158 */     hints = mergeSystemHints(hints);
/* 159 */     return (Set<FunctionFactory>)new LazySet(getServiceRegistry().getServiceProviders(FunctionFactory.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static FeatureLockFactory getFeatureLockFactory(Hints hints) {
/* 177 */     hints = mergeSystemHints(hints);
/* 178 */     return lookup(FeatureLockFactory.class, hints, Hints.FEATURE_LOCK_FACTORY);
/*     */   }
/*     */   
/*     */   public static synchronized Set<FeatureLockFactory> getFeatureLockFactories(Hints hints) {
/* 189 */     hints = mergeSystemHints(hints);
/* 190 */     return (Set<FeatureLockFactory>)new LazySet(getServiceRegistry().getServiceProviders(FeatureLockFactory.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static synchronized Set<FileDataStoreFactorySpi> getFileDataStoreFactories(Hints hints) {
/* 201 */     hints = mergeSystemHints(hints);
/* 202 */     return (Set<FileDataStoreFactorySpi>)new LazySet(getServiceRegistry().getServiceProviders(FileDataStoreFactorySpi.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static FeatureFactory getFeatureFactory(Hints hints) {
/* 216 */     hints = mergeSystemHints(hints);
/* 217 */     if (hints.get(Hints.FEATURE_FACTORY) == null)
/*     */       try {
/* 219 */         Class<?> lenient = Class.forName("org.geotools.feature.LenientFeatureFactoryImpl");
/* 220 */         hints.put(Hints.FEATURE_FACTORY, lenient);
/* 221 */       } catch (ClassNotFoundException e) {
/* 222 */         e.printStackTrace();
/*     */       }  
/* 225 */     return lookup(FeatureFactory.class, hints, Hints.FEATURE_FACTORY);
/*     */   }
/*     */   
/*     */   public static FeatureTypeFactory getFeatureTypeFactory(Hints hints) {
/* 238 */     hints = mergeSystemHints(hints);
/* 239 */     return lookup(FeatureTypeFactory.class, hints, Hints.FEATURE_TYPE_FACTORY);
/*     */   }
/*     */   
/*     */   public static FeatureCollections getFeatureCollections(Hints hints) {
/* 255 */     hints = mergeSystemHints(hints);
/* 256 */     return lookup(FeatureCollections.class, hints, Hints.FEATURE_COLLECTIONS);
/*     */   }
/*     */   
/*     */   public static FeatureCollections getFeatureCollections() {
/* 270 */     return getFeatureCollections((Hints)null);
/*     */   }
/*     */   
/*     */   public static synchronized Set<FeatureCollections> getFeatureCollectionsSet(Hints hints) {
/* 279 */     hints = mergeSystemHints(hints);
/* 280 */     return (Set<FeatureCollections>)new LazySet(getServiceRegistry().getServiceProviders(FeatureCollections.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static FilterFactory getFilterFactory(Hints hints) throws FactoryRegistryException {
/* 299 */     hints = mergeSystemHints(hints);
/* 300 */     return lookup(FilterFactory.class, hints, Hints.FILTER_FACTORY);
/*     */   }
/*     */   
/*     */   public static FilterFactory getFilterFactory() throws FactoryRegistryException {
/* 317 */     return getFilterFactory((Hints)null);
/*     */   }
/*     */   
/*     */   private static <T> T lookup(Class<T> category, Hints hints, Hints.Key key) {
/* 332 */     if (hints == null || key == null)
/* 333 */       return null; 
/* 337 */     Object hint = hints.get(key);
/* 338 */     if (hint != null && 
/* 339 */       category.isInstance(hint))
/* 340 */       return category.cast(hint); 
/* 345 */     synchronized (CommonFactoryFinder.class) {
/* 346 */       return getServiceRegistry().getServiceProvider(category, (ServiceRegistry.Filter)null, hints, key);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static synchronized Set<FilterFactory> getFilterFactories(Hints hints) {
/* 357 */     hints = mergeSystemHints(hints);
/* 358 */     return (Set<FilterFactory>)new LazySet(getServiceRegistry().getServiceProviders(FilterFactory.class, (ServiceRegistry.Filter)null, hints));
/*     */   }
/*     */   
/*     */   public static FilterFactory2 getFilterFactory2(Hints hints) throws FactoryRegistryException {
/* 377 */     hints = mergeSystemHints(hints);
/* 379 */     Object h = hints.get(Hints.FILTER_FACTORY);
/* 380 */     if ((h instanceof Class) ? FilterFactory2.class.isAssignableFrom((Class)h) : (h instanceof FilterFactory2)) {
/* 387 */       hints = new Hints(hints);
/* 388 */       hints.put(Hints.FILTER_FACTORY, FilterFactory2.class);
/*     */     } 
/* 390 */     return (FilterFactory2)getFilterFactory(hints);
/*     */   }
/*     */   
/*     */   public static FilterFactory2 getFilterFactory2() throws FactoryRegistryException {
/* 404 */     return getFilterFactory2((Hints)null);
/*     */   }
/*     */   
/*     */   public static synchronized void scanForPlugins() {
/* 417 */     if (registry != null)
/* 418 */       registry.scanForPlugins(); 
/*     */   }
/*     */   
/*     */   public static void reset() {
/* 426 */     FactoryRegistry copy = registry;
/* 427 */     registry = null;
/* 428 */     if (copy != null)
/* 429 */       copy.deregisterAll(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\CommonFactoryFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */