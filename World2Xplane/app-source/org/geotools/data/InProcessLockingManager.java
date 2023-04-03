/*     */ package org.geotools.data;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.feature.Feature;
/*     */ import org.opengis.feature.simple.SimpleFeature;
/*     */ import org.opengis.feature.simple.SimpleFeatureType;
/*     */ import org.opengis.feature.type.FeatureType;
/*     */ 
/*     */ public class InProcessLockingManager implements LockingManager {
/*  62 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.data");
/*     */   
/*  66 */   protected Map lockTables = new HashMap<Object, Object>();
/*     */   
/*     */   public synchronized void lockFeatureID(String typeName, String featureID, Transaction transaction, FeatureLock featureLock) throws FeatureLockException {
/*  85 */     Lock lock = getLock(typeName, featureID);
/*  89 */     while (lock != null) {
/*  91 */       if (lock instanceof TransactionLock) {
/*  92 */         TransactionLock tlock = (TransactionLock)lock;
/*  94 */         if (transaction == tlock.transaction)
/*  98 */           throw new FeatureLockException("Transaction Lock is already held by this Transaction", featureID); 
/*     */         try {
/* 104 */           synchronized (tlock) {
/* 105 */             tlock.wait();
/*     */           } 
/* 108 */           lock = getLock(typeName, featureID);
/* 109 */         } catch (InterruptedException interupted) {
/* 110 */           throw new FeatureLockException("Interupted while waiting for Transaction Lock", featureID, interupted);
/*     */         } 
/*     */         continue;
/*     */       } 
/* 114 */       if (lock instanceof MemoryLock) {
/* 115 */         MemoryLock mlock = (MemoryLock)lock;
/* 116 */         throw new FeatureLockException("Feature Lock is held by Authorization " + mlock.authID, featureID);
/*     */       } 
/* 120 */       throw new FeatureLockException("Lock is already held " + lock, featureID);
/*     */     } 
/* 127 */     lock = createLock(transaction, featureLock);
/* 128 */     locks(typeName).put(featureID, lock);
/*     */   }
/*     */   
/*     */   protected Lock getLock(String typeName, String featureID) {
/* 144 */     Map locks = locks(typeName);
/* 148 */     synchronized (locks) {
/* 149 */       if (locks.containsKey(featureID)) {
/* 150 */         Lock lock = (Lock)locks.get(featureID);
/* 152 */         if (lock.isExpired()) {
/* 153 */           locks.remove(featureID);
/* 156 */           return null;
/*     */         } 
/* 160 */         return lock;
/*     */       } 
/* 166 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected synchronized Lock createLock(Transaction transaction, FeatureLock featureLock) throws FeatureLockException {
/* 184 */     if (featureLock == FeatureLock.TRANSACTION) {
/* 186 */       if (transaction == Transaction.AUTO_COMMIT)
/* 187 */         throw new FeatureLockException("We cannot issue a Transaction lock against AUTO_COMMIT"); 
/* 191 */       TransactionLock lock = (TransactionLock)transaction.getState(this);
/* 193 */       if (lock == null) {
/* 194 */         lock = new TransactionLock();
/* 195 */         transaction.putState(this, lock);
/* 197 */         return lock;
/*     */       } 
/* 199 */       return lock;
/*     */     } 
/* 202 */     return new MemoryLock(featureLock);
/*     */   }
/*     */   
/*     */   public Map locks(String typeName) {
/* 214 */     synchronized (this.lockTables) {
/* 215 */       if (this.lockTables.containsKey(typeName))
/* 216 */         return (Map)this.lockTables.get(typeName); 
/* 218 */       Map<Object, Object> locks = new HashMap<Object, Object>();
/* 219 */       this.lockTables.put(typeName, locks);
/* 221 */       return locks;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Set allLocks() {
/* 232 */     synchronized (this.lockTables) {
/* 233 */       Set set = new HashSet();
/* 236 */       for (Iterator<Map> i = this.lockTables.values().iterator(); i.hasNext(); ) {
/* 237 */         Map fidLocks = i.next();
/* 238 */         set.addAll(fidLocks.values());
/*     */       } 
/* 241 */       return set;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void assertAccess(String typeName, String featureID, Transaction transaction) throws FeatureLockException {
/* 281 */     Lock lock = getLock(typeName, featureID);
/* 286 */     if (lock != null && !lock.isAuthorized(transaction))
/* 287 */       throw new FeatureLockException("Transaction does not have authorization for " + typeName + ":" + featureID); 
/*     */   }
/*     */   
/*     */   public FeatureWriter<SimpleFeatureType, SimpleFeature> checkedWriter(final FeatureWriter<SimpleFeatureType, SimpleFeature> writer, final Transaction transaction) {
/* 303 */     SimpleFeatureType featureType = writer.getFeatureType();
/* 304 */     final String typeName = featureType.getTypeName();
/* 306 */     return new DelegatingFeatureWriter<SimpleFeatureType, SimpleFeature>() {
/* 307 */         SimpleFeature live = null;
/*     */         
/*     */         public FeatureWriter<SimpleFeatureType, SimpleFeature> getDelegate() {
/* 310 */           return writer;
/*     */         }
/*     */         
/*     */         public SimpleFeatureType getFeatureType() {
/* 314 */           return (SimpleFeatureType)writer.getFeatureType();
/*     */         }
/*     */         
/*     */         public SimpleFeature next() throws IOException {
/* 318 */           this.live = (SimpleFeature)writer.next();
/* 320 */           return this.live;
/*     */         }
/*     */         
/*     */         public void remove() throws IOException {
/* 324 */           if (this.live != null)
/* 325 */             InProcessLockingManager.this.assertAccess(typeName, this.live.getID(), transaction); 
/* 328 */           writer.remove();
/* 329 */           this.live = null;
/*     */         }
/*     */         
/*     */         public void write() throws IOException {
/* 333 */           if (this.live != null)
/* 334 */             InProcessLockingManager.this.assertAccess(typeName, this.live.getID(), transaction); 
/* 337 */           writer.write();
/* 338 */           this.live = null;
/*     */         }
/*     */         
/*     */         public boolean hasNext() throws IOException {
/* 342 */           this.live = null;
/* 344 */           return writer.hasNext();
/*     */         }
/*     */         
/*     */         public void close() throws IOException {
/* 348 */           this.live = null;
/* 349 */           if (writer != null)
/* 350 */             writer.close(); 
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   public synchronized void unLockFeatureID(String typeName, String featureID, Transaction transaction, FeatureLock featureLock) throws IOException {
/* 368 */     assertAccess(typeName, featureID, transaction);
/* 369 */     locks(typeName).remove(featureID);
/*     */   }
/*     */   
/*     */   public synchronized boolean refresh(String authID, Transaction transaction) throws IOException {
/* 389 */     if (authID == null)
/* 390 */       throw new IllegalArgumentException("lockID required"); 
/* 393 */     if (transaction == null || transaction == Transaction.AUTO_COMMIT)
/* 394 */       throw new IllegalArgumentException("Tansaction required (with authorization for " + authID + ")"); 
/* 399 */     boolean refresh = false;
/* 401 */     for (Iterator<Lock> i = allLocks().iterator(); i.hasNext(); ) {
/* 402 */       Lock lock = i.next();
/* 404 */       if (lock.isExpired()) {
/* 405 */         i.remove();
/*     */         continue;
/*     */       } 
/* 406 */       if (lock.isMatch(authID)) {
/* 407 */         if (lock.isAuthorized(transaction)) {
/* 408 */           lock.refresh();
/* 409 */           refresh = true;
/*     */           continue;
/*     */         } 
/* 411 */         throw new IOException("Not authorized to refresh " + lock);
/*     */       } 
/*     */     } 
/* 416 */     return refresh;
/*     */   }
/*     */   
/*     */   public boolean release(String authID, Transaction transaction) throws IOException {
/* 439 */     if (authID == null)
/* 440 */       throw new IllegalArgumentException("lockID required"); 
/* 443 */     if (transaction == null || transaction == Transaction.AUTO_COMMIT)
/* 444 */       throw new IllegalArgumentException("Tansaction required (with authorization for " + authID + ")"); 
/* 449 */     boolean release = false;
/* 457 */     for (Iterator<Map> i = this.lockTables.values().iterator(); i.hasNext(); ) {
/* 458 */       Map fidMap = i.next();
/* 459 */       Set<String> unLockedFids = new HashSet();
/* 461 */       for (Iterator<String> j = fidMap.keySet().iterator(); j.hasNext(); ) {
/* 462 */         String fid = j.next();
/* 463 */         Lock lock = (Lock)fidMap.get(fid);
/* 467 */         if (lock.isExpired()) {
/* 468 */           unLockedFids.add(fid);
/*     */           continue;
/*     */         } 
/* 471 */         if (lock.isMatch(authID)) {
/* 475 */           if (lock.isAuthorized(transaction)) {
/* 476 */             unLockedFids.add(fid);
/* 479 */             release = true;
/*     */             continue;
/*     */           } 
/* 481 */           throw new IOException("Not authorized to release " + lock);
/*     */         } 
/*     */       } 
/* 487 */       for (Iterator<String> k = unLockedFids.iterator(); k.hasNext();)
/* 488 */         fidMap.remove(k.next()); 
/*     */     } 
/* 492 */     return release;
/*     */   }
/*     */   
/*     */   public boolean exists(String authID) {
/* 512 */     if (authID == null)
/* 513 */       return false; 
/* 518 */     for (Iterator<Lock> i = allLocks().iterator(); i.hasNext(); ) {
/* 519 */       Lock lock = i.next();
/* 521 */       if (lock.isExpired()) {
/* 522 */         i.remove();
/*     */         continue;
/*     */       } 
/* 523 */       if (lock.isMatch(authID))
/* 524 */         return true; 
/*     */     } 
/* 528 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isLocked(String typeName, String featureID) {
/* 540 */     return (getLock(typeName, featureID) != null);
/*     */   }
/*     */   
/*     */   static interface Lock {
/*     */     boolean isExpired();
/*     */     
/*     */     boolean isMatch(String param1String);
/*     */     
/*     */     boolean isAuthorized(Transaction param1Transaction);
/*     */     
/*     */     void refresh();
/*     */     
/*     */     void release();
/*     */   }
/*     */   
/*     */   class TransactionLock implements Lock, Transaction.State {
/*     */     Transaction transaction;
/*     */     
/*     */     public boolean isMatch(String authID) {
/* 619 */       return false;
/*     */     }
/*     */     
/*     */     public boolean isExpired() {
/* 628 */       return (this.transaction != null);
/*     */     }
/*     */     
/*     */     public void release() {
/* 635 */       this.transaction = null;
/* 636 */       notifyAll();
/*     */     }
/*     */     
/*     */     public void refresh() {}
/*     */     
/*     */     public boolean isAuthorized(Transaction transaction) {
/* 655 */       return (this.transaction == transaction);
/*     */     }
/*     */     
/*     */     public void addAuthorization(String AuthID) throws IOException {}
/*     */     
/*     */     public void commit() throws IOException {
/* 675 */       release();
/*     */     }
/*     */     
/*     */     public void rollback() throws IOException {
/* 684 */       release();
/*     */     }
/*     */     
/*     */     public void setTransaction(Transaction transaction) {
/* 693 */       if (transaction == null)
/* 694 */         release(); 
/* 697 */       this.transaction = transaction;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 701 */       return "TranasctionLock(" + (!isExpired() ? 1 : 0) + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   class MemoryLock implements Lock {
/*     */     String authID;
/*     */     
/*     */     long duration;
/*     */     
/*     */     long expiry;
/*     */     
/*     */     MemoryLock(FeatureLock lock) {
/* 720 */       this(lock.getAuthorization(), lock.getDuration());
/*     */     }
/*     */     
/*     */     MemoryLock(String id, long length) {
/* 724 */       this.authID = id;
/* 725 */       this.duration = length;
/* 726 */       this.expiry = System.currentTimeMillis() + length;
/*     */     }
/*     */     
/*     */     public boolean isMatch(String id) {
/* 730 */       return this.authID.equals(id);
/*     */     }
/*     */     
/*     */     public void refresh() {
/* 734 */       this.expiry = System.currentTimeMillis() + this.duration;
/*     */     }
/*     */     
/*     */     public void release() {}
/*     */     
/*     */     public boolean isExpired() {
/* 741 */       if (this.duration == 0L)
/* 742 */         return false; 
/* 745 */       long now = System.currentTimeMillis();
/* 747 */       return (now >= this.expiry);
/*     */     }
/*     */     
/*     */     public boolean isAuthorized(Transaction transaction) {
/* 755 */       return (transaction != Transaction.AUTO_COMMIT && transaction.getAuthorizations().contains(this.authID));
/*     */     }
/*     */     
/*     */     public String toString() {
/* 760 */       if (this.duration == 0L)
/* 761 */         return "MemoryLock(" + this.authID + "|PermaLock)"; 
/* 764 */       long now = System.currentTimeMillis();
/* 765 */       long delta = this.expiry - now;
/* 766 */       long dur = this.duration;
/* 768 */       return "MemoryLock(" + this.authID + "|" + delta + "ms|" + dur + "ms)";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\InProcessLockingManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */