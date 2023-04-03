/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public final class DataAccessFinder {
/*  63 */   protected static final Logger LOGGER = Logging.getLogger("org.geotools.data");
/*     */   
/*     */   private static FactoryRegistry registry;
/*     */   
/*     */   public static synchronized DataAccess<FeatureType, Feature> getDataStore(Map<String, Serializable> params) throws IOException {
/*  95 */     Iterator<DataAccessFactory> ps = getAvailableDataStores();
/*  96 */     return (DataAccess)getDataStore(params, ps);
/*     */   }
/*     */   
/*     */   static DataAccess<? extends FeatureType, ? extends Feature> getDataStore(Map<String, Serializable> params, Iterator<? extends DataAccessFactory> ps) throws IOException {
/* 104 */     IOException canProcessButNotAvailable = null;
/* 105 */     while (ps.hasNext()) {
/* 106 */       DataAccessFactory fac = ps.next();
/* 107 */       boolean canProcess = false;
/*     */       try {
/* 109 */         canProcess = fac.canProcess(params);
/* 110 */       } catch (Throwable t) {
/* 111 */         LOGGER.log(Level.WARNING, "Problem asking " + fac.getDisplayName() + " if it can process request:" + t, t);
/*     */         continue;
/*     */       } 
/* 117 */       if (canProcess) {
/* 118 */         boolean isAvailable = false;
/*     */         try {
/* 120 */           isAvailable = fac.isAvailable();
/* 121 */         } catch (Throwable t) {
/* 122 */           LOGGER.log(Level.WARNING, "Difficulity checking if " + fac.getDisplayName() + " is available:" + t, t);
/*     */           continue;
/*     */         } 
/* 128 */         if (isAvailable)
/*     */           try {
/* 130 */             return fac.createDataStore(params);
/* 131 */           } catch (IOException couldNotConnect) {
/* 132 */             canProcessButNotAvailable = couldNotConnect;
/* 133 */             LOGGER.log(Level.WARNING, fac.getDisplayName() + " should be used, but could not connect", couldNotConnect);
/*     */             continue;
/*     */           }  
/* 137 */         canProcessButNotAvailable = new IOException(fac.getDisplayName() + " should be used, but is not availble. Have you installed the required drivers or jar files?");
/* 140 */         LOGGER.log(Level.WARNING, fac.getDisplayName() + " should be used, but is not availble", canProcessButNotAvailable);
/*     */       } 
/*     */     } 
/* 145 */     if (canProcessButNotAvailable != null)
/* 146 */       throw canProcessButNotAvailable; 
/* 148 */     return null;
/*     */   }
/*     */   
/*     */   public static synchronized Iterator<DataAccessFactory> getAllDataStores() {
/* 160 */     Set<DataAccessFactory> all = new HashSet<DataAccessFactory>();
/* 161 */     Iterator<DataStoreFactorySpi> allDataStores = DataStoreFinder.getAllDataStores();
/* 162 */     Iterator<DataAccessFactory> allDataAccess = getAllDataStores(getServiceRegistry(), DataAccessFactory.class);
/* 164 */     while (allDataStores.hasNext()) {
/* 165 */       DataStoreFactorySpi next = allDataStores.next();
/* 166 */       all.add(next);
/*     */     } 
/* 169 */     while (allDataAccess.hasNext())
/* 170 */       all.add(allDataAccess.next()); 
/* 173 */     return all.iterator();
/*     */   }
/*     */   
/*     */   static synchronized <T extends DataAccessFactory> Iterator<T> getAllDataStores(FactoryRegistry registry, Class<T> category) {
/* 178 */     return registry.getServiceProviders(category, null, null);
/*     */   }
/*     */   
/*     */   public static synchronized Iterator<DataAccessFactory> getAvailableDataStores() {
/* 191 */     FactoryRegistry serviceRegistry = getServiceRegistry();
/* 192 */     Set<DataAccessFactory> availableDS = getAvailableDataStores(serviceRegistry, DataAccessFactory.class);
/* 195 */     Iterator<DataStoreFactorySpi> availableDataStores = DataStoreFinder.getAvailableDataStores();
/* 197 */     while (availableDataStores.hasNext())
/* 198 */       availableDS.add(availableDataStores.next()); 
/* 201 */     return availableDS.iterator();
/*     */   }
/*     */   
/*     */   static synchronized <T extends DataAccessFactory> Set<T> getAvailableDataStores(FactoryRegistry registry, Class<T> targetClass) {
/* 206 */     Set<T> availableDS = new HashSet<T>(5);
/* 207 */     Iterator<T> it = registry.getServiceProviders(targetClass, null, null);
/* 209 */     while (it.hasNext()) {
/* 210 */       DataAccessFactory dataAccessFactory = (DataAccessFactory)it.next();
/* 212 */       if (dataAccessFactory.isAvailable())
/* 213 */         availableDS.add((T)dataAccessFactory); 
/*     */     } 
/* 217 */     return availableDS;
/*     */   }
/*     */   
/*     */   private static FactoryRegistry getServiceRegistry() {
/* 225 */     assert Thread.holdsLock(DataAccessFinder.class);
/* 226 */     if (registry == null)
/* 227 */       registry = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { DataAccessFactory.class })); 
/* 229 */     return registry;
/*     */   }
/*     */   
/*     */   public static synchronized void scanForPlugins() {
/* 243 */     DataStoreFinder.scanForPlugins();
/* 244 */     getServiceRegistry().scanForPlugins();
/*     */   }
/*     */   
/*     */   public static void reset() {
/* 251 */     FactoryRegistry copy = registry;
/* 252 */     registry = null;
/* 253 */     if (copy != null)
/* 254 */       copy.deregisterAll(); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\DataAccessFinder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */