/*     */ package org.geotools.data.directory;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataAccessFactory;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.DataStoreFactorySpi;
/*     */ import org.geotools.data.DataStoreFinder;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ class DirectoryTypeCache {
/*  55 */   static final Logger LOGGER = Logging.getLogger(DirectoryTypeCache.class);
/*     */   
/*  61 */   Map<String, FileEntry> ftCache = new ConcurrentHashMap<String, FileEntry>();
/*     */   
/*     */   File directory;
/*     */   
/*     */   DirectoryWatcher watcher;
/*     */   
/*  77 */   ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
/*     */   
/*     */   FileStoreFactory factory;
/*     */   
/*     */   DirectoryTypeCache(File directory, FileStoreFactory factory) throws IOException {
/*  93 */     if (directory == null)
/*  94 */       throw new NullPointerException("Directory parameter should be not null"); 
/*  97 */     if (!directory.exists())
/*  98 */       throw new IllegalArgumentException("Specified directory does not exists: " + directory.getAbsolutePath()); 
/* 103 */     if (!directory.isDirectory())
/* 104 */       throw new IllegalArgumentException("Specified path is not a directory, it'a s file instead: " + directory.getAbsolutePath()); 
/* 109 */     this.directory = directory;
/* 110 */     this.factory = factory;
/* 112 */     this.watcher = new ImmediateDirectoryWatcher(directory);
/*     */   }
/*     */   
/*     */   DataStore getDataStore(String typeName, boolean forceUpdate) throws IOException {
/* 124 */     this.lock.readLock().lock();
/*     */     try {
/* 126 */       if (forceUpdate)
/* 127 */         updateCache(); 
/* 130 */       FileEntry fileEntry = this.ftCache.get(typeName);
/* 131 */       if (fileEntry == null)
/* 132 */         throw new IOException("Not available: " + typeName); 
/* 134 */       return fileEntry.getStore(true);
/*     */     } finally {
/* 136 */       this.lock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   Set<String> getTypeNames() throws IOException {
/* 145 */     this.lock.readLock().lock();
/*     */     try {
/* 148 */       updateCache();
/* 149 */       return this.ftCache.keySet();
/*     */     } finally {
/* 151 */       this.lock.readLock().unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   List<DataStore> getDataStores() {
/* 161 */     List<DataStore> stores = new ArrayList<DataStore>();
/* 162 */     this.lock.readLock().lock();
/*     */     try {
/* 165 */       for (FileEntry entry : this.ftCache.values()) {
/*     */         try {
/* 167 */           DataStore store = entry.getStore(false);
/* 168 */           if (store != null)
/* 169 */             stores.add(store); 
/* 170 */         } catch (Exception e) {
/* 171 */           LOGGER.log(Level.FINE, "Error occurred trying to grab a datastore", e);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 175 */       this.lock.readLock().unlock();
/*     */     } 
/* 178 */     return stores;
/*     */   }
/*     */   
/*     */   private void updateCache() throws IOException {
/* 187 */     if (this.watcher.isStale()) {
/* 189 */       this.lock.readLock().unlock();
/* 190 */       this.lock.writeLock().lock();
/*     */       try {
/* 194 */         if (this.watcher.isStale()) {
/* 195 */           this.watcher.mark();
/* 196 */           refreshCacheContents();
/*     */         } 
/*     */       } finally {
/* 200 */         this.lock.readLock().lock();
/* 201 */         this.lock.writeLock().unlock();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void refreshCacheContents() throws IOException {
/* 226 */     Map<String, FileEntry> result = new TreeMap<String, FileEntry>();
/* 229 */     Map<File, FileEntry> fileCache = new HashMap<File, FileEntry>();
/* 230 */     for (FileEntry entry : this.ftCache.values())
/* 231 */       fileCache.put(entry.file, entry); 
/* 235 */     for (File file : this.directory.listFiles()) {
/* 237 */       if (!file.isDirectory()) {
/* 243 */         FileEntry entry = fileCache.get(file);
/* 246 */         if (entry == null) {
/* 247 */           DataStore store = this.factory.getDataStore(file);
/* 248 */           if (store != null)
/* 249 */             entry = new FileEntry(file, store); 
/*     */         } 
/* 254 */         if (entry != null)
/* 255 */           for (String typeName : entry.getStore(true).getTypeNames()) {
/* 257 */             if (!result.containsKey(typeName)) {
/* 258 */               result.put(typeName, entry);
/*     */             } else {
/* 260 */               LOGGER.log(Level.WARNING, "Type name " + typeName + " is available from multiple datastores");
/*     */             } 
/*     */           }  
/*     */       } 
/*     */     } 
/* 274 */     Set<String> removedFTs = new HashSet<String>(this.ftCache.keySet());
/* 275 */     removedFTs.removeAll(result.keySet());
/* 281 */     Set<FileEntry> disposable = new HashSet<FileEntry>();
/* 282 */     for (String removedFT : removedFTs)
/* 283 */       disposable.add(this.ftCache.remove(removedFT)); 
/* 285 */     for (FileEntry entry : result.values())
/* 286 */       disposable.remove(entry); 
/* 288 */     for (FileEntry entry : disposable)
/* 289 */       entry.dispose(); 
/* 293 */     Set<String> added = new HashSet<String>(result.keySet());
/* 294 */     added.removeAll(this.ftCache.keySet());
/* 295 */     for (String newFeatureType : added)
/* 296 */       this.ftCache.put(newFeatureType, result.get(newFeatureType)); 
/*     */   }
/*     */   
/*     */   List<FactoryAdapter> lookupFileDataStores() {
/* 307 */     List<FactoryAdapter> adapters = new ArrayList<FactoryAdapter>();
/* 310 */     Iterator<DataStoreFactorySpi> it = DataStoreFinder.getAllDataStores();
/* 311 */     while (it.hasNext()) {
/* 312 */       DataStoreFactorySpi factory = it.next();
/* 313 */       DataAccessFactory.Param[] params = factory.getParametersInfo();
/* 315 */       if (params == null) {
/* 316 */         LOGGER.fine("DataStore factory " + factory + " returns null from getParametersInfo!");
/*     */         continue;
/*     */       } 
/* 320 */       DataAccessFactory.Param fileParam = null;
/* 321 */       DataAccessFactory.Param nsParam = null;
/* 322 */       for (DataAccessFactory.Param param : params) {
/* 323 */         Class<?> type = param.type;
/* 324 */         String key = param.key;
/* 325 */         if (File.class.isAssignableFrom(type) || URL.class.isAssignableFrom(type)) {
/* 327 */           fileParam = param;
/* 328 */         } else if (key.equalsIgnoreCase("namespace") && (String.class.isAssignableFrom(type) || URI.class.isAssignableFrom(type))) {
/* 331 */           nsParam = param;
/*     */         } 
/*     */       } 
/* 334 */       if (fileParam != null)
/* 335 */         adapters.add(new FactoryAdapter(factory, fileParam, nsParam)); 
/*     */     } 
/* 338 */     return adapters;
/*     */   }
/*     */   
/*     */   void dispose() {
/* 349 */     for (FileEntry entry : this.ftCache.values())
/* 350 */       entry.dispose(); 
/*     */   }
/*     */   
/*     */   class DirectoryFilter implements FileFilter {
/*     */     public boolean accept(File pathname) {
/* 363 */       return !pathname.isDirectory();
/*     */     }
/*     */   }
/*     */   
/*     */   class FileEntry {
/*     */     File file;
/*     */     
/*     */     SoftReference<DataStore> ref;
/*     */     
/*     */     public FileEntry(File file, DataStore store) {
/* 374 */       this.file = file;
/* 375 */       this.ref = new DataStoreSoftReference(store);
/*     */     }
/*     */     
/*     */     DataStore getStore(boolean force) throws IOException {
/* 379 */       DataStore store = (this.ref != null) ? this.ref.get() : null;
/* 380 */       if (store == null && force) {
/* 381 */         store = DirectoryTypeCache.this.factory.getDataStore(this.file);
/* 382 */         this.ref = new DataStoreSoftReference(store);
/*     */       } 
/* 384 */       return store;
/*     */     }
/*     */     
/*     */     void dispose() {
/* 388 */       DataStore store = (this.ref != null) ? this.ref.get() : null;
/* 389 */       if (store != null)
/* 390 */         store.dispose(); 
/* 391 */       this.ref.clear();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryTypeCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */