/*     */ package org.geotools.data.directory;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import org.geotools.data.DataStore;
/*     */ import org.geotools.data.FeatureLock;
/*     */ import org.geotools.data.LockingManager;
/*     */ import org.geotools.data.Transaction;
/*     */ 
/*     */ public class DirectoryLockingManager implements LockingManager {
/*     */   DirectoryTypeCache cache;
/*     */   
/*     */   public DirectoryLockingManager(DirectoryTypeCache cache) {
/*  42 */     this.cache = cache;
/*     */   }
/*     */   
/*     */   public boolean exists(String authID) {
/*  46 */     List<DataStore> stores = this.cache.getDataStores();
/*  47 */     for (DataStore store : stores) {
/*  48 */       if (store.getLockingManager() != null && store.getLockingManager().exists(authID))
/*  50 */         return true; 
/*     */     } 
/*  54 */     return false;
/*     */   }
/*     */   
/*     */   public boolean release(String authID, Transaction transaction) throws IOException {
/*  59 */     List<DataStore> stores = this.cache.getDataStores();
/*  60 */     for (DataStore store : stores) {
/*  61 */       if (store.getLockingManager() != null && store.getLockingManager().exists(authID))
/*  63 */         return store.getLockingManager().release(authID, transaction); 
/*     */     } 
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public boolean refresh(String authID, Transaction transaction) throws IOException {
/*  72 */     List<DataStore> stores = this.cache.getDataStores();
/*  73 */     for (DataStore store : stores) {
/*  74 */       if (store.getLockingManager() != null && store.getLockingManager().exists(authID))
/*  76 */         return store.getLockingManager().refresh(authID, transaction); 
/*     */     } 
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public void unLockFeatureID(String typeName, String authID, Transaction transaction, FeatureLock featureLock) throws IOException {
/*  86 */     DataStore store = this.cache.getDataStore(typeName, false);
/*  88 */     if (store != null && store.getLockingManager() != null)
/*  89 */       store.getLockingManager().unLockFeatureID(typeName, authID, transaction, featureLock); 
/*     */   }
/*     */   
/*     */   public void lockFeatureID(String typeName, String authID, Transaction transaction, FeatureLock featureLock) throws IOException {
/*  97 */     DataStore store = this.cache.getDataStore(typeName, false);
/*  99 */     if (store != null && store.getLockingManager() != null)
/* 100 */       store.getLockingManager().lockFeatureID(typeName, authID, transaction, featureLock); 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\directory\DirectoryLockingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */