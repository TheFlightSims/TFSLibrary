/*     */ package org.geotools.data.shapefile.index;
/*     */ 
/*     */ public class LockManager {
/*     */   private static final int EXCLUSIVE_LOCK_TIMEOUT = 20;
/*     */   
/*     */   private static final int SHARED_LOCK_TIMEOUT = 10;
/*     */   
/*     */   public static final short READ = 1;
/*     */   
/*     */   public static final short WRITE = 2;
/*     */   
/*     */   private Lock exclusiveLock;
/*     */   
/*     */   private int leases;
/*     */   
/*     */   public synchronized void release(Lock lock) {
/*  39 */     LockImpl li = (LockImpl)lock;
/*  41 */     if (li.getType() == 2) {
/*  42 */       this.exclusiveLock = null;
/*     */     } else {
/*  44 */       this.leases--;
/*     */     } 
/*  47 */     notify();
/*     */   }
/*     */   
/*     */   public synchronized Lock aquireExclusive() throws LockTimeoutException {
/*  58 */     int cnt = 0;
/*  61 */     while ((this.exclusiveLock != null || this.leases > 0) && cnt < 20) {
/*  62 */       cnt++;
/*     */       try {
/*  65 */         wait(500L);
/*  66 */       } catch (InterruptedException e) {
/*  67 */         throw new LockTimeoutException(e);
/*     */       } 
/*     */     } 
/*  71 */     if (this.exclusiveLock != null || this.leases > 0)
/*  72 */       throw new LockTimeoutException("Timeout aquiring exclusive lock"); 
/*  75 */     this.exclusiveLock = new LockImpl((short)2);
/*  77 */     return this.exclusiveLock;
/*     */   }
/*     */   
/*     */   public synchronized Lock aquireShared() throws LockTimeoutException {
/*  88 */     int cnt = 0;
/*  90 */     while (this.exclusiveLock != null && cnt < 10) {
/*  91 */       cnt++;
/*     */       try {
/*  94 */         wait(500L);
/*  95 */       } catch (InterruptedException e) {
/*  96 */         throw new LockTimeoutException(e);
/*     */       } 
/*     */     } 
/* 100 */     if (this.exclusiveLock != null)
/* 101 */       throw new LockTimeoutException("Timeout aquiring shared lock"); 
/* 104 */     this.leases++;
/* 106 */     return new LockImpl((short)1);
/*     */   }
/*     */   
/*     */   private class LockImpl implements Lock {
/*     */     private short type;
/*     */     
/*     */     public LockImpl(short type) {
/* 123 */       this.type = type;
/*     */     }
/*     */     
/*     */     public short getType() {
/* 130 */       return this.type;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\index\LockManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */