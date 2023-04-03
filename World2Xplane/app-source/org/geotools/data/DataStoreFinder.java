/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public final class DataStoreFinder {
/*  59 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   public static synchronized DataStore getDataStore(Map<String, Serializable> params) throws IOException {
/*  87 */     Iterator<DataStoreFactorySpi> ps = getAvailableDataStores();
/*  89 */     DataAccess<? extends FeatureType, ? extends Feature> dataStore = DataAccessFinder.getDataStore(params, (Iterator)ps);
/*  90 */     return (DataStore)dataStore;
/*     */   }
/*     */   
/*     */   public static synchronized Iterator<DataStoreFactorySpi> getAllDataStores() {
/* 102 */     return DataAccessFinder.getAllDataStores(getServiceRegistry(), DataStoreFactorySpi.class);
/*     */   }
/*     */   
/*     */   public static synchronized Iterator<DataStoreFactorySpi> getAvailableDataStores() {
/* 115 */     FactoryRegistry serviceRegistry = getServiceRegistry();
/* 116 */     Set<DataStoreFactorySpi> availableDS = DataAccessFinder.getAvailableDataStores(serviceRegistry, DataStoreFactorySpi.class);
/* 117 */     return availableDS.iterator();
/*     */   }
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/* 125 */     assert Thread.holdsLock(DataStoreFinder.class);
/* 126 */     if (registry == null)
/* 127 */       registry = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { DataStoreFactorySpi.class })); 
/* 130 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized void scanForPlugins() {
/* 145 */     getServiceRegistry().scanForPlugins();
/*     */   }
/*     */   
/*     */   public static void reset() {
/* 153 */     FactoryRegistry copy = registry;
/* 154 */     registry = null;
/* 155 */     if (copy != null)
/* 156 */       copy.deregisterAll(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataStoreFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */